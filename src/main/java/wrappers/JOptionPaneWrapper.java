package wrappers;

import javax.swing.JOptionPane;

public class JOptionPaneWrapper {
	
	public void showDialoge(int numberOfMoves) {
		
	 JOptionPane.showMessageDialog(null, "Number of Moves:"+numberOfMoves+"\n"+"Game Over", "Game Over", JOptionPane.INFORMATION_MESSAGE);
	
	}

}
