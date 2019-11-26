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
import wrappers.MathWrapper;
import wrappers.ReaderWrapper;

public class MovePlayerIntoOpponentSpaceStepDef extends LevelCreationStepDefHelper {
	private GameEngine gameEngine;

	@Given("^the level design by adding opponent is:$")
	public void the_level_design_by_adding_opponent_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), new MathWrapper());

	}

	@When("^the player moves up to opponent space and opponent current position is above the player$")
	public void the_player_moves_up_to_opponent_space_and_opponent_current_position_is_above_the_player()
			throws Throwable {
		gameEngine.keyUp();
	}

	@When("^the player moves down to opponent space and opponent current position is below the player$")
	public void the_player_moves_down_to_opponent_space_and_opponent_current_position_is_below_the_player()
			throws Throwable {
		gameEngine.setOpponent(2, 3);
		gameEngine.keyDown();
	}

	@When("^the player moves left to opponent space and opponent current position is left side of the player$")
	public void the_player_moves_left_to_opponent_space_and_opponent_current_position_is_left_side_of_the_player()
			throws Throwable {
		gameEngine.setOpponent(1, 2);
		gameEngine.keyLeft();
	}

	@When("^the player moves down to opponent space and opponent current position is right side of the player$")
	public void the_player_moves_down_to_opponent_space_and_opponent_current_position_is_right_side_of_the_player()
			throws Throwable {
		gameEngine.setOpponent(3, 2);
		gameEngine.keyRight();
	}

	@Then("^the player is located at new position (\\d+),(\\d+)$")
	public void the_player_is_located_at_new_position(int playerXCoordinate, int playerYCoordinate) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerXCoordinate));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerYCoordinate));
	}
}
