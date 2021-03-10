package hud;

import gfx.Assets;
import main.Handler;

import java.awt.*;

public class ChakraBar
{
    Handler handler;

    public ChakraBar(Handler handler)
    {
        this.handler = handler;

    }

    public void tick()
    {

    }

    public void render(Graphics g, int x, int y)
    {
        //desenare bara chakra
        g.drawImage(Assets.chakrabar, x, y,(int)handler.getWorld().getEntityManager().getPlayer().getChakra(),11,null);


    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
