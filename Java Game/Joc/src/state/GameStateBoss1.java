package state;

import gfx.Assets;
import main.Handler;
import worlds.World;
import worlds.World2;
import worlds.WorldBoss1;

import java.awt.*;

public class GameStateBoss1 extends State
{

    private WorldBoss1 world;

    public GameStateBoss1(Handler handler)
    {
        super(handler);
        world = new WorldBoss1(handler,"res/textures/worldboss1.txt"); //initializare lume primul inamic
        handler.setWorld(world);//setare lume
    }

    @Override
    public void tick() {
        //actualizare lume
        world.tick();
        //pentru motive de testare la apasarea tastet <N> se trece la urmatorul nivel
        if(handler.getKeyManager().n)
            State.setState(new Boss1Defeated(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare lume
        world.render(g, Assets.BG);
    }
}