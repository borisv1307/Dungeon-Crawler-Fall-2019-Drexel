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
import wrappers.JOptionPaneWrapper;

public class GameEngineTest {

	private static final int ZERO = 0;
	private static final int ONE = 1;
	private int game_score;

	GameEngine gameEngine;
	JOptionPaneWrapper joptionPaneWrapper;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		JOptionPaneWrapper joptionPaneWrapper = Mockito.mock(JOptionPaneWrapper.class);
		this.joptionPaneWrapper = joptionPaneWrapper;
		gameEngine = new GameEngine(levelCreator, joptionPaneWrapper);
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
	public void checking_score() {
		gameEngine.addTile(ZERO, ONE, TileType.PLAYER);
		gameEngine.addTile(ONE, ONE, TileType.ENERGY);
		gameEngine.addTile(ONE, ZERO, TileType.ENERGY);
		gameEngine.movePlayer(1, 0);
		gameEngine.movePlayer(0, -1);
		assertThat(2, equalTo(gameEngine.game_score));

	}

	@Test
	public void checking_player_killing_status() {
		gameEngine.addTile(ZERO, ONE, TileType.PLAYER);
		gameEngine.addTile(ONE, ONE, TileType.ENEMY);
		gameEngine.movePlayer(1, 0);
		assertThat(11, equalTo(gameEngine.getPlayerXCoordinate()));
		assertThat(11, equalTo(gameEngine.getPlayerYCoordinate()));

	}

	@Test
	public void display_score_box_when_reached_max() {

		game_score = 5;
		gameEngine.messageDisplay(joptionPaneWrapper, game_score);
		Mockito.verify(joptionPaneWrapper, Mockito.times(1)).showMessageDialog(null, "Reached Max Score:5",
				"ENERGY SCORE", joptionPaneWrapper.ERROR_MESSAGE);

	}

	@Test
	public void display_score_box_when_player_killed_after_one_energy_gained() {
		game_score = 1;
		gameEngine.messageDisplay(joptionPaneWrapper, game_score);
		Mockito.verify(joptionPaneWrapper, Mockito.times(1)).showMessageDialog(null,
				"GAME END ..Lets try again!!! YOUR SCORE:" + game_score, "ENERGY SCORE",
				joptionPaneWrapper.ERROR_MESSAGE);

	}

	@Test
	public void display_score_box_when_player_killed_after_two_energy_gained() {
		game_score = 2;
		gameEngine.messageDisplay(joptionPaneWrapper, game_score);
		Mockito.verify(joptionPaneWrapper, Mockito.times(1)).showMessageDialog(null,
				"GAME END ..Lets try again!!! YOUR SCORE:" + game_score, "ENERGY SCORE",
				joptionPaneWrapper.ERROR_MESSAGE);

	}

	@Test
	public void display_score_box_when_player_killed_after_three_energy_gained() {
		game_score = 3;
		gameEngine.messageDisplay(joptionPaneWrapper, game_score);
		Mockito.verify(joptionPaneWrapper, Mockito.times(1)).showMessageDialog(null,
				"GAME END ..Lets try again!!! YOUR SCORE:" + game_score, "ENERGY SCORE",
				joptionPaneWrapper.ERROR_MESSAGE);

	}

	@Test
	public void display_score_box_when_player_killed_after_four_energy_gained() {
		game_score = 4;
		gameEngine.messageDisplay(joptionPaneWrapper, game_score);
		Mockito.verify(joptionPaneWrapper, Mockito.times(1)).showMessageDialog(null,
				"GAME END ..Lets try again!!! YOUR SCORE:" + game_score, "ENERGY SCORE",
				joptionPaneWrapper.ERROR_MESSAGE);

	}

}
