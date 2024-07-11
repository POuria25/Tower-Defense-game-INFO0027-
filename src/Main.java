
/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * Main.java
 * This file contains the Main class.
 * This class is used to represent the entities in the game.
 */
import java.io.IOException;
import java.util.*;
import graphics.TowerDefenseView;
import graphics.exceptions.UnknownTowerException;

/*
 * This class is used to represent the entities in the game.
 */
public class Main {

    private final static short MAX_FPS = 60;
    public static void main(String[] args) 
    {
        Game game = Game.get_game();
        
        try {
            TowerDefenseView towerDefenseView = new TowerDefenseView(game);
            towerDefenseView.unlockTower(0);

            Timer timer = new Timer();
            long period = 1000 / MAX_FPS;

            TimerTask gameTask = new TimerTask() {
                @Override
                public void run() {
                    game.get_state().run(game, towerDefenseView);
                }
            };
            
            timer.scheduleAtFixedRate(gameTask, 0, period);
        } 
        catch (IOException error) { 
            error.printStackTrace(); 
        }
        catch(UnknownTowerException error){
            error.printStackTrace();
        }
    }
}

