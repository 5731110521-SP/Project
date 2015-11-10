package character;

import input.InputUtility;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import entity.Player;
import render.IRenderable;
import render.Resource;

public class Luffy extends Character
					implements IRenderable{
	BufferedImage luffy;
	private int x=100;
	private int y=300;
	private int width = 53;
	private int height = 51;
	private int[] countPic= new int[3];
	private int jumpMax = 10;
	private int count=1;
	private boolean isRun,isRight,isJump,isAttack;
	private Player player;
	public Luffy(int ap, int dp, int hp, int mp,Player player) {
		super(ap, dp, hp, mp);
		this.player = player;
		isAttack = false;
		isRun = false;
		isJump = false;
		luffy = Resource.luffy.getSubimage(278, 40, 60, 58);
		for(int a:countPic){
			a=0;
		}
		
	}
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(luffy,x,y,width, height,null);
	}

	@Override
	public boolean isVisible() {
		return true;
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
		}
	}

	@Override
	public void run(boolean isRight) {
		if(isAttack) return;
		isRun=true;
		this.isRight=isRight;
		if(isRight){
			x+=20;
		}
		else {
			x-=20;
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
			}else{
				count=1;
			}
		}
		
		//Change image
				if(isJump){
					if(countPic[1]>7){
						countPic[1] = 0;
					}
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
					for(int a :countPic){
						a=0;
					}
				}
				
				if(isAttack){
					luffy = Resource.luffy.getSubimage(39+countPic[2]*40, 231, 40, 50);
					width = 40;
					height = 50;
					if(countPic[2]==0) {
						countPic[2] =1;
					}else if(countPic[2]==1) {
						countPic[2]++;
					}else if(countPic[2]>1 && countPic[2]<6) {
						luffy = Resource.luffy.getSubimage(39+countPic[2]*40, 231, 40*2, 50);
						countPic[2]++;
						width = 80;
						height = 50;
					}else if(countPic[2]>=6){
						countPic[2] = 0;
						isAttack=false;
					}
				}
				
				
				transform();
	}
	@Override
	public void attack(Character c) {
		// TODO Auto-generated method stub
		isAttack = true;
	}

}
