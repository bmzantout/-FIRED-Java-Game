package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffects {
	
	
	Clip clip;
	URL [] SoundEffects_URL = new URL [25];
	
	public SoundEffects()
	{
		SoundEffects_URL[0] = getClass().getResource("/soundEffects/MainMusic.wav");
		SoundEffects_URL[1] = getClass().getResource("/soundEffects/Congrats.wav");
		SoundEffects_URL[2] = getClass().getResource("/soundEffects/Ooops.wav");
		SoundEffects_URL[3] = getClass().getResource("/soundEffects/Document.wav");
		SoundEffects_URL[4] = getClass().getResource("/soundEffects/HitMonster.wav");
		SoundEffects_URL[5] = getClass().getResource("/soundEffects/GameOver.wav");
		SoundEffects_URL[6] = getClass().getResource("/soundEffects/GameWon.wav");

	}
	
	
	
	public void setFile(int i)
	{
		try
		{
			AudioInputStream audio = AudioSystem.getAudioInputStream(SoundEffects_URL[i]);
			clip = AudioSystem.getClip();
			clip.open(audio);
		}
		catch (Exception e){}
	}
	
	
	public void play()
	{
		clip.start();
	}
	
	
	public void loop()
	{
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop()
	{
		clip.stop();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
