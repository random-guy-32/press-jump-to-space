package pressjumptospace.entity;

import pressjumptospace.entity.meta.Hitbox;

public class TileHitbox extends Hitbox {
    public TileHitbox(int x_, int y_, int width_, int height_, int offsetX_, int offsetY_, boolean[] solid_) {
        super(x_, y_, width_, height_, offsetX_, offsetY_, solid_);
    }
}
