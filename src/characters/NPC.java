package characters;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC extends Characters {

	
	public NPC (GamePanel gp)
	{
		super(gp);
		direction = "down";
		speed = 5;
		
		solidArea = new Rectangle();
		solidArea.x = 9;
		solidArea.y = 18;
		solidArea_default_X = solidArea.x;
		solidArea_default_Y = solidArea.y;
		solidArea.width = 30;
		solidArea.height = 30;
		
		getNPCImage();
	}
	
	
	
	public void getNPCImage()    
	{
		try {
			
			up_1 = ImageIO.read(getClass().getResourceAsStream("/npc/cop_back_1.png"));
			up_2 = ImageIO.read(getClass().getResourceAsStream("/npc/cop_back_2.png"));
			down_1 = ImageIO.read(getClass().getResourceAsStream("/npc/cop_front_1.png"));
			down_2 = ImageIO.read(getClass().getResourceAsStream("/npc/cop_front_2.png"));
			right_1 = ImageIO.read(getClass().getResourceAsStream("/npc/cop_right_1 (1).png"));
			right_2 = ImageIO.read(getClass().getResourceAsStream("/npc/cop_right_2 (1).png"));
			left_1 = ImageIO.read(getClass().getResourceAsStream("/npc/cop_left_1 (1).png"));
			left_2 = ImageIO.read(getClass().getResourceAsStream("/npc/cop_left_2 (1).png"));
					
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	}
	
	
	
	public void setAction()
	{
		Action_Lock_Counter++;
		
		if (Action_Lock_Counter == 75)
		{
			Random rand = new Random();
			int r = rand.nextInt(100) + 1;
			
			
     //the NPC moves in each direction 25% of the time 
			
			if (r <= 25)
			{
				direction = "up";
			}
			
			if (r > 25 && r <= 50)
			{
				direction = "down";
			}
			
			if (r > 50 && r <= 75)
			{
				direction = "right";
			}
			
			if (r > 75 && r <= 100)
			{
				direction = "left";
			}
			
			Action_Lock_Counter = 0;
		
	}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
