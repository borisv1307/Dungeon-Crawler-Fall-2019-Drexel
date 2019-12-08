package values;

import java.awt.Color;
import java.util.EnumMap;

import tiles.TileType;

public final class TileColorMap {

	private static final EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

	static {
		tileColors.put(TileType.PASSABLE, Color.WHITE);
		tileColors.put(TileType.NOT_PASSABLE, Color.BLACK);
		tileColors.put(TileType.PLAYER, Color.GREEN);
		tileColors.put(TileType.FOOD, Color.YELLOW);
	}

	public void changeColor(int stage) {
		switch (stage) {
		case 1:
			tileColors.put(TileType.PLAYER, Color.RED);
			break;
		case 2:
			tileColors.put(TileType.PLAYER, Color.BLUE);
			break;
		case 3:
			tileColors.put(TileType.PLAYER, Color.CYAN);
			break;
		case 4:
			tileColors.put(TileType.PLAYER, Color.GRAY);
			break;
		case 5:
			tileColors.put(TileType.PLAYER, Color.ORANGE);
			break;
		case 6:
			tileColors.put(TileType.PLAYER, Color.MAGENTA);
			break;
		case 7:
			tileColors.put(TileType.PLAYER, Color.GREEN);
			break;
		default:
			break;
		}

	}

	public static Color get(TileType key) {
		return tileColors.get(key);
	}
}