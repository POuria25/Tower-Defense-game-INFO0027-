
/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * Map.java
 * This file contains the Map class.
 * This class is used to represent the map in the game.
 */
import java.util.Scanner;
import java.util.Vector;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * This class is used to represent the map in the game.
 */
public class Map {

    private final short width = 12;
    private final short height = 9;
    private int[][] grid = new int[width][height];
    private Vector<Point2D> path = new Vector<Point2D>();
    private static Map map = null;

    /*
     * Constructor for the Map class
     */
    private Map() {
        set_map();
        set_path();
    }

    /*
     * This method is used to get the instance of the Map class
     * 
     * @param: nothing
     * 
     * @return: the instance of the Map class
     */
    public static Map get_map() {
        return map == null ? (map = new Map()) : map;
    }

    /*
     * This method is used to set the map
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void set_map() {
        try (Scanner scanner = new Scanner(new File("../resources/map.txt"))) {
            int j = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.trim().split(" ");
                for (int i = 0; i < width; i++) {
                    grid[i][j] = Integer.parseInt(values[i]);
                }
                j++;
            }
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }
    }

    /*
     * This method is used to set the path
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    private void set_path() {
        try (BufferedReader br = new BufferedReader(new FileReader("../resources/path.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] coordinates = line.trim().split(" ");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                path.add(new Point2D.Double(x, y));
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /*
     * This method is used to get the path
     * 
     * @param: nothing
     * 
     * @return: the path
     */
    public Vector<Point2D> get_path() {
        return this.path;
    }

    /*
     * This method is used to get the width of the map
     * 
     * @param: nothing
     * 
     * @return: the width of the map
     */
    public int get_cell(int x, int y) {
        return grid[x][y];
    }

    /*
     * This method is used to set the cell as occupied
     * 
     * @param x: the x coordinate of the cell
     * 
     * @param y: the y coordinate of the cell
     * 
     * @return: nothing
     */
    public void set_occupied(int x, int y) {
        grid[x][y] = 1;
    }
}