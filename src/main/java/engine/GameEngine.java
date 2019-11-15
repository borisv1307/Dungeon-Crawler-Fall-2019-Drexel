package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

public class GameEngine {

	private class Door{
		private GameEngine gameEngine;
		private int currentLevel;
		Door(GameEngine gameEngine, int level){
			this.gameEngine = gameEngine;
			this.currentLevel = level;
		}
		private int goNextLevel() {
			// TODO Auto-generated method stub
			currentLevel ++;
			levelCreator.createLevel(this.gameEngine, this.currentLevel);
			return this.currentLevel;
		}
		
		
	}
	
	
	
	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private final int level;
	private int currentLevel;
	private Door door;
	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level = 1;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
		door = new Door(this,level);
		this.currentLevel = level;
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

	protected void setPlayer(int x, int y) {
		player = new Point(x, y);
		if(this.getTileFromCoordinates(x, y) == TileType.EXIT) {
			this.currentLevel = door.goNextLevel();
		}
	}

	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}

	public void keyLeft() {
		// TODO Implement movement logic here
		if(this.getTileFromCoordinates(this.getPlayerXCoordinate()-1,this.getPlayerYCoordinate()) != TileType.NOT_PASSABLE)
			this.setPlayer(this.getPlayerXCoordinate()-1, this.getPlayerYCoordinate());
	}

	public void keyRight() {
		// TODO Implement movement logic here
		if(this.getTileFromCoordinates(this.getPlayerXCoordinate()+1,this.getPlayerYCoordinate()) != TileType.NOT_PASSABLE)
		this.setPlayer(this.getPlayerXCoordinate()+1, this.getPlayerYCoordinate());
	}

	public void keyUp() {
		// TODO Implement movement logic here
		if(this.getTileFromCoordinates(this.getPlayerXCoordinate(),this.getPlayerYCoordinate()-1) != TileType.NOT_PASSABLE)
		this.setPlayer(this.getPlayerXCoordinate(), this.getPlayerYCoordinate()-1);
	}

	public void keyDown() {
		// TODO Implement movement logic here
		if(this.getTileFromCoordinates(this.getPlayerXCoordinate(),this.getPlayerYCoordinate()+1) != TileType.NOT_PASSABLE)
		this.setPlayer(this.getPlayerXCoordinate(), this.getPlayerYCoordinate()+1);
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
	public int getCurrentLevel() {
		return this.currentLevel;
	}
	
}
