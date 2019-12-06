package movement;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import engine.GameEngine;
import movement.Movement;
import parser.LevelCreator;
import parser.LevelCreatorITHelper;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class MovementWallIntegrationTest extends LevelCreatorITHelper {

	Movement movement;

	@Before
	public void setUp() throws Throwable {
		List<String> levelStrings = new ArrayList<>();
		levelStrings.add("XXX");
		levelStrings.add("XPX");
		levelStrings.add("XXX");
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		movement = new Movement(gameEngine);
	}

	@Test
	public void player_can_not_move_through_wall_left() throws Throwable {
		movement.keyLeft();
		playerIsLocatedAt(1, 1);
	}

	@Test
	public void player_can_not_move_through_wall_right() throws Throwable {
		movement.keyRight();
		playerIsLocatedAt(1, 1);
	}

	@Test
	public void player_can_not_move_through_wall_down() throws Throwable {
		movement.keyDown();
		playerIsLocatedAt(1, 1);
	}

	@Test
	public void player_can_not_move_through_wall_up() throws Throwable {
		movement.keyUp();
		playerIsLocatedAt(1, 1);
	}

}