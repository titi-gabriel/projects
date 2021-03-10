package state;

import entities.creatures.Player;
import entities.statics.Orb;
import gfx.Assets;
import main.Game;
import main.Handler;
import tiles.Tile;
import worlds.*;

import java.awt.*;

public class GameState extends State {

    private World world;

    //primul nivel
    public GameState(Handler handler)
    {
        super(handler);
        world = new World1(handler,"res/textures/world1.txt"); //initializare prima lume
        handler.setWorld(world); //setare lume
        State.level = 1; //setare nivel actual
    }

    @Override
    public void tick() {
        //actualizare lume
        world.tick();
        //pentru motive de testare la apasarea tastet <N> se trece la urmatorul nivel
        if(handler.getKeyManager().n)
            State.setState(new LoadingBoss1(handler));
    }

    @Override
    public void render(Graphics g) {
        //desenare lume
        world.render(g, Assets.BG);
    }
}
