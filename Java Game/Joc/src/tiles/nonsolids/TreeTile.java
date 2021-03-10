package tiles.nonsolids;

import gfx.Assets;
import tiles.Tile;

import java.awt.image.BufferedImage;

public class TreeTile extends Tile {
    public TreeTile(int id) {
        //initializare cu textura potrivita
        super(Assets.tree, id);
        //modificarea dimensiunilor Tileului
        actualHeight = 2*actualHeight;
        actualWidth = 2*actualWidth;
    }
}
