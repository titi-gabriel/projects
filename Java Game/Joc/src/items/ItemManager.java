package items;

import main.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager
{

    private Handler handler;
    private ArrayList<Item> items; //lista cu itemele de pe harta

    public ItemManager(Handler handler)
    {
        this.handler = handler;
        items = new ArrayList<Item>();
    }

    public void tick()
    {
        Iterator<Item> it = items.iterator();
        while (it.hasNext())
        {
            Item i = it.next();
            //actualizare item
            i.tick();
            if(i.isPickedUp()) //daca itemul a fost ridicat, va disparea de pe harta
            {
                it.remove();
            }
        }
    }

    //desenare item pe harta
    public void render(Graphics g)
    {
        for( Item i : items)
        {
            i.render(g);
        }
    }

    //adaugare item in lista
    public void addItem(Item i)
    {
        i.setHandler(this.handler);
        items.add(i);
    }

    public Handler getHandler() {
        return handler;
    }
}
