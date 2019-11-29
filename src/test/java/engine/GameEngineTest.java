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
	GameEngine gameEngine;
	LevelMove levelMove;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		this.levelMove = new LevelMove(GameEngineTest.ONE, GameEngineTest.ONE, GameEngineTest.FIVE);
		this.gameEngine = new GameEngine(levelCreator, this.levelMove);
		int level = 1;
		Mockito.verify(levelCreator, Mockito.times(level)).createLevel(this.gameEngine, level);
	}

	@Test
	public void run() {
		GameFrame gameFrame = Mockito.mock(GameFrame.class);
		Component component = Mockito.mock(Component.class);
		Mockito.when(gameFrame.getComponents()).thenReturn(new Component[] { component });
		this.gameEngine.run(gameFrame);
		Mockito.verify(component, Mockito.times(1)).repaint();
	}

	@Test
	public void add_and_get_tile() {
		TileType tileType = TileType.PASSABLE;
		this.gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
		TileType actual = this.gameEngine.getTileFromCoordinates(ZERO, ONE);
		assertThat(actual, equalTo(tileType));
	}

	@Test
	public void set_and_get_horizontal_dimension() {
		this.gameEngine.setLevelHorizontalDimension(ONE);
		int actual = this.gameEngine.getLevelHorizontalDimension();
		assertThat(actual, equalTo(ONE));
	}

	@Test
	public void set_and_get_vertical_dimension() {
		this.gameEngine.setLevelVerticalDimension(ONE);
		int actual = this.gameEngine.getLevelVerticalDimension();
		assertThat(actual, equalTo(ONE));
	}

	@Test
	public void add_and_get_player_coordinates() {
		TileType tileType = TileType.PLAYER;
		this.gameEngine.addTile(ZERO, ONE, tileType);
		int actualX = this.gameEngine.getPlayerXCoordinate();
		int actualY = this.gameEngine.getPlayerYCoordinate();
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void set_and_get_exit() {
		boolean exit = true;
		this.gameEngine.setExit(exit);
		boolean actual = this.gameEngine.isExit();
		assertThat(actual, equalTo(exit));
	}

	@Test
	public void get_current_level_move() {
		this.levelMove = this.gameEngine.getLevelMove();
		int actual = this.levelMove.getLevelNum();
		assertThat(actual, equalTo(GameEngineTest.ONE));
	}
}
