package de.pictastic.singlePong;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.Timer;


public class Game extends JPanel implements KeyListener, ActionListener {
	
	private int height, width;
	//ball speed
	private Timer t = new Timer(5, this);
	private boolean first;
	
	private HashSet<String> keys = new HashSet<String>();
	
	// pad
	private final int SPEED = 1;
	private int padH = 10, padW = 40;
	private int PadX;
	private int inset = 10;
	
	// ball
	private double ballX, ballY, velX = 1, velY = 1, ballSize = 20;
	
	// score
	private int  score;
	
	public Game() {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		first = true;
		t.setInitialDelay(100);
		t.start();
		
	}
	
	protected void startPosition() {
		if (first) {
			PadX = width / 2 - padW / 2;
			ballX = width / 2 - ballSize / 2;
			ballY = height / 2 - ballSize / 2;
			first = false;
		}
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		height = getHeight();
		width = getWidth();

		// initial positioning
		startPosition();
		
		//  pad
		Rectangle2D Pad = new Rectangle(PadX, height - padH - inset, padW, padH);
		g2d.fill(Pad);
		
		
		// ball
		Ellipse2D ball = new Ellipse2D.Double(ballX, ballY, ballSize, ballSize);
		g2d.fill(ball);
		
		// scores
		String scoreB = "Score: " + new Integer(score).toString();
		g2d.drawString(scoreB, 10, height / 2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// side walls
		if (ballX < 0 || ballX > width - ballSize) {
			velX = -velX;
		}
		// top wall
		if (ballY < 0) {
			velY = -velY;
			
		}
		// down wall
		if (ballY - ballSize > height) {
			t.stop();
			MainFrame.panel=new Replay();
			Main.main(null);
			
		
		}
		//  pad
		else
		if (ballY + ballSize >= height - padH - inset && velY > 0)
			if (ballX + ballSize >= PadX && ballX <= PadX + padW) {
				velY = -velY;
				score++;
			}
				


		ballX += velX;
		ballY += velY;
		
		// pressed keys
		if (keys.size() == 1) {
			if (keys.contains("LEFT")) {
				PadX -= (PadX > 0) ? SPEED : 0;
			}
			else if (keys.contains("RIGHT")) {
				PadX += (PadX < width - padW) ? SPEED : 0;
			}
		}
	

		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_LEFT:
			keys.add("LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			keys.add("RIGHT");
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_LEFT:
			keys.remove("LEFT");
			break;
		case KeyEvent.VK_RIGHT:
			keys.remove("RIGHT");
			break;
		}
	}
}
