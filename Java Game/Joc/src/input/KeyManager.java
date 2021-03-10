package input;

import gfx.Animation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyManager implements KeyListener
{

    private boolean[] keys; //vector ce continte taste
    public boolean up,down, left, right,enter,punch,esc, kick,n; //denumirea actiunii tastei
    public KeyManager()
    {
        keys = new boolean[500];
    }

    public void tick()
    {
        //actualizare taste
        up = keys[KeyEvent.VK_W];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        down = keys[KeyEvent.VK_S];
        enter = keys[KeyEvent.VK_ENTER];
        punch = keys[KeyEvent.VK_P];
        kick = keys[KeyEvent.VK_L];
        esc = keys[KeyEvent.VK_ESCAPE];
        n = keys[KeyEvent.VK_N];
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //apasare tasta

        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;

        //se retine in vectorul de flaguri ca o tasta a fost apasata.
        keys[e.getKeyCode()] = true;

        //se verifica animatiile actiunii realizate de fiecare tasta
        if(e.getKeyCode() == KeyEvent.VK_P)
            Animation.punchNow = false;
        else
            if(e.getKeyCode() == KeyEvent.VK_L)
                Animation.kickNow = false;
            else
                if(e.getKeyCode() == KeyEvent.VK_K)
                    Animation.kunaiNow = false;
                else
                    if(e.getKeyCode() == KeyEvent.VK_O)
                        Animation.rasenganNow = false;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //tasta a fost apasata si eliberata

        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;

        //se retine in vectorul de flaguri ca o tasta a fost eliberata.
        keys[e.getKeyCode()] = false;

        //se verifica animatiile actiunii realizate de fiecare tasta
        //animatia acum va putea fi folosita
        if(e.getKeyCode() == KeyEvent.VK_P) {
            Animation.punchNow = true;
            Animation.kickNow = false;
            Animation.kunaiNow = false;
            Animation.rasenganNow = false;
        }
        else
            if(e.getKeyCode() == KeyEvent.VK_L) {
                Animation.kickNow = true;
                Animation.punchNow = false;
                Animation.kunaiNow = false;
                Animation.rasenganNow = false;
            }
            else
                if(e.getKeyCode() == KeyEvent.VK_K)
                {
                    Animation.kickNow = false;
                    Animation.punchNow = false;
                    Animation.kunaiNow = true;
                    Animation.rasenganNow = false;
                }
                else
                    if(e.getKeyCode() == KeyEvent.VK_O)
                    {
                        Animation.rasenganNow = true;
                        Animation.kickNow = false;
                        Animation.punchNow = false;
                        Animation.kunaiNow = false;
                    }
    }
}
