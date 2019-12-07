package launcher;

import java.io.IOException;

import engine.GameEngine;
import main.DungeonCrawler;
import main.ObjectFactory;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import wrappers.PrintWriterWrapper;
import wrappers.ThreadWrapper;

public class Launcher {

	public static void main(String[] args) throws IOException {

		PrintWriterWrapper printWriterWrapper = ObjectFactory.getDefaultPrintWriterWrapper();
		RandomFile randomFile = ObjectFactory.getDefaultRandomFile();
		ThreadWrapper threadWrapper = ObjectFactory.getDefaultThreadWrapper();
		GameEngine gameEngine = ObjectFactory.getDefaultGameEngine();
		GameFrame gameFrame = ObjectFactory.getDefaultGameFrame();
		FramesPerSecondHandler framesPerSecondHandler = ObjectFactory.getDefaultFramesPerSecondHandler();
		new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler, printWriterWrapper,
				randomFile);
	}
}
