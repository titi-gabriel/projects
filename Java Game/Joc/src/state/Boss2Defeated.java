package state;

import gfx.Assets;
import gfx.Text;
import main.Game;
import main.Handler;

import java.awt.*;

public class Boss2Defeated extends State {

    public Boss2Defeated(Handler handler)
    {
        super(handler);
    }

    @Override
    public void tick() {
        //pentru a trece la urmatorul State se va apasa <ENTER>
        if(handler.getKeyManager().enter)
            State.setState(new MenuState(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare fundal
        g.drawImage(Assets.foxBG2,0,0,1280,960,null);
        //desenare scor
        Text.drawString(g, " "+Game.getScore(), 290, 879, false, Color.WHITE, Assets.fontScore);

    }
}
