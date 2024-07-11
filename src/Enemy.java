/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * Enemy.java
 * This file contains the Enemy class.
 * This class is used to represent the enemies in the game.
 */
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/*
 * This class is used to represent the enemies in the game.
 */
public class Enemy extends Entity {

    private final int maxIndex = 35;

    private float health;
    private float speed;
    private int rewards;
    private int currentPathIndex;
    private long lastMoveTime;
    private List<EnemyObserver> observers;

    /*
     * Constructor for the Enemy class
     */
    public Enemy(int type, int level, Point2D startPoint) {

        super((int) startPoint.getX(), (int) startPoint.getY(), 10 * type, type + 2, level + type + 1); 
                                                                                                      
        this.observers = new ArrayList<EnemyObserver>();
        switch (type) {
            case 1:
                this.health = 20 * (level + 1);
                this.speed = 4;
                break;
            case 2:
                this.health = 30 * (level + 1);
                this.speed = 3;
                break;
            case 3:
                this.health = 40 * (level + 1);
                this.speed = 2;
                break;
            case 4:
                this.health = 50 * (level + 1);
                this.speed = 1;
                break;
            default:
                type = 1;
                this.health = 20 * (level + 1);
                this.speed = 3;
                break;
        }
        this.rewards = 5 * type;
        this.set_image(EntityFlyweight.getEnnemiesImg(type));
    }

    /*
     * This method is used to register an observer
     * 
     * @param: an EnemyObserver
     * 
     * @return: nothing
     */
    public void registerObserver(EnemyObserver observer) {
        observers.add(observer);
    }

    /*
     * This method is used to unregister an observer
     * 
     * @param: an EnemyObserver
     * 
     * @return: nothing
     */
    public void unregisterObserver(EnemyObserver observer) {
        observers.remove(observer);
    }

    /*
     * This method is used to notify the observers
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    private void notifyObservers() {
        for (EnemyObserver observer : observers) {
            observer.anEnemyInRange(this);
        }
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public boolean is_dead() {
        return this.health <= 0;
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    private boolean enough_time_passed() {
        long timeSinceLastAttack = System.currentTimeMillis() - this.get_lastAttackTime();
        return timeSinceLastAttack >= (1000 / this.get_fireRate());
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public boolean in_range(Base base) {
        return currentPathIndex >= maxIndex - this.get_range();
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    private boolean can_attack(Base base) {
        return in_range(base) && enough_time_passed();
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    private void perform_attack(Base base) {
        this.set_lastAttackTime(System.currentTimeMillis()); // Update the last attack time
        base.take_damage((int) get_damage()); // Attack the base
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public boolean attack(Base base) {

        if (can_attack(base)) {
            perform_attack(base);
            return true;
        } else {
            return false;
        }
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public Point2D get_pos() {
        return new Point2D.Double((double) this.get_x(), (double) this.get_y());
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public float get_health() {
        return this.health;
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void increment_current_path_index() {
        this.currentPathIndex++;
        notifyObservers();
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public int get_current_path_index() {
        return this.currentPathIndex;
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public long get_last_move_time() {
        return this.lastMoveTime;
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void set_last_move_time(long time) {
        this.lastMoveTime = time;
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public float get_speed() {
        return this.speed;
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void set_speed(float speed) {
        this.speed = speed;
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void take_damage(float damage) {
        this.health -= damage;
    }

    /*
     * This method is used to move the enemy
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void set_health(float health) {
        this.health = health;
    }

    /*
     * This method is used to get the rewards
     * 
     * @param: nothing
     * 
     * @return: the rewards
     */
    public int get_rewards() {
        return this.rewards;
    }
}
