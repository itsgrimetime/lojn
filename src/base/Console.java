package base;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

public class Console {

    private ArrayList<String> log; //
    private int logSize; // number of entries to keep in log
    private int width;
    private int height;
    private int posX; //
    private int posY;
    private int index; // index in log to start printing at
    private int maxLines; // maximum # of lines that can be displayed at once
    private int lineLength; // length of a line in the log
    private TrueTypeFont font;

    public Console(int width, int height, int logSize, int posX, int posY) {
        this.logSize = logSize;
        this.width = width;
        this.height = height;
        this.posX = posX;
        this.posY = posY;
        this.index = 0;

        // gonna leave these static for now
        this.maxLines = 11;
        this.lineLength = 55;

        this.font = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 10), false);

        this.log = new ArrayList<String>(1);
    }

    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(posX, posY, width, height);
        g.setColor(Color.white);
        g.setFont(this.font);

        int linesToPrint = maxLines;
        if (log.size() < maxLines) linesToPrint = log.size();

        int pos = 0;
        for (int i = index; i < index + linesToPrint; i++) {
            g.drawString(log.get(i), posX + 4, (posY + 3) + (13 * pos++));
        }
    }

    public void addMessage(String str) {
        StringBuffer sb = new StringBuffer(str);

        Date dNow = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("[HH:mm:ss]: ");
        String time = ft.format(dNow);

        // truncate lines to max line width size
        if (str.length() > lineLength) {
            int i = this.lineLength;
            while (sb.length() > lineLength) {
                while (sb.charAt(i) != ' ' && i > 0) {
                    i--;
                }

                String toAdd = ft.format(dNow) + sb.substring(0, i);
                this.log.add(toAdd);
                System.out.println("added " + toAdd);
                sb = new StringBuffer(sb.substring(i));
                i = sb.length() - 1;
                System.out.println(sb.length());
            }
            this.log.add(time + sb.toString());
        } else {
            this.log.add(time + str);
            System.out.println("added " + time + str);
        }
        if (this.log.size() > maxLines) index = this.log.size() - maxLines;
    }

    public void scrollUp() {
        if (index > 0) this.index--;
    }

    public void scrollDown() {
        if (this.index < this.log.size() - maxLines) this.index++;
    }

}
