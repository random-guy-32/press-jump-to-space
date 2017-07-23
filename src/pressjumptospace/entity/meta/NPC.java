package pressjumptospace.entity.meta;

import pressjumptospace.render.Sprite;

/**
 * Parent class for all entities that represent creatures and aren't the player.
 * NPCs usually can move on their own and have their own autonomous behaviour.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class NPC extends Entity {
    /**
     * Standard constructor with all default values.
     */
    public NPC() {
        super(0, 0, new boolean[] {false, false, false, false}, new int[] {0, 0, 0, 0}, 16, 16, 0, 0, Sprite.nul, (byte) 0, false, 20, 20);
    }

    /**
     * Standard constructor with some default values.
     * @param x_ Horizontal position.
     * @param y_ Vertical position.
     * @param solid_ Directional solidity.
     * @param hurts_ Directional damage output.
     * @param hitboxWidth Width.
     * @param hitboxHeight Height.
     * @param hitboxOffsetX Bounding box x offset.
     * @param hitboxOffsetY Bounding box y offset.
     * @param sprite_ Sprite source.
     * @param dir_ Direction.
     * @param willFall_ Whether this NPC is affected by gravity.
     * @param health_ Current HP.
     * @param maxHealth_ Maximal HP.
     */
    public NPC(int x_, int y_, boolean[] solid_, int[] hurts_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_) {
        super(x_, y_, solid_, hurts_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_);
    }

    /**
     * Standard Constructor.
     * @param x_ Horizontal position.
     * @param y_ Vertical position.
     * @param solid_ Directional solidity.
     * @param hurts_ Directional damage output.
     * @param vulnerable_ Directional vulnerability.
     * @param hitboxWidth Width.
     * @param hitboxHeight Height.
     * @param hitboxOffsetX Bounding box x offset.
     * @param hitboxOffsetY Bounding box y offset.
     * @param sprite_ Sprite source.
     * @param dir_ Direction.
     * @param willFall_ Whether this NPC is affected by gravity.
     * @param health_ Current HP.
     * @param maxHealth_ Maximal HP.
     * @param hurtByTile Whether this NPC can be damaged by hurtful tiles.
     * @param hurtByEntity Whethert his NPC can be damaged by other hurtful entities.
     */
    public NPC(int x_, int y_, boolean[] solid_, int[] hurts_, boolean[] vulnerable_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_, boolean hurtByTile, boolean hurtByEntity) {
        super(x_, y_, solid_, hurts_, vulnerable_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_, hurtByTile, hurtByEntity);
    }

    /**
     * Called every tick to determine NPC's behaviour.
     */
    public abstract void ai();
}