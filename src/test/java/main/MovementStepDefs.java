package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.awt.Color;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import tiles.TileType;
import values.TestingTunableParameters;
import values.TileColorMap;
import wrappers.ReaderWrapper;

public class MovementStepDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;
	private TileColorMap tileColorMap;

	@Then("^the player color will be changed at (\\d+)$")
	public void the_player_color_will_be_changed_at(int arg1) throws Throwable {
		gameEngine.increaseLevels(0);
		assertSame(Color.RED, TileColorMap.get(TileType.PLAYER));
		gameEngine.increaseLevels(0);
		assertSame(Color.BLUE, TileColorMap.get(TileType.PLAYER));
		gameEngine.increaseLevels(0);
		assertSame(Color.CYAN, TileColorMap.get(TileType.PLAYER));
		gameEngine.increaseLevels(0);
		assertSame(Color.GRAY, TileColorMap.get(TileType.PLAYER));
		gameEngine.increaseLevels(0);
		assertSame(Color.ORANGE, TileColorMap.get(TileType.PLAYER));
		gameEngine.increaseLevels(0);
		assertSame(Color.MAGENTA, TileColorMap.get(TileType.PLAYER));
		gameEngine.increaseLevels(0);
		assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
	}

	@Given("^the level design is:$")
	public void level_is(List<String> levelStrings) throws Throwable {
		writeLevelFile(levelStrings);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		tileColorMap = new TileColorMap();
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

	@Then("^the player will be moved to location \\((\\d+), (\\d+)\\)$")
	public void the_player_will_be_moved_to_location(int newcordinateX, int newcordinateY) throws Throwable {
		assertThat(gameEngine.getFoodXCoordinate(), equalTo(newcordinateX));
		assertThat(gameEngine.getFoodYCoordinate(), equalTo(newcordinateY));
	}

	@Then("^food will be disappered from \\((\\d+),(\\d+)\\)$")
	public void food_will_be_disappered_from(int foodXCoordinate, int foodYCoordinate) throws Throwable {
		assertThat(gameEngine.getTileFromCoordinates(foodXCoordinate, foodYCoordinate), equalTo(TileType.FOOD));
	}

	@When("^the player reaches \\((\\d+),(\\d+)\\)  of level (\\d+)$")
	public void the_player_reaches_of_level(int foodXCoordinate, int foodYCoordinate, int currentLevel)
			throws Throwable {
		assertThat(gameEngine.getFoodXCoordinate(), equalTo(foodXCoordinate));
		assertThat(gameEngine.getFoodYCoordinate(), equalTo(foodYCoordinate));
		assertEquals(currentLevel, gameEngine.getLevel());
	}

	@Then("^the player moves to level (\\d+)$")
	public void the_player_moves_to_level(int currentLevel) throws Throwable {
		assertEquals(currentLevel, gameEngine.getLevel() + 1);
	}

//	@Then("^the player color will be RED at (\\d+)$")
//	public void the_player_color_will_be_RED_at(int level) throws Throwable {
//		gameEngine.increaseLevels(0);
//		assertSame(Color.RED, TileColorMap.get(TileType.PLAYER));
//	}

	@When("^the player moves right of level (\\d+) the level gets restarted$")
	public void the_player_moves_right_of_level_the_level_gets_restarted(int currentLevel) throws Throwable {
		gameEngine.keyRight();
		assertEquals(currentLevel, gameEngine.getLevel());
	}

	@Then("^the player will be located at \\((\\d+), (\\d+)\\)$")
	public void the_player_will_be_located_at(int playerXCoordinate, int playerYCoordinate) throws Throwable {
		assertThat(gameEngine.getPlayerXCoordinate(), equalTo(playerXCoordinate));
		assertThat(gameEngine.getPlayerYCoordinate(), equalTo(playerYCoordinate));
	}

	@Then("^play level will be (\\d+)$")
	public void play_level_will_be(int currentLevel) throws Throwable {
		assertEquals(currentLevel, gameEngine.getLevel());
	}
}
