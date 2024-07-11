
/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * Tower.java
 * This file contains the Tower class.
 * This class is used to represent the towers in the game.
 */

/* 
 * This class is used to represent the towers in the game.
*/
public class Tower extends Entity implements EnemyObserver {

    private int cost;
    private Power power;

    /*
     * Constructor for the Tower class
     */
    public Tower(int x, int y, int type) {
        super(x, y, 5 * type, (type * 10) / 3, type + 1);
        this.cost = type * 10;
        switch (type) {
            case 1:
                power = Power.POISON;
                break;

            case 2:
                power = Power.SLOW;
                break;

            case 3:
                power = Power.NONE;
                break;

            case 4:
                power = Power.BURN;
                break;

            case 5:
                power = Power.POISON;
                break;

            case 6:
                power = Power.BURN;
                break;

            case 7:
                power = Power.SLOW;
                break;

            case 8:
                power = Power.FREEZE;
                break;
            default:
                power = Power.POISON;
                break;
        }
        set_img(EntityFlyweight.getTowersImg(type));
    }

    /*
     * This method is used to get the power of the tower
     * 
     * @param: nothing
     * 
     * @return: the power of the tower
     */
    @Override
    public void anEnemyInRange(Enemy enemy) {
        attack(enemy);
    }

    /*
     * This method is used to get the power of the tower
     * 
     * @param: nothing
     * 
     * @return: the power of the tower
     */
    public boolean is_in_range(Entity enemy) {
        return (Math.sqrt(Math.pow(enemy.get_x() - this.get_x(), 2) + Math.pow(enemy.get_y() - this.get_y(), 2)) <= this
                .get_range());
    }

    /*
     * This method is used to attack the enemy
     * 
     * @param enemy: the enemy to attack
     * 
     * @return: nothing
     */
    public void attack(Enemy enemy) {
        if (!is_in_range(enemy)) {
            return;
        }

        if (System.currentTimeMillis() - this.get_lastAttackTime() < 1000 / this.get_fireRate()) {
            return;
        }

        this.set_lastAttackTime(System.currentTimeMillis());

        float newangle = compute_angle(enemy.get_position());
        this.set_angle(newangle);

        enemy.take_damage(this.get_damage());

        switch (power) {
            case BURN:
                enemy.take_damage(this.get_damage() / 3);
                break;
            case SLOW:
                enemy.set_speed((int) (enemy.get_speed() * 0.75));
                break;
            case FREEZE:
                enemy.set_speed(0);
                break;
            case POISON:
                enemy.set_health(enemy.get_health() / 2);
                break;
            default:
                break;
        }
        return;
    }

    /*
     * This method is used to set the position of the tower
     * 
     * @param x: the x position of the tower
     * 
     * @param y: the y position of the tower
     * 
     * @return: nothing
     */
    public void set_tower_pos(int x, int y) {
        this.set_position(x, y);
    }

    /*
     * This method is used to get the cost of the tower
     * 
     * @param: nothing
     * 
     * @return: the cost of the tower
     */
    public int getCost() {
        return this.cost;
    }
}
