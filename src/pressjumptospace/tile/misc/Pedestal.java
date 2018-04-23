package pressjumptospace.tile.misc;

import pressjumptospace.tile.meta.Material;
import pressjumptospace.tile.meta.Tile;

/**
 * A decorative pedestal that can be used to display items.
 * Only solid from the top and bottom.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class Pedestal extends Tile {
    /**
     * Standard constructor.
     */
    public Pedestal() {
        super(new boolean[] {true, false, true, false}, new int[] {0, 0, 0, 0}, Material.Metal, "tile/pedestal.png", "Pedestal");
    }
}
