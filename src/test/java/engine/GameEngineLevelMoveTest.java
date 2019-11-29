package engine;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import parser.LevelCreator;
import parser.LevelMove;
import tiles.TileType;
import values.TestingTunableParameters;
import wrappers.ReaderWrapper;

public class GameEngineLevelMoveTest {

	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FIVE = 5;
	GameEngine gameEngine;
	LevelMove levelMove;

	@Before
	public void setUp() throws Exception {
		this.levelMove = new LevelMove(ONE, ONE, FIVE);
		LevelCreator levelCreator = new LevelCreator(TestingTunableParameters.FILE_LOCATION_PREFIX,
				new ReaderWrapper());
		this.gameEngine = new GameEngine(levelCreator, this.levelMove);
		this.gameEngine.getLevelCreator().createLevel(this.gameEngine, TWO);
		this.levelMove.setLevelNum(TWO);
	}

	@Test
	public void check_player_can_move_to_next_level_tile() {
		this.gameEngine.getLevelCreator().createLevel(this.gameEngine, TWO);
		this.levelMove.setLevelNum(TWO);
		int coordinateX = this.gameEngine.getPlayerXCoordinate();
		int coordinateY = this.gameEngine.getPlayerYCoordinate();
		TileType attempedLocation = this.gameEngine.getTileFromCoordinates(coordinateX + ONE, coordinateY);
		assertThat(attempedLocation, equalTo(TileType.NEXT_LEVEL));
	}

	@Test
	public void check_player_can_move_to_next_level_tile_and_then_onto_next_level() {
		this.gameEngine.getLevelCreator().createLevel(this.gameEngine, TWO);
		this.levelMove.setLevelNum(TWO);
		this.gameEngine.keyRight();
		int levelNumber = this.gameEngine.getLevelMove().getLevelNum();
		assertThat(levelNumber, equalTo(THREE));
	}

	@Test
	public void check_player_can_move_to_past_level_tile() {
		this.gameEngine.getLevelCreator().createLevel(this.gameEngine, THREE);
		this.levelMove.setLevelNum(THREE);
		int coordinateX = this.gameEngine.getPlayerXCoordinate();
		int coordinateY = this.gameEngine.getPlayerYCoordinate();
		TileType attempedLocation = this.gameEngine.getTileFromCoordinates(coordinateX + ONE, coordinateY);
		assertThat(attempedLocation, equalTo(TileType.PAST_LEVEL));
	}

	@Test
	public void check_player_can_move_to_past_level_tile_and_then_onto_past_level() {
		this.gameEngine.getLevelCreator().createLevel(this.gameEngine, THREE);
		this.levelMove.setLevelNum(THREE);
		this.gameEngine.keyRight();
		int levelNumber = this.gameEngine.getLevelMove().getLevelNum();
		assertThat(levelNumber, equalTo(TWO));
	}
}
