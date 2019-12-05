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
import wrappers.JOptionPaneWrapper;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	

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
 public void random_Number_Generator() {
		gameEngine.addTile(ONE, ONE, TileType.PLAYER);
		gameEngine.addTile(ZERO, ONE, TileType.PASSABLE);
		gameEngine.addTile(ONE, TWO, TileType.BLOCKER);
		
	 RandomNumberGenerator randomNumber=Mockito.mock(RandomNumberGenerator.class);
	 Mockito.when(randomNumber.randomCoordinatesForBlockers(16)).thenReturn(1);
	 Mockito.when(randomNumber.randomCoordinatesForBlockers(8)).thenReturn(2);
	 gameEngine.movePlayer(-1,0,randomNumber);
	assertThat(1,equalTo(gameEngine.randomXCoordinatesArray[2]));
	assertThat(2,equalTo(gameEngine.randomYCoordinatesArray[0])); 
	  
 }

@Test
public void displaying_number_of_moves_once_game_is_over() {
	
	JOptionPaneWrapper  jOptionPaneWrapper = Mockito.mock(JOptionPaneWrapper.class);
	gameEngine.displayStatus(3,jOptionPaneWrapper); 
	Mockito.verify(jOptionPaneWrapper,Mockito.times(1)).showDialoge(3);	
}


}
