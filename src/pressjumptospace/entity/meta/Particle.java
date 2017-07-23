package pressjumptospace.entity.meta;

import pressjumptospace.render.Renderable;
import pressjumptospace.render.Sprite;

/**
 * Particles are decorative objects that generally don't interact with anything in the level.
 * Still unfinished.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class Particle implements Renderable{
    /**
     * Standard constructor.
     */
    public Particle() {

    }

    /**
     * Texture.
     */
    public Sprite sprite;

    /**
     * Horizontal position.
     */
    public int x;

    /**
     * Vertical position.
     */
    public int y;

    /**
     * Width.
     */
    public int width;

    /**
     * Height.
     */
    public int height;

    /**
     * Horizontal velocity.
     */
    public float velX;

    /**
     * Vertical velocity.
     */
    public float velY;

    /**
     * How many ticks this particle has already existed.
     */
    public int age;

    /**
     * I don't know.
     */
    public float frictionX;

    /**
     * I don't know.
     */
    public float frictionY;

    /**
     * Multiplied by standard gravity to make particle fall slower or faster than other objects.
     *
     * @see pressjumptospace.util.Physics
     */
    public float gravityModifier;

    /**
     * Whether this particle is affected by solid surfaces or liquids.
     */
    public boolean tangible;
}
