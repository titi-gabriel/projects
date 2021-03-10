package entities;

import main.Game;
import main.Handler;
import state.State;

import java.awt.*;

public abstract class Entity
{
    public static final int DEFAULT_HEALTH = 100; //puncte de viata standard
    protected Handler handler;
    protected float x,y; //coordonatele entitatii
    protected int width, height; //latime, inaltime entitate
    protected Rectangle bounds; //dreptunghi pt hitboxul entitatii
    protected int health; //puncte de viata
    protected float chakra; //puncte de chakra(necesara utilizarii atacurilor)
    protected boolean active = true; //stare entitate
    protected boolean killable = true; //boolean pentru a vedea daca entitatea poate fi ucisa
    protected boolean enemyFlag = false; //boolean folosit pentru detectarea inamicilor

    public Entity(Handler handler,float x, float y, int width, int height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        health = DEFAULT_HEALTH;
        chakra = 180;
        bounds = new Rectangle(0,0,width,height);
        killable = true;
    }

    public void hurt(int amt) //metoda folosita pentru a scadea viata entitatii atunci cand sufera pagube
    {
        health -= amt; //actualizare viata
        if(health <= 0) {
            active = false; //daca viata a ajuns la <= 0 atunci starea entitatii va deveni false
            die(); //metoda pentru moartea entitatii
        }
    }

    public abstract void die();

    public boolean checkEntityCollision(float xOffset, float yOffset) //verificare coliziuni cu alte entitati
    {
        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if(e.equals(this)) //conditie pusa pentru a elimina posibilitatea coliziunii cu sine
                continue;
            if(e.getCollisionBounds(0f,0f).intersects(getCollisionBounds(xOffset,yOffset )))
            {
                return true;
            }
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) //returneaza dreptunghiul hitbox
    {
        return new Rectangle((int)(x + bounds.x + xOffset), (int)(y + bounds.y + yOffset), bounds.width, bounds.height);
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

   public boolean isKillable() {
        return killable;
    }

    public float getChakra() {
        return chakra;
    }

    public boolean isEnemyFlag() {
        return enemyFlag;
    }
}
