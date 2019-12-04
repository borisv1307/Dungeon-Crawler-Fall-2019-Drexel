package wrappers;

import java.security.SecureRandom;

public class MathWrapper {
	public int getRandomInteger(int maximum) {
		return new SecureRandom().nextInt(maximum - 1) + 1;

	}

}
