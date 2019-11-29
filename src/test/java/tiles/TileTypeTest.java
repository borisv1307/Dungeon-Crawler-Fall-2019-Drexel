package tiles;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class TileTypeTest {

	private static final char INVALID_CHAR = 'Z';
	private static final char VALID_CHAR = ' ';
	private static final char PAST_CHAR = 'R';
	private static final char NEXT_CHAR = 'N';

	@Test
	public void get_tile_type_by_char_invalid_char() {
		try {
			TileType.getTileTypeByChar(TileTypeTest.INVALID_CHAR);
		} catch (final IllegalArgumentException exception) {
			Assert.assertEquals(exception.getMessage(), TileType.INVALID_CHARACTER_PROVIDED_MESSAGE + "Z");
		}
	}

	@Test
	public void get_tile_type_by_char_next_char() {
		final TileType actual = TileType.getTileTypeByChar(TileTypeTest.NEXT_CHAR);
		Assert.assertEquals(TileType.NEXT_LEVEL, actual);
	}

	@Test
	public void get_tile_type_by_char_previous_char() {
		final TileType actual = TileType.getTileTypeByChar(TileTypeTest.PAST_CHAR);
		Assert.assertEquals(TileType.PAST_LEVEL, actual);
	}

	@Test
	public void get_tile_type_by_char_valid_char() {
		final TileType actual = TileType.getTileTypeByChar(TileTypeTest.VALID_CHAR);
		Assert.assertEquals(TileType.PASSABLE, actual);
	}

	@Test
	public void value_of() {
		MatcherAssert.assertThat(TileType.valueOf(TileType.PASSABLE.name()), Matchers.equalTo(TileType.PASSABLE));
	}
}
