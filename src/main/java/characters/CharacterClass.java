package characters;

public enum CharacterClass {
	WARRIOR, ROGUE, SORCERER;

	public CharacterClass next() {
		switch (this) {
		case WARRIOR:
			return ROGUE;
		case ROGUE:
			return SORCERER;
		case SORCERER:
			return WARRIOR;
		}

		throw new RuntimeException("unhandled character class");
	}
}
