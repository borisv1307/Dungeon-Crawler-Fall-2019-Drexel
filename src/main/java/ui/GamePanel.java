package ui;

import java.awt.*;

import engine.GameEngine;
import tiles.TileType;
import values.TunableParameters;

public class GamePanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Image dbImage;
	private final GameEngine gameEngine;
	private final TilePainter tilePainter;
	private int tileWidth;
	private int tileHeight;

	public GamePanel(GameEngine gameEngine, TilePainter tilePainter) {
		this.gameEngine = gameEngine;
		this.tilePainter = tilePainter;
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
		for(Point a:gameEngine.getNpcList()){
			tilePainter.paintPlayer(graphics,(int)a.getX() , (int)a.getY(),
					tileWidth, tileHeight, TileType.NPC);
		}
		tilePainter.paintPlayer(graphics, gameEngine.getPlayerXCoordinate(), gameEngine.getPlayerYCoordinate(),
				tileWidth, tileHeight, TileType.PLAYER);
		if(gameEngine.isWin()){
			graphics.setColor(Color.RED);
			graphics.setFont(new Font("微软雅黑",Font.BOLD, 50));
			graphics.drawString("YOU WIN", TunableParameters.SCREEN_WIDTH /2-125,
					TunableParameters.SCREEN_HEIGHT/2);
		}
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
		}

		return true;
	}
}
