package engine;

import java.awt.Component;
import java.awt.Frame;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import main.ObjectFactory;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.ThreadWrapper;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private final int level;
	private Map<Point, TileType> obstacles = new HashMap<Point, TileType>();
	private ArrayList<Thread> threads = new ArrayList<Thread>();;
	private int obstaclesCount = 0;
	private Frame f;
	private final int obstacleSpeed = 200;
	static int nextLevel;
	public final int finalLevel;
	ThreadWrapper threadWrapper;
	private int playerDefaultXCoordinate;
	private int playerDefaultYCoordinate;
	static {
		nextLevel = 1;
	}

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		finalLevel = 4;
		playerDefaultXCoordinate = 9;
		playerDefaultYCoordinate = 8;
		this.threadWrapper = ObjectFactory.getDefaultThreadWrapper();
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
		f = ObjectFactory.getDefaultFrame();
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
		} else if (tileType.equals(TileType.OBSTACLE)) {
			obstaclesCount++;
			tiles.put(new Point(x, y), TileType.OBSTACLE);
			setObstacle(x, y);
		} else {
			tiles.put(new Point(x, y), tileType);
		}
	}

	private void setObstacle(int x, int y) {
		obstacles.put(new Point(x, y), TileType.OBSTACLE);
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

	private void movePlayer(int xDiff, int yDiff) {
		if (getTileFromCoordinates(getPlayerXCoordinate(), getPlayerYCoordinate()) == TileType.EXIT) {
		} else {
			TileType attempedLocation = getTileFromCoordinates(getPlayerXCoordinate() + xDiff,
					getPlayerYCoordinate() + yDiff);
			if (attempedLocation.equals(TileType.PASSABLE)) {
				setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
				checkForCollisionFromPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
			}
			if (attempedLocation.equals(TileType.EXIT)) {
				setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
				if (nextLevel == finalLevel) {
					JOptionPane.showMessageDialog(f, "WellDone!!!");
					setExit(true);
				} else {
					JOptionPane.showMessageDialog(f, "WellDone, goto " + (nextLevel + 1));
					nextLevel++;
					setPlayer(playerDefaultXCoordinate, playerDefaultYCoordinate);
				}

			}
		}
	}

	private void checkForCollisionFromPlayer(int playerX, int playerY) {

		for (Object points : obstacles.keySet()) {
			int ObsX = (int) ((Point) points).getX();
			int ObsY = (int) ((Point) points).getY();
			if (ObsX == playerX && ObsY == playerY) {
				JOptionPane.showMessageDialog(f, "Game Over Buddy");
				setExit(true);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void setExit(boolean exit) {
		if (threads != null)
			for (Thread temp : threads) {
				temp.stop();
			}
		if (f != null)
			f.dispose();
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

	public void moveObstacles(boolean isHorizontalMovement) {
		for (Object points : obstacles.keySet()) {
			int X = ((Point) points).x;
			int Y = ((Point) points).y;
			threads.add(new Thread(new Runnable() {
				public void run() {
					obstaclesHandler(X, Y, isHorizontalMovement);
				}
			}));
		}

		for (Thread temp : threads) {
			temp.start();
		}
	}

	private void obstaclesHandler(int x, int y, boolean isHorizontalMovement) {
		int offset = 1;
		TileType attemptedLocation = getTileFromCoordinates(x + offset, y);
		while ((attemptedLocation == TileType.PASSABLE)) {
			try {
				threadWrapper.sleep(obstacleSpeed / nextLevel);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			obstacles.remove(new Point(x, y));
			setObstacle(x + offset, y);
			checkForCollisionFromObstacle(x + offset, y);
			addTile(x, y, TileType.PASSABLE);
			addTile(x + offset, y, TileType.OBSTACLE);
			x = x + offset;
			attemptedLocation = getTileFromCoordinates(x + offset, y);
			if (!(attemptedLocation == TileType.PASSABLE)) {
				offset *= -1;
			}
			attemptedLocation = getTileFromCoordinates(x + offset, y);
		}

	}

	private void checkForCollisionFromObstacle(int ObstacleX, int ObstacleY) {

		if (getPlayerXCoordinate() == ObstacleX && getPlayerYCoordinate() == ObstacleY) {
			JOptionPane.showMessageDialog(f, "Game Over Buddy");
			setExit(true);
		}

	}

	public int getObstacleXCoordinate(int obstacleNumber) {

		int obstacleCurrentNumber = 0;
		int obstacleXCoordinate = -1;
		for (Object points : obstacles.keySet()) {
			obstacleCurrentNumber++;
			if (obstacleNumber == obstacleCurrentNumber) {
				obstacleXCoordinate = (int) ((Point) points).getX();
			}
		}
		return obstacleXCoordinate;
	}

	public int getObstacleYCoordinate(int obstacleNumber) {
		int obstacleCurrentNumber = 0;
		int obstacleYCoordinate = -1;
		for (Object points : obstacles.keySet()) {
			obstacleCurrentNumber++;
			if (obstacleNumber == obstacleCurrentNumber) {
				obstacleYCoordinate = (int) ((Point) points).getY();
			}
		}
		return obstacleYCoordinate;
	}

	public void moveObstacleLeft(int obstacleNumber) {

		int currentXObstacle = getObstacleXCoordinate(obstacleNumber);
		int currentYObstacle = getObstacleYCoordinate(obstacleNumber);

		obstacles.remove(new Point(currentXObstacle, currentYObstacle));
		setObstacle(currentXObstacle - 1, currentYObstacle);

	}

	public int getCurrentLevel() {
		return nextLevel;
	}

	public int getPlayerDefaultXCoordinate() {
		return playerDefaultXCoordinate;
	}

	public int getPlayerDefaultYCoordinate() {
		return playerDefaultYCoordinate;
	}

}
