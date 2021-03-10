package tiles.nonsolids;

import gfx.Assets;
import tiles.Tile;

public class LavaTreeTile extends Tile {
    public LavaTreeTile(int id)
    {
        //initializare cu textura potrivita
        super(Assets.lavatree, id);
        //modificarea dimensiunilor Tileului
        actualWidth = 2*actualWidth;
        actualHeight = 2*actualHeight;
    }
}
