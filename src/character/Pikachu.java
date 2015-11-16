package character;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javafx.beans.binding.SetBinding;
import entity.Player;
import render.IRenderable;
import render.Resource;

public class Pikachu extends Character
						implements IRenderable{
	BufferedImage pikachu;
	private int[] countPic= new int[3];
	private int jumpMax = 10;
	private int count = 1;
	private int flashCounter,flashDurationCounter;
	private int width = 25,height = 28;
	private int xp=0,yp=0;
	private boolean isRun,isRight,isJump,isAttack,isDoubleAttack;
	private Player player;
	private Character enemy;

	public Pikachu(int ap, int dp, int hp, int mp,Player player) {
		super(10, dp, 100, mp);
		x=50;
		y=320;
		this.player = player;
		isAttack = false;
		isRun = false;
		isJump = false;
		isRight = true;
		pikachu = Resource.pikachu.getSubimage(102, 4, 25, 28);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(pikachu,x-xp,y-yp,width,height,null);
		xp=0;
	}
	
	public boolean collideWith(Character ch){
		System.out.println(ch);
		System.out.println((x+width/2)+" "+(ch.x+ch.width/2)+" asd "+(width/2+ch.width));
		if( Math.hypot((x+width/2)-(ch.x+ch.width/2),y-ch.y) <= width/2+ch.width){
			return true;
		}
		return false;
	}
	
	public void update(){
		System.out.println(healthPoint);
		//If release
		if(!InputUtility.getKeyPressed(player.getLeft()) && !InputUtility.getKeyPressed(player.getRight())){
			isRun=false;
			System.out.println("stop");
		}
		
		//Change Y isJump
		if(isJump){
			System.out.println("jump");
			if(count <= jumpMax) {
				y -= 20;
				count++;
			}else if(count > jumpMax && count <= jumpMax*2){
				y += 20;
				count++;
			}else{
				count=1;
			}
		}
		
		//Change image
		if(isJump){
			System.out.println("jump");
			if(countPic[1]>3){
				countPic[1] = 0;
			}
			pikachu = Resource.pikachu.getSubimage(64+countPic[1]*26, 110, 26, 32);
			width = 26;
			height = 32;
			if(count==1) countPic[1]=1;
			else if(count==jumpMax+1) countPic[1]=2;
			else if(count==jumpMax*2) countPic[1]=3;
			else if(countPic[1]>=3){
				countPic[1] = 0;
				isJump=false;
			}
			
		}else if(isRun){
			System.out.println("run");
			isAttack = false;
			pikachu = Resource.pikachu.getSubimage(39+countPic[0]*25, 147, 25, 28);
			width = 25;
			height = 28;
			countPic[0]++;
			if(countPic[0]==6)countPic[0]=0;
		}else{
			System.out.println("stand");
			pikachu = Resource.pikachu.getSubimage(102, 4, 25, 28);
			width = 25;
			height = 28;
			countPic[0]=0;
		}
		if(isAttack){
			System.out.println("attack");
			pikachu = Resource.pikachu.getSubimage(1+countPic[2]*28, 181, 28, 25);
			width = 28;
			height = 25;
			if(isRight) x += 20;
			else x-=20;
			countPic[2]++;
			if(countPic[2]>=7) {
				pikachu = Resource.pikachu.getSubimage(102, 4, 25, 28);
				width = 25;
				height = 28;
				countPic[2] = 0;
				isAttack = false;
				isDoubleAttack = false;
			}
		}
		transform();	
		if(isAttack && collideWith(enemy) && !isDoubleAttack){
			System.out.println("attack2");
			enemy.setAttacked(true);
			enemy.attacked(attackPower);
			isDoubleAttack = true;
		}
		
		if(isAttacked && flashing && flashDurationCounter%2==0){
			flashing = false;
			flashDurationCounter++;
		}else if(isAttacked && !flashing && flashDurationCounter%2==1){
			flashing = true;
			flashDurationCounter++;
		}
		if(isAttacked && flashDurationCounter==flashCounter){
			flashing = false;
			isAttacked = false;
			flashDurationCounter = 0;
		}
	
	}

	@Override
	public int getZ() {
		return 0;
	}
	
	public void run(boolean isRight){
		if(isAttack) return;
		isRun=true;
		this.isRight=isRight;
		if(isRight)	x+=20;
		else x-=20;
	}

	@Override
	public void transform() {
		AffineTransform at = new AffineTransform();
		if(!isRight){
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-pikachu.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			pikachu = op.filter(pikachu, null);
			if(width>25) xp=width-25;
		}
	}

	@Override
	public void jump() {
		isJump = true;
	}

	@Override
	public void attack(Character c) {
		isAttack = true;
		this.enemy = c;
	}

	@Override
	public void hitByEnemy() {
		flashCounter = 5;
		flashDurationCounter = 0;
	}

}
