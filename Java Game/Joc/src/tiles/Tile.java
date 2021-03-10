package tiles;

import tiles.nonsolids.*;
import tiles.solids.*;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    // De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
    // o singura data in memorie
    public static Tile[] tiles = new Tile[256];
    public static Tile cactusTile = new CactusTile(7);
    public static Tile signTile = new SignTile(6);
    public static Tile treeTile = new TreeTile(5);
    public static Tile boxTile = new BoxTile(4);
    public static Tile transparentTile = new TransparentTile(0);
    public static Tile sandPlatform1 = new SandPlatform1(1);
    public static Tile sandPlatform2 = new SandPlatform2(2);
    public static Tile sandPlatform3 = new SandPlatform3(3);
    public static Tile lava11 = new Lava1Tile(11);
    public static Tile lava22 = new Lava2Tile(22);
    public static Tile lava33 = new Lava3Tile(33);
    public static Tile lava44 = new Lava4Tile(44);
    public static Tile lava55 = new Lava5Tile(55);
    public static Tile lava66 = new Lava6Tile(66);
    public static Tile lava77 = new Lava7Tile(77);
    public static Tile lavaBox = new LavaBoxTile(93);
    public static Tile warningSign = new WarningSignTile(88);
    public static Tile skull = new SkullTile(99);
    public static Tile lavaTree = new LavaTreeTile(90);
    public static Tile bush1 = new Bush1Tile(91);
    public static Tile bush2 = new Bush2Tile(92);

    //
    protected BufferedImage texture; //textura
    protected final int id; //id
    public static final int TILEWIDTH = 80, TILEHEIGHT = 80; //dimensiuni standard Tile
    protected int actualWidth=80, actualHeight=80; //folosit pentru Tileurile care vor avea o alta dimensiune

    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;
        tiles[id] = this;
    }

    public int getId() {
        return id;
    }

    public void tick()
    {

    }

    public void render(Graphics g, int x, int y)
    {
        //desenare
        g.drawImage(texture, x,y, actualWidth, actualHeight,null);
    }

    //initial niciun tile nu este solid
    public boolean isSolid()
    {
        return false;
    }

    //returneaza un mix drepntunghi din partea de sus a Tileului folosit pentru verificarea coliziunii
    public Rectangle getTop( int xTile, int yTile)
    {
        Rectangle top = new Rectangle(xTile*Tile.TILEWIDTH+6,yTile*TILEHEIGHT,Tile.TILEWIDTH-12,6  );
        return top;
    }

}
