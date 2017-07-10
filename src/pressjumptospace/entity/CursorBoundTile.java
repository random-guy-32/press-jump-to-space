package pressjumptospace.entity;

import pressjumptospace.entity.meta.CursorBoundObject;
import pressjumptospace.level.Level;

public class CursorBoundTile extends CursorBoundObject {
    public CursorBoundTile(int x_, int y_, short tileID_) {
        super(x_, y_, (tileID_ == -16) ? CursorBoundTile.getSprite() : Level.tileset.get(tileID_).sprite.src);
        id = tileID_;
    }
    public short id;


}