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

public class GameExitStepDef extends GameExitStepDefHelper {
	GameEngine engine;

	@Given("^the level (\\d+) is reached:$")
	public void the_level_is_reached(int level, List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		LevelCreator levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX,
				new ReaderWrapper());

		engine = new GameEngine(levelCreator);
	}

	@When("^the player reaches (\\d+),(\\d+) of final level$")
	public void the_player_reaches_of_final_level(int x, int y) throws Throwable {
		engine.jumpPlayer(x - 1, y - 1);

	}

	@Then("^the game gets over$")
	public void the_game_gets_over() throws Throwable {
		assertThat(true, equalTo(engine.isExit()));
	}

}
