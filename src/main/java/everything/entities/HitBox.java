package everything.entities;

/*
 * The coordinates of the top left corner (x, y) and the (with, heigh) of the rectangle.
 */ 
public class HitBox {
    public int x;
    public int y;
    public int w;
    public int h;

    public HitBox(int cornerX, int cornerY, int width, int height) {
        this.x = cornerX;
        this.y = cornerY;
        this.w = width;
        this.h = height;
    }
}
