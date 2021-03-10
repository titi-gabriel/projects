package state;
import gfx.Assets;
import main.Game;
import main.Handler;

import java.awt.*;

public class LoadingState extends State {

    public static float loading = 0;
    public LoadingState(Handler handler)
    {
        super(handler);
    }

    //metoda pentru simularea unui loading
    public static float loading() {
        loading += 10;
        return loading;
    }
    @Override
    public void tick() {
        if (loading == 1200) {
            //cand s-a ajuns la finalul "incarcarii" se trece la MenuState
            State.setState(new MenuState(handler));
        }
    }

    @Override
    public void render(Graphics g) {
        //desenare fundal
        g.drawImage(Assets.loadingBG, 0, 0,null);
        //desenare bara de loading
        g.setColor(Color.CYAN);
        g.fillRect(40, 900, (int)loading(), 20);
        g.setColor(Color.black);
        g.drawRect(40, 900, 1200, 20);;
    }
}
