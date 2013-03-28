import org.newdawn.slick.Image;

public class GameObject extends Atom {

	private boolean alive;
	private int kills;
	private int id;
	protected Map map;
	private int visRange;
	
	public GameObject(int x, int y, Image image, boolean alive, Map map) {
		super(x, y, image);	this.alive = alive;
		this.kills = 0;
		this.map = map;
		this.visRange = 3;
	}

	int getId() { return id; }
	void setId(int id) { this.id = id; }
	
	boolean isAlive() { return alive; }
	void setAlive(boolean alive) { this.alive = alive; }
	
	void setVisRange(int visRange) { this.visRange = visRange; }
	int getVisRange() { return this.visRange; }
	
	void addKill() { kills++; }
	int getKills() { return kills; }
	
	Map getMap() { return map; }
	
}
