package pressjumptospace.tile.spike;

import pressjumptospace.tile.meta.SpikeTile;

/**
 * A spike tile that hurts in all directions.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class SpikyBall extends SpikeTile {
    /**
     * Standard constructor.
     */
    public SpikyBall() {
        super(new int[] {1, 1, 1, 1}, "tile/spiky-ball.png", "Spiky Ball");
    }
}
