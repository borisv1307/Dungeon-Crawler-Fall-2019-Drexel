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

	private static final String trapSprung = "You have sprung a trap. <Insert> key to get free.";
	private static final String noTrapHere = "There is nowhere to insert your key";
	private static final String disabledTrap = "You have disabled the trap. You are free!";
	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private Point trap;
	private final int level;
	private final SystemWrapper systemWrapper;
	public boolean isTrapSprung = false;
	public boolean isTrapDisabled = false;

	public GameEngine(LevelCreator levelCreator) {
		this.systemWrapper = new SystemWrapper();
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
		} else if (tileType.equals(TileType.TRAP) && isTrapDisabled == false) {
			setTrap(x, y);
			tiles.put(new Point(x, y), TileType.TRAP);
		} else if (tileType.equals(TileType.TRAP) && isTrapDisabled == true) {
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
		if (isTrapSprung == false) {
			movePlayer(-1, 0);
			// this if would be better at movePlayer but that breaks testing
		}

	}

	public void keyRight() {
		if (isTrapSprung == false) {
			movePlayer(1, 0);
		}

	}

	public void keyUp() {
		if (isTrapSprung == false) {
			movePlayer(0, -1);
		}
	}

	public void keyDown() {
		if (isTrapSprung == false) {
			movePlayer(0, 1);
		}
	}

	public void insertKey() {
		if (isTrapSprung == true) {
			int disabledTrapX = getTrapXCoordinate();
			int disabledTrapY = getTrapYCoordinate();
			isTrapSprung = false;
			isTrapDisabled = true;
			systemWrapper.println(disabledTrap);

		} else {
			systemWrapper.println(noTrapHere);
		}

	}

	private void movePlayer(int xDiff, int yDiff) {
		TileType attempedLocation = getTileFromCoordinates(getPlayerXCoordinate() + xDiff,
				getPlayerYCoordinate() + yDiff);
		if (attempedLocation.equals(TileType.PASSABLE)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
		}
		int checkPlayerX = getPlayerXCoordinate();
		int checkPlayerY = getPlayerYCoordinate();
		int checkTrapX = getTrapXCoordinate();
		int checkTrapY = getTrapYCoordinate();
		checkForTrap(checkPlayerX, checkPlayerY, checkTrapX, checkTrapY);

	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

	public void setTrap(int x, int y) {
		trap = new Point(x, y);

	}

	public int getTrapXCoordinate() {
		return (int) trap.getX();
	}

	public int getTrapYCoordinate() {
		return (int) trap.getY();

	}

	public boolean checkForTrap(int playerX, int playerY, int trapX, int trapY) {
		if ((Math.abs(playerX - trapX) < 2) && Math.abs(playerY - trapY) < 2 && isTrapDisabled == false) {
			isTrapSprung = true;
			systemWrapper.println(trapSprung);
		}

		return isTrapSprung;
	}

}
