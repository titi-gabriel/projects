package entities.statics;

import entities.Entity;
import main.Handler;

//clasa pentru entitatile care nu se misca

public abstract class StaticEntity extends Entity {

    public StaticEntity(Handler handler, float x, float y, int width, int height)
    {
        super(handler,x,y,width,height);
    }



}
