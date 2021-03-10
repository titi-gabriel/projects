package tiles.solids;

import gfx.Assets;
import tiles.Tile;

public class LavaBoxTile extends Tile {
    //initializare cu textura potrivita
    public LavaBoxTile(int id)
    {
        super(Assets.lavabox, id);
    }
    //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
    public boolean isSolid()
    {
        return true;
    }
}
