package values;

import java.awt.Color;
import java.util.EnumMap;

import tiles.TileType;

public final class TileColorMap {
	private static final EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);

	static {
		TileColorMap.tileColors.put(TileType.PASSABLE, Color.WHITE);
		TileColorMap.tileColors.put(TileType.NOT_PASSABLE, Color.BLACK);
		TileColorMap.tileColors.put(TileType.PLAYER, Color.GREEN);
		TileColorMap.tileColors.put(TileType.PAST_LEVEL, Color.BLUE);
		TileColorMap.tileColors.put(TileType.NEXT_LEVEL, Color.YELLOW);
	}

	public static Color get(TileType key) {
		return TileColorMap.tileColors.get(key);
	}

	private TileColorMap() {
	}
}