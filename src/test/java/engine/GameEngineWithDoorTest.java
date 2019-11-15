package engine;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import main.ObjectFactory;
import parser.LevelCreator;
import tiles.TileType;

public class GameEngineWithDoorTest {
	GameEngineWithDoor gameEngine;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	
	@Before
	public void setUp() throws Exception {
		
		gameEngine = ObjectFactory.getGameEngineWithDoor();
		TileType tileType = TileType.PLAYER;
		gameEngine.addTile(ZERO, ONE, tileType);
		tileType = TileType.EXIT;
		gameEngine.addTile(ONE, ONE, tileType);
	}
	
	@Test
	public void go_to_next_level_when_in_the_door() {
		gameEngine.keyRight();
		assertEquals(2,this.gameEngine.getCurrentLevel());
	}
}
