package entity;

import java.awt.event.KeyEvent;

import character.Character;
import character.Luffy;
import character.Pikachu;
import character.Playable;
import input.InputUtility;
import render.IRenderable;
import render.RenderableHolder;

public class GameLogic {
	protected Player player1,player2;
	protected Playable character1,character2;
	
	public GameLogic(){
		player1 = new Player(KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_UP);
		character1 = (Playable) player1.getCharacter()[0];
		RenderableHolder.getInstance().add(character1);
		
		player2 = new Player(KeyEvent.VK_A,KeyEvent.VK_D,KeyEvent.VK_W);
		character2 = (Playable) player2.getCharacter()[1];
		RenderableHolder.getInstance().add(character2);
	}
	
	public void logicUpdate(){
		//Pikachu
		if(InputUtility.getKeyPressed(KeyEvent.VK_RIGHT)){
			 ((Character) character1).run(true);
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_LEFT)){
			((Character) character1).run(false);
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_DOWN)){
			((Character) character1).attack((Character)(character2));
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_UP)){
			((Character) character1).jump();
		}
		
		//Luffy
		if(InputUtility.getKeyPressed(KeyEvent.VK_D)){
			((Character) character2).run(true);
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_A)){
			((Character) character2).run(false);
		}
		
		if(InputUtility.getKeyPressed(KeyEvent.VK_W)){
			((Character) character2).jump();
		}
		if(InputUtility.getKeyPressed(KeyEvent.VK_F)){
			((Character)character2).attack((Character) character1);
		}
		
		for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
			if(entity.isVisible()){
				entity.update();
			}
		}
		
	}
}
