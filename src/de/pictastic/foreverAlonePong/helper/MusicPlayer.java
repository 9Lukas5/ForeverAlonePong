package de.pictastic.foreverAlonePong.helper;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import sun.audio.AudioStream;

public class MusicPlayer {

	private static AudioInputStream audioInputStream;
	private static Clip clip;
	private static boolean mute = false;

	public static void playMusicContinously(String path) {
	if (!mute) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
			

		} catch (Exception e) { 
			System.out.println("Wiedergabe fehlgeschlagen");
	
		}
	}
	}
	
	public static void playMusic(String path) {
		if (!mute) {
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File(path));
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				

			} catch (Exception e) { 
				e.printStackTrace();
				System.out.println("Wiedergabe fehlgeschlagen");
		
			}
		}
	}

	
	public static void stopMusic() {
		clip.close();
	}
	
	public static void disableSound() {
		clip.close();
		mute = true;
	}
	public static void enableSound() {
		mute = false;
	}
	
}


