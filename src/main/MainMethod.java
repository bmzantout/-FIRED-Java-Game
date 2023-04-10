package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import characters.QuestionPanel;
import java.awt.Font;

public class MainMethod {
	
	public static QuestionPanel q;
	public static JPanel MAINPANEL;
	

	public void startTheGame()
	{
		JFrame game_frame = new JFrame("Welcome to the Game!");
		game_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game_frame.setResizable(false);
		
		MAINPANEL = new JPanel();
	    GamePanel game_panel  = new GamePanel(); // Main panel of the Game
		q = new QuestionPanel();
		
		game_panel.SetUpGame();
		game_panel.startGameThread();
	
		MAINPANEL.add(game_panel);
		MAINPANEL.add(q);
		game_frame.add(MAINPANEL);
		
		game_frame.pack();
		game_frame.setLocationRelativeTo(null);
		game_frame.setVisible(true);
	}
}
