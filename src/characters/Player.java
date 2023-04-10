package characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.*;
import java.util.Random;
import main.GamePanel;
import main.KeyBoardInput;
import main.MainClass;
import main.MainMethod;

public class Player extends Characters {
 
	

	KeyBoardInput key;
	public final int screenX;
	public final int screenY;
	public int Document_NUM;
    public boolean DecreaseSoul = false;
	public int Question = 1; 
	public static boolean answer_check;
	public int Action_Lock_Counter2 = 0;

	

	public Player (GamePanel gp, KeyBoardInput key)
	{
	    super (gp);
		this.key = key;
		screenX = gp.ScreenWidth/2 ;
		screenY = gp.ScreenHeight/2 ;

		
		solidArea = new Rectangle();
		solidArea.x = 9;
		solidArea.y = 18;
		solidArea_default_X = solidArea.x;
		solidArea_default_Y = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
	    setDefaultValues();
	    getPlayerImage();
	}
	
	public void setDefaultValues()
	{
		worldX = gp.TileSize * 11;
		worldY = gp.TileSize * 10;
		speed = 3;
		direction = "standard";
		
		//PLAYER STATUS
		max_LIFE = 6;
		current_LIFE = max_LIFE;
	}
	
	
	public void getPlayerImage()    
	{
		try {
			
			up_1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
			up_2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
			down_1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
			down_2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
			right_1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
			right_2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
			left_1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
			left_2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
			standard_2 = ImageIO.read(getClass().getResourceAsStream("/player/standard_2.png"));
					
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	}
	
	
	public void update() 
	{
		if (key.moveUP == true || key.moveDOWN == true || key.moveLEFT == true || key.moveRIGHT == true ) {
			
			if (key.moveUP == true)
			{   direction = "up";}
			
			else if (key.moveDOWN == true)
			{   direction = "down";}
			
			else if (key.moveRIGHT == true)
			{   direction = "right";}
			
			else if (key.moveLEFT == true)
			{   direction = "left";}
			
			
			sprite_counter++;
			if (sprite_counter > 15) 
			{ 
				if (number_of_sprites == 1)
					{number_of_sprites = 2;}
				
				else if (number_of_sprites == 2)
					{number_of_sprites = 1;}
				
				sprite_counter = 0;
			}
		}
		
		else direction = "standard";
		
		
		//CHECK TILE COLLISION
		coll = false;
		gp.c.Check_Tile_Collision(this);
		
		//CHECK OBJECT COLLISION
		int objINDEX = gp.c.Check_Object_Collision(this, true);
		PickDocument(objINDEX);
		
		//CHECK NPC COLLISION
		int npcINDEX = gp.c.Check_PlayertoNPC_Collision(this, gp.npc);
		Ineract_With_NPC(npcINDEX);
		
		//IF COLLIsion IS FALSE PLAYER CAN MOVE
		
		if (coll == false) {
			
			switch (direction) {
			  
			case "up": worldY -= speed;
				break;
				
			case "down": worldY += speed;
				break;
				
			case "right": worldX += speed;
				break;
				
			case "left":  worldX -= speed;
				break;
					
			}
		}
	}
	
	
	public void PickDocument(int index)
	{
		if (index != 999)
		{
			String object_name = gp.obj[index].name;
			
			Random r = new Random();
			int set = r.nextInt(5) + 1;

			
			switch (object_name)
			{
			case "Document":
				
				gp.playSoundEffects(3);
				gp.obj[index] = null;
				this.speed = 0;
				for (int i = 0; i < gp.npc.length ; i++)
				{
					if (gp.npc[i] != null)
					gp.npc[i].speed = 0;
				}
				MainMethod.q.removeAll();
				Ask_Question(set);
				Question++;
				
				Document_NUM++;
				gp.GUI.showMessage("You got a document!");
					
				
				if (Document_NUM == 6)
				{
					gp.GUI.showMessage("Run to the car!");
				}
				
				break;
				
			case "Car":
				
				if (Document_NUM == 6)
				{
					gp.obj[index] = null;
					gp.game_state = gp.GameWon_state;
					gp.stopMusic();
					gp.playSoundEffects(6);
				}
				
				break;
			}
			
		}
}
	
    public void	Ineract_With_NPC(int index)
    {
         
    	
    	if (index != 999)
    	{  
    		Action_Lock_Counter++;
    		if (Action_Lock_Counter == 15)
    		{
    			gp.player.current_LIFE--;
    			Action_Lock_Counter = 0;
    			
    			if (gp.player.current_LIFE == 0)
				{
					gp.stopMusic();
					gp.playSoundEffects(5);
					gp.game_state = gp.GameOver_state;

				}
    		}
    	}
    	
    }

	
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		
		switch (direction) {
		
		case "up":
			if (number_of_sprites == 1)
			   {image = up_1;}
			
			if (number_of_sprites == 2)
			   {image = up_2;}
			break;
			
		case "down": 
			if (number_of_sprites == 1)
			   {image = down_1;}
			
			if (number_of_sprites == 2)
			   {image = down_2;}
			break;
			
		case "right": 
			if (number_of_sprites == 1)
			   {image = right_1;}
			
			if (number_of_sprites == 2)
			   {image = right_2;}
			break;
			
		case "left": 
			if (number_of_sprites == 1)
			   {image = left_1;}
			
			if (number_of_sprites == 2)
			   {image = left_2;}
			break;
			
		case "standard": image = standard_2; 
		break;
		}
		
		g2.drawImage(image, screenX, screenY, (gp.TileSize), (gp.TileSize), null);
	}
	
	
	JLabel question = new JLabel();
	JLabel multiple_choice_input = new JLabel("<html><br/><br/>Write the letter of your answer choice: <br/><br/><html>");
	JLabel true_false_input = new JLabel("<html><br/><br/>Write the letter 'T' for True or 'F' for False: <hmtl>");
	JLabel output = new JLabel();
	JTextField answer_box;
	String ans;
	
