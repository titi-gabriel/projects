package bullets;

import entities.Entity;
import entities.creatures.Player;
import main.Handler;

import java.awt.*;
import java.lang.reflect.AnnotatedType;
import java.util.ArrayList;
import java.util.Iterator;

public class AttackManager {
    private Handler handler;
    private Player player;
    private ArrayList<Attack> attacks; //toate atacurile unei entitati


    public AttackManager(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
        attacks = new ArrayList<Attack>();

    }

    public void tick()
    {
        Iterator<Attack> it = attacks.iterator();
        while(it.hasNext())
        {
            Attack a = it.next();
            a.tick(); //actualizeaza variabilele atacului
            if (!a.isActive())
                it.remove(); //elimina atacul daca acesta a fost incetat
        }
    }

    public void render(Graphics g)
    {
        for(Attack a : attacks)
        {
            a.render(g); //afiseaza atacul pe ecran
        }
    }

    public void addAttack(Attack a)
    {
        this.attacks.add(a);
    } //adauga un atac in lista

    //Getters Setters


    public ArrayList<Attack> getAttacks() {
        return attacks;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
