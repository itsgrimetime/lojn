import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Atom {

	protected int x, y;
	protected Image image;
	private boolean visible;
	private boolean explored;
	
	public Atom(int x, int y, Image image) {
		this.x = x; this.y = y; this.image = image;
		this.explored = false;
		this.visible = false;
	}
		
	void setImage(Image image) { this.image = image; }
	
	void setX(int x) { this.x = x; }
	void setY(int y) { this.y = y; }
	
	int getX() { return x; }
	int getY() { return y; }
	
	void render(int x, int y, Graphics g) {
		g.drawImage(image, x * 32, y * 32);
	}
	
	public boolean isVisible() {
		return this.visible;
	}
	
	public boolean isExplored() {
		return this.explored;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	
	void renderFog(int x, int y, Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
		g.fillRect(x * 32, y * 32, 32, 32);
		g.setColor(oldColor);
	}
	
}
