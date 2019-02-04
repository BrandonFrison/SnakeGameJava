import java.awt.Color;

import javax.swing.JFrame;

public class MainSnakeGameClass {

	public static void main(String[] args) {

		// Jframe window initiation code
		JFrame frame = new JFrame();
		SnakeGameworks game = new SnakeGameworks(); 
		
		frame.setBounds(40, 40, 905, 750);
		frame.setBackground(Color.DARK_GRAY);
		frame.setResizable(false);	
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
	}

}
