/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * EnemyObserver.java
 * This file contains the EnemyObserver interface.
 * This interface is used to represent the enemy observer in the game.
 */
public interface EnemyObserver {
    
    /*
     * This method is used to avoid an enemy in range
     * 
     * @param enemy: the enemy
     * 
     * @return: nothing
     */
    void anEnemyInRange(Enemy enemy);
}
