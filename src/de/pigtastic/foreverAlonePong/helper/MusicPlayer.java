package de.pigtastic.foreverAlonePong.helper;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

	private static AudioInputStream audioInputStream;
	private static Clip clip;
	private static boolean mute = false;

	public static void playMusicContinously(URL path) {
	if (!mute) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path.toString()));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			

		} catch (Exception e) { 
			System.out.println("Wiedergabe fehlgeschlagen");
	
		}
	}
	}
	
	public static void playMusic(URL path) {
		if (!mute) {
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(path.toString()));
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				

			} catch (Exception e) { 
				System.out.println("Wiedergabe fehlgeschlagen");
		
			}
		}
	}

	
	public static void stopMusic() {
		try
		{
			clip.close();
		}
		catch (Exception e)
		{
			System.err.println("music bug^^");
		}
	}
	
	public static void disableSound() {
		try
		{
			clip.close();
		}
		catch (Exception e)
		{
			System.err.println("music bug^^");
		}
		mute = true;
	}
	public static void enableSound() {
		mute = false;
	}
	
}


