
/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * Game.java
 * This file contains the Game class, which is a singleton class.
 * This class is used to represent the game.
 */
import graphics.TowerDefenseEventsHandlerInterface;
import java.util.List;
import java.util.Vector;

/*
 * This class is used to represent the game.
 */
public class Game implements TowerDefenseEventsHandlerInterface {

    private Base base;
    private int level;
    private int money;

    private Vector<Tower> towers;
    private EnemyManager enemy_manager;
    private StateManager state;
    private Map map;
    private static Game game = null;

    /*
     * Constructor for the Game class
     */
    private Game() {
        this.money = 50;
        this.level = 0;
        this.base = Base.get_base();
        this.towers = new Vector<Tower>();
        this.enemy_manager = EnemyManager.get_enemiesManager();
        this.state = StateManager.get_StateManager();
        this.map = Map.get_map();

    }

    /*
     * This method is used to get the instance of the Game class
     * 
     * @param: nothing
     * 
     * @return: the instance of the Game class
     */
    public static Game getInstance() {
        return (game == null) ? new Game() : game;
    }

    /*
     * This method is used to restart the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void restart() {
        this.money = 50;
        this.level = 0;
        this.base.reset();
        this.towers.clear();
        this.enemy_manager.clear();
        this.map.set_map();
        this.state.set_state(new StandbyState());
    }

    /*
     * This method is used to get the instance of the Game class
     * 
     * @param: nothing
     * 
     * @return: the instance of the Game class
     */
    public static Game get_game() {
        return game == null ? game = new Game() : game;
    }

    /*
     * This method is used to launch the wave
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void launchWave() {
        this.state.launch_wave(this);
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    @Override
    public void startNewGame() {
        this.restart();
    }

    /*
     * This method is used to update the game
     * 
     * @param: tower: the tower to be bought
     * 
     * @return: nothing
     */
    public void buy_Tower(Tower tower) {
        if (this.money >= tower.getCost()) {
            this.towers.add(tower);
            this.money -= tower.getCost();
        }
        return;
    }

    /*
     * This method is used to update the game
     * 
     * @param: x: the x coordinate of the tower
     * @param: y: the y coordinate of the tower
     * @param: type: the type of the tower
     * 
     * @return: nothing
     */
    @Override
    public void moveTowerToField(int x, int y, int type) {
        if (map.get_cell(x, y) != 0 || state.get_state() instanceof WaveState) {
            return;
        }
        
        Tower tower = new Tower(x, y, type);
        buy_Tower(tower);
        tower.set_tower_pos(x, y);
        map.set_occupied(x, y);

        return;
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void generate_ennemies() {
        enemy_manager.generate_ennemies(level);
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public EnemyManager get_enemy_manager() {
        return this.enemy_manager;
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public Base get_base() {
        return this.base;
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public int get_money() {
        return this.money;
    }

    /*
     * This method is used to update the game
     * 
     * @param: money: the amount of money to be added
     * 
     * @return: nothing
     */
    public void add_money(int money) {
        this.money += money;
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public int get_level() {
        return this.level;
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public Vector<Tower> get_towers() {
        return this.towers;
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public List<Enemy> get_enemies() {
        return this.enemy_manager.get_enemies();
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public StateManager get_state() {
        return this.state;
    }

    /*
     * This method is used to update the game
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void increment_level() {
        this.level++;
    }

    /*
     * This method is used to update the game
     * 
     * @param: money: the amount of money to be added
     * 
     * @return: nothing
     */
    public void gain_money(int money) {
        this.money += money;
    }

}
