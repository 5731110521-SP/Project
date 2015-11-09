import javax.swing.JFrame;

import render.GameScreen;
import entity.GameLogic;

public class Main {
	public static void main(String[] args){
		JFrame frame = new JFrame("Get em' all");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameScreen screen = new GameScreen();
		frame.getContentPane().add(screen);
		frame.setVisible(true);
		frame.pack();
		GameLogic logic = new GameLogic();
		screen.requestFocus();
		
		while(true){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
			screen.repaint();
			logic.logicUpdate();
		}
	}
}
