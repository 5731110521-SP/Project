package entity;

import java.awt.event.KeyEvent;

import character.Character;
import character.Kurosaki;
import character.Pikachu;
import character.Playable;
import character.Reborn;
import input.InputUtility;
import render.IRenderable;
import render.Login;
import render.RenderableHolder;
import render.Resource;
import sun.security.x509.IssuingDistributionPointExtension;

public class GameLogic {
	protected static Playable character1;
	protected static Playable character2;
	
	public GameLogic(int player1Choose,int  player2Choose){	
//Login.player1
		RenderableHolder.getInstance().getRenderableList().clear();
		
		character1 = (Playable) Login.player1.getCharacter()[player1Choose];
		RenderableHolder.getInstance().add(character1);
	
		character2 = (Playable) Login.player2.getCharacter()[player2Choose];
		RenderableHolder.getInstance().add(character2);
	
		StatusBar sb = new StatusBar((Character)character1, (Character)character2);
		RenderableHolder.getInstance().add(sb);
		//time
		Time t = new Time();
		RenderableHolder.getInstance().add(t);
	}
	
	//delete else && change KeyEvent
	
	public void logicUpdate(){
		if(InputUtility.getKeyPressed(Login.player1.getRight())){
			if(!((Character) character1).isLose() && Time.isPlay){
				((Character) character1).run(true);
//				Resource.run.play();
			}
		}else if(InputUtility.getKeyPressed(Login.player1.getLeft())){
			if(!((Character) character1).isLose() && Time.isPlay){
				((Character) character1).run(false);
//				Resource.run.play();
			}
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_S)){
			if(!((Character) character1).isLose() && Time.isPlay){
				((Character) character1).attack((Character)(character2));
				if((Character)character1 instanceof Reborn) Resource.rebornGun.play();
				else Resource.fencing.play();
			}
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_F)){
			if(!((Character) character1).isLose() && Time.isPlay){
				((Character) character1).shoot(((Character) character2));
				if((Character)character1 instanceof Kurosaki) Resource.fencing.play();
				else if((Character)character1 instanceof Reborn) Resource.rebornBomb.play();
				else if((Character)character1 instanceof Pikachu) Resource.pikaShoot.play();
			}
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_Z)){
			if(!character1.isLose() && Time.isPlay){
				character1.superAttack();
				if((Character)character1 instanceof Pikachu) Resource.pikachu2.play();
			}
		}
		
		if(InputUtility.getKeyPressed(Login.player1.getUp())){
			if(!((Character) character1).isLose()&& Time.isPlay){
				((Character) character1).jump();
				if((Character)character1 instanceof Pikachu)Resource.pikachu1.play();
				else Resource.jump.play();
			}
		}
		
		
		if(InputUtility.getKeyPressed(Login.player2.getRight())){
			if(!((Character) character2).isLose()&& Time.isPlay){
				((Character) character2).run(true);
//				Resource.run.play();
			}
		}else if(InputUtility.getKeyPressed(Login.player2.getLeft())){
			if(!((Character) character2).isLose()&& Time.isPlay){
				((Character) character2).run(false);
//				Resource.run.play();
			}
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_DOWN)){
			if(!((Character) character2).isLose()&& Time.isPlay){
				((Character)character2).attack((Character) character1);
				if((Character)character2 instanceof Reborn) Resource.rebornGun.play();
				else Resource.fencing.play();
			}
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_ENTER)){
			if(!((Character) character2).isLose()&& Time.isPlay){
				((Character) character2).shoot(((Character) character1));
				if((Character)character2 instanceof Kurosaki) Resource.fencing.play();
				else if((Character)character2 instanceof Reborn) Resource.rebornBomb.play();
				else if((Character)character2 instanceof Pikachu) Resource.pikaShoot.play();
			}
		}else if(InputUtility.getKeyPressed(KeyEvent.VK_BACK_SLASH)){
			if(!character2.isLose()&& Time.isPlay){
				character2.superAttack();
			}
		}
		
		if(InputUtility.getKeyPressed(Login.player2.getUp())){
			if(!((Character) character2).isLose()&& Time.isPlay){
				((Character) character2).jump();
				if((Character)character2 instanceof Pikachu)Resource.pikachu1.play();
				else Resource.jump.play();
			}
		}
		
		synchronized (RenderableHolder.getInstance().getRenderableList()) {
			for(IRenderable entity : RenderableHolder.getInstance().getRenderableList()){
				if(entity.isVisible()){
					entity.update();
				}
			}
		}
		
	}
	
	public static int getWinner(){
		if (((Character) character1).isLose() && ((Character) character2).isLose()) return 0;
		if(((Character) character1).isLose()) return 2;
		if(((Character) character2).isLose()) return 1;
		
		if(((Character) character1).getHealthPoint()>((Character) character2).getHealthPoint()) return 1;
		if(((Character) character2).getHealthPoint()>((Character) character1).getHealthPoint()) return 2;
		return 0;
	}
	
}
