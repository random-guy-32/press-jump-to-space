package pressjumptospace.entity;

import pressjumptospace.entity.meta.CursorBoundObject;
import pressjumptospace.entity.meta.Entity;

public class CursorBoundSpawner extends CursorBoundObject {
    public CursorBoundSpawner(int x_, int y_, Class type_) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        // I'm giving up on life
        super(x_, y_, (!Entity.class.isAssignableFrom(type_)) ? CursorBoundTile.getSprite() : ((Entity) type_.newInstance()).sprite.src);
        type = type_;
    }

    public Class type;
}
