package gfx;

import entities.Entity;
import main.Game;
import main.Handler;
import tiles.Tile;

public class GameCamera
{
    private Handler handler;
    private float xOffSet, yOffSet; //cu cat se va misca camera

    public GameCamera(Handler handler, float xOffSet, float yOffSet)
    {
        this.handler = handler;
        this.xOffSet = xOffSet;
        this.yOffSet = yOffSet;
    }

    //metoda care muta camera
    public void checkBlankSpace()
    {
        if(xOffSet < 0)
            xOffSet = 0;
        else {
            //daca playerul este la marginea mapei, camera nu va fi centrata pe player
            if (xOffSet > handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth())
                xOffSet = handler.getWorld().getWidth() * Tile.TILEWIDTH - handler.getWidth();
        }

        if(yOffSet < 0)
            yOffSet = 0;
        else {
            //daca playerul este la marginea mapei, camera nu va fi centrata pe player
            if (yOffSet > handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight())
                yOffSet = handler.getWorld().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
        }
    }

    //se cauta coordonatele playerului si se centreaza camera pe el
    public void centerOnEntity(Entity e)
    {
        xOffSet = e.getX() - handler.getWidth() / 2 + e.getWidth() /2;
        yOffSet = e.getY() - handler.getHeight() / 2 + e.getHeight() /2;

        //verifica daca playerul este la marginea mapei
        checkBlankSpace();
    }


    public float getxOffSet() {
        return xOffSet;
    }

    public void setxOffSet(float xOffSet) {
        this.xOffSet = xOffSet;
    }

    public float getyOffSet() {
        return yOffSet;
    }

    public void setyOffSet(float yOffSet) {
        this.yOffSet = yOffSet;
    }
}
