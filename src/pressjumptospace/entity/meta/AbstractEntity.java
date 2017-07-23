package pressjumptospace.entity.meta;

/**
 * Parent class for all entities.
 * Not really sure why this is needed.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class AbstractEntity extends DynamicObject {
    // What is the point of this class?

    /**
     * Standard constructor.
     *
     * @param x_ The x coordinate.
     * @param y_ The y coordinate.
     * @param solid_ Directional solidity of this object's borders.
     * @param hurts_ Directional damage output.
     * @param sprite_ Sprite source.
     */
    public AbstractEntity(int x_, int y_, boolean[] solid_, int[] hurts_, String sprite_) {
        super(solid_, hurts_, sprite_);
        sprite.x = x_;
        sprite.y = y_;
        x = x_;
        y = y_;
    }

    /**
     * Horizontal position.
     */
    public int x;

    /**
     * Vertical position.
     */
    public int y;

    /**
     * Synchronizes the sprite's location to the entity's location.
     */
    public void updateSpriteLocation() {
        this.sprite.x = this.x;
        this.sprite.y = this.y;
    }

    /**
     * Synchronizes the hitbox's location the the entity's location.
     */
    public void updateHitboxLocation() {
        this.hitbox.x = this.x;
        this.hitbox.y = this.y;
    }
}