package tiles.solids;

import gfx.Assets;
import tiles.Tile;

public class Lava3Tile extends Tile {
    //initializare cu textura potrivita
    public Lava3Tile(int id)
    {
        super(Assets.lava3, id);
    }
    //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
    public boolean isSolid()
    {
        return true;
    }
}
