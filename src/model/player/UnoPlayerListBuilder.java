package model.player;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import model.player.type.IPlayer;

/**
 * Class that builds a player list following a FIFO pattern.
 * 
 * @author danno-s
 */
public class UnoPlayerListBuilder implements IPlayerListBuilder {
  private LinkedBlockingQueue<IPlayer> players = new LinkedBlockingQueue<IPlayer>();

  /**
   * Adds the player to the queue
   * @param player The player to be added
   */
  @Override
  public void addPlayer(IPlayer player) {
    players.add(player);
  }

  /**
   * Empties the queue into an ArrayList, then returns the ArrayList.
   * @return An ArrayList with the players in the queue
   */
  @Override
  public ArrayList<IPlayer> buildPlayerList() {
    ArrayList<IPlayer> playerList = new ArrayList<IPlayer>();
    while(!players.isEmpty()) {
      playerList.add(players.poll());
    }
    return playerList;
  }
}
