package pressjumptospace.entity;

import pressjumptospace.entity.meta.Entity;
import pressjumptospace.entity.meta.Hitbox;
import pressjumptospace.level.Level;
import pressjumptospace.main.Game;
import pressjumptospace.tile.meta.LiquidTile;
import pressjumptospace.tile.meta.Tile;
import pressjumptospace.util.Util;

import java.util.ArrayList;
import java.util.List;

public class EntityHitbox extends Hitbox {
    public EntityHitbox() {
        super(new boolean[] {true, true, true, true});
    }
    public EntityHitbox(boolean[] solid_) {
        super(solid_);
    }
    public EntityHitbox(boolean[] solid_, int x_, int y_, int width_, int height_, int offsetX_, int offsetY_) {
        super(x_, y_, width_, height_, offsetX_, offsetY_, solid_);
    }

    public static int searchRadius = 64;

    public boolean onGround() {
        return (this.y + this.offsetY) % 16 == 0 && (this.getTileSolid(0, 0, 0) || this.getTileSolid(0, -1, 0));
    }

    public boolean isInHitbox(int x, int y) {
        return (x >= this.pos('x') && x <= this.pos('x') + this.width - 1) && (y >= this.pos('y') && y <= this.pos('y') + this.height - 1);
    }

    public int getCollision(char direction) {
        return Math.min(this.getTileCollision(direction), this.getEntityCollision(direction));
    }

    public int getEntityCollision(char direction) {
        // Util.log("Checking entity collision for " + this);

        List<Entity> nearEntities = this.getNearEntities();
        int distance = -1;

        if (nearEntities.size() == 0) {
            return 3000;
        }

        int firstCorner, secondCorner, refPosition;

        switch (direction) {
            case 'u':
                // UP
                firstCorner = this.pos('x');
                secondCorner = this.pos('x') + this.width - 1;
                refPosition = this.pos('y');

                for (int i = 1; i <= searchRadius; i++) {
                    for (int j = 0; j < nearEntities.size(); j++) {
                        Entity e = nearEntities.get(j);

                        if (e.solid[2] && (((EntityHitbox) e.hitbox).isInHitbox(firstCorner, refPosition - i) || ((EntityHitbox) e.hitbox).isInHitbox(secondCorner, refPosition - i))) {
                            distance = i - 1;
                            return distance;
                        }
                    }
                }

                return 3000;
            case 'r':
                // RIGHT
                firstCorner = this.pos('y');
                secondCorner = this.pos('y') + this.height - 1;
                refPosition = this.pos('x') + this.width - 1;

                for (int i = 1; i <= searchRadius; i++) {
                    for (int j = 0; j < nearEntities.size(); j++) {
                        Entity e = nearEntities.get(j);

                        if (e.solid[3] && (((EntityHitbox) e.hitbox).isInHitbox(refPosition + i, firstCorner) || ((EntityHitbox) e.hitbox).isInHitbox(refPosition + i, secondCorner))) {
                            distance = i - 1;
                            return distance;
                        }
                    }
                }

                return 3000;
            case 'd':
                // DOWN
                firstCorner = this.pos('x');
                secondCorner = this.pos('x') + this.width - 1;
                refPosition = this.pos('y') + this.height - 1;

                for (int i = 1; i <= searchRadius; i++) {
                    for (int j = 0; j < nearEntities.size(); j++) {
                        Entity e = nearEntities.get(j);

                        if (e.solid[0] && (((EntityHitbox) e.hitbox).isInHitbox(firstCorner, refPosition + i) || ((EntityHitbox) e.hitbox).isInHitbox(secondCorner, refPosition + i))) {
                            distance = i - 1;
                            return distance;
                        }
                    }
                }

                return 3000;
            case 'l':
                // LEFT
                firstCorner = this.pos('y');
                secondCorner = this.pos('y') + this.height - 1;
                refPosition = this.pos('x');

                for (int i = 1; i <= searchRadius; i++) {
                    for (int j = 0; j < nearEntities.size(); j++) {
                        Entity e = nearEntities.get(j);

                        if (e.solid[1] && ((EntityHitbox) e.hitbox).isInHitbox(refPosition - i, firstCorner) || ((EntityHitbox) e.hitbox).isInHitbox(refPosition - i, secondCorner)) {
                            distance = i - 1;
                            return distance;
                        }
                    }
                }

                return 3000;
            default:
                Util.err("Unknown direction for entity collision calculation.");
                return -1;
        }
    }

