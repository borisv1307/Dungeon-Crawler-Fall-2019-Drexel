package wrappers;

import java.security.SecureRandom;

public class SecureRandomWrapper {
	public int nextInt(int max, int min) {
		return new SecureRandom().nextInt((max - min) + 1) + min;
	}

}