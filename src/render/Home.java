package render;

import input.InputUtility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javafx.scene.image.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home extends JPanel{
	private boolean isVisible;
	private BufferedImage button;
	private int next;
	public Home(){
		super();
		next=-1;
		isVisible=true;
		this.setPreferredSize(new Dimension(640,480));
		this.setDoubleBuffered(true);
		this.setVisible(true);
		this.requestFocus();
		button = Resource.button[0];
		
		JPanel panel1 = new JPanel();
		JLabel label1 = new JLabel(new ImageIcon(button));
		panel1.add(label1);
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel(new ImageIcon(button));
		panel2.add(label2);
		
		panel1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				InputUtility.mouseLeftRelease();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				InputUtility.mouseLeftDown();
				next=1;
			}
		});
		
		panel2.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				InputUtility.mouseLeftRelease();
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				InputUtility.mouseLeftDown();
				next=2;
			}
		});
		
		this.add(panel1);
		this.add(panel2);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(Resource.bg[1], 0, 0, null);
//		g2d.drawImage(button, 220, 325, null);
//		g2d.setColor(ColResource.button[0]or.BLACK);
//		g2d.fillRect(0, 0, 640, 480);
	}

	public int update() {
		if(InputUtility.isLeftClickTriggered()){
			isVisible=false;
			return next;
		}
		return next;
	}
	
	public boolean isVisible(){
		return isVisible;
	}

	
}
