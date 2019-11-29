package values;

import java.awt.Color;

import org.junit.Assert;
import org.junit.Test;

import tiles.TileType;

public class TileColorMapTest {

	@Test
	public void next_level() {
		Assert.assertSame(Color.YELLOW, TileColorMap.get(TileType.NEXT_LEVEL));
	}

	@Test
	public void not_passable() {
		Assert.assertSame(Color.BLACK, TileColorMap.get(TileType.NOT_PASSABLE));
	}

	@Test
	public void passable() {
		Assert.assertSame(Color.WHITE, TileColorMap.get(TileType.PASSABLE));
	}

	@Test
	public void player() {
		Assert.assertSame(Color.GREEN, TileColorMap.get(TileType.PLAYER));
	}

	@Test
	public void previous_level() {
		Assert.assertSame(Color.BLUE, TileColorMap.get(TileType.PAST_LEVEL));
	}
}
