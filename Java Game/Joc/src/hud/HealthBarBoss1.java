package hud;

import gfx.Assets;
import main.Handler;

import java.awt.*;

public class HealthBarBoss1 extends HealthBar {
    public HealthBarBoss1(Handler handler) {
        super(handler);
    }

    public void render(Graphics g, int x, int y)
    {
        //desenare bara viata pentru primul inamic
        g.drawImage(Assets.healthbar,x, y,(int)handler.getWorld().getEntityManager().getBoss1().getHealth() / 2,22,null);
    }
}
