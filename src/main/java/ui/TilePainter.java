package ui;

import java.awt.Graphics;

import engine.GameEngine;
import tiles.TileType;
import values.MovableColorMap;
import values.TileColorMap;

public class TilePainter {
	int movableID;

	void paintTiles(Graphics graphics, GameEngine game, int tileWidth, int tileHeight) {
		for (int x = 0; x < game.getLevelHorizontalDimension(); x++) {
			for (int y = 0; y < game.getLevelVerticalDimension(); y++) {
				TileType tileType = game.getTileFromCoordinates(x, y);
				getMovableID(game, x, y, tileType);
				paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
			}
		}
	}

	void paintPlayer(Graphics graphics, int x, int y, int tileWidth, int tileHeight, TileType tileType) {
		paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
	}

	private void paintTile(Graphics graphics, int tileWidth, int tileHeight, int x, int y, TileType tileType) {
		if (tileType == TileType.MOVABLE) {
			handleMovableTile(graphics, movableID);
		} else {
			handleTile(graphics, tileType);
		}
		graphics.fillRect(x * tileWidth, y * tileHeight, tileWidth, tileHeight);
	}

	private void handleTile(Graphics graphics, TileType tileType) {
		graphics.setColor(TileColorMap.get(tileType));
	}

	private void handleMovableTile(Graphics graphics, int movableID) {
		graphics.setColor(MovableColorMap.get(movableID));
	}

	private void getMovableID(GameEngine game, int x, int y, TileType tileType) {
		if (tileType == TileType.MOVABLE) {
			movableID = game.getMovableID(x, y);
		}
	}

}
