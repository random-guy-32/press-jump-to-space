package pressjumptospace.entity.meta;

import pressjumptospace.render.Sprite;

/**
 * A simple enemy whose only behaviour consists of walking in a straight line between to obstacles.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class PatrollingEnemy extends Enemy {
    public PatrollingEnemy(int x_, int y_, boolean[] solid_, int[] hurts_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String[] sprites, byte dir_, boolean willFall_, int health_, int maxHealth_) {
        super(x_, y_, solid_, hurts_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, Sprite.nul, dir_, willFall_, health_, maxHealth_);
        dirSprites = sprites;
        this.adjustDirectionalSprite();
    }
    public PatrollingEnemy(int x_, int y_, boolean[] solid_, int[] hurts_, boolean[] vulnerable_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String[] sprites, byte dir_, boolean willFall_, int health_, int maxHealth_, boolean hurtByTile, boolean hurtByEntity) {
        super(x_, y_, solid_, hurts_, vulnerable_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, Sprite.nul, dir_, willFall_, health_, maxHealth_, hurtByTile, hurtByEntity);
        dirSprites = sprites;
        this.adjustDirectionalSprite();
    }

    /**
     * Array of sprites for each direction this enemy can walk.
     */
    public String[] dirSprites;

    /**
     * Updates direction sprite to the enemy's current direction.
     */
    protected void adjustDirectionalSprite() {
        this.sprite.src = this.dirSprites[this.dir];
    }

    /**
     * Each tick, new behaviour is determined.
     * Enemy will keep walking in one direction until it hits an obstacle.
     * Upon encountering an obstacle enemy will reverse direction and continue walking.
     */
    public void ai() {
        if (this.dirToChar() == 'l' && this.getCollision('l') <= 0) {
            // If enemy is walking to the left and there is a wall to the left, direction is switched to right.
            this.setDir('r');
            this.adjustDirectionalSprite();
        }
        else if (this.dirToChar() == 'r' && this.getCollision('r') <= 0) {
            // If enemy is walking to the right and there is a wall to the right, direction is switched to left.
            this.setDir('l');
            this.adjustDirectionalSprite();
        }

        this.walk(this.walkSpeed);
    }
}