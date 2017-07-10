package pressjumptospace.entity.meta;

public abstract class Hitbox extends AbstractDynamicComponent {
    public Hitbox(boolean[] solid_) {
        super(0, 0, 0, 0);
        solid = solid_;
    }
    public Hitbox(int x_, int y_, int width_, int height_, int offsetX_, int offsetY_, boolean[] solid_) {
        super(x_, y_, width_, height_, offsetX_, offsetY_);
        solid = solid_;
    }

    public boolean[] solid;
}