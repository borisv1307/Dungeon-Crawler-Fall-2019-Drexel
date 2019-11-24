package wrappers;

import java.util.Random;

public class RandomWrapper {

	public int nextInt(Object object) {
		return new Random().nextInt((int) object);
	}

}
