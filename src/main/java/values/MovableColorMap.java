package values;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

public class MovableColorMap {
	private static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	private static final Color LIGHT_RED = new Color(255, 51, 51);
	private static final Color RED = new Color(255, 0, 0);
	private static final Color DARK_RED = new Color(204, 0, 0);
	private static final Color VERY_DARK_RED = new Color(153, 0, 0);

	private final static Map<Integer, Color> movableColors = new HashMap<>();

	static {
		movableColors.put(0, VERY_LIGHT_RED);
		movableColors.put(1, LIGHT_RED);
		movableColors.put(2, RED);
		movableColors.put(3, DARK_RED);
		movableColors.put(4, VERY_DARK_RED);
	}

	public static Color get(Integer id) {
		return movableColors.get(id);
	}
}
