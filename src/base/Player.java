package base;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Player extends GameObject {

	private String name;

	public Player(int x, int y, Image image, boolean alive, Map map, String name) {
		super(x, y, image, alive, map);
		this.name = name;
	}

	void moveUp() {
		if (this.getMap().getTileArray().get(x).get(y - 1).isWalkable())
			y--;
	}

	void moveDown() {
		if (this.getMap().getTileArray().get(x).get(y + 1).isWalkable())
			y++;
	}

	void moveLeft() {
		if (this.getMap().getTileArray().get(x - 1).get(y).isWalkable())
			x--;
	}

	void moveRight() {
		if (this.getMap().getTileArray().get(x + 1).get(y).isWalkable())
			x++;
	}

	void moveUpLeft() {
		if (this.getMap().getTileArray().get(x - 1).get(y - 1).isWalkable()) {
			x--;
			y--;
		}
	}

	void moveUpRight() {
		if (this.getMap().getTileArray().get(x + 1).get(y - 1).isWalkable()) {
			x++;
			y--;
		}
	}

	void moveDownLeft() {
		if (this.getMap().getTileArray().get(x - 1).get(y + 1).isWalkable()) {
			x--;
			y++;
		}
	}

	void moveDownRight() {
		if (this.getMap().getTileArray().get(x + 1).get(y + 1).isWalkable()) {
			x++;
			y++;
		}
	}

	void move(int x, int y) {
		if (x < this.x) {
			if (y < this.y) {
				moveUpLeft();
			} else if (y > this.y) {
				moveDownLeft();
			} else {
				moveLeft();
			}
		} else if (x > this.x) {
			if (y < this.y) {
				moveUpRight();
			} else if (y > this.y) {
				moveDownRight();
			} else {
				moveRight();
			}
		} else {
			if (y < this.y) {
				moveUp();
			} else if (y > this.y) {
				moveDown();
			}
		}
	}

	public void render(Graphics g) {
		int renderX = 0;
		int renderY = 0;
		int width = LegendOfJ.gameWindowWidth / 32; // Game window sizes in
													// tiles
		int height = LegendOfJ.gameWindowHeight / 32;
		int mWidth = getMap().getWidth();
		int mHeight = getMap().getHeight() - 1;

		if (this.x >= (mWidth - (width / 2))) {
			renderX = (width / 2) + ((width / 2) - (mWidth - this.x));
		} else if (this.x < width / 2) {
			renderX = this.x;
		} else {
			renderX = width / 2;
		}

		if (this.y >= (mHeight - (height / 2))) {
			renderY = (height / 2) + ((height / 2) - (mHeight - this.y));
		} else if (this.y < height / 2) {
			renderY = this.y;
		} else {
			renderY = height / 2;
		}

		if (this.map.getTileArray().get(this.x).get(this.y).isWater()) {
			g.drawImage(image, renderX * 32, renderY * 32, (renderX + 1) * 32,
					(renderY * 32) + 16, 0, 0, 32, 16);
		} else {
			g.drawImage(image, renderX * 32, renderY * 32);
		}
	}

	public void updateExploredTiles() {
		for (int i = -this.getVisRange(); i <= this.getVisRange(); i++) {
			for (int j = -this.getVisRange(); j <= this.getVisRange(); j++) {
				if (this.x + i < 0 || this.x + i >= this.getMap().getWidth()
						|| this.y + j < 0
						|| this.y + j >= this.getMap().getHeight()) {
					continue;
				} else {
					if (i * i + j * j <= this.getVisRange()
							* this.getVisRange()) {
						this.getMap().getTileArray().get(this.x + i)
								.get(this.y + j).setExplored(true);
					}
				}
			}
		}
	}

}
