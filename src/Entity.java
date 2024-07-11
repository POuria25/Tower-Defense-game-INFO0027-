/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * Entity.java
 * This file contains the Entity class.
 * This class is used to represent the entities in the game.
 */

import javax.swing.ImageIcon;
import java.awt.Point;
import java.awt.geom.Point2D;

/*
 * This class is used to represent the entities in the game.
 */
public class Entity {

    private Point2D position;
    private float angle;
    private float damage;
    private float range;
    private float fireRate;
    private long lastAttackTime;
    private ImageIcon img;

    /*
     * Constructor for the Entity class
     */
    public Entity(int x, int y, float damage, float range, float fireRate) {
        this.position = new Point(x, y);
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;

    }

    /*
     * This method is used to get the x coordinate of the entity
     * 
     * @param: nothing
     * 
     * @return: the x coordinate of the entity
     */
    public double get_x() {
        return position.getX();
    }

    /*
     * This method is used to get the y coordinate of the entity
     * 
     * @param: nothing
     * 
     * @return: the y coordinate of the entity
     */
    public double get_y() {
        return position.getY();
    }

    /*
     * This method is used to get the position of the entity
     * 
     * @param: nothing
     * 
     * @return: the position of the entity
     */
    public Point2D get_position() {
        return position;
    }

    /*
     * This method is used to set the position of the entity
     * 
     * @param x: the x coordinate of the entity
     * 
     * @param y: the y coordinate of the entity
     * 
     * @return: nothing
     */
    public void set_position(int x, int y) {
        position.setLocation(x, y);
    }

    /*
     * This method is used to get the fire rate of the entity
     * 
     * @param: nothing
     * 
     * @return: the fire rate of the entity
     */
    public float get_fireRate() {
        return fireRate;
    }

    /*
     * This method is used to get the last attack time of the entity
     * 
     * @param: nothing
     * 
     * @return: the last attack time of the entity
     */
    public long get_lastAttackTime() {
        return lastAttackTime;
    }

    /*
     * This method is used to set the last attack time of the entity
     * 
     * @param time: the last attack time of the entity
     * 
     * @return: nothing
     */
    public void set_lastAttackTime(long time) {
        this.lastAttackTime = time;
    }

    /*
     * This method is used to get the damage of the entity
     * 
     * @param: nothing
     * 
     * @return: the damage of the entity
     */
    public float get_damage() {
        return this.damage;
    }

    /*
     * This method is used to set the damage of the entity
     * 
     * @param damage: the damage of the entity
     * 
     * @return: nothing
     */
    public float get_range() {
        return this.range;
    }

    /*
     * This method is used to set the range of the entity
     * 
     * @param range: the range of the entity
     * 
     * @return: nothing
     */
    public void set_range(float range) {
        this.range = range;
    }

    /*
     * This method is used to get the angle of the entity
     * 
     * @param: nothing
     * 
     * @return: the angle of the entity
     */
    public float get_angle() {
        return this.angle;
    }

    /*
     * This method is used to set the angle of the entity
     * 
     * @param angle: the angle of the entity
     * 
     * @return: nothing
     */
    public void set_angle(float angle) {
        this.angle = angle;
    }

    /*
     * This method is used to compute the angle of the entity
     * 
     * @param newPosition: the new position of the entity
     * 
     * @return: the angle of the entity
     */
    public float compute_angle(Point2D newPosition) {
        double x = newPosition.getX() - this.get_x();
        double y = newPosition.getY() - this.get_y();
        return (float) Math.toDegrees(Math.atan2(y, x));
    }

    /*
     * This method is used to set the image of the entity
     * 
     * @param img: the image of the entity
     * 
     * @return: nothing
     */
    public void set_image(ImageIcon img) {
        this.img = img;
    }

    /*
     * This method is used to get the image of the entity
     * 
     * @param: nothing
     * 
     * @return: the image of the entity
     */
    public void set_img(ImageIcon img) {
        this.img = img;
    }

    /*
     * This method is used to get the image of the entity
     * 
     * @param: nothing
     * 
     * @return: the image of the entity
     */
    public ImageIcon get_image() {
        return this.img;
    }

    /*
     * This method is used to set the x and y coordinates of the entity
     * 
     * @param x: the x coordinate of the entity
     * 
     * @param y: the y coordinate of the entity
     * 
     * @return: nothing
     */
    public void set_pos_x_y(int x, int y) {
        this.position.setLocation((int) x, (int) y);
    }
}
