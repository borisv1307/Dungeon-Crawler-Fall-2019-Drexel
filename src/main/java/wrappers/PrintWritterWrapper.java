package wrappers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWritterWrapper {
	private FileWriter fileWriter;

	public PrintWritterWrapper(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	@SuppressWarnings("resource")
	public void print(String c) throws IOException {
		new PrintWriter(fileWriter).print(c);
	}

	public void close() throws IOException {
		new PrintWriter(fileWriter).close();
	}

}
