package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;
import wrappers.SystemWrapper;

public class MovementStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;
	private SystemWrapper systemWrapper;

	@Given("^the level design is:$")
	public void level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
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

	@When("^the player inserts key$")
	public void the_player_inserts_key() throws Throwable {
		gameEngine.insertKey();
	}

	@Then("^the player is located at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_located_at(int playerX, int playerY) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerX - COORDINATE_OFFSET));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerY - COORDINATE_OFFSET));
	}

	@Then("^check for trap$")
	public void check_for_trap() throws Throwable {
		assertThat(gameEngine.isTrapSprung, equalTo(true));
	}

	@Then("^trap has sprung$")
	public void trap_has_sprung() throws Throwable {
		assertThat(gameEngine.isTrapSprung, equalTo(true));
	}

	@Then("^no trap has sprung$")
	public void no_trap_has_sprung() throws Throwable {
		assertThat(gameEngine.isTrapSprung, equalTo(false));
	}

}
