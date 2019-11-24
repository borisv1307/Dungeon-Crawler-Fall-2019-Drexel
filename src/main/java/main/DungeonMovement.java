package main;

import tiles.TileType;

public class DungeonMovement {

	public boolean isPassable(TileType tile) {
		switch(tile) {
			case TREASURE:
			case PASSABLE:
			case PLAYER:
				return true;

			default:
				return false;
		}
	}
}
