package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
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
	SystemWrapper systemWrapper;
	ArrayList<Integer> Xcoordinates = new ArrayList<>();
	ArrayList<Integer> Ycoordinates = new ArrayList<>();
	private boolean compareIndex;
	private static final String gameWonMsg = "Game won";

	private int count = 0;

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

	public void setLocation(int x, int y) {
		setPlayer(x, y);

	}

	public void addCordToList() {
		Xcoordinates.add(4);
		Ycoordinates.add(1);
		Xcoordinates.add(4);
		Ycoordinates.add(7);
		Xcoordinates.add(10);
		Ycoordinates.add(5);
		Xcoordinates.add(10);
		Ycoordinates.add(3);
		Xcoordinates.add(10);
		Ycoordinates.add(1);
		Xcoordinates.add(16);
		Ycoordinates.add(1);
		Xcoordinates.add(16);
		Ycoordinates.add(3);
	}

	public boolean getIndexOfElement(int xcord, int ycord) {
		boolean returnvalue = false;
		int indexofX;
		int indexofY;
		indexofX = Xcoordinates.indexOf(xcord);
		indexofY = Ycoordinates.indexOf(ycord);

		if (indexofX == indexofY) {
			returnvalue = true;
		}
		return returnvalue;

	}

	private void movePlayer(int xDiff, int yDiff) {
		TileType attempedLocation = getTileFromCoordinates(getPlayerXCoordinate() + xDiff,
				getPlayerYCoordinate() + yDiff);

		if (attempedLocation.equals(TileType.PASSABLE)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
		} else if (attempedLocation.equals(TileType.COIN)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
			compareIndex = getIndexOfElement(getPlayerXCoordinate(), getPlayerYCoordinate());
			if (compareIndex == true) {
				count++;
				tiles.remove(new Point(getPlayerXCoordinate(), getPlayerYCoordinate()), TileType.COIN);
				tiles.put(new Point(getPlayerXCoordinate(), getPlayerYCoordinate()), TileType.PASSABLE);
				if (count == 7) {
					tiles.remove(new Point(18, 1), TileType.WINDOW);
					tiles.put(new Point(18, 1), TileType.PASSABLE);
				}

			}
		}

	}

	public void displayWin(SystemWrapper sy) {

		sy.println(gameWonMsg);

	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
}
