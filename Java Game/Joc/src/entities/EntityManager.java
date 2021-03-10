package entities;

import entities.creatures.Enemy;
import entities.creatures.Enemy2;
import entities.creatures.Player;
import entities.statics.Portal;
import main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EntityManager
{

    private Handler handler;
    private Player player;
    private ArrayList<Entity> entities; //lista care va contine toate entitatile
    private Portal portal; //pentru a trece la urmatorul nivel va fi nevoie sa fie distrus portalul(statuia)
    private Enemy boss1; //primul inamic
    private Enemy2 boss2; //al doilea inamic


    public EntityManager(Handler handler, Player player)
    {
        this.handler = handler;
        this.player = player;
        entities = null;
        entities = new ArrayList<Entity>();
        addEntity(player); //playerul reprezinta o entitate, deci va trebui adaugat in lista entitatilor
    }

    public void tick()
    {
        //cat timp sunt entitati in lista, le vom actualiza variabilele
        Iterator<Entity> it = entities.iterator();
        while(it.hasNext())
        {
            Entity e = it.next();
            e.tick();
            if (!e.isActive()) //daca entitate nu mai este activa, va fi eliminata din lista
                it.remove();
        }
    }

    public void render(Graphics g)
    {
        //fiecare entitate va fi desena pe ecran
        for(Entity e : entities)
        {
            e.render(g);
        }
    }

    public void addEntity(Entity e)
    {
        this.entities.add(e);
    } //adaugarea unei entitati in lista

    //Getters Setters


    public ArrayList<Entity> getEntities() {
        return entities;
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

    public Portal getPortal() {
        return portal;
    }

    public void setPortal(Portal portal) {
        this.portal = portal;
        this.addEntity(this.portal);
    }

    public Enemy getBoss1() {
        return boss1;
    }

    public void setBoss1(Enemy boss1) {
        this.boss1 = boss1;
        addEntity(boss1);
    }

    public Enemy2 getBoss2() {
        return boss2;
    }

    public void setBoss2(Enemy2 boss2) {
        this.boss2 = boss2;
        addEntity(boss2);
    }
}
