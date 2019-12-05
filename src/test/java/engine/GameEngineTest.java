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
import wrappers.RandomWrapper;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);

	GameEngine gameEngine;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		gameEngine = new GameEngine(levelCreator, randomWrapper);
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
	public void mock_random_return_0_enemy_moves_left() {
		TileType tileType = TileType.ENEMY;
		gameEngine.addTile(3, 3, tileType);
		Mockito.when(randomWrapper.nextInt(4)).thenReturn(0);
		gameEngine.generateMoveForEnemy();
		int actualX = gameEngine.getEnemyXCoordinate();
		int actualY = gameEngine.getEnemyYCoordinate();
		assertThat(actualX, equalTo(2));
		assertThat(actualY, equalTo(3));
	}

	@Test
	public void mock_random_return_1_enemy_moves_right() {
		TileType tileType = TileType.ENEMY;
		gameEngine.addTile(3, 3, tileType);
		Mockito.when(randomWrapper.nextInt(4)).thenReturn(1);
		gameEngine.generateMoveForEnemy();
		int actualX = gameEngine.getEnemyXCoordinate();
		int actualY = gameEngine.getEnemyYCoordinate();
		assertThat(actualX, equalTo(4));
		assertThat(actualY, equalTo(3));
	}

	@Test
	public void mock_random_return_2_enemy_moves_up() {
		TileType tileType = TileType.ENEMY;
		gameEngine.addTile(3, 3, tileType);
		Mockito.when(randomWrapper.nextInt(4)).thenReturn(2);
		gameEngine.generateMoveForEnemy();
		int actualX = gameEngine.getEnemyXCoordinate();
		int actualY = gameEngine.getEnemyYCoordinate();
		assertThat(actualX, equalTo(3));
		assertThat(actualY, equalTo(2));
	}

	@Test
	public void mock_random_return_3_enemy_moves_down() {
		TileType tileType = TileType.ENEMY;
		gameEngine.addTile(3, 3, tileType);
		Mockito.when(randomWrapper.nextInt(4)).thenReturn(3);
		gameEngine.generateMoveForEnemy();
		int actualX = gameEngine.getEnemyXCoordinate();
		int actualY = gameEngine.getEnemyYCoordinate();
		assertThat(actualX, equalTo(3));
		assertThat(actualY, equalTo(4));
	}

	@Test
	public void mock_random_return_4_enemy_doesnt_move() {
		TileType tileType = TileType.ENEMY;
		gameEngine.addTile(3, 3, tileType);
		Mockito.when(randomWrapper.nextInt(4)).thenReturn(4);
		gameEngine.generateMoveForEnemy();
		int actualX = gameEngine.getEnemyXCoordinate();
		int actualY = gameEngine.getEnemyYCoordinate();
		assertThat(actualX, equalTo(3));
		assertThat(actualY, equalTo(3));
	}
}