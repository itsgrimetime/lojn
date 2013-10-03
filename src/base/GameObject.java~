package base;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class GameObject extends Atom {

	protected int x, y;
	private boolean alive;
	private int kills;
	private int id;
	protected Map map;
	private int visRange;

	public GameObject(int x, int y, Image image, boolean alive, Map map) {
		super(image);
		this.x = x;
		this.y = y;
		this.alive = alive;
		this.kills = 0;
		this.map = map;
		this.visRange = 5;
	}
	
	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	boolean isAlive() {
		return alive;
	}

	void setAlive(boolean alive) {
		this.alive = alive;
	}

	void setVisRange(int visRange) {
		this.visRange = visRange;
	}

	int getVisRange() {
		return this.visRange;
	}

	void addKill() {
		kills++;
	}

	int getKills() {
		return kills;
	}

	Map getMap() {
		return map;
	}

	public void render(Graphics g) {
		super.render(this.x, this.y, g);
	}
	
}
