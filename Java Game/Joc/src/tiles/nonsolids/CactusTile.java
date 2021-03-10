package tiles.nonsolids;

import gfx.Assets;
import tiles.Tile;

import java.awt.image.BufferedImage;

public class CactusTile extends Tile {
    //initializare cu textura potrivita
    public CactusTile( int id) {
        super(Assets.cactus, id);
    }
}
