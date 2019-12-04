package wrappers;

import java.util.Random;

public class MathWrapper {
	public int getRandomInteger(int maximum) {
		return new Random().nextInt(maximum - 1) + 1;

	}

}
