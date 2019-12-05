package parser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class WinGameDef extends LevelCreationStepDefHelper {
	private GameEngine gameEngine;

	@Given("^Level of game is:$")
	public void level_of_game_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^when player collects coin and hit location \\((\\d+), (\\d+)\\)$")
	public void when_player_collects_coin_and_hit_location(int xcod, int ycod) throws Throwable {
		gameEngine.setLocation(xcod, ycod);
	}

	@Then("^the game prints \"([^\"]*)\"$")
	public void the_game_prints(String message) throws Throwable {
		assertEquals(message, "Game won");
	}

}
