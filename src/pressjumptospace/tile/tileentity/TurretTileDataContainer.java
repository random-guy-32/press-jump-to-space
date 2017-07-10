package pressjumptospace.tile.tileentity;

import pressjumptospace.entity.meta.Entity;
import pressjumptospace.entity.meta.Projectile;
import pressjumptospace.entity.projectile.SmallBullet;
import pressjumptospace.tile.meta.Tile;
import pressjumptospace.tile.meta.TileDataContainer;
import pressjumptospace.util.Util;

public class TurretTileDataContainer extends TileDataContainer {
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
