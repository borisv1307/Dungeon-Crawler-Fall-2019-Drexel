package engine;

import parser.LevelCreator;

public class LevelManager {
	private GameEngine gameEngine;
	private int currentLevel;
	private LevelCreator levelCreator;
	private final int maxLevel;

	LevelManager(GameEngine gameEngine, int level, LevelCreator levelCreator) {
		this.maxLevel = 2;
		this.gameEngine = gameEngine;
		this.currentLevel = level;
		this.levelCreator = levelCreator;
	}

	public void goNextLevel() {
		// TODO Auto-generated method stub
		if (this.currentLevel < this.maxLevel) {
			currentLevel++;
			levelCreator.createLevel(this.gameEngine, this.currentLevel);
		}
	}

	public int getCurrentLevel() {
		return this.currentLevel;
	}

}
