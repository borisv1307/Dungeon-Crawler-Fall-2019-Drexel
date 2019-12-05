package main;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import engine.GameEngine;
import parser.LevelCreationStepDefHelper;
import parser.LevelCreator;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;
import wrappers.ThreadWrapper;

public class ObstacleMovementDefs extends LevelCreationStepDefHelper {

	private GameEngine gameEngine;

	@When("^the obstacle moves left$")
	public void the_obstacle_moves_left() throws Throwable {
		new ThreadWrapper().sleep(200);
		gameEngine = new GameEngine(
				new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX, new ReaderWrapper()));
		gameEngine.moveObstacleLeft(1);
	}

	@Then("^the obstacle is located at \\((\\d+), (\\d+)\\)$")
	public void the_obstacle_is_located_at(int obstacleXCoordinate, int obstacleYCoordinate) throws Throwable {

		int actualObstacleXCoordinate = gameEngine.getObstacleXCoordinate(1);
		int actualObstacleYCoordinate = gameEngine.getObstacleYCoordinate(1);

		assertEquals(actualObstacleXCoordinate, obstacleXCoordinate - COORDINATE_OFFSET);
		assertEquals(actualObstacleYCoordinate, obstacleYCoordinate - COORDINATE_OFFSET);
	}

}
