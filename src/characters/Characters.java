package characters;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.awt.Graphics2D;

import main.GamePanel;
public class Characters {

	public int worldX;
	public int worldY  ;
	public int speed;
	public GamePanel gp;
	
	public BufferedImage up_1, up_2, down_1, down_2, right_1, right_2, left_1, left_2, standard_2, up_NPC, down_NPC, right_NPC, left_NPC; // to store our (buffered) image files
	public String direction;
	public int sprite_counter = 0;
	public int number_of_sprites = 1;
	
	public Rectangle solidArea;
	public int solidArea_default_X, solidArea_default_Y;
	public boolean coll = false, coll2 = false;
	public int Action_Lock_Counter = 0, Action_Lock_Counter2 = 0; 

	//CHARACTER STATUS
	public int max_LIFE;
	public int current_LIFE;
	
	public Characters(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setAction() {}
	
	public void update() 
	{
		setAction();
		
		coll = false;
		coll2 = false;
		gp.c.Check_Tile_Collision(this);
		gp.c.Check_Object_Collision(this, false);
		gp.c.Check_NPCtoPlayer_Collsion(this);
		
		
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
		else if (coll2 == true)
		{
			Action_Lock_Counter2++;
    		if (Action_Lock_Counter2 == 18)
    		{
    			gp.player.current_LIFE--;
    			Action_Lock_Counter2 = 0;
    			
    			if (gp.player.current_LIFE == 0)
				{
					gp.stopMusic();
					gp.playSoundEffects(5);
					gp.game_state = gp.GameOver_state;

				}
    		}
    	}
		
		
		
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
	
	public void draw(Graphics2D g2)
	{
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		//this if-statement is to improve rendering performance 
		
		if (worldX + gp.TileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.TileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.TileSize > gp.player.worldY - gp.player.screenY && 
			worldY - gp.TileSize < gp.player.worldY + gp.player.screenY)
			
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
				
			}
			
		{g2.drawImage(image, screenX, screenY, (gp.TileSize-2), (gp.TileSize-2), null);}
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

