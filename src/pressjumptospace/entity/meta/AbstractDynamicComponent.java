package pressjumptospace.entity.meta;

import pressjumptospace.util.Util;

/**
 * Parent class for hitboxes and sprites.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class AbstractDynamicComponent {
    /**
     * Standard constructor.
     *
     * @param x_ The x coordinate.
     * @param y_ The y coordinate.
     * @param width_ The width of the component.
     * @param height_ The height of the component.
     * @param offsetX_ The horizontal offset of the component from its container.
     * @param offsetY_ The vertical offset of the component from its container.
     */
    public AbstractDynamicComponent(int x_, int y_, int width_, int height_, int offsetX_, int offsetY_) {
        x = x_;
        y = y_;
        width = width_;
        height = height_;
        offsetX = offsetX_;
        offsetY = offsetY_;
    }

    /**
     * Standard constructor with default size values.
     *
     * @param x_ The x coordinate.
     * @param y_ The y coordinate.
     * @param offsetX_ The horizontal offset of the component from its container.
     * @param offsetY_ The vertical offset of the component from its container.
     */
    public AbstractDynamicComponent(int x_, int y_, int offsetX_, int offsetY_) {
        x = x_;
        y = y_;
        width = 16;
        height = 16;
        offsetX = offsetX_;
        offsetY = offsetY_;
    }

    /**
     * Standard constructor with default size and offset values.
     *
     * @param x_ The x coordinate.
     * @param y_ The y coordinate.
     */
    public AbstractDynamicComponent(int x_, int y_) {
        x = x_;
        y = y_;
        width = 16;
        height = 16;
        offsetX = 0;
        offsetY = 0;
    }

    /**
     * Horizontal position. Equal to its container's position.
     */
    public int x;

    /**
     * Vertical position. Equal to its container's position.
     */
    public int y;

    /**
     * Horizontal size. Default is 16.
     */
    public int width;

    /**
     * Vertical size. Default is 16.
     */
    public int height;

    /**
     * Horizontal offset from its container's position. Default is 0 (equal position).
     */
    public int offsetX;

    /**
     * Vertical offset from its container's position. Default is 0 (equal position).
     */
    public int offsetY;

    /**
     * Returns component's true position, calculated from the position of its container object and its relative offset.
     *
     * @param axis The axis of the coordinate pair (either 'x' or 'y').
     * @return The coordinate.
     */
    public int pos(char axis) {
        switch (axis) {
            case 'x':
                return this.x + this.offsetX;
            case 'y':
                return this.y + this.offsetY;
            default:
                Util.err("Illegal axis value for dynamic component positioning.");
                // Yes, I'm very funny.
                return -1337;
        }
    }
}