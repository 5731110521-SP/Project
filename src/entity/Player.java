package entity;

import java.awt.event.KeyEvent;

import render.IRenderable;
import character.Kurosaki;
import character.Luffy;
import character.Naruto;
import character.Natsu;
import character.Pikachu;
import character.Reborn;
import logic.Name;

public class Player {
	private int level,player;
	private String name;
	private IRenderable[] character = new IRenderable[6];
	
	public Player(int player,String name){
		this.player=player;	
		this.name=name;
		level = Name.findName(name);
		
		Pikachu pikachu = new Pikachu(player,this);
		character[0] = pikachu;
		Luffy luffy = new Luffy(player,this);
		character[1] = luffy;
		Naruto naruto = new Naruto(player,this);
		character[2] = naruto;
		Reborn reborn = new Reborn(player,this);
		character[3] = reborn;
		Natsu natsu = new Natsu(player,this);
		character[4] = natsu;
		Kurosaki kuro = new Kurosaki(player,this);
		character[5] = kuro;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public IRenderable[] getCharacter() {
		return character;
	}
	
}
