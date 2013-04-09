package base;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Atom {
	
	protected Image image;
	
	public Atom(Image image) {
		this.image = image;
	}

	void setImage(Image image) {
		this.image = image;
	}

	public void render(int x, int y, Graphics g) {
		g.drawImage(image, x * 32, y * 32);
		
	}

}
