package pressjumptospace.entity.meta;

import pressjumptospace.render.Sprite;

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

    public String[] dirSprites;

    protected void adjustDirectionalSprite() {
        this.sprite.src = this.dirSprites[this.dir];
    }

    public void ai() {
        if (this.dirToChar() == 'l' && this.getCollision('l') <= 0) {
            this.setDir('r');
            this.adjustDirectionalSprite();
        }
        else if (this.dirToChar() == 'r' && this.getCollision('r') <= 0) {
            this.setDir('l');
            this.adjustDirectionalSprite();
        }

        this.walk(this.walkSpeed);
    }
}