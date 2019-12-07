package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.awt.Component;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.ObjectFactory;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.ThreadWrapper;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;

	GameEngine gameEngine;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		gameEngine = new GameEngine(levelCreator);
		int level = 1;
		Mockito.verify(levelCreator, Mockito.times(level)).createLevel(gameEngine, level);
	}

	@Test
	public void run() {
		GameFrame gameFrame = Mockito.mock(GameFrame.class);
		Component component = Mockito.mock(Component.class);
		Mockito.when(gameFrame.getComponents()).thenReturn(new Component[] { component });
		gameEngine.run(gameFrame);
		Mockito.verify(component, Mockito.times(1)).repaint();
	}

	@Test
	public void add_and_get_tile() {
		TileType tileType = TileType.PASSABLE;
		gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
		TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
		assertThat(actual, equalTo(tileType));
	}

	@Test
	public void set_and_get_horizontal_dimension() {
		gameEngine.setLevelHorizontalDimension(ONE);
		int actual = gameEngine.getLevelHorizontalDimension();
		assertThat(actual, equalTo(ONE));
	}

	@Test
	public void set_and_get_vertical_dimension() {
		gameEngine.setLevelVerticalDimension(ONE);
		int actual = gameEngine.getLevelVerticalDimension();
		assertThat(actual, equalTo(ONE));
	}

	@Test
	public void add_and_get_player_coordinates() {
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void set_and_get_exit() {
		boolean exit = true;
		gameEngine.setExit(exit);
		boolean actual = gameEngine.isExit();
		assertThat(actual, equalTo(exit));
	}

	@Test
	public void player_hits_obstacle() {
		TileType playerTile = TileType.PLAYER;
		TileType obstacle = TileType.OBSTACLE;
		gameEngine.addTile(ZERO, ONE, playerTile);
		gameEngine.addTile(ZERO, ZERO, obstacle);
		gameEngine.keyUp();
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertEquals(actualX, gameEngine.getObstacleXCoordinate(1));
		assertThat(actualY, equalTo(1));

	}

	@Test
	public void obstacle_hits_player() {
		TileType playerTile = TileType.PLAYER;
		TileType obstacle = TileType.OBSTACLE;
		gameEngine.addTile(ZERO, ZERO, playerTile);
		gameEngine.addTile(ONE, ZERO, obstacle);
		gameEngine.moveObstacleLeft(1);
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertEquals(actualX, gameEngine.getObstacleXCoordinate(1));
		assertEquals(actualY, gameEngine.getObstacleYCoordinate(1));
	}

	@Test
	public void when_player_goes_though_exit_player_comes_to_default_postition() {
		TileType playerTile = TileType.PLAYER;
		TileType exitTile = TileType.EXIT;
		gameEngine.addTile(gameEngine.getPlayerDefaultXCoordinate(), gameEngine.getPlayerDefaultYCoordinate(),
				playerTile);
		gameEngine.addTile(gameEngine.getPlayerDefaultXCoordinate() + 1, gameEngine.getPlayerDefaultYCoordinate(),
				exitTile);
		gameEngine.keyRight();
		assertEquals(gameEngine.getPlayerYCoordinate(), gameEngine.getPlayerDefaultYCoordinate());
	}

	@Test
	public void does_player_goto_next_level() {
		TileType playerTile = TileType.PLAYER;
		TileType exitTile = TileType.EXIT;
		int previousLevel = gameEngine.getCurrentLevel();
		gameEngine.addTile(ZERO, ZERO, playerTile);
		gameEngine.addTile(ONE, ZERO, exitTile);
		gameEngine.keyRight();
		assertEquals(gameEngine.getCurrentLevel(), previousLevel);
	}

	@Test
	public void check_if_obstacles_move_continously() throws InterruptedException {
		TileType playerTile = TileType.PLAYER;
		TileType obstacleTile = TileType.OBSTACLE;
		gameEngine.addTile(ZERO, ZERO, playerTile);
		gameEngine.addTile(ONE, ZERO, obstacleTile);
		int previousObstacleXCoordinate = gameEngine.getObstacleXCoordinate(1);
		int previousObstacleYCoordinate = gameEngine.getObstacleYCoordinate(1);
		gameEngine.moveObstacles(true);
		gameEngine.moveObstacleLeft(1);
		ThreadWrapper threadWrapper = ObjectFactory.getDefaultThreadWrapper();
		int defaultSpeed = 200;
		threadWrapper.sleep(defaultSpeed / gameEngine.getCurrentLevel());
		assertEquals(previousObstacleXCoordinate - 1, gameEngine.getObstacleXCoordinate(1));
		assertEquals(previousObstacleYCoordinate, gameEngine.getObstacleYCoordinate(1));
	}

	@Test
	public void check_if_game_goes_to_final_level() {
		TileType playerTile = TileType.PLAYER;
		TileType exitTile = TileType.EXIT;
		gameEngine.addTile(gameEngine.getPlayerDefaultXCoordinate(), gameEngine.getPlayerDefaultYCoordinate(),
				playerTile);
		gameEngine.addTile(gameEngine.getPlayerDefaultXCoordinate() + 1, gameEngine.getPlayerDefaultYCoordinate(),
				exitTile);
		while (gameEngine.getCurrentLevel() != gameEngine.finalLevel) {
			gameEngine.keyRight();
		}
		assertEquals(gameEngine.getCurrentLevel(), gameEngine.finalLevel);

	}

}
