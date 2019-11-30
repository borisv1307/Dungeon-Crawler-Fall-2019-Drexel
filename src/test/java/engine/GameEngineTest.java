package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.Component;
import java.util.EnumSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import characters.CharacterClass;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;

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
	public void starts_in_character_select_mode() {
		GameEngine.Mode actual = gameEngine.getMode();
		assertThat(actual, equalTo(GameEngine.Mode.SELECT_CHARACTER));
	}

	@Test
	public void starts_with_warrior_character_selected() {
		CharacterClass actual = gameEngine.getSelectedCharacter();
		assertThat(actual, equalTo(CharacterClass.WARRIOR));
	}

	@Test
	public void mode_changes_to_play_level_when_select_key_pressed() {

		gameEngine.keySelect();

		GameEngine.Mode actual = gameEngine.getMode();
		assertThat(actual, equalTo(GameEngine.Mode.PLAY_LEVEL));
	}

	private void assertSelectedCharacterIs(CharacterClass expected) {
		CharacterClass actual = gameEngine.getSelectedCharacter();
		assertThat(actual, equalTo(expected));
	}

	private void selectNextCharacter() {
		CharacterClass initial = gameEngine.getSelectedCharacter();
		gameEngine.keyDown();
		assertSelectedCharacterIs(initial.next());
	}

	@Test
	public void selected_character_changes_to_next_when_down_key_pressed() {
		selectNextCharacter();
	}

	@Test
	public void selected_character_changes_to_next_when_down_key_pressed_for_all_characters() {
		// this covers boundary condition where last character is selected and the down
		// key is pressed
		// causing the first character to be selected
		EnumSet.allOf(CharacterClass.class).forEach(c -> selectNextCharacter());
	}

	private void assertPlayerAtPosition(int x, int y) {
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertThat(actualX, equalTo(x));
		assertThat(actualY, equalTo(y));
	}

	@Test
	public void in_character_select_mode_player_does_not_move() {

		add_and_get_player_coordinates();

		int x = gameEngine.getPlayerXCoordinate();
		int y = gameEngine.getPlayerYCoordinate();

		gameEngine.keyDown();
		assertPlayerAtPosition(x, y);
		gameEngine.keyLeft();
		assertPlayerAtPosition(x, y);
		gameEngine.keyRight();
		assertPlayerAtPosition(x, y);
		gameEngine.keyUp();
		assertPlayerAtPosition(x, y);
	}

	@Test
	public void in_character_select_mode_up_left_right_keys_dont_change_character_selection() {

		CharacterClass initial = gameEngine.getSelectedCharacter();

		gameEngine.keyLeft();
		assertSelectedCharacterIs(initial);
		gameEngine.keyRight();
		assertSelectedCharacterIs(initial);
		gameEngine.keyUp();
		assertSelectedCharacterIs(initial);
	}

	@Test
	public void in_play_level_mode_up_key_does_not_change_character_selection() {

		add_and_get_player_coordinates();
		gameEngine.addTile(ONE, ONE, TileType.PASSABLE);
		gameEngine.addTile(ZERO, ZERO, TileType.PASSABLE);
		mode_changes_to_play_level_when_select_key_pressed();

		CharacterClass initial = gameEngine.getSelectedCharacter();

		gameEngine.keyRight();
		assertSelectedCharacterIs(initial);
		gameEngine.keyLeft();
		assertSelectedCharacterIs(initial);
		gameEngine.keyUp();
		assertSelectedCharacterIs(initial);
		gameEngine.keyDown();
		assertSelectedCharacterIs(initial);
	}
}
