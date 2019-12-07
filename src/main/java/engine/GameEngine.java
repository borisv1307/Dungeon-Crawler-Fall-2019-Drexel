package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;

	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private final int level;
	private int enemies_killed = 0;
	public TileType eachTileOfPanelCoordinates;
	private GameFrame gameFrame;
	private int total_enemy_count = 3;

	public GameEngine(LevelCreator levelCreator, GameFrame gameFrame) {
		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.gameFrame = gameFrame;
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
			cleanTilesHorizontalToPassable(x);
			for (int i = 0; i <= enemies_killed; i++) {
				tiles.put(new Point(x - 1 + i, y), TileType.PLAYER);
				tiles.put(new Point(x + i, y), TileType.PASSABLE);
			}
		} else if (player1.equals(TileType.Enemy)) {
			movePlayer(-1, 0);
		}
	}

	public void keyRight() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType player1 = getTileFromCoordinates(x + 1, y);
		if (player1.equals(TileType.PASSABLE)) {
			setPlayer(x + 1, y);
			cleanTilesHorizontalToPassable(x);
			for (int i = 0; i <= enemies_killed; i++) {
				tiles.put(new Point(x + 1 - i, y), TileType.PLAYER);
				tiles.put(new Point(x - i, y), TileType.PASSABLE);
			}
		} else if (player1.equals(TileType.Enemy)) {
			movePlayer(1, 0);
		}
	}

	public void keyUp() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType player1 = getTileFromCoordinates(x, y - 1);
		if (player1.equals(TileType.PASSABLE)) {
			setPlayer(x, y - 1);
			cleanTilesVerticalToPassable(y);
			for (int i = 0; i <= enemies_killed; i++) {
				tiles.put(new Point(x, y - 1 + i), TileType.PLAYER);
				tiles.put(new Point(x, y + i), TileType.PASSABLE);
			}
		} else if (player1.equals(TileType.Enemy)) {
			movePlayer(0, -1);
		}
	}

	public void keyDown() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType player1 = getTileFromCoordinates(x, y + 1);

		if (player1.equals(TileType.PASSABLE)) {
			setPlayer(x, y + 1);
			cleanTilesVerticalToPassable(y);
			for (int i = 0; i <= enemies_killed; i++) {
				tiles.put(new Point(x, y + 1 - i), TileType.PLAYER);
				tiles.put(new Point(x, y - i), TileType.PASSABLE);
			}
		} else if (player1.equals(TileType.Enemy)) {
			movePlayer(0, 1);
		}
	}

	public void movePlayer(int xDiff, int yDiff) {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		setPlayer(x + xDiff, y + yDiff);
		enemies_killed++;
		setEnemy(x, y);
		count();
	}

	public void count() {
		if (total_enemy_count == enemies_killed) {
			gameFrame.popup(enemies_killed);
			exit = true;
		}

	}

	private void cleanTilesVerticalToPassable(int y) {
		for (int i = 1; i < levelHorizontalDimension - 2; i++) {
			eachTileOfPanelCoordinates = getTileFromCoordinates(i, y);
			if (y != i) {
				if (eachTileOfPanelCoordinates != TileType.Enemy)
					tiles.put(new Point(i, y), TileType.PASSABLE);
			}
		}
	}

	private void cleanTilesHorizontalToPassable(int x) {
		for (int i = 1; i < levelVerticalDimension - 1; i++) {
			eachTileOfPanelCoordinates = getTileFromCoordinates(x, i);
			if (x != i) {
				if (eachTileOfPanelCoordinates != TileType.Enemy)
					tiles.put(new Point(x, i), TileType.PASSABLE);
			}
		}
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

	public int getEnemies_killed() {
		return enemies_killed;
	}

	public void setEnemies_killed(int enemies_killed) {
		this.enemies_killed = enemies_killed;
	}

}
