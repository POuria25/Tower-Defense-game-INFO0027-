/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * WaveState.java
 * This file contains the WaveState class.
 * This class is used to represent the wave state in the game.
 */
import java.util.List;
import java.util.Vector;
import graphics.TowerDefenseView;
import graphics.exceptions.*;

/*
 * This class is used to represent the wave state in the game.
 */
public class WaveState implements StateInterface {

    /*
     * Constructor for the WaveState class
     */
    public void update_base(Base base, TowerDefenseView towerDefenseView)
            throws WrongBasePositionException, EmptySpriteException {
        base.heal();

        towerDefenseView.updateBase((int) base.get_position().getX(), (int) base.get_position().getY(),
                base.get_health(), base.get_image());
    }

    /*
     * This method is used to update the towers in the game
     * 
     * @param towers: the list of towers
     * 
     * @param towerDefenseView: the view of the tower defense game
     * 
     * @return: nothing
     */
    private void update_towers(Vector<Tower> towers, TowerDefenseView towerDefenseView)
            throws WrongTowerPositionException, EmptySpriteException {

        int towerPosx;
        int towerPosy;
        float angle;

        for (Tower tower : towers) {

            towerPosx = (int) tower.get_x();
            towerPosy = (int) tower.get_y();
            angle = tower.get_angle();

            towerDefenseView.updateTowerField(towerPosx, towerPosy, tower.get_image(), angle);
        }
    }

    /*
     * This method is used to update the enemies in the game
     * 
     * @param ennemies: the list of enemies
     * 
     * @param towerDefenseView: the view of the tower defense game
     * 
     * @return: nothing
     */
    public void update_ennemies(List<Enemy> ennemies, TowerDefenseView towerDefenseView)
            throws WrongAttackerPositionException, EmptySpriteException {
        float angle;
        for (Enemy enemy : ennemies) {
            angle = enemy.get_angle();

            towerDefenseView.updateAttackerField(enemy.get_pos(), enemy.get_health(), enemy.get_image(), angle);
        }
    }

    /*
     * This method is used to attack the enemies
     * 
     * @param currentGame: the current game
     * 
     * @param towers: the list of towers
     * 
     * @param ennemies: the list of enemies
     * 
     * @param towerDefenseView: the view of the tower defense game
     * 
     * @return: nothing
     */
    public void attack_ennemies(Game currentGame, Vector<Tower> towers, List<Enemy> ennemies,
            TowerDefenseView towerDefenseView) {
        for (Tower tower : towers) {
            for (Enemy enemy : ennemies) {
                tower.attack(enemy);
                if (enemy.is_dead()) {
                    currentGame.gain_money(enemy.get_rewards());
                }
            }
        }

    }

    /*
     * This method is used to end the wave
     * 
     * @param currentState: the current state
     * 
     * @param currentGame: the current game
     * 
     * @param towerDefenseView: the view of the tower defense game
     * 
     * @return: nothing
     */
    public void wave_end(StateManager currentState, Game currentGame, TowerDefenseView towerDefenseView)
            throws UnknownTowerException {
        currentGame.get_state().set_state(new StandbyState());
        currentGame.increment_level();
        if (currentGame.get_level() < 8) {
            towerDefenseView.unlockTower(currentGame.get_level());
        }
    }

    /*
     * This method is used to run the wave
     * 
     * @param currentGame: the current game
     * 
     * @param towerDefenseView: the view of the tower defense game
     * 
     * @return: nothing
     */
    public void run(Game currentGame, TowerDefenseView towerDefenseView) {

        try {
            if (currentGame.get_base().is_dead()) {
                towerDefenseView.promptNewGame();
                return;
            }

            towerDefenseView.refreshWindow();

            update_base(currentGame.get_base(), towerDefenseView);
            attack_ennemies(currentGame, currentGame.get_towers(), currentGame.get_enemies(), towerDefenseView);
            update_ennemies(currentGame.get_enemies(), towerDefenseView);
            update_towers(currentGame.get_towers(), towerDefenseView);

            currentGame.get_enemy_manager().check_dead();
            currentGame.get_enemy_manager().update();

            if (currentGame.get_enemies().isEmpty()) {
                wave_end(currentGame.get_state(), currentGame, towerDefenseView);
            }

            towerDefenseView.updateMoney(currentGame.get_money());

        } catch (UnknownTowerException error) {
            error.printStackTrace();
        } catch (WrongTowerPositionException error) {
            error.printStackTrace();
        } catch (WrongAttackerPositionException error) {
            error.printStackTrace();
        } catch (WrongBasePositionException error) {
            error.printStackTrace();
        } catch (EmptySpriteException error) {
            error.printStackTrace();
        }

    }

    /*
     * This method is used to launch the wave
     * 
     * @param currentGame: the current game
     * 
     * @return: nothing
     */
    public void launch_wave(Game currentGame) {
        return;
    }

}
