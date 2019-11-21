package movement;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import tiles.TileType;

public class Movement {

	private Map<Point, TileType> tiles = new HashMap<>();
	private Point player;

	private int level = 0;

	public int setLocation(Point player, Map<Point, TileType> tiles, int x, int y, int level) {

		this.player = player;
		this.tiles = tiles;
		if (tiles.get(new Point(x, y)) != TileType.NOT_PASSABLE) {
			this.player.setLocation(x, y);
			this.level = level;
		}

		if (tiles.get(new Point(x, y)) == TileType.WIN_POINT) {

			this.level = level++;

		}
		return level;
	}

	public boolean checkifLastLevl(int level) {

		if (level == 4)

			return true;

		else
			return false;

	}
}
