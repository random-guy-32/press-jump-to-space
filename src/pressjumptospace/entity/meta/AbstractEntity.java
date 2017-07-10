package pressjumptospace.entity.meta;

import pressjumptospace.util.Util;

public abstract class AbstractEntity extends DynamicObject {
    // what is the point of this class?
    public AbstractEntity(int x_, int y_, boolean[] solid_, int[] hurts_, String sprite_) {
        super(solid_, hurts_, sprite_);
        sprite.x = x_;
        sprite.y = y_;
        x = x_;
        y = y_;
    }

    public int x;
    public int y;

    public void updateSpriteLocation() {
        this.sprite.x = this.x;
        this.sprite.y = this.y;
    }
    public void updateHitboxLocation() {
        this.hitbox.x = this.x;
        this.hitbox.y = this.y;
    }
}