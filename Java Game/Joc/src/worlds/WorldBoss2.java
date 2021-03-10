package worlds;

import entities.creatures.Enemy;
import entities.creatures.Enemy2;
import main.Handler;

public class WorldBoss2 extends World {
    public WorldBoss2(Handler handler, String path) {
        super(handler, path);
        //setare inamic pentru mapa celui de al doilea inamic
        entityManager.setBoss2(new Enemy2(handler, 12*80,8*80));
    }
}
