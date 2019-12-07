package wrappers;

import java.awt.Frame;

import javax.swing.JOptionPane;

public class PaneWrapper {

	public void displayMessage(Frame frame, String message) {
		JOptionPane.showMessageDialog(frame, message);
		
	}

}
