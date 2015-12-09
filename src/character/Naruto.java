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

public class Naruto extends Character
					implements IRenderable{
	BufferedImage naruto;
	private int[] countPic= new int[4];
	private int jumpMax = 10;
	private int count = 1;
	
	public Naruto(int ap, int dp, int hp,Player player) {
		super(20, 20, 100);
		indexC = 0;
		width = 53;
		height = 61;
		x=100;
		y=373-height;
		this.player = player;
		isAttack = false;
		isRun = false;
		isJump = false;
		isRight = true;
		countShoot = 5;
		naruto = Resource.naruto.getSubimage(0, 0, 53, 61);
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(naruto,x-xp,y-yp,width,height,null);
		xp=0;
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void hitByEnemy() {
		flashCounter = 5;
		flashDurationCounter = 0;
	}

	@Override
	public void transform() {
		AffineTransform at = new AffineTransform();
		if(!isRight){
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-naruto.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			naruto = op.filter(naruto, null);
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
	public void shoot(Character c) {
		this.enemy = c;
		if(countShoot >= 10){
			RenderableHolder.getInstance().add(new Shootable(this));
			countShoot=0;
			isShoot = true;
		}
	}

	@Override
	public void update() {
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
					if(countPic[1] < 3){
						if(countPic[1] == 0)
							naruto = Resource.naruto.getSubimage(1, 146, 42, 44);
						else if(countPic[1] == 1)
							naruto = Resource.naruto.getSubimage(110, 135, 41, 68);
						else if(countPic[1] == 2)
							naruto = Resource.naruto.getSubimage(163, 135, 51, 66);
						countPic[1]++;
						if(countPic[1] == 3) {
							countPic[1] = 0;
							isJump=false;
						}
					}
					if(count==1) countPic[1]=1;
					else if(count==jumpMax+1) countPic[1]=2;
					else if(count==jumpMax*2) countPic[1]=3;
					else if(countPic[1]>=3){
						countPic[1] = 0;
						isJump=false;
					}
				}else if(isRun){
					isAttack = false;
					if(countPic[0] == 0)
						naruto = Resource.naruto.getSubimage(0, 75, 50, 55);
					else if(countPic[0] == 1)
						naruto = Resource.naruto.getSubimage(55,78, 65, 51);
					else if(countPic[0] == 2)
						naruto = Resource.naruto.getSubimage(130,78, 54, 52);
					else if(countPic[0] == 3)
						naruto = Resource.naruto.getSubimage(193, 76,49, 52);
					else if(countPic[0] == 4)
						naruto = Resource.naruto.getSubimage(254, 76, 61,47);
					else if(countPic[0] == 5)
						naruto = Resource.naruto.getSubimage(320, 72, 56,56);
					countPic[0]++;
					if(countPic[0] == 6)countPic[0] = 0;
				}else{
					naruto = Resource.naruto.getSubimage(0, 0, 53, 61);
					countPic[0]=0;
				}
				if(isAttack){
					if(countPic[2] == 0)
						naruto = Resource.naruto.getSubimage(10, 375, 45, 61);
					else if(countPic[2] == 1)
						naruto = Resource.naruto.getSubimage(65, 377, 57, 55);
					else if(countPic[2] == 2)
						naruto = Resource.naruto.getSubimage(138,381, 59, 54);
					else if(countPic[2] == 3)
						naruto = Resource.naruto.getSubimage(209, 383, 58, 52);
					else if(countPic[2] == 4)
						naruto = Resource.naruto.getSubimage(274, 384, 51,53);
					if(isRight) x += 20;
					else x-=20;
					countPic[2]++;
					if(countPic[2]>=5) {
						naruto = Resource.naruto.getSubimage(0, 0, 53, 61);
						countPic[2] = 0;
						isAttack = false;
						isDoubleAttack = false;
					}
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

}
