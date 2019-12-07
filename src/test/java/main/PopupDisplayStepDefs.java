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
import wrappers.ReaderWrapper;

public class PopupDisplayStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;
	private GameFrame gameFrame;

	@Given("^Popup not to display when count is one$")
	public void popup_not_to_display_when_count_is_one(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameFrame = Mockito.mock(GameFrame.class);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), gameFrame);

	}

	@When("^player moves up and kill one enemy$")
	public void player_moves_up_and_kill_one_enemy() throws Throwable {
		gameEngine.keyUp();
	}

	@Then("^popup should not displyed$")
	public void popup_should_not_displyed() throws Throwable {
		Mockito.verify(gameFrame, Mockito.times(0)).popup(0);

	}

	@Given("^Popup to display when count is three$")
	public void popup_to_display_when_count_is_three(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameFrame = Mockito.mock(GameFrame.class);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), gameFrame);
		gameEngine.setEnemies_killed(2);

	}

	@When("^player moved in any direction to kill third enemy$")
	public void player_moved_in_any_direction_to_kill_third_enemy() throws Throwable {
		gameEngine.keyUp();
	}

	@Then("^popup should displyed$")
	public void popup_should_displyed() throws Throwable {
		Mockito.verify(gameFrame, Mockito.times(1)).popup(3);
	}
}
