package everything.top;

import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import everything.game.Tile;

public class Config {
    // resolution
    public static final int WINDOWWIDTH = 1920;
    public static final int WINDOWHEIGHT = 1080;

    // fps
    public static final int FPS = 60;

    // camera
    public static final int XTILES = 32;
    public static final int YTILES = 18;
    public static final int TILESIZE = 60;

    // map
    public static final int MAPSIZE = 30;
    public static final int TILETYPES = 10;
    public static Tile[] tileTypes = new Tile[TILETYPES];
    public static int[][] MAP = new int[MAPSIZE][MAPSIZE];

    // entities
    public static final int MAXENT = 10;

    // Initialize map from file map.txt
    public static void initMap() {
        Scanner scanner = new Scanner(Config.class.getResourceAsStream("/map.txt"));
        int number;
        int row;
        int col;
        
        for (int i = 0; scanner.hasNext(); i++) {
            number = Integer.valueOf(scanner.nextInt());
            row = i / MAPSIZE;
            col = i % MAPSIZE;
            MAP[row][col] = number;
        }
        scanner.close();
    }

    // Initialize tiles from resources
    public static void initTiles() {
        try {
            tileTypes[1] = new Tile();
            tileTypes[1].image = ImageIO.read(Config.class.getResourceAsStream("/Grass.png"));

            tileTypes[2] = new Tile();
            tileTypes[2].image = ImageIO.read(Config.class.getResourceAsStream("/Stone.png"));
            tileTypes[2].collision = true;

            tileTypes[3] = new Tile();
            tileTypes[3].image = ImageIO.read(Config.class.getResourceAsStream("/Water.png"));
            tileTypes[3].slowdown = 40;
            tileTypes[3].damage = 5;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
