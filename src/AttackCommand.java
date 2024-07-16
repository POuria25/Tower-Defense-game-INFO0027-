/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * AttackCommand.java
 * This file contains the AttackCommand class, which is a concrete implementation of the Command interface.
 * This class is used to attack a base.
 */

public class AttackCommand implements Command {
    private Enemy enemy;
    private Base base;


    public AttackCommand(Enemy enemy, Base base) {
        this.enemy = enemy;
        this.base = base;
    }

    @Override
    public void execute() {
        enemy.attack(base);
    }
}
