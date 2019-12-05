package movement;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import engine.GameEngine;
import parser.LevelCreator;
import parser.LevelCreatorITHelper;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class MovementTest extends LevelCreatorITHelper {

	Movement movement;

	@Before
	public void setUp() throws Throwable {
		List<String> levelStrings = new ArrayList<>();
		levelStrings.add("XXXXXXX");
		levelStrings.add("X     X");
		levelStrings.add("X     X");
		levelStrings.add("X  P  X");
		levelStrings.add("X     X");
		levelStrings.add("X     X");
		levelStrings.add("XXXXXXX");
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		movement = new Movement(gameEngine);
	}

	@Test
	public void player_can_move_left() throws Throwable {
		movement.keyLeft();

		playerIsLocatedAt(2, 3);
	}

	@Test
	public void player_can_move_left_more_than_once() throws Throwable {
		movement.keyLeft();
		movement.keyLeft();

		playerIsLocatedAt(1, 3);
	}

	@Test
	public void player_can_move_right() throws Throwable {
		movement.keyRight();

		playerIsLocatedAt(4, 3);
	}

	@Test
	public void player_can_move_right_more_than_once() throws Throwable {
		movement.keyRight();
		movement.keyRight();

		playerIsLocatedAt(5, 3);
	}

	@Test
	public void player_can_move_up() throws Throwable {
		movement.keyUp();

		playerIsLocatedAt(3, 2);
	}

	@Test
	public void player_can_move_up_more_than_once() throws Throwable {
		movement.keyUp();
		movement.keyUp();

		playerIsLocatedAt(3, 1);
	}

	@Test
	public void player_can_move_down() throws Throwable {
		movement.keyDown();

		playerIsLocatedAt(3, 4);
	}

	@Test
	public void player_can_move_down_more_than_once() throws Throwable {
		movement.keyDown();
		movement.keyDown();

		playerIsLocatedAt(3, 5);
	}

	@Test
	public void player_can_move_left_then_right() throws Throwable {
		movement.keyLeft();
		movement.keyRight();

		playerIsLocatedAt(3, 3);
	}

	@Test
	public void player_can_move_up_then_down() throws Throwable {
		movement.keyUp();
		movement.keyDown();

		playerIsLocatedAt(3, 3);
	}

	@Test
	public void player_can_move_up_down_left_right() throws Throwable {
		movement.keyUp();
		movement.keyDown();
		movement.keyLeft();
		movement.keyRight();

		playerIsLocatedAt(3, 3);
	}

}
