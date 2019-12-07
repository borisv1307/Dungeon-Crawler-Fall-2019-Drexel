package main;

import java.io.FileWriter;
import java.io.IOException;

import engine.GameEngine;
import launcher.RandomFile;
import parser.LevelCreator;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import ui.GamePanel;
import ui.TilePainter;
import ui.WindowAdapterSystemExit;
import values.TunableParameters;
import wrappers.JOptionPaneWrapper;
import wrappers.PrintWriterWrapper;
import wrappers.ReaderWrapper;
import wrappers.SecureRandomWrapper;
import wrappers.SystemWrapper;
import wrappers.ThreadWrapper;

public abstract class ObjectFactory {
	private ObjectFactory() {
	}

	private static String name = "src/main/resources/levels/1.txt";
	private static ThreadWrapper defaultThreadWrapper = new ThreadWrapper();

	private static LevelCreator defaultLevelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
			new ReaderWrapper());

	private static GameEngine defaultGameEngine = new GameEngine(defaultLevelCreator, new JOptionPaneWrapper());

	private static GameFrame defaultGameFrame = new GameFrame(new GamePanel(defaultGameEngine, new TilePainter()),
			new WindowAdapterSystemExit(defaultGameEngine));
	private static RandomFile defaultRandomFile = new RandomFile(getDefaultSecureRandomWrapper());

	private static FramesPerSecondHandler defaultFramesPerSecondHandler = new FramesPerSecondHandler(
			TunableParameters.TARGET_FPS, new SystemWrapper());

	public static PrintWriterWrapper getDefaultPrintWriterWrapper() throws IOException {
		return new PrintWriterWrapper(new FileWriter(name));

	}

	public static SecureRandomWrapper getDefaultSecureRandomWrapper() {
		return new SecureRandomWrapper();
	}

	public static RandomFile getDefaultRandomFile() {
		return defaultRandomFile;
	}

	public static ThreadWrapper getDefaultThreadWrapper() {
		return defaultThreadWrapper;
	}

	public static GameEngine getDefaultGameEngine() {
		return defaultGameEngine;
	}

	public static GameFrame getDefaultGameFrame() {
		return defaultGameFrame;
	}

	public static FramesPerSecondHandler getDefaultFramesPerSecondHandler() {
		return defaultFramesPerSecondHandler;
	}

}
