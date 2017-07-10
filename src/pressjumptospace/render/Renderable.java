package pressjumptospace.render;

import java.awt.*;

public interface Renderable {
    // look, ma, I'm using interfaces!
    /*
    * Just to be clear: There is absolutely no reason for this interface to exist. It is needed literally nowhere in the code.
    * But I am trying to conform to widely accepted coding practices. Therefore I have included this interface in my program.
     */

    void render(Graphics g);
    void render(Graphics g, int x, int y);
}
