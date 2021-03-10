package tiles.solids;

import gfx.Assets;
import tiles.Tile;

public class Lava6Tile extends Tile {
    //initializare cu textura potrivita
    public Lava6Tile(int id)
    {
        super(Assets.lava6, id);
    }
    //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
    public boolean isSolid()
    {
        return true;
    }
}
