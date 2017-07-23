package pressjumptospace.entity.item;

import pressjumptospace.entity.meta.HealthUpgrade;

/**
 * An item that restores one health point to the player.
 * Entity ID is 5.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class Battery extends HealthUpgrade {
    /**
     * Standard constructor with default coordinates.
     */
    public Battery() {
        super(0, 0, 12, 15, 2, 1, "item/battery.png", false, 1);

        entityID = 5;
    }

    /**
     * Standard constructor.
     *
     * @param x_ The x coordinate.
     * @param y_ The y coordinate.
     */
    public Battery(int x_, int y_) {
        super(x_, y_, 12, 15, 2, 1, "item/battery.png", false, 1);

        entityID = 5;
    }
}
