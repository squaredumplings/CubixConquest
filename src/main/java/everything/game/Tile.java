package everything.game;

import java.awt.image.BufferedImage;

public class Tile {
    
    public BufferedImage image;
    
    // if the tile will stop you
    public boolean collision = false;

    // percentage of speed to which you will be slowed, min 0, max 100
    public int slowdown = 100;

    // damage you take on contact with tile
    public int damage = 0;
}
