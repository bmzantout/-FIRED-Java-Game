package tiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tiles;
	public int [][] tile_MAP;
	
	
	public TileManager(GamePanel gp)
	{
		this.gp = gp;
		tiles = new Tile [15];
		tile_MAP = new int [gp.MaxWorld_Column][gp.MaxWorld_Row];
		
		getTileImage();
		LoadMap();
		
	}
	
	public void getTileImage()
	{
        try {
			
        tiles[0] = new Tile();
        tiles[0].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
        tiles[0].collide = true;
        
        tiles[1] = new Tile();
        tiles[1].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_VERTICAL.png"));
        
        tiles[2] = new Tile();
        tiles[2].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_HORIZONTAL.png"));
        
        tiles[3] = new Tile();
        tiles[3].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_CENTER.png"));
        
        tiles[4] = new Tile();
        tiles[4].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_TURN_upLEFT (1).png"));
        
        tiles[5] = new Tile();
        tiles[5].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_TURN_upRIGHT (1).png"));
        
        tiles[6] = new Tile();
        tiles[6].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_TURN_downLEFT (1).png"));
        
        tiles[7] = new Tile();
        tiles[7].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_TURN_downRIGHT (1).png"));
        
        tiles[8] = new Tile();
        tiles[8].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_THREE_UP.png"));
        
        tiles[9] = new Tile();
        tiles[9].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_THREE_DOWN.png"));
        
        tiles[10] = new Tile();
        tiles[10].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_THREE_RIGHT.png"));
        
        tiles[11] = new Tile();
        tiles[11].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/street_THREE_LEFT.png"));
        
        tiles[12] = new Tile();
        tiles[12].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
        tiles[12].collide = true;
        
        tiles[13] = new Tile();
        tiles[13].tileImage = ImageIO.read(getClass().getResourceAsStream("/tiles/house.png"));
        tiles[13].collide = true;
	
					
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void LoadMap()
	{
		try {
			InputStream is = getClass().getResourceAsStream("/map/worldMAP.txt");
			BufferedReader b = new BufferedReader(new InputStreamReader(is));
			
	       int column = 0;
	       int row = 0;
	       
			while ((column < gp.MaxWorld_Column) && (row < gp.MaxWorld_Row))
			{
				String line = b.readLine();
				
				while (column < gp.MaxWorld_Column)
				{
					String [] TileNumbers = line.split(" ");
					int TileNumber = Integer.parseInt(TileNumbers[column]);
					
					tile_MAP[column][row] = TileNumber;
					column++;
				}
				
				if (column == gp.MaxWorld_Column)
				{
					column = 0;
					row++;
				}
			}
						
			}
			catch (Exception e) {}
	}
	
	
	public void draw (Graphics2D g2)
	{
	
		int World_column = 0;
		int World_row = 0;
		
		while ((World_column < gp.MaxWorld_Column) && (World_row < gp.MaxWorld_Row))
		{
			int tile_number = tile_MAP[World_column][World_row];
			
			int worldX = World_column * gp.TileSize;
			int worldY = World_row * gp.TileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//this if-statement is to improve rendering performance 
			
			if (worldX + gp.TileSize > gp.player.worldX - gp.player.screenX && 
				worldX - gp.TileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.TileSize > gp.player.worldY - gp.player.screenY && 
				worldY - gp.TileSize < gp.player.worldY + gp.player.screenY)
				
			{g2.drawImage(tiles[tile_number].tileImage, screenX, screenY, (gp.TileSize), (gp.TileSize), null);}
			
			World_column++;
			
			
			if (World_column == gp.MaxWorld_Column)
			{
				World_column = 0;
				World_row++;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
