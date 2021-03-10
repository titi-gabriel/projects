package tiles.nonsolids;

import gfx.Assets;
import tiles.Tile;

public class SkullTile extends Tile {
    //initializare cu textura potrivita
    public SkullTile(int id)
    {
        super(Assets.skull, id);
    }
}
