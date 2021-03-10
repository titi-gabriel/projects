package entities.creatures;

import bullets.EnergyBall;
import bullets.SandBall;
import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import hud.ChakraBarBoss2;
import hud.HealthBarBoss2;
import main.Game;
import main.Handler;
import state.Boss2Defeated;
import state.State;

import java.awt.*;
import java.util.Random;


//al doilea inamic
public class Enemy2 extends Entity {
    //Attack timer
    private long lastAttackTimer, attackTimer = 400;

    //Animations
    Animation animFoxAttack; //animatie atac
    Animation animFox; //animatie cand nu ataca


    //HUD
    protected HealthBarBoss2 healthBar; //bara de viata
    protected ChakraBarBoss2 chakraBar; //vata de chakra

    //pozitiile la care va aparea inamicul
    private int[] xPos;
    private int[] yPos;

    private int chakra2; //acest inamic are nevoie de inca o resursa(chakra2) pentru a se putea teleporta
                         //chakra simpla e folosita pentru a ataca, iar chakra2 pentru a se teleporta

    public Enemy2(Handler handler, int x, int y)
    {
        super(handler,x,y,Creature.DEFAULT_CREATURE_WIDTH/2, Creature.DEFAULT_CREATURE_HEIGHT/2);
        health = 900; //viata
        chakra2 = 1000; //puncte de chakra2 (pentru teleportare)
        animFoxAttack = new Animation(40, Assets.foxAttack, 500); //animatie atac
        animFox = new Animation(70, Assets.fox); //animatie cand nu ataca
        enemyFlag = true; //este inamic
        healthBar = new HealthBarBoss2(handler);
        chakraBar = new ChakraBarBoss2(handler);
        xPos = new int[]{12*80,14*80,13*80,15*80}; //coordonatele x la care se va afla
        yPos = new int[]{8*80,5*80,80,10*80}; //coordonatele y la care se va afla
    }

    @Override
    public void die() {
        //actualizare scor
        //scor curent + viata ramasa a jucatorului
        Game.setScore(Game.getScore() + handler.getWorld().getEntityManager().getPlayer().getHealth());
        //setare la un nou State
        State.setState(new Boss2Defeated(handler));

    }

    @Override
    public void tick() {
        //actualizare animatii, bara de viata si bara de chakra
        animFoxAttack.tick();
        animFox.tick();
        healthBar.tick();
        chakraBar.tick();

        //daca viata <= 0 -> inamicul moare
        if(this.health <= 0)
            die();

        teleport(); //teleportare
        updateChakra(); //actualizare chakra
        updateChakra2(); //actualizare chakra2
        checkAttacks(animFoxAttack); //verificare stare atac
    }

    //teleportare
    public void teleport()
    {
        if(chakra2 >= 2000) //daca are destule puncte de chakra2
        {
            //se ia o pozitie random la care inamicul va fi pus
            int rand = new Random().nextInt(xPos.length);
            //actualizare pozitie
            x = xPos[rand];
            y = yPos[rand];
            chakra2 = 0; //s-au folosit toate punctele de chakra2 pentru teleportare
        }
    }

    @Override
    public void render(Graphics g)
    {
        //desenare bara de viata, bara de chakra si rama pentru bara de viata/chakra + poza inamicului
        healthBar.render(g, 870, 38);
        chakraBar.render(g, 1014, 61);
        g.drawImage(Assets.hudBoss2,840,0,440,78,null);
        //daca atacul poate fi folosit si animatia este pregatita
        if(attackTimer < animFoxAttack.getCooldown() && Animation.sandBallYes )
        {
            //desenare frame in ipostaza de atac
            g.drawImage(animFoxAttack.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                    (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
        }
        else
            //desenare frame cand nu ataca
            g.drawImage(animFox.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                    (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
    }



    public void updateChakra() //actualizare chakra
    {
        if(chakra < 180) //daca chakra nu este la maxim, se vor adauga puncte constant
            chakra += 3;

    }

    public void updateChakra2() //actualizare chakra2
    {
        if(chakra2 < 2000) //daca chakra2 nu este la maxim, se vor adauga puncte constant
            chakra2 += 5;

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
            Animation.sandBallNow = false;//nu poate fi afisata animatia de atac
            return;
        }

        //resetare index animatie( asa la fiecare atac nou, animatia va incepe de la inceput)
        anim.setIndex(0);
        //daca se poate face animatia de atac
        if (Animation.sandBallNow ) {
            Animation.sandBallYes = false;//nu stim inca daca are puncte chakra destule
            if(chakra >= 180)
            {
                Animation.sandBallYes = true;//are puncte chakra destule, deci poate folosi atacul
                chakra = 0;//atacul consuma puncte chakra(in cazul asta pe toate)
                //adaugam un nou atac la managerul de atacuri
                handler.getWorld().getAttackManager().addAttack(new EnergyBall(handler,x-100,y,80,80, -7, false));
            }
        }
        else
            return;


        attackTimer = 0;
    }


}
