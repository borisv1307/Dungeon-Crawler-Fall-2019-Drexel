package parser;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import tiles.TileType;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class CollectCoinsStepDef extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;

	@Given("^the level of game is:$")
	public void the_level_of_game_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}

	@When("^the player collects coins available in grid$")
	public void the_player_moves_on_top_of_key() throws Throwable {
		gameEngine.keyUp();

	}

	@Then("^the player is located on location \\((\\d+), (\\d+)\\) collects coins$")
	public void the_player_is_located_on_location_collects_coins(int coinXloc, int coinYloc) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(coinXloc - COORDINATE_OFFSET));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(coinYloc - COORDINATE_OFFSET));
	}

	@Then("^coin dissappears from location \\((\\d+), (\\d+)\\)$")
	public void coin_dissappears_from_location(int coinXloc, int coinYloc) throws Throwable {
		assertThat(gameEngine.getTileFromCoordinates(coinXloc - COORDINATE_OFFSET, coinYloc - COORDINATE_OFFSET),
				equalTo(TileType.PASSABLE));

	}

}
