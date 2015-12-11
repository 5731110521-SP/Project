package render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import input.InputUtility;

public class Login extends JPanel{
	public Login() {
		super(new GridBagLayout());
		setPreferredSize(new Dimension(640, 480));
		setBackground(Color.BLACK);
		
		JLabel l = new JLabel("Enter Player1's name : ");
		l.setForeground(Color.WHITE);
		l.setFont(new Font("Tahoma", 0, 30));
		add(l);
		
		JTextField tf = new JTextField();
		tf.setPreferredSize(new Dimension(200, 50));
		tf.setFont(new Font("Tahoma", 0, 30));
		add(tf);
		
		
		addMouseListener(new MouseAdapter() {
			
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
			}
		});
	}
	
	public boolean update(){
		if(InputUtility.isLeftClickTriggered()){
			return true;
		}
		return false;
	}
}
