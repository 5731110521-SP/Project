package entity;

import java.awt.Graphics2D;

import render.IRenderable;

public class Shootable implements IRenderable{
	public static int z=1;
	private int power;
	private Character shooter;
	private Character enemy;

	public Shootable(Character ch) {
		shooter = ch;
//		enemy = ch.
	}
	
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getFlashing() {
		// TODO Auto-generated method stub
		return false;
	}

}
