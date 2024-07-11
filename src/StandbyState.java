/* @Author: KATOUZIAN Pouria ID: S-192865
 * @Date: 11/07/2024
 * StandbyState.java
 * This file contains the StandbyState class.
 * This class is used to represent the standby state in the game.
 */

import graphics.TowerDefenseView;
import graphics.exceptions.EmptySpriteException;
import graphics.exceptions.WrongBasePositionException;
import graphics.exceptions.WrongTowerPositionException;
import java.util.Vector;

/*
 * This class is used to represent the standby state in the game.
 */
public class StandbyState implements StateInterface {

  /*
   * This method is used to update the base in the game
   * 
   * @param base: the base
   * 
   * @param towerDefenseView: the view of the tower defense game
   * 
   * @return: nothing
   */
  public void update_base(Base base, TowerDefenseView towerDefenseView)
      throws WrongBasePositionException, EmptySpriteException {

    towerDefenseView.updateBase((int) base.get_position().getX(),
        (int) base.get_position().getY(), base.get_health(), base.get_image());
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

    for (Tower tower : towers) {

      towerPosx = (int) tower.get_x();
      towerPosy = (int) tower.get_y();

      towerDefenseView.updateTowerField(towerPosx, towerPosy, tower.get_image(), 0);
    }
  }

  /*
   * This method is used to update the money in the game
   * 
   * @param money: the money
   * 
   * @param towerDefenseView: the view of the tower defense game
   * 
   * @return: nothing
   */
  private void update_money(int money, TowerDefenseView towerDefenseView) {
    towerDefenseView.updateMoney(money);
  }

  /*
   * This method is used to run the game
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
      update_towers(currentGame.get_towers(), towerDefenseView);
      update_money(currentGame.get_money(), towerDefenseView);

    } catch (WrongBasePositionException error) {
      error.printStackTrace();
    } catch (WrongTowerPositionException error) {
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
    currentGame.generate_ennemies();
    currentGame.get_state().set_state(new WaveState());
  }

}
