package wrappers;

public class SystemWrapper {

	public long nanoTime() {
		return System.nanoTime();
	}

	public void println(Object object) {
		System.out.println(object);
	}

}
