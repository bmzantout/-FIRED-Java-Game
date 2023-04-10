package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardInput implements KeyListener {

	public boolean moveUP, moveDOWN, moveRIGHT, moveLEFT; 
	GamePanel gp;
	
	public KeyBoardInput(GamePanel gp)
	{
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//(NOT USED)//
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int input = e.getKeyCode();
		
		if (gp.game_state == gp.title_state)
		{
			if (input == KeyEvent.VK_W)
			{
				gp.GUI.commandNum--;
				
				if(gp.GUI.commandNum < 0)
				{
					gp.GUI.commandNum = 1;
				}
			}
			
			if (input == KeyEvent.VK_S)
			{
				gp.GUI.commandNum++;
				
				if(gp.GUI.commandNum > 1)
				{
					gp.GUI.commandNum = 0;
				}
			}
			
			if (input == KeyEvent.VK_ENTER)
			{
				if (gp.GUI.commandNum == 0)
				{
					gp.game_state = gp.play_state;
					gp.playMusic(0);
				}
				
				if (gp.GUI.commandNum == 1)
				{
					System.exit(0);
				}
			}
		}
		
		if (gp.game_state == gp.play_state)
		{
			
			if (input == KeyEvent.VK_W)
			{
				moveUP = true;
			}
			
			if (input == KeyEvent.VK_S)
			{
				moveDOWN = true;
			}
			
			if (input == KeyEvent.VK_A)
			{
				moveLEFT = true;
			}
			
			if (input == KeyEvent.VK_D)
			{
				moveRIGHT = true;
			}
		}
		
		if (gp.game_state == gp.GameOver_state)
		{
			/*if (input == KeyEvent.VK_W)
			{
				gp.GUI.commandNum--;
				
				if(gp.GUI.commandNum < 0)
				{
					gp.GUI.commandNum = 1;
				}
			}*/
			
			if (input == KeyEvent.VK_S)
			{
				//gp.GUI.commandNum++;
				
				//if(gp.GUI.commandNum > 1)
				//{
					gp.GUI.commandNum = 1;
				//}
			}
			
			if (input == KeyEvent.VK_ENTER)
			{
				/*if (gp.GUI.commandNum == 0)
				{
					gp.game_state = gp.title_state;
				}*/
				
			//	if (gp.GUI.commandNum == 1)
				//{
					System.exit(0);
				//}
			}
		}
		
		if (gp.game_state == gp.GameWon_state)
		{
			/*if (input == KeyEvent.VK_W)
			{
				gp.GUI.commandNum--;
				
				if(gp.GUI.commandNum < 0)
				{
					gp.GUI.commandNum = 1;
				}
			}*/
			
			if (input == KeyEvent.VK_S)
			{
				//gp.GUI.commandNum++;
				
			//	if(gp.GUI.commandNum > 1)
				//{
					gp.GUI.commandNum = 1;
				//}
			}
			
			if (input == KeyEvent.VK_ENTER)
			{
				/*if (gp.GUI.commandNum == 0)
				{
					gp.game_state = gp.play_state;
				}*/
				
				if (gp.GUI.commandNum == 1)
				{
					System.exit(0);;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
int input = e.getKeyCode();
		
		if (input == KeyEvent.VK_W)
		{
			moveUP = false;
		}
		
		if (input == KeyEvent.VK_S)
		{
			moveDOWN = false;
		}
		
		if (input == KeyEvent.VK_A)
		{
			moveLEFT = false;
		}
		
		if (input == KeyEvent.VK_D)
		{
			moveRIGHT = false;
		}
	}
}
