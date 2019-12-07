package wrappers;

import java.security.SecureRandom;

public class RandomNumberWrapper {

	public int nextInt(int max) {

			return new SecureRandom().nextInt(max)+1;		
		
	}
}
