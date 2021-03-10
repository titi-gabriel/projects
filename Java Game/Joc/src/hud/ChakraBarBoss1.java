package hud;

import gfx.Assets;
import main.Handler;

import java.awt.*;

public class ChakraBarBoss1 extends ChakraBar {
    public ChakraBarBoss1(Handler handler) {
        super(handler);
    }

    public void render(Graphics g, int x, int y)
    {
        //desenare bara chakra pentru primul inamic
        g.drawImage(Assets.chakrabar, x, y,(int)handler.getWorld().getEntityManager().getBoss1().getChakra(),11,null);
    }
}
