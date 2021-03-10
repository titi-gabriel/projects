package entities.statics;

import gfx.Animation;
import gfx.Assets;
import main.Handler;
import state.GameState2;
import state.LoadingBoss1;
import state.LoadingBoss2;
import state.State;
import tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

//clasa pentru portal(statuie)
//distrugand statuia vom trece la urmatorul nivel
public class Portal extends StaticEntity {

    private Animation animStatue; //animatie

    public Portal(Handler handler, float x, float y)
    {
        super(handler,x,y, 160, 160);
        animStatue = new Animation(75, Assets.statue); //initializare animatie
        //hitbox statuie
        bounds.x = 0;
        bounds.y = 0;
        bounds.height = 160;
        bounds.width = 160;
        health = 150; //viata
    }

    @Override
    public void tick() {
        animStatue.tick();
    } //actualizare

    @Override
    public void render(Graphics g) {
        //desenare pe frameuri
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
    }

    public void die() //metoda pentru schimbarea nivelului atunci cand statuia este distrusa
    {
        if(State.level == 1) //daca suntem la primul nivel, atunci vom trece la nivelul cu primul inamic
            State.setState(new LoadingBoss1(handler)); //setam Stateul la un nou nivel
        if(State.level == 2) //daca suntem la al doilea nivel, atunci vom trece la nivelul cu al doilea inamic
            State.setState(new LoadingBoss2(handler));
    }

    //returneaza frameul actual al animatiei
    private BufferedImage getCurrentAnimationFrame()
    {
        return animStatue.getCurrentFrame();
    }
}
