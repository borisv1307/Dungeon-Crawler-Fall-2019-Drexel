package parser;

import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class LevelMovePastStepDefs extends LevelCreatorITHelper {

	private static final int ONE = 1;
	private static final int THREE = 3;
	private GameEngine gameEngine;
	private LevelMove levelMove;
	private LevelCreator levelCreator;

	@Given("^level (\\d+) grid containing past is:$")
	public void level_grid_containing_past(int levelNumber, DataTable gameGrid) throws Throwable {
		List<String> level = gameGrid.asList(String.class);
		this.writeLevelFile(level);
		this.levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper());
		this.levelMove = new LevelMove(ONE, ONE, THREE);
		this.gameEngine = new GameEngine(this.levelCreator, this.levelMove);
		this.gameEngine.getLevelCreator().createLevel(this.gameEngine, levelNumber);
		this.levelMove.setLevelNum(THREE);
	}

	@Then("^the player has moved to past level (\\d+)$")
	public void the_player_has_moved_to_past_level(int pastLevelNumber) throws Throwable {
		int actual = this.gameEngine.getLevelMove().getLevelNum();
		Assert.assertEquals(actual, pastLevelNumber);
	}

	@When("^the player in level moves right to past")
	public void the_player_in_level_moves_right_to_past() throws Throwable {
		this.gameEngine.keyRight();
	}

	@Then("^the player is now located on new level tile after past at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_now_located_on_new_Level_tile_after_past_at(int tileXCoordinate, int tileYCoordinate)
			throws Throwable {
		int actualX = this.gameEngine.getPlayerXCoordinate();
		int actualY = this.gameEngine.getPlayerYCoordinate();
		Assert.assertEquals(actualX, tileXCoordinate);
		Assert.assertEquals(actualY, tileYCoordinate);
	}

}