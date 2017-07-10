package pressjumptospace.entity.meta;

import pressjumptospace.util.Util;

public abstract class AbstractDynamicComponent {

    public AbstractDynamicComponent(int x_, int y_, int width_, int height_, int offsetX_, int offsetY_) {
        x = x_;
        y = y_;
        width = width_;
        height = height_;
        offsetX = offsetX_;
        offsetY = offsetY_;
    }
    public AbstractDynamicComponent(int x_, int y_, int offsetX_, int offsetY_) {
        x = x_;
        y = y_;
        width = 16;
        height = 16;
        offsetX = offsetX_;
        offsetY = offsetY_;
    }
    public AbstractDynamicComponent(int x_, int y_) {
        x = x_;
        y = y_;
        width = 16;
        height = 16;
        offsetX = 0;
        offsetY = 0;
    }

    public int x;
    public int y;
    public int width;
    public int height;
    public int offsetX;
    public int offsetY;

    public int pos(char axis) {
        switch (axis) {
            case 'x':
                return this.x + this.offsetX;
            case 'y':
                return this.y + this.offsetY;
            default:
                Util.err("Illegal axis value for dynamic component positioning.");
                return -1337;
        }
    }
}