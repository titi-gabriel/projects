package hud;

import gfx.Assets;
import gfx.Text;
import items.Item;
import main.Handler;

import java.awt.*;
import java.util.ArrayList;

public class HealthBar
{
    Handler handler;

    public HealthBar(Handler handler)
    {
        this.handler = handler;

    }

    public void tick()
    {

    }

    public void render(Graphics g, int x, int y)
    {
        //desenare bara viata
        g.drawImage(Assets.healthbar,x, y,(int)handler.getWorld().getEntityManager().getPlayer().getHealth(),22,null);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
