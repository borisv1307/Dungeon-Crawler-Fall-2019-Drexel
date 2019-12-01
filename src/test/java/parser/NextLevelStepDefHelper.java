package parser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import values.TestingTunableParameters;
import values.TunableParameters;

public class NextLevelStepDefHelper {

	protected int NEXT_LEVEL = 1;

	protected void writeLevelFile(List<String> levelStrings, int level)
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(getFilePath(), "UTF-8");
		this.NEXT_LEVEL = level;
		for (String levelString : levelStrings) {
			writer.println(levelString);
		}
		writer.close();
	}

	private String getFilePath() {
		return TestingTunableParameters.FILE_LOCATION_PREFIX + NEXT_LEVEL + TunableParameters.FILE_NAME_SUFFIX;
	}
}
