package tiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileTypeTest {

	private static final char INVALID_CHAR = 'Z';
	private static final char VALID_CHAR = ' ';
	private static final char INVALID_INLET_CHAR = 'C';
	private static final char VALID_INLET_CHAR = 'I';
	private static final char INVALID_OUTLET_CHAR = 'D';
	private static final char VALID_OUTLET_CHAR = 'O';

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
	public void get_tile_type_by_char_valid_Inlet_char() {
		TileType actual = TileType.getTileTypeByChar(VALID_INLET_CHAR);
		assertEquals(TileType.INLET, actual);
	}

	@Test
	public void get_tile_type_by_char_invalid_Inlet_char() {
		try {
			TileType.getTileTypeByChar(INVALID_INLET_CHAR);
		} catch (IllegalArgumentException exception) {
			assertEquals(exception.getMessage(), TileType.INVALID_CHARACTER_PROVIDED_MESSAGE + "C");
		}
	}

	@Test
	public void get_tile_type_by_char_valid_outlet_char() {
		TileType actual = TileType.getTileTypeByChar(VALID_OUTLET_CHAR);
		assertEquals(TileType.OUTLET, actual);
	}

	@Test
	public void get_tile_type_by_char_invalid_outlet_char() {
		try {
			TileType.getTileTypeByChar(INVALID_OUTLET_CHAR);
		} catch (IllegalArgumentException exception) {
			assertEquals(exception.getMessage(), TileType.INVALID_CHARACTER_PROVIDED_MESSAGE + "D");
		}
	}
}
