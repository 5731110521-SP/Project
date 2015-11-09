package entity;

import java.awt.event.KeyEvent;

import input.InputUtility;
import render.IRenderable;
import render.RenderableHolder;

public class GameLogic {
	protected Player player1,player2;
	protected Pikachu pikachu;
	protected Pikachu pika;
	
	public GameLogic(){
		player1 = new Player(KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_UP);
		pikachu = new Pikachu(0, 0, 0, 0,player1);
		RenderableHolder.getInstance().add(pikachu);
		
		player2 = new Player(KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_W);
		pika = new Pikachu(0, 0, 0, 0,player2);
		RenderableHolder.getInstance().add(pika);
	}
	
	public void logicUpdate(){
		if(InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)){
			 pikachu.run(true);
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_LEFT)){
			pikachu.run(false);
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_UP)){
			pikachu.jump();
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_D)){
			 pika.run(true);
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_A)){
			pika.run(false);
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_W)){
			pika.jump();
		}
		for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
			if(entity.isVisible()){
				entity.update();
			}
		}
		
	}
}
