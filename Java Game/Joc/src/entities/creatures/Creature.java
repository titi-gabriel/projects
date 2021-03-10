package entities.creatures;

import entities.Entity;
import main.Game;
import main.Handler;
import tiles.Tile;

import java.awt.*;
import java.util.TooManyListenersException;


//clasa pentru entitatile care se misca si pot realiza diferite actiuni
public abstract class Creature extends Entity
{


    public static final int DEFAULT_SPEED = 4; //viteza standard a creaturii

    //latimea si inaltimea standard a creaturii
    public static final int DEFAULT_CREATURE_WIDTH = 160;
    public static final int DEFAULT_CREATURE_HEIGHT = 160;


    protected float speed; //viteza
    protected float xMove, yMove; //variabile pentru actualizarea pozitiei creaturii
    protected boolean direction = true; //directia de miscare a creaturii
    public boolean falling = true ; //boolean pentru a vedea daca se afla in cadere creatura
    public boolean canJump; //boolean pentru a vedea daca poate sari creatura
    protected int maxFallingSpeed; //viteza maxima pe care o poate avea creatura atunci cand se afla in cadere
    protected float gravity, maxJump; //gravitatia si inaltimea maxima pentru saritura

    public Creature(Handler handler, float x, float y, int width, int height)
    {
        super(handler,x,y,width,height);
        gravity = 0.2f;
        maxFallingSpeed = 15;
        maxJump = -15;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    //metoda pentru caderea creaturii
    public void fall()
    {
        if(falling) //se testeaza daca poate cadea
        {
            yMove += gravity; //se adauga la yMove gravitatia, care apoi va fi necesara schimbarii pozitiei creaturii
            if(yMove > maxFallingSpeed)
                yMove = maxFallingSpeed; //daca am ajuns la viteza maxima de cadere, atunci vom ramane la asta
        }

    }

    //metoda pentru sarit
    protected void jump(double jumpHeight)
    {
        //se testeaza daca poate sarita creatura si daca nu se afla in cadere
        if(canJump && !falling) {
            if (yMove >= maxJump) {
                yMove -= jumpHeight; //daca am ajuns la inaltimea maxima a sariturii, atunci incepem sa scadem inaltimea la care safla creatura
            }
            else { //daca se afla in saritura
                //yMove = 0; //resetam variabila la 0
                canJump = false; //nu mai poate sari
                falling = true; //o sa cada
            }
            canJump = false; //pentru a evita sariturile multiple
        }
    }


    //metoda de miscare a creaturii
    protected void move() {

        //se verifica coliziunea intre hitboxuri
        int tyyy = (int) (y + 2 + bounds.y + bounds.height) / Tile.TILEHEIGHT;
        //coliziunea in jos
        if (!collisionWithTileBottom((int) (x + bounds.x) / Tile.TILEWIDTH, tyyy) &&
                !collisionWithTileBottom((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, tyyy)) {
            falling = true; //nu este coliziune in jos, atunci cratura trebuie sa cada
        }else
            canJump = true; //daca este coliziune jos, creatura va putea sari


        if(!falling) canJump = true; //daca nu este in cadere, va putea sari
        fall(); //cadere

        //verificare coliziuni (orizontala si verticala)
        if (!checkEntityCollision(xMove, 0f)) {
            moveX();
        }
        if (!checkEntityCollision(0f, yMove))
            moveY();
        else {
            canJump = true;
            if (yMove > 0) {
                yMove = 0;
                falling = false;
            }
        }
    }




    public void moveX()
    {

        if( xMove > 0) //deplasare la dreapta
        {
            //verificare coliziuni
            int tx = (int)(x+ xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx,(int)(y + bounds.y) / Tile.TILEHEIGHT) &&
                    !collisionWithTile(tx,(int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
            {
                x += xMove; //actualizare pozitie

            }
            else {
                xMove = 0; //daca este coliziune se inceteaza miscarea

                //se reseteaza pozitia putin mai la stanga pentru a evita efectul de ticaire a imaginii in momentul coliziunii
                //si pentru a se putea misca creatura din nou(se evita fenomenul de coliziune infinita)
                x = tx * Tile.TILEWIDTH - bounds.x - bounds.width -1;

            }
            direction = true; //directia este catre dreapta(true)

        }
        else
            if ( xMove < 0) //deplasare la stanga
            {
                //verificare coliziuni
                int tx = (int)(x+ xMove + bounds.x) / Tile.TILEWIDTH;
                if(!collisionWithTile(tx,(int)(y + bounds.y) / Tile.TILEHEIGHT) &&
                        !collisionWithTile(tx,(int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT))
                {
                    x += xMove;//actualizare pozitie
                }
                else {
                    xMove = 0;//daca este coliziune se inceteaza miscarea
                    //se reseteaza pozitia putin mai la dreapta pentru a evita efectul de ticaire a imaginii in momentul coliziunii
                    //si pentru a se putea misca creatura din nou(se evita fenomenul de coliziune infinita)
                    x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x + 1;
                }
                direction = false; //directia este catre stanga(false)
            }
    }

    public void moveY()
    {
        if(yMove < 0 ) //deplasare in sus
        {

            int ty = (int)(y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int)(x + bounds.x )/Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int)(x + bounds.x +bounds.width)/Tile.TILEWIDTH, ty) )
            {
                //nu am scris nimic, deoarece se vor apela automat metodele fall si jump
            }
            else{

                //se reseteaza pozitia putin mai jos pentru a evita efectul de ticaire a imaginii in momentul coliziunii
                //si pentru a se putea misca creatura din nou(se evita fenomenul de coliziune infinita)
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y + 5;
                yMove=0;//daca este coliziune se inceteaza miscarea
            }

        }

        if(yMove < 0 && canJump ) //deplasare in sus cand creatura poate sari
        {
            //verificare coliziuni
            int ty = (int)(y + yMove + bounds.y) / Tile.TILEHEIGHT;
            if(!collisionWithTile((int)(x + bounds.x )/Tile.TILEWIDTH, ty) &&
                    !collisionWithTile((int)(x + bounds.x +bounds.width)/Tile.TILEWIDTH, ty) )
            {

                y += yMove; //actualizare pozitie
                falling = false; //in timpul sariturii, caderea va fi imposibila pentru a evita suprapunerea sariturii
                                 // cu caderea

            }
            else{
                //se reseteaza pozitia putin mai jos pentru a evita efectul de ticaire a imaginii in momentul coliziunii
                //si pentru a se putea misca creatura din nou(se evita fenomenul de coliziune infinita)
                y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y + 1;
                canJump = false; //nu mai poate sari din moment ce se afla in aer
                yMove = 0; //se inceteaza miscarea
            }

        }
        else {

            if (yMove > 0 || falling) //deplasare in jos
            {
                //verificare coliziuni
                int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT;
                if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty) &&
                        !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty))
                {
                    y += yMove; //actualizare pozitie
                    falling = true; //creatura se afla in cadere

                } else {
                    falling = false; //caderea a incetat pentru ca avem coliziune in jos
                    canJump = true; //creatura poate sari pentru ca se afla pe pamant(are coliziune in jos)
                    //se reseteaza pozitia putin mai sus pentru a evita efectul de ticaire a imaginii in momentul coliziunii
                    //si pentru a se putea misca creatura din nou(se evita fenomenul de coliziune infinita)
                    y = ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 1;
                    yMove = 0; //se inceteaza miscarea

                }
            }
        }


    }



    //verificare coliziune cu Tile
    protected boolean collisionWithTile(int x, int y)
    {
        return handler.getWorld().getTile(x,y).isSolid();
    }

    //verificare coliziune in partea de jos
    protected boolean collisionWithTileBottom(int xT, int yT)
    {
        if(handler.getWorld().getTile(xT,yT).isSolid()) {
            //hitbox
            Rectangle pb = new Rectangle((int) (x + bounds.x), (int) (y + bounds.y), width, height);

            //se afla dreptunghiul mix al partii de sus al Tile-ului si se verifica intersectia intre hitboxul caracterului
            //si dreptunghi
            if (pb.intersects(handler.getWorld().getTile(xT, yT).getTop(xT, yT))) {
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }


    //GETTERS SETTERS
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isCanJump() {
        return canJump;
    }


}
