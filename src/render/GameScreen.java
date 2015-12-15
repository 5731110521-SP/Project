package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;


public class GameScreen extends JComponent{
	public static int width;
	public static int height;
	public static int y;
	private static int indexBg;
	static{
		width = 640;
		height = 480;
		indexBg = (int)(Math.random()*(Resource.bg.length-1));
		if(indexBg == 0)	y = 430;
		else if(indexBg == 1)	y = 420;
		else if(indexBg == 2)	y = 380;
		else if(indexBg == 3)	y = 415;
		else if(indexBg == 4)	y = 430;
		else if(indexBg == 5)	y = 350;
		else if(indexBg == 6)	y = 350;
		else if(indexBg == 7)	y = 430;
		else if(indexBg == 8)	y = 350;
		else if(indexBg == 9)	y = 400;
		else if(indexBg == 10)	y = 415;
		else if(indexBg == 11)	y = 370;
	}
	public GameScreen(){
		super();
		this.setPreferredSize(new Dimension(640,480));
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		
		this.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), false);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				InputUtility.setKeyPressed(e.getKeyCode(), true);
			}
		});
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//Background
		g2d.drawImage(Resource.bg[indexBg],0,0,640,480,null);
//		g2d.setBackground(Color.WHITE);
//		g2d.clearRect(0, 0, 640, 480);
		synchronized (RenderableHolder.getInstance().getRenderableList()) {
			for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
				if(entity.isVisible() && !entity.getFlashing()){
					entity.draw(g2d);
				}
			}
		}
	}
}
