package main;

import characters.Characters;

public class Collision {
	
	GamePanel gp;
	
	public Collision(GamePanel gp)
	{
		this.gp = gp;
		
	}

	 public void Check_Tile_Collision(Characters c)
	{
		int char_worldX_LEFT = c.worldX + c.solidArea.x;
		int char_worldX_RIGHT = c.worldX + c.solidArea.x + c.solidArea.width;
		int char_worldY_TOP  = c.worldY + c.solidArea.y;
		int char_worldY_BOTTOM  = c.worldY + c.solidArea.y + c.solidArea.height;
		
		int char_column_LEFT = char_worldX_LEFT / gp.TileSize;
		int char_column_RIGHT = char_worldX_RIGHT / gp.TileSize;
		int char_row_TOP = char_worldY_TOP / gp.TileSize;
		int char_row_BOTTOM = char_worldY_BOTTOM / gp.TileSize;
		
		int tile1, tile2;
		try {
		switch (c.direction)
		{
		case "up" : 
			char_row_TOP = (char_worldY_TOP - c.speed) / gp.TileSize;
			tile1 = gp.tileM.tile_MAP[char_column_LEFT][char_row_TOP];
			tile2 = gp.tileM.tile_MAP[char_column_RIGHT][char_row_TOP];
			
			if (gp.tileM.tiles[tile1].collide == true || gp.tileM.tiles[tile2].collide == true )
			{
				c.coll = true;
			}
			
			break;
			
		case "down" : 
			char_row_BOTTOM = (char_worldY_BOTTOM + c.speed) / gp.TileSize;
			tile1 = gp.tileM.tile_MAP[char_column_LEFT][char_row_BOTTOM];
			tile2 = gp.tileM.tile_MAP[char_column_RIGHT][char_row_BOTTOM];
			
			if (gp.tileM.tiles[tile1].collide == true || gp.tileM.tiles[tile2].collide == true )
			{
				c.coll = true;
			}
			
			break;
			
		case "right" : 
			char_column_RIGHT = (char_worldX_RIGHT + c.speed) / gp.TileSize;
			tile1 = gp.tileM.tile_MAP[char_column_RIGHT][char_row_TOP];
			tile2 = gp.tileM.tile_MAP[char_column_RIGHT][char_row_BOTTOM];
			
			if (gp.tileM.tiles[tile1].collide == true || gp.tileM.tiles[tile2].collide == true )
			{
				c.coll = true;
			}
			
			break;
			
		case "left" : 
			char_column_LEFT = (char_worldX_LEFT - c.speed) / gp.TileSize;
			tile1 = gp.tileM.tile_MAP[char_column_LEFT][char_row_TOP];
			tile2 = gp.tileM.tile_MAP[char_column_LEFT][char_row_BOTTOM];
			
			if (gp.tileM.tiles[tile1].collide == true || gp.tileM.tiles[tile2].collide == true )
			{
				c.coll = true;
			}
			
			break;}
		}
		
		catch(ArrayIndexOutOfBoundsException e)
		{c.coll=true;}
		
		
	}


