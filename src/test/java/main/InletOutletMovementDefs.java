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

	@Given("^the level design with inlet and outlet is:$")
	public void the_level_design_with_inlet_and_outlet_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player moves \\(-(\\d+), -(\\d+)\\) from current position into inlet$")
	public void the_player_moves_from_current_position_into_inlet(int inletXCoordinateDiff, int inletYCoordinateDiff)
			throws Throwable {
		gameEngine.sendPlayerToInlet(inletXCoordinateDiff, inletYCoordinateDiff);
	}

	@Then("^the player is in outlet at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_in_outlet_at(int outletXCoordinate, int outletYCoordinate) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(outletXCoordinate));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(outletYCoordinate));
	}

}
