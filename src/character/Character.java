package character;

import java.awt.Graphics2D;

import com.sun.org.apache.xml.internal.security.utils.HelperNodeList;

import entity.Player;
import render.IRenderable;

public abstract class Character implements Playable {

	public int getPowerCount() {
		return powerCount;
	}

	public int indexC;
	protected int attackPower;
	protected int defencePower;
	protected int healthPoint;
	protected int powerCount;
	protected boolean lose;
	protected int x, y, width, height;
	protected int xp = 0, yp = 0;
	protected boolean isAttacked, isVisible;
	protected boolean flashing = false;
	protected int flashCounter, flashDurationCounter, counter,countShoot;
	protected boolean isRun, isRight, isJump, isAttack, isDoubleAttack,
			isShoot;
	protected Player player;
	protected Character enemy;

	public Character(int ap, int dp, int hp) {
		attackPower = ap;
		defencePower = dp;
		healthPoint = hp;
		powerCount=0;
		lose = false;
		isAttacked = false;
		isVisible = true;
	}
	
	public boolean isRight() {
		return isRight;
	}

	public Character getEnemy() {
		return enemy;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getXp() {
		return xp;
	}

	public int getYp() {
		return yp;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setAttacked(boolean isAttacked) {
		this.isAttacked = isAttacked;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}

	public int getDefencePower() {
		return defencePower;
	}

	public void setDefencePower(int defencePower) {
		this.defencePower = defencePower;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public boolean getFlashing() {
		return flashing;
	}

	public abstract void hitByEnemy();

	public abstract void transform();

	public void run(boolean isRight){
		if(isAttack) return;
		isRun=true;
		this.isRight=isRight;
		if(isRight)	{
			x+=20;
			if(x>=640-width) x = 640-width;
		}
		else  {
			x-=20;
			if(x<=0) x = 0;
		}
	}

	public abstract void jump();

	public abstract void attack(Character c);

	public abstract void shoot(Character c);

	public void attacked(int amount) {
		amount = amount-defencePower;
		if(amount<=0) amount=1;
		healthPoint -= amount;
		if (healthPoint == 0)
			lose = true;
		if (healthPoint < 0)
			healthPoint = 0;
		isAttacked = true;
		flashing = true;
		hitByEnemy();
	}
	
	public boolean collideWith(Character ch){
		if(Math.abs((x-xp+width/2.0)-(ch.x-ch.xp+ch.width/2.0)) <= width/2.0+ch.width/2.0 
				&& Math.abs((y-yp+height/2.0)-(ch.y-ch.yp+ch.height/2.0)) <= height/2.0+ch.height/2.0){
			return true;
		}
		return false;
	}

	public boolean isLose() {
		if (healthPoint <= 0)
			return true;
		else
			return false;
	}

	public abstract void update();

}