
/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * EntityFlyweight.java
 * This file contains the EntityFlyweight class.
 * This class is used to represent the entity flyweight in the game.
 */
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;

/*
 * This class is used to represent the entity flyweight in the game.
 */
public class EntityFlyweight {

    private static final Map<Integer, ImageIcon> EnnemiesMap = new HashMap<Integer, ImageIcon>();
    private static final Map<Integer, ImageIcon> TowersMap = new HashMap<Integer, ImageIcon>();

    /*
     * This method is used to get the image of the ennemies
     * 
     * @param type: the type of the ennemy
     * 
     * @return: the image of the ennemies
     */
    public static ImageIcon getEnnemiesImg(int type) {
        ImageIcon img = EnnemiesMap.get(type);
        if (img == null) {
            img = new ImageIcon("../resources/attackers/attacker" + type + ".png");
            EnnemiesMap.put(type, img);
        }
        return img;
    }

    /*
     * This method is used to get the image of the towers
     * 
     * @param type: the type of the tower
     * 
     * @return: the image of the towers
     */
    public static ImageIcon getTowersImg(int type) {
        ImageIcon img = TowersMap.get(type);
        if (img == null) {
            img = new ImageIcon("../resources/towers/tower" + type + ".png");
            TowersMap.put(type, img);
        }
        return img;
    }
}
