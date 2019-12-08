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

	SystemWrapper systemWrapper = new SystemWrapper();

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private final Map<Point, Integer> movableID = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;

	private Point player;
	private final int level;
	private int movableTileID = 0;

	private TileType nextTile;
	private Point lightestMovableTile;
	Boolean isWin = false;

	final int MAX_SEQUENCE = 5;
	int checkSequence;

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
		} else if (tileType.equals(TileType.MOVABLE)) {
			tiles.put(new Point(x, y), TileType.MOVABLE);
			movableID.put(new Point(x, y), movableTileID);

			if (movableTileID == 0) {
				lightestMovableTile = new Point(x, y);
			}

			movableTileID++;
		} else {
			tiles.put(new Point(x, y), tileType);
		}
	}

	public void resetMovableTileToPassable(int xCoordinateMovable, int yCoordinateMovable) {
		tiles.put(new Point(xCoordinateMovable, yCoordinateMovable), TileType.PASSABLE);
		checkColorSequence();
	}

	public void setNewMovableTilePosition(int xCoordinateMovable, int yCoordinateMovable) {
		tiles.put(new Point(xCoordinateMovable, yCoordinateMovable), TileType.MOVABLE);
	}

	public void setNewMovableTileID(int xCoordinateMovable, int yCoordinateMovable, int id) {
		movableID.put(new Point(xCoordinateMovable, yCoordinateMovable), id);
		if (id == 0) {
			setLightestMovableTilePoint(xCoordinateMovable, yCoordinateMovable);
		}

	}

	public void setLightestMovableTilePoint(int xCoordinateMovable, int yCoordinateMovable) {
		this.lightestMovableTile = new Point(xCoordinateMovable, yCoordinateMovable);
	}

	public Point getLightestMovableTilePoint() {
		return lightestMovableTile;
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

	public void setPlayer(int x, int y) {
		player = new Point(x, y);
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

	public int getMovableID(int x, int y) {
		return movableID.get(new Point(x, y));
	}

	public void checkColorSequence() {
		int currentSequence = 1;

		int xCoordinateToCheck = (int) lightestMovableTile.getX();
		int yCoordinateToCheck = (int) lightestMovableTile.getY();

		for (int sequenceNumber = 1; sequenceNumber < MAX_SEQUENCE; sequenceNumber++) {

			determineNextTile(xCoordinateToCheck + sequenceNumber, yCoordinateToCheck);
			Point reference = new Point(xCoordinateToCheck + sequenceNumber, yCoordinateToCheck);

			if (nextTile.equals(TileType.MOVABLE)) {
				if (movableID.get(reference) == sequenceNumber) {
					currentSequence++;
				}
			}
		}
		checkForWinSequence(currentSequence);
		checkSequence = currentSequence;
	}

	public void checkForWinSequence(int currentSequence) {

		if (currentSequence == MAX_SEQUENCE) {
			isWin = true;
			displayWinMessage(systemWrapper);
		}
	}

	public void displayWinMessage(SystemWrapper systemWrapper) {
		systemWrapper.JOptionPanelDisplay("Congratulations!", "You have successfully aligned blocks!");
	}

	public void determineNextTile(int xCoordinate, int yCoordinate) {
		nextTile = getTileFromCoordinates(xCoordinate, yCoordinate);
	}

}
