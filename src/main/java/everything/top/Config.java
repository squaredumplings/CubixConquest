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
    public static final int MAPSIZE = 100;
    public static final int TILETYPES = 10;
    public static Tile[] tileTypes = new Tile[TILETYPES];
    public static int[][] MAP = new int[MAPSIZE][MAPSIZE];

    // entities
    public static final int MAXENT = 5;

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
            tileTypes[0] = new Tile();
            tileTypes[0].image = ImageIO.read(Config.class.getResourceAsStream("/grass.png"));

            tileTypes[1] = new Tile();
            tileTypes[1].image = ImageIO.read(Config.class.getResourceAsStream("/wall.png"));
            tileTypes[1].collision = true;

            tileTypes[2] = new Tile();
            tileTypes[2].image = ImageIO.read(Config.class.getResourceAsStream("/water.png"));
            tileTypes[2].collision = true;

            tileTypes[3] = new Tile();
            tileTypes[3].image = ImageIO.read(Config.class.getResourceAsStream("/mud.png"));
            tileTypes[3].slowdown = 40;

            tileTypes[4] = new Tile();
            tileTypes[4].image = ImageIO.read(Config.class.getResourceAsStream("/rock.png"));
            tileTypes[4].collision = true;

            tileTypes[5] = new Tile();
            tileTypes[5].image = ImageIO.read(Config.class.getResourceAsStream("/spikes.png"));
            tileTypes[5].damage = 4;
            tileTypes[5].slowdown = 40;

            tileTypes[6] = new Tile();
            tileTypes[6].image = ImageIO.read(Config.class.getResourceAsStream("/crystal.png"));
            tileTypes[6].collision = true;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
