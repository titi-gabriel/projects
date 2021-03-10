package items;

import gfx.Assets;
import main.Game;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    //HANDLER

    public static Item[] items = new Item[256];
    public static Item pergamentItem = new Item(Assets.pergament, "Pergament", 0); //initializare item

    //CLASS
    public static final int ITEMWIDTH =80, ITEMHEIGHT = 80; //dimensiuni standard ale itemului

    protected Handler handler;
    protected BufferedImage texture; //textura itemului
    protected String name; //numele itemului
    protected final int id; //idul de identificare a itemului

    protected int x, y, count; //pozitia si cantitatea itemului
    public double movedX, movedY; //deplasarea itemului
    protected boolean dirX, dirY; //directia de deplasare
    protected boolean last; //folosit pentru schimbarea directiei de deplasare(stanga, dreapta, sus, jos)

    protected Rectangle bounds; //hitbox item

    protected boolean pickedUp = false; //flag pentru verificare daca itemul a fost ridicat

    public Item(BufferedImage texture, String name, int id)
    {
        this.texture = texture;
        this.name = name;
        this.id = id;
        count = 1;

        items[id] = this;

        movedX = 0;
        movedY = 0;
        dirX = true;
        dirY = true;
        last = true;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);


    }

    //actualizare
    public void tick()
    {
        //id==0 -> pergament
        //pergamentul se misca pe ecran
        if(this.id == 0) {
            if(last)
            moveX(1,5);
            else
            moveY(1,10);
        }

        //verificare coliziune cu pergamentul
        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f,0f).intersects(bounds))
        {
            //daca este coliziune pergamentul va fi ridicat
            pickedUp = true;
            //se adauga pergamentul la inventar
            handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
            //ridicarea unui pergament creste scorul cu 10
            Game.setScore(Game.getScore() + 10);
        }

    }

    //desenare
    public void render(Graphics g)
    {
        if(handler == null)    return;
        render(g,(int)(x - handler.getGameCamera().getxOffSet()), (int)(y - handler.getGameCamera().getyOffSet()));
    }

    public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture,x,y,ITEMWIDTH, ITEMHEIGHT, null);
    }

    //aceesi miscare ca cea a primului inamic
    public void moveX(double speed,int distance)
    {
        if(dirX)
        {
            x+=speed;
            movedX += speed;
        }
        else
        {
            x -= speed;
            movedX -= speed;

        }

        if(movedX >= distance) {
            last = false;
            dirX = false;
        }
        else
            if(movedX == 0)
            {last = false;
                dirX = true;
            }
    }
    public void moveY(double speed, int distance)
    {
        if(!dirY)
        {
            y+=speed;
            movedY += speed;
        }
        else
        {
            y -= speed;
            movedY -= speed;
        }

        if(movedY <= -distance) {
            last = true;
            dirY = false;
        }
        else
        if(movedY == 0) {
            last = true;
            dirY = true;
        }
    }

    //creare item nou la o pozitie data
    public Item createNew(int x, int y)
    {
        Item i = new Item(texture,name,id);
        i.setPosition(x,y); //se seteaza pozitia itemului
        return i;
    }

    //create item cu o cantitate data
    public Item createNew(int count)
    {
        Item i = new Item(texture,name,id);
        i.setPickedUp(true);
        i.setCount(count);
        return i;
    }

    //setarea pozitiei itemului si a pozitiei hitboxului
    public void setPosition(int x, int y)
    {
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }


    //GETTERS SETTERS

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }
}
