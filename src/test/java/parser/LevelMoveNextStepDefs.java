package parser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class LevelMoveNextStepDefs extends LevelCreatorITHelper {

	private GameEngine gameEngine;
	private LevelMove levelMove;
	private LevelCreator levelCreator;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;

	@Given("^grid containing next level (\\d+) is:$")
	public void grid_containing_next_level(int arg1, DataTable arg2) throws Throwable {
		List<String> level = arg2.asList(String.class);
		writeLevelFile(level);
		levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper());
		levelMove = new LevelMove(ONE, ONE, THREE);
		gameEngine = new GameEngine(levelCreator, levelMove);
		gameEngine.getLevelCreator().createLevel(gameEngine, arg1);
		levelMove.setLevelNum(TWO);
	}

	@When("^the player in level moves right to next")
	public void the_player_in_level_moves_right_to_next() throws Throwable {
		gameEngine.keyRight();
	}

	@Then("^the player is now located on new level tile after next at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_now_located_on_new_level_tile_after_next_at(int arg1, int arg2) throws Throwable {
		int actualX = gameEngine.getPlayerXCoordinate();
		int actualY = gameEngine.getPlayerYCoordinate();
		assertEquals(actualX, arg1);
		assertEquals(actualY, arg2);
	}

	@Then("^the player has moved to next level (\\d+)$")
	public void the_player_has_moved_to_next_level(int arg1) throws Throwable {
		int actual = gameEngine.getLevelMove().getLevelNum();
		assertEquals(actual, arg1);
	}

}
