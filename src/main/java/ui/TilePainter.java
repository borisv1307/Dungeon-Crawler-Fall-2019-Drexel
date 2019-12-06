package ui;

import java.awt.Color;
import java.awt.Graphics;

import engine.GameEngine;
import tiles.TileType;
import values.TileColorMap;

public class TilePainter {

	private static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	private static final Color LIGHT_RED = new Color(255, 51, 51);
	private static final Color RED = new Color(255, 0, 0);
	private static final Color DARK_RED = new Color(204, 0, 0);
	private static final Color VERY_DARK_RED = new Color(153, 0, 0);

	static Color[] redHues = new Color[] { VERY_LIGHT_RED, LIGHT_RED, RED, DARK_RED, VERY_DARK_RED };

	int movableID;

	void paintTiles(Graphics graphics, GameEngine game, int tileWidth, int tileHeight) {
		for (int x = 0; x < game.getLevelHorizontalDimension(); x++) {
			for (int y = 0; y < game.getLevelVerticalDimension(); y++) {
				TileType tileType = game.getTileFromCoordinates(x, y);

				if (tileType == TileType.MOVABLE) {
					movableID = game.getMovableID(x, y);
				}

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
		if (movableID == 0) {
			graphics.setColor(VERY_LIGHT_RED);
		} else if (movableID == 1) {
			graphics.setColor(LIGHT_RED);
		} else if (movableID == 2) {
			graphics.setColor(RED);
		} else if (movableID == 3) {
			graphics.setColor(DARK_RED);
		} else if (movableID == 4) {
			graphics.setColor(VERY_DARK_RED);
		}
	}

}
