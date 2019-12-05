package values;

import static org.junit.Assert.assertSame;

import java.awt.Color;

import org.junit.Test;

import tiles.TileType;

public class TileColorMapTest {

	@Test
	public void passable() {
		assertSame(Color.WHITE, TileColorMap.get(TileType.PASSABLE));
	}

	@Test
	public void not_passable() {
		assertSame(Color.BLACK, TileColorMap.get(TileType.NOT_PASSABLE));
	}

	@Test
	public void player() {
		assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
	}

	@Test
	public void obstacle() {
		assertSame(Color.RED, TileColorMap.get(TileType.OBSTACLE));
	}

	@Test
	public void wall() {
		assertSame(Color.GRAY, TileColorMap.get(TileType.WALL));
	}

	@Test
	public void exit() {
		assertSame(Color.YELLOW, TileColorMap.get(TileType.EXIT));
	}

}
