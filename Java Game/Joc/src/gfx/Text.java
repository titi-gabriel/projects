package gfx;

import java.awt.*;

public class Text
{
    //desenarea pe ecran a textului
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font)
    {
        g.setColor(c); //setare culoare text
        g.setFont(font); //setare font text
        //pozitia textului pe ecran
        int x = xPos;
        int y = yPos;

        //desenarea textului considerand coordonatele date ca fiind centrul textului
        if(center)
        {
            FontMetrics fm = g.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        //desenare text
        g.drawString(text,x,y);
    }
}
