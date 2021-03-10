package tiles.solids;

import gfx.Assets;
import tiles.Tile;

public class Lava7Tile extends Tile {
    //initializare cu textura potrivita
    public Lava7Tile(int id)
    {
        super(Assets.lava7, id);
    }
    //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
    public boolean isSolid()
    {
        return true;
    }
}
