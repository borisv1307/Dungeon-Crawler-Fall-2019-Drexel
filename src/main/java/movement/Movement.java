package movement;

import java.awt.Point;

import engine.GameEngine;
import tiles.TileType;

public class Movement {

	private GameEngine gameEngine;

	int xCoordinatePlayer;
	int yCoordinatePlayer;

	int xCoordinateMovable;
	int yCoordinateMovable;

	Point pointOfPlayer;
	Point pointOfMovable;
	Point pointOfTileAfterMovable;

	TileType nextTile;
	TileType nextTileAfterMovable;

	String direction;

	public Movement(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public void keyLeft() {
		direction = "Left";
		determineMovement(direction);
	}

	public void keyRight() {
		direction = "Right";
		determineMovement(direction);
	}

	public void keyUp() {
		direction = "Up";
		determineMovement(direction);
	}

	public void keyDown() {
		direction = "Down";
		determineMovement(direction);
	}

	public void determineMovement(String direction) {
		getPlayerCoordinates();
		getTileAfterPlayerCoordinates(direction);

		if (!isPassable(nextTile)) {
			setPlayerToSamePosition();
		} else if (isMovable(nextTile)) {
			getMovableCoordinates();
			getTileAfterMovableCoordinates(direction);
			if (!isPassable(nextTileAfterMovable)) {
				setPlayerToSamePosition();
				setMovableToSamePosition();
			} else {
				setPlayerAccordingToDirection(direction);
				setMovableAccordingToDirection(direction);
			}
		} else if (isPassable(nextTile)) {
			setPlayerAccordingToDirection(direction);
		}
	}

	public void getPlayerCoordinates() {
		xCoordinatePlayer = gameEngine.getPlayerXCoordinate();
		yCoordinatePlayer = gameEngine.getPlayerYCoordinate();

		pointOfPlayer = new Point(xCoordinatePlayer, yCoordinatePlayer);
	}

	public void getTileAfterPlayerCoordinates(String direction) {
		if (direction == "Left") {
			nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer - 1, yCoordinatePlayer);
		} else if (direction == "Right") {
			nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer + 1, yCoordinatePlayer);
		} else if (direction == "Up") {
			nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer, yCoordinatePlayer - 1);
		} else if (direction == "Down") {
			nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer, yCoordinatePlayer + 1);
		}
	}

	public void getMovableCoordinates() {
		xCoordinateMovable = gameEngine.getMovableXCoordinate();
		yCoordinateMovable = gameEngine.getMovableYCoordinate();

		pointOfMovable = new Point(xCoordinateMovable, yCoordinateMovable);
	}

	public void getTileAfterMovableCoordinates(String direction) {
		if (direction == "Left") {
			nextTileAfterMovable = gameEngine.getTileFromCoordinates(xCoordinateMovable - 1, yCoordinateMovable);
		} else if (direction == "Right") {
			nextTileAfterMovable = gameEngine.getTileFromCoordinates(xCoordinateMovable + 1, yCoordinateMovable);
		} else if (direction == "Up") {
			nextTileAfterMovable = gameEngine.getTileFromCoordinates(xCoordinateMovable, yCoordinateMovable - 1);
		} else if (direction == "Down") {
			nextTileAfterMovable = gameEngine.getTileFromCoordinates(xCoordinateMovable, yCoordinateMovable + 1);
		}
	}

	public boolean isPassable(TileType nextTile) {
		boolean state = true;
		if (nextTile.equals(TileType.NOT_PASSABLE)) {
			return false;
		}
		return state;
	}

	public boolean isMovable(TileType nextTile) {
		boolean state = false;
		if (nextTile.equals(TileType.MOVABLE)) {
			return true;
		}
		return state;
	}

	public void setPlayerToSamePosition() {
		gameEngine.setPlayer(xCoordinatePlayer, yCoordinatePlayer);
	}

	public void setMovableToSamePosition() {
		gameEngine.setMovable(xCoordinateMovable, yCoordinateMovable);
	}

	public void setPlayerAccordingToDirection(String direction) {
		if (direction == "Left") {
			gameEngine.setPlayer(xCoordinatePlayer - 1, yCoordinatePlayer);
		} else if (direction == "Right") {
			gameEngine.setPlayer(xCoordinatePlayer + 1, yCoordinatePlayer);
		} else if (direction == "Up") {
			gameEngine.setPlayer(xCoordinatePlayer, yCoordinatePlayer - 1);
		} else if (direction == "Down") {
			gameEngine.setPlayer(xCoordinatePlayer, yCoordinatePlayer + 1);
		}
	}

	public void setMovableAccordingToDirection(String direction) {
		if (direction == "Left") {
			gameEngine.setMovable(xCoordinateMovable - 1, yCoordinateMovable);
			gameEngine.setNewMovableTilePosition(xCoordinateMovable - 1, yCoordinateMovable);
			gameEngine.resetMovableTile(xCoordinateMovable, yCoordinateMovable);
		} else if (direction == "Right") {
			gameEngine.setMovable(xCoordinateMovable + 1, yCoordinateMovable);
			gameEngine.setNewMovableTilePosition(xCoordinateMovable + 1, yCoordinateMovable);
			gameEngine.resetMovableTile(xCoordinateMovable, yCoordinateMovable);
		} else if (direction == "Up") {
			gameEngine.setMovable(xCoordinateMovable, yCoordinateMovable - 1);
			gameEngine.setNewMovableTilePosition(xCoordinateMovable, yCoordinateMovable - 1);
			gameEngine.resetMovableTile(xCoordinateMovable, yCoordinateMovable);
		} else if (direction == "Down") {
			gameEngine.setMovable(xCoordinateMovable, yCoordinateMovable + 1);
			gameEngine.setNewMovableTilePosition(xCoordinateMovable, yCoordinateMovable + 1);
			gameEngine.resetMovableTile(xCoordinateMovable, yCoordinateMovable);
		}
	}

}
