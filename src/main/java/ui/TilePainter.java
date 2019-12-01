package ui;

import java.awt.Graphics;
import java.awt.Point;

import engine.GameEngine;
import tiles.TileType;
import values.TileColorMap;

public class TilePainter {

	void paintTiles(Graphics graphics, GameEngine game, int tileWidth, int tileHeight) {
		for (int x = 0; x < game.getLevelHorizontalDimension(); x++) {
			for (int y = 0; y < game.getLevelVerticalDimension(); y++) {
				TileType tileType = game.getTileFromCoordinates(x, y);
				paintTile(graphics, tileWidth, tileHeight, x, y, tileType);
			}
		}
	}

	private void paintTile(Graphics graphics, int tileWidth, int tileHeight, int x, int y, TileType tileType) {
		graphics.setColor(TileColorMap.get(tileType));
		Point tileLocation = getTileLocation(tileWidth, tileHeight, x, y);
		graphics.fillRect(tileLocation.x, tileLocation.y, tileWidth, tileHeight);
	}

	public Point getTileLocation(int tileWidth, int tileHeight, int x, int y) {
		return new Point(x * tileWidth, y * tileHeight);
	}

}
