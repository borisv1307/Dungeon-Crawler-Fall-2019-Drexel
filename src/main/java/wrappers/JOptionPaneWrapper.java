package wrappers;

import java.awt.Component;
import java.awt.HeadlessException;

import javax.swing.JOptionPane;

public class JOptionPaneWrapper {
	public int ERROR_MESSAGE;

	@SuppressWarnings("static-access")
	public void showMessageDialog(Component parentComponent, Object message, String title, int messageType)
			throws HeadlessException {
		new JOptionPane().showMessageDialog(parentComponent, message, title, messageType);

	}

}
