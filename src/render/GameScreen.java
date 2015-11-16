package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

public class GameScreen extends JComponent{
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
		g.drawImage(Resource.bg[0],0,0,640,480,null);
//		g2d.setBackground(Color.WHITE);
//		g2d.clearRect(0, 0, 640, 480);
		
			for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
				if(entity.isVisible() && !entity.getFlashing()){
					entity.draw(g2d);
				}
			}
	}
}
