package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import engine.GameEngine;
import tiles.TileType;
import values.TunableParameters;
import wrappers.ReaderWrapper;

public class LevelCreator {
	private static final Logger LOGGER = Logger.getLogger(LevelCreator.class.getName());

	String fileLocationPrefix;
	String fileNameSuffix = TunableParameters.FILE_NAME_SUFFIX;
	ReaderWrapper readerWrapper;
	LevelHandler levelHandler;

	public LevelCreator(String fileLocationPrefix, ReaderWrapper readerWrapper, LevelHandler levelHandler) {
		this.fileLocationPrefix = fileLocationPrefix;
		this.readerWrapper = readerWrapper;
		this.levelHandler = levelHandler;
	}

	public void createLevel(GameEngine gameEngine, int level) {

		BufferedReader reader = null;

		try {
			int newLevel = levelHandler.getNewLevel(level);

			if (!levelHandler.checkIfLastLevel(newLevel)) {
				reader = readerWrapper.createBufferedReader(getFilePath(newLevel));
				gameEngine.level = newLevel;
			} else {
				gameEngine.setExit(true);
				return;
			}
		} catch (FileNotFoundException e) {

			LOGGER.log(Level.SEVERE, e.toString(), e);
			gameEngine.setExit(true);
			return;

		}
		try {
			String line = null;
			int y = 0;
			while ((line = reader.readLine()) != null) {
				int x = 0;
				for (char ch : line.toCharArray()) {
					gameEngine.addTile(x, y, TileType.getTileTypeByChar(ch));
					x++;
				}
				gameEngine.setLevelHorizontalDimension(x);
				y++;
			}
			gameEngine.setLevelVerticalDimension(y);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			gameEngine.setExit(true);
		} finally {
			closeBufferedReader(reader, gameEngine);
		}
	}

	private void closeBufferedReader(BufferedReader reader, GameEngine gameEngine) {
		try {
			reader.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			gameEngine.setExit(true);
		}
	}

	String getFilePath(int level) {
		return fileLocationPrefix + level + fileNameSuffix;
	}
}
