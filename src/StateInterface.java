
/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * StateInterface.java
 * This file contains the StateInterface class.
 * This class is used to represent the state interface in the game.
 */
import graphics.TowerDefenseView;

/*
 * This class is used to represent the state interface in the game.
 */
interface StateInterface {

    /*
     * This method is used to run the game
     * 
     * @param currentGame: the current game
     * 
     * @param towerDefenseView: the view of the tower defense
     * 
     * @return: nothing
     */
    void run(Game currentGame, TowerDefenseView towerDefenseView);

    /*
     * This method is used to launch a wave
     * 
     * @param currentGame: the current game
     * 
     * @return: nothing
     */
    void launch_wave(Game currentGame);
}