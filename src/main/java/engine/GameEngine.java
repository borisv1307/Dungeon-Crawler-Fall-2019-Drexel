package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.JOptionPaneWrapper;
import wrappers.RandomNumberWrapper;



public class GameEngine {

    private boolean exit;
    private final LevelCreator levelCreator;
    private final RandomNumberWrapper randomNumberWrapper;
    private final JOptionPaneWrapper jOptionPaneWrapper;
    private final Map < Point, TileType > tiles = new HashMap < > ();
    private int levelHorizontalDimension;
    private int levelVerticalDimension;
    private Point player;
    public Map < Point, TileType > blocker = new HashMap < Point, TileType > ();
    public final int level;
    int maxXCoordinate = 16;
    int maxYCoordinate = 8;
    public ArrayList < Integer > randomYCoordinatesArray = new ArrayList < Integer > ();
    public ArrayList < Integer > randomXCoordinatesArray = new ArrayList < Integer > ();
    public int numberOfMoves;
    public int blockerCount;


    public GameEngine(LevelCreator levelCreator, RandomNumberWrapper randomNumberWrapper, JOptionPaneWrapper jOptionPaneWrapper) {
        exit = false;
        level = 1;
        this.levelCreator = levelCreator;
        this.randomNumberWrapper = randomNumberWrapper;
        this.jOptionPaneWrapper = jOptionPaneWrapper;
        this.levelCreator.createLevel(this, level);
    }

    public void run(GameFrame gameFrame) {
        for (Component component: gameFrame.getComponents()) {
            component.repaint();
        }
    }

    public void addTile(int x, int y, TileType tileType) {
        if (tileType.equals(TileType.PLAYER)) {

            setPlayer(x, y);
            tiles.put(new Point(x, y), TileType.PASSABLE);
        } else if (tileType.equals(TileType.BLOCKER)) {
            setBlocker(x, y);
            tiles.put(new Point(x, y), TileType.BLOCKER);
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

    public void setPlayer(int x, int y) {
        player = new Point(x, y);
    }
    public void setBlocker(int x, int y) {
        blocker.put(new Point(x, y), TileType.BLOCKER);
    }

    public int getPlayerXCoordinate() {
        return (int) player.getX();
    }

    public int getPlayerYCoordinate() {
        return (int) player.getY();
    }
    public int getBlockerXCoordinate(int blockerNumber) {
        int blockeroffset = 1;
        int blockerXCoordinate = 0;
        for (Point p: blocker.keySet()) {

            if (blockeroffset == blockerNumber) {
                blockerXCoordinate = (int) p.getX();
            }
            blockeroffset++;
        }
        return blockerXCoordinate;

    }

    public int getBlockerYCoordinate(int blockerNumber) {
        int blockeroffset = 1;
        int blockerYCoordinate = 0;
        for (Point p: blocker.keySet()) {

            if (blockeroffset == blockerNumber) {
                blockerYCoordinate = (int) p.getY();
            }
            blockeroffset++;
        }
        return blockerYCoordinate;

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

    public void movePlayer(int xDiff, int yDiff) {
        TileType tile = getTileFromCoordinates(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);

        if (tile != TileType.NOT_PASSABLE) {
            if (tile == TileType.BLOCKER) {
                 blockerCount = blocker.size();
                setPlayer(50, 50);
                updateBlockerTile(blockerCount);
                displayGameStatus(numberOfMoves,jOptionPaneWrapper);
                exit = true;
            } else {
                setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
                numberOfMoves++;
                generatingRandomBlockers(randomNumberWrapper);
            }
        }
    }

    public void displayGameStatus(int numberOfMoves,JOptionPaneWrapper jOptionPaneWrapper) {
    	
		jOptionPaneWrapper.showMessage(numberOfMoves);
		
	}

	public void updateBlockerPosition(int blockerCount) {

        for (int i = 1; i <= blockerCount; i++) {
            addTile(getBlockerXCoordinate(i), getBlockerYCoordinate(i), TileType.BLOCKER);
        }

    }

    public void updateBlockerTile(int blockerCount) {
        for (int i = 1; i <= blockerCount; i++) {
            addTile(getBlockerXCoordinate(i), getBlockerYCoordinate(i), TileType.PASSABLE);
        }

    }

    public void generatingRandomBlockers(RandomNumberWrapper randomNumber) {
    	blockerCount = blocker.size();
        updateBlockerTile(blockerCount);
        blocker.clear();
        randomXCoordinatesArray.clear();
        randomYCoordinatesArray.clear();
        for (int i = 0; i < blockerCount; i++) {
            int random = randomNumber.nextInt(maxXCoordinate);
            if ((randomXCoordinatesArray.contains(random)))
                i--;
            else
                randomXCoordinatesArray.add(random);
            randomYCoordinatesArray.add(randomNumber.nextInt(maxYCoordinate));
            setBlocker(randomXCoordinatesArray.get(i), randomYCoordinatesArray.get(i));
        }
        
        updateBlockerPosition(blockerCount);
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public boolean isExit() {
        return exit;
    }


}