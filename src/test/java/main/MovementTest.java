package main;

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

public class MovementTest extends LevelCreatorITHelper {

	Movement movement;

	@Before
	public void setUp() throws Throwable {
		List<String> levelStrings = new ArrayList<>();
		levelStrings.add("XXXXX");
		levelStrings.add("X   X");
		levelStrings.add("X P X");
		levelStrings.add("X M X");
		levelStrings.add("X   X");
		levelStrings.add("XXXXX");
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		movement = new Movement(gameEngine);
	}

	@Test
	public void player_can_move_left() throws Throwable {
		movement.keyLeft();
		playerIsLocatedAt(1, 2);
	}

	@Test
	public void player_can_move_right() throws Throwable {
		movement.keyRight();
		playerIsLocatedAt(3, 2);
	}

	@Test
	public void player_can_move_up() throws Throwable {
		movement.keyUp();
		playerIsLocatedAt(2, 1);
	}

	@Test
	public void player_can_move_down() throws Throwable {
		movement.keyDown();
		playerIsLocatedAt(2, 3);
	}

	@Test
	public void player_can_move_movable_tile() throws Throwable {
		movement.keyDown();
		playerIsLocatedAt(2, 3);
		movableIsLocatedAt(2, 4);
	}

	@Test
	public void player_and_movable_stay_in_the_same_tiles_if_trying_to_move_to_not_passable_tile() throws Throwable {
		movement.keyDown();
		movement.keyDown();
		playerIsLocatedAt(2, 3);
		movableIsLocatedAt(2, 4);
	}

}
