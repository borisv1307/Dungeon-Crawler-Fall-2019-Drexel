package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.*;
import java.util.Map.Entry;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private final int level;
	private RandomWrapper randomWrapper;

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
	}

	public GameEngine(LevelCreator defaultLevelCreator, RandomWrapper defaultRandomWrapper) {
		this(defaultLevelCreator);
		randomWrapper = defaultRandomWrapper;
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
		List<Point> tiles = getTilesOfType(TileType.PASSABLE);				
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
		player = new Point(x, y);
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public void keyLeft() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType newTile = getTileFromCoordinates(x - 1, y);
		if (newTile != TileType.NOT_PASSABLE)
			this.setPlayer(x - 1, y);
	}

	public void keyRight() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType newTile = getTileFromCoordinates(x + 1, y);
		if (newTile != TileType.NOT_PASSABLE)
			this.setPlayer(x + 1, y);
	}

	public void keyUp() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType newTile = getTileFromCoordinates(x, y - 1);
		if (newTile != TileType.NOT_PASSABLE)
			this.setPlayer(x, y - 1);
	}

	public void keyDown() {
		int x = this.getPlayerXCoordinate();
		int y = this.getPlayerYCoordinate();
		TileType newTile = getTileFromCoordinates(x, y + 1);
		if (newTile != TileType.NOT_PASSABLE)
			this.setPlayer(x, y + 1);
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
	
	public List<Point> getTilesOfType(TileType tileTypeToCount) {

		List<Point> matchingTiles = new ArrayList<Point>();

        Iterator<Entry<Point, TileType>> hmIterator = tiles.entrySet().iterator(); 
    
        while (hmIterator.hasNext()) { 
            Map.Entry<Point, TileType> mapElement = (Map.Entry<Point, TileType>)hmIterator.next(); 
            TileType tileType = (TileType)mapElement.getValue();
            Point tilePoint = (Point)mapElement.getKey();
            
			if(tileType == tileTypeToCount) {
				matchingTiles.add (new Point(tilePoint.x, tilePoint.y) );
			}
        } 		
				
		return matchingTiles;
	}

	public int getTileCount(TileType tileTypeToCount) {
		List<Point> existingTiles = getTilesOfType(tileTypeToCount);
		return existingTiles.size();
	}
}
