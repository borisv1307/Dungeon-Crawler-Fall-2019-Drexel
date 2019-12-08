package wrappers;

import javax.swing.JOptionPane;

public class SystemWrapper {

	public long nanoTime() {
		return System.nanoTime();
	}

	public void JOptionPanelDisplay(String header, String message) {
		JOptionPane.showMessageDialog(null, message, header, JOptionPane.INFORMATION_MESSAGE);
	}
}
