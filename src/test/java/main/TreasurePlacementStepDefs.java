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
import wrappers.ReaderWrapper;

import ui.GameFrame;

@StepDefAnnotation
public class TreasurePlacementStepDefs extends LevelCreatorITHelper {

	private static final int ZERO = 0;
	private static final int ONE = 1;
	
	private GameEngine gameEngine;

	@Given("^the treasure design is:$")
	public void level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
	}
	
	@When("^the game adds (\\d+) treasure$")
	public void the_game_adds_treasure(int numOfTreasureTilesToAdd) throws Throwable {
		TileType tileType = TileType.TREASURE;
		gameEngine.addTileAtRandomAvailablePoint(tileType);
	}

	@Then("^the treasure count is (\\d+)$")
	public void the_treasure_count_is(int expectedTreasureCount) throws Throwable {
		int actual = gameEngine.getTileCount(TileType.TREASURE);
		assertThat(actual, equalTo(expectedTreasureCount));		
	}
	
}
