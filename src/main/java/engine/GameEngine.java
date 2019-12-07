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
	ArrayList<Integer> playerXposition = new ArrayList<>();
	ArrayList<Integer> playerYposition = new ArrayList<>();
	private static final String WON = "Game won";

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
		playerXposition.add(4);
		playerYposition.add(1);
		playerXposition.add(4);
		playerYposition.add(7);
		playerXposition.add(10);
		playerYposition.add(5);
		playerXposition.add(10);
		playerYposition.add(3);
		playerXposition.add(10);
		playerYposition.add(1);
		playerXposition.add(16);
		playerYposition.add(1);
		playerXposition.add(16);
		playerYposition.add(3);
	}

	public boolean getIndexOfElement(int xcord, int ycord) {
		boolean returnvalue = false;
		int indexofX;
		int indexofY;
		indexofX = playerXposition.indexOf(xcord);
		indexofY = playerYposition.indexOf(ycord);

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
			boolean compareIndex = getIndexOfElement(getPlayerXCoordinate(), getPlayerYCoordinate());
			if (compareIndex) {
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

		sy.println(WON);

	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
}
