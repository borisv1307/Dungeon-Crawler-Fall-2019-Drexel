package engine;

import parser.LevelCreator;

public class LevelManager {
	private GameEngine gameEngine;
	private int currentLevel;
	private LevelCreator levelCreator;
	LevelManager(GameEngine gameEngine, int level,LevelCreator levelCreator){
		this.gameEngine = gameEngine;
		this.currentLevel = level;
		this.levelCreator = levelCreator;
	}
	public void goNextLevel() {
		// TODO Auto-generated method stub
		currentLevel ++;
		levelCreator.createLevel(this.gameEngine, this.currentLevel);
	}
	
	public int getCurrentLevel() {
		return this.currentLevel;
	}

}
