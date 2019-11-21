package parser;

public class LevelHandler {

	public boolean checkIfLastLevel(int level) {

		if (level >= 4)
			return true;

		else
			return false;
	}

	public int getNewLevel(int level) {

		return ++level;
	}
}
