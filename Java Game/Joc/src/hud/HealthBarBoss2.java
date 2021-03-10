package hud;

import gfx.Assets;
import main.Handler;

import java.awt.*;

public class HealthBarBoss2 extends HealthBar {
    public HealthBarBoss2(Handler handler) {
        super(handler);
    }

    public void render(Graphics g, int x, int y)
    {
        //desenare bara viata pentru al doilea inamic
        g.drawImage(Assets.healthbar,x, y,handler.getWorld().getEntityManager().getBoss2().getHealth() / 3,22,null);
    }
}
