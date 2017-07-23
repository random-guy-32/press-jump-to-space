package pressjumptospace.entity.meta;

/**
 * Parent class for everything that can be collected by the player.
 * Items disappear once collected.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class Item extends Entity {
    /**
     * Standard Constructor.
     *
     * @param x_ Horizontal position.
     * @param y_ Vertical position.
     * @param hitboxWidth Width.
     * @param hitboxHeight Height.
     * @param hitboxOffsetX Bounding box offset from item's x position.
     * @param hitboxOffsetY Bounding box offset from item's y position.
     * @param sprite_ Sprite source.
     * @param willFall_ Whether this item is subject to the pull of gravity.
     */
    public Item(int x_, int y_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, boolean willFall_) {
        super(x_, y_, new boolean[]{false, false, false, false}, new int[] {0, 0, 0, 0}, new boolean[] {false, false, false, false}, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, (byte) 0, willFall_, 1, 1, false, false);
    }
}
