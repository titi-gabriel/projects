package worlds;

import bullets.AttackManager;
import entities.EntityManager;
import entities.creatures.Enemy;
import entities.creatures.Player;
import entities.statics.Orb;
import entities.statics.Portal;
import gfx.Assets;
import items.ItemManager;
import main.Handler;
import state.GameState2;
import state.GameStateBoss1;
import state.LoadingBoss1;
import tiles.Tile;
import utils.Utils;

import java.awt.*;

public class World1 extends World {

    public World1(Handler handler, String path)
    {
        super(handler,path);

        //adaugare entitati pe mapa
        entityManager.addEntity(new Orb(handler, 2*80, 4*80));
        entityManager.addEntity(new Orb(handler, 5*80, 2*80));
        entityManager.addEntity(new Orb(handler, 8*80, 10*80));
        entityManager.addEntity(new Orb(handler, 14*80, 7*80));
        entityManager.addEntity(new Orb(handler, 18*80, 5*80));
        entityManager.addEntity(new Orb(handler, 23*80, 4*80));
        entityManager.addEntity(new Orb(handler, 27*80, 3*80));
        entityManager.addEntity(new Orb(handler, 33*80, 6*80));
        entityManager.addEntity(new Orb(handler, 37*80, 3*80));
        entityManager.addEntity(new Orb(handler, 1*80, 8*80));

        //adaugare portal(statuie) pe mapa
        entityManager.setPortal(new Portal(handler, 3680, 6*80));
    }

}
