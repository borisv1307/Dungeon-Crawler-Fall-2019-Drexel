package main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import tiles.TileType;

public class DungeonMovementTest {

	private DungeonMovement dungeonMovement;

	@Before
	public void setUp() {
		dungeonMovement = new DungeonMovement();
	}
	
	@Test
	public void passable_types() {
		assertThat(dungeonMovement.isPassable(TileType.TREASURE), equalTo(true));
		assertThat(dungeonMovement.isPassable(TileType.PASSABLE), equalTo(true));
		assertThat(dungeonMovement.isPassable(TileType.PLAYER), equalTo(true));
	}
	
	@Test
	public void not_passable_types() {
		assertThat(dungeonMovement.isPassable(TileType.NOT_PASSABLE), equalTo(false));
	}
}
