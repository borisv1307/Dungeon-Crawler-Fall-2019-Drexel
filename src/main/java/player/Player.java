package player;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import tiles.TileType;

public class Player extends Point {
	
	public final Map<TileType, Integer> collectedTiles = new HashMap<>();
	
    public Player() {
        super();
    }
    
    public Player(Player player) {
        super(player);        
        collectedTiles.putAll(player.collectedTiles);
    }
	
    public Player(int x, int y) {
        super(x, y);
    }
    
	public boolean canCollectTile(TileType tile) {
		switch(tile) {
			case TREASURE:
				return true;
	
			case PASSABLE:
			case PLAYER:
				return false;
				
			default:
				return false;
		}
	}

	public boolean attemptCollectTile(TileType tile) {
		if(canCollectTile(tile)) {
			Integer count = collectedTiles.get(tile);
			if(count == null) {
				count = 0;
			}			
			count++;
			collectedTiles.put(tile, count);
			return true;
		}
		return false;
	}

	public int getCollectedTileInventory(TileType tile) {
		Integer count = collectedTiles.get(tile);
		if(count == null) {
			count = 0;
		}
		return count;
	}
}
