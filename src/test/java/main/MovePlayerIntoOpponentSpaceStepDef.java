package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.mockito.Mockito;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import ui.GameFrame;
import values.TestingTunableParameters;
import wrappers.MathWrapper;
import wrappers.ReaderWrapper;

public class MovePlayerIntoOpponentSpaceStepDef extends LevelCreationStepDefHelper {
	private GameEngine gameEngine;
	MathWrapper mathWrapper;

	@Given("^The opponent current position is above the player$")
	public void the_opponent_current_position_is_above_the_player(List<String> levelStrings) throws Throwable {
		setUp(levelStrings);
	}

	@When("^the player moves up to opponent area$")
	public void the_player_moves_up_to_opponent_area() throws Throwable {
		gameEngine.keyUp();
	}

	@Given("^The opponent current position is below the player$")
	public void the_opponent_current_position_is_below_the_player(List<String> levelStrings) throws Throwable {
		setUp(levelStrings);
	}

	@When("^the player moves down to opponent area$")
	public void the_player_moves_down_to_opponent_area() throws Throwable {
		gameEngine.keyDown();
	}

	@Given("^The opponent current position is left side of the player$")
	public void the_opponent_current_position_is_left_side_of_the_player(List<String> levelStrings) throws Throwable {
		setUp(levelStrings);
	}

	@When("^the player moves left to opponent area$")
	public void the_player_moves_left_to_opponent_area() throws Throwable {
		gameEngine.keyLeft();
	}

	@Given("^The opponent current position is right side of the player$")
	public void the_opponent_current_position_is_right_side_of_the_player(List<String> levelStrings) throws Throwable {
		setUp(levelStrings);

	}

	@When("^the player moves right to opponent area$")
	public void the_player_moves_right_to_opponent_area() throws Throwable {
		gameEngine.keyRight();
	}

	@Then("^the player is located at new position (\\d+),(\\d+)$")
	public void the_player_is_located_at_new_position(int playerXCoordinate, int playerYCoordinate) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerXCoordinate));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerYCoordinate));

	}

	void setUp(List<String> levelStrings) throws FileNotFoundException, UnsupportedEncodingException {
		writeLevelFile(levelStrings);
		mathWrapper = Mockito.mock(MathWrapper.class);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), mathWrapper,
				new GameFrame());

	}
}
