package state;

import gfx.Assets;
import main.Handler;
import worlds.World;
import worlds.World2;
import worlds.WorldBoss1;
import worlds.WorldBoss2;

import java.awt.*;

public class GameStateBoss2 extends State
{

    private WorldBoss2 world;

    public GameStateBoss2(Handler handler)
    {
        super(handler);
        world = new WorldBoss2(handler,"res/textures/worldboss2.txt"); //initializare lume al doilea inamic
        handler.setWorld(world);//setare lume
    }

    @Override
    public void tick() {
        //actualizare lume
        world.tick();
        //pentru motive de testare la apasarea tastet <N> se trece la urmatorul nivel
        if(handler.getKeyManager().n)
            State.setState(new Boss2Defeated(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare lume
        world.render(g, Assets.BG2);
    }
}