	public void Ask_Question(int set)
	{

		
		switch (set)
		{
		
		case 1: 
			
			if (Question == 1)
		{
			question.setText("<html><font color='green'>Question 1: </font>Valid arguments to <br/>the System.out object’s println <br/>method include:<br/><br/>A) \"Anything with double quotes\"<br/><br/>B) String variables<br/><br/>C) Variables of type int<br/><br/>D) All of the above<html>");
		    question.setFont(new Font("verdana", Font.BOLD, 20));
		    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
			ans = "D";
			answer_box = new JTextField(10);

			MainMethod.q.add(question);
			MainMethod.q.add(multiple_choice_input);
			MainMethod.q.add(answer_box);

			answer_box.addActionListener(new Answer_Checker());
			MainMethod.q.add(output);
		
		}
			
			if (Question == 2)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 2: </font>Standard code<br/> libraries in Java are called:<br/><br/>A) Methods<br/><br/>B) Classes<br/><br/>C) Packages<br/><br/>D) Statements<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "C";
				answer_box = new JTextField(10);
				
				MainMethod.q.add(question);
				MainMethod.q.add(multiple_choice_input);
				MainMethod.q.add(answer_box);
		
				answer_box.setText(null);
	
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	                 
			}
			
			if (Question == 3)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 3: </font>Creating two or <br/>more methods with the same <br/>name but with different<br/> signatures is called:<br/><br/>A) Method Overriding<br/><br/>B) Method Overloading<br/><br/>C) Method Decomposition<br/><br/>D) Method Declaration<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "B";
				answer_box = new JTextField(10);

				MainMethod.q.add(question);
				MainMethod.q.add(multiple_choice_input);
				MainMethod.q.add(answer_box);

				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 4)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 4: </font>The Scanner class <br/>has a method next that allows <br/>an entire line of String <br/>text to be read<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "F";
				answer_box = new JTextField(10);

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);

				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 5)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 5: </font>The new line <br/>character is represented<br/> as /n<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "F";
				answer_box = new JTextField(10);

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);

				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 6)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 6: </font>Abstract classes can<br/> contain normal (non-abstract)<br/> methods<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "T";
				answer_box = new JTextField(10);

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);

				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
		        
			
			break;
			
		case 2:
			
			if (Question == 1)
		{
			question.setText("<html><font color='green'>Question 1: </font>What Java Package <br/>includes the class Scanner?<br/><br/>A) util<br/><br/>B) swing<br/><br/>C) io<br/><br/>D) awt<html>");
		    question.setFont(new Font("verdana", Font.BOLD, 20));
		    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
			ans = "A";
			answer_box = new JTextField(10);;

			MainMethod.q.add(question);
			MainMethod.q.add(multiple_choice_input);
			MainMethod.q.add(answer_box);
	
			answer_box.addActionListener(new Answer_Checker());
			MainMethod.q.add(output);
        	
            
		}
			
			if (Question == 2)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 2: </font>The conversion from a <br/>primitive value to a wrapper <br/>object is called:<br/><br/>A) Casting<br/><br/>B) Unboxing<br/><br/>C) Promotion<br/><br/>D) Autoboxing<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "D";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(multiple_choice_input);
				MainMethod.q.add(answer_box);
		
				answer_box.setText(null);
			
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	         
			}
			
			if (Question == 3)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 3: </font>A support method - a <br/>method that assists a service<br/> method - must be set as:<br/><br/>A) Protected<br/><br/>B) Public<br/><br/>C) Private<br/><br/>D) Default<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "C";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(multiple_choice_input);
				MainMethod.q.add(answer_box);

				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 4)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 4: </font>Public constants <br/>violate the encapsulation <br/>principle<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "F";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);
			
				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 5)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 5: </font>Two or more <br/>references to one object are <br/>called Aliases<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "T";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);

				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 6)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 6: </font>The toString method<br/> is automatically called when an<br/> object is passed to the println<br/> method<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "T";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);
		
				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			break;
			
			
		case 3:
			
			if (Question == 1)
		{
			question.setText("<html><font color='green'>Question 1: </font>What Java Package <br/>includes the class Math?<br/><br/>A) awt<br/><br/>B) lang<br/><br/>C) util<br/><br/>D) swing<html>");
		    question.setFont(new Font("verdana", Font.BOLD, 20));
		    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
			ans = "B";
			answer_box = new JTextField(10);;

			MainMethod.q.add(question);
			MainMethod.q.add(multiple_choice_input);
			MainMethod.q.add(answer_box);
	
			answer_box.addActionListener(new Answer_Checker());
			MainMethod.q.add(output);
        	
            
		}
			
			if (Question == 2)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 2: </font>Data that is applicable<br/> only inside the method it is <br/>declared in is called:<br/><br/>A) Instance data<br/><br/>B) Public data<br/><br/>C) Private data<br/><br/>D) Local data<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "D";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(multiple_choice_input);
				MainMethod.q.add(answer_box);
		
				answer_box.setText(null);
			
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	         
			}
			
			if (Question == 3)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 3: </font>Sorting by selecting a<br/> value and putting it in its <br/>final place in the list is <br/>called:<br/><br/>A) Insertion Sort<br/><br/>B) Heap Sort<br/><br/>C) Selection Sort<br/><br/>D) Merge Sort<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "C";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(multiple_choice_input);
				MainMethod.q.add(answer_box);
				
				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 4)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 4: </font>A class implementing <br/>a child class must also implement <br/>its parent’s methods, too<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "T";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);

				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 5)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 5: </font>The formal parameter <br/>creates a copy of the object <br/>passed to the actual <br/>parameter<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "F";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);
				
				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
	          
			}
			
			if (Question == 6)
			{
				output.setText("");
				MainMethod.q.add(output);
				MainMethod.q.repaint();
				question.setText("<html><font color='green'>Question 6: </font>We can have more <br/>than one type of parameter <br/>along with a variable-length <br/>parameter<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "T";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(true_false_input);
				MainMethod.q.add(answer_box);
			
				answer_box.setText(null);
				
				answer_box.addActionListener(new Answer_Checker());
	            MainMethod.q.add(output);
			}
			
			break;
			
			
		case 4:
			
			if (Question == 1)
			{
				question.setText("<html><font color='green'>Question 1: </font>What Java Package <br/>includes the class Random?<br/><br/>A) util<br/><br/>B) swing<br/><br/>C) io<br/><br/>D) awt<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "A";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(multiple_choice_input);
				MainMethod.q.add(answer_box);
		
				answer_box.addActionListener(new Answer_Checker());
				MainMethod.q.add(output);
	        	
	            
			}
				
				if (Question == 2)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 2: </font>A set of input and<br/> user actions, coupled with<br/> the expected results is <br/>called:<br/><br/>A) an inspection<br/><br/>B) a walkthrough<br/><br/>C) a test case<br/><br/>D) defect testing<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "C";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(multiple_choice_input);
					MainMethod.q.add(answer_box);
			
					answer_box.setText(null);
				
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		         
				}
				
				if (Question == 3)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 3: </font>When designing a <br/>class hierarchy it is important <br/>that the common features be<br/><br/>A) in abstract classes<br/><br/>B) higher in the class hierarchy<br/><br/>C) lower in the class hierarchy<br/><br/>D) in the Object class<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "B";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(multiple_choice_input);
					MainMethod.q.add(answer_box);

					answer_box.setText(null);
					
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		          
				}
				
				if (Question == 4)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 4: </font>An interface can <br/>inherit abstract methods from <br/>another interface<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "T";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(true_false_input);
					MainMethod.q.add(answer_box);

					answer_box.setText(null);
					
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		          
				}
				
				if (Question == 5)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 5: </font>A try block can be <br/>followed by more than one catch <br/>clause<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "T";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(true_false_input);
					MainMethod.q.add(answer_box);

					answer_box.setText(null);
					
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		          
				}
				
				if (Question == 6)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 6: </font>A class is required <br/>to call a method in the same <br/>class it is declared using an <br/> object in<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "F";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(true_false_input);
					MainMethod.q.add(answer_box);

					answer_box.setText(null);
					
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		          
				}
			
			
			break;
			
		case 5:
			
			if (Question == 1)
			{
				question.setText("<html><font color='green'>Question 1: </font>Running previous <br/>test suites to ensure new errors <br/>haven't been introduced is <br/>known as:<br/><br/>A) Defect Testing<br/><br/>B) Regression Testing<br/><br/>C) Performance Testing<br/><br/>D) Integration Testing<html>");
			    question.setFont(new Font("verdana", Font.BOLD, 20));
			    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
				ans = "B";
				answer_box = new JTextField(10);;

				MainMethod.q.add(question);
				MainMethod.q.add(multiple_choice_input);
				MainMethod.q.add(answer_box);
		
				answer_box.addActionListener(new Answer_Checker());
				MainMethod.q.add(output);
	        	
	            
			}
				
				if (Question == 2)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 2: </font>Which of the <br/>following is a checked Exception:<br/><br/>A) ClassNotFoundException<br/><br/>B) IndexOutOfBoundsException<br/><br/>C) NullPointerException<br/><br/>D) ArithmeticException<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "A";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(multiple_choice_input);
					MainMethod.q.add(answer_box);
			
					answer_box.setText(null);
				
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		         
				}
				
				if (Question == 3)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 3: </font>To terminate a <br/>program, use the Java statement:<br/><br/>A) System.abort(0);<br/><br/>B) System.quit(0);<br/><br/>C) System.end(0);<br/><br/>D) System.exit(0);<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    multiple_choice_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "D";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(multiple_choice_input);
					MainMethod.q.add(answer_box);

					answer_box.setText(null);
					
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		          
				}
				
				if (Question == 4)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 4: </font>\"void\" is a return <br/>type that does not return a value<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "T";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(true_false_input);
					MainMethod.q.add(answer_box);

					answer_box.setText(null);
					
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		          
				}
				
				if (Question == 5)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 5: </font>In a switch statement, <br/>the default case is always <br/>executed<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "F";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(true_false_input);
					MainMethod.q.add(answer_box);

					answer_box.setText(null);
					
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		          
				}
				
				if (Question == 6)
				{
					output.setText("");
					MainMethod.q.add(output);
					MainMethod.q.repaint();
					question.setText("<html><font color='green'>Question 6: </font>The compareTo <br/>method is included in every class <br/>created in Java by inheritance<html>");
				    question.setFont(new Font("verdana", Font.BOLD, 20));
				    true_false_input.setFont(new Font("verdana", Font.ITALIC, 16));
					ans = "F";
					answer_box = new JTextField(10);;

					MainMethod.q.add(question);
					MainMethod.q.add(true_false_input);
					MainMethod.q.add(answer_box);

					answer_box.setText(null);
					
					answer_box.addActionListener(new Answer_Checker());
		            MainMethod.q.add(output);
		          
				}
			break;
			
		
		}
		
		
	}

	
	public String x;
	
	
	 class Answer_Checker implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				x = answer_box.getText();
				if (x.equalsIgnoreCase(ans))
				{
					gp.playSoundEffects(1);
					output.setText("<html><br/><br/><font color='green'>Congrats! YOU LOOK LIKE<br/>YOU'VE STUDIED WELL!</font><html>");
					output.setFont(new Font("verdana", Font.BOLD, 22));
					output.setAlignmentX(JLabel.CENTER);

				}
				else 
				{
					gp.playSoundEffects(2);
					output.setText("<html><br/><br/><font color='red'>Ooops! You've lost a <br/>soul! STUDY MORE!</font><html>");
					output.setFont(new Font("verdana", Font.BOLD, 25));
					gp.player.current_LIFE--;
					
					if (gp.player.current_LIFE == 0)
					{
						gp.stopMusic();
						gp.playSoundEffects(5);
						gp.game_state = gp.GameOver_state;
					}

				}					
				answer_box.setEnabled(false);
				speed = 3;
				for (int i = 0; i < gp.npc.length ; i++)
				{
					if (gp.npc[i] != null)
					gp.npc[i].speed = 5;
				}
			}
		 }
	 
}



























