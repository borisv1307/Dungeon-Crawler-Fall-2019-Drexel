package engine;

import java.util.Random;

public class SystemWrapper {

	public int randomNumberGenerator() {
		return new Random().nextInt(4);
	}

}
