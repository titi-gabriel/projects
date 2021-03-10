package input;

import ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

    private boolean leftPressed, rightPressed; //apasare click stanga/dreapta
    private int mouseX, mouseY; //pozitia mouseului pe ecran
    private UIManager uiManager; //manager de butoane

    public MouseManager()
    {

    }

    public void setUiManager(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    //Getters
    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //mouseul a fost apasat
        if(e.getButton() == MouseEvent.BUTTON1)
            leftPressed = true;
        else
            if (e.getButton() == MouseEvent.BUTTON3)
                rightPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //mouseul a fost eliberat
        if(e.getButton() == MouseEvent.BUTTON1)
            leftPressed = false;
        else
        if (e.getButton() == MouseEvent.BUTTON3)
            rightPressed = false;

        if(uiManager != null)
            uiManager.onMouseRelease(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //pozitia pe ecran a mouseului
        mouseX = e.getX();
        mouseY = e.getY();
        if(uiManager != null)
            uiManager.onMouseMove(e);
    }
}
