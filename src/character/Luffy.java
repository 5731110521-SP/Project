package character;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entity.Player;
import entity.Shootable;
import render.IRenderable;
import render.RenderableHolder;
import render.Resource;

public class Luffy extends Character
					implements IRenderable{
	BufferedImage luffy;
	private int[] countPic= new int[6];
	private int jumpMax = 10;
	private int count=1;
	
	public Luffy(int ap, int dp, int hp,Player player) {
		super(10, 5, 100);
		indexC = 1;
		width = 53;
		height = 51;
		x=100;
		y=373-height;
		this.player = player;
		isAttack = false;
		isRun = false;
		isJump = false;
		isRight = true;
		isShoot=false;
		countShoot=0;
		luffy = Resource.luffy.getSubimage(278, 40, 60, 58);
		for(int a:countPic)	a=0;
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(luffy,x-xp,y-yp,width, height,null);
		xp=0;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	@Override
	public void attack(Character c) {
		isAttack = true;
		this.enemy = c;
	}
	
	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void transform() {
		AffineTransform at = new AffineTransform();
		if(!isRight){
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-luffy.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			luffy = op.filter(luffy, null);
			if(width>53) xp=width-53;
		}
	}

	@Override
	public void jump() {
		isJump = true;
	}

	@Override
	public void update() {
		if(!InputUtility.getKeyPressed(player.getLeft()) && !InputUtility.getKeyPressed(player.getRight())){
			isRun=false;
		}
		
		if(isJump){
			if(count <= jumpMax) {
				y -= 20;
				count++;
			}else if(count > jumpMax && count <= jumpMax*2){
				y += 20;
				count++;
			}else count=1;
		}
		
		//Change image
		if(isJump){
			if(countPic[1]>7)	countPic[1] = 0;
			luffy = Resource.luffy.getSubimage(34+countPic[1]*47, 164, 47, 54);
			width = 47;
			height = 54;
			if(count==1) countPic[1]=1;
			else if(count==jumpMax+1) countPic[1]=3;
			else if(count==jumpMax+2) countPic[1]=4;
			else if(count==jumpMax*2) countPic[1]=7;
			else if(countPic[1]>=7){
				countPic[1] = 0;
				isJump=false;
			}
					
		}else if(isRun){
			isAttack = false;
			luffy = Resource.luffy.getSubimage(380+countPic[0]*55, 43, 55, 50);
			width = 55;
			height = 50;
			countPic[0]++;
			if(countPic[0]==8)countPic[0]=0;
		}else{
			luffy = Resource.luffy.getSubimage(278, 40, 53, 51);
			width = 53;
			height = 51;
			for(int a :countPic)	a=0;
		}
		
		if(isAttack){
			luffy = Resource.luffy.getSubimage(39+countPic[2]*40, 228, 45, 55);
			width = 45;
			height = 51;
			if(countPic[2]==0) {
				countPic[2] =1;
			}else if(countPic[2]==1) {
				countPic[2]++;
			}else if(countPic[2]>1 && countPic[2]<6) {
				luffy = Resource.luffy.getSubimage(132+(countPic[2]-2)*109, 229, 109, 53);
				countPic[2]++;
				width = 109;
				height = 51;
			}else if(countPic[2]>=6){
				luffy = Resource.luffy.getSubimage(568, 229, 40, 55);
				countPic[2]=0;
				width=40;
				height=51;
				isAttack=false;
				isDoubleAttack = false;
			}
		}
		
		if(lose){
			if(countPic[3] <3) 
				luffy = Resource.luffy.getSubimage(261+(countPic[3]*56), 890, 56, 43);
			countPic[3]++;
			if(countPic[3] >= 3) 
				luffy = Resource.luffy.getSubimage(261+(2*56), 890, 56, 43);
		}
		
		if(isShoot){
			luffy = Resource.luffy.getSubimage(154, 618, 57, 53);
			width=57;
			height=53;
			countPic[5]++;
			if(countPic[5] == 1)
				luffy = Resource.luffy.getSubimage(278, 621, 56, 53);
			else if(countPic[5] ==2)
				luffy = Resource.luffy.getSubimage(400, 619, 55, 53);
			else if(countPic[5] == 3){
				luffy = Resource.luffy.getSubimage(532, 622, 49, 48);
				isShoot = false;
				countPic[5] = 0;
			}
		}
		countShoot++;
		
		transform();
		if(isAttack && collideWith(enemy) && !isDoubleAttack){
			enemy.setAttacked(true);
			enemy.attacked(attackPower);
			isDoubleAttack=true;
			powerCount++;
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
	public void hitByEnemy() {
		flashCounter = 5;
		flashDurationCounter = 0;
	}

	@Override
	public void shoot(Character c) {
		this.enemy = c;
		if(countShoot >= 10){
			RenderableHolder.getInstance().add(new Shootable(this));
			countShoot=0;
			isShoot = true;
		}
	}

}
