package ui;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JOptionPane;

import values.TunableParameters;
import wrappers.PaneWrapper;

public class GameFrame extends Frame {

	private static final long serialVersionUID = 1L;

	public GameFrame() {

	}

	public GameFrame(GamePanel gamePanel, WindowAdapterSystemExit windowAdapterSystemExit) {
		setResizable(false);
		addWindowListener(windowAdapterSystemExit);
		gamePanel.setPreferredSize(new Dimension(TunableParameters.SCREEN_WIDTH, TunableParameters.SCREEN_HEIGHT));
		add(gamePanel);
		pack();
		gamePanel.init();
		setVisible(true);
	}

	public void displayPopupMessage(PaneWrapper paneWrapper) {
		paneWrapper.displayMessage(getFrames()[0], "Game Completed Successfully. Click OK to Play Again");
		
	}

}
