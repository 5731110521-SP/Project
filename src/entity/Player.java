package entity;

import java.awt.event.KeyEvent;

public class Player {
	
	private int level;
	private String name;
	private int left,right,up;
	
	public Player(int l,int r, int u){
		left = l;
		right = r;
		up = u;
	}
	
	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}

	public int getUp() {
		return up;
	}

	public void setUp(int up) {
		this.up = up;
	}

}
