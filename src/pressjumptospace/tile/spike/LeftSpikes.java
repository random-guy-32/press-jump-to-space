package pressjumptospace.tile.spike;

import pressjumptospace.tile.meta.SpikeTile;

/**
 * A spike tile pointing left.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */
public class LeftSpikes extends SpikeTile {
    /**
     * Standard constructor.
     */
    public LeftSpikes() {
        super(new int[] {0, 0, 0, 1}, "tile/spikes-left.png", "Left Spikes");
    }
}
