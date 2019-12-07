package wrappers;

import javax.swing.JOptionPane;

public class JOptionPaneWrapper {

	public void showMessage(int count) {
		
		JOptionPane.showMessageDialog(null, "Numer Of Moves:"+count+"\n"+"Game Over");
		
	}
}
