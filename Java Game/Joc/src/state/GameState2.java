package state;

import gfx.Assets;
import main.Handler;
import worlds.World;
import worlds.World2;

import java.awt.*;

public class GameState2 extends State
{

    private World2 world;

    //al doilea nivel
    public GameState2(Handler handler)
    {
        super(handler);
        world = new World2(handler,"res/textures/world2.txt"); //initializare a doua lume
        handler.setWorld(world);//setare lume
        State.level = 2; //setare nivel actual
    }

    @Override
    public void tick() {
        //actualizare lume
        world.tick();
        //pentru motive de testare la apasarea tastet <N> se trece la urmatorul nivel
        if(handler.getKeyManager().n)
            State.setState(new LoadingBoss2(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare lume
        world.render(g, Assets.BG2);
    }
}
