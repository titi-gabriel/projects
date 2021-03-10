package state;
import gfx.Assets;
import main.Game;
import main.Handler;

import java.awt.*;

public class Boss1Defeated extends State {

    public Boss1Defeated(Handler handler)
    {
        super(handler);
    }

    @Override
    public void tick() {
        //pentru a trece la urmatorul State se va apasa <ENTER>
        if(handler.getKeyManager().enter)
            State.setState(new GameState2(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare fundal
        g.drawImage(Assets.gaaraBG2,0,0,1280,960,null);

    }
}
