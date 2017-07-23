package pressjumptospace.entity.meta;

import pressjumptospace.entity.EntityHitbox;
import pressjumptospace.entity.player.Player;
import pressjumptospace.main.Game;
import pressjumptospace.render.Renderable;
import pressjumptospace.tile.meta.Tile;
import pressjumptospace.util.Util;

import java.awt.*;

/**
 * Parent class for all objects that don't stick to the level's coordinate grid.
 *
 * @author Charlotte Buff
 * @version 1.4.3
 */

public abstract class Entity extends AbstractEntity implements Renderable {
    /**
     * Default constructor with default values for causing and receiving damage.
     *
     * @param x_
     * @param y_
     * @param solid_
     * @param hurts_
     * @param hitboxWidth
     * @param hitboxHeight
     * @param hitboxOffsetX
     * @param hitboxOffsetY
     * @param sprite_
     * @param dir_
     * @param willFall_
     * @param health_
     * @param maxHealth_
     */
    public Entity(int x_, int y_, boolean[] solid_, int[] hurts_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_) {
        super(x_, y_, solid_, hurts_, sprite_);
        dir = dir_;
        velX = 0f;
        velY = 0f;
        willFall = willFall_;
        maxHealth = maxHealth_;
        health = health_;
        width = 16;
        height = 16;
        vulnerable = new boolean[] {true, true, true, true};
        hitbox = new EntityHitbox(solid_, x_, y_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY);
        isHurtByTile = false;
        isHurtByEntity = false;
        damageCooldown = 0;
        maxDamageCooldown = 80;
    }

    /**
     * Default constructor.
     *
     * @param x_
     * @param y_
     * @param solid_
     * @param hurts_
     * @param vulnerable_
     * @param hitboxWidth
     * @param hitboxHeight
     * @param hitboxOffsetX
     * @param hitboxOffsetY
     * @param sprite_
     * @param dir_
     * @param willFall_
     * @param health_
     * @param maxHealth_
     * @param hurtByTile
     * @param hurtByEntity
     */
    public Entity(int x_, int y_, boolean[] solid_, int[] hurts_, boolean[] vulnerable_, int hitboxWidth, int hitboxHeight, int hitboxOffsetX, int hitboxOffsetY, String sprite_, byte dir_, boolean willFall_, int health_, int maxHealth_, boolean hurtByTile, boolean hurtByEntity) {
        super(x_, y_, solid_, hurts_, sprite_);
        dir = dir_;
        velX = 0f;
        velY = 0f;
        willFall = willFall_;
        maxHealth = maxHealth_;
        health = health_;
        width = 16;
        height = 16;
        vulnerable = vulnerable_;
        isHurtByTile = hurtByTile;
        isHurtByEntity = hurtByEntity;
        hitbox = new EntityHitbox(solid_, x_, y_, hitboxWidth, hitboxHeight, hitboxOffsetX, hitboxOffsetY);
        damageCooldown = 0;
        maxDamageCooldown = 80;
    }

    /**
     * 0 = up; 1 = right; 2 = down; 3 = left
     */
    public byte dir;

    /**
     * Horizontal velocity.
     */
    public float velX;

    /**
     * Vertical velocity.
     */
    public float velY;

    /**
     * Whether this entity is affected by the pull of gravity.
     *
     * @see pressjumptospace.util.Physics
     */
    public boolean willFall;

    /**
     * Current HP.
     */
    public int health;

    /**
     * Maximal HP.
     */
    public int maxHealth;

    /**
     * Width of the entity. May be different from dimensions of corresponding sprite or hitbox.
     */
    public int width;

    /**
     * Height of the entity. May be different from dimensions of corresponding sprite or hitbox.
     */
    public int height;

    /**
     * Speed at which an entity is able to propel itself forwards.
     *
     * @see #walk(float)
     */
    public float walkSpeed;

    /**
     * Directional vulnerability.
     */
    public boolean[] vulnerable;

    /**
     * Whether this entity can be hurt by tiles.
     */
    public boolean isHurtByTile;

