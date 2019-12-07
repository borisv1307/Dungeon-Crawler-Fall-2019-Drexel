package main;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import engine.GameEngine;
import parser.LevelCreator;
import parser.LevelCreatorITHelper;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class MovementTrapIntegrationTest extends LevelCreatorITHelper {

	@Before
	public void setUp() throws Throwable {
		List<String> levelStrings = new ArrayList<>();
		levelStrings.add("XXXXX");
		levelStrings.add("X T X");
		levelStrings.add("XTPTX");
		levelStrings.add("X T X");
		levelStrings.add("XXXXX");
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@Test
	public void player_can_not_move_through_trap_left() throws Throwable {
		gameEngine.keyLeft();
		playerIsLocatedAt(3, 3);
	}

	@Test
	public void player_can_not_move_through_trap_right() throws Throwable {
		gameEngine.keyRight();
		playerIsLocatedAt(3, 3);
	}

	@Test
	public void player_can_not_move_through_trap_down() throws Throwable {
		gameEngine.keyDown();
		playerIsLocatedAt(3, 3);
	}

	@Test
	public void player_can_not_move_through_trap_up() throws Throwable {
		gameEngine.keyUp();
		playerIsLocatedAt(3, 3);
	}

}
