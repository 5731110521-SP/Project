import java.awt.Frame;

import javax.swing.JFrame;

import render.GameScreen;
import render.Home;
import render.Login;
import entity.GameLogic;

public class Main {
	private static GameLogic logic;
	private static JFrame frame;
	private static Home home;

	public static void main(String[] args) {
		frame = new JFrame("Get em' all");
		home = new Home();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		login();
		
		frame.getContentPane().add(home);
		
		while (true) {
			int next = home();
			if (next == 1) {
				choose();
				game();
			} else if (next == 2) {
				how();
			} else if(next==3){
				setting();
			}
		}

	}
	
	public static void login(){
		Login login = new Login();
		frame.getContentPane().add(login);
		frame.pack();
		login.requestFocus();
		login.setVisible(true);

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (login.update()) {
				login.setVisible(false);
				return;
			}
		}
	}

	public static int home() {
		home.requestFocus();
		home.setVisible(true);

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			home.repaint();
			int next = home.update();
			if (next != -1) {
				home.setVisible(false);
				return next;
			}
		}

	}
	
	public static void how(){
		
	}
	
	public static void setting(){
		
	}

	public static void choose(){
		
	}

	public static void game() {
		GameScreen screen = new GameScreen();
		logic = new GameLogic();
		frame.getContentPane().add(screen);
		frame.setVisible(true);
		frame.pack();
		screen.requestFocus();

		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
			screen.repaint();
			logic.logicUpdate();
			// if()
		}
	}
}
