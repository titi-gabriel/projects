package worlds;

import entities.statics.Orb;
import entities.statics.Portal;
import main.Handler;
import state.GameStateBoss2;

public class World2 extends World
{

    public World2(Handler handler, String path)
    {
        super(handler,path);
        //adaugare entitati pe mapa
        entityManager.addEntity(new Orb(handler, 22*80,3*80));
        entityManager.addEntity(new Orb(handler, 17*80,385));
        entityManager.addEntity(new Orb(handler, 43*80,0));
        entityManager.addEntity(new Orb(handler, 3*80,2*80));
        entityManager.addEntity(new Orb(handler, 29*80,2*80));
        entityManager.addEntity(new Orb(handler, 36*80,8*80));
        entityManager.addEntity(new Orb(handler, 45*80,2*80));
        entityManager.addEntity(new Orb(handler, 47*80,7*80));
        entityManager.addEntity(new Orb(handler, 13*80,8*80));
        entityManager.addEntity(new Orb(handler, 80,6*80));
        entityManager.addEntity(new Orb(handler, 4*80,10*80));

        //adaugare portal(statuie) pe mapa
        entityManager.setPortal(new Portal(handler, 3680, 4*80));
    }

}
