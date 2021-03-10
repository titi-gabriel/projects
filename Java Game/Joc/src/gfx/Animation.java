package gfx;

import java.awt.image.BufferedImage;

public class Animation
{
    //indexul animatiei, viteza cu care se face animatia, cat de des se poate face animatia
    private int index, speed, cooldown;
    //timer pt animatie
    private long lastTime, timer;
    //vector pentru frameurile fiecarei animatii
    private BufferedImage[] frames;

    //starea fiecarei animatii
    public static boolean punchNow = false;
    public static boolean kickNow = false;
    public static boolean kunaiNow = false;
    public static boolean punchYes = false;
    public static boolean kickYes = false;
    public static boolean kunaiYes = false;
    public static boolean rasenganNow = false;
    public static boolean rasenganYes = false;
    public static boolean sandBallNow = false;
    public static boolean sandBallYes = false;

    //se folosesc 2 constructori, unul pentru animatiile care pot fi efectuate mai rar
    //si unul pentru animatiile care pot fi efectuate in orice moment
    public Animation(int speed, BufferedImage[] frames, int cooldown)
    {
        lastTime = System.currentTimeMillis();
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        this.cooldown = cooldown;

    }
    public Animation(int speed, BufferedImage[] frames)
    {
        lastTime = System.currentTimeMillis();
        this.frames = frames;
        this.speed = speed;
        index = 0;
        timer = 0;
        this.cooldown = 0;
    }

    public void tick()
    {
        //actualizare animatie

        //timerul pentru a verifica cand se trece la urmatorul frame al animatiei
        timer += System.currentTimeMillis() - lastTime;
        //ultima data cand s-a actualizat
        lastTime = System.currentTimeMillis();

        if(timer > speed) //daca au trecut destule secunde se va putea trece la urmatorul frame
        {

            index++; //se creste indexul pentru vector
            timer = 0; //se reseteaza timerul
            if(index >= frames.length) //daca s-a trecut prin toate frameurile indexul trebuie resetat
            {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame()
    {
        return frames[index];
    } //returneaza frameul de la indexul curent

    public int getSpeed() {
        return speed;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public BufferedImage[] getFrames() {
        return frames;
    }
}
