package display;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame; //fereastra
    private Canvas canvas; //"foaia" pe care vom "desena"

    private String title; //titlul jocului(ferestrei)
    private int width, height; //dimensiunile ferestrei

    public Display(String title, int width, int height)
    {
        this.title = title;
        this.width = width;
        this.height = height;

        createDisplay();
    }


    private void createDisplay() //creaza fereastra si canvasul
    {
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //la apasarea butonului X fereastra se va inchide
        frame.setResizable(false); //nu putem modifica dimensiunile ferestrei
        frame.setLocationRelativeTo(null); //centreaza fereastra
        frame.setVisible(true); //face vizibila fereastra

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height)); //dimensiunea canvasului
        canvas.setMaximumSize(new Dimension(width,height)); //dim maxima a canvasului
        canvas.setMinimumSize(new Dimension(width,height)); //dim minima a canvasului
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas()
    {
        return canvas;
    }
    public JFrame getFrame()
    {
        return frame;
    }
}
