package render;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Font;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import character.Luffy;

public class Resource {
	public static final Font standardFont = new Font("Tahoma", Font.BOLD, 30);
	public static BufferedImage pikachu;
	public static BufferedImage luffy;
	public static AudioClip coinSound;
	
	static{
		try{
			ClassLoader loader = RenderableHolder.class.getClassLoader();
			pikachu = ImageIO.read(loader.getResource("res/p.gif"));
			luffy = ImageIO.read(loader.getResource("res/luffy.png"));
			System.out.println("try");
		}catch(Exception e){
			pikachu = null;
			coinSound = null;
			System.out.println("catch");
		}
	}
}