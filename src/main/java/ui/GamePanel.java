package ui;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Point;

import engine.GameEngine;
import tiles.TileType;

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
	public boolean keyDown(Event evt, int key) {
		if (key == Event.LEFT) {
			gameEngine.keyLeft();
		} else if (key == Event.RIGHT) {
			gameEngine.keyRight();
		} else if (key == Event.UP) {
			gameEngine.keyUp();
		} else if (key == Event.DOWN) {
			gameEngine.keyDown();
		} else if (key == Event.ENTER) {
			gameEngine.keySelect();
		}

		return true;
	}
}
