package engine;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import launcher.Launcher;
import main.DungeonCrawler;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import ui.GamePanel;
import ui.TilePainter;
import values.TunableParameters;


public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player,blocker,blocker1,blocker2,blocker3;
	public static final int level=1;
	public int numberOfMoves;
	public GameFrame gameFrame;
	private int randomYCoordinatesArray[]= new int[4];
	private int randomXCoordinatesArray[]=new int[4];
	

	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		this.levelCreator = levelCreator;
		this.levelCreator.createLevel(this, level);
	}

	public void run(GameFrame gameFrame) {
		this.gameFrame=gameFrame;
		for (Component component : gameFrame.getComponents()) {
			component.repaint();
		}
	}

	public void addTile(int x, int y, TileType tileType) {
		if (IsPlayer(tileType)) {
			
			setPlayer(x, y);
			tiles.put(new Point(x, y), TileType.PASSABLE);
			
			} if(IsBlocker(tileType)){
				
			  setBlocker(x, y); 
			  tiles.put(new Point(x,y), TileType.BLOCKER);
			  
			  }if(IsBlocker1(tileType)){ 
				  
				setBlocker1(x, y);
				tiles.put(new Point(x,y), TileType.BLOCKER1);
				
			  } if (IsBlocker2(tileType)){ 
				  
				  setBlocker2(x, y);
				  tiles.put(new Point(x,y), TileType.BLOCKER2); 
			  } if (IsBlocker3(tileType)){
					  
				  setBlocker3(x, y); 
				  tiles.put(new Point(x,y), TileType.BLOCKER3);
				  
			  }if(tileType==TileType.PASSABLE||tileType==TileType.NOT_PASSABLE) {
				  
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

	public void setPlayer(int x, int y) {
		player = new Point(x, y);
	}
	public void setBlocker(int x, int y) {
		blocker = new Point(x, y);
	}
	public void setBlocker1(int x, int y) {
		blocker1 = new Point(x, y);
	}
	public void setBlocker2(int x, int y) {
		blocker2 = new Point(x, y);
	}	
	public void setBlocker3(int x, int y) {
		blocker3 = new Point(x, y);
		
	}
	
	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}
	public int getBlockerXCoordinate() {
		return (int) blocker.getX();
	}

	public int getBlockerYCoordinate() {
		return (int) blocker.getY();
	} 
	public int getBlocker1XCoordinate() {
		return (int) blocker1.getX();
	}

	public int getBlocker1YCoordinate() {
		return (int) blocker1.getY();
	}
	public int getBlocker2XCoordinate() {
		return (int) blocker2.getX();
	}

	public int getBlocker2YCoordinate() {
		return (int) blocker2.getY();
	}
	public int getBlocker3XCoordinate() {
		return (int) blocker3.getX();
	}

	public int getBlocker3YCoordinate() {
		return (int) blocker3.getY();
	}
	

public void keyLeft() {
		
		movePlayer(-1,0);
	}

	public void keyRight() {
		
		movePlayer(1,0);
	}
		

	public void keyUp() {
		movePlayer(0,-1);
	}

	public void keyDown() {		
		movePlayer(0, 1);
	}
	
	public void movePlayer(int xDiff, int yDiff) {
		int maxXCoordinate=16,maxYCoordinate=8;
	
		TileType tile =getTileFromCoordinates(getPlayerXCoordinate()+xDiff, getPlayerYCoordinate()+yDiff );
		
	
		if(tile!=TileType.NOT_PASSABLE)
		{
			if(tile==TileType.BLOCKER||tile==TileType.BLOCKER1||tile==TileType.BLOCKER2||tile==TileType.BLOCKER3)	{	
				setPlayer(50, 50);
				updateBlockerTile();
				setBlocker(50,50);
				setBlocker1(50,50);
				setBlocker2(50,50);
				setBlocker3(50,50);
				updateBlockerPosition();
				
				displayStatus(numberOfMoves);
				exit=true;
				
		}
			else {
				setPlayer(getPlayerXCoordinate()+xDiff, getPlayerYCoordinate()+yDiff);
				updateBlockerTile();
				numberOfMoves++;
				randomXCoordinatesForBlockers(maxXCoordinate);
				randomYCoordinatesForBlockers(maxYCoordinate);
				setBlocker(randomXCoordinatesArray[0], randomYCoordinatesArray[0]);
				setBlocker1(randomXCoordinatesArray[1], randomYCoordinatesArray[1]);
				setBlocker2(randomXCoordinatesArray[2], randomYCoordinatesArray[2]);
				setBlocker3(randomXCoordinatesArray[3], randomYCoordinatesArray[3]);
					
				updateBlockerPosition();
			}
			}
		
		else {
			setPlayer(getPlayerXCoordinate(), getPlayerYCoordinate());
		}
		}
	
	
	public void displayStatus(int numberOfMoves) {
		JOptionPane.showMessageDialog(null, "Number of Moves:"+numberOfMoves+"\n"+"Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
		
	}

	private void updateBlockerPosition() {
		addTile(getBlockerXCoordinate(), getBlockerYCoordinate(), TileType.BLOCKER);
		addTile(getBlocker1XCoordinate(), getBlocker1YCoordinate(), TileType.BLOCKER1);
		addTile(getBlocker2XCoordinate(), getBlocker2YCoordinate(), TileType.BLOCKER2);
		addTile(getBlocker3XCoordinate(), getBlocker3YCoordinate(), TileType.BLOCKER3);
		
	}

	private void updateBlockerTile() {
		addTile(getBlockerXCoordinate(), getBlockerYCoordinate(), TileType.PASSABLE);
		addTile(getBlocker1XCoordinate(), getBlocker1YCoordinate(), TileType.PASSABLE);
		addTile(getBlocker2XCoordinate(), getBlocker2YCoordinate(), TileType.PASSABLE);
		addTile(getBlocker3XCoordinate(), getBlocker3YCoordinate(), TileType.PASSABLE);
		
	}

	public void randomXCoordinatesForBlockers(int max) {

		for (int i=0;i<4;i++) {
			randomXCoordinatesArray[i]=new Random().nextInt(max)+1;
		}		
		
	}
	public void randomYCoordinatesForBlockers(int max) {
		
		for (int i=0;i<4;i++) {
			randomYCoordinatesArray[i]=new Random().nextInt(max)+1;
		}		
		
	}
	
public boolean IsPlayer(TileType tileType) {
	int player;
	if (tileType.equals(TileType.PLAYER)) {
	player=1;
	return true;	
	}
	else 
	{
		player=0;
		return false;
	}
}
	public boolean IsBlocker(TileType tileType) {
		int blocker;
		if (tileType.equals(TileType.BLOCKER)) {
		blocker=1;
		return true;	
		}
		else 
		{
			blocker=0;
			return false;
		}
	}
		public boolean IsBlocker1(TileType tileType) {
			int blocker;
			if (tileType.equals(TileType.BLOCKER1)) {
				blocker=1;
			return true;	
			} 
			else 
			{
				blocker=0;
				return false;
			}
		}
		public boolean IsBlocker2(TileType tileType) {
			int blocker=0;
			if (tileType.equals(TileType.BLOCKER2)) {
			blocker=1;
			return true;	
			}
			else 
			{
				blocker=0;
				return false;
			}
		}
		public boolean IsBlocker3(TileType tileType) {
			int blocker;
			if (tileType.equals(TileType.BLOCKER3)) {
			blocker=1;
			return true;	
			}
			else 
			{
				blocker=0;
				return false;
			}
		}


	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

}
