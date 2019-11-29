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

public class LevelMoveNextStepDefs extends LevelCreatorITHelper {

	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private GameEngine gameEngine;
	private LevelMove levelMove;
	private LevelCreator levelCreator;

	@Given("^level (\\d+) grid containing next is:$")
	public void level_grid_containing_next(int level_number, DataTable game_grid) throws Throwable {
		List<String> level = game_grid.asList(String.class);
		this.writeLevelFile(level);
		this.levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper());
		this.levelMove = new LevelMove(ONE, ONE, THREE);
		this.gameEngine = new GameEngine(this.levelCreator, this.levelMove);
		this.gameEngine.getLevelCreator().createLevel(this.gameEngine, level_number);
		this.levelMove.setLevelNum(TWO);
	}

	@Then("^the player has moved to next level (\\d+)$")
	public void the_player_has_moved_to_next_level(int next_Level_number) throws Throwable {
		int actual = this.gameEngine.getLevelMove().getLevelNum();
		Assert.assertEquals(actual, next_Level_number);
	}

	@When("^the player in level moves right to next")
	public void the_player_in_level_moves_right_to_next() throws Throwable {
		this.gameEngine.keyRight();
	}

	@Then("^the player is now located on new level tile after next at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_now_located_on_new_level_tile_after_next_at(int tileXCoordinate, int tileYCoordinate)
			throws Throwable {
		int actualX = this.gameEngine.getPlayerXCoordinate();
		int actualY = this.gameEngine.getPlayerYCoordinate();
		Assert.assertEquals(actualX, tileXCoordinate);
		Assert.assertEquals(actualY, tileYCoordinate);
	}

}
