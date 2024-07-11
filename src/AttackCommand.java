/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * AttackCommand.java
 * This file contains the AttackCommand class, which is a concrete implementation of the Command interface.
 * This class is used to attack a base.
 */

public class AttackCommand implements Command {
    private Enemy enemy;
    private Base base;

    /*
     * Constructor for the AttackCommand class
     * 
     * @param enemy: the enemy that is attacking the base
     * 
     * @param base: the base that is being attacked
     * 
     * @return: nothing
     */
    public AttackCommand(Enemy enemy, Base base) {
        this.enemy = enemy;
        this.base = base;
    }

    /*
     * This method is used to execute the attack command
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    @Override
    public void execute() {
        enemy.attack(base);
    }
}
