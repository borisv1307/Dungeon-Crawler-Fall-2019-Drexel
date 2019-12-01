package parser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class ComeBacktoStartPointStepDef extends LevelCreationStepDefHelper {

	GameEngine engine;

	@Given("^the level (\\d+) is ongoing:$")
	public void the_level_is_ongoing(int level, List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		LevelCreator levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX,
				new ReaderWrapper());

		engine = new GameEngine(levelCreator);
	}

	@When("^the player hits (\\d+),(\\d+)$")
	public void the_player_hits(int x, int y) throws Throwable {
		engine.jumpPlayer(x - 1, y - 1);
	}

	@Then("^the level will be (\\d+) and position will be (\\d+),(\\d+)$")
	public void the_level_will_be_and_position_will_be(int level, int x, int y) throws Throwable {
		assertEquals(level, engine.getLevel());
		assertEquals(x - 1, engine.getPlayerXCoordinate());
		assertEquals(y - 1, engine.getPlayerYCoordinate());
	}

}
