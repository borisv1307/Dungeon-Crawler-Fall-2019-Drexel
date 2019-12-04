package main;

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

public class PopupMessagesStepDefs extends LevelCreationStepDefHelper {
	private GameEngine gameEngine;
	GameFrame frame;

	@Given("^The number of enemies already killed is one$")
	public void the_number_of_enemies_already_killed_is_one(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		frame = Mockito.mock(GameFrame.class);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), new MathWrapper(),
				frame);
		gameEngine.setNumberOfEnemiesKilled(1);
	}

	@When("^the player moves up and kill enemy$")
	public void the_player_moves_up_and_kill_enemy() throws Throwable {
		gameEngine.keyUp();
	}

	@Then("^Pop up message should not display$")
	public void pop_up_message_should_not_display() throws Throwable {
		Mockito.verify(frame, Mockito.times(0)).displayPopupMessage();
	}

	@Given("^The number of enemies already killed is nine$")
	public void the_number_of_enemies_already_killed_is_nine(List<String> levelStrings) throws Throwable {

		writeLevelFile(levelStrings);
		frame = Mockito.mock(GameFrame.class);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), new MathWrapper(),
				frame);
		gameEngine.setNumberOfEnemiesKilled(9);
	}

	@Then("^Pop up message should display once$")
	public void pop_up_message_should_display_once() throws Throwable {
		Mockito.verify(frame, Mockito.times(1)).displayPopupMessage();
	}
}
