package main;

import static org.junit.Assert.assertFalse;

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

public class OpponentMovementStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;
	MathWrapper mathWrapper;

	@Given("^The Opponent current position is above the player$")
	public void the_Opponent_current_position_is_above_the_player(List<String> levelStrings) throws Throwable {
		setUp(levelStrings);
	}

	@When("^the player moves up to opponent space$")
	public void the_player_moves_up_to_opponent_space() throws Throwable {
		gameEngine.keyUp();
	}

	@Given("^The Opponent current position is below the player$")
	public void the_Opponent_current_position_is_below_the_player(List<String> levelStrings) throws Throwable {
		setUp(levelStrings);
	}

	@When("^the player moves down to opponent space$")
	public void the_player_moves_down_to_opponent_space() throws Throwable {
		gameEngine.keyDown();
	}

	@Given("^The Opponent current position is left side of the player$")
	public void the_Opponent_current_position_is_left_side_of_the_player(List<String> levelStrings) throws Throwable {
		setUp(levelStrings);
	}

	@When("^the player moves left to opponent space$")
	public void the_player_moves_left_to_opponent_space() throws Throwable {
		gameEngine.keyLeft();
	}

	@Given("^The Opponent current position is right side of the player$")
	public void the_Opponent_current_position_is_right_side_of_the_player(List<String> levelStrings) throws Throwable {
		setUp(levelStrings);
	}

	@When("^the player moves right to opponent space$")
	public void the_player_moves_right_to_opponent_space() throws Throwable {
		gameEngine.keyRight();
	}

	@Then("^the opponent should move to random position$")
	public void the_opponent_should_move_to_random_position() throws Throwable {
		assertFalse(gameEngine.getPlayerXCoordinate() == gameEngine.getOpponentXCoordinate());
		assertFalse(gameEngine.getPlayerYCoordinate() == gameEngine.getOpponentYCoordinate());
		Mockito.verify(mathWrapper, Mockito.times(1)).getRandomInteger(19);
		Mockito.verify(mathWrapper, Mockito.times(1)).getRandomInteger(9);
	}

	void setUp(List<String> levelStrings) throws FileNotFoundException, UnsupportedEncodingException {
		writeLevelFile(levelStrings);
		mathWrapper = Mockito.mock(MathWrapper.class);
		GameFrame frame = Mockito.mock(GameFrame.class);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), mathWrapper,
				frame);
	}

}
