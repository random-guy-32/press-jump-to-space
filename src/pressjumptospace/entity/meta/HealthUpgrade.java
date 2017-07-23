package pressjumptospace.entity.meta;

/**
 * A collectible item that restores a certain amount of health to the player.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class HealthUpgrade extends Item {
    /**
     * Default constructor.
     *
     * @param x_ Horizontal position.
     * @param y_ Vertical position.
     * @param hitboxWidth Width of bounding box.
     * @param hitboxHeight Height of bounding box.
     * @param hitboxOffsetX Bounding box's offset from item's horizontal position.
     * @param hitboxOffsetY Bound box's offset from item's vertical position.
     * @param sprite_ Sprite source.
     * @param willFall_ Whether this item is subject to the pull of gravity.
     * @param heals_ The amount of HP this item heals upon collection.
     */
    public HealthUpgrade(int x_, int y_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, boolean willFall_, int heals_) {
        super(x_, y_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, willFall_);
        heals = heals_;
    }

    /**
     * The amount of HP this item heals upon collection.
     * Negative values will damage the player instead without triggering damage cooldown.
     */
    public int heals;
}
