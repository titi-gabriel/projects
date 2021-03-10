package state;

import gfx.Assets;
import gfx.Text;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DieState extends State
{
    public DieState(Handler handler)
    {
        super(handler);
    }

    @Override
    public void tick() {
        //pentru a reveni la meniu se va apasa <ENTER>
        if(handler.getKeyManager().enter)
            State.setState(new MenuState(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare fundal
        g.drawImage(Assets.deathBG, 0,0,null);
    }
}
