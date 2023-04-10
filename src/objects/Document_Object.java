package objects;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class Document_Object extends Objects {
	
	GamePanel gp;
	
	public Document_Object(GamePanel gp)
	{
		this.gp = gp;
		
		this.name = "Document";
		
		try  { image = ImageIO.read(getClass().getResourceAsStream("/objects/Document.png")); }
		catch(IOException e) {e.printStackTrace();}
	}

}
