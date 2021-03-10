package ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

//buton
public abstract class UIObject
{
    protected float x , y; //pozitie
    protected int width, height; //dimensiuni
    protected Rectangle bounds; //hitbox
    protected boolean hovering = false; //verifica daca mouseul este deasupra butonului

    public UIObject(float x, float y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle((int)x,(int)y,width,height);
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void onClick();

    public void onMouseMove(MouseEvent e)
    {
        //daca mouseul este deasupra butonului, flagul hovering va deveni true si invers
        if(bounds.contains( e.getX(), e.getY()))
            hovering = true;
        else
            hovering = false;
    }
    public void onMouseRelease(MouseEvent e)
    {
        //daca mouseul este deasupra butonului si a fost apasat, se va apela metoda onClick() specifica fiecarui buton
        if(hovering)
            onClick();
    }


    //GETTER SETTERS
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

    public boolean isHovering() {
        return hovering;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
}
