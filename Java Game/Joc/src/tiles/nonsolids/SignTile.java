package tiles.nonsolids;

import gfx.Assets;
import tiles.Tile;

import java.awt.image.BufferedImage;

public class SignTile extends Tile {
    //initializare cu textura potrivita
    public SignTile(int id) {
        super(Assets.sign, id);
    }
}
