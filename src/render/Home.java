package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class Home extends JComponent{
	private boolean isVisible;
	public Home(){
		super();
		isVisible=true;
		this.setPreferredSize(new Dimension(640,480));
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		this.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				InputUtility.mouseLeftRelease();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				InputUtility.mouseLeftDown();
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, 640, 480);
	}

	public boolean update() {
		if(InputUtility.isLeftClickTriggered()){
			isVisible=false;
			return true;
		}
		return false;
	}
	
	public boolean isVisible(){
		return isVisible;
	}

	
}
