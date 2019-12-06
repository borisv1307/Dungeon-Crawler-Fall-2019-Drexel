package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private Point enemy;
	private final int level;
	private RandomWrapper randomWrapper;

	public GameEngine(LevelCreator levelCreator, RandomWrapper randomWrapper) {
		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
		this.randomWrapper = randomWrapper;
	}

	public void run(GameFrame gameFrame) {
		for (Component component : gameFrame.getComponents()) {
			component.repaint();
		}
	}

	public void addTile(int x, int y, TileType tileType) {
		if (tileType.equals(TileType.PLAYER)) {
			setPlayer(x, y);
			tiles.put(new Point(x, y), TileType.PASSABLE);
		} else if (tileType.equals(TileType.ENEMY)) {
			setEnemy(x, y);
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
		enemy = new Point(x, y);
	}

	public int getEnemyXCoordinate() {
		return (int) enemy.getX();

	}

	public int getEnemyYCoordinate() {
		return (int) enemy.getY();
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();

	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public void keyLeft() {

		checkForWallMovePlayer(-1, 0);
		generateMoveForEnemy();
		checkForKill();
	}

	public void keyRight() {
		checkForWallMovePlayer(1, 0);
		generateMoveForEnemy();
		checkForKill();
	}

	public void keyUp() {
		checkForWallMovePlayer(0, -1);
		generateMoveForEnemy();
		checkForKill();
	}

	public void keyDown() {
		checkForWallMovePlayer(0, 1);
		generateMoveForEnemy();
		checkForKill();
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

	public void checkForWallMovePlayer(int xCoordinate, int yCoordinate) {
		if (getTileFromCoordinates(getPlayerXCoordinate() + xCoordinate,
				getPlayerYCoordinate() + yCoordinate) != TileType.NOT_PASSABLE) {
			setPlayer(getPlayerXCoordinate() + xCoordinate, getPlayerYCoordinate() + yCoordinate);
		}
	}

	public void moveEnemyWhenPLayerMoves(int xCoordinate, int yCoordinate) {
		if (getTileFromCoordinates(getEnemyXCoordinate() + xCoordinate,
				getEnemyYCoordinate() + yCoordinate) != TileType.NOT_PASSABLE) {
			setEnemy(getEnemyXCoordinate() + xCoordinate, getEnemyYCoordinate() + yCoordinate);
		}
	}

	public void generateMoveForEnemy() {
		int randomizer = randomWrapper.nextInt(4);
		if (randomizer == 0) {
			moveEnemyWhenPLayerMoves(-1, 0);
		} else if (randomizer == 1) {
			moveEnemyWhenPLayerMoves(1, 0);
		} else if (randomizer == 2) {
			moveEnemyWhenPLayerMoves(0, -1);
		} else if (randomizer == 3) {
			moveEnemyWhenPLayerMoves(0, 1);
		}
	}

	public void checkForKill() {
		if ((getEnemyXCoordinate() == getPlayerXCoordinate()) && (getEnemyYCoordinate() == getPlayerYCoordinate())) {
			this.levelCreator.createLevel(this, level);
		}
	}

}
