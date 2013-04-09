package base;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Console {

	private ArrayList<String> log; //
	// private int logSize; // number of entries to keep in log (not used right now, but will implement later)
	private int width;
	private int height;
	private int posX; //
	private int posY;
	private int index; // index in log to start printing at
	private int maxLines; // maximum # of lines that can be displayed at once
	private int lineLength; // length of a line in the log
	private TrueTypeFont font;

	public Console(int width, int height, int logSize, int posX, int posY) {
		// this.logSize = logSize;
		this.width = width;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
		this.index = 0;

		// gonna leave these static for now
		this.maxLines = 11;
		this.lineLength = 51;

		this.font = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 10), false);

		this.log = new ArrayList<String>(1);
	}

	public void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(posX, posY, width, height);
		g.setColor(Color.white);
		g.setFont(this.font);

		int linesToPrint = maxLines;
		if (log.size() < maxLines)
			linesToPrint = log.size();

		int pos = 0;
		for (int i = index; i < index + linesToPrint; i++) {
			g.drawString(log.get(i), posX + 4, (posY + 3) + (13 * pos++));
		}
	}

	public void addMessage(String str) {
		StringBuffer sb = new StringBuffer(str);
		if (str.length() > lineLength) {
			int i = this.lineLength;
			while (sb.length() > lineLength) {
				while ((sb.charAt(i) != ' ' && i > 0) || i >= lineLength) {
					i--;
				}
				System.out.println("adding substring 0 to " + i);
				StringBuffer toAdd = new StringBuffer(sb.substring(0, i));
				this.log.add(toAdd.toString());
				System.out.println("added " + toAdd.toString());
				sb = new StringBuffer(sb.substring(i));
				System.out.println("new sb: " + sb.toString());
				i = sb.length() - 1;
				System.out.println(sb.length());
			}
			this.log.add(sb.toString());
		} else {
			this.log.add(str);
			System.out.println("added " + str);
		}
		if (this.log.size() > maxLines)
			index = this.log.size() - maxLines;
	}

	public void scrollUp() {
		if (index > 0)
			this.index--;
	}

	public void scrollDown() {
		if (this.index < this.log.size() - maxLines)
			this.index++;
	}

}
