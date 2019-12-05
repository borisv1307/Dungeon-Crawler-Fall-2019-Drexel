package launcher;

import java.io.FileWriter;
import java.io.IOException;

import engine.GameEngine;
import main.DungeonCrawler;
import main.ObjectFactory;
import timer.FramesPerSecondHandler;
import ui.GameFrame;
import wrappers.PrintWritterWrapper;
import wrappers.ThreadWrapper;

public class Launcher {
	public static String name = "src/main/resources/levels/1.txt";

	public static void main(String[] args) throws IOException {

		PrintWritterWrapper printWriter = new PrintWritterWrapper(new FileWriter(name));
		new RandomFile().generateFile(printWriter);
		ThreadWrapper threadWrapper = ObjectFactory.getDefaultThreadWrapper();
		GameEngine gameEngine = ObjectFactory.getDefaultGameEngine();
		GameFrame gameFrame = ObjectFactory.getDefaultGameFrame();
		FramesPerSecondHandler framesPerSecondHandler = ObjectFactory.getDefaultFramesPerSecondHandler();
		new DungeonCrawler(threadWrapper, gameEngine, gameFrame, framesPerSecondHandler);
	}
}
