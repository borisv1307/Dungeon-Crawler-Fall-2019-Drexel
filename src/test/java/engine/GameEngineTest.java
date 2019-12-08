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
import wrappers.SystemWrapper;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int FOUR = 4;
	private static final int EIGHTEEN = 18;

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
	public void check_if_coin_exists() {
		TileType tileType = TileType.COIN;
		gameEngine.addTile(FOUR, ONE, TileType.COIN);
		TileType actual = gameEngine.getTileFromCoordinates(FOUR, ONE);
		assertThat(actual, equalTo(tileType));
	}

	@Test
	public void check_if_window_exists() {
		TileType tileType = TileType.WINDOW;
		gameEngine.addTile(EIGHTEEN, ONE, TileType.WINDOW);
		TileType actual = gameEngine.getTileFromCoordinates(EIGHTEEN, ONE);
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
	public void add_and_get_coin_coordinates() {
		TileType tileType = TileType.COIN;
		gameEngine.addcoins(FOUR, ONE, tileType);
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertThat(actualX, equalTo(FOUR));
		assertThat(actualY, equalTo(ONE));
	}

	@Test
	public void add_and_get_windows_coordinates() {
		TileType tileType = TileType.WINDOW;
		gameEngine.addwindow(EIGHTEEN, ONE, tileType);
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertThat(actualX, equalTo(EIGHTEEN));
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
	public void check_if_coin_coordinates_exists() {
		int valofX = 4;
		int valofY = 1;
		gameEngine.addCordToList();
		assertThat(valofX, equalTo(gameEngine.playerXposition.get(0)));
		assertThat(valofY, equalTo(gameEngine.playerYposition.get(0)));
	}

	@Test
	public void game_win_exit() {
		SystemWrapper sy = Mockito.mock(SystemWrapper.class);
		gameEngine.displayWin(sy);
		Mockito.verify(sy).println("Game won");
	}

	@Test
	public void check_if_all_coins_collected() {
		int count = gameEngine.getCountOfCollectedCoins();
		assertThat(count, equalTo(7));
	}

}
