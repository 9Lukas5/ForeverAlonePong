package de.pictastic.foreverAlonePong;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class Main {
public static JFrame main;
public static FrameCondition condition = new FrameCondition();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    try { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
	    } catch(Exception ignored){}
		main = new MainFrame();
	}
	

	


}
