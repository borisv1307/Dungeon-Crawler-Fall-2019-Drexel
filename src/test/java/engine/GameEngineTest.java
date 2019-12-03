package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Component;
import java.awt.Event;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import ui.GamePanel;

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
	public void is_a_player() {
		
		assertEquals(true,gameEngine.IsPlayer(TileType.PLAYER));
	}
	@Test
	
	public void is_a_blocker() {
		
		assertEquals(true,gameEngine.IsBlocker(TileType.BLOCKER));
	}
	@Test
	public void is_a_blocker1() {
		
		assertEquals(true,gameEngine.IsBlocker1(TileType.BLOCKER1));
	}
	@Test
	public void is_a_blocker2() {
		
		assertEquals(true,gameEngine.IsBlocker2(TileType.BLOCKER2));
	}
	@Test
	public void is_a_blocker3() {
		
		assertEquals(true,gameEngine.IsBlocker3(TileType.BLOCKER3));
	}
	
	@Test
	public void set_and_get_exit() {
		boolean exit = true;
		gameEngine.setExit(exit);
		boolean actual = gameEngine.isExit();
		assertThat(actual, equalTo(exit));
	} 
	@Test
	
	public void add_and_get_blocker_coordinates() {
		TileType tileType = TileType.BLOCKER;
		gameEngine.addTile(ZERO, ONE, tileType);
		int actualX = gameEngine.getBlockerXCoordinate();
		int actualY = gameEngine.getBlockerYCoordinate();
		assertThat(actualX, equalTo(ZERO));
		assertThat(actualY, equalTo(ONE));
	}
	@Test
	public void add_and_get_blocker1_coordinates() {
		TileType tileType = TileType.BLOCKER1;
		gameEngine.addTile(ONE, ONE, tileType);
		int actualX = gameEngine.getBlocker1XCoordinate();
		int actualY = gameEngine.getBlocker1YCoordinate();
		assertThat(actualX, equalTo(ONE));
		assertThat(actualY, equalTo(ONE));
	}
	@Test
	public void add_and_get_blocker2_coordinates() {
		TileType tileType = TileType.BLOCKER2;
		gameEngine.addTile(ONE, ZERO, tileType);
		int actualX = gameEngine.getBlocker2XCoordinate();
		int actualY = gameEngine.getBlocker2YCoordinate();
		assertThat(actualX, equalTo(ONE));
		assertThat(actualY, equalTo(ZERO));
	}
	@Test
	public void add_and_get_blocker3_coordinates() {
		TileType tileType = TileType.BLOCKER3;
		gameEngine.addTile(ONE, ONE, tileType);
		int actualX = gameEngine.getBlocker3XCoordinate();
		int actualY = gameEngine.getBlocker3YCoordinate();
		assertThat(actualX, equalTo(ONE));
		assertThat(actualY, equalTo(ONE));
	}
	
@Test 
public void mocking_random_number_method() {
	GameEngine  gameEngine = Mockito.mock(GameEngine.class);
	gameEngine.randomXCoordinatesForBlockers(10);
	gameEngine.randomYCoordinatesForBlockers(10);
	Mockito.verify(gameEngine,Mockito.times(1)).randomXCoordinatesForBlockers(10);
	Mockito.verify(gameEngine,Mockito.times(1)).randomYCoordinatesForBlockers(10);
	
	
}
@Test
public void displaying_number_of_moves_once_game_is_over() {
	GameEngine  gameEngine = Mockito.mock(GameEngine.class);
	gameEngine.displayStatus(10);
	Mockito.verify(gameEngine,Mockito.times(1)).displayStatus(10);	
}


}
