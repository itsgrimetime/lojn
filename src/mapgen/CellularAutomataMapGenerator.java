package mapgen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;


public class CellularAutomataMapGenerator {
	private int[][] dirs = new int[][]{ {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}, {0, -1} };

	public CellularAutomataMapGenerator(int width, int height, int wallIters,
			int waterIters, double percWall, double percWater, String filename) {
		
		File file = new File(filename);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening file " + file.getName());
			e.printStackTrace();
			System.exit(-1);
		}
		
		char[][] tiles = new char[width][height];
		
		Random rand = new Random(System.currentTimeMillis());
		
		// Initialize map edges
		
		for(int i = 0; i < width; i++) {
			tiles[i][0] = '#';
			tiles[i][height - 1] = '#';
		}
		
		for (int i = 0; i < height; i++) {
			tiles[0][i] = '#';
			tiles[width - 1][i] = '#';
		}
		
		// randomly place walls in map
		
		for(int i = 1; i < width - 1; i++) {
			for (int j = 1; j < height - 1; j++) {
				double roll = rand.nextDouble();
				if (roll <= percWall) tiles[i][j] = '#';
				else tiles[i][j] = '.';
			}
		}
		
		System.out.println("width: " + width);
		System.out.println("height: " + height);
		// ## Debug - print map before automata are iterated
		
		// printMap(tiles);
		
		// Iterate automata
		
		for (int i = 0; i < wallIters; i++)  {
			iterateAutomata('#', '.', 5, tiles);
			// printMap(tiles);
		}
		addWater(percWater, tiles, width, height);
		// printMap(tiles);

		for (int i = 0; i < waterIters; i++) {
			spreadWater(4, tiles);
			// printMap(tiles);
		}
		
		// this hurts me deeply
		
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (tiles[i][j] == '~') {
					for (int[] vec : dirs) {
						if (tiles[i + vec[0]][j + vec[1]] == '.') {
//							tiles[i + vec[0]][j + vec[1]] = 'w';
							tiles[i][j] = 'w';
						}
					}
 				}
			}
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (tiles[i][j] == 'w') {
					for (int[] vec : dirs) {
						if (tiles[i + vec[0]][j + vec[1]] == '.') {
							tiles[i + vec[0]][j + vec[1]] = 'm';
						}
					}
 				}
			}
		}
		
		// print dimensions to map file
		
		pw.println(width);
		pw.println(height);
		
		// ## Debug - print generated map data
		
		printMap(tiles);
		
		// write map data to file
		
		for(int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pw.write(tiles[i][j]);
			}
			pw.print('\n');
		}
		
		pw.close();
	}
	
	@SuppressWarnings("unused")
	private void printMap(char[][] tiles) {
		for (int i = 0; i < tiles.length; i++) {
				System.out.println(tiles[i]);
			}
			System.out.print('\n');
	}

	private void addWater(double percWater, char[][] tiles, int width, int height) {
		Random rand = new Random(System.currentTimeMillis());
		for(int i = 1; i < width - 1; i++) {
			for (int j = 1; j < height - 1; j++) {
				if (tiles[i][j] == '.') {
					double roll = rand.nextDouble(); 		// 1 / n chance of being wall
					if (roll <= percWater) tiles[i][j] = '~';
				}
			}
		}
	}

	/* Runs an iteration of the cellular automata - if the number of similar
	 *  tiles surrounding the tile is greater than or equal to 
	 *  surroundingTiles, that tile becomes that type. 
	 */
	private static void iterateAutomata(char tile, char nonTile, int surroundingTiles, char[][] tiles) {
		for (int i = 1; i < tiles.length - 1; i++) {
			for (int j = 1; j < tiles[i].length - 1; j++) {
				if (areaTiles(tiles, tiles.length, tiles[i].length, 
						i, j, tile) >= surroundingTiles) {
					tiles[i][j] = tile;
				} else {
					if (!(nonTile == '\0')) {
						tiles[i][j] = nonTile;
					}
				}
			}
		}
	}
	
	private static void spreadWater(int surroundingTiles, char[][] tiles) {
		for (int i = 1; i < tiles.length - 1; i++) {
			for (int j = 1; j < tiles[i].length - 1; j++) {
				if (areaTiles(tiles, tiles.length, tiles[i].length, i, j, '~') >= surroundingTiles) {
					tiles[i][j] = '~';
				} else {
					if (tiles[i][j] == '~') tiles[i][j] = '.';
				}
			}
		}
	}
	
	private static int areaTiles(char[][] tiles, int width, int height, int x,
			int y, char tile) {
		int count = 0;
		
		if (x <= 0 || x >= width - 1) {
			if (y <= 0 || y >= height - 1) {
				count += 5;
			} else {
				count += 3;
			}
		} else if (y <= 0 || y >= height - 1) {
			count += 3;
		} else {
			for(int i = x - 1; i <= x + 1; i++) {
				for (int j = y - 1; j <= y + 1; j++) {
					if (tiles[i][j] == tile) count++;					
				}
			}
		}
		return count;
	}
	
}
