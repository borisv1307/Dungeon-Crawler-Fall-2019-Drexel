package engine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import parser.LevelCreator;
import tiles.TileType;

public class LevelManagerTest {
	LevelManager levelManager;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	GameEngine gameEngine;

	@Before
	public void setUp() throws Exception {
		LevelCreator levelCreator = Mockito.mock(LevelCreator.class);
		gameEngine = new GameEngine(levelCreator);
		levelCreator.createLevel(gameEngine, ONE);
		this.levelManager = new LevelManager(this.gameEngine, ONE, levelCreator);
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		tileType = TileType.EXIT;
		gameEngine.addTile(ONE, ONE, tileType);
	}

	@Test
	public void go_to_next_level_when_call_goNextLevel() {
		levelManager.goNextLevel();
		assertEquals(2, this.levelManager.getCurrentLevel());
	}

	@Test
	public void stop_when_beyond_level() {
		levelManager.goNextLevel();
		levelManager.goNextLevel();
		assertEquals(2, this.levelManager.getCurrentLevel());
	}
}
