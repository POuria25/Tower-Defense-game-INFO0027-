
/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * StateManager.java
 * This file contains the StateManager class.
 * This class is used to represent the state manager in the game.
 */
import graphics.TowerDefenseView;

/*
 * This class is used to represent the state manager in the game.
 */
public class StateManager {
    private StateInterface state;
    private static StateManager instance = null;

    /*
     * Constructor for the StateManager class
     */
    private StateManager() {
        this.state = new StandbyState();
    }

    /*
     * This method is used to get the instance of the StateManager class
     * 
     * @param: nothing
     * 
     * @return: the instance of the StateManager class
     */
    public static StateManager get_StateManager() {
        return instance == null ? (instance = new StateManager()) : instance;
    }

    /*
     * This method is used to get the state
     * 
     * @param: nothing
     * 
     * @return: the state
     */
    public StateInterface get_state() {
        return this.state;
    }

    /*
     * This method is used to set the state
     * 
     * @param state: the state
     * 
     * @return: nothing
     */
    public void set_state(StateInterface state) {
        this.state = state;
    }

    /*
     * This method is used to run the game
     * 
     * @param currentGame: the current game
     * 
     * @param towerDefenseView: the view of the tower defense game
     * 
     * @return: nothing
     */
    public void run(Game currentGame, TowerDefenseView towerDefenseView) {
        state.run(currentGame, towerDefenseView);
    }

    /*
     * This method is used to launch the wave
     * 
     * @param currentGame: the current game
     * 
     * @return: nothing
     */
    public void launch_wave(Game currentGame) {
        state.launch_wave(currentGame);
    }

}