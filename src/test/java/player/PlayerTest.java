package player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import tiles.TileType;

public class PlayerTest {

	Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player();
	}
	
	@Test
	public void clone_player() {
		player = new Player(2, 3);
		player.attemptCollectTile(TileType.TREASURE);
		
		Player actual = new Player(player);
		assertThat(actual.getX(), equalTo(2.0));
		assertThat(actual.getY(), equalTo(3.0));
		assertThat(actual.getCollectedTileInventory(TileType.TREASURE), equalTo((int)1));
	}	
	
	@Test
	public void player_can_collect_tile() {
		boolean actual = player.canCollectTile(TileType.TREASURE);
		assertThat(actual, equalTo(true));
		actual = player.canCollectTile(TileType.PLAYER);
		assertThat(actual, equalTo(false));
		actual = player.canCollectTile(TileType.NOT_PASSABLE);
		assertThat(actual, equalTo(false));
		actual = player.canCollectTile(TileType.PASSABLE);
		assertThat(actual, equalTo(false));
	}
		
	@Test
	public void player_attempt_collect_tile() {
		boolean actual = player.attemptCollectTile(TileType.TREASURE);
		assertThat(actual, equalTo(true));
		actual = player.attemptCollectTile(TileType.PLAYER);
		assertThat(actual, equalTo(false));
		actual = player.attemptCollectTile(TileType.NOT_PASSABLE);
		assertThat(actual, equalTo(false));
		actual = player.attemptCollectTile(TileType.PASSABLE);
		assertThat(actual, equalTo(false));
	}
	
	@Test
	public void player_get_collected_tiles() {
		player.attemptCollectTile(TileType.TREASURE);
		int actual = player.getCollectedTileInventory(TileType.TREASURE);
		assertThat(actual, equalTo(1));
		actual = player.getCollectedTileInventory(TileType.PLAYER);
		assertThat(actual, equalTo(0));
		actual = player.getCollectedTileInventory(TileType.NOT_PASSABLE);
		assertThat(actual, equalTo(0));
		actual = player.getCollectedTileInventory(TileType.PASSABLE);
		assertThat(actual, equalTo(0));
	}

}
