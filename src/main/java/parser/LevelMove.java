package parser;

import engine.GameEngine;

public class LevelMove {

	private int levelNum;
	private int levelMin;
	private int levelMax;

	public LevelMove(int levelNum, int levelMin, int levelMax) {
		super();
		this.levelNum = levelNum;
		this.levelMin = levelMin;
		this.levelMax = levelMax;
	}

	public int getLevelMax() {
		return this.levelMax;
	}

	public int getLevelMin() {
		return this.levelMin;
	}

	public int getLevelNum() {
		return this.levelNum;
	}

	public void nextLevel(GameEngine gameEngine) {
		if (this.levelNum < this.levelMax) {
			this.levelNum = this.levelNum + 1;
			gameEngine.getLevelCreator().createLevel(gameEngine, this.levelNum);
		}
	}

	public void pastLevel(GameEngine gameEngine) {
		if (this.levelNum > this.levelMin) {
			this.levelNum = this.levelNum - 1;
			gameEngine.getLevelCreator().createLevel(gameEngine, this.levelNum);
		}
	}

	public void setLevelMax(int level) {
		this.levelMax = level;
	}

	public void setLevelMin(int level) {
		this.levelMin = level;
	}

	public void setLevelNum(int level) {
		this.levelNum = level;
	}
}
