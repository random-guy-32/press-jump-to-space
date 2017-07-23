package pressjumptospace.entity.meta;

/**
 * Parent class for all NPCs that should be able to deal damage to the player, or be in turn defeated by the player.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class Enemy extends NPC {
    /**
     * Standard constructor with default values for causing and receiving damage.
     *
     * @param x_ Horizontal Position.
     * @param y_ Vertical Position.
     * @param solid_ Directional solidity.
     * @param hurts_ Directional damage output.
     * @param hitboxWidth Width of bounding box.
     * @param hitboxHeight Height of bounding box.
     * @param hitboxOffsetX Horizontal offset of bounding box.
     * @param hitboxOffsetY Vertical offset of bounding box.
     * @param sprite_ Sprite source.
     * @param dir_ Direction.
     * @param willFall_ Gravity affection.
     * @param health_ Health points.
     * @param maxHealth_ Maximal number of health points.
     */
    public Enemy(int x_, int y_, boolean[] solid_, int[] hurts_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_) {
        super(x_, y_, solid_, hurts_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_);
    }

    /**
     * Standard constructor.
     *
     * @param x_ Horizontal Position.
     * @param y_ Vertical Position.
     * @param solid_ Directional solidity.
     * @param hurts_ Directional damage output.
     * @param vulnerable_ Directional vulnerability.
     * @param hitboxWidth Width of bounding box.
     * @param hitboxHeight Height of bounding box.
     * @param hitboxOffsetX Horizontal offset of bounding box.
     * @param hitboxOffsetY Vertical offset of bounding box.
     * @param sprite_ Sprite source.
     * @param dir_ Direction.
     * @param willFall_ Gravity affection.
     * @param health_ Health points.
     * @param maxHealth_ Maximal number of health points.
     * @param hurtByTile Whether this enemy will be hurt by damaging tiles.
     * @param hurtByEntity Whether this enemy will be hurt by damaging entities.
     */
    public Enemy(int x_, int y_, boolean[] solid_, int[] hurts_, boolean[] vulnerable_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_, boolean hurtByTile, boolean hurtByEntity) {
        super(x_, y_, solid_, hurts_, vulnerable_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY, sprite_, dir_, willFall_, health_, maxHealth_, hurtByTile, hurtByEntity);
    }
}