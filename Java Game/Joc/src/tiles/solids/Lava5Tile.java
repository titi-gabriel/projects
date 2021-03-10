package tiles.solids;

import gfx.Assets;
import tiles.Tile;

public class Lava5Tile extends Tile {
    //initializare cu textura potrivita
    public Lava5Tile(int id)
    {
        super(Assets.lava5, id);
    }
    //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
    public boolean isSolid()
    {
        return true;
    }
}
