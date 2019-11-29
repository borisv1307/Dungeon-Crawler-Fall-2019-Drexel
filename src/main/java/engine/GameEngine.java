package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import parser.LevelMove;
import tiles.TileType;
import ui.GameFrame;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final LevelMove levelMove;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private final int level;

	public GameEngine(LevelCreator levelCreator, LevelMove levelMove) {
		this.exit = false;
		this.level = 1;
		this.levelCreator = levelCreator;
		this.levelMove = levelMove;
		this.levelCreator.createLevel(this, this.level);
	}

	public void addTile(int x, int y, TileType tileType) {
		if (tileType.equals(TileType.PLAYER)) {
			this.setPlayer(x, y);
			this.tiles.put(new Point(x, y), TileType.PASSABLE);
		} else {
			this.tiles.put(new Point(x, y), tileType);
		}
	}

	public LevelCreator getLevelCreator() {
		return this.levelCreator;
	}

	public int getLevelHorizontalDimension() {
		return this.levelHorizontalDimension;
	}

	public LevelMove getLevelMove() {
		return this.levelMove;
	}

	public int getLevelVerticalDimension() {
		return this.levelVerticalDimension;
	}

	public int getPlayerXCoordinate() {
		return (int) this.player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) this.player.getY();
	}

	public TileType getTileFromCoordinates(int x, int y) {
		return this.tiles.get(new Point(x, y));
	}

	public boolean isExit() {
		return this.exit;
	}

	public void keyDown() {
		this.movePlayer(0, 1);
	}

	public void keyLeft() {
		this.movePlayer(-1, 0);
	}

	public void keyRight() {
		this.movePlayer(1, 0);
	}

	public void keyUp() {
		this.movePlayer(0, -1);
	}

	private void movePlayer(int xDiff, int yDiff) {
		final TileType attempedLocation = this.getTileFromCoordinates(this.getPlayerXCoordinate() + xDiff,
				this.getPlayerYCoordinate() + yDiff);
		if (attempedLocation.equals(TileType.PASSABLE)) {
			this.setPlayer(this.getPlayerXCoordinate() + xDiff, this.getPlayerYCoordinate() + yDiff);
		} else if (attempedLocation.equals(TileType.PAST_LEVEL)) {
			this.setPlayer(this.getPlayerXCoordinate() + xDiff, this.getPlayerYCoordinate() + yDiff);
			this.getLevelMove().pastLevel(this);
		} else if (attempedLocation.equals(TileType.NEXT_LEVEL)) {
			this.setPlayer(this.getPlayerXCoordinate() + xDiff, this.getPlayerYCoordinate() + yDiff);
			this.getLevelMove().nextLevel(this);
		}
	}

	public void run(GameFrame gameFrame) {
		for (final Component component : gameFrame.getComponents()) {
			component.repaint();
		}
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public void setLevelHorizontalDimension(int levelHorizontalDimension) {
		this.levelHorizontalDimension = levelHorizontalDimension;
	}

	public void setLevelVerticalDimension(int levelVerticalDimension) {
		this.levelVerticalDimension = levelVerticalDimension;
	}

	private void setPlayer(int x, int y) {
		this.player = new Point(x, y);
	}
}
