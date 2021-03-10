package bullets;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Kunai extends Attack {


    private Animation animKunaiFlyLeft, animKunaiFlyRight; //animatiile atacului
    public Kunai(Handler handler, float x, float y, int width, int height, float speed, boolean throwDirection) {
        super(handler, x, y, width, height);
        this.speed = speed; //viteza atacului
        this.throwDirection = throwDirection; //directia atacului
        boundsA.y = 36; // coordonata y a hitboxului
        boundsA.width = 42; //latimea hitboxului
        boundsA.height = 17; //inaltimea hb

        //in functie de directia de aruncare a cutitului vom alege alta animatie pentru afisare
        //si vom schimba coordonata x a hitboxului pentru a functiona corect coliziunea
        if(throwDirection) {
            animKunaiFlyRight = new Animation(50, Assets.kunaiFlyRight);
            boundsA.x = 2;

        }
        else{
            animKunaiFlyLeft = new Animation(50, Assets.kunaiFlyLeft);
            boundsA.x = 25;

        }
    }

    @Override
    public void die() {

    }

    @Override
    public void tick() {
        move(); //deplasarea atacului

        //in fct de directia de aruncare vom actualiza animatia
        if(!throwDirection)
            animKunaiFlyLeft.tick();
        else
            animKunaiFlyRight.tick();
        checkAttacks(); //verificam starea atacului
    }

    @Override
    public void render(Graphics g) {
        //se deseneaza atacul pe frame-uri
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );

    }

    private BufferedImage getCurrentAnimationFrame()
    {
        //in fct de directie vom alege de la care animatie vom lua frame-ul
        if(!throwDirection)
            return animKunaiFlyLeft.getCurrentFrame();
        return animKunaiFlyRight.getCurrentFrame();

    }

    private void checkAttacks()
    {
        Rectangle cb = getCollisionBounds(0,0); //hitbox atac
        Rectangle ar = new Rectangle(); //dreptunghi copie a hitboxului
        //in fct de directie modificam hitboxul pentru a functiona coliziunile
        if(throwDirection)
            ar.x = cb.x + 11; //pentru dreapta, vom muta hbul putin la dreapta
        else
            ar.x = cb.x - 11;//pentru stanga, vom muta hbul putin la stanga
        ar.y = cb.y; //copie coordonata y
        ar.width = cb.width; //copie latimea
        ar.height = cb.height;//copie inaltimea

        //pentru fiecare entitate se verifica coliziune atacului
        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {
            if(e.getCollisionBounds(0,0).intersects(ar))
            {
                e.hurt(50); //daca este coliziune cu entitate, atunci entitatea va suferi pagube
                active = false; //actualizam starea atacului pentru a fi inlaturat atunci cand a fost coliziune
                return;
            }
        }
    }

}
