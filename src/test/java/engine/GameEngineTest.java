package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Component;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parser.LevelCreator;
import parser.LevelMove;
import tiles.TileType;
import ui.GameFrame;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int FIVE = 5;
	private static final int THREE = 3;

	GameEngine gameEngine;
	LevelMove levelMove;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		levelMove = new LevelMove(ONE, ONE, FIVE);
		gameEngine = new GameEngine(levelCreator, levelMove);
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
	public void get_current_level_move() {
		levelMove = gameEngine.getLevelMove();
		int actual = levelMove.getLevelNum();
		assertThat(actual, equalTo(ONE));
	}

	@Test
	public void check_player_can_move_to_past_level_tile() {
		TileType tileTypeLevel = TileType.PAST_LEVEL;
		gameEngine.addTile(ZERO, ZERO, tileTypeLevel);
		TileType tileTypePlayer = TileType.PLAYER;
		gameEngine.addTile(ONE, ZERO, tileTypePlayer);
		gameEngine.keyLeft();
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ZERO));
	}

	@Test
	public void check_player_on_landing_past_level_tiles_moves_past_level() {
		TileType tileTypeLevel = TileType.PAST_LEVEL;
		gameEngine.addTile(ZERO, ZERO, tileTypeLevel);
		TileType tileTypePlayer = TileType.PLAYER;
		gameEngine.addTile(ONE, ZERO, tileTypePlayer);
		levelMove.setLevelNum(4);
		gameEngine.keyLeft();
		int actual = levelMove.getLevelNum();
		assertThat(actual, equalTo(THREE));
	}

	@Test
	public void check_player_can_move_to_next_level_tile() {
		TileType tileTypeLevel = TileType.NEXT_LEVEL;
		gameEngine.addTile(ZERO, ZERO, tileTypeLevel);
		TileType tileTypePlayer = TileType.PLAYER;
		gameEngine.addTile(ONE, ZERO, tileTypePlayer);
		gameEngine.keyLeft();
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ZERO));
	}

	@Test
	public void check_player_on_landing_next_level_tiles_moves_next_level() {
		TileType tileTypeLevel = TileType.NEXT_LEVEL;
		gameEngine.addTile(ZERO, ZERO, tileTypeLevel);
		TileType tileTypePlayer = TileType.PLAYER;
		gameEngine.addTile(ONE, ZERO, tileTypePlayer);
		levelMove.setLevelNum(4);
		gameEngine.keyLeft();
		int actual = levelMove.getLevelNum();
		assertThat(actual, equalTo(FIVE));
	}

}
