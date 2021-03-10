package state;

import main.Game;
import main.Handler;

import java.awt.*;

public abstract class State {

    //dimensiuni fereastra
    public int width = 1280;
    public int height = 960;
    //Stateul curent
    private static State currentState = null;
    //nivelul curent
    public static int level;

    //setare State
    public static void setState(State state)
    {
        currentState = state;
    }
    //CLASS
    protected Handler handler;
    public State(Handler handler)
    {
        this.handler = handler;
    }

    public static State getState()
    {
        return currentState;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
