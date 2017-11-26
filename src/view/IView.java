package view;

import model.player.type.IPlayer;

public interface IView {
  
  /**
   * Updates the current status of the game.
   */
  void updateCurrentStatus();
  
  /**
   * Shows a player's hand.
   * 
   * @param player player with the hand you need to show.
   */
  void showPlayerHand(IPlayer player);
  
  /**
   * Shows a custom message.
   * 
   * @param message custom message
   */
  void showMessage(String message);
  
  /**
   * Shows the last card played.
   * 
   * @param player
   * @param card
   */
  public void updatePlayedCard();
  
}
