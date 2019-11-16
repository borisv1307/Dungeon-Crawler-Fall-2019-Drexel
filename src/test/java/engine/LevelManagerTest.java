package engine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.ObjectFactory;
import parser.LevelCreator;
import tiles.TileType;

public class LevelManagerTest {
	LevelManager levelManager;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	GameEngine gameEngine;
	@Before
	public void setUp() throws Exception {
		
		gameEngine = ObjectFactory.getDefaultGameEngine();
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		tileType = TileType.EXIT;
		gameEngine.addTile(ONE, ONE, tileType);
		this.levelManager = new LevelManager(gameEngine,ONE);
	}
	
	
	@Test
	public void go_to_next_level_when_call_goNextLevel() {
		levelManager.goNextLevel();
		assertEquals(2,this.levelManager.getCurrentLevel());
	}
}
