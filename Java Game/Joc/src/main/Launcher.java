package main;

import main.Game;

public class Launcher {
    public static void main(String[] args)
    {
        Game game = new Game("Naruto si vulpea cu 9 cozi",1280, 960); //Rezolutie optima pentru afisarea optimizata a jocului
        //Game game = new Game("Naruto si vulpea cu 9 cozi",1280, 720);
        game.start();
    }
}
