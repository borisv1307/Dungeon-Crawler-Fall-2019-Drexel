package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.DungeonMovement;
import parser.LevelCreator;
import player.Player;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Player player;
	private final int level;
	private RandomWrapper randomWrapper;
	private DungeonMovement dungeonMovement;

	public GameEngine(LevelCreator levelCreator, RandomWrapper randomWrapperIn, DungeonMovement dungeonMovementIn) {
		randomWrapper = randomWrapperIn;
		dungeonMovement = dungeonMovementIn;
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

	public void addTileAtRandomAvailablePoint(TileType tileType) {
		List<Point> tiles = getEmptyTiles();
		if(tiles.isEmpty())
			return;
		
		int randIndex = randomWrapper.nextInt(tiles.size());
		Point point = tiles.get(randIndex);
		addTile(point.x, point.y, tileType);
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

		if(player == null) {
			player = new Player(x, y);
		} else {			
			player = new Player(player);
			player.setLocation(x, y);
		}
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}
	
	public int getCollectedTreasure() {
		return (int) player.getCollectedTileInventory(TileType.TREASURE);
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
		TileType attempedLocation = getTileFromCoordinates(getPlayerXCoordinate() + xDiff,
				getPlayerYCoordinate() + yDiff);
		if (dungeonMovement.isPassable(attempedLocation)) {
			setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
			
			if(player.attemptCollectTile(attempedLocation)) {
				addTile(getPlayerXCoordinate(), getPlayerYCoordinate(), TileType.PASSABLE);
			}
		}
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
	
	public List<Point> getTilesOfType(TileType tileTypeToCount) {
		List<Point> matchingTiles = new ArrayList<Point>();	
		tiles.forEach((k,v) -> collectSpecificTiles(matchingTiles,k,v, tileTypeToCount));
		return matchingTiles;		
	}

	private void collectSpecificTiles(List<Point> matchingTiles, Point tilePoint, TileType tileType, TileType tileTypeToCount) {
		if(tileType == tileTypeToCount) {
			matchingTiles.add (new Point(tilePoint.x, tilePoint.y) );
		}
	}

	public int getTileCount(TileType tileTypeToCount) {
		List<Point> existingTiles = getTilesOfType(tileTypeToCount);
		return existingTiles.size();
	}
	
	public List<Point> getEmptyTiles() {
		List<Point> emptyTiles = new ArrayList<Point>();		
		tiles.forEach((k,v) -> collectEmptyTiles(emptyTiles,k,v));
		return emptyTiles;
	}
	
	private void collectEmptyTiles(List<Point> emptyTiles, Point tilePoint, TileType tileType) {
		if(tileType == TileType.PASSABLE && !player.equals(tilePoint)) {
			emptyTiles.add(new Point(tilePoint.x, tilePoint.y) );
		}
	}

	public int getEmptyTileCount() {
		List<Point> existingTiles = getEmptyTiles();
		return existingTiles.size();
	}
}
