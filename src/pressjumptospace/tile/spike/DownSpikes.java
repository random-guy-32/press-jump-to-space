package pressjumptospace.tile.spike;

import pressjumptospace.tile.meta.SpikeTile;

/**
 * A spike tile pointing down.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class DownSpikes extends SpikeTile {
    /**
     * Standard constructor.
     */
    public DownSpikes() {
        super(new int[] {0, 0, 1, 0}, "tile/spikes-down.png", "Down Spikes");
    }
}
