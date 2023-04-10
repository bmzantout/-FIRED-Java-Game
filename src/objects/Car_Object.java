package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Car_Object extends Objects {
	
	GamePanel gp;
	
	public Car_Object(GamePanel gp)
	{
		this.gp = gp;
		
		this.name = "Car";
		
		try  { image = ImageIO.read(getClass().getResourceAsStream("/objects/Car.png")); }
		catch(IOException e) {e.printStackTrace();}
		
        object_collision = true;
	}

}