    /**
     * Whether this entity can be hurt by other entities.
     * The player can hurt entities regardless of this property.
     */
    public boolean isHurtByEntity;

    /**
     * The time in ticks until this entity is able to take damage again.
     */
    public int damageCooldown;

    /**
     * The damage cooldown time is set to this value when this entity takes damage.
     */
    public int maxDamageCooldown;

    /**
     * This entity type's ID that is used in save files.
     */
    public short entityID;

    /**
     * Makes this entity jump by setting its vertical velocity.
     *
     * @param vel Initial velocity.
     */
    public void jump(float vel) {
        this.velY = vel;
    }

    /**
     * Makes this entity move on its own.
     * Despite this method's name, walking may also occur in mid air.
     *
     * @param vel Initial velocity.
     */
    public void walk(float vel) {
        if (this.dir == 1) {
            // If this entity is pointed to the left, velocity is set to a positive value.
            this.velX = vel;
        }
        else if (this.dir == 3) {
            // If this entity is pointed to the right, velocity is set to a negative value.
            this.velX = -vel;
        }
    }

    /**
     * Increases this entity's HP by a certain amount.
     *
     * @param amount HP gained.
     */
    public void heal(int amount) {
        this.setHealth(this.health + amount);
    }

    /**
     * Decreases this entity's HP by a certain amount.
     *
     * @param amount HP lost.
     */
    public void hurt(int amount) {
        if (this.damageCooldown == 0) {
            // Entity can only take damage when damage cooldown has run out.
            this.setHealth(this.health - amount);

            if (amount > 0) {
                // It is possible to take 0 damage, for some reason.
                // Damage cooldown period only starts if damage takes is greater than 0.
                this.damageCooldown = this.maxDamageCooldown;
            }
        }
    }

    /**
     * Decreases this entity's HP by a certain amount, with a custom cooldown time.
     * Ignores damage cooldown.
     *
     * @param amount HP lost.
     * @param cooldown Cooldown in ticks until the entity can take damage again.
     */
    public void hurt(int amount, int cooldown) {
        this.setHealth(this.health - amount);
        this.damageCooldown = cooldown;
    }

    /**
     * Sets entity's health to specified amount, with safety measures to prevent invalid values.
     *
     * @param amount HP.
     */
    public void setHealth(int amount) {
        if (amount < 0) {
            // HP can't go lower than 0, so any value below that just becomes 0 instead.
            this.health = 0;
        }
        else if (amount > this.maxHealth) {
            // HP can't go above max HP.
            this.health = this.maxHealth;
        }
        else {
            this.health = amount;
        }

        if (this instanceof Player) {
            // The player changes texture depending on how many health points it has.
            ((Player) this).adjustHealthSprite();
        }
    }

    /**
     * Every tick, damage cooldown is decreased by one until it reaches 0.
     */
    public void tickDamageCooldown() {
        if (this.damageCooldown > 0) {
            this.damageCooldown--;
        }
    }

    /**
     * Sets entity's health to 0, meaning it will be removed from the game on the next tick.
     */
    public void kill() {
        this.health = 0;
    }

    /**
     * Converts {@link #dir} attribute to a character that more obviously describes direction.
     *
     * @return Direction as char (up = u; right = r; down = d; left = l).
     */
    public char dirToChar() {
        switch (this.dir) {
            case 0:
                return 'u';
            case 1:
                return 'r';
            case 2:
                return 'd';
            case 3:
                return 'l';
            default:
                Util.err("Unknown direction data. Assuming default value 'u'.");
                return 'u';
        }
    }

    /**
     * Converts {@link #dir} attribute from a char back to its byte representation for processing.
     *
     * @param dir A direction char (up = 'u'; right = 'r'; down = 'd'; left = 'l').
     * @return The corresponding byte value (up = 0; right = 1; down = 2; left = 3).
     */
    public byte dirCharToByte(char dir) {
        switch (dir) {
            case 'u':
                return 0;
            case 'r':
                return 1;
            case 'd':
                return 2;
            case 'l':
                return 3;
            default:
                Util.err("Unknown direction data. Assuming default value 0.");
                return 0;
        }
    }

