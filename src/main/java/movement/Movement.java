package movement;

import engine.GameEngine;
import tiles.TileType;

public class Movement {

	private GameEngine gameEngine;

	int xCoordinatePlayer;
	int yCoordinatePlayer;

	int xCoordinate;
	int yCoordinate;

	int movableID;

	TileType nextTile;
	TileType tileAfterAllMovable;

	int numberOfMovableTiles;
	int numberOfTilesFromPlayer;

	private static final int ONE = 1;

	enum Direction {
		LEFT, RIGHT, UP, DOWN;
	}

	Direction direction;

	public Movement(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	public void setDirectionOfMovement(Direction direction) {
		this.direction = direction;
	}

	public void keyLeft() {
		setDirectionOfMovement(Direction.LEFT);
		determineMovement();

	}

	public void keyRight() {
		setDirectionOfMovement(Direction.RIGHT);
		determineMovement();
	}

	public void keyUp() {
		setDirectionOfMovement(Direction.UP);
		determineMovement();
	}

	public void keyDown() {
		setDirectionOfMovement(Direction.DOWN);
		determineMovement();
	}

	public void determineMovement() {
		getPlayerCoordinates();
		setCoordinatesToReference(ONE);
		determineNextTile(xCoordinate, yCoordinate);

		if (!isPassable(nextTile)) {
			setPlayerToSamePosition();
		} else if (isMovable(nextTile)) {
			countMovableTiles();
			setCoordinatesToReference(numberOfTilesFromPlayer);
			determineTileAfterAllMovable(xCoordinate, yCoordinate);
			if (isPassable(tileAfterAllMovable)) {
				setCoordinatesToReference(ONE);
				movePlayer(xCoordinate, yCoordinate);
				moveAllMovable();
			}
		} else {
			setCoordinatesToReference(ONE);
			movePlayer(xCoordinate, yCoordinate);
		}
	}

	public void getPlayerCoordinates() {
		xCoordinatePlayer = gameEngine.getPlayerXCoordinate();
		yCoordinatePlayer = gameEngine.getPlayerYCoordinate();
	}

	public void setCoordinatesToReference(int referenceFromPlayerCoordinate) {
		if (direction.equals(Direction.LEFT)) {
			xCoordinate = xCoordinatePlayer - referenceFromPlayerCoordinate;
			yCoordinate = yCoordinatePlayer;
		} else if (direction.equals(Direction.RIGHT)) {
			xCoordinate = xCoordinatePlayer + referenceFromPlayerCoordinate;
			yCoordinate = yCoordinatePlayer;
		} else if (direction.equals(Direction.UP)) {
			xCoordinate = xCoordinatePlayer;
			yCoordinate = yCoordinatePlayer - referenceFromPlayerCoordinate;
		} else if (direction.equals(Direction.DOWN)) {
			xCoordinate = xCoordinatePlayer;
			yCoordinate = yCoordinatePlayer + referenceFromPlayerCoordinate;
		}
	}

	public void determineNextTile(int xCoordinate, int yCoordinate) {
		nextTile = gameEngine.getTileFromCoordinates(xCoordinate, yCoordinate);
	}

	public void setPlayerToSamePosition() {
		gameEngine.setPlayer(xCoordinatePlayer, yCoordinatePlayer);
	}

	public void determineTileAfterAllMovable(int xCoordinate, int yCoordinate) {
		tileAfterAllMovable = gameEngine.getTileFromCoordinates(xCoordinate, yCoordinate);
	}

	public boolean isPassable(TileType nextTile) {
		boolean state = true;
		if (nextTile.equals(TileType.NOT_PASSABLE)) {
			return false;
		}
		return state;
	}

	public boolean isMovable(TileType tile) {
		boolean state = false;
		if (tile.equals(TileType.MOVABLE)) {
			return true;
		}
		return state;
	}

	public void movePlayer(int xCoordinate, int yCoordinate) {
		gameEngine.setPlayer(xCoordinate, yCoordinate);
	}

	public void countMovableTiles() {
		numberOfMovableTiles = 0;
		numberOfTilesFromPlayer = 1;

		while (isMovable(nextTile)) {
			if (isMovable(nextTile)) {
				numberOfMovableTiles++;
				numberOfTilesFromPlayer++;
				setCoordinatesToReference(numberOfTilesFromPlayer);
				determineNextTile(xCoordinate, yCoordinate);
			}
		}
	}

	public void moveAllMovable() {
		while (numberOfMovableTiles > 0) {
			moveEachMovable();
			numberOfMovableTiles--;
		}
	}

	public void moveEachMovable() {
		setCoordinatesToReference(numberOfMovableTiles);

		getMovableID(xCoordinate, yCoordinate);

		if (direction.equals(Direction.LEFT)) {
			setNewMovable(xCoordinate - 1, yCoordinate);
		} else if (direction.equals(Direction.RIGHT)) {
			setNewMovable(xCoordinate + 1, yCoordinate);
		} else if (direction.equals(Direction.UP)) {
			setNewMovable(xCoordinate, yCoordinate - 1);
		} else if (direction.equals(Direction.DOWN)) {
			setNewMovable(xCoordinate, yCoordinate + 1);
		}

		resetMovableTile(xCoordinate, yCoordinate);
	}

	public void getMovableID(int xCoordinate, int yCoordinate) {
		movableID = gameEngine.getMovableID(xCoordinate, yCoordinate);
	}

	public void resetMovableTile(int xCoordinate, int yCoordinate) {
		gameEngine.resetMovableTileToPassable(xCoordinate, yCoordinate);
	}

	public void setNewMovable(int xCoordinate, int yCoordinate) {
		gameEngine.setNewMovableTilePosition(xCoordinate, yCoordinate);
		gameEngine.setNewMovableTileID(xCoordinate, yCoordinate, movableID);
	}
}
