package parser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import values.TestingTunableParameters;
import values.TunableParameters;

public class LevelCreationStepDefHelper {
	protected static int LEVEL = 1;
	protected static final int COORDINATE_OFFSET = LEVEL;

	protected void writeLevelFile(List<String> levelStrings, int... level)
			throws FileNotFoundException, UnsupportedEncodingException {

		if (level.length > 0)
			LEVEL = level[0];
		PrintWriter writer = new PrintWriter(getFilePath(), "UTF-8");
		for (String levelString : levelStrings) {
			writer.println(levelString);
		}
		writer.close();
	}

	private String getFilePath() {
		return TestingTunableParameters.FILE_LOCATION_PREFIX + LEVEL + TunableParameters.FILE_NAME_SUFFIX;
	}
}