package main;

import database.SLGame;
import gfx.GameCamera;
import input.KeyManager;
import input.MouseManager;
import state.*;
import display.Display;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable{

    private Display display; //fereastra si canvasul
    private int width, height; //dimensiunile ferestrei
    public String title; //titlul jocului
    private boolean running = false; //folosit pentru a vedea daca ruleaza sau nu programul

    private Thread thread; //Referinta catre thread-ul de update si draw al ferestrei

    private BufferStrategy bs;//Referinta catre un mecanism cu care se organizeaza memoria complexa pentru un canvas.
    private Graphics g; //Referinta catre un context grafic.

    /// Sunt cateva tipuri de "complex buffer strategies", scopul fiind acela de a elimina fenomenul de
    /// flickering (palpaire) a ferestrei.
    /// Modul in care va fi implementata aceasta strategie in cadrul proiectului curent va fi triplu buffer-at

    ///                         |------------------------------------------------>|
    ///                         |                                                 |
    ///                 ****************          *****************        ***************
    ///                 *              *   Show   *               *        *             *
    /// [ Ecran ] <---- * Front Buffer *  <------ * Middle Buffer * <----- * Back Buffer * <---- Draw()
    ///                 *              *          *               *        *             *
    ///                 ****************          *****************        ***************
    //INPUT
    private KeyManager keyManager; //referinta catre mecanismul prin care functioneaza tastatura
    private MouseManager mouseManager;//referinta catre mecanismul prin care functioneaza mouseul

    //STATES
    private static State loadingState; //primul statiu in care va fi jocul va fi cel de Loading

    //CAMERA
    private GameCamera gameCamera; //camera jocului

    //HANDLER
    private Handler handler; //mecanism prin care usuram organizarea

    //SCORE
    private static int score; //scorul

    public Game(String title, int width, int height)
    {
        this.height = height;
        this.width = width;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        this.score = 0;
    }

    private void init()
    {
        display = new Display(title,width,height); //se construieste fereastra grafica
        display.getFrame().addKeyListener(keyManager); //Sa ataseaza ferestrei managerul de tastatura pentru a primi evenimentele furnizate de fereastra.
        display.getFrame().addMouseListener(mouseManager);//Sa ataseaza ferestrei managerul de mouse pentru a primi evenimentele furnizate de fereastra.
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init(); //se incarca elementele grafice
        handler = new Handler(this);
        gameCamera = new GameCamera(handler,0,0);

        loadingState = new LoadingState(handler);
        State.setState(loadingState); //setarea stateului cu care va incepe jocul
    }


    private void tick() //functie in care se vor actualiza toate variabilele
    {
        keyManager.tick(); //determina starea tastelor
        if(State.getState() != null)//Trebuie obtinuta starea curenta pentru care urmeaza a se actualiza starea, atentie trebuie sa fie diferita de null.
        {
            State.getState().tick();//Actualizez starea curenta a jocului daca exista.
        }
    }

    private void render()//Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.
    {
        bs = display.getCanvas().getBufferStrategy();// Returnez bufferStrategy pentru canvasul existent
        if(bs == null)// Verific daca buffer strategy a fost construit sau nu
        {
            display.getCanvas().createBufferStrategy(3);// Se construieste tripul buffer
            return;
        }
        g = bs.getDrawGraphics();// Se obtine contextul grafic curent in care se poate desena.
        g.clearRect(0,0,width,height);//se sterge ce era

        //incep a desena

        if(State.getState() != null)
        {
            State.getState().render(g);
        }
        //termin a desena

        bs.show();//se afiseaza pe ecran
        g.dispose();// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
                    // elementele grafice ce au fost desenate pe canvas).
    }

    @Override
    public void run()
    {
        // Initializeaza obiectul game
        init();

        int fps = 60; //Constanta intreaga initializata cu numarul de frame-uri pe secunda.
        double timePerTick = 1000000000 / fps; // Durata unui frame in nanosecunde.
        double delta = 0;
        long now; //Retine timpul curent de executie.
        long lastTime = System.nanoTime(); //Retine timpul in nanosecunde aferent frame-ului anterior.
        long timer = 0;
        int ticks = 0;

        while(running)
        {
            now = System.nanoTime();
            delta += (now-lastTime) / timePerTick; //Cat timp avem ca sa apelam din nou tick() si render().
                                                   //Spune cand sa apeleze si cand nu tick() si render().
            timer += now - lastTime; //cat timp a trecut de cand am apelat liniile de cod din acest block.
            lastTime = now;
            if(delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000)
            {
                //System.out.println("Ticks and Frames :" + ticks); //frames per second, de cate ori a fost actualizat ecranul intr o sec.
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera(){ return gameCamera;}

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start()
    {
        if(running)
            return;
        running = true;// Se actualizeaza flagul de stare a threadului
        // Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
        /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
        thread = new Thread(this);
        thread.start();// Threadul creat este lansat in executie (va executa metoda run())
    }
    public synchronized void stop()
    {
        if(!running)
            return;
        running = false;// Actualizare stare thread
        try
        {
            // Metoda join() pune un thread in asteptare pana cand un altul isi termina executie.
            // Totusi, in situatia de fata efectul apelului este de oprire a threadului.
            thread.join();
        }
        catch(InterruptedException e)
        {
            // In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
            e.printStackTrace();
        }
    }


    public static State getLoadingState() {
        return loadingState;
    }

    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Game.score = score;
    }
}