    public int getTileCollision(char direction) {
        // i'll clean up later. first I need to make sure this actually works

        Tile tile1;
        Tile tile2;

        int distance = -1;

        switch (direction) {
            case 'd':
                // DOWN
                int leftC = this.x + this.offsetX;
                int rightC = this.x + this.offsetX + this.width - 1;
                int downE = this.y + this.offsetY + this.height - 1;

                for (int i = 1; distance == - 1; i++) {
                    tile1 = Level.getTile(leftC, downE + i);
                    tile2 = Level.getTile(rightC, downE + i);

                    if (tile1 != null) {
                        if (tile1.solid[0]) {
                            distance = i - 1;
                        }
                    }
                    if (tile2 != null) {
                        if (tile2.solid[0]) {
                            distance = i - 1;
                        }
                    }

                    if (downE + i > Game.OBJHEIGHT) {
                        distance = Game.OBJHEIGHT;
                    }
                }

                return distance;
            case 'l':
                // LEFT
                int topC = this.y + this.offsetY;
                int bottomC = this.y + this.offsetY + this.height - 1;
                int leftE = this.x + this.offsetX;

                for (int i = 1; distance == -1; i++) {
                    tile1 = Level.getTile(leftE - i, topC);
                    tile2 = Level.getTile(leftE - i, bottomC);

                    if (tile1 != null) {
                        if (tile1.solid[1]) {
                            distance = i - 1;
                        }
                    }
                    if (tile2 != null) {
                        if (tile2.solid[1]) {
                            distance = i - 1;
                        }
                    }

                    if (leftE - i < 0) {
                        distance = Game.OBJWIDTH;
                    }
                }

                return distance;
            case 'u':
                // UP
                int leftC2 = this.x + this.offsetX;
                int rightC2 = this.x + this.offsetX + this.width - 1;
                int upE = this.y + this.offsetY;

                for (int i = 1; distance == - 1; i++) {
                    /* ================================== */
                    tile1 = Level.getTile(leftC2, upE - i);
                    tile2 = Level.getTile(rightC2, upE - i);
                    /* ================================== */

                    if (tile1 != null) {
                        if (tile1.solid[2]) {
                            distance = i - 1;
                        }
                    }
                    if (tile2 != null) {
                        if (tile2.solid[2]) {
                            distance = i - 1;
                        }
                    }

                    if (upE - i < 0) {
                        distance = Game.OBJHEIGHT;
                    }
                }

                return distance;
            case 'r':
                // RIGHT
                int topC2 = this.y + this.offsetY;
                int bottomC2 = this.y + this.offsetY + this.height - 1;
                int rightE = this.x + this.offsetX + this.width - 1;

                for (int i = 1; distance == -1; i++) {
                    tile1 = Level.getTile(rightE + i, topC2);
                    tile2 = Level.getTile(rightE + i, bottomC2);

                    if (tile1 != null) {
                        if (tile1.solid[3]) {
                            distance = i - 1;
                        }
                    }
                    if (tile2 != null) {
                        if (tile2.solid[3]) {
                            distance = i - 1;
                        }
                    }

                    if (rightE + i > Game.OBJWIDTH) {
                        distance = Game.OBJWIDTH;
                    }
                }

                return distance;
            default:
                Util.err("Unknown direction for distance calculation.");
                return -1;
        }
    }
    public boolean getTileSolid(int dir, int offsetX, int offsetY) {
		
		
		/*
		0: tile to the bottom
		1: tile to the left
		2: tile to the top
		3: tile to the right
		*/

        int offX = (dir == 1) ? -1 : (dir == 3) ? 1 : 0;
        int offY = (dir == 0) ? 1 : (dir == 2) ? -1 : 0;

        int coordX = this.x + this.offsetX + (16 * offX) + (16 * offsetX);
        int coordY = this.y + this.offsetY + (16 * offY) + (16 * offsetY);

        if (Level.getTile(coordX, coordY) != null) {
            return Level.getTile(coordX, coordY).solid[dir];
        }
        else {
            return false;
        }
    }

