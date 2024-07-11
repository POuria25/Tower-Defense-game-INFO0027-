/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * EnemyManager.java
 * This file contains the EnemyManager class, which is a singleton class.
 * This class is used to manage the enemies in the game.
 */

import java.awt.geom.Point2D;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * This class is used to manage the enemies in the game.
 */
public class EnemyManager {

    private List<Enemy> enemies;
    private Vector<Point2D> path;
    private List<Tower> towers;
    private static EnemyManager enemyManager = null;

    /*
     * Constructor for the EnemyManager class
     */
    private EnemyManager() {
        this.enemies = new Vector<Enemy>();
        this.path = Map.get_map().get_path();
        this.towers = new ArrayList<>();
    }

    /*
     * This method is used to get the instance of the EnemyManager class
     * 
     * @param: nothing
     * 
     * @return: the instance of the EnemyManager class
     */
    public static EnemyManager get_enemiesManager() {
        if (enemyManager == null) {
            enemyManager = new EnemyManager();
        }
        return enemyManager;
    }

    /*
     * This method is used to add a tower to the list of towers
     * 
     * @param tower: the tower to be added
     * 
     * @return: nothing
     */
    public void generate_ennemies(int level) {
        for (int i = 0; i < (level + 1) * 2; i++) {
            int enemyTypes = 4;
            Random random = new Random();
            int newEnemyType = random.nextInt(enemyTypes) + 1;
            Enemy newEnemy = EnemyFactory.createEnemy(newEnemyType, level, path.elementAt(0));
            for (Tower tower : towers) {
                newEnemy.registerObserver((EnemyObserver) tower); // Register each tower as an observer to the new enemy
            }
            enemies.add(newEnemy);
        }
    }

    /*
     * This method is used to update the enemies
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void update() {
        for (Enemy enemy : enemies) {
            if (enemy.in_range(Base.get_base())) {
                Command attackCommand = new AttackCommand(enemy, Base.get_base());
                attackCommand.execute(); // Execute the attack command
            } else if (System.currentTimeMillis() - enemy.get_last_move_time() >= 1000 / enemy.get_speed()) {
                enemy.set_last_move_time(System.currentTimeMillis());
                enemy.increment_current_path_index();
                Point2D next_point = path.elementAt(enemy.get_current_path_index());
                enemy.set_angle(enemy.compute_angle(next_point));
                enemy.set_position((int) next_point.getX(), (int) next_point.getY());
            }
        }
    }

    /*
     * This method is used to add a tower to the list of towers
     * 
     * @param tower: the tower to be added
     * 
     * @return: nothing
     */
    public void check_dead() {
        enemies.removeIf(Enemy::is_dead);
    }

    /*
     * This method is used to add a tower to the list of towers
     * 
     * @param tower: the tower to be added
     * 
     * @return: nothing
     */
    public List<Enemy> get_enemies() {
        return this.enemies;
    }

    /*
     * This method is used to add a tower to the list of towers
     * 
     * @param tower: the tower to be added
     * 
     * @return: nothing
     */
    public void clear() {
        this.enemies.clear();
    }

}