package engine;

import parser.LevelCreator;
import tiles.TileType;

public class GameEngineWithDoor extends GameEngine{

	private int currentLevel;
	public GameEngineWithDoor(LevelCreator levelCreator) {
		super(levelCreator);
		// TODO Auto-generated constructor stub
		currentLevel = super.level;
	}
	
	protected void setPlayer(int x, int y) {
		super.setPlayer(x, y);
		if(super.getTileFromCoordinates(x, y) == TileType.EXIT)
			goNextLevel();
	}

	private void goNextLevel() {
		// TODO Auto-generated method stub
		currentLevel ++;
		super.levelCreator.createLevel(this, this.currentLevel);
	}
	
	public int getCurrentLevel() {
		return this.currentLevel;
	}
	
}
