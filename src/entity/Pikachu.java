package entity;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import render.IRenderable;
import render.Resource;

public class Pikachu extends Character
						implements IRenderable{
	BufferedImage pikachu;
	private int x=100;
	private int y=100;
	private int countPic=0;
	private int jumpMax = 10;
	private int count=1;
	private boolean isRun;
	private boolean isRight;
	private boolean isJump;
	private Player player;

	public Pikachu(int ap, int dp, int hp, int mp,Player player) {
		super(ap, dp, hp, mp);
		this.player = player;
		pikachu = Resource.pikachu.getSubimage(102, 4, 25, 28);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.drawImage(pikachu,x,y,25,28,null);
		
	}
	
	public void update(){
		//If release
		if(!InputUtility.getKeyPressed(player.getLeft()) && !InputUtility.getKeyPressed(player.getRight())){
			isRun=false;
		}
		
		//Change Y isJump
		if(isJump){
			if(count <= jumpMax) {
				y -= 10;
				count++;
			}else if(count > jumpMax && count <= jumpMax*2){
				y += 10;
				count++;
			}else{
				count=1;
			}
		}
		
		//Change image
		if(isJump){
			pikachu = Resource.pikachu.getSubimage(64+countPic*26, 110, 26, 32);
			if(count==1) countPic=1;
			else if(count==jumpMax+1) countPic=2;
			else if(count==jumpMax*2) countPic=3;
			else if(countPic==3){
				countPic = 0;
				isJump=false;
			}
			
		}else if(isRun){
			pikachu = Resource.pikachu.getSubimage(39+countPic*25, 147, 25, 28);
			countPic++;
			if(countPic==6)countPic=0;
		}else{
			pikachu = Resource.pikachu.getSubimage(102, 4, 25, 28);
			countPic=0;
		}
		
		//Tranform isLeft
		AffineTransform at = new AffineTransform();
		if(!isRight){
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-pikachu.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			pikachu = op.filter(pikachu, null);
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void run(boolean isRight){
		isRun=true;
		this.isRight=isRight;
		if(isRight){
			x+=10;
		}
		else {
			x-=10;
		}
	}

	@Override
	public void transform() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump() {
		isJump = true;
	}

}
