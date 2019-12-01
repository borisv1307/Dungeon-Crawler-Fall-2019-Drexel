package parser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class NextLevelStepDefs extends NextLevelStepDefHelper {

	GameEngine engine;

	@Given("^the level (\\d+) is:$")
	public void the_level_is(int level, List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings, level);
		LevelCreator levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX,
				new ReaderWrapper());

		engine = new GameEngine(levelCreator);
	}

	@When("^the player reaches (\\d+),(\\d+) of current level (\\d+)$")
	public void the_player_reaches_of_current_level(int x, int y, int level) throws Throwable {
		engine.jumpPlayer(x - 1, y - 1);
	}

	@Then("^the next level will be (\\d+)$")
	public void the_next_level_will_be(int expectedLevel) throws Throwable {
		assertEquals(expectedLevel, engine.getLevel());

	}

}
