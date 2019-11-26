package wrappers;

public class MathWrapper {
	public int getRandomInteger(int maximum, int minimum){
		return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }

}
