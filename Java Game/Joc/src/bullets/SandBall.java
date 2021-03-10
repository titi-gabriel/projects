package bullets;

import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import main.Handler;
import tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SandBall extends Attack {

    protected Animation animSandBall; //animatie
    protected int targetX, targetY; //coordonatele tintei atacului
    protected float toX, toY; //diferenta dintre coord atacului si cele a tintei(jucatorul)
    protected float angle; //unghiul atacului
    public SandBall(Handler handler, float x, float y, int width, int height, float speed, boolean throwdirection) {
        super(handler, x, y, width, height);
        this.speed = speed; //viteza
        this.throwDirection = throwdirection;//directie
        //hitbox atac
        boundsA.y = 2;
        boundsA.width = 75;
        boundsA.height = 75;
        boundsA.x = 2;
        animSandBall = new Animation(50, Assets.sandBall); //animatie
        //coordonatele tintei modificate putin pentru a tinti catre capul jucatorului
        targetX = (int)(handler.getWorld().getEntityManager().getPlayer().getX()) - 3;
        targetY = (int)(handler.getWorld().getEntityManager().getPlayer().getY()) - 15 ;
        toX = targetX - x;
        toY = targetY - y;
        angle = (float)Math.atan2(toY, toX); //unghiul dintre atac si tinta
    }

    @Override
    public void die() {

    }

    @Override
    public void tick()  //actualizare atac
    {
        move(); //miscare atac
        animSandBall.tick(); //actualizare animatie
        checkAttacks(); //starea atacului
    }

    @Override
    public void render(Graphics g)
    {
        //se deseneaza pe frameuri
        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );

    }

    protected BufferedImage getCurrentAnimationFrame()//returnam frame-ul animatiei atacului
    {

        return animSandBall.getCurrentFrame();

    }

    protected void checkAttacks()
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
            if(e.getCollisionBounds(0,0).intersects(ar) && e == handler.getWorld().getEntityManager().getPlayer())
            {
                e.hurt(50); //face pagube jucatorului
                active = false;
                return;
            }
        }
    }

    protected void move()
    {
        //se verifica coliziunea, se verifica coliziunea si se face miscare
        if (!checkAttackCollision(speed, 0f)) {
            checkCol(); //verifica coliziunea cu mediul inconjurator
            follow(); //miscarea atacului
        }
        else
            active = false;

    }

    protected void checkCol()
    {
        if( speed > 0) //deplasare la dreapta
        {
            //daca la dreapta atacul va intersecta un tile, acesta va disparea(se va actualiza starea atacului)
            int tx = (int)(x+ speed + boundsA.x + boundsA.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx,(int)(y + boundsA.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx,(int)(y + boundsA.y + boundsA.height) / Tile.TILEHEIGHT))
            {
            }
            else {
                active = false;
            }

        }
        else
        if ( speed < 0) //deplasare la stanga
        {
            //daca la stanga atacul va intersecta un tile, acesta va disparea(se va actualiza starea atacului)
            int tx = (int)(x+ speed + boundsA.x) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx,(int)(y + boundsA.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx,(int)(y + boundsA.y + boundsA.height) / Tile.TILEHEIGHT))
            {
            }
            else {
                active = false;
            }
        }
    }

    public void follow()
    {
        //se actualizeaza coordonatele atacului pentru a se deplasa catre tinta
        x -= speed * Math.cos(angle);
        y -= speed * Math.sin(angle);

    }


}
