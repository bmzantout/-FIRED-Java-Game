package objects;

import java.awt.image.BufferedImage;
import main.GamePanel;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Objects {

	public BufferedImage image, image2, image3;
	public String name;
	public boolean object_collision;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle (0,0,48,48);
	public int solidArea_default_X = 0;
    public int solidArea_default_Y = 0;

	
	public void draw (Graphics2D g2, GamePanel gp)
	{
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		//this if-statement is to improve rendering performance 
		
		if (worldX + gp.TileSize > gp.player.worldX - gp.player.screenX && 
			worldX - gp.TileSize < gp.player.worldX + gp.player.screenX &&
			worldY + gp.TileSize > gp.player.worldY - gp.player.screenY && 
			worldY - gp.TileSize < gp.player.worldY + gp.player.screenY)
			
		{g2.drawImage(image, screenX, screenY, (gp.TileSize), (gp.TileSize), null);}
	}
	
}
