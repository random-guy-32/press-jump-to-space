package pressjumptospace.entity.meta;

public abstract class Projectile extends Entity {
    public Projectile(int x_, int y_, boolean[] solid_, int[] hurts_, boolean[] vulnerable_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_, boolean hurtByTile, boolean hurtByEntity, boolean collidesWithTile_, boolean collidesWithEntity_, String collisionReaction_, float defaultVel_) {
        super(x_, y_, solid_, hurts_, vulnerable_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_, hurtByTile, hurtByEntity);
        collidesWithTile = collidesWithTile_;
        collidesWithEntity = collidesWithEntity_;
        collisionReaction = collisionReaction_;
        defaultVel = defaultVel_;
    }

    public boolean collidesWithTile;
    public boolean collidesWithEntity;
    public String collisionReaction;    // can be "ricochet", "stop", "drop", "destroy" or "default"
    public float defaultVel;

    public void drop() {
        this.hurts = new int[] {0, 0, 0, 0};
        this.solid = new boolean[] {false, false, false, false};
        this.vulnerable = new boolean[] {false, false, false, false};
        this.collidesWithEntity = false;
        this.collidesWithTile = false;
        this.willFall = true;
    }
}
