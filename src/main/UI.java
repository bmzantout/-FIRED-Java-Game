package main;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.Graphics2D;
import java.awt.Color;
import objects.Document_Object;
import objects.Heart_Object;
import objects.Objects;

public class UI {

	
	GamePanel gp;
	Graphics2D g2;
	Font font, font2, fontFINAL, font_title;
	
	BufferedImage document_image, message, heart_full, heart_half, heart_blank, MainMenu; 
    public boolean message_visible = false;
    public String messageText = "";
    public int message_time_counter = 0;
    public boolean GameOver = false;
    public boolean GameWon = false;
    public int commandNum = 0;
    
    double timer;
    DecimalFormat df = new DecimalFormat("#0.00");
	
	
	public UI (GamePanel gp)
	{
		this.gp = gp;
		font = new Font("verdana", Font.PLAIN, 30);
		font2 = new Font("arial", Font.BOLD, 20);
		fontFINAL = new Font("bookman old style", Font.BOLD, 80);
		font_title= new Font("arial", Font.BOLD, 48);
		Document_Object doc = new Document_Object(gp);
		document_image = doc.image;
		try  { message = ImageIO.read(getClass().getResourceAsStream("/messages/message.png")); 
		       MainMenu = ImageIO.read(getClass().getResourceAsStream("/mainmenu/MainMenu2.png"));}
		catch(IOException e) {e.printStackTrace();}
		
		//PLAYER HEARTS
		Objects heart = new Heart_Object(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3; 
	}
	
	public void showMessage(String messageText)
	{
		this.messageText = messageText;
		message_visible = true;
	}
	
	public void draw(Graphics2D g2)
	{
		
		this.g2 = g2;
		
		
	//TITLE STATE
		if (gp.game_state == gp.title_state)
		{
			draw_TitleScreen();
		}
		
	//PLAY STATE
		if (gp.game_state == gp.play_state)
		{
			draw_Messages_and_Counters();
			draw_PlayerLife();
		}
	//GAME OVER STATE
		if (gp.game_state == gp.GameOver_state)
		{
			draw_GameOver();
		}
	//GAME Won STATE
		if (gp.game_state == gp.GameWon_state)
		{
			draw_GameWon();
		}

	

	}
	
	
	public void draw_GameOver()
	{
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(fontFINAL);
		
		text = "YOU ARE FIRED!";
		
		//SHADOW
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.TileSize * 4;
		g2.drawString(text, x, y);
		
		//MAIN TEXT
		g2.setColor(Color.red);
		g2.drawString(text, x-4, y-4);
		
	/*	//RETRY
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(50F));
		text = "RETRY";
		x = getXforCenteredText(text);
		y += gp.TileSize * 4;
		g2.drawString(text, x, y);
		
		 if (commandNum == 0)
	        {
	        	g2.drawString(">", x-40, y);
	        }*/
		
		//QUIT (back to title screen)
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(50F));
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.TileSize*5;
		g2.drawString(text, x, y);
		
		commandNum=1;
		
		 if (commandNum == 1)
	        {
	        	g2.drawString(">", x-40, y);
	        }
	}
	
	
	public void draw_GameWon()
	{
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.ScreenWidth, gp.ScreenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(fontFINAL);
		g2.setFont(g2.getFont().deriveFont(65F));
		
		text = "CONGRATULATIONS!";
		
		//SHADOW
		g2.setColor(Color.black);
		x = getXforCenteredText(text);
		y = gp.TileSize * 4;
		g2.drawString(text, x, y);
		
		//MAIN TEXT
		g2.setColor(Color.yellow);
		g2.drawString(text, x-4, y-4);
		
		//TIMER 
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(30F));
		text = "YOUR TIME IS: " + df.format(timer);
		x += getXforCenteredText(text);
		y += gp.TileSize;
		g2.drawString(text, x, y);
		
		/*//RETRY
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(50F));
		text = "PLAY AGAIN";
		x = getXforCenteredText(text);
		y += gp.TileSize * 3;
		g2.drawString(text, x, y);
		
		 if (commandNum == 0)
	        {
	        	g2.drawString(">", x-40, y);
	        }*/
		
		//QUIT (back to title screen)
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(50F));
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.TileSize*4;
		g2.drawString(text, x, y);
		
		commandNum=1;
		
		 if (commandNum == 1)
	        {
	        	g2.drawString(">", x-40, y);
	        }
	}
	
	
	
	public void draw_Messages_and_Counters()
	{
		//DOCUMENT
		g2.setFont(font);
		g2.setColor(Color.red);
		g2.fillRect(10, 7, gp.TileSize*2, gp.TileSize-10);
		g2.setColor(Color.black);
		g2.drawRect(10, 7, gp.TileSize*2, gp.TileSize-10);
		g2.setColor(Color.white);
		g2.drawImage(document_image, 10, 1, gp.TileSize, gp.TileSize, null);
		g2.drawString("x " + gp.player.Document_NUM, 65, 40);
		
		//TIMER 
		timer += (double)1/60;

		g2.setFont(font);
		g2.setColor(Color.red);
		g2.fillRect(565, 7, 230, gp.TileSize-10);
		g2.setColor(Color.black);
		g2.drawRect(565, 7, 230, gp.TileSize-10);
		g2.setColor(Color.white);
		g2.drawString("Timer: " + df.format(timer), 570, 40);
		
		//MESSAGE
		if (message_visible == true)
		{
			g2.drawImage(message, 8, 470, 300, 100, null);
			g2.setFont(font2);
			g2.setColor(Color.black);
			g2.drawString(messageText, 52, 515);
			
			message_time_counter++;
			
			if (message_time_counter >120)
			{
				message_time_counter = 0;
				message_visible = false;
	}
	
		}
	}
	
	
	public void draw_PlayerLife()
	{
		int x = gp.TileSize * 11;
		int y = gp.TileSize * 9;
		int i = 0;
		
		//BLANK HEARTS
		while (i < gp.player.max_LIFE/2)
		{
			g2.drawImage(heart_blank, x, y, gp.TileSize-5, gp.TileSize-5, null);
			i++;
			x+=gp.TileSize;
		}
		
		//Reset
		x = gp.TileSize * 11;
		y = gp.TileSize * 9;
		i = 0;
		
		//CURRENT HEART (LIFE)
		while (i < gp.player.current_LIFE)
		{
			g2.drawImage(heart_half, x, y, gp.TileSize-5, gp.TileSize-5, null);
			i++;
			
			if (i < gp.player.current_LIFE)
			{
				g2.drawImage(heart_full, x, y, gp.TileSize-5, gp.TileSize-5, null);
			}
			
			i++;
			x+=gp.TileSize;
		}
	}
	
	
	
	public void draw_TitleScreen()
	{
		g2.drawImage(MainMenu, 0, 0, 798, 570, null);
		
		g2.setFont(font_title);
		g2.setColor(Color.white);
		
		int x = gp.TileSize * 3 + 30;
		int y = gp.TileSize * 6 + 40;
		
        if (commandNum == 0)
        {
        	g2.drawString(">", x, y);
        }
        
	     x = gp.TileSize * 5 + 10;
		 y = gp.TileSize * 8 + 15;
		
        if (commandNum == 1)
        {
        	g2.drawString(">", x, y);
        }
        
		
	}
	

    public int getXforCenteredText(String text)
   {
	   int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
	   int x = gp.ScreenWidth/2 - length/2;
	   return x;
   }
   

   








}
