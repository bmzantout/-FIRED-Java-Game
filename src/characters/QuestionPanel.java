package characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;


public class QuestionPanel extends JPanel {

	
	static JLabel input;
	public JLabel title;
	public JLabel welcome;
	
	public QuestionPanel()
	{
		this.setPreferredSize(new Dimension(400 , 570));
		this.setBackground(Color.white);
		
		title = new JLabel("<html><font color='red'>FIRED!</font><br/><br/><html>");
		title.setFont(new Font("verdana", Font.BOLD, 40));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		welcome = new JLabel("<html>You stole 6 important documents<br/><br/>from your company. BUT,a wind<br/><br/>blow scattered the documents in<br/><br/>random places on the street. <br/><br/>Avoiding the police, collect the<br/><br/> documents, answer the questions,<br/><br/>and runto your car!<html>");
		welcome.setFont(new Font("verdana", Font.BOLD, 15));
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(title);
		this.add(welcome);

	}
	
}


 class QuestionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}