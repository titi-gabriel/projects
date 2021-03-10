package tiles.solids;

import gfx.Assets;
import tiles.Tile;

import java.awt.image.BufferedImage;

public class BoxTile extends Tile {

    public BoxTile(int id) {
        //initializare cu textura potrivita
        super(Assets.box, id);
    }

    @Override
    public boolean isSolid()
    {
        //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
        return true;
    }
}
