package ui;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

import engine.GameEngine;
import movement.Movement;
import tiles.TileType;

public class GamePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Image dbImage;
	private final GameEngine gameEngine;
	private final TilePainter tilePainter;
	private int tileWidth;
	private int tileHeight;
	private Movement movement;

	public GamePanel(GameEngine gameEngine, TilePainter tilePainter, Movement movement) {
		this.gameEngine = gameEngine;
		this.tilePainter = tilePainter;
		this.movement = movement;
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

		tilePainter.paintPlayer(graphics, gameEngine.getPlayerXCoordinate(), gameEngine.getPlayerYCoordinate(),
				tileWidth, tileHeight, TileType.PLAYER);

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
			movement.keyLeft();
		} else if (key == Event.RIGHT) {
			movement.keyRight();
		} else if (key == Event.UP) {
			movement.keyUp();
		} else if (key == Event.DOWN) {
			movement.keyDown();
		}

		return true;
	}
}
