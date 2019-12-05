package wrappers;

public class SystemWrapper {

	public long nanoTime() {
		return System.nanoTime();
	}

	public void println(Object obj) {
		System.out.println(obj);
	}
}
