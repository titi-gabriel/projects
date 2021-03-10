package ui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

    private BufferedImage[] images; //texturile butonului atunci cand mouseul este deasupra si cand nu
    private ClickListener clicker; //folosit pentru verificarea apasarii mouseului
    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker)
    {
        super(x,y,width,height);
        this.images = images;
        this.clicker = clicker;
    }
    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        //desenare buton
        if(hovering) //daca mouseul este deasupra
            g.drawImage(images[1], (int)x, (int)y, width,height,null);
        else //daca mouseul nu este deasupra
            g.drawImage(images[0], (int)x, (int)y, width,height,null);
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
