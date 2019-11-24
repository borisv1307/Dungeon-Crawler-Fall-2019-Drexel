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
	public void generate_random_numbers_for_enemy_to_move() {
		RandomWrapper randomWrapper = Mockito.mock(RandomWrapper.class);
		gameEngine.generateRandomNumbers(randomWrapper);
		Mockito.verify(randomWrapper).nextInt(4);
	}

	@Test
	public void predict_movement_of_enemy() {
		TileType tileType = TileType.ENEMY;
		gameEngine.addTile(3, 3, tileType);

		for (int i = 0; i < 4; i++) {
			gameEngine.generateMoveForEnemy(i);
			int actualX = gameEngine.getEnemyXCoordinate();
			int actualY = gameEngine.getEnemyYCoordinate();
			if (i == 0) {
				assertThat(actualX, equalTo(2));
				assertThat(actualY, equalTo(3));
			} else if (i == 1) {
				assertThat(actualX, equalTo(3));
				assertThat(actualY, equalTo(3));
			} else if (i == 2) {
				assertThat(actualX, equalTo(3));
				assertThat(actualY, equalTo(2));
			} else if (i == 3) {
				assertThat(actualX, equalTo(3));
				assertThat(actualY, equalTo(3));
			}
		}
	}
}