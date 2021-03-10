package gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet; //imaginea cu cadrele animatiilor

    public SpriteSheet(BufferedImage sheet)
    {
        this.sheet = sheet;
    }

    //creara unor subimagini
    public BufferedImage crop(int x, int y, int width, int height)
    {
        return sheet.getSubimage(x,y,width,height);
    }
}
