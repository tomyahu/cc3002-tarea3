package model.player;

import java.util.ArrayList;

import model.player.type.IPlayer;

/**
 * Player manager that uses indices to store the current player and next player.
 * 
 * @author danno
 *
 */
public class UnoPlayerManager implements IPlayerManager {
  private ArrayList<IPlayer> players;
  private Direction direction = Direction.COUNTERCLOCKWISE;
  private int currentPlayer = 0, nextPlayer = 0;

  /**
   * Initializes this player manager and sets its player list to the one built by the given player
   * list builder.
   * 
   * @param playerList the player list builder.
   */
  public UnoPlayerManager(IPlayerListBuilder playerList) {
    this.players = playerList.buildPlayerList();
  }
  
  @Override
  public IPlayer getCurrentPlayer() {
    return players.get(currentPlayer);
  }
  
  @Override
  public ArrayList<IPlayer> getPlayers() {
    return players;
  }
  
  @Override
  public void invertDirection() {
    direction = Direction.change(direction);
    nextPlayer = currentPlayer + direction.getValue();
    if(players.size() ==  2) nextPlayer = currentPlayer;
    clamp();
  }
  
  @Override
  public void startTurn() {
    currentPlayer = nextPlayer;
    nextPlayer = currentPlayer + direction.getValue();
    clamp();
  }
  
  @Override
  public void skipPlayer() {
    nextPlayer += direction.getValue();
    clamp();
  }

  private void clamp() {
    currentPlayer = clamp(currentPlayer);
    nextPlayer = clamp(nextPlayer);
  }

  private int clamp(int player) {
    player += players.size();
    player %= players.size();
    return player;
  }
}
