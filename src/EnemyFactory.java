/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * EnemyFactory.java
 * This file contains the EnemyFactory class.
 * This class is used to create enemies.
 */

import java.awt.geom.Point2D;

/*
 * This class is used to create enemies.
 */
public class EnemyFactory {

    /*
     * This method is used to create an enemy
     * 
     * @param type: the type of the enemy
     * @param level: the level of the enemy
     * @param startPoint: the starting point of the enemy
     * 
     * @return: the enemy
     */
    public static Enemy createEnemy(int type, int level, Point2D startPoint) {
        return new Enemy(type, level, startPoint);
    }
}
