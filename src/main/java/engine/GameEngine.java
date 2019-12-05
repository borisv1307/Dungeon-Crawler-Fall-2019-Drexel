package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.SystemWrapper;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private int level;
	private int winat;
	private int count = 0;
	SystemWrapper systemwrapper;

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		winat = 2;
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
		movePlayer(-1, 0);
	}

	public void keyRight() {
		movePlayer(1, 0);
	}

	public void keyUp() {
		movePlayer(0, -1);
	}

	public void keyDown() {
		movePlayer(0, 1);
	}

	public int win() {
		return this.level;
	}

	public void setLocation(int x, int y) {
		setPlayer(x, y);

	}

	private void movePlayer(int xDiff, int yDiff) {
		TileType attempedLocation = getTileFromCoordinates(getPlayerXCoordinate() + xDiff,
				getPlayerYCoordinate() + yDiff);

		if (attempedLocation.equals(TileType.PASSABLE)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
		} else if (attempedLocation.equals(TileType.COIN)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);

			if (getPlayerXCoordinate() == 4 && getPlayerYCoordinate() == 1) {
				count++;
				tiles.remove(new Point(4, 1), TileType.COIN);
				tiles.put(new Point(4, 1), TileType.PASSABLE);
				if (count == 7) {
					tiles.remove(new Point(18, 1), TileType.WINDOW);
					tiles.put(new Point(18, 1), TileType.PASSABLE);
				}

			} else if (getPlayerXCoordinate() == 4 && getPlayerYCoordinate() == 7) {
				count++;
				tiles.remove(new Point(4, 7), TileType.COIN);
				tiles.put(new Point(4, 7), TileType.PASSABLE);
				if (count == 7) {
					tiles.remove(new Point(18, 1), TileType.WINDOW);
					tiles.put(new Point(18, 1), TileType.PASSABLE);
				}
			} else if (getPlayerXCoordinate() == 10 && getPlayerYCoordinate() == 5) {
				count++;
				tiles.remove(new Point(10, 5), TileType.COIN);
				tiles.put(new Point(10, 5), TileType.PASSABLE);
				if (count == 7) {
					tiles.remove(new Point(18, 1), TileType.WINDOW);
					tiles.put(new Point(18, 1), TileType.PASSABLE);
				}

			} else if (getPlayerXCoordinate() == 10 && getPlayerYCoordinate() == 3) {
				count++;
				tiles.remove(new Point(10, 3), TileType.COIN);
				tiles.put(new Point(10, 3), TileType.PASSABLE);
				if (count == 7) {
					tiles.remove(new Point(18, 1), TileType.WINDOW);
					tiles.put(new Point(18, 1), TileType.PASSABLE);
				}

			} else if (getPlayerXCoordinate() == 10 && getPlayerYCoordinate() == 1) {
				count++;
				tiles.remove(new Point(10, 1), TileType.COIN);
				tiles.put(new Point(10, 1), TileType.PASSABLE);
				if (count == 7) {
					tiles.remove(new Point(18, 1), TileType.WINDOW);
					tiles.put(new Point(18, 1), TileType.PASSABLE);
				}

			} else if (getPlayerXCoordinate() == 16 && getPlayerYCoordinate() == 1) {
				count++;
				tiles.remove(new Point(16, 1), TileType.COIN);
				tiles.put(new Point(16, 1), TileType.PASSABLE);
				if (count == 7) {
					tiles.remove(new Point(18, 1), TileType.WINDOW);
					tiles.put(new Point(18, 1), TileType.PASSABLE);
				}

			} else if (getPlayerXCoordinate() == 16 && getPlayerYCoordinate() == 3) {
				count++;
				tiles.remove(new Point(16, 3), TileType.COIN);
				tiles.put(new Point(16, 3), TileType.PASSABLE);
				if (count == 7) {
					tiles.remove(new Point(18, 1), TileType.WINDOW);
					tiles.put(new Point(18, 1), TileType.PASSABLE);
					displaywin(systemwrapper);

				}
			}

		}

	}

	public void displaywin(SystemWrapper sy) {

		sy.println("Game won");

	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
}
