package movement;

import engine.GameEngine;
import tiles.TileType;

public class Movement {

	public TileType setLocation(GameEngine gameEngine, int xCoordinateToSet, int yCoordinateToSet) {

		if (gameEngine.getTileFromCoordinates(xCoordinateToSet, yCoordinateToSet) == TileType.PASSABLE) {

			gameEngine.getPlayer().setLocation(xCoordinateToSet, yCoordinateToSet);

		}

		return gameEngine.getTileFromCoordinates(xCoordinateToSet, yCoordinateToSet);
	}
}
