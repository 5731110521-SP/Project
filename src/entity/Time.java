package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.w3c.dom.NamedNodeMap;

import character.Character;
import character.Naruto;
import render.GameScreen;
import render.IRenderable;
import render.Login;
import render.Resource;

public class Time implements IRenderable {
	public static boolean isPlay, isend, ishasWinner;
	private boolean isUpdate;
	private double startTime;
	private int timeEnd, time, state;
	private String winner;
	private BufferedImage image;
	private static int playeri;

	static {
		isPlay = false;
		isend = false;
	}

	public Time() {
		state = 0;
		startTime = System.nanoTime() / 1000000000;
		timeEnd = 120;
		winner = "";
		time = timeEnd;
		isPlay = false;
		isend = false;
		ishasWinner = false;
		isUpdate = false;
	}

	@Override
	public void draw(Graphics2D g) {

		if(state>=1 && state<=4 || state>=timeEnd+5 && state<=timeEnd+15){
			g.drawImage(Resource.center, 0, 0, null);
		}
		
		g.setFont(new Font("Tahoma", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		FontMetrics fm = g.getFontMetrics();
		Rectangle2D r2 = fm.getStringBounds("3", g);
		if (state == 1)
			g.drawString("3", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		else if (state == 2)
			g.drawString("2", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		else if (state == 3)
			g.drawString("1", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		else if (state == 4) {
			r2 = fm.getStringBounds("Start", g);
			g.drawString("Start", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if (state == timeEnd + 5) {
			r2 = fm.getStringBounds("Time out", g);
			g.drawString("Time out", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if ((state >= timeEnd + 6 && state <= timeEnd + 8) || (state >= timeEnd + 12 && state <= timeEnd + 14)) {
			r2 = fm.getStringBounds(winner, g);
			g.drawString(winner, GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if (state == timeEnd + 11) {
			r2 = fm.getStringBounds("KO", g);
			g.drawString("KO", GameScreen.width / 2 - (int) r2.getWidth() / 2, GameScreen.height / 2);
		} else if (state == timeEnd + 16) {
//			g.drawImage(image, GameScreen.width / 2 - image.getWidth(), GameScreen.height / 2 - image.getHeight(),
//					null);
			g.drawImage(image, 0, 0,null);
		}
		g.drawImage(Resource.timePoint, (int)(165+295-((time*1.0)/timeEnd)*295), 5, null);
//		r2 = fm.getStringBounds(Integer.toString(time), g);
//		g.drawString(Integer.toString(time), GameScreen.width / 2 - (int) r2.getWidth() / 2, 40);

	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public void update() {
		if ((((Character) GameLogic.character[0]).isSuperAttack()
				|| ((Character) GameLogic.character[1]).isSuperAttack()) && !isPlay) {
			if (((Character) GameLogic.character[0]).isSuperAttack()) {
				image = Resource.pic[((Character) GameLogic.character[0]).indexC];
				playeri = 0;
				image = Resource.ss[2];

			} else {
				playeri = 1;
				image = Resource.pic[((Character) GameLogic.character[1]).indexC];
				image = Resource.ss[2];

			}
			if (!isUpdate) {
				int prevState = state;
				state = timeEnd + 16;
				isUpdate = true;
				new Thread(new Runnable() {

					@Override
					public void run() {
						synchronized (Login.player[playeri]) {
							try {
								Login.player[playeri].wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						isPlay = true;
						isUpdate = false;
						state = prevState;
					}
				}).start();
			}
			return;
		}

		double lastTime = System.nanoTime() / 1000000000;
		if (lastTime - startTime >= 1 && !isend) {
			state++;
			startTime = lastTime;
			if (state <= timeEnd + 4 && state >= 5 && !ishasWinner)
				time--;
		}
		if (ishasWinner) {
			isPlay = false;
			if (state < timeEnd + 4)
				state = timeEnd + 10;
			else if (state == timeEnd + 11)
				getWinnerAn();
			else if (state == timeEnd + 15)
				isend = true;
		} else if (state < 4) {
			isPlay = false;
		} else if (state < timeEnd + 4) {
			isPlay = true;
		} else if (state == timeEnd + 4) {
			isPlay = false;
			getWinnerAn();
		} else if (state == timeEnd + 9) {
			isend = true;
		}
	}

	@Override
	public boolean getFlashing() {
		// TODO Auto-generated method stub
		return false;
	}

	// player[]
	public void getWinnerAn() {
		int i = GameLogic.getWinner();
		if (i == 1) {
			winner = "The winner is " + Login.player[0].getName();
		} else if (i == 2) {
			winner = "The winner is " + Login.player[1].getName();
		} else {
			winner = "Draw";
		}
	}

}
