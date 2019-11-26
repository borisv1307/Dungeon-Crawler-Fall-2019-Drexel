package parser;

public class LevelHandler {

	public boolean checkIfLastLevel(int level) {

		return level >= 4;
	}

	public int getNewLevel(int level) {

		return ++level;
	}
}
