package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.JOptionPaneWrapper;



public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	private Point player;
	private Map<Point, TileType> blocker=new HashMap<Point,TileType>() ;
	public final int level;
	private int numberOfMoves;
	private int blockerXCoordinate;
	private int blockerYCoordinate;
	private int blockerCount=4;
	RandomNumberGenerator randomNumber= new RandomNumberGenerator();
	public int[] randomYCoordinatesArray= new int[4];
	public int[] randomXCoordinatesArray=new int[4];
	
	
	public GameEngine(LevelCreator levelCreator) {
		exit = false;
		level=1;
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
		}
		else if(tileType.equals(TileType.BLOCKER)){
			setBlocker(x,y);
			tiles.put(new Point(x, y), TileType.BLOCKER);
		}
		else {
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
		blocker.put( new Point(x, y),TileType.BLOCKER);
	}
	
	public int getPlayerXCoordinate() {
		return (int) player.getX();
	}

	public int getPlayerYCoordinate() {
		return (int) player.getY();
	}
	public int getBlockerXCoordinate(int blockerNumber) {
		int blockeroffset=1;
		for(Point p: blocker.keySet()) {
			
			if(blockeroffset == blockerNumber) {
				blockerXCoordinate= (int)p.getX();
			}
			blockeroffset++;
		  }
		return blockerXCoordinate;
		
	}

	public int getBlockerYCoordinate(int blockerNumber) {
		int blockeroffset=1;
		for(Point p: blocker.keySet()) {
			
			if(blockeroffset == blockerNumber) {
				blockerYCoordinate= (int)p.getY();
			}
			blockeroffset++;
		  }
		return blockerYCoordinate;
		
	}

	public void keyLeft() {
		
		movePlayer(-1,0,randomNumber);
	}

	public void keyRight() {
		
		movePlayer(1,0,randomNumber);
	}
		

	public void keyUp() {
		movePlayer(0,-1,randomNumber);
	}

	public void keyDown() {		
		movePlayer(0, 1,randomNumber);
	}
	
	public void movePlayer(int xDiff, int yDiff,RandomNumberGenerator randomNumber) {
		int maxXCoordinate=16;
		int maxYCoordinate=8;
		TileType tile =getTileFromCoordinates(getPlayerXCoordinate()+xDiff, getPlayerYCoordinate()+yDiff );
	
		if(tile!=TileType.NOT_PASSABLE)
		{
			if(tile==TileType.BLOCKER)	{	
				setPlayer(50, 50);
				updateBlockerTile();	
				JOptionPaneWrapper jOptionPaneWrapper=new JOptionPaneWrapper();
				displayStatus(numberOfMoves,jOptionPaneWrapper);
				exit=true; 	
		    }
			else {
				setPlayer(getPlayerXCoordinate()+xDiff, getPlayerYCoordinate()+yDiff);
				updateBlockerTile();
				blocker.clear();
				numberOfMoves++;				
				for(int i=0;i<4;i++) { 
					randomXCoordinatesArray[i]=randomNumber.randomCoordinatesForBlockers(maxXCoordinate);
					randomYCoordinatesArray[i]=randomNumber.randomCoordinatesForBlockers(maxYCoordinate);
					setBlocker(randomXCoordinatesArray[i], randomYCoordinatesArray[i]);
				
				}	
				updateBlockerPosition();
			}
		 }
		
		else {
			setPlayer(getPlayerXCoordinate(), getPlayerYCoordinate());
		}
		}
	
	
	public void displayStatus(int numberOfMoves,JOptionPaneWrapper jOptionPaneWrapper) {
		jOptionPaneWrapper.showDialoge(numberOfMoves);
		
		}

	private void updateBlockerPosition() {
		
		for(int i=1;i<=blockerCount;i++) {
		addTile(getBlockerXCoordinate(i), getBlockerYCoordinate(i), TileType.BLOCKER); 
		}
		
	} 

	private void updateBlockerTile() {
		
		for(int i=1;i<=blockerCount;i++) {
			addTile(getBlockerXCoordinate(i), getBlockerYCoordinate(i), TileType.PASSABLE);
		}
		
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}

}
