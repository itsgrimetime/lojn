package items;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import base.Atom;

public class Item extends Atom {

	private String name;
	private int baseDamage;
	private ArrayList<Enchantment> enchants;
	private ArrayList<Curse> curses;
	private int weight;
	private WeaponType type;
	
	public Item(Image image, String name, int baseDamage, int weight, WeaponType type) {
		super(image);
		this.name = name; this.baseDamage = baseDamage;
		this.weight = weight; this.type = type;
	}
	
}
