package main;

import engine.GameEngine;
import parser.LevelCreator;
import parser.LevelMove;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import ui.GamePanel;
import ui.TilePainter;
import ui.WindowAdapterSystemExit;
import values.TunableParameters;
import wrappers.ReaderWrapper;
import wrappers.SystemWrapper;
import wrappers.ThreadWrapper;

public abstract class ObjectFactory {
	private static ThreadWrapper defaultThreadWrapper = new ThreadWrapper();

	private static LevelMove defaultLevelMove = new LevelMove(TunableParameters.MIN_LEVEL, TunableParameters.MIN_LEVEL,
			TunableParameters.MAX_LEVEL);

	private static LevelCreator defaultLevelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
			new ReaderWrapper());

	private static GameEngine defaultGameEngine = new GameEngine(ObjectFactory.defaultLevelCreator,
			ObjectFactory.defaultLevelMove);

	private static GameFrame defaultGameFrame = new GameFrame(
			new GamePanel(ObjectFactory.defaultGameEngine, new TilePainter()),
			new WindowAdapterSystemExit(ObjectFactory.defaultGameEngine));

	private static FramesPerSecondHandler defaultFramesPerSecondHandler = new FramesPerSecondHandler(
			TunableParameters.TARGET_FPS, new SystemWrapper());

	public static FramesPerSecondHandler getDefaultFramesPerSecondHandler() {
		return ObjectFactory.defaultFramesPerSecondHandler;
	}

	public static GameEngine getDefaultGameEngine() {
		return ObjectFactory.defaultGameEngine;
	}

	public static GameFrame getDefaultGameFrame() {
		return ObjectFactory.defaultGameFrame;
	}

	public static ThreadWrapper getDefaultThreadWrapper() {
		return ObjectFactory.defaultThreadWrapper;
	}

	public static LevelMove getLevelMove() {
		return ObjectFactory.defaultLevelMove;
	}

	private ObjectFactory() {
	}
}
