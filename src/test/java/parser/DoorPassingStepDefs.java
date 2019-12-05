package parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class DoorPassingStepDefs extends LevelCreationStepDefHelper {
	private GameEngine gameEngine;

	@Given("^the level of game with door is:$")
	public void the_level_of_game_with_door_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the key is not loacted on location \\((\\d+), (\\d+)\\)$")
	public void the_key_is_not_loacted_on_location(int keyXCoordinate, int keyYCoordinate) throws Throwable {
		gameEngine.keyUp();
	}

	@Then("^the player is located on door location \\((\\d+), (\\d+)\\)$")
	public void the_player_is_located_on_door_location(int doorXCoordinate, int doorYCoordinate) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(doorXCoordinate - COORDINATE_OFFSET));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(doorYCoordinate - COORDINATE_OFFSET));
	}
}
