package pressjumptospace.tile.spike;

import pressjumptospace.tile.meta.SpikeTile;

/**
 * A spike tile pointing up.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class UpSpikes extends SpikeTile {
    /**
     * Standard constructor.
     */
    public UpSpikes() {
        super(new int[] {1, 0, 0, 0}, "tile/spikes-up.png", "Up Spikes");
    }
}
