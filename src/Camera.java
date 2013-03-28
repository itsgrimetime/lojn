import org.newdawn.slick.Graphics;


public class Camera {

	private Map map;
	private Player player;
	private int width; // width of game window in 32x32 tiles
	private int height; // height of game window in 32x32 tiles
	 
	
	// 
	public Camera(Map map, Player player, int width, int height) {
		this.map = map; this.player = player;
		this.width = width; this.height = height;
	}
	
	void render(Graphics g) {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (getXTile() + x >= map.getTileArray().size() || 
						getYTile() + y >= map.getTileArray().get(x).size()) {
					continue;
				} else {
					Tile tile = map.getTileArray().get(getXTile() + x).get(getYTile() + y);
					if (Tile.isTileVisible(tile, player)) {
						tile.render(x, y, g);
					} else {
						if (tile.isExplored()) {
							tile.render(x, y, g);
							tile.renderFog(x, y, g);
						} else {
							continue;
						}
					}
				}
			}
		}
		player.render(g);
	}
	
	int getYTile() {
		int mHeight = map.getHeight();
		int y = player.getY();
		
		if (y < height / 2) { // Player is on top half of camera
			return 0;
		} else if (y >= mHeight - (height / 2)) { // Player is in bottom half 
			return mHeight - height;
		} else { // Player is in between
			return y - height / 2;
		}
	}
	
	int getXTile() { 
		int mWidth = map.getWidth();
		int x = player.getX();
		
		if (x < width / 2) {
			return 0;
		} else if (x > mWidth - (width / 2)) {
			return mWidth - width;
		} else {
			return x - width / 2;
		}
	}
			
}
