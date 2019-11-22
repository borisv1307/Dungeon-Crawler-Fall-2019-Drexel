package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.awt.event.WindowEvent;
import java.util.List;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.StepDefAnnotation;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import parser.LevelCreatorITHelper;
import tiles.TileType;
import values.TestingTunableParameters;
import wrappers.RandomWrapper;
import wrappers.ReaderWrapper;

@StepDefAnnotation
public class TreasurePlacementStepDefs extends LevelCreatorITHelper {

	private static final int ONE = 1;
	protected static final int COORDINATE_OFFSET = ONE;
	
	private GameEngine gameEngine;
	private RandomWrapper randomWrapper;

	@Given("^the treasure design is:$")
	public void level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		randomWrapper = new RandomWrapper();
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()), 
				randomWrapper);
	}
	
	@When("^the game adds (\\d+) treasure$")
	public void the_game_adds_treasure(int numOfTreasureTilesToAdd) throws Throwable {
		TileType tileType = TileType.TREASURE;
		gameEngine.addTileAtRandomAvailablePoint(tileType);
	}

	@Then("^the treasure count is (\\d+)$")
	public void the_treasure_count_is(int expectedTreasureCount) throws Throwable {
		int actual = gameEngine.getEmptyTileCount();
		assertThat(actual, equalTo(expectedTreasureCount));		
	}
	
	@Then("^the treasure is located at \\((\\d+), (\\d+)\\)$")
	public void the_player_is_located_at(int treasureX, int treasureY) throws Throwable {
		TileType actual = gameEngine.getTileFromCoordinates(treasureX - COORDINATE_OFFSET, treasureY - COORDINATE_OFFSET);
		assertThat(actual, equalTo(TileType.TREASURE));
	}
	
}
