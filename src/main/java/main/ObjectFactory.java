package main;

import engine.GameEngine;
import parser.LevelCreator;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import ui.GamePanel;
import ui.TilePainter;
import ui.WindowAdapterSystemExit;
import values.TunableParameters;
import wrappers.JOptionPaneWrapper;
import wrappers.RandomNumberWrapper;
import wrappers.ReaderWrapper;
import wrappers.SystemWrapper;
import wrappers.ThreadWrapper;

public abstract class ObjectFactory {
    private ObjectFactory() {}

    private static ThreadWrapper defaultThreadWrapper = new ThreadWrapper();
    private static RandomNumberWrapper defaultRandomNumberWrapper = new RandomNumberWrapper();
    private static JOptionPaneWrapper defaultJOptionPaneWrapper = new JOptionPaneWrapper();
    private static LevelCreator defaultLevelCreator = new LevelCreator(TunableParameters.FILE_LOCATION_PREFIX,
        new ReaderWrapper());

    private static GameEngine defaultGameEngine = new GameEngine(defaultLevelCreator, defaultRandomNumberWrapper, defaultJOptionPaneWrapper);

    private static GameFrame defaultGameFrame = new GameFrame(new GamePanel(defaultGameEngine, new TilePainter()),
        new WindowAdapterSystemExit(defaultGameEngine));

    private static FramesPerSecondHandler defaultFramesPerSecondHandler = new FramesPerSecondHandler(
        TunableParameters.TARGET_FPS, new SystemWrapper());

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