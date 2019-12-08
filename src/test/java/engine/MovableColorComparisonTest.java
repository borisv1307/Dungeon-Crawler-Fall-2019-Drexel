package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import movement.Movement;
import parser.LevelCreator;
import parser.LevelCreatorITHelper;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;
import wrappers.SystemWrapper;

public class MovableColorComparisonTest extends LevelCreatorITHelper {

	GameEngine gameEngine;
	Movement movement;

	@Before
	public void setUp() throws Throwable {
		List<String> levelStrings = new ArrayList<>();
		levelStrings.add("XXXXXXXXXXXXXX");
		levelStrings.add("X M M M M M  X");
		levelStrings.add("X    P       X");
		levelStrings.add("XXXXXXXXXXXXXX");
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		movement = new Movement(gameEngine);
	}

	@Test
	public void check_for_movable_id_one_to_the_left_movable_id_two() {
		movement.keyUp();
		move_left_a_number_of_times(1);

		int currentSequence = gameEngine.checkSequence;
		assertThat(currentSequence, equalTo(2));
	}

	@Test
	public void check_for_movable_id_one_two_three_in_order() {
		move_right_a_number_of_times(2);
		movement.keyUp();
		move_left_a_number_of_times(2);

		int currentSequence = gameEngine.checkSequence;
		assertThat(currentSequence, equalTo(3));
	}

	@Test
	public void check_for_movable_id_one_two_three_four_in_order() {
		move_right_a_number_of_times(4);
		movement.keyUp();
		move_left_a_number_of_times(3);

		int currentSequence = gameEngine.checkSequence;
		assertThat(currentSequence, equalTo(4));
	}

	@Test
	public void check_for_movable_id_one_two_three_four_five_in_order() {
		move_right_a_number_of_times(6);
		movement.keyUp();
		move_left_a_number_of_times(4);

		int currentSequence = gameEngine.checkSequence;
		assertThat(currentSequence, equalTo(5));
	}

	@Test
	public void open_win_message() {
		SystemWrapper systemWrapper = Mockito.mock(SystemWrapper.class);
		gameEngine.displayWinMessage(systemWrapper);
		Mockito.verify(systemWrapper).JOptionPanelDisplay("Congratulations!", "You have successfully aligned blocks!");
	}

	public void move_right_a_number_of_times(int times) {
		while (times > 0) {
			movement.keyRight();
			times--;
		}
	}

	public void move_left_a_number_of_times(int times) {
		while (times > 0) {
			movement.keyLeft();
			times--;
		}
	}
}
