package pressjumptospace.tile.turret;

import pressjumptospace.entity.player.Controls;
import pressjumptospace.entity.projectile.SmallBullet;
import pressjumptospace.tile.meta.Material;
import pressjumptospace.tile.meta.Turret;
import pressjumptospace.tile.tile_entity.TurretTileDataContainer;

/**
 * Periodically spawns small bullets.
 * @see SmallBullet
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public class HorizontalSmallBulletTurret extends Turret {
    /**
     * Standard constructor with default values for positioning.
     */
    public HorizontalSmallBulletTurret() {
        super(new boolean[] {true, true, true, true}, new int[] {0, 0, 0, 0}, Material.Metal, "tile/horizontal-small-bullet-turret.png", "Horizontal Small Bullet Turret", SmallBullet.class, new boolean[] {false, true, false, true}, true, Controls.player, false, true, 90, 90, new int[] {0, 0, 9, -2, 0, 0, -7, -2});
    }

    /**
     * Initiates the data container that holds all of this turret's supplemental information.
     *
     * @param x Data container's exact x coordinate.
     * @param y Data container's exact y coordinate.
     * @param tileX Data container's tile grid x coordinate.
     * @param tileY Data container's tile grid y coordinate.
     */
    public void createDataContainer(int x, int y, int tileX, int tileY) {
        this.tileEntity = new TurretTileDataContainer(this, x, y, x / 16, y / 16, this.bulletType, this.directions, this.willFollowEntity, this.targetEntity, this.shootsThroughSolidTile, this.shootsThroughSolidEntity, this.fireCooldown, this.fireFrequency, this.projectileOrigin);
    }
}
