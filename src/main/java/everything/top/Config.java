package everything.top;

import java.io.File;
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
    public static final int MAPSIZE = 40;
    public static Tile[] tiles = new Tile[10];;
    public static int[][] MAP = new int[MAPSIZE][MAPSIZE];


    // Initializes map from file map.txt
    public static void initMap() {
        try {
            Scanner scanner = new Scanner(new File("src/main/resources/map.txt"));
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
        } catch (IOException exception) {
            exception.printStackTrace();
        }   
    }

    public static void initTiles() {
        try {
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(Config.class.getResourceAsStream("/Grass.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(Config.class.getResourceAsStream("/Stone.png"));
            tiles[2].collision = true;

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(Config.class.getResourceAsStream("/Water.png"));
            tiles[3].collision = true;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
