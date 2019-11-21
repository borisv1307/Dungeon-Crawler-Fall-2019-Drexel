package wrappers;

import java.util.Random;

public class RandomWrapper {
	Random rand = new Random();
	
	public int nextInt(int bound) {
		return rand.nextInt(bound);
	}
	
}
