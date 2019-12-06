package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.mockito.Mockito;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.RandomWrapper;
import wrappers.ReaderWrapper;

public class MovementStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;
	RandomWrapper randomWrapper = new RandomWrapper();
	private RandomWrapper randomWrapperMock = Mockito.mock(RandomWrapper.class);

	@Given("^the level design is:$")
	public void level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()),
				randomWrapperMock);
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

	@When("^the enemy tries to move \"([^\"]*)\"$")
	public void the_enemy_tries_to_move(String direction) throws Throwable {
		if (direction.equals("left")) {
			Mockito.when(randomWrapperMock.nextInt(4)).thenReturn(0);
		} else if (direction.equals("right")) {
			Mockito.when(randomWrapperMock.nextInt(4)).thenReturn(1);
		} else if (direction.equals("up")) {
			Mockito.when(randomWrapperMock.nextInt(4)).thenReturn(2);
		} else if (direction.equals("down")) {
			Mockito.when(randomWrapperMock.nextInt(4)).thenReturn(3);
		}
		gameEngine.generateMoveForEnemy();
	}

	@Then("^the Enemy located at \\((\\d+),(\\d+)\\)$")
	public void the_Enemy_located_at(int enemyX1, int enemyY1) throws Throwable {
		int actualX = gameEngine.getEnemyXCoordinate();
		int actualY = gameEngine.getEnemyYCoordinate();
		assertThat(actualX, equalTo(enemyX1 - COORDINATE_OFFSET));
		assertThat(actualY, equalTo(enemyY1 - COORDINATE_OFFSET));
	}

	@Then("^the level is restarted$")
	public void the_level_is_restarted() throws Throwable {
		gameEngine.checkForKill();
		assertFalse((gameEngine.getPlayerXCoordinate() == gameEngine.getEnemyXCoordinate())
				&& (gameEngine.getPlayerYCoordinate() == gameEngine.getEnemyYCoordinate()));
	}

}
