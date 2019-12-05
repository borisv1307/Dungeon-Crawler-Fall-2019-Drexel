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

public class MovementMultipleMovableIntegrationTest extends LevelCreatorITHelper {

	Movement movement;

	@Before
	public void setUp() throws Throwable {
		List<String> levelStrings = new ArrayList<>();
		levelStrings.add("XXXXXXXXXXX");
		levelStrings.add("X         X");
		levelStrings.add("X         X");
		levelStrings.add("X    M    X");
		levelStrings.add("X    M    X");
		levelStrings.add("X MMMPMM  X");
		levelStrings.add("X    M    X");
		levelStrings.add("X    M    X");
		levelStrings.add("X         X");
		levelStrings.add("X         X");
		levelStrings.add("XXXXXXXXXXX");
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		movement = new Movement(gameEngine);
	}

	@Test
	public void player_can_move_multiple_movable_tiles_to_the_left() throws Throwable {
		movement.keyLeft();

		playerIsLocatedAt(4, 5);
		checkTileTypeByLocation(3, 5, 'M');
		checkTileTypeByLocation(2, 5, 'M');
		checkTileTypeByLocation(1, 5, 'M');
	}

}
