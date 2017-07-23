package pressjumptospace.entity.meta;

/**
 * Every tile and entity has one of these objects which represents its bounding box.
 * Hitboxes are used for collision and positioning calculations.
 * Hitbox size and location are separate from its parent's.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class Hitbox extends AbstractDynamicComponent {
    /**
     * Standard constructor with default values for positioning.
     *
     * @param solid_ Directional solidity.
     */
    public Hitbox(boolean[] solid_) {
        super(0, 0, 0, 0);
        solid = solid_;
    }

    /**
     * Standard constructor.
     *
     * @param x_ Horizontal position.
     * @param y_ Vertical position.
     * @param width_ Horizontal size.
     * @param height_ Vertical size.
     * @param offsetX_ Offset from parent's horizontal position.
     * @param offsetY_ Offset from parent's vertical positon.
     * @param solid_ Directional solidity.
     */
    public Hitbox(int x_, int y_, int width_, int height_, int offsetX_, int offsetY_, boolean[] solid_) {
        super(x_, y_, width_, height_, offsetX_, offsetY_);
        solid = solid_;
    }

    /**
     * Whether this hitbox is solid for other entities.
     * Can be set for each cardinal direction separately.
     */
    public boolean[] solid;
}