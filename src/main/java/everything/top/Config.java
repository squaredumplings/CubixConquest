package everything.top;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

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
    public static final int OUTERWALL = 2;
    public static int[][] MAP = new int[MAPSIZE][MAPSIZE];

    public static void initMap() {
        try {
            Path path = Path.of(Config.class.getResource("/map.txt").getPath());
            Scanner scanner = new Scanner(path); 
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

    //small issue to fix: it makes the file in ~/cubixconquest
    public static void makemap() {
        for (int i = 0; i < MAPSIZE; i++) {
            for (int j = 0; j < MAPSIZE; j++) {
                if ((i < OUTERWALL) || (MAPSIZE - OUTERWALL - 1 < i) 
                    || (j < OUTERWALL) || (MAPSIZE - OUTERWALL - 1 < j)) {
                    MAP[i][j] = 2;
                } else if ((40 < i) && (i < MAPSIZE - 40) && (40 < j) && (j < MAPSIZE - 40)) {
                    MAP[i][j] = 3;
                } else {
                    MAP[i][j] = 1;
                }
            }
        }

        try {
            FileWriter myWriter = new FileWriter("map.txt");
            for (int i = 0; i < MAPSIZE; i++) {
                for (int j = 0; j < MAPSIZE; j++) {
                    myWriter.write(String.valueOf(MAP[i][j]) + " ");
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("This is busted");
            e.printStackTrace();
        }
    }
}