    /**
     * Sets entity's direction.
     *
     * @param dir A direction char (up = 'u'; right = 'r'; down = 'd'; left = 'l').
     */
    public void setDir(char dir) {
        switch (dir) {
            case 'u':
                this.dir = 0;
                break;
            case 'r':
                this.dir = 1;
                break;
            case 'd':
                this.dir = 2;
                break;
            case 'l':
                this.dir = 3;
                break;
            default:
                Util.err("Unknown direction data. Assuming default value 0.");
                this.dir = 0;
        }
    }

    /**
     * Passes render instruction on to entity's sprite.
     *
     * @param g Graphics object that is used to render this object.
     */
    public void render(Graphics g) {
        if (this.damageCooldown == 0 || Game.age % 3 == 0) {
            // If entity is under the effect of damage cooldown, it is only rendered on every third frame.
            // This creates a blinking effect.
            this.sprite.render(g);
        }
    }

    /**
     * Passes render instruction on to entity's sprite, with custom positioning.
     *
     * @param g Graphics object that is used to render this object.
     * @param x The x coordinate where this object should be rendered.
     * @param y The y coordinate where this object should be rendered.
     */
    public void render(Graphics g, int x, int y) {
        if (this.damageCooldown == 0 || Game.age % 3 == 0) {
            this.sprite.render(g, x, y);
        }
    }

    /**
     * Yields hitbox's position along an axis.
     *
     * @param axis The specified axis. Must be either 'x' or 'y'.
     * @return The hitbox's coordinates.
     */
    public int pos(char axis) {return this.hitbox.pos(axis);}

    /**
     * @deprecated Was presumably used to determine whether entity pushes against a solid surface. Use {@link #getCollision(char)} instead.
     *
     * @see EntityHitbox#getTileCollision(char)
     *
     * @param dir
     * @return
     */
    @Deprecated
    public boolean getTileSolid(byte dir) {
        return ((EntityHitbox) this.hitbox).getTileSolid(dir, this.hitbox.offsetX, this.hitbox.offsetY);
    }

    /**
     * Passes collision function on to hitbox.
     * @see EntityHitbox#getCollision(char)
     *
     * @param dir Direction towards which collision is calculated.
     * @return Distance to nearest solid surface.
     */
    public int getCollision(char dir) {
        return ((EntityHitbox) this.hitbox).getCollision(dir);
    }

    /**
     * @deprecated Passes tile collision function on to hitbox. Use hitbox methods directly instead.
     *
     * @param dir Direction towards which collision is calculated.
     * @return Distance to nearest solid tile.
     */
    @Deprecated
    public int getTileCollision(char dir) {
        return ((EntityHitbox) this.hitbox).getTileCollision(dir);
    }

    /**
     * @deprecated Passes entity collision function on to hitbox. Use hitbox methods directly instead.
     *
     * @param dir Direction towards which collision is calculated.
     * @return Distance to nearest solid entity.
     */
    @Deprecated
    public int getEntityCollision(char dir) {
        return ((EntityHitbox) this.hitbox).getEntityCollision(dir);
    }

    /**
     * If entity receives damage from several different directions at once, returns highest of those damage values.
     *
     * @return Damage dealt.
     */
    public int getTotalHurts() {
        return Math.max(Math.max(this.getHurts('u'), this.getHurts('l')), Math.max(this.getHurts('d'), this.getHurts('r')));
    }

    /**
     * Returns the damage dealt to this entity from a certain direction.
     *
     * @param dir Direction from which damage is calculated.
     * @return Damage dealt.
     */
    public int getHurts(char dir) {
        // Damage only occurs if entity is vulnerable from the specific direction.
        if (this.vulnerable[this.dir]) {
            // If entity receives damages from both a tile and an entity at the same time, only the largest of the two values is used.
            return Math.max(this.getTileHurts(dir), this.getEntityHurts(dir));
        }
        else return 0;
    }

