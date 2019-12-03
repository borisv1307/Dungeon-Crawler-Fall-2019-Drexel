package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Component;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.MathWrapper;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;

	GameEngine gameEngine;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		GameFrame frame = Mockito.mock(GameFrame.class);
		gameEngine = new GameEngine(levelCreator, new MathWrapper(), frame);
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
		assertThat(gameEngine.getTileFromCoordinates(ZERO, ONE), equalTo(tileType));
	}

	@Test
	public void add_and_get_tile_of_opponent() {
		TileType tileType = TileType.OPPONENT;
		gameEngine.addTile(ZERO, ONE, TileType.OPPONENT);
		assertThat(gameEngine.getTileFromCoordinates(ZERO, ONE), equalTo(tileType));
	}

	@Test
	public void set_and_get_horizontal_dimension() {
		gameEngine.setLevelHorizontalDimension(ONE);
		assertThat(gameEngine.getLevelHorizontalDimension(), equalTo(ONE));
	}

	@Test
	public void set_and_get_vertical_dimension() {
		gameEngine.setLevelVerticalDimension(ONE);
		assertThat(gameEngine.getLevelVerticalDimension(), equalTo(ONE));
	}

	@Test
	public void add_and_get_player_coordinates() {
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(ZERO));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(ONE));
	}

	@Test
	public void add_and_get_opponent_coordinates() {
		gameEngine.addTile(ZERO, ONE, TileType.OPPONENT);
		assertThat(gameEngine.getOpponentXCoordinate(), equalTo(ZERO));
		assertThat(gameEngine.getOpponentYCoordinate(), equalTo(ONE));
	}

	@Test
	public void set_and_get_exit() {
		boolean exit = true;
		gameEngine.setExit(exit);
		assertThat(gameEngine.isExit(), equalTo(exit));
	}

}
