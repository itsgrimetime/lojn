import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map {

	private int width;
	private int height;
	ArrayList<ArrayList<Tile>> map;
	
	public Map(String filename) {

		Image wallImage = loadImage("images/brick01.png");
		Image floorImage = loadImage("images/stone_floor01.png");
		Image waterImage = loadImage("images/water01.png");
		
		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.err.println("error loading map file");
			System.exit(-1);
			e.printStackTrace();
		}
				
		width = fileScanner.nextInt();
		System.out.printf("map width: %d\n", width);
		height = fileScanner.nextInt();
		System.out.printf("map height: %d\n", height);
		
		fileScanner.nextLine(); // eat up to next line
		
		map = new ArrayList<ArrayList<Tile>>();
		
		for (int x = 0; x < this.width; x++) // Initialize tile array
			map.add(x, new ArrayList<Tile>());
		
		for (int y = 0; y < this.height; y++) {
			char[] mapRow = fileScanner.nextLine().toCharArray();
			for (int x = 0; x < this.width; x++) {
				Tile tile = new Tile(x, y, null, false);
				if (mapRow[x] == '.') {
					tile.setImage(floorImage);
					tile.setWalkable(true);
				} else if (mapRow[x] == '#'){
					tile.setImage(wallImage);
				} else if (mapRow[x] == '~') {
					tile.setWalkable(true);
					tile.setWater(true);
					tile.setImage(waterImage);
				}
				map.get(x).add(y, tile);
			}
		}
		fileScanner.close();
	}
	
	private Image loadImage(String filename) {
		Image image = null;
		try {
			image = new Image(filename);
		} catch (SlickException e) {
			System.err.println("Error loading image " + filename);
			System.exit(-1);
			e.printStackTrace();
		}
		return image;
	}

	ArrayList<ArrayList<Tile>> getTileArray() { return map; }
	
	int getWidth() { return width; }
	int getHeight() { return height; }
	
	/*
	 * Finds a valid spawn location on the map, to make sure the player
	 * doesn't spawn on walls
	 */
	public int[] getValidSpawnLocation() {
		int[] ret = new int[2];
		for (int i = 0; i < this.width; i++) {
			if (this.map.get(i).get(i).isWalkable()) {
				ret[0] = ret[1] = i;
				break;
			}
		}
		return ret;
	}
	
}
