package mapgen;

import java.util.Random;

public class Rectangle {

	int x;
	int y;
	int width;
	int height;

	public Rectangle(int x, int y, int width, int height) {
		this.x = x; this.y = y; this.width = width; this.height = height;
	}
	
	public int getArea() { return width * height; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public int getX() { return x; }
	public int getY() { return y; }
	
	public Rectangle[] split() {
		Rectangle[] ret = new Rectangle[2];
		
		Random rand = new Random();
		
		// still need to make the splitting random
		
		if (rand.nextBoolean()) { // split horizontally
			ret[0] = new Rectangle(this.x, this.y, this.width, this.height / 2);
			ret[1] = new Rectangle(this.x, this.y + this.height / 2, this.width, this.height / 2);
		} else { // split vertically
			ret[0] = new Rectangle(this.x, this.y, this.width / 2, this.height);
			ret[1] = new Rectangle(this.x + this.width / 2, this.y, this.width / 2, this.height);
		}
		
		return ret;
	}
	
}
