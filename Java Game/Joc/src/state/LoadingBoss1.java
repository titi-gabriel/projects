package state;

import gfx.Assets;
import main.Handler;

import java.awt.*;

public class LoadingBoss1 extends State {

    public LoadingBoss1(Handler handler)
    {
        super(handler);
    }

    @Override
    public void tick() {
        //pentru a trece la urmatorul State se va apasa <ENTER>
        if(handler.getKeyManager().enter)
            State.setState(new GameStateBoss1(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare fundal
        g.drawImage(Assets.gaaraBG1,0,0,1280,960,null);

    }
}
