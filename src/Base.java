/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * Base.java
 * This file contains the Base class, which is a singleton class.
 * This class is used to represent the base of the game.
 */

import javax.swing.ImageIcon;
import java.awt.geom.Point2D;

/*
 * This class is used to represent the base of the game.
*/
public class Base {
    
    private final int MAX_HEALTH = 2000;
    private int health;
    private short healthRegen;
    private Point2D position;
    private long lastHealTime;
    private long healDelay;
    private ImageIcon image;
    private static Base base;

    /*
     * Constructor for the Base class
     */
    private Base() {
        this.health = MAX_HEALTH;
        this.healthRegen = 2;
        this.healDelay = healthRegen * (long) Math.pow(10, 3);
        this.position = new Point2D.Double(10, 1);
        this.image = new ImageIcon("../resources/base/base.png");
    }

    /*
     * This method is used to get the instance of the Base class
     * 
     * @param: nothing
     * 
     * @return: the instance of the Base class
     */
    public static Base get_base() {
        return base == null ? (base = new Base()) : base;
    }

    /*
     * This method is used to reset the base
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void reset() {
        this.health = MAX_HEALTH;
    }

    /*
     * This method is used to check if the base is dead
     * 
     * @param: nothing
     * 
     * @return: a boolean value that indicates if the base is dead
     */
    public boolean is_dead() {
        return (this.health <= 0);
    }

    /*
     * This method is used to take damage
     * 
     * @param damage: the amount of damage that the base will take
     * 
     * @return: nothing
     */
    public void take_damage(int damage) {
        if (is_dead() || this.health - damage <= 0) {
            this.health = 0;
        } else {
            this.health -= damage;
        }
    }

    /*
     * This method is used to check if the base can heal
     * 
     * @param: nothing
     * 
     * @return: a boolean value that indicates if the base can heal
     */
    private boolean can_heal() {
        return (System.currentTimeMillis() - lastHealTime) > healDelay;
    }

    /*
     * This method is used to heal the base
     * 
     * @param: nothing
     * 
     * @return: nothing
     */
    public void heal() {
        if (can_heal()) {
            boolean isMaxHealth = (this.health == MAX_HEALTH);
            if (isMaxHealth) {
                this.health = MAX_HEALTH;
            } else {
                this.health = this.health + healthRegen;
            }
            lastHealTime = System.currentTimeMillis();
        }
    }

    /*
     * This method is used to get the health of the base
     * 
     * @param: nothing
     * 
     * @return: the health of the base
     */
    public int get_health() {
        return this.health;
    }

    /*
     * This method is used to get the health regen of the base
     * 
     * @param: nothing
     * 
     * @return: the health regen of the base
     */
    public short get_regen() {
        return this.healthRegen;
    }

    /*
     * This method is used to get the position of the base
     * 
     * @param: nothing
     * 
     * @return: the position of the base
     */
    public Point2D get_position() {
        return this.position;
    }

    /*
     * This method is used to get the image of the base
     * 
     * @param: nothing
     * 
     * @return: the image of the base
     */
    public ImageIcon get_image() {
        return this.image;
    }
}