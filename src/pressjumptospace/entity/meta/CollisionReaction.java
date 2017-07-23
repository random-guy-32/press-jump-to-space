package pressjumptospace.entity.meta;

/**
 * Determines how a projectile reacts when colliding with a solid surface.
 *
 * RICOCHET: Projectile is reflected at an angle.
 * STOP: Projectile ceases all motion while remaining active.
 * DROP: Projectile becomes inactive and displays a dropping animation that puts it out of frame.
 * DESTROY: Projectile destroys the tile it collides with.
 * DEFAULT: Projectile phases through any obstacles.
 *
 * @see Projectile
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public enum CollisionReaction {
    RICOCHET, STOP, DROP, DESTROY, DEFAULT
}
