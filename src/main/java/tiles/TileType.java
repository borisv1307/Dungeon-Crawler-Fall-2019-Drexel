package tiles;

public enum TileType {

	PASSABLE(' '), NOT_PASSABLE('X'), PLAYER('P'), PAST_LEVEL('R'), NEXT_LEVEL('N');

	static final String INVALID_CHARACTER_PROVIDED_MESSAGE = "Invalid character provided: ";

	public static TileType getTileTypeByChar(final char ch) {
		for (final TileType type : TileType.values()) {
			if (type.asChar == ch) {
				return type;
			}
		}

		throw new IllegalArgumentException(TileType.INVALID_CHARACTER_PROVIDED_MESSAGE + ch);
	}

	private final char asChar;

	private TileType(char asChar) {
		this.asChar = asChar;
	}
}
