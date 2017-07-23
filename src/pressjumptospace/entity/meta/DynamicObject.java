package pressjumptospace.entity.meta;

import pressjumptospace.render.Renderable;
import pressjumptospace.render.Sprite;
import pressjumptospace.util.Util;

import java.awt.*;

/**
 * Parent class to tiles and entities.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class DynamicObject implements Renderable {
    /**
     * Standard constructor.
     *
     * @param solid_ Directional solidity.
     * @param hurts_ Directional damage output.
     * @param sprite_ Sprite source.
     */
    public DynamicObject(boolean[] solid_, int[] hurts_, String sprite_) {
        sprite = new Sprite(sprite_);
        solid = solid_;
        hurts = hurts_;
    }

    /**
     * Sprite, including sprite source.
     */
    public Sprite sprite;

    /**
     * Bounding box.
     */
    public Hitbox hitbox;

    /**
     * Directional solidity.
     */
    public boolean[] solid;

    /**
     * Directional damage output.
     */
    public int[] hurts;

    /**
     * Allows retrieval of 'solid' property with a string argument rather than byte/char.
     * @deprecated Use byte or char argument instead.
     * @see #solid
     * @see Entity#dirToChar()
     * @see Entity#dirCharToByte(char)
     *
     * @param dir Direction of solidity, must be one of "top", "right", "bottom", or "left".
     * @return Solidity in the specified direction. Default is false (not solid).
     */
    @Deprecated
    public boolean isSolid(String dir) {
        switch (dir) {
            case "top":
                return this.solid[0];
            case "right":
                return this.solid[1];
            case "bottom":
                return this.solid[2];
            case "left":
                return this.solid[3];
            default:
                Util.err("Invalid argument in DynamicObject.isSolid(String).");
                return false;
        }
    }

    /**
     * Returns average damage output of this object for damage calculations.
     *
     * @return Average of this object's four directional damage attributes.
     */
    public int getAverageHurts() {
        int result = 0;

        // Iterates through the objects damage attributes and adds them to the subtotal.
        for (int i = 0; i < this.hurts.length; i++) {
            result += this.hurts[i];
        }

        // Outputs the average.
        return result / this.hurts.length;
    }

    /**
     * Passes render instruction on to this object's sprite.
     *
     * @param g Graphics object that is used to render this object.
     */
    public void render(Graphics g) {
        this.sprite.render(g);
    }

    /**
     * Passes render instructions on to this object's sprite, with specific coordinates that may differ from its actual position.
     *
     * @param g Graphics object that is used to render this object.
     * @param x The x coordinate where this object should be rendered.
     * @param y The y coordinate where this object should be rendered.
     */
    public void render(Graphics g, int x, int y) {
        this.sprite.render(g, x, y);
    }
}