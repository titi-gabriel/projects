package inventory;

import gfx.Assets;
import gfx.Text;
import items.Item;
import main.Handler;

import java.awt.*;
import java.util.ArrayList;

public class Inventory
{

    Handler handler;
    private ArrayList<Item> inventoryItems; //lista cu itemele din inventar

    public Inventory(Handler handler)
    {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();

        addItem(Item.pergamentItem.createNew(0)); //se adauga in lista 0 iteme de tip pergament
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        //desenare pe ecran a inventarului
        g.drawImage(Assets.inventoryscreen, 1120,895,160,80,null);
        //desenare pe ecran a numarului de iteme(pergamente) colectionate
        Text.drawString(g, inventoryItems.get(0).getCount()+"/10", 1175, 927, true, Color.BLACK, Assets.font30);
    }

    //adaugare item in lista
    public void addItem(Item item)
    {
        //se verifica daca itemul este deja in inventar
        for(Item i : inventoryItems)
        {
            if(i.getId() == item.getId())
            {
                i.setCount(i.getCount() + item.getCount()); //daca este deja in inventar se actualizeaza numarul de iteme
                return;
            }
        }
        inventoryItems.add(item); //daca nu este in inventar, se adauga la lista
    }


    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int checkCount()
    {
        return inventoryItems.get(0).getCount();
    }
}
