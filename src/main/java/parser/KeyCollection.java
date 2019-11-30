package parser;

import java.awt.Color;
import java.util.EnumMap;

import tiles.TileType;

public class KeyCollection {

	public static void change_key_color() {
		final EnumMap<TileType, Color> tileColors = new EnumMap<>(TileType.class);
		tileColors.put(TileType.KEY, Color.WHITE);

	}

}