    /**
     * Returns damages received from hurtful tiles in the specified direction.
     *
     * @see EntityHitbox#getTileHurts(char)
     *
     * @param dir Direction from which damage is dealt.
     * @return Damage dealt.
     */
    public int getTileHurts(char dir) {
        // Damage only occures if entity can be hurt by tiles.
        if (this.isHurtByTile) {
            // Damage calculation takes place within hitbox's methods.
            return ((EntityHitbox) this.hitbox).getTileHurts(dir);
        }
        else return 0;
    }

    /**
     * Returns damage received from other hurtful entities in the specific direction.
     *
     * @see EntityHitbox#getEntityHurts(char)
     *
     * @param dir Direction from which damage is dealt.
     * @return Damage dealt.
     */
    public int getEntityHurts(char dir) {
        // Damage only occures if entity can be hurt by other entities.
        if (this.isHurtByEntity) {
            // Damage calculation takes place withint hitbox's methods.
            return ((EntityHitbox) this.hitbox).getEntityHurts(dir);
        }
        else return 0;
    }

    /**
     * Calculates whether a geometric points lies within the entities hitbox.
     *
     * @see EntityHitbox#containsCoordinates(int, int)
     *
     * @param x Point's x coordinate.
     * @param y Point's y coordinate.
     * @return Whether the point lies within the hitbox.
     */
    public boolean containsCoordinates(int x, int y) {
        return ((EntityHitbox) this.hitbox).containsCoordinates(x, y);
    }

    /**
     * Calculates whether any part of the entity intersects a tile of a certain type.
     *
     * @see EntityHitbox#isInTile(Class)
     *
     * @param type Type of tile to be tested.
     * @return Whether such an intersection occurs.
     */
    public boolean isInTile(Class type) {
        return ((EntityHitbox) this.hitbox).isInTile(type);
    }

    /**
     * Calculates whether any part of the entity intersects another entity of a certain type.
     *
     * @see EntityHitbox#isInEntity(Class)
     *
     * @param type Type of entity to be tested.
     * @return Whether such an intersection occurs.
     */
    public boolean isInEntity(Class type) {
        return ((EntityHitbox) this.hitbox).isInEntity(type);
    }

    /**
     * Returns the type of tile that a certain point of entity's hitbox lies in.
     *
     * @see EntityHitbox#getTile(String)
     *
     * @param position Point. Must be one of "topleft", "topright", "bottomright", "bottomleft", or "center".
     * @return Class of found tile.
     */
    public Tile getTile(String position) {
        return ((EntityHitbox) this.hitbox).getTile(position);
    }

    /**
     * Returns the average viscosity values of all tiles this entity intersects.
     *
     * @see EntityHitbox#getAverageViscosity()
     *
     * @return Average Viscosity.
     */
    public float getAverageViscosity() {
        return ((EntityHitbox) this.hitbox).getAverageViscosity();
    }

    /**
     * @deprecated Returns the coordinates of the tile that the top left corner of this entity is situated in.
     *
     * @param axis Either 'x' or 'y'.
     * @return One tile coordinate, either horizontal (x) or vertical (y).
     */
    @Deprecated
    public int tileCoords(char axis) {
        if (axis == 'x') {
            return (this.x / 16);
        }
        else if (axis == 'y') {
            return (this.y / 16);
        }
        else {
            Util.err("Incorrect axis value for positioning.");
            return -1;
        }
    }

    /**
     * Deals damage to another entity, with default cooldown.
     *
     * @param target Target entity to be damaged.
     * @param amount Amount of HP removed from target.
     */
    public void dealDamage(Entity target, int amount) {
        target.hurt(amount);
    }

    /**
     * Deals damage to another entity, with custom cooldown.
     * Ignores target's damage cooldown.
     *
     * @param target Target entity to be damaged.
     * @param amount Amount of HP removed from target.
     * @param cooldown New cooldown timer value for target.
     */
    public void dealDamage(Entity target, int amount, int cooldown) {
        target.hurt(amount, cooldown);
    }
}