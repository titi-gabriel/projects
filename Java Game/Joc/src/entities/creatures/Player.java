package entities.creatures;

import bullets.Kunai;
import bullets.Rasengan;
import entities.Entity;
import gfx.Animation;
import gfx.Assets;
import hud.ChakraBar;
import hud.HealthBar;
import inventory.Inventory;
import main.Game;
import main.Handler;
import state.DieState;
import state.State;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends Creature
{

    //Attack timer
    private long lastAttackTimer, attackTimer = 400;

    //Inventory
    private Inventory inventory; //inventar

    //HUD bara viata si bara chakra
    private HealthBar healthBar;
    private ChakraBar chakraBar;

    //Animations
    private Animation animRight;
    private Animation animLeft;
    private Animation animJump;
    private Animation animJumpLeft;
    private Animation animStanceRight;
    private Animation animStanceLeft;
    private Animation animPunchRight;
    private Animation animPunchLeft;
    private Animation animKickRight;
    private Animation animKickLeft;
    private Animation animKunaiRight;
    private Animation animKunaiLeft;
    private Animation animNarutoRasenganRight;
    private Animation animNarutoRasenganLeft;


    public Player(Handler handler, float x, float y)
    {

        super(handler,x,y,Creature.DEFAULT_CREATURE_WIDTH/2, Creature.DEFAULT_CREATURE_HEIGHT/2);
        //hitbox
        bounds.x = 28;
        bounds.y = 15;
        bounds.width = 30;
        bounds.height = 60;
        health = 300;//viata

        inventory = new Inventory(handler);
        healthBar = new HealthBar(handler);
        chakraBar = new ChakraBar(handler);

        //Animations
        animRight = new Animation(50, Assets.naruto_right);
        animLeft = new Animation(50, Assets.naruto_left);
        animJump = new Animation(80,Assets.naruto_jump_right);
        animJumpLeft = new Animation(80,Assets.naruto_jump_left);
        animStanceRight = new Animation(100, Assets.naruto_stance_right);
        animStanceLeft = new Animation(100, Assets.naruto_stance_left);
        animPunchRight = new Animation(80,Assets.punchRight, 400);
        animPunchLeft = new Animation(80,Assets.punchLeft, 400);
        animKickRight = new Animation(80,Assets.kickRight, 400);
        animKickLeft = new Animation(80,Assets.kickLeft, 400);
        animKunaiRight = new Animation(80,Assets.kunaiRight, 400);
        animKunaiLeft = new Animation(80,Assets.kunaiLeft, 400);
        animNarutoRasenganRight = new Animation(40, Assets.narutoRasenganRight, 400);
        animNarutoRasenganLeft = new Animation(40, Assets.narutoRasenganLeft, 400);
    }



    @Override
    public void tick()
    {
        //actualizare animatii
        animRight.tick();
        animLeft.tick();
        animStanceRight.tick();
        animStanceLeft.tick();
        animJump.tick();
        animJumpLeft.tick();
        animPunchLeft.tick();
        animPunchRight.tick();
        animKickRight.tick();
        animKickLeft.tick();
        animKunaiRight.tick();
        animKunaiLeft.tick();
        animNarutoRasenganLeft.tick();
        animNarutoRasenganRight.tick();

        getInput(); //verificare taste

        move(); //deplasare

        if(this.health <= 0 || y > 1000) //daca viata <=0 sau playerul a cazut in gol va muri
            die();

        updateChakra(); //actualizare chakra
        //stare atac
        checkAttacks(animPunchRight);
        checkAttacks(animPunchLeft);
        checkAttacks(animKickLeft);
        checkAttacks(animKickRight);
        checkAttacks(animKunaiLeft);
        checkAttacks(animKunaiRight);
        checkAttacks(animNarutoRasenganLeft);
        checkAttacks(animNarutoRasenganRight);

        //actualizare inventar
        inventory.tick();
        //centrare camera caracter
        handler.getGameCamera().centerOnEntity(this);
    }



    @Override
    public void render(Graphics g)
    {
        //desenare in functie de ce face playerul

        //daca poate ataca cu pumnul
        if(attackTimer < animPunchRight.getCooldown() && direction &&Animation.punchYes)
        {
            //desenare frame
            g.drawImage(animPunchRight.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                    (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
        }
        else
             if(attackTimer < animPunchLeft.getCooldown() && !direction&&Animation.punchYes)
             {
                 //desenare frame
                  g.drawImage(animPunchLeft.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
             }
             else
                 //daca poate ataca cu piciorul
                if(attackTimer < animKickRight.getCooldown() && direction && Animation.kickYes)
                {
                    //desenare frame
                    g.drawImage(animKickRight.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                         (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
                }
                else
                    if(attackTimer < animKickLeft.getCooldown() && !direction&& Animation.kickYes)
                    {
                        //desenare frame
                        g.drawImage(animKickLeft.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                         (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
                    }
                    else
                        //daca poate arunca cutit
                        if(attackTimer < animKunaiLeft.getCooldown() && !direction&& Animation.kunaiYes)
                        {
                            //desenare frame
                            g.drawImage(animKunaiLeft.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                                    (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
                        }
                        else
                            if(attackTimer < animKunaiRight.getCooldown() && direction&& Animation.kunaiYes)
                            {
                                //desenare frame
                                g.drawImage(animKunaiRight.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                                        (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
                            }
                            else
                                //daca poate arunca bila de energie
                                if(attackTimer < animNarutoRasenganRight.getCooldown() && direction&& Animation.rasenganYes)
                                {
                                    //desenare frame
                                    g.drawImage(animNarutoRasenganRight.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                                            (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
                                }
                                else
                                    if(attackTimer < animNarutoRasenganLeft.getCooldown() && !direction&& Animation.rasenganYes )
                                    {
                                        //desenare frame
                                        g.drawImage(animNarutoRasenganLeft.getCurrentFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                                                (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );
                                    }
                                    else
                                        //daca nu ataca, se va desena frameul pentru saritura, cadere sau stat
                                        g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffSet()),
                                            (int)(y - handler.getGameCamera().getyOffSet()),width,height, null );

    }

    public void updateChakra() //actualizare chakra
    {
        if(chakra < 180) //daca chakra nu este la maxim, se vor adauga puncte constant
            chakra += 1;

    }
    public void checkAttacks(Animation anim)
    {

        //timerul pentru a verifica daca poate ataca
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        //ultimda data cand a atacat
        lastAttackTimer = System.currentTimeMillis();

        //daca nu poate ataca(a atacat prea de curand)
        if(attackTimer <anim.getCooldown()) {
            //nu poate fi afisata animatia de atac
            Animation.punchNow = false;
            Animation.kickNow = false;
            Animation.kunaiNow = false;
            Animation.rasenganNow = false;
            return;
        }

        //resetare index animatie( asa la fiecare atac nou, animatia va incepe de la inceput)
        anim.setIndex(0);

        Rectangle cb = getCollisionBounds(0,0); //hitbox
        Rectangle ar = new Rectangle(); //hitbox atac
        int arSize = 20;
        ar.height = arSize;
        ar.width = arSize;
        //daca ataca
        if(Animation.punchNow || Animation.kickNow || Animation.kunaiNow || Animation.rasenganNow) {
            //daca foloseste atacuri care nu necesita chakra
            if ((Animation.punchNow || Animation.kickNow)) {
                if (Animation.punchNow) {
                    //se seteaza animatia potrivita
                    Animation.punchYes = true;
                    Animation.kickYes = false;
                    Animation.kunaiYes = false;
                    Animation.rasenganYes = false;
                } else {
                    Animation.punchYes = false;
                    Animation.kickYes = true;
                    Animation.kunaiYes = false;
                    Animation.rasenganYes = false;
                }
                if (!direction) {
                    //daca ataca la stanga se pune hitboxul mai la stanga(pana unde ajunge sa loveasca)
                    ar.x = cb.x - arSize;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;

                    //cand loveste se opreste din miscare
                    xMove = 0;
                    yMove = 0;
                } else {
                    //daca ataca la dreatpa se pune hitboxul mai la dreapta(pana unde ajunge sa loveasca)
                    ar.x = cb.x + cb.width;
                    ar.y = cb.y + cb.height / 2 - arSize / 2;

                    //cand loveste se opreste din miscare
                    xMove = 0;
                    yMove = 0;
                }
            }

            //daca arunca cutitul (necesita chakra)
            if (Animation.kunaiNow) {
                //setare animatie
                Animation.kunaiYes = false;
                Animation.punchYes = false;
                Animation.kickYes = false;
                Animation.rasenganYes = false;
                //verifica daca are chakra destula
                if(chakra >= 60){
                    Animation.kunaiYes = true; //poate ataca
                    chakra -= 60; //se scade chakra necesara pentru a ataca
                    if(direction) //la dreapta
                    {
                        //creez un kunaiObject nou care sa se duca in dreapta

                        handler.getWorld().getAttackManager().addAttack(new Kunai(handler,x+bounds.x +bounds.width-4,y,80,80, 10,true));
                    }
                    else //la stanga
                    {
                        //creez un kunaiObject nou care sa se duca in stanga
                        handler.getWorld().getAttackManager().addAttack(new Kunai(handler,x-30,y,80,80, -10,false));
                    }
                }
            }

            //daca foloseste bila de energie(necesita chakra)
            if (Animation.rasenganNow ) {
                //setare animatie
                Animation.kunaiYes = false;
                Animation.punchYes = false;
                Animation.kickYes = false;
                Animation.rasenganYes = false;
                if(chakra >= 90) //verifica daca are chakra destula
                {
                    Animation.rasenganYes = true; //poate folosi atacul
                    chakra -= 90; //se scade chakra necesara
                    if(direction) //la dreapta
                    {
                        //creez o bila de energie noua care sa se duca in dreapta

                        handler.getWorld().getAttackManager().addAttack(new Rasengan(handler,x+bounds.x +bounds.width+10,y,80,80, 10, true));

                    }
                    else
                    {
                        //creez o bila de energie noua care sa se duca in stanga

                        handler.getWorld().getAttackManager().addAttack(new Rasengan(handler,x-40,y,80,80, -10, false));
                    }
                }
            }
        }
        else
            return;

        //resetare timer
        attackTimer = 0;


        //verificare coliziuni
        for(Entity e : handler.getWorld().getEntityManager().getEntities())
        {

            if(e.equals(this)) //playerul nu poate suferi pagube de la propriul atac
                continue;
            if(e.getCollisionBounds(0,0).intersects(ar) && e.isKillable()) //daca este coliziune si entitatea se poate distruge
            {
                e.hurt(25); //face pagube entitatii
                return;
            }
        }

    }

    public void getInput() {
        if(!falling )//pentru a evita saritura incontinuu
        {
        xMove = 0;
        yMove = 0;}

        //verificare apasare taste
        if(handler.getKeyManager().up)
            if(canJump )
                jump(6);
        if(handler.getKeyManager().right)
            xMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;

    }

    //verifica ce frame al animatiei trebuie desenat
    private BufferedImage getCurrentAnimationFrame()
    {
        switch (whatDoes())
        {
            //returneaza dupa caz frameul animatiei potrivite
            case 1: return animRight.getCurrentFrame();
            case 2: return animLeft.getCurrentFrame();
            case 3: return animJump.getCurrentFrame();
            case 4: return animJumpLeft.getCurrentFrame();
            case 5: return animStanceRight.getCurrentFrame();
            case 6: return animStanceLeft.getCurrentFrame();



            default: break;
        }
        //caracterul incepe in joc prin a sta privind la dreapta
        return animStanceRight.getCurrentFrame();
    }

    //verifica ce face caracterul
    private int whatDoes()
    {

        if(xMove > 0 && yMove==0)//deplasare la dreapta
        {

            return 1;
        }
        if(xMove < 0 && yMove==0) //deplasare la stanga
        {

            return 2;
        }
        if(yMove < 0 && xMove==0 && direction) //saritura pe loc cu fata la dreapta
        {

            return 3;
        }
        if(yMove < 0 && xMove==0 && !direction) //saritura pe loc cu fata la stanga
        {

            return 4;
        }
        if(xMove > 0 && yMove<0)  //saritura la dreapta
        {

            return 3;
        }
        if(xMove < 0  && yMove<0) //saritura la stanga
        {

            return 4;
        }
        if(xMove == 0 && yMove==0 && direction) //stationare cu fata la dreapta
        {

            return 5;
        }
        if(xMove == 0 && yMove==0 && !direction) //stationare cu fata la stanga
        {

            return 6;
        }

        if(yMove > 0 && direction) //falling cu fata la dreapta
            return 3;
        if(yMove > 0 && !direction) //falling cu fata la stanga
            return 4;

        return 0;
    }

    //caracterul moare
    public void die()
    {
        Game.setScore(0); //se seteaza scorul la 0
        State.setState(new DieState(handler)); // se intra in Stateul de moarte a caracterului
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public ChakraBar getChakraBar() {
        return chakraBar;
    }
}
