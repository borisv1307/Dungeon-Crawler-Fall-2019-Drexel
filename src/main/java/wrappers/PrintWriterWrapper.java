package wrappers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterWrapper {
	private FileWriter fileWriter;

	public PrintWriterWrapper(FileWriter fileWriter) {
		this.fileWriter = fileWriter;
	}

	public void print(String c) throws IOException {
		new PrintWriter(fileWriter).print(c);
	}

	public void close() throws IOException {
		new PrintWriter(fileWriter).close();
	}

}
