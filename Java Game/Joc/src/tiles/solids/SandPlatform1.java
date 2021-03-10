package tiles.solids;

import gfx.Assets;
import tiles.Tile;

import java.awt.image.BufferedImage;

public class SandPlatform1 extends Tile {
    //initializare cu textura potrivita
    public SandPlatform1(int id) {

        super(Assets.earth1, id);
    }
    //suprascrierea metodei pentru a returna true, deoarece acest Tile este solid
    public boolean isSolid()
    {
        return true;
    }
}
