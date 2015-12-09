package character;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entity.Player;
import entity.Shootable;
import render.IRenderable;
import render.RenderableHolder;
import render.Resource;

public class Pikachu extends Character
						implements IRenderable{
	BufferedImage pikachu;
	private int[] countPic= new int[4];
	private int jumpMax = 10;
	private int count = 1;

	public Pikachu(int ap, int dp, int hp,Player player) {
		super(10, dp, 100);
		indexC = 0;
		width = 25;
		height = 30;
		x=50;
		y=373-height;
		this.player = player;
		isAttack = false;
		isRun = false;
		isJump = false;
		isRight = true;
		countShoot = 5;
		pikachu = Resource.pikachu.getSubimage(102, 4, 25, 28);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(pikachu,x-xp,y-yp,width,height,null);
		xp=0;
	}
	
	public void update(){
//		System.out.println("pikachu"+healthPoint);
		//If release
		if(!InputUtility.getKeyPressed(player.getLeft()) && !InputUtility.getKeyPressed(player.getRight())){
			isRun=false;
		}
		
		//Change Y isJump
		if(isJump){
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
			isAttack = false;
			pikachu = Resource.pikachu.getSubimage(39+countPic[0]*25, 147, 25, 28);
			width = 25;
			height = 28;
			countPic[0]++;
			if(countPic[0]==6)countPic[0]=0;
		}else{
			pikachu = Resource.pikachu.getSubimage(102, 4, 25, 28);
			width = 25;
			height = 28;
			countPic[0]=0;
		}
		if(isAttack){
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
		if(lose){
			pikachu = Resource.pikachu.getSubimage(102, 4, 25, 28);
			AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(-90), width/2, height/2);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
			pikachu = op.filter(pikachu, null);
		}
		
		transform();	
		if(isAttack && collideWith(enemy) && !isDoubleAttack){;
			enemy.setAttacked(true);
			enemy.attacked(attackPower);
			isDoubleAttack = true;
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
		
		countShoot++;
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

	@Override
	public void shoot(Character c) {
		isShoot = true;
		this.enemy = c;
		if(countShoot >= 10){
			RenderableHolder.getInstance().add(new Shootable(this));
			countShoot=0;
		}
		
	}

}
