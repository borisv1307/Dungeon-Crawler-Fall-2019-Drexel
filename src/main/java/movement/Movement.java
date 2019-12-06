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
	TileType tileAfterAllMovable;

	String direction;

	int numberOfMovableTilesLeft;
	int numberOfTilesLeftFromPlayer;

	int numberOfMovableTilesRight;
	int numberOfTilesRightFromPlayer;

	int numberOfMovableTilesUp;
	int numberOfTilesUpFromPlayer;

	int numberOfMovableTilesDown;
	int numberOfTilesDownFromPlayer;

	public Movement(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public void keyLeft() {
		getPlayerCoordinates();
		nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer - 1, yCoordinatePlayer);

		if (!isPassable(nextTile)) {
			setPlayerToSamePosition();
		} else if (isMovable(nextTile)) {
			countMovableTilesLeft();
			tileAfterAllMovable = gameEngine.getTileFromCoordinates(xCoordinatePlayer - numberOfTilesLeftFromPlayer,
					yCoordinatePlayer);
			if (isPassable(tileAfterAllMovable)) {
				movePlayerLeft();
				moveAllMovableLeft();
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
			countMovableTilesRight();
			tileAfterAllMovable = gameEngine.getTileFromCoordinates(xCoordinatePlayer + numberOfTilesRightFromPlayer,
					yCoordinatePlayer);
			if (isPassable(tileAfterAllMovable)) {
				movePlayerRight();
				moveAllMovableRight();
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
			countMovableTilesUp();
			tileAfterAllMovable = gameEngine.getTileFromCoordinates(xCoordinatePlayer,
					yCoordinatePlayer - numberOfTilesUpFromPlayer);
			if (isPassable(tileAfterAllMovable)) {
				movePlayerUp();
				moveAllMovableUp();
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
			countMovableTilesDown();
			tileAfterAllMovable = gameEngine.getTileFromCoordinates(xCoordinatePlayer,
					yCoordinatePlayer + numberOfTilesDownFromPlayer);
			if (isPassable(tileAfterAllMovable)) {
				movePlayerDown();
				moveAllMovableDown();
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

	public boolean isMovable(TileType tile) {
		boolean state = false;
		if (tile.equals(TileType.MOVABLE)) {
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

	public void countMovableTilesLeft() {
		numberOfMovableTilesLeft = 0;
		numberOfTilesLeftFromPlayer = 1;

		while (isMovable(nextTile)) {
			if (isMovable(nextTile)) {
				numberOfMovableTilesLeft++;
				numberOfTilesLeftFromPlayer++;
				nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer - numberOfTilesLeftFromPlayer,
						yCoordinatePlayer);
			}
		}
	}

	public void countMovableTilesRight() {
		numberOfMovableTilesRight = 0;
		numberOfTilesRightFromPlayer = 1;

		while (isMovable(nextTile)) {
			if (isMovable(nextTile)) {
				numberOfMovableTilesRight++;
				numberOfTilesRightFromPlayer++;
				nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer + numberOfTilesRightFromPlayer,
						yCoordinatePlayer);
			}
		}
	}

	public void countMovableTilesUp() {
		numberOfMovableTilesUp = 0;
		numberOfTilesUpFromPlayer = 1;

		while (isMovable(nextTile)) {
			if (isMovable(nextTile)) {
				numberOfMovableTilesUp++;
				numberOfTilesUpFromPlayer++;
				nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer,
						yCoordinatePlayer - numberOfTilesUpFromPlayer);
			}
		}
	}

	public void countMovableTilesDown() {
		numberOfMovableTilesDown = 0;
		numberOfTilesDownFromPlayer = 1;

		while (isMovable(nextTile)) {
			if (isMovable(nextTile)) {
				numberOfMovableTilesDown++;
				numberOfTilesDownFromPlayer++;
				nextTile = gameEngine.getTileFromCoordinates(xCoordinatePlayer,
						yCoordinatePlayer + numberOfTilesDownFromPlayer);
			}
		}
	}

	public void moveAllMovableLeft() {
		while (numberOfMovableTilesLeft > 0) {
			moveEachMovableLeft(numberOfMovableTilesLeft);
			numberOfMovableTilesLeft--;
		}
	}

	public void moveAllMovableRight() {
		while (numberOfMovableTilesRight > 0) {
			moveEachMovableRight(numberOfMovableTilesRight);
			numberOfMovableTilesRight--;
		}
	}

	public void moveAllMovableUp() {
		while (numberOfMovableTilesUp > 0) {
			moveEachMovableUp(numberOfMovableTilesUp);
			numberOfMovableTilesUp--;
		}
	}

	public void moveAllMovableDown() {
		while (numberOfMovableTilesDown > 0) {
			moveEachMovableDown(numberOfMovableTilesDown);
			numberOfMovableTilesDown--;
		}
	}

	public void moveEachMovableLeft(int numberOfTilesFromPlayer) {
		gameEngine.setNewMovableTilePosition(xCoordinatePlayer - (numberOfTilesFromPlayer + 1), yCoordinatePlayer);
		gameEngine.resetMovableTileToPassable(xCoordinatePlayer - numberOfTilesFromPlayer, yCoordinatePlayer);
	}

	public void moveEachMovableRight(int numberOfTilesFromPlayer) {
		gameEngine.setNewMovableTilePosition(xCoordinatePlayer + (numberOfTilesFromPlayer + 1), yCoordinatePlayer);
		gameEngine.resetMovableTileToPassable(xCoordinatePlayer + numberOfTilesFromPlayer, yCoordinatePlayer);
	}

	public void moveEachMovableUp(int numberOfTilesFromPlayer) {
		gameEngine.setNewMovableTilePosition(xCoordinatePlayer, yCoordinatePlayer - (numberOfTilesFromPlayer + 1));
		gameEngine.resetMovableTileToPassable(xCoordinatePlayer, yCoordinatePlayer - numberOfTilesFromPlayer);
	}

	public void moveEachMovableDown(int numberOfTilesFromPlayer) {
		gameEngine.setNewMovableTilePosition(xCoordinatePlayer, yCoordinatePlayer + (numberOfTilesFromPlayer + 1));
		gameEngine.resetMovableTileToPassable(xCoordinatePlayer, yCoordinatePlayer + numberOfTilesFromPlayer);
	}

}
