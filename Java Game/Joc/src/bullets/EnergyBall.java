package bullets;

import gfx.Animation;
import gfx.Assets;
import main.Handler;

import java.awt.image.BufferedImage;

//acest atac se comporta exact ca atacul SandBall, avand diferente doar la animatie si hitbox
public class EnergyBall extends SandBall{
    public EnergyBall(Handler handler, float x, float y, int width, int height, float speed, boolean throwdirection) {
        super(handler, x, y, width, height, speed, throwdirection);
        this.animSandBall = new Animation(50, Assets.energyBall);
        boundsA.height = 60;
        boundsA.y = 5;
    }
}
