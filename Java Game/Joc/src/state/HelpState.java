package state;

import gfx.Assets;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;

public class HelpState extends State {


    public HelpState(Handler handler)
    {
        super(handler);

    }

    @Override
    public void tick() {
        //pentru a reveni la meniu se va apasa <ESC>
        if(handler.getKeyManager().esc)
            State.setState(new MenuState(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare fundal
        g.drawImage(Assets.helpBG,0,0,1280,960,null);

    }
}
