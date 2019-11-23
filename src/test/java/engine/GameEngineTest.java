package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.awt.Component;
import java.awt.Point;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.DungeonMovement;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import wrappers.RandomWrapper;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;

	GameEngine gameEngine;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		gameEngine = new GameEngine(levelCreator, new RandomWrapper(), new DungeonMovement());
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
	public void add_random_tile() {
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
		Mockito.when(randomWrapper.nextInt((Integer)2))
				   .thenReturn(1);
		
		gameEngine.addTile(ZERO, ZERO, TileType.NOT_PASSABLE);
		gameEngine.addTile(ONE, ZERO, TileType.PASSABLE);
		gameEngine.addTile(ZERO, ONE, TileType.PASSABLE); // should choose this...
		gameEngine.addTile(ONE, ONE, TileType.PLAYER);

		gameEngine.addTileAtRandomAvailablePoint(TileType.TREASURE);

		int actual = gameEngine.getTileCount(TileType.TREASURE);
		assertThat(actual, equalTo(1));
	}
	
	@Test
	public void add_random_to_empty_board_nothing_added() {
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
		Mockito.when(randomWrapper.nextInt((Integer)2))
				   .thenReturn(1);
		
		// no tiles added
		
		gameEngine.addTileAtRandomAvailablePoint(TileType.TREASURE);

		int actual = gameEngine.getTileCount(TileType.TREASURE);
		assertThat(actual, equalTo(0));
	}
	
	@Test
	public void add_and_get_treasure_tile() {
		TileType tileType = TileType.TREASURE;
		gameEngine.addTile(ZERO, ONE, tileType);
		TileType actual = gameEngine.getTileFromCoordinates(ZERO, ONE);
		assertThat(actual, equalTo(tileType));
	}
	
	@Test
	public void add_2_and_count_2_tile() {
		TileType tileType = TileType.TREASURE;
		gameEngine.addTile(ZERO, ONE, tileType);
		gameEngine.addTile(ONE, ZERO, tileType);
		int actual = gameEngine.getTileCount(tileType);
		assertThat(actual, equalTo(2));
	}
	
	@Test
	public void add_0_and_expect_0_without_exception() {
		TileType tileType = TileType.TREASURE;
		int actual = gameEngine.getTileCount(tileType);
		assertThat(actual, equalTo(0));
	}
	
	@Test
	public void add_2_treasure_then_list_treasure_tile_positions() {
		TileType tileType = TileType.TREASURE;
		gameEngine.addTile(ZERO, ONE, tileType);
		gameEngine.addTile(ONE, ZERO, tileType);
		List<Point> actual = gameEngine.getTilesOfType(tileType);
		assertThat(actual.get(0), equalTo(new Point(ZERO, ONE)));
		assertThat(actual.get(1), equalTo(new Point(ONE, ZERO)));
		assertThat(actual.size(), equalTo(2));
	}
	
	@Test
	public void add_0_treasure_then_expect_empty_list_of_tiles() {
		TileType tileType = TileType.TREASURE;
		List<Point> actual = gameEngine.getTilesOfType(tileType);
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), equalTo(0));
	}
	
	@Test
	public void add_1_passable_1_player_1_nonpassable_expect_1_empty_tile() {
		gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
		gameEngine.addTile(ONE, ZERO, TileType.PLAYER); // PASSABLE, but not empty
		gameEngine.addTile(ONE, ONE, TileType.NOT_PASSABLE);
		
		List<Point> actual = gameEngine.getEmptyTiles();
		assertThat(actual, is(notNullValue()));
		assertThat(actual.size(), equalTo(1));
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
}
