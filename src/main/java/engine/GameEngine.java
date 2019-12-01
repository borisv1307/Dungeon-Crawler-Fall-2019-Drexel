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
	private Point movable;
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
		} else if (tileType.equals(TileType.MOVABLE)) {
			setMovable(x, y);
			tiles.put(new Point(x, y), TileType.MOVABLE);
		} else {
			tiles.put(new Point(x, y), tileType);
		}
	}

	public void resetMovableTile(int xCoordinateMovable, int yCoordinateMovable) {
		tiles.put(new Point(xCoordinateMovable, yCoordinateMovable), TileType.PASSABLE);
	}

	public void setNewMovableTilePosition(int xCoordinateMovable, int yCoordinateMovable) {
		setMovable(xCoordinateMovable, yCoordinateMovable);
		tiles.put(new Point(xCoordinateMovable, yCoordinateMovable), TileType.MOVABLE);
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

	public void setMovable(int x, int y) {
		movable = new Point(x, y);
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public int getMovableXCoordinate() {
		return (int) movable.getX();
	}

	public int getMovableYCoordinate() {
		return (int) movable.getY();
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

}
