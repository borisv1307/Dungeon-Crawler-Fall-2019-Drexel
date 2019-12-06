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

public class MovementMultipleMovableTilesIntegrationTest extends LevelCreatorITHelper {

	Movement movement;

	@Before
	public void setUp() throws Throwable {
		List<String> levelStrings = new ArrayList<>();
		levelStrings.add("XXXXXXXXXXXXX");
		levelStrings.add("X           X");
		levelStrings.add("X     M     X");
		levelStrings.add("X           X");
		levelStrings.add("X     M     X");
		levelStrings.add("X     M     X");
		levelStrings.add("X M MMPMM M X");
		levelStrings.add("X     M     X");
		levelStrings.add("X     M     X");
		levelStrings.add("X           X");
		levelStrings.add("X     M     X");
		levelStrings.add("X           X");
		levelStrings.add("XXXXXXXXXXXXX");
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		movement = new Movement(gameEngine);
	}

	@Test
	public void player_can_move_two_movable_tiles_to_the_left() throws Throwable {
		movement.keyLeft();

		playerIsLocatedAt(5, 6);
		checkTileTypeByLocation(4, 6, 'M');
		checkTileTypeByLocation(3, 6, 'M');
	}

	@Test
	public void player_can_move_two_movable_tiles_to_the_right() throws Throwable {
		movement.keyRight();

		playerIsLocatedAt(7, 6);
		checkTileTypeByLocation(8, 6, 'M');
		checkTileTypeByLocation(9, 6, 'M');
	}

	@Test
	public void player_can_move_two_movable_tiles_up() throws Throwable {
		movement.keyUp();

		playerIsLocatedAt(6, 5);
		checkTileTypeByLocation(6, 4, 'M');
		checkTileTypeByLocation(6, 3, 'M');
	}

	@Test
	public void player_can_move_two_movable_tiles_down() throws Throwable {
		movement.keyDown();

		playerIsLocatedAt(6, 7);
		checkTileTypeByLocation(6, 8, 'M');
		checkTileTypeByLocation(6, 9, 'M');
	}

	@Test
	public void player_can_move_more_than_two_movable_tiles_to_the_left() throws Throwable {
		movement.keyLeft();
		movement.keyLeft();

		playerIsLocatedAt(4, 6);
		checkTileTypeByLocation(3, 6, 'M');
		checkTileTypeByLocation(2, 6, 'M');
		checkTileTypeByLocation(1, 6, 'M');
	}

	@Test
	public void player_cannot_move_movable_past_not_passable_left() throws Throwable {
		movement.keyLeft();
		movement.keyLeft();
		movement.keyLeft();

		playerIsLocatedAt(4, 6);
		checkTileTypeByLocation(3, 6, 'M');
		checkTileTypeByLocation(2, 6, 'M');
		checkTileTypeByLocation(1, 6, 'M');
	}

	@Test
	public void player_can_move_more_than_two_movable_tiles_to_the_right() throws Throwable {
		movement.keyRight();
		movement.keyRight();

		playerIsLocatedAt(8, 6);
		checkTileTypeByLocation(9, 6, 'M');
		checkTileTypeByLocation(10, 6, 'M');
		checkTileTypeByLocation(11, 6, 'M');
	}

	@Test
	public void player_cannot_move_movable_past_not_passable_right() throws Throwable {
		movement.keyRight();
		movement.keyRight();
		movement.keyRight();

		playerIsLocatedAt(8, 6);
		checkTileTypeByLocation(9, 6, 'M');
		checkTileTypeByLocation(10, 6, 'M');
		checkTileTypeByLocation(11, 6, 'M');
	}

	@Test
	public void player_can_move_more_than_two_movable_tiles_up() throws Throwable {
		movement.keyUp();
		movement.keyUp();

		playerIsLocatedAt(6, 4);
		checkTileTypeByLocation(6, 3, 'M');
		checkTileTypeByLocation(6, 2, 'M');
		checkTileTypeByLocation(6, 1, 'M');
	}

	@Test
	public void player_cannot_move_movable_past_not_passable_up() throws Throwable {
		movement.keyUp();
		movement.keyUp();
		movement.keyUp();

		playerIsLocatedAt(6, 4);
		checkTileTypeByLocation(6, 3, 'M');
		checkTileTypeByLocation(6, 2, 'M');
		checkTileTypeByLocation(6, 1, 'M');
	}

	@Test
	public void player_can_more_than_move_two_movable_tiles_down() throws Throwable {
		movement.keyDown();
		movement.keyDown();

		playerIsLocatedAt(6, 8);
		checkTileTypeByLocation(6, 9, 'M');
		checkTileTypeByLocation(6, 10, 'M');
		checkTileTypeByLocation(6, 11, 'M');
	}

	@Test
	public void player_cannot_move_movable_past_not_passable_down() throws Throwable {
		movement.keyDown();
		movement.keyDown();

		playerIsLocatedAt(6, 8);
		checkTileTypeByLocation(6, 9, 'M');
		checkTileTypeByLocation(6, 10, 'M');
		checkTileTypeByLocation(6, 11, 'M');
	}

}
