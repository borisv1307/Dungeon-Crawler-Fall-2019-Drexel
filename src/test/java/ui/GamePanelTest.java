package ui;

import static org.junit.Assert.assertSame;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import characters.CharacterClass;
import engine.GameEngine;

public class GamePanelTest {

	int width = 50;
	int horizontalDimension = 5;
	int height = 60;
	int verticalDimension = 6;
	int tileWidth = width / horizontalDimension;
	int tileHeight = height / verticalDimension;

	GamePanel gamePanel;
	GameEngine gameEngine;
	TilePainter tilePainter;
	CharacterPainter characterPainter;
	CharacterSelectionPainter characterSelectionPainter;

	@Before
	public void setUp() throws Exception {
		gameEngine = Mockito.mock(GameEngine.class);
		tilePainter = Mockito.mock(TilePainter.class);
		characterPainter = Mockito.mock(CharacterPainter.class);
		characterSelectionPainter = Mockito.mock(CharacterSelectionPainter.class);
		Mockito.when(gameEngine.getLevelHorizontalDimension()).thenReturn(horizontalDimension);
		Mockito.when(gameEngine.getLevelVerticalDimension()).thenReturn(verticalDimension);
		gamePanel = new GamePanel(gameEngine, tilePainter, characterPainter, characterSelectionPainter);
		gamePanel.setSize(width, height);
		gamePanel.init();
	}

	@Test
	public void paint() {
		Graphics graphics = Mockito.mock(Graphics.class);
		int playerXCoordinate = 2;
		int playerYCoordinate = 3;
		Point playerLocationInPixels = new Point(playerXCoordinate * tileWidth, playerYCoordinate * tileHeight);

		Mockito.when(gameEngine.getPlayerXCoordinate()).thenReturn(playerXCoordinate);
		Mockito.when(gameEngine.getPlayerYCoordinate()).thenReturn(playerYCoordinate);
		Mockito.when(gameEngine.getSelectedCharacter()).thenReturn(CharacterClass.WARRIOR);
		Mockito.when(
				tilePainter.getTileLocation(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt(), Mockito.anyInt()))
				.thenReturn(playerLocationInPixels);
		gamePanel.paint(graphics);
		Mockito.verify(tilePainter).paintTiles(graphics, gameEngine, tileWidth, tileHeight);
		Mockito.verify(characterPainter).paintPlayer(graphics, playerLocationInPixels.x, playerLocationInPixels.y,
				tileWidth, tileHeight, CharacterClass.WARRIOR);
		Mockito.verify(characterSelectionPainter).paintMenu(graphics, gameEngine, width, height);
	}

	@Test
	public void update() {
		Graphics dbg = Mockito.mock(Graphics.class);
		Image dbImage = Mockito.mock(Image.class);
		Mockito.when(dbImage.getGraphics()).thenReturn(dbg);

		gamePanel = Mockito.mock(GamePanel.class, Mockito.CALLS_REAL_METHODS);
		Mockito.when(gamePanel.getWidth()).thenReturn(width);
		Mockito.when(gamePanel.getHeight()).thenReturn(height);
		Mockito.when(gamePanel.createImage(width, height)).thenReturn(dbImage);
		Mockito.doNothing().when(gamePanel).paint(dbg);
		Graphics graphics = Mockito.mock(Graphics.class);
		gamePanel.update(graphics);
		gamePanel.update(graphics);
		Mockito.verify(gamePanel, Mockito.times(1)).createImage(width, height);
		Mockito.verify(gamePanel, Mockito.times(3)).getWidth();
		Mockito.verify(gamePanel, Mockito.times(3)).getHeight();
		Mockito.verify(gamePanel, Mockito.times(2)).paint(dbg);
		Mockito.verify(dbImage, Mockito.times(2)).getGraphics();

		Mockito.verify(graphics, Mockito.times(2)).drawImage(dbImage, 0, 0, gamePanel);
	}

	private void pressKey(int key) {
		KeyEvent evt = Mockito.mock(KeyEvent.class);
		Mockito.when(evt.getID()).thenReturn(KeyEvent.KEY_PRESSED);
		Mockito.when(evt.getKeyCode()).thenReturn(key);
		gamePanel.processKeyEvent(evt);

		evt = Mockito.mock(KeyEvent.class);
		Mockito.when(evt.getID()).thenReturn(KeyEvent.KEY_RELEASED);
		Mockito.when(evt.getKeyCode()).thenReturn(key);
		gamePanel.processKeyEvent(evt);
	}

	@Test
	public void key_left() {
		pressKey(KeyEvent.VK_LEFT);
		Mockito.verify(gameEngine, Mockito.times(1)).keyLeft();
	}

	@Test
	public void key_right() {
		pressKey(KeyEvent.VK_RIGHT);
		Mockito.verify(gameEngine, Mockito.times(1)).keyRight();
	}

	@Test
	public void key_up() {
		pressKey(KeyEvent.VK_UP);
		Mockito.verify(gameEngine, Mockito.times(1)).keyUp();
	}

	@Test
	public void key_down() {
		pressKey(KeyEvent.VK_DOWN);
		Mockito.verify(gameEngine, Mockito.times(1)).keyDown();
	}

	@Test
	public void key_enter() {
		pressKey(KeyEvent.VK_ENTER);
		Mockito.verify(gameEngine, Mockito.times(1)).keySelect();
	}
}
