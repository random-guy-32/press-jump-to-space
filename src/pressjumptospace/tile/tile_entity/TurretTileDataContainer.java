package pressjumptospace.tile.tile_entity;

import pressjumptospace.entity.meta.Entity;
import pressjumptospace.tile.meta.Tile;
import pressjumptospace.tile.meta.tile_data.TileDataContainer;
import pressjumptospace.util.Util;

/**
 * Data container specifically for turret tiles.
 * This is still largely unfinished.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class TurretTileDataContainer extends TileDataContainer {
    /**
     * Standard constructor.
     *
     * @param tile_ The tile this container belongs to.
     * @param x_ Container's exact x coordinate.
     * @param y_ Container's exact y coordinate.
     * @param tileX_ Container's x coordinate on the tile grid.
     * @param tileY_ Container's y coordinate on the tile grid.
     * @param bulletType_ What bullet will be fired.
     * @param directions_ The directions from which bullets will be fired.
     * @param willFollowEntity_ Whether the bullets will home in on their targets.
     * @param targetEntity_ The entity that is the default target of the bullets.
     * @param shootsThroughSolidTile_ Whether a bullet will be fired if the turret is blocked by a solid tile.
     * @param shootsThroughSolidEntity_ Whether a bullet will be fired if the turret is block by a solid entity.
     * @param fireCooldown_
     * @param fireFrequency_
     * @param projectileOrigin_
     */
    public TurretTileDataContainer(Tile tile_, int x_, int y_, int tileX_, int tileY_, Class bulletType_, boolean[] directions_, boolean willFollowEntity_, Entity targetEntity_, boolean shootsThroughSolidTile_, boolean shootsThroughSolidEntity_, int fireCooldown_, int fireFrequency_, int[] projectileOrigin_) {
        super(tile_, x_, y_, tileX_, tileY_);
        bulletType = bulletType_;
        directions = directions_;
        willFollowEntity = willFollowEntity_;
        targetEntity = targetEntity_;
        shootsThroughSolidEntity = shootsThroughSolidEntity_;
        shootsThroughSolidTile = shootsThroughSolidTile_;
        fireCooldown = fireCooldown_;
        fireFrequency = fireFrequency_;
        projectileOrigin = projectileOrigin_;
    }

    public Class bulletType;
    public boolean[] directions;
    public boolean willFollowEntity;
    public Entity targetEntity;
    public boolean shootsThroughSolidTile;
    public boolean shootsThroughSolidEntity;
    public int fireCooldown;
    public int fireFrequency;
    public int[] projectileOrigin;

    public boolean freeToShoot(byte direction) {
        return true;
    }

    public void action() {
        if (this.fireCooldown == 0) {
            boolean canShootUp = true;
            boolean canShootRight = true;
            boolean canShootDown = true;
            boolean canShootLeft = true;

            try {
                if (this.directions[0]) {
                    // shooting up
                    if (this.shootsThroughSolidTile || this.shootsThroughSolidEntity) {

                    }
                }
                if (this.directions[1]) {
                    // shooting right
                    if (this.shootsThroughSolidTile || this.shootsThroughSolidEntity) {
                        this.bulletType.newInstance();
                    }
                }
                if (this.directions[2]) {
                    // shooting down
                    if (this.shootsThroughSolidTile || this.shootsThroughSolidEntity) {

                    }
                }
                if (this.directions[3]) {
                    // shooting left
                    if (this.shootsThroughSolidTile || this.shootsThroughSolidEntity) {

                    }
                }
            }
            catch (Throwable e) {
                Util.err("Something went very, very wrong.");
            }
        }
    }
}
