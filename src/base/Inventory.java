package base;

import items.Item;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Inventory {

	private Image inventorySlot;
	private Item[] items = new Item[16];
	private int selected;
	private int invX;
	private int invY;

	public Inventory(int x, int y) {
		this.invX = x;
		this.invY = y;
		try {
			this.inventorySlot = new Image("images/inv_slot.png");
		} catch (SlickException e) {
			System.err.println("Error opening inventory slot image file");
			e.printStackTrace();
		}
		
		// debug for testing drawing of weapons + items
		try {
			this.items[0] = new Item(new Image("images/broad_sword_blue.png"), "Broad Sword", 10, 5, null);
            this.items[1] = new Item(new Image("images/scroll01.png"), "Scroll of Shittyness", 10, 5, null);
		} catch (SlickException e) {
			System.err.println("Error loading item image");
			e.printStackTrace();
		}
	}

	public void dropItem(int slot) {
		
		items[slot] = null;
		
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < 16; i++) {
			g.drawImage(this.inventorySlot, this.invX + (i % 4) * 32, this.invY + (i / 4) * 32);
			
			Color oldColor = g.getColor();
			g.setColor(Color.green);
			if (this.selected == i) {
				g.drawRect(this.invX + (i % 4) * 32, this.invY + (i / 4) * 32, 31, 31);
			}
			g.setColor(oldColor);
			
			
			String slotString = null;
			
			if (i >= 0 && i <= 8) {
				slotString = Integer.toString(i + 1);
			} else if (i == 9) {
				slotString = "0";
			} else if (i > 9) {
				switch (i) {
					case 10: slotString = "t"; break;
					case 11: slotString = "y"; break;
					case 12: slotString = "u"; break;
					case 13: slotString = "i"; break;
					case 14: slotString = "o"; break;
					case 15: slotString = "p"; break;
				}
			}
			// not really sure why it won't render when I call item.render. oh well, this works for now.
			if (items[i] != null) {
//				items[i].render(this.invX + (i % 4), this.invY + (i / 4), g);
				g.drawImage(items[i].image, this.invX + (i % 4) * 32, this.invY + (i / 4) * 32);
			}
            g.drawString(slotString, this.invX + (i % 4) * 32 + 23, this.invY + (i / 4) * 32 + 20);
		}
	}

	public void selectSlot(int i) {
		this.selected = i;
	}
}
