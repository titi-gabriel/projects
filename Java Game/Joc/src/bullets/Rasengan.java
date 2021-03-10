package bullets;

import entities.Entity;
import entities.EntityManager;
import gfx.Animation;
import gfx.Assets;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Rasengan extends  Attack {
    private Animation animRasengan; //animatie
    public Rasengan(Handler handler, float x, float y, int width, int height, float speed,boolean throwdirection) {
        super(handler, x, y, width, height);
        this.speed = speed; //viteza atacului
        this.throwDirection = throwdirection; //directia de aruncare
        //coordonatele, inaltimea, latimea hitboxului atacului
        boundsA.y = 0;
        boundsA.width = 75;
        boundsA.height = 75;
        boundsA.x = 2;
        animRasengan = new Animation(50, Assets.rasengan); //initializam animatia
    }

    @Override
    public void die() {

    }

    @Override
    public void tick() //actualizam variabilele atacului
    {
        move(); //miscare atacului
        animRasengan.tick(); //actualizarea animatiei
        checkAttacks(); //verificam starea atacului
    }

    @Override
    public void render(Graphics g) //se deseneaza atacul pe frame-uri
    {
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );

    }

    private BufferedImage getCurrentAnimationFrame()
    {

        return animRasengan.getCurrentFrame(); //returnam frame-ul animatiei atacului

    }

    private void checkAttacks()
    {
        Rectangle cb = getCollisionBounds(0,0);//hitbox atac
        //dreptunghi copie a hitboxului
        Rectangle ar = new Rectangle();
        if(throwDirection)//in fct de directie modificam hitboxul pentru a functiona coliziunile
            ar.x = cb.x + 11;//pentru dreapta, vom muta hbul putin la dreapta
        else
            ar.x = cb.x - 11;//pentru stanga, vom muta hbul putin la stanga
        //copie coordonata y, latimea si inaltimea
        ar.y = cb.y;
        ar.width = cb.width;
        ar.height = cb.height;
        //pentru fiecare entitate se verifica coliziune atacului
        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {

            if(e.getCollisionBounds(0,0).intersects(ar) )
            {
                e.hurt(100);//daca este coliziune cu entitate, atunci entitatea va suferi pagube
                active = false;//actualizam starea atacului pentru a fi inlaturat atunci cand a fost coliziune
                return;
            }
        }
    }
}
