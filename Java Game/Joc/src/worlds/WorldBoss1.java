package worlds;

import entities.creatures.Enemy;
import entities.statics.Orb;
import main.Handler;

public class WorldBoss1 extends World {
    public WorldBoss1(Handler handler, String path)
    {
        super(handler,path);
        //setare inamic pentru mapa primului inamic
        entityManager.setBoss1(new Enemy(handler, 11*80,10*80));
    }
}
