package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class MovementStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;
	wrappers.RandomWrapper randomWrapper = new wrappers.RandomWrapper();

	@Given("^the level design is:$")
	public void level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), randomWrapper);
	}

	@When("^the player moves left$")
	public void the_player_moves_left() throws Throwable {
		gameEngine.keyLeft();
	}

	@When("^the player moves right$")
	public void the_player_moves_right() throws Throwable {
		gameEngine.keyRight();
	}

	@When("^the player moves up$")
	public void the_player_moves_up() throws Throwable {
		gameEngine.keyUp();
	}

	@When("^the player moves down$")
	public void the_player_moves_down() throws Throwable {
		gameEngine.keyDown();
	}

	@Then("^the player is located at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_located_at(int playerX, int playerY) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX - COORDINATE_OFFSET));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY - COORDINATE_OFFSET));
	}

	@Then("^the Enemy located at \\((\\d+), (\\d+)\\) or \\((\\d+),(\\d+)\\) or \\((\\d+),(\\d+)\\)$")
	public void the_Enemy_located_at_or_or(int enemyX1, int enemyY1, int enemyX2, int enemyY2, int enemyX3, int enemyY3)
			throws Throwable {
		boolean atX1, atX2, atY1, atY2, atX3, atY3, checkAtX1Y1, checkAtX2Y2, checkAtX3Y3;
		atX1 = (gameEngine.getEnemyXCoordinate() == (enemyX1 - COORDINATE_OFFSET));
		atX2 = (gameEngine.getEnemyXCoordinate() == (enemyX2 - COORDINATE_OFFSET));
		atX3 = (gameEngine.getEnemyXCoordinate() == (enemyX3 - COORDINATE_OFFSET));
		atY1 = (gameEngine.getEnemyYCoordinate() == (enemyY1 - COORDINATE_OFFSET));
		atY2 = (gameEngine.getEnemyYCoordinate() == (enemyY2 - COORDINATE_OFFSET));
		atY3 = (gameEngine.getEnemyYCoordinate() == (enemyY3 - COORDINATE_OFFSET));
		checkAtX1Y1 = atX1 && atY1;
		checkAtX2Y2 = atX2 && atY2;
		checkAtX3Y3 = atX3 && atY3;
		assertTrue((checkAtX1Y1 || checkAtX2Y2) || checkAtX3Y3);

	}

	@Then("^the Enemy located at \\((\\d+),(\\d+)\\)$")
	public void the_Enemy_located_at(int enemyX1, int enemyY1) throws Throwable {
		boolean atX1, atY1;
		atX1 = (gameEngine.getEnemyXCoordinate() == (enemyX1 - COORDINATE_OFFSET));
		atY1 = (gameEngine.getEnemyYCoordinate() == (enemyY1 - COORDINATE_OFFSET));
		assertTrue(atX1 && atY1);

	}
}
