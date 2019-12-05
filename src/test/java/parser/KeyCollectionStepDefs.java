package parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import tiles.TileType;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class KeyCollectionStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;

	@Given("^the level of game is:$")
	public void the_level_of_game_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves on top of key$")
	public void the_player_moves_on_top_of_key() throws Throwable {
		gameEngine.keyUp();

	}

	@Then("^the player is located on location \\((\\d+), (\\d+)\\)$")
	public void the_player_is_located_on_location(int keyXCoordinate, int keyYCoordinate) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(keyXCoordinate - COORDINATE_OFFSET));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(keyYCoordinate - COORDINATE_OFFSET));
	}

	@Then("^key dissappears from location \\((\\d+), (\\d+)\\)$")
	public void key_dissappears_from_location(int keyXCoordinate, int keyYCoordinate) throws Throwable {
		assertThat(gameEngine.getTileFromCoordinates(keyXCoordinate - COORDINATE_OFFSET,
				keyYCoordinate - COORDINATE_OFFSET), equalTo(TileType.PASSABLE));

	}

}
