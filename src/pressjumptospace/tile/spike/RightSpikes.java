package pressjumptospace.tile.spike;

import pressjumptospace.tile.meta.SpikeTile;

/**
 * A spike tile pointing right.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class RightSpikes extends SpikeTile {
    /**
     * Standard constructor.
     */
    public RightSpikes() {
        super(new int[] {0, 1, 0, 0}, "tile/spikes-right.png", "Right Spikes");
    }
}
