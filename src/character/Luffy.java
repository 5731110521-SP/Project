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

public class Luffy extends Character implements IRenderable {

	public Luffy(int ap, int dp, int hp, Player player) {
		super(10, dp, 100);
		indexC = 1;
		width = 53;
		height = 51;
		x = 100;
		y = 373 - height;
		this.player = player;
		character = Resource.luffy.getSubimage(278, 40, 60, 58);

	}

	@Override
	public int getZ() {
		return 0;
	}
	
	public void picRunUpdate() {
		if (isJump || isAttack)
			return;
		character = Resource.luffy.getSubimage(380 + countPic[0] * 55, 43, 55, 50);
//		width = 55;
//		height = 50;
		countPic[0]++;
		if (countPic[0] == 8)
			countPic[0] = 0;

	}
	
	public void picJumpUpdate(){
		if (countPic[1] > 7)
			countPic[1] = 0;
		character = Resource.luffy.getSubimage(34 + countPic[1] * 47, 164, 47, 54);
//		width = 47;
//		height = 54;
		if (count == 1)
			countPic[1] = 1;
		else if (count == jumpMax + 1)
			countPic[1] = 3;
		else if (count == jumpMax + 2)
			countPic[1] = 4;
		else if (count == jumpMax * 2)
			countPic[1] = 7;
		else if (countPic[1] >= 7) {
			countPic[1] = 0;
		}
	}

	public void stand() {
		if (isRun || isJump || isAttack)
			return;
		character = Resource.luffy.getSubimage(278, 40, 53, 51);
//		width = 53;
//		height = 51;
		for (int a : countPic)
			a = 0;
	}

	public void picAttackUpdate() {
		if (!isAttack)
			return;
		character = Resource.luffy.getSubimage(39 + countPic[2] * 40, 228, 45, 55);
//		width = 45;
//		height = 51;
		if (countPic[2] == 0) {
			countPic[2] = 1;
		} else if (countPic[2] == 1) {
			countPic[2]++;
		} else if (countPic[2] > 1 && countPic[2] < 6) {
			character = Resource.luffy.getSubimage(132 + (countPic[2] - 2) * 109, 229, 109, 53);
			countPic[2]++;
//			width = 109;
//			height = 51;
		} else if (countPic[2] >= 6) {
			character = Resource.luffy.getSubimage(568, 229, 40, 55);
			countPic[2] = 0;
			width = 40;
			height = 51;
			isAttack = false;
			isDoubleAttack = false;
		}

	}

	public void picShootUpdate() {
		if (!isShoot)
			return;
		character = Resource.luffy.getSubimage(154, 618, 57, 53);
//		width=57;
//		height=53;
		countPic[5]++;
		if(countPic[5] == 1)
			character = Resource.luffy.getSubimage(278, 621, 56, 53);
		else if(countPic[5] ==2)
			character = Resource.luffy.getSubimage(400, 619, 55, 53);
		else if(countPic[5] == 3){
			character = Resource.luffy.getSubimage(532, 622, 49, 48);
			isShoot = false;
			countPic[5] = 0;
		}
	}

	public void picLoseUpdate() {
		if (!lose)
			return;
		if (countPic[3] < 3)
			character = Resource.luffy.getSubimage(261 + (countPic[3] * 56), 890, 56, 43);
		countPic[3]++;
		if (countPic[3] >= 3)
			character = Resource.luffy.getSubimage(261 + (2 * 56), 890, 56, 43);

	}

	@Override
	public void picSuperAttack() {
		if(getPowerCount() >= 4){
			if(countPic[4] == 0)
				character = Resource.luffy.getSubimage(42, 305, 37, 47);
			else if(countPic[4] == 1)
				character = Resource.luffy.getSubimage(88, 315, 45, 36);
			else if(countPic[4] == 2)
				character = Resource.luffy.getSubimage(143, 298, 97, 54);
			else if(countPic[4] == 3)
				character = Resource.luffy.getSubimage(253, 298, 93, 54);
			else if(countPic[4] == 4)
				character = Resource.luffy.getSubimage(357, 301, 99, 50);
			else if(countPic[4] == 5)
				character = Resource.luffy.getSubimage(471, 300, 95, 52);
			countPic[4]++;
			if(countPic[4] >= 6){
				countPic[4] = 0;
			}
		}
	}

}