    public List<Entity> getNearEntities() {
        return this.getNearEntities(EntityHitbox.searchRadius);
    }
    public List<Entity> getNearEntities(int radius) {
        List<Entity> entities = new ArrayList<Entity>();

        for (int i = 0; i < Level.entities.size(); i++) {
            Entity entity = Level.entities.get(i);

            if (entity.hitbox != this && Math.sqrt(Math.pow(this.pos('x') - entity.hitbox.pos('x'), 2) + Math.pow(this.pos('y') - entity.hitbox.pos('y'), 2)) <= radius) {
                entities.add(entity);
            }
        }

        return entities;
    }

    public List<Entity> getNearEntities(Class type) {
        return this.getNearEntities(type, EntityHitbox.searchRadius);
    }
    public List<Entity> getNearEntities(Class type, int radius) {
        List<Entity> entities = new ArrayList<Entity>();

        for (int i = 0; i < Level.entities.size(); i++) {
            Entity entity = Level.entities.get(i);

            if (type.isInstance(entity) && entity.hitbox != this && Math.sqrt(Math.pow(this.pos('x') - entity.hitbox.pos('x'), 2) + Math.pow(this.pos('y') - entity.hitbox.pos('y'), 2)) <= radius) {
                entities.add(entity);
            }
        }

        return entities;
    }

    public boolean isInTile(Class type) {
        return type.isInstance(this.getTile("topleft")) || type.isInstance(this.getTile("topright")) || type.isInstance(this.getTile("bottomleft")) || type.isInstance(this.getTile("bottomright"));
    }

    public boolean isInEntity(Class type) {
        List<Entity> nearEntities = this.getNearEntities(type);

        for (int i = 0; i < nearEntities.size(); i++) {
            Entity e = nearEntities.get(i);

            if (
                    ((this.pos('x') >= e.pos('x') && this.pos('x') <= e.pos('x') + e.hitbox.width - 1) &&
                    (this.pos('y') >= e.pos('y') && this.pos('y') <= e.pos('y') + e.hitbox.height - 1)) ||

                    ((this.pos('x') + this.width - 1 >= e.pos('x') && this.pos('x') + this.width - 1 <= e.pos('x') + e.hitbox.width - 1) &&
                    (this.pos('y') >= e.pos('y') && this.pos('y') <= e.pos('y') + e.hitbox.height - 1)) ||

                    ((this.pos('x') >= e.pos('x') && this.pos('x') <= e.pos('x') + e.hitbox.width - 1) &&
                    (this.pos('y') + this.height - 1 >= e.pos('y') && this.pos('y') + this.height - 1 <= e.pos('y') + e.hitbox.height - 1)) ||

                    ((this.pos('x') + this.width - 1 >= e.pos('x') && this.pos('x') + this.width - 1 <= e.pos('x') + e.hitbox.width - 1) &&
                    (this.pos('y') + this.height - 1 >= e.pos('y') && this.pos('y') + this.height - 1 <= e.pos('y') + e.hitbox.height - 1))
                ) {
                    return true;
            }
        }

        return false;
    }



    public Tile getTile(String position) {
        switch (position) {
            case "topleft":
                return Level.getTile(this.pos('x'), this.pos('y'));
            case "topright":
                return Level.getTile(this.pos('x') + this.width - 1, this.pos('y'));
            case "bottomleft":
                return Level.getTile(this.pos('x'), this.pos('y') + this.height - 1);
            case "bottomright":
                return Level.getTile(this.pos('x') + this.width - 1, this.pos('y') + this.height - 1);
            case "center":
                return Level.getTile(this.pos('x') + (this.width / 2), this.pos('y') + (this.height / 2));
            default:
                Util.err("Unknown argument for tile determination.");
                return null;
        }
    }

