package tiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileTypeTest {

	private static final char INVALID_CHAR = 'Z';
	private static final char VALID_CHAR = ' ';
	private static final char PASSABLE_CHAR = 'T';

	@Test
	public void value_of() {
		assertThat(TileType.valueOf(TileType.PASSABLE.name()), equalTo(TileType.PASSABLE));
	}

	@Test
	public void get_tile_type_by_char_valid_char() {
		TileType actual = TileType.getTileTypeByChar(VALID_CHAR);
		assertEquals(TileType.PASSABLE, actual);
	}

	@Test
	public void get_tile_type_by_char_invalid_char() {
		try {
			TileType.getTileTypeByChar(INVALID_CHAR);
		} catch (IllegalArgumentException exception) {
			assertEquals(exception.getMessage(), TileType.INVALID_CHARACTER_PROVIDED_MESSAGE + "Z");
		}
	}
	
	@Test
	public void passable_types() {
		assertThat(TileType.isPassable(TileType.TREASURE), equalTo(true));
		assertThat(TileType.isPassable(TileType.PASSABLE), equalTo(true));
		assertThat(TileType.isPassable(TileType.PLAYER), equalTo(true));
	}
	
	@Test
	public void not_passable_types() {
		assertThat(TileType.isPassable(TileType.NOT_PASSABLE), equalTo(false));
	}
}
