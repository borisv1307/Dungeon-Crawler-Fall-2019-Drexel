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
	private final int level;
	public static int game_score = 0;
	JOptionPaneWrapper joptionPaneWrapper = new JOptionPaneWrapper();

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
		TileType attempedLocation = getTileFromCoordinates(getPlayerXCoordinate() + xDiff,
				getPlayerYCoordinate() + yDiff);

		if (attempedLocation != TileType.NOT_PASSABLE) {
			if (attempedLocation == TileType.ENEMY) {
				setPlayer(11, 11);
				messageDisplay(joptionPaneWrapper, game_score);
				exit = true;
			} else {
				addTile(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff, TileType.PASSABLE);
				setPlayer(getPlayerXCoordinate() + xDiff, getPlayerYCoordinate() + yDiff);
				if (attempedLocation == TileType.ENERGY)
					game_score++;
				if (game_score == 5) {
					messageDisplay(joptionPaneWrapper, game_score);
					exit = true;
				}

			}

		}
	}

	public void messageDisplay(JOptionPaneWrapper joptionPaneWrapper, int game_score) {
		if (game_score != 5)
			joptionPaneWrapper.showMessageDialog(null, "GAME END ..Lets try again!!! YOUR SCORE:" + game_score,
					"ENERGY SCORE", joptionPaneWrapper.ERROR_MESSAGE);
		else
			joptionPaneWrapper.showMessageDialog(null, "Reached Max Score:5", "ENERGY SCORE",
					joptionPaneWrapper.ERROR_MESSAGE);

	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}

	public boolean isExit() {
		return exit;
	}
}
