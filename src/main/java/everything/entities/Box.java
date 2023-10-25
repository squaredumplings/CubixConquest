package everything.entities;

public class Box {
    public int x;
    public int y;
    public int w;
    public int h;

    public Box(int cornerX, int cornerY, int width, int height) {
        this.x = cornerX;
        this.y = cornerY;
        this.w = width;
        this.h = height;
    }
}