	public int Check_Object_Collision(Characters character, boolean player)
	{
		int index = 999;
		
		for (int i = 0; i<gp.obj.length ; i++)
	{
			if (gp.obj[i] != null)
			{
				//Get Character's solid area position 
		character.solidArea.x = character.worldX + character.solidArea.x;
		character.solidArea.y = character.worldY + character.solidArea.y;
		
		       //Get Object's solid are position
		gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
		gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
		
		switch (character.direction)
		{
		
		case "up":
			character.solidArea.y -= character.speed;
			if (character.solidArea.intersects(gp.obj[i].solidArea))
			{
				if (gp.obj[i].object_collision == true)
				{
					character.coll =  true;
				}
				
				if (player == true)
				{
					index = i;
				}
			}
			break;
			
		case "down":
			character.solidArea.y += character.speed;
			if (character.solidArea.intersects(gp.obj[i].solidArea))
			{
				if (gp.obj[i].object_collision == true)
				{
					character.coll =  true;
				}
				
				if (player == true)
				{
					index = i;
				}
			}
			break;
			
		case "right":
			character.solidArea.x += character.speed;
			if (character.solidArea.intersects(gp.obj[i].solidArea))
			{
				if (gp.obj[i].object_collision == true)
				{
					character.coll =  true;
				}
				
				if (player == true)
				{
					index = i;
				}
			}
			break;
			
		case "left":
			character.solidArea.x -= character.speed;
			if (character.solidArea.intersects(gp.obj[i].solidArea))
			{
				if (gp.obj[i].object_collision == true)
				{
					character.coll =  true;
				}
				
				if (player == true)
				{
					index = i;
				}
			}
			break;
		
		}
		
		character.solidArea.x = character.solidArea_default_X;
		character.solidArea.y = character.solidArea_default_Y;
		gp.obj[i].solidArea.x = gp.obj[i].solidArea_default_X;
		gp.obj[i].solidArea.y = gp.obj[i].solidArea_default_Y;


			}}
		
		return index;
	}
	
	
	
	public int Check_PlayertoNPC_Collision (Characters character, Characters[] target)
	{
		int index = 999;
		
		for (int i = 0; i<target.length ; i++)
	{
			if (target[i] != null)
			{
				//Get Character's solid area position 
		character.solidArea.x = character.worldX + character.solidArea.x;
		character.solidArea.y = character.worldY + character.solidArea.y;
		
		       //Get Object's solid are position
		target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
		target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
		
		switch (character.direction)
		{
		
		case "up":
			character.solidArea.y -= character.speed;
			if (character.solidArea.intersects(target[i].solidArea))
			{
					character.coll =  true;
					index = i;
			}
			break;
			
		case "down":
			character.solidArea.y += character.speed;
			if (character.solidArea.intersects(target[i].solidArea))
			{
                    character.coll =  true;
				    index = i;
			}
			break;
			
		case "right":
			character.solidArea.x += character.speed;
			if (character.solidArea.intersects(target[i].solidArea))
			{
					character.coll =  true;
                    index = i;
			}
			break;
			
		case "left":
			character.solidArea.x -= character.speed;
			if (character.solidArea.intersects(target[i].solidArea))
			{
                 character.coll =  true;
				 index = i;

			}
			break;
		
		}
		
		character.solidArea.x = character.solidArea_default_X;
		character.solidArea.y = character.solidArea_default_Y;
		target[i].solidArea.x = target[i].solidArea_default_X;
		target[i].solidArea.y = target[i].solidArea_default_Y;


			}}
		
		return index;
	}
	
	
	
	public void Check_NPCtoPlayer_Collsion(Characters character)
	{
		//Get Character's solid area position 
				character.solidArea.x = character.worldX + character.solidArea.x;
				character.solidArea.y = character.worldY + character.solidArea.y;
				
				       //Get Object's solid are position
				gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
				gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
				
				switch (character.direction)
				{
				
				case "up":
					character.solidArea.y -= character.speed;
					if (character.solidArea.intersects(gp.player.solidArea))
					{
							character.coll2 =  true;
							character.coll =  true;
					}
					break;
					
				case "down":
					character.solidArea.y += character.speed;
					if (character.solidArea.intersects(gp.player.solidArea))
					{
		                    character.coll2 =  true;
							character.coll =  true;
					}
					break;
					
				case "right":
					character.solidArea.x += character.speed;
					if (character.solidArea.intersects(gp.player.solidArea))
					{
							character.coll2 =  true;
							character.coll =  true;
					}
					break;
					
				case "left":
					character.solidArea.x -= character.speed;
					if (character.solidArea.intersects(gp.player.solidArea))
					{
		                 character.coll2 =  true;
						 character.coll =  true;

					}
					break;
				
				}
				
				character.solidArea.x = character.solidArea_default_X;
				character.solidArea.y = character.solidArea_default_Y;
				gp.player.solidArea.x = gp.player.solidArea_default_X;
				gp.player.solidArea.y = gp.player.solidArea_default_Y;
	}




























}






























