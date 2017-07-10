package pressjumptospace.tile.meta;

import pressjumptospace.entity.meta.Entity;

abstract public class Turret extends TileWithDataContainer {
    public Turret(boolean[] solid_, int[] hurts_, Material material_, String sprite_, String name_, Class bulletType_, boolean[] directions_, boolean willFollowEntity_, Entity targetEntity_, boolean shootsThroughSolidTile_, boolean shootsThroughSolidEntity_, int fireCooldown_, int fireFrequency_, int[] projectileOrigin_) {
        super(solid_, hurts_, material_, sprite_, name_);
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
    public int[] projectileOrigin; // up, up, right, right, down, down, left, left

    public void fire() {

    }
}
