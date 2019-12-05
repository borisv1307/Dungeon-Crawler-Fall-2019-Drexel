package engine;

import java.security.SecureRandom;

public class RandomNumberGenerator {

	public int randomCoordinatesForBlockers(int max) {

			return new SecureRandom().nextInt(max)+1;		
		
	}
}
