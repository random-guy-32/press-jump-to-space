package pressjumptospace.tile.misc;

import pressjumptospace.tile.meta.Material;
import pressjumptospace.tile.meta.Tile;

public class Pedestal extends Tile {
    public Pedestal() {
        super(new boolean[] {true, false, true, false}, new int[] {0, 0, 0, 0}, Material.Metal, "tile/pedestal.png", "Pedestal");
    }
}
