package tiles.solids;

import gfx.Assets;
import tiles.Tile;

public class Lava1Tile extends Tile {
    public Lava1Tile(int id)
    {
        //initializare cu textura potrivita
        super(Assets.lava1, id);
    }
    //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
    public boolean isSolid()
    {
        return true;
    }
}

