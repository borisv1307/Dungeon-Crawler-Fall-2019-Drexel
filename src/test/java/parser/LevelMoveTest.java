package parser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import engine.GameEngine;
import wrappers.ReaderWrapper;

public class LevelMoveTest {

	private GameEngine gameEngine;
	private LevelCreator levelCreator;
	private final String FILE_LOCATION_PREFIX = "FILE_LOCATION_PREFIX";
	private ReaderWrapper readerWrapper;
	public final int MIN_LEVEL = 1;
	public final int MAX_LEVEL = 3;

	private LevelMove levelMove;

	@Before
	public void setUp() {
		gameEngine = Mockito.mock(GameEngine.class);
		readerWrapper = Mockito.mock(ReaderWrapper.class);
		levelCreator = new LevelCreator(FILE_LOCATION_PREFIX, readerWrapper);
		levelMove = new LevelMove(MIN_LEVEL, MIN_LEVEL, MAX_LEVEL);
	}

	@Test
	public void set_and_get_level_num() {
		levelMove.setLevelNum(2);
		int actual = levelMove.getLevelNum();
		assertEquals(2, actual);
	}

	@Test
	public void set_and_get_level_min() {
		levelMove.setLevelMin(1);
		int actual = levelMove.getLevelMin();
		assertEquals(1, actual);
	}

	@Test
	public void set_and_get_level_max() {
		levelMove.setLevelMax(3);
		int actual = levelMove.getLevelMax();
		assertEquals(3, actual);
	}

//	@Test
//	public void get_past_level() {
//		levelMove.setLevelNum(1);
//		levelMove.nextLevel(gameEngine);
//		levelMove.pastLevel(gameEngine);
//		int actual = levelMove.getLevelNum();
//		assertEquals(1, actual);
//	}
//
//	@Test
//	public void get_next_level() {
//		levelMove.setLevelNum(2);
//		levelMove.nextLevel(gameEngine);
//		int actual = levelMove.getLevelNum();
//		assertEquals(3, actual);
//	}

	@Test
	public void level_cannot_move_less_than_min() {
		levelMove.setLevelNum(1);
		levelMove.pastLevel(gameEngine);
		int actual = levelMove.getLevelNum();
		assertEquals(1, actual);
	}

	@Test
	public void level_cannot_move_greater_than_max() {
		levelMove.setLevelNum(3);
		levelMove.nextLevel(gameEngine);
		int actual = levelMove.getLevelNum();
		assertEquals(3, actual);
	}
}
