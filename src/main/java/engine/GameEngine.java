package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.MathWrapper;
import wrappers.PaneWrapper;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private Point opponent;
	private final int level;
	private MathWrapper mathWrapper;
	private int numberOfEnemiesKilled;
	private GameFrame frame;

	public GameEngine(LevelCreator levelCreator, MathWrapper mathWrapper, GameFrame frame) {
		exit = false;
		level = 1;
		this.mathWrapper = mathWrapper;
		this.frame = frame;
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
		} else if (tileType.equals(TileType.OPPONENT)) {
			setOpponent(x, y);
			tiles.put(new Point(x, y), TileType.OPPONENT);
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

	public void setOpponent(int x, int y) {
		opponent = new Point(x, y);
		tiles.put(new Point(x, y), TileType.OPPONENT);
	}

	public int getOpponentXCoordinate() {
		return (int) opponent.getX();
	}

	public int getOpponentYCoordinate() {
		return (int) opponent.getY();
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

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

	public int getNumberOfEnemiesKilled() {
		return numberOfEnemiesKilled;
	}

	public void setNumberOfEnemiesKilled(int numberOfEnemiesKilled) {
		this.numberOfEnemiesKilled = numberOfEnemiesKilled;
	}

	private void movePlayer(int xDiff, int yDiff) {
		tiles.put(new Point(getPlayerXCoordinate(), getPlayerYCoordinate()), TileType.PASSABLE);
		TileType attempedLocation = getTileFromCoordinates(getPlayerXCoordinate() + xDiff,
				getPlayerYCoordinate() + yDiff);
		if (attempedLocation.equals(TileType.PASSABLE)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
		} else if (attempedLocation.equals(TileType.OPPONENT)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
			displayGameOverMessage();
			moveOpponent();
		}
	}

	private void displayGameOverMessage() {
		numberOfEnemiesKilled++;
		if (getNumberOfEnemiesKilled() == 10) {
			frame.displayPopupMessage(new PaneWrapper());
			setNumberOfEnemiesKilled(0);
		}
	}

	private void moveOpponent() {
		setOpponent(mathWrapper.getRandomInteger(getLevelHorizontalDimension() - 1),
				mathWrapper.getRandomInteger(getLevelVerticalDimension() - 1));
	}

}
