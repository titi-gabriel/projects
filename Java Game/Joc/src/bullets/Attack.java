package bullets;

import entities.Entity;
import main.Handler;
import tiles.Tile;

import java.awt.*;

//clasa pentru fiecare atac al caracterului
public abstract class Attack {

    protected Handler handler;
    protected float x,y; //pozitita atacului
    protected int width, height; //dimensiunile atacului
    protected Rectangle boundsA; //dreptunghi pentru hit-boxul atacului
    protected boolean active = true; //starea atacului
    protected float speed; //viteza atacului
    protected boolean throwDirection; //directia de atac

    public Attack(Handler handler,float x, float y, int width, int height)
    {
        this.handler = handler; //referinta catre handler
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        boundsA = new Rectangle(0,0,width,height);
    }

    public abstract void die(); //disparitia atacului

    protected void move() //metoda pentru deplasarea atacului
    {
        if (!checkAttackCollision(speed, 0f)) //verifica coliziunea atacului cu celelalte elemente ale jocului
        {
            moveX(); //daca nu a fost detectata o coliziune atunci atacului se va deplasa
        }
        else
            active = false; //daca s-a gasit coliziune atunci vom inceta atacul

    }

    protected void moveX()
    {
        if( speed > 0) //deplasare la dreapta
        {
            int tx = (int)(x+ speed + boundsA.x + boundsA.width) / Tile.TILEWIDTH;
            //se verifica coliziunea cu imprejurarile
            if(!collisionWithTile(tx,(int)(y + boundsA.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx,(int)(y + boundsA.y + boundsA.height) / Tile.TILEHEIGHT))
            {
                x += speed; //daca nu este coliziunea atunci pozitia se va actualiza

            }
            else {
                active = false;//daca este coliziune atacul va inceta
            }

        }
        else
        if ( speed < 0) //deplasare la stanga
        {
            int tx = (int)(x+ speed + boundsA.x) / Tile.TILEWIDTH;
            //se verifica coliziunea cu imprejurarile
            if(!collisionWithTile(tx,(int)(y + boundsA.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx,(int)(y + boundsA.y + boundsA.height) / Tile.TILEHEIGHT))
            {
                x += speed;//daca nu este coliziunea atunci pozitia se va actualiza
            }
            else {
                active = false;//daca este coliziune atacul va inceta
            }
        }
    }

    //metoda pentru verificare a coliziunii cu un Tile
    protected boolean collisionWithTile(int x, int y)
    {
        return handler.getWorld().getTile(x,y).isSolid();
    }

    public boolean checkAttackCollision(float xOffset, float yOffset)
    {
        //din managerul pentru entitati se va extrage fiecare entitate si se va verifica daca atacul intra in coliziune cu entitatea
        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            // se verifica daca hit boxul entitatii se intersecteaza cu dreptunghiul hit box al atacul
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,0 )))
            {
                return true;
            }
        }
        return false;
    }

    //returneaza un hitboxul(dreptunghi)
    public Rectangle getCollisionBounds(float xOffset, float yOffset)
    {
        return new Rectangle((int)(x + boundsA.x + xOffset), (int)(y + boundsA.y + yOffset), boundsA.width, boundsA.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public abstract void tick();

    public abstract void render(Graphics g);


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