    public float getAverageViscosity() {
        Tile[] tiles = new Tile[] {this.getTile("topleft"), this.getTile("topright"), this.getTile("bottomleft"), this.getTile("bottomright")};
        float result = 0f;
        byte amount = 0;

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != null && tiles[i] instanceof LiquidTile) {
                result += ((LiquidTile) tiles[i]).viscosity;
                amount++;
            }
        }

        return result / amount;
    }
    public int getMaxTileHurts() {
        Tile[] tiles = new Tile[] {this.getTile("topleft"), this.getTile("topright"), this.getTile("bottomleft"), this.getTile("bottomright")};
        int result = 0;

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i] != null && tiles[i] instanceof LiquidTile) {
                result = Math.max(result, tiles[i].getAverageHurts());
            }
        }

        return result;
    }

    public int getTotalHurts() {
        return Math.max(Math.max(this.getHurts('u'),this.getHurts('d')),Math.max(this.getHurts('l'),this.getHurts('r')));
    }
    public int getHurts(char direction) {
        return Math.max(this.getTileHurts(direction), this.getEntityHurts(direction));
    }
    public int getTileHurts(char direction) {
        // tiles that entity is stuck in
        int overlapping = 0;
        if (this.isInTile(LiquidTile.class)) {
            overlapping = this.getMaxTileHurts();
        }

        // tiles that entity is touching
        int touching = 0;
        Tile tile1;
        Tile tile2;

        switch (direction) {
            case 'u':
                tile1 = Level.getTile(this.pos('x'), this.pos('y') - 1);
                tile2 = Level.getTile(this.pos('x') + this.width - 1, this.pos('y') - 1);

                if (tile1 != null) {
                    touching = tile1.hurts[2];
                }
                if (tile2 != null) {
                    touching = Math.max(touching, tile2.hurts[2]);
                }
                break;
            case 'r':
                tile1 = Level.getTile(this.pos('x') + this.width, this.pos('y'));
                tile2 = Level.getTile(this.pos('x') + this.width, this.pos('y') + this.height - 1);

                if (tile1 != null) {
                    touching = tile1.hurts[3];
                }
                if (tile2 != null) {
                    touching = Math.max(touching, tile2.hurts[3]);
                }
                break;
            case 'd':
                tile1 = Level.getTile(this.pos('x'), this.pos('y') + this.height);
                tile2 = Level.getTile(this.pos('x') + this.width - 1, this.pos('y') + this.height);

                if (tile1 != null) {
                    touching = tile1.hurts[0];
                }
                if (tile2 != null) {
                    touching = Math.max(touching, tile2.hurts[0]);
                }
                break;
            case 'l':
                tile1 = Level.getTile(this.pos('x') - 1, this.pos('y'));
                tile2 = Level.getTile(this.pos('x') - 1, this.pos('y') + this.height - 1);

                if (tile1 != null) {
                    touching = tile1.hurts[1];
                }
                if (tile2 != null) {
                    touching = Math.max(touching, tile2.hurts[1]);
                }
                break;
            default:
                Util.err("Unknown directional data for damage calculation.");
                return 0;
        }

        return Math.max(overlapping, touching);
    }
    public int getEntityHurts(char direction) {
        List<Entity> entities = this.getNearEntities(32);
        if (entities.size() == 0) {
            return 0;
        }

        int result = 0;

        int checkX1, checkX2, checkY1, checkY2;

        switch (direction) {
            case 'u':
                checkX1 = this.pos('x');
                checkX2 = this.pos('x') + this.width - 1;
                checkY1 = this.pos('y') - 1;
                checkY2 = this.pos('y') - 1;
                break;
            case 'r':
                checkX1 = this.pos('x') + this.width;
                checkX2 = this.pos('x') + this.width;
                checkY1 = this.pos('y');
                checkY2 = this.pos('y') + this.height - 1;
                break;
            case 'd':
                checkX1 = this.pos('x');
                checkX2 = this.pos('x') + this.width - 1;
                checkY1 = this.pos('y') + this.height;
                checkY2 = this.pos('y') + this.height;
                break;
            case 'l':
                checkX1 = this.pos('x') - 1;
                checkX2 = this.pos('x') - 1;
                checkY1 = this.pos('y');
                checkY2 = this.pos('y') + this.height - 1;
                break;
            default:
                Util.err("Unknown direction for entity damage calculation.");
                return 0;
        }

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);

            if (e.containsCoordinates(checkX1, checkY1) || e.containsCoordinates(checkX2, checkY2)) {
                result = Math.max(result, e.hurts[e.dirCharToByte(Util.oppositeDir(direction))]);
            }
        }

        return result;
    }

    public boolean containsCoordinates(int x, int y) {
        return (x >= this.pos('x') && x <= this.pos('x') + this.width - 1) && (y >= this.pos('y') && y <= this.pos('y') + this.height - 1);
    }
}