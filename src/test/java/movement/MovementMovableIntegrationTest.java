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

public class MovementMovableIntegrationTest extends LevelCreatorITHelper {

	Movement movement;

	@Before
	public void setUp() throws Throwable {
		List<String> levelStrings = new ArrayList<>();
		levelStrings.add("XXXXXXXXX");
		levelStrings.add("X       X");
		levelStrings.add("X       X");
		levelStrings.add("X   M   X");
		levelStrings.add("X  MPM  X");
		levelStrings.add("X   M   X");
		levelStrings.add("X       X");
		levelStrings.add("X       X");
		levelStrings.add("XXXXXXXXX");
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		movement = new Movement(gameEngine);
	}

	@Test
	public void player_can_move_movable_tile_to_the_left() throws Throwable {
		movement.keyLeft();

		playerIsLocatedAt(3, 4);
		checkTileTypeByLocation(2, 4, 'M');
	}

	@Test
	public void player_can_move_movable_tile_to_the_right() throws Throwable {
		movement.keyRight();

		playerIsLocatedAt(5, 4);
		checkTileTypeByLocation(6, 4, 'M');
	}

	@Test
	public void player_can_move_movable_tile_up() throws Throwable {
		movement.keyUp();

		playerIsLocatedAt(4, 3);
		checkTileTypeByLocation(4, 2, 'M');
	}

	@Test
	public void player_can_move_movable_tile_down() throws Throwable {
		movement.keyDown();

		playerIsLocatedAt(4, 5);
		checkTileTypeByLocation(4, 6, 'M');
	}

	@Test
	public void player_can_move_movable_tile_to_the_left_more_than_once() throws Throwable {
		movement.keyLeft();
		movement.keyLeft();

		playerIsLocatedAt(2, 4);
		checkTileTypeByLocation(1, 4, 'M');
	}

	@Test
	public void player_can_move_movable_tile_to_the_right_more_than_once() throws Throwable {
		movement.keyRight();
		movement.keyRight();

		playerIsLocatedAt(6, 4);
		checkTileTypeByLocation(7, 4, 'M');
	}

	@Test
	public void player_can_move_movable_tile_up_more_than_once() throws Throwable {
		movement.keyUp();
		movement.keyUp();

		playerIsLocatedAt(4, 2);
		checkTileTypeByLocation(4, 1, 'M');
	}

	@Test
	public void player_can_move_movable_tile_down_more_than_once() throws Throwable {
		movement.keyDown();
		movement.keyDown();

		playerIsLocatedAt(4, 6);
		checkTileTypeByLocation(4, 7, 'M');
	}

	@Test
	public void player_cannot_move_movable_tile_into_not_passable_tile_to_the_left() throws Throwable {
		movement.keyLeft();
		movement.keyLeft();
		movement.keyLeft();

		playerIsLocatedAt(2, 4);
		checkTileTypeByLocation(1, 4, 'M');
	}

	@Test
	public void player_cannot_move_movable_tile_into_not_passable_tile_to_the_right() throws Throwable {
		movement.keyRight();
		movement.keyRight();
		movement.keyRight();

		playerIsLocatedAt(6, 4);
		checkTileTypeByLocation(7, 4, 'M');
	}

	@Test
	public void player_cannot_move_movable_tile_into_not_passable_tile_up() throws Throwable {
		movement.keyUp();
		movement.keyUp();
		movement.keyUp();

		playerIsLocatedAt(4, 2);
		checkTileTypeByLocation(4, 1, 'M');
	}

	@Test
	public void player_cannot_move_movable_tile_into_not_passable_tile_down() throws Throwable {
		movement.keyDown();
		movement.keyDown();
		movement.keyDown();

		playerIsLocatedAt(4, 6);
		checkTileTypeByLocation(4, 7, 'M');
	}
}
