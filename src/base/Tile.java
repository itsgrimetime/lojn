package base;


import items.Item;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Tile extends Atom {

	public enum type {
		
		WATER {
			
			
		},
		
		BASE {
			
			
		}

	};
	
	private int x, y;
	private boolean walkable;
	private boolean water;
	private type depth;
	private boolean explored;
	private ArrayList<GameObject> entities; // the entities on this tile
	private ArrayList<Item> items; // the entities on this tile
	
	public Tile(int x, int y, Image image, boolean walkable) {
		super(image);
		this.x = x;
		this.y = y;
		this.walkable = walkable;
		this.water = false;
	}

	void renderFog(int x, int y, Graphics g) {
		Color oldColor = g.getColor();
		g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.75f));
		g.fillRect(x * 32, y * 32, 32, 32);
		g.setColor(oldColor);
	}

	public boolean isExplored() {
		return this.explored;
	}

	public void setExplored(boolean explored) {
		this.explored = explored;
	}
	
	boolean isWalkable() {
		return walkable;
	}

	void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	void setWater(boolean water) {
		this.water = water;
	}
	
	void setDepth(int depth) {
		// this.depth = depth;
	}

	boolean isWater() {
		return water;
	}
	
	boolean isShallowWater() {
		// return this.depth == type.SHALLOW;
        return false;
	}
	
	public boolean isDeepWater() {
		// return this.depth == type.DEEP;
        return false;
	}

	public static boolean isTileVisible(Tile tile, GameObject go) {
		return ((Math.pow((tile.x - go.x), 2) + Math.pow((tile.y - go.y), 2) <= Math
				.pow(go.getVisRange(), 2)));
	}
}
