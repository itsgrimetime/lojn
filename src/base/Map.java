package base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Map {

	private int width;
	private int height;
	ArrayList<ArrayList<Tile>> map;

	public Map(String filename) {

		// all this static numbers stuff hurts my most of me

		Image[] wallImages = new Image[4];
		Image[] floorImages = new Image[3];

		for (int i = 0; i < wallImages.length; i++) {
			wallImages[i] = loadImage("images/brick0" + (i + 1) + ".png");
		}

		for (int i = 0; i < floorImages.length; i++) {
			floorImages[i] = loadImage("images/stone_floor0" + (i + 1) + ".png");
		}

		Image deepWaterImage = loadImage("images/deep_water.png");
		Image shallowWaterImage = loadImage("images/shallow_water.png");

		Scanner fileScanner = null;
		try {
			fileScanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			System.err.println("error loading map file");
			System.exit(-1);
			e.printStackTrace();
		}

		height = fileScanner.nextInt();
		System.out.printf("map height: %d\n", height);
		width = fileScanner.nextInt();
		System.out.printf("map width: %d\n", width);

		fileScanner.nextLine(); // eat up to next line

		map = new ArrayList<ArrayList<Tile>>();

		for (int x = 0; x < this.width; x++)
			// Initialize tile array
			map.add(x, new ArrayList<Tile>());

		Random rand = new Random(System.currentTimeMillis());

		// all this shit sucks really bad and I am not proud of it

		for (int y = 0; y < this.height; y++) {
			char[] mapRow = fileScanner.nextLine().toCharArray();
			for (int x = 0; x < this.width; x++) {
				Tile tile = new Tile(x, y, null, false);

                switch (mapRow[x]) {
                    case '.':
                        if (rand.nextDouble() <= .15) {
                            tile.setImage(floorImages[rand
                                    .nextInt(floorImages.length - 1) + 1]);
                        } else {
                            tile.setImage(floorImages[0]);
                        }
                        tile.setWalkable(true);
                        break;
                    case '#':
                        if (rand.nextDouble() <= .05) {
                            tile.setImage(wallImages[rand
                                    .nextInt(wallImages.length - 1) + 1]);
                        } else {
                            tile.setImage(wallImages[0]);
                        }
                        tile.setWalkable(false);
                        break;
                    case '~':
                        tile.setWalkable(true);
                        tile.setWater(true);
                        // tile.setDepth(Tile.type.DEEP);
                        tile.setImage(deepWaterImage);
                        break;
                    case 'w':
                        tile.setWalkable(true);
                        tile.setWater(true);
                        tile.setImage(shallowWaterImage);
                        break;
                    case 'm':
                        tile.setWalkable(true);
                        tile.setImage(floorImages[rand
                                .nextInt(wallImages.length - 2) + 1]);
                        break;
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

	ArrayList<ArrayList<Tile>> getTileArray() {
		return map;
	}

	int getWidth() {
		return width;
	}

	int getHeight() {
		return height;
	}

	/*
	 * Finds a valid spawn location on the map, to make sure the player doesn't
	 * spawn on walls
	 */
	public int[] getValidSpawnLocation() {
		int[] ret = new int[2];
		for (int i = 0; i < this.width; i++) {
			if (this.map.get(i).get(i).isWalkable()) {
				ret[0] = ret[1] = i;
				break;
			}
		}

		Random rand = new Random();
		do {
			ret[0] = rand.nextInt(this.width - 1);
			ret[1] = rand.nextInt(this.height - 1);
		} while (!(this.map.get(ret[0]).get(ret[1]).isWalkable()));
		return ret;
	}

}
