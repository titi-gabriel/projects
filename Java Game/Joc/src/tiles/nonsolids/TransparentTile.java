package tiles.nonsolids;

import gfx.Assets;
import tiles.Tile;

import java.awt.image.BufferedImage;

public class TransparentTile extends Tile {
    //initializare cu textura potrivita
    public TransparentTile(int id) {
        super(Assets.transparent, id);
    }
}
