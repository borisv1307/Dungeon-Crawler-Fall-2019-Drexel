package characters;

import exceptions.LogicError;

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

		throw new LogicError("unhandled character class");
	}
}
