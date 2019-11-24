package parser;

import engine.GameEngine;

public class LevelMove {

	private int levelNum;
	private int levelMin;
	private int levelMax;
	// private LevelCreator levelCreator;

	public LevelMove(int levelNum, int levelMin, int levelMax) {
		super();
		this.levelNum = levelNum;
		this.levelMin = levelMin;
		this.levelMax = levelMax;
	}

	public int getLevelNum() {
		return levelNum;
	}

	public void setLevelNum(int level) {
		this.levelNum = level;
	}

	public int getLevelMin() {
		return levelMin;
	}

	public void setLevelMin(int level) {
		this.levelMin = level;
	}

	public int getLevelMax() {
		return levelMax;
	}

	public void setLevelMax(int level) {
		this.levelMax = level;
	}

	public void pastLevel(GameEngine gameEngine) {
		if (levelNum > levelMin) {
			this.levelNum = this.levelNum - 1;
			gameEngine.getLevelCreator().createLevel(gameEngine, this.levelNum);
		}
	}

	public void nextLevel(GameEngine gameEngine) {
		if (levelNum < levelMax) {
			this.levelNum = this.levelNum + 1;
			gameEngine.getLevelCreator().createLevel(gameEngine, this.levelNum);
		}
	}
}
