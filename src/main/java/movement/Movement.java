package movement;

import java.awt.Point;

import engine.GameEngine;
import tiles.TileType;

public class Movement {

	private GameEngine gameEngine;

	int xCoordinatePlayer;
	int yCoordinatePlayer;

	Point pointOfPlayer;
	Point pointOfMovable;
	Point pointOfTileAfterMovable;

	TileType nextTile;
	TileType nextTileToTheLeft;

	String direction;

	public Movement(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public void keyLeft() {
		getPlayerCoordinates();
		nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer - 1, yCoordinatePlayer);

		if (!isPassable(nextTile)) {
			setPlayerToSamePosition();
		} else if (isMovable(nextTile)) {
			if (isPassable(gameEngine.getTileFromCoordinates(xCoordinatePlayer - 2, yCoordinatePlayer))) {
				movePlayerLeft();
				gameEngine.setNewMovableTilePosition(xCoordinatePlayer - 2, yCoordinatePlayer);
				gameEngine.resetMovableTileToPassable(xCoordinatePlayer - 1, yCoordinatePlayer);
			}
		} else {
			movePlayerLeft();
		}
	}

	public void keyRight() {
		getPlayerCoordinates();
		nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer + 1, yCoordinatePlayer);

		if (!isPassable(nextTile)) {
			setPlayerToSamePosition();
		} else if (isMovable(nextTile)) {
			if (isPassable(gameEngine.getTileFromCoordinates(xCoordinatePlayer + 2, yCoordinatePlayer))) {
				movePlayerRight();
				gameEngine.setNewMovableTilePosition(xCoordinatePlayer + 2, yCoordinatePlayer);
				gameEngine.resetMovableTileToPassable(xCoordinatePlayer + 1, yCoordinatePlayer);
			}
		} else {
			movePlayerRight();
		}
	}

	public void keyUp() {
		getPlayerCoordinates();
		nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer, yCoordinatePlayer - 1);

		if (!isPassable(nextTile)) {
			setPlayerToSamePosition();
		} else if (isMovable(nextTile)) {
			if (isPassable(gameEngine.getTileFromCoordinates(xCoordinatePlayer, yCoordinatePlayer - 2))) {
				movePlayerUp();
				gameEngine.setNewMovableTilePosition(xCoordinatePlayer, yCoordinatePlayer - 2);
				gameEngine.resetMovableTileToPassable(xCoordinatePlayer, yCoordinatePlayer - 1);
			}
		} else {
			movePlayerUp();
		}
	}

	public void keyDown() {
		getPlayerCoordinates();
		nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer, yCoordinatePlayer + 1);

		if (!isPassable(nextTile)) {
			setPlayerToSamePosition();
		} else if (isMovable(nextTile)) {
			if (isPassable(gameEngine.getTileFromCoordinates(xCoordinatePlayer, yCoordinatePlayer + 2))) {
				movePlayerDown();
				gameEngine.setNewMovableTilePosition(xCoordinatePlayer, yCoordinatePlayer + 2);
				gameEngine.resetMovableTileToPassable(xCoordinatePlayer, yCoordinatePlayer + 1);
			}
		} else {
			movePlayerDown();
		}
	}

	public void getPlayerCoordinates() {
		xCoordinatePlayer = gameEngine.getPlayerXCoordinate();
		yCoordinatePlayer = gameEngine.getPlayerYCoordinate();
	}

	public boolean isPassable(TileType nextTile) {
		boolean state = true;
		if (nextTile.equals(TileType.NOT_PASSABLE)) {
			return false;
		}
		return state;
	}

	public void setPlayerToSamePosition() {
		gameEngine.setPlayer(xCoordinatePlayer, yCoordinatePlayer);
	}

	public boolean isMovable(TileType nextTile) {
		boolean state = false;
		if (nextTile.equals(TileType.MOVABLE)) {
			return true;
		}
		return state;
	}

	public void movePlayerLeft() {
		gameEngine.setPlayer(xCoordinatePlayer - 1, yCoordinatePlayer);
	}

	public void movePlayerRight() {
		gameEngine.setPlayer(xCoordinatePlayer + 1, yCoordinatePlayer);
	}

	public void movePlayerUp() {
		gameEngine.setPlayer(xCoordinatePlayer, yCoordinatePlayer - 1);
	}

	private void movePlayerDown() {
		gameEngine.setPlayer(xCoordinatePlayer, yCoordinatePlayer + 1);
	}
}
