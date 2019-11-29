package parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import engine.GameEngine;
import wrappers.ReaderWrapper;

public class LevelMoveTest {

	private GameEngine gameEngine;
	public int MIN_LEVEL = 1;
	public int MAX_LEVEL = 3;

	private LevelMove levelMove;

	@Test
	public void level_cannot_move_greater_than_max() {
		this.levelMove.setLevelNum(3);
		this.levelMove.nextLevel(this.gameEngine);
		int actual = this.levelMove.getLevelNum();
		Assert.assertEquals(3, actual);
	}

	@Test
	public void level_cannot_move_less_than_min() {
		this.levelMove.setLevelNum(1);
		this.levelMove.pastLevel(this.gameEngine);
		int actual = this.levelMove.getLevelNum();
		Assert.assertEquals(1, actual);
	}

	@Test
	public void set_and_get_level_max() {
		this.levelMove.setLevelMax(3);
		int actual = this.levelMove.getLevelMax();
		Assert.assertEquals(3, actual);
	}

	@Test
	public void set_and_get_level_min() {
		this.levelMove.setLevelMin(1);
		int actual = this.levelMove.getLevelMin();
		Assert.assertEquals(1, actual);
	}

	@Test
	public void set_and_get_level_num() {
		this.levelMove.setLevelNum(2);
		int actual = this.levelMove.getLevelNum();
		Assert.assertEquals(2, actual);
	}

	@Test
	public void set_and_then_get_next_level() {
		this.levelMove.setLevelNum(3);
		this.levelMove.nextLevel(this.gameEngine);
		int actual = this.levelMove.getLevelNum();
		Assert.assertEquals(3, actual);
	}

	@Test
	public void set_and_then_get_past_level() {
		this.levelMove.setLevelNum(1);
		this.levelMove.pastLevel(this.gameEngine);
		int actual = this.levelMove.getLevelNum();
		Assert.assertEquals(1, actual);
	}

	@Before
	public void setUp() {
		this.gameEngine = Mockito.mock(GameEngine.class);
		Mockito.mock(ReaderWrapper.class);
		this.levelMove = new LevelMove(this.MIN_LEVEL, this.MIN_LEVEL, this.MAX_LEVEL);
	}

}
