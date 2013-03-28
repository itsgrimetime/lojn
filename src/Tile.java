import org.newdawn.slick.Image;


public class Tile extends Atom {

	private boolean walkable;
	private boolean water;
	
	public Tile(int x, int y, Image image, boolean walkable) {
		super(x, y, image);
		this.walkable = walkable;
		this.water = false;	
	}
	
	boolean isWalkable() { return walkable; }
	
	void setWalkable(boolean walkable) { this.walkable = walkable; }

	void setWater(boolean water) { this.water = water; }
	
	public static boolean isTileVisible(Tile tile, Player player) {

		// this is broken
		
//		if (tile.getX() * tile.getX() + tile.getY() * tile.getY() <= player.getX() + (player.getVisRange() * player.getVisRange()) &&
//				) {
//			return true;
//		}
//		
//		for (int i = -this.getVisRange(); i <= this.getVisRange(); i++) {
//			for (int j = -this.getVisRange(); j <= this.getVisRange(); j++) {
//				if (i * i + j * j <= this.getVisRange() * this.getVisRange()) {
//					this.getMap().getTileArray().get(this.x + i).get(this.y + j).setExplored(true);
//				}
//			}
//		}
		
		return (Math.abs(player.x - tile.x) < player.getVisRange() && Math.abs(player.y - tile.y) < player.getVisRange());
	}	
}
