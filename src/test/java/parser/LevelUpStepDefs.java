package parser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import exception.LastLevelException;
import movement.Movement;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class LevelUpStepDefs extends LevelCreationStepDefHelper {
	GameEngine gameEngine;
	String lastLevelException;

	@Given("^the level (\\d+) is:$")
	public void the_level_is(int currentLevel, DataTable levelStrings) throws Throwable {

		List<String> levelString = levelStrings.asList(String.class);

		writeLevelFile(levelString);
		gameEngine = new GameEngine(new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper(),
				new LevelHandler()), new Movement());
		gameEngine.setLevel(currentLevel);

	}

	@When("^the player reaches (\\d+) and makes (.*)$")
	public void the_player_reaches_and_makes_moves(int level, List<String> movements) throws Throwable {

		gameEngine.setLevel(level);
		String[] movementList = movements.toArray(new String[0]);
		int i = 0;
		while (i < movementList.length) {
			if (movementList[i].equals("left"))
				gameEngine.keyLeft();
			if (movementList[i].equals("up"))
				gameEngine.keyUp();
			if (movementList[i].equals("right"))
				gameEngine.keyRight();
			if (movementList[i].equals("down"))
				gameEngine.keyDown();
			i++;
		}
	}

	@When("^a new level is trying to get created$")
	public void a_new_level_is_trying_to_get_created() throws Throwable {
		LevelCreator levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper(),
				new LevelHandler());
		try {
			levelCreator.getNewLevelFile(gameEngine, 4);
		} catch (LastLevelException ex) {
			lastLevelException = ex.getMessage();
		}
	}

	@Then("^Current Level will be (\\d+)$")
	public void level_will_be(int expectedLevel) throws Throwable {

		assertEquals(expectedLevel, gameEngine.getLevel());

	}

	@Then("^last level message is thrown$")
	public void last_level_message_is_thrown() throws Throwable {

		assertEquals("exceptionChecking", new LastLevelException().getMessage(), lastLevelException);
	}

	@Then("^the game will EXIT$")
	public void the_game_will_EXIT() throws Throwable {
		Assert.assertTrue(gameEngine.isExit());
	}

}
