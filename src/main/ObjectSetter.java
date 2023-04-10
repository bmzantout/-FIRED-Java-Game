package main;

import java.util.Random;

import characters.NPC;
import objects.Car_Object;
import objects.Document_Object;

public class ObjectSetter {

	GamePanel gp;
	
	public ObjectSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	

	public void setObject()
	{
		
		gp.obj[9] = new Car_Object(gp);
		gp.obj[9].worldX = 27 * gp.TileSize;
		gp.obj[9].worldY = 0* gp.TileSize ;
		
		Random r = new Random();
		int rand = r.nextInt(3) + 1;
		
		switch (rand) {
		
		case 1:
			
			gp.obj[0] = new Document_Object(gp);
			gp.obj[0].worldX = 27 * gp.TileSize;
			gp.obj[0].worldY = 6 * gp.TileSize ;
			
			gp.obj[1] = new Document_Object(gp);
			gp.obj[1].worldX = 4 * gp.TileSize;
			gp.obj[1].worldY = 2 * gp.TileSize ;
			
			gp.obj[2] = new Document_Object(gp);
			gp.obj[2].worldX = 13 * gp.TileSize;
			gp.obj[2].worldY = 24 * gp.TileSize ;
			
			gp.obj[3] = new Document_Object(gp);
			gp.obj[3].worldX = 24 * gp.TileSize;
			gp.obj[3].worldY = 22 * gp.TileSize ;
		
			gp.obj[4] = new Document_Object(gp);
			gp.obj[4].worldX = 1 * gp.TileSize;
			gp.obj[4].worldY = 25 * gp.TileSize ;
			
			gp.obj[5] = new Document_Object(gp);
			gp.obj[5].worldX = 21 * gp.TileSize;
			gp.obj[5].worldY = 4 * gp.TileSize ;
			
			
			break;
			
		case 2:
			
			gp.obj[0] = new Document_Object(gp);
			gp.obj[0].worldX = 9 * gp.TileSize;
			gp.obj[0].worldY = 24 * gp.TileSize ;
			
			gp.obj[1] = new Document_Object(gp);
			gp.obj[1].worldX = 1 * gp.TileSize;
			gp.obj[1].worldY = 1 * gp.TileSize ;
			
			gp.obj[2] = new Document_Object(gp);
			gp.obj[2].worldX = 11 * gp.TileSize;
			gp.obj[2].worldY = 0 * gp.TileSize ;
			
			gp.obj[3] = new Document_Object(gp);
			gp.obj[3].worldX = 20 * gp.TileSize;
			gp.obj[3].worldY = 18 * gp.TileSize ;
			
			gp.obj[4] = new Document_Object(gp);
			gp.obj[4].worldX = 9 * gp.TileSize;
			gp.obj[4].worldY = 27 * gp.TileSize ;
			
			gp.obj[5] = new Document_Object(gp);
			gp.obj[5].worldX = 27 * gp.TileSize;
			gp.obj[5].worldY = 23 * gp.TileSize ;
			
			
			break;
			
		case 3:
			
			gp.obj[0] = new Document_Object(gp);
			gp.obj[0].worldX = 4 * gp.TileSize;
			gp.obj[0].worldY = 23 * gp.TileSize ;
			
			gp.obj[1] = new Document_Object(gp);
			gp.obj[1].worldX = 15 * gp.TileSize;
			gp.obj[1].worldY = 24 * gp.TileSize ;
			
			gp.obj[2] = new Document_Object(gp);
			gp.obj[2].worldX = 20 * gp.TileSize;
			gp.obj[2].worldY = 22 * gp.TileSize ;
			
			gp.obj[3] = new Document_Object(gp);
			gp.obj[3].worldX = 16 * gp.TileSize;
			gp.obj[3].worldY = 4 * gp.TileSize ;
			
			gp.obj[4] = new Document_Object(gp);
			gp.obj[4].worldX = 11 * gp.TileSize;
			gp.obj[4].worldY = 5 * gp.TileSize ;
			
			gp.obj[5] = new Document_Object(gp);
			gp.obj[5].worldX = 4 * gp.TileSize;
			gp.obj[5].worldY = 3 * gp.TileSize ;
			
			
			break;
			
	
		}	
		}
	
	
	
	
	public void setNPC()
	{
		gp.npc[0] = new NPC(gp);
		gp.npc[0].worldX = gp.TileSize * 4;
		gp.npc[0].worldY = gp.TileSize * 14;
		
		gp.npc[1] = new NPC(gp);
		gp.npc[1].worldX = gp.TileSize * 25;
		gp.npc[1].worldY = gp.TileSize * 17;
		
		gp.npc[2] = new NPC(gp);
		gp.npc[2].worldX = gp.TileSize * 18;
		gp.npc[2].worldY = gp.TileSize * 22;
		
		gp.npc[3] = new NPC(gp);
		gp.npc[3].worldX = gp.TileSize * 10;
		gp.npc[3].worldY = gp.TileSize * 4;
		
		gp.npc[4] = new NPC(gp);
		gp.npc[4].worldX = gp.TileSize * 23;
		gp.npc[4].worldY = gp.TileSize * 5;
		
		gp.npc[5] = new NPC(gp);
		gp.npc[5].worldX = gp.TileSize * 18;
		gp.npc[5].worldY = gp.TileSize * 4;
		
		gp.npc[5] = new NPC(gp);
		gp.npc[5].worldX = gp.TileSize * 7;
		gp.npc[5].worldY = gp.TileSize * 24;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
