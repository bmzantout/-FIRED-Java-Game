package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Heart_Object extends Objects {
	
	GamePanel gp;
	
	public Heart_Object(GamePanel gp)
	{
		this.gp = gp;
		
		this.name = "Heart";
		
		try  { 
			image = ImageIO.read(getClass().getResourceAsStream("/objects/heart_full.png"));
		    image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_half.png"));
		    image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart_blank.png"));
		    }
		
		catch(IOException e) {e.printStackTrace();}
	}

	
}
