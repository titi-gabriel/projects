package hud;

import gfx.Assets;
import main.Handler;

import java.awt.*;

public class ChakraBarBoss2 extends ChakraBar {
    public ChakraBarBoss2(Handler handler) {
        super(handler);
    }

    public void render(Graphics g, int x, int y)
    {
        //desenare bara chakra pentru al doilea inamic
        g.drawImage(Assets.chakrabar, x, y,(int)handler.getWorld().getEntityManager().getBoss2().getChakra(),11,null);
    }
}
