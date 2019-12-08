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

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
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

	public void setTileType(int x, int y) {
		tiles.replace(new Point(x, y), TileType.PASSED);
	}

	private void setPlayer(int x, int y) {
		player = new Point(x, y);
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public void keyLeft() {
		movement(-1, 0);
	}

	public void keyRight() {
		movement(1, 0);
	}

	public void keyUp() {
		movement(0, -1);
	}

	public void keyDown() {
		movement(0, 1);
	}

	public void movement(int x, int y) {
		boolean keepMoving = true;
		while (keepMoving) {
			isLevelComplete(getLevelVerticalDimension());
			keepMoving = movePlayer(x, y);
		}
	}

	private boolean movePlayer(int xDiff, int yDiff) {

		TileType attempedLocation = getTileFromCoordinates(getPlayerXCoordinate() + xDiff,
				getPlayerYCoordinate() + yDiff);
		if (isMovable(attempedLocation)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
			setTileType(getPlayerXCoordinate() - xDiff, getPlayerYCoordinate() - yDiff);
			return true;
		}
		return false;
	}

	public boolean isMovable(TileType location) {
		return (location.equals(TileType.PASSABLE) || location.equals(TileType.PASSED));
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

	public boolean ifPassedOrPlayer(int x, int y) {

		int playerX = getPlayerXCoordinate();
		int playerY = getPlayerYCoordinate();

		TileType tileType = getTileFromCoordinates(x, y);

		return !tileType.equals(TileType.PASSABLE) || (x == playerX && y == playerY);
	}

	public boolean isCurrentRowCovered(int currentRowNumber) {
		boolean flag = true;
		int i = 0;
		while (flag && i < getLevelHorizontalDimension()) {
			flag = ifPassedOrPlayer(i, currentRowNumber);
			i++;
		}
		return flag;
	}

	public boolean isLevelComplete(int levelVerticalDimension) {
		boolean isLevelCompleteFlag = true;
		int i = 0;
		while (isLevelCompleteFlag && i < levelVerticalDimension) {
			isLevelCompleteFlag = isCurrentRowCovered(levelVerticalDimension - 1);
			i++;
		}
		return isLevelCompleteFlag;
	}
}
