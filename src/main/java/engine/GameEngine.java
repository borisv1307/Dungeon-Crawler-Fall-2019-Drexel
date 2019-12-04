package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

public class GameEngine {

	public static final int WIDTH = 640;
	private boolean exit;
	private final LevelCreator levelCreator;

	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private final int level;
	private int p = 0;
	public TileType tile;
	private GameFrame gameFrame;

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
	}

	public void run(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
		for (Component component : gameFrame.getComponents()) {
			component.repaint();
		}
	}

	public void addTile(int x, int y, TileType tileType) {
		if (tileType.equals(TileType.PLAYER)) {
			setPlayer(x, y);
			tiles.put(new Point(x, y), TileType.PASSABLE);
		} else {

			tiles.put(new Point(x, y), tileType);

		}
	}

	public void setLevelHorizontalDimension(int levelHorizontalDimension) {
		this.levelHorizontalDimension = levelHorizontalDimension;
	}

	public void setLevelVerticalDimension(int levelVerticalDimension) {
		this.levelVerticalDimension = levelVerticalDimension;
	}

	public int getLevelHorizontalDimension() {
		return levelHorizontalDimension;
	}

	public int getLevelVerticalDimension() {
		return levelVerticalDimension;
	}

	public TileType getTileFromCoordinates(int x, int y) {
		return tiles.get(new Point(x, y));
	}

	private void setPlayer(int x, int y) {
		player = new Point(x, y);
	}

	private void setEnemy(int x, int y) {
		tiles.put(new Point(x, y), TileType.PLAYER);

	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public void keyLeft() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType player1 = getTileFromCoordinates(x - 1, y);
		if (player1.equals(TileType.PASSABLE)) {
			setPlayer(x - 1, y);
			for (int i = 1; i < 9; i++) {
				tile = getTileFromCoordinates(x, i);
				if (x != i) {
					if (tile != TileType.Enemy)
						tiles.put(new Point(x, i), TileType.PASSABLE);
				}
			}
			for (int i = 0; i < p; i++) {
				tiles.put(new Point(x - 1 + i, y), TileType.PLAYER);
				tiles.put(new Point(x + i, y), TileType.PASSABLE);
			}
		} else if (player1.equals(TileType.Enemy)) {
			setPlayer(x - 1, y);
			p++;
			setEnemy(getPlayerXCoordinate(), getPlayerYCoordinate());
			count();
		}
	}

	public void keyRight() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType player1 = getTileFromCoordinates(x + 1, y);
		if (player1.equals(TileType.PASSABLE)) {
			setPlayer(x + 1, y);
			for (int i = 1; i < 9; i++) {
				tile = getTileFromCoordinates(x, i);
				if (x != i) {
					if (tile != TileType.Enemy)
						tiles.put(new Point(x, i), TileType.PASSABLE);

				}
			}
			for (int i = 0; i < p; i++) {
				tiles.put(new Point(x + 1 - i, y), TileType.PLAYER);
				tiles.put(new Point(x - i, y), TileType.PASSABLE);

			}
		} else if (player1.equals(TileType.Enemy)) {
			setPlayer(x + 1, y);
			p++;

			setEnemy(getPlayerXCoordinate(), getPlayerYCoordinate());
			count();
		}
	}

	public void keyUp() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType player1 = getTileFromCoordinates(x, y - 1);
		if (player1.equals(TileType.PASSABLE)) {
			setPlayer(x, y - 1);
			for (int i = 1; i < 18; i++) {
				tile = getTileFromCoordinates(i, y);
				if (y != i) {
					if (tile != TileType.Enemy)
						tiles.put(new Point(i, y), TileType.PASSABLE);

				}
			}

			for (int i = 0; i < p; i++) {
				tiles.put(new Point(x, y - 1 + i), TileType.PLAYER);
				tiles.put(new Point(x, y + i), TileType.PASSABLE);

			}
		} else if (player1.equals(TileType.Enemy)) {
			setPlayer(x, y - 1);
			p++;
			setEnemy(getPlayerXCoordinate(), getPlayerYCoordinate());
			count();
		}
	}

	public void keyDown() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType player1 = getTileFromCoordinates(x, y + 1);

		if (player1.equals(TileType.PASSABLE)) {
			setPlayer(x, y + 1);
			for (int i = 1; i < 18; i++) {
				tile = getTileFromCoordinates(i, y);
				if (y != i) {
					if (tile != TileType.Enemy)
						tiles.put(new Point(i, y), TileType.PASSABLE);

				}
			}
			for (int i = 0; i < p; i++) {

				tiles.put(new Point(x, y + 1 - i), TileType.PLAYER);
				tiles.put(new Point(x, y - i), TileType.PASSABLE);

			}
		} else if (player1.equals(TileType.Enemy)) {
			setPlayer(x, y + 1);
			p++;
			setEnemy(getPlayerXCoordinate(), getPlayerYCoordinate());
			count();
		}
	}

	public void count() {
		int enemy_count = 0;
		for (int i = 1; i < 18; i++) {
			for (int j = 1; j < 18; j++) {
				tile = getTileFromCoordinates(i, j);
				if (tile == TileType.Enemy)
					enemy_count++;
			}
		}
		if (enemy_count == 0) {
			gameFrame.popup(p);
			exit = true;
		}

	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
}
