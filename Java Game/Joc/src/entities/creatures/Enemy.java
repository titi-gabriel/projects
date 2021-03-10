package entities.creatures;

import bullets.Kunai;
import bullets.Rasengan;
import bullets.SandBall;
import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import hud.*;
import inventory.Inventory;
import main.Game;
import main.Handler;
import state.Boss1Defeated;
import state.GameState2;
import state.State;

import java.awt.*;

//primul inamic
public class Enemy extends Entity {

    //Attack timer
    private long lastAttackTimer, attackTimer = 400;

    //Animations
    Animation animGaaraAttack; //animatie atac
    Animation animGaaraStance; //animatie cand inamicul sta

    //move
    //protected int count;
    public double movedX, movedY; //cat s-a deplasat
    protected boolean dirX, dirY; //directia de deplasare
    protected boolean last; //folosit pentru schimbarea directiei de deplasare(stanga, dreapta, sus, jos)

    //HUD
    protected HealthBarBoss1 healthBar; //bara de viata
    protected ChakraBarBoss1 chakraBar; //bara de chakra

    public Enemy(Handler handler, int x, int y)
    {
        super(handler,x,y,Creature.DEFAULT_CREATURE_WIDTH/2, Creature.DEFAULT_CREATURE_HEIGHT/2);
        health = 600; //viata
        //count = 1;
        movedX = 0;
        movedY = 0;
        dirX = true;
        dirY = true;
        last = true;
        animGaaraAttack = new Animation(50, Assets.gaaraAttack, 400);
        animGaaraStance = new Animation(150, Assets.gaaraStance);
        enemyFlag = true;
        healthBar = new HealthBarBoss1(handler);
        chakraBar = new ChakraBarBoss1(handler);
    }

    @Override
    public void die() {
        //actualizare scor(scor curent + viata ramasa a jucatorului)
        Game.setScore(Game.getScore() + handler.getWorld().getEntityManager().getPlayer().getHealth());
        //trecere la urmatorul State
        State.setState(new Boss1Defeated(handler));

    }

    @Override
    public void tick() {
        //actualizare animatii, bara de viata si bara de chakra
        animGaaraAttack.tick();
        animGaaraStance.tick();
        healthBar.tick();
        chakraBar.tick();
        //last==true -> deplasare pe orizontala
        //last==false -> deplasare pe verticala
        if(last)
            moveX(5,320);
        else
            moveY(3,720);

        //daca viata <= 0, inamicul va muri
        if(this.health <= 0)
            die();

        updateChakra(); //actualizare puncte chakra

        //verifica starea atacului
        checkAttacks(animGaaraAttack);


    }

    @Override
    public void render(Graphics g)
    {
        //desenare bara viata si bara chakra
        healthBar.render(g, 870, 38);
        chakraBar.render(g, 1014, 61);
        //desenare rama pentru bara de viata si chakra + poza inamicului
        g.drawImage(Assets.hudBoss1,840,0,440,78,null);
        //daca atacul poate fi folosit si animatia este pregatita
        if(attackTimer < animGaaraAttack.getCooldown() && Animation.sandBallYes )
        {
            //desenare frame in ipostaza de atac
            g.drawImage(animGaaraAttack.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                    (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
        }
        else
            //desenare frame in ipostaza cand nu ataca
            g.drawImage(animGaaraStance.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                    (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
    }


    public void moveX(double speed,int distance)
    {
        if(dirX) //deplasare la dreapta
        {
            x+=speed; //actualizare pozitie
            movedX += speed; //cat s-a deplasat
        }
        else
        {
            x -= speed;//actualizare pozitie
            movedX -= speed;//cast s-a deplasat

        }

        //daca s-a deplasat pe distanta data, atunci se va schimba directia de deplasare
        if(movedX >= distance) {
            last = false; //se trece la deplasarea opusa( din verticala in orizontala si invers)
            dirX = false; //se trece de la deplasarea la stanga la dreapta si invers
        }
        else
        if(movedX == 0)
        {last = false;
            dirX = true;
        }
    }
    public void moveY(double speed, int distance)
    {
        if(!dirY) //deplasare in jos
        {
            y+=speed; //actualizare pozitie
            movedY += speed; //cat s-a deplasat
        }
        else
        {
            y -= speed;//actualizare poz
            movedY -= speed;//cat s-a deplasat
        }

        //daca s-a deplasat pe distanta data, atunci se va schimba directia de deplasare
        if(movedY <= -distance) {
            last = true;//se trece la deplasarea opusa( din verticala in orizontala si invers)
            dirY = false;//se trece de la deplasarea in jos la deplasarea in jos si invers
        }
        else
        if(movedY == 0) {
            last = true;
            dirY = true;
        }
    }

    public void updateChakra() //actualizare puncte chakra
    {
        if(chakra < 180) //daca punctele chakra sunt mai mici decat maximul, atunci vom adauga puncte
            //chakra += 0.1;
            chakra += 4;

    }


    public void checkAttacks(Animation anim)
    {
        //timerul pentru a verifica daca poate ataca
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        //ultimda data cand a atacat
        lastAttackTimer = System.currentTimeMillis();

        Animation.sandBallNow = true;//poate fi afisata animatia

        //daca nu poate ataca(a atacat prea de curand)
        if(attackTimer <anim.getCooldown()) {
            Animation.sandBallNow = false; //nu poate fi afisata animatia de atac
            return;
        }

        //resetare index animatie( asa la fiecare atac nou, animatia va incepe de la incepu)
        anim.setIndex(0);

        //daca se poate face animatia de atac
        if (Animation.sandBallNow ) {
            Animation.sandBallYes = false; //nu stim inca daca are puncte chakra destule
            if(chakra >= 180)
            {
                Animation.sandBallYes = true; //are puncte chakra destule, deci poate folosi atacul
                chakra = 0; //atacul consuma puncte chakra(in cazul asta pe toate)
                //adaugam un nou atac la managerul de atacuri
                handler.getWorld().getAttackManager().addAttack(new SandBall(handler,x-100,y,80,80, -7, false));
            }
        }
        else
            return;

        //se reseteaza timerul
        attackTimer = 0;
    }


}
