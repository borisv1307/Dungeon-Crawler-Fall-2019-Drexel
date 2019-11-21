package engine;

import java.awt.Component;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import movement.Movement;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

public class GameEngine {

	private boolean exit;
	private final LevelCreator levelCreator;
	private final Movement movementHandler;
	private final Map<Point, TileType> tiles = new HashMap<>();
	private int levelHorizontalDimension;
	private int levelVerticalDimension;
	public Point player;
	public int level = 0;

	public GameEngine(LevelCreator levelCreator, Movement defaultMovement) {
		exit = false;
		this.movementHandler = defaultMovement;
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

		setLocation((int) player.getX() - 1, (int) player.getY());
	}

	public void keyRight() {

		setLocation((int) player.getX() + 1, (int) player.getY());
	}

	public void keyUp() {

		setLocation((int) player.getX(), (int) player.getY() - 1);

	}

	public void keyDown() {

		setLocation((int) player.getX(), (int) player.getY() + 1);
	}

	public void setLocation(int xCoordinateToSet, int yCoordinateToSet) {

		TileType newPosition = movementHandler.setLocation(this, xCoordinateToSet, yCoordinateToSet);

		if (newPosition == TileType.WIN_POINT) {

			this.levelCreator.createLevel(this, level);
		}

	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
}
