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

public class InletOutletMovementDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;

	@Given("^the level design with player right of inlet and outlet is:$")
	public void the_level_design_with_player_right_of_inlet_and_outlet_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves left into inlet$")
	public void the_player_moves_left_into_inlet() throws Throwable {
		gameEngine.keyLeft();
	}

	@Given("^the level design with player left of inlet and outlet is:$")
	public void the_level_design_with_player_left_of_inlet_and_outlet_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves right into inlet$")
	public void the_player_moves_right_into_inlet() throws Throwable {
		gameEngine.keyRight();
	}

	@Given("^the level design with player above the inlet and outlet is:$")
	public void the_level_design_with_player_above_the_inlet_and_outlet_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves down into inlet$")
	public void the_player_moves_down_into_inlet() throws Throwable {
		gameEngine.keyDown();
	}

	@Given("^the level design with player below the inlet and outlet is:$")
	public void the_level_design_with_player_below_the_inlet_and_outlet_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves up into inlet$")
	public void the_player_moves_up_into_inlet() throws Throwable {
		gameEngine.keyUp();
	}

	@Then("^the player is in outlet at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_in_outlet_at(int outletX, int outletY) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(outletX));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(outletY));
	}

}
