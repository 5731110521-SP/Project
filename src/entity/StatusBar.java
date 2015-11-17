package entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import render.IRenderable;
import render.Resource;

public class StatusBar implements IRenderable{
	private character.Character c1,c2;
	
	public StatusBar(character.Character c1, character.Character c2){
		this.c1 = c1;
		this.c2 = c2;
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
		//HpBar 290x21
		g.setBackground(Color.RED);
		g.clearRect(9, 50, (int)(c1.getHealthPoint()/100.0*290), 21);
		g.clearRect(341+290-(int)(c2.getHealthPoint()/100.0*290), 50, (int)(c2.getHealthPoint()/100.0*290), 21);
		
		//CharacterPic 100x35
		BufferedImage c1Pic = Resource.pic[c1.indexC];
		g.drawImage(c1Pic,9,9,100, 35,null);
		
		BufferedImage c2Pic = Resource.pic[c2.indexC];
		AffineTransform at = new AffineTransform();
			at = AffineTransform.getScaleInstance(-1, 1);
			at.translate(-c2Pic.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			c2Pic = op.filter(c2Pic, null);
		g.drawImage(c2Pic,531,9,100, 35,null);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 99999;
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
