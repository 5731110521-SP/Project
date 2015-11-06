package itf;

public class Character {
	private int attackPower;
	private int defencePower;
	private int healthPoint;
	private int maxPower;
	private boolean lose;
	
	public Character(int ap,int dp,int hp, int mp){
		attackPower = ap;
		defencePower = dp;
		healthPoint = hp;
		maxPower = mp;
		lose = false;
	}
	public void setLose(boolean lose) {
		this.lose = lose;
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
	
	public void transform() {
		// TODO Auto-generated method stub
		
	}
	
	public void walk() {
		// TODO Auto-generated method stub
		
	}

	public void jump() {
		// TODO Auto-generated method stub
		
	}

	public void attack(Character c) {
		// TODO Auto-generated method stub
		c.attacked(attackPower);
	}

	public void shoot(int attack) {
		// TODO Auto-generated method stub
	}
	
	public void attacked(int amount){
		healthPoint -= amount;
		if(isLose()) setLose(true);
	}
	
	public boolean isLose(){
		if(healthPoint <= 0) return true;
		else return false;
	}
	
	
}