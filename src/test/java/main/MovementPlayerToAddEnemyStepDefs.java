package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.mockito.Mockito;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import tiles.TileType;
import ui.GameFrame;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class MovementPlayerToAddEnemyStepDefs extends LevelCreationStepDefHelper {
	private GameEngine gameEngine;
	private GameFrame gameFrame;

	@Given("^the level design for adding enemy:$")
	public void the_level_design_for_adding_enemy(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameFrame = Mockito.mock(GameFrame.class);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), gameFrame);
	}

	@When("^the player moves left two times$")
	public void the_player_moves_left_two_times() throws Throwable {
		gameEngine.keyLeft();
		gameEngine.keyLeft();
	}

	@When("^the player moves right two times$")
	public void the_player_moves_right_two_times() throws Throwable {
		gameEngine.keyRight();
		gameEngine.keyRight();
	}

	@When("^the player moves up two times$")
	public void the_player_moves_up_two_times() throws Throwable {
		gameEngine.keyUp();
		gameEngine.keyUp();
	}

	@When("^the player moves down two times$")
	public void the_player_moves_down_two_times() throws Throwable {
		gameEngine.keyDown();
		gameEngine.keyDown();
	}

	@Then("^the enemy turned into passable at \\((\\d+), (\\d+)\\)$")
	public void the_enemy_turned_into_passable_at(int x, int y) throws Throwable {
		assertThat(gameEngine.getTileFromCoordinates(x, y), equalTo(TileType.PASSABLE));
	}

}
