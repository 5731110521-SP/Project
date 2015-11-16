package character;

import java.awt.Graphics2D;

import render.IRenderable;

public abstract class Character implements Playable{
	public int indexC;
	protected int attackPower;
	protected int defencePower;
	protected int healthPoint;
	protected int maxPower;
	protected boolean lose;
	protected int x,y,width,height;
	protected boolean isAttacked,isVisible;
	protected boolean flashing= false;
	
	public Character(int ap,int dp,int hp, int mp){
		attackPower = ap;
		defencePower = dp;
		healthPoint = hp;
		maxPower = mp;
		lose = false;
		isAttacked = false;
		isVisible = true;
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

	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}
	
	public boolean getFlashing(){
		return flashing;
	}
	
	public abstract void hitByEnemy();
	
	public abstract void transform();
	
	public abstract void run(boolean isRight);

	public abstract void jump();

	public abstract void attack(Character c);

	public void shoot(int attack) {
		// TODO Auto-generated method stub
	}
	
	public void attacked(int amount){
		healthPoint -= amount;
		isAttacked = true;
		flashing = true;
		hitByEnemy();
	}
	
	public boolean isLose(){
		if(healthPoint <= 0) return true;
		else return false;
	}
	
	public abstract void update();
	
}