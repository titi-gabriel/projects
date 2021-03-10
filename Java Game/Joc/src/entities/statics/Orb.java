package entities.statics;

import gfx.Animation;
import gfx.Assets;
import items.Item;
import main.Handler;
import tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;


//clasa pentru entitatea care la moarte va crea un pergament
public class Orb extends StaticEntity{

    private Animation animOrb; //animatie
    public Orb (Handler handler, float x, float y)
    {
        super(handler,x,y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
        animOrb = new Animation(70, Assets.orb); //initializare animatie
        //hitbox
        bounds.x = 15;
        bounds.y = 0;
        bounds.height = 80;
        bounds.width = 50;
        health = 200;//viata
    }

    @Override
    public void tick() {
        animOrb.tick();
    } //actualizare

    @Override
    public void render(Graphics g) {
        //desenare pe frameuri
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
    }

    //la moarte, se va adauga in managerul de entitati un nou pergament la pozitia acestui obiect
    public void die()
    {
        handler.getWorld().getItemManager().addItem(Item.pergamentItem.createNew((int)x , (int)y ));
    }

    //returneaza frameul animatiei actual
    private BufferedImage getCurrentAnimationFrame()
    {
        return animOrb.getCurrentFrame();
    }
}
