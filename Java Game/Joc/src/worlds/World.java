package worlds;

import bullets.AttackManager;
import entities.EntityManager;
import entities.creatures.Enemy;
import entities.creatures.Player;
import entities.statics.Orb;
import gfx.Assets;
import items.ItemManager;
import main.Handler;
import tiles.Tile;
import utils.Utils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class World {

    protected Handler handler;
    protected int width, height; //dimensiuni
    protected int spawnX, spawnY; //coordonatele de start a playerului
    protected int[][] tiles;

    //Entities
    protected EntityManager entityManager; //manager de entitati

    //Items
    protected ItemManager itemManager; //manager de iteme

    //Attacks
    protected AttackManager attackManager; //manager de atacuri

    public World(Handler handler, String path)
    {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 0,0));
        attackManager = new AttackManager(handler, new Player(handler, 0,0));
        itemManager = new ItemManager(handler);
        loadWorld(path); //incarca lume
        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick()
    {
        //actualizare manageri
        itemManager.tick();
        entityManager.tick();
        attackManager.tick();
    }

    public void render(Graphics g, BufferedImage bg)
    {
        //desenare lume
        //deplasare lume cand playerul se misca
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffSet() / Tile.TILEWIDTH -1);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffSet() + handler.getWidth()) / Tile.TILEWIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffSet() / Tile.TILEHEIGHT -1);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffSet() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
        //desemare fundal

        g.drawImage(bg,0,0,1280,960,null);
        //desenare fiecare Tile
        for(int y = yStart; y<yEnd; y++)
        {
            for(int x = xStart; x<xEnd; x++)
            {
                getTile(x,y).render(g, (int)(x*Tile.TILEWIDTH - handler.getGameCamera().getxOffSet()),
                        (int)(y*Tile.TILEHEIGHT - handler.getGameCamera().getyOffSet()) );

            }
        }


        //desenare Entities
        entityManager.render(g);
        //desenare Attacks
        attackManager.render(g);
        //desenare Items
        itemManager.render(g);
        //desenare HUD
        getEntityManager().getPlayer().getHealthBar().render(g, 112,38);
        getEntityManager().getPlayer().getInventory().render(g);
        getEntityManager().getPlayer().getChakraBar().render(g,89,61);
        g.drawImage(Assets.hud,0,0,440,78,null);

    }

    public Tile getTile(int x, int y)
    {
        //tileurile care nu pot fi vazute vor fi desenate ca tile-uri transparente
        if(x<0 || y<0 || x>=width || y>=height)
            return Tile.transparentTile;
        //returneaza tileul de la idul specificat in text de la pozitia x, y
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.transparentTile;
        }
        return t;
    }

    //incarcare mapa
    protected void loadWorld(String path)
    {
        //transformare in String a fisierului
        String file = Utils.loadFileAsString(path);
        //se imparte textul dupa spatii
        String[] tokens = file.split("\\s+");

        width = Utils.parseInt(tokens[0]); //primul numar din fisierul text reprezinta latimea mapei
        height = Utils.parseInt(tokens[1]);//al doilea numar din fisierul text reprezinta inaltimea mapei
        spawnX = Utils.parseInt(tokens[2]);//al treilea numar din fisierul text reprezinta coordonata X la care va incepe playerul
        spawnY = Utils.parseInt(tokens[3]);//al patrulea numar din fisierul text reprezinta coordonata Y la care va incepe playerul

        //initializare vector de tiles
        tiles = new int[width][height];
        //se citeste din fisierul text urmatoarele numere pe linii si coloane
        for(int y = 0; y<height; y++)
        {
            for(int x = 0; x<width; x++)
            {
                tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    public AttackManager getAttackManager() { return attackManager; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getTiles() {
        return tiles;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
