package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class MovementOutlet extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;

	@Given("^the level design with player right of outlet:$")
	public void the_level_design_with_player_right_of_outlet(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves left into outlet$")
	public void the_player_moves_left_into_outlet() throws Throwable {
		gameEngine.keyLeft();
	}

	@Given("^the level design with player left of outlet:$")
	public void the_level_design_with_player_left_of_outlet(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves right into outlet$")
	public void the_player_moves_right_into_outlet() throws Throwable {
		gameEngine.keyRight();
	}

	@Given("^the level design with player above the outlet:$")
	public void the_level_design_with_player_above_the_outlet(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves down into outlet$")
	public void the_player_moves_down_into_outlet() throws Throwable {
		gameEngine.keyDown();
	}

	@Given("^the level design with player below the outlet:$")
	public void the_level_design_with_player_below_the_outlet(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves up into outlet$")
	public void the_player_moves_up_into_outlet() throws Throwable {
		gameEngine.keyUp();
	}

	@Then("^the player is at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_at(int playerX, int playerY) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY));
	}

}
