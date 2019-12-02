package ui;

import java.awt.AWTEvent;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;
import java.awt.event.KeyEvent;

import engine.GameEngine;

public class GamePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Image dbImage;
	private final GameEngine gameEngine;
	private final TilePainter tilePainter;
	private final CharacterPainter characterPainter;
	private final CharacterSelectionPainter characterSelectionPainter;
	private int tileWidth;
	private int tileHeight;

	public GamePanel(GameEngine gameEngine, TilePainter tilePainter, CharacterPainter characterPainter,
			CharacterSelectionPainter characterSelectionPainter) {
		this.gameEngine = gameEngine;
		this.tilePainter = tilePainter;
		this.characterPainter = characterPainter;
		this.characterSelectionPainter = characterSelectionPainter;
		repaint();
	}

	void init() {
		this.enableEvents(AWTEvent.KEY_EVENT_MASK);
		tileWidth = this.getWidth() / gameEngine.getLevelHorizontalDimension();
		tileHeight = this.getHeight() / gameEngine.getLevelVerticalDimension();
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		requestFocusInWindow();
		tilePainter.paintTiles(graphics, gameEngine, tileWidth, tileHeight);

		Point playerLoc = tilePainter.getTileLocation(tileWidth, tileHeight, gameEngine.getPlayerXCoordinate(),
				gameEngine.getPlayerYCoordinate());
		characterPainter.paintPlayer(graphics, playerLoc.x, playerLoc.y, tileWidth, tileHeight,
				gameEngine.getSelectedCharacter());

		characterSelectionPainter.paintMenu(graphics, gameEngine, this.getWidth(), this.getHeight());
	}

	@Override
	public void update(Graphics graphics) {
		if (dbImage == null) {
			dbImage = createImage(getWidth(), getHeight());
		}
		Graphics dbg = dbImage.getGraphics();
		dbg.setColor(getBackground());
		dbg.fillRect(0, 0, getWidth(), getHeight());
		dbg.setColor(getForeground());
		paint(dbg);
		graphics.drawImage(dbImage, 0, 0, this);
	}

	@Override
	public void processKeyEvent(KeyEvent evt) {
		if (evt.getID() != KeyEvent.KEY_PRESSED) {
			return;
		}

		int key = evt.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			gameEngine.keyLeft();
		} else if (key == KeyEvent.VK_RIGHT) {
			gameEngine.keyRight();
		} else if (key == KeyEvent.VK_UP) {
			gameEngine.keyUp();
		} else if (key == KeyEvent.VK_DOWN) {
			gameEngine.keyDown();
		} else if (key == KeyEvent.VK_ENTER) {
			gameEngine.keySelect();
		}
	}
}
