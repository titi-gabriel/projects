package state;

import gfx.Assets;
import gfx.Text;
import main.Game;
import main.Handler;
import ui.ClickListener;
import ui.UIImageButton;
import ui.UIManager;

import java.awt.*;

public class MenuState extends State
{
    private UIManager uiManager1; //manager de butoane

    public MenuState(Handler handler)
    {
        super(handler);
        uiManager1 = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager1);


        //adaugare buton
        uiManager1.addObject(new UIImageButton(155, 288, 260, 130, Assets.startButton, new ClickListener() {
            @Override
            public void onClick() {
                //la apasarea butonului, managerul se va face null pentru a evita erorile cand se intra pentru o a doua
                //oara in meniu si se seteaza Stateul la GameState
                handler.getMouseManager().setUiManager(null);
                State.setState(new GameState(handler));
            }
        }));
        uiManager1.addObject(new UIImageButton(155, 453, 260, 130, Assets.helpButton, new ClickListener() {
            @Override
            public void onClick() {
                //la apasarea butonului, managerul se va face null pentru a evita erorile cand se intra pentru o a doua
                //oara in meniu si se seteaza Stateul la HelpState
                handler.getMouseManager().setUiManager(null);
                State.setState(new HelpState(handler));
            }
        }));
    }

    @Override
    public void tick() {

        //actualizare manager
         uiManager1.tick();
    }

    @Override
    public void render(Graphics g) {
        //desenare fundal meniu si butoane
        g.drawImage(Assets.menuBG,0,0,1280,960,null);
        uiManager1.render(g);
    }
}
