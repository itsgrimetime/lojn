package base;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class MiniMap {
	
	int x;
	int y;
	Map map;
	Player player;
	
	public MiniMap(int x, int y, Map map, Player player) {
		this.x = x; this.y = y; this.map = map;
		this.player = player;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(x, y, 192, 128);
		
		for (int i = 0; i < map.getWidth(); i++) {
			for (int j = 0; j < map.getHeight(); j++) {
				if (!map.getTileArray().get(i).get(j).isWalkable()) {
					g.setColor(Color.gray);
				} else if (map.getTileArray().get(i).get(j).isWater()){
					g.setColor(Color.blue);
				} else {
					g.setColor(Color.black);
				}
				g.fillRect(x + (3 * i), y + (3 * j), 3, 3);
			}
		}
        g.setColor(Color.white);
        g.fillRect(x + player.getX() * 3, y + player.getY() * 3, 3, 3);
	}

}
