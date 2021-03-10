package tiles.solids;

import gfx.Assets;
import tiles.Tile;

public class Lava4Tile extends Tile {
    //initializare cu textura potrivita
    public Lava4Tile(int id)
    {
        super(Assets.lava4, id);
    }
    //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
    public boolean isSolid()
    {
        return true;
    }
}
