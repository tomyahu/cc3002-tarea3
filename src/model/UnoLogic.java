package model;

import java.util.ArrayList;

import controller.IController;
import model.card.ICardPilesManager;
import model.card.UnoCardPilesManager;
import model.card.deck.IDeckStrategy;
import model.card.type.ICard;
import model.player.IPlayerListBuilder;
import model.player.IPlayerManager;
import model.player.UnoPlayerManager;
import model.player.type.IPlayer;

public class UnoLogic implements IGameLogic {
  private IPlayerManager playerManager;
  private ICardPilesManager cardManager;
  private ArrayList<ICard> drawWell;

  /**
   * Initializes the game logic with the given players and deck strategy.
   * 
   * @param playerList players to be added into the game.
   * @param deck strategy to build the game's deck.
   */
  public UnoLogic(IPlayerListBuilder playerList, IDeckStrategy deck) {
    playerManager = new UnoPlayerManager(playerList);
    cardManager = new UnoCardPilesManager(deck);
    drawWell = new ArrayList<ICard>();
    for(IPlayer player : playerManager.getPlayers()) {
      cardManager.addCardsToPlayer(player, 7);
    }
  }
  
  @Override
  public boolean hasEnded() {
    return getCurrentPlayer().hasWon();
  }
  
  @Override
  public IPlayer getCurrentPlayer() {
    return playerManager.getCurrentPlayer();
  }
  
  @Override
  public ICard getCurrentPlayedCard() {
    return cardManager.getCurrentPlayedCard();
  }
  
  @Override
  public void autoShoutUNO(IController ctrl) {
    IPlayer currentPlayer = getCurrentPlayer();
    if (!currentPlayer.hasSaidUNO() && currentPlayer.getHandSize() == 1) {
      currentPlayer.setSaidUNO(true);
      ctrl.showMessage("-----UNO!-----");
    }
  }
  
  @Override
  public void startTurn(IController ctrl) {
    playerManager.startTurn();
    autoShoutUNO(ctrl);
    if(!isDrawWellEmpty()) {
      drawCardsFromWell(getCurrentPlayer(), ctrl);
    }
  }
  
  @Override
  public void skipPlayer() {
    playerManager.skipPlayer();
  }
  
  @Override
  public void addToDrawWell(int number) {
    drawWell.addAll(cardManager.drawCards(number));
  }
  
  @Override
  public void resetDrawWell() {
    drawWell.clear();
  }
  
  @Override
  public boolean isDrawWellEmpty() {
    return drawWell.isEmpty();
  }
  
  @Override
  public void drawCardsFromWell(IPlayer player, IController ctrl) {
    player.addToHand(drawWell);
    resetDrawWell();
    startTurn(ctrl);
  }
  
  @Override
  public void invertDirection() {
    playerManager.invertDirection();
  }
  
  @Override
  public boolean playCard(ICard playedCard, IController ctrl) {
    if (playedCard.isPlayableOver(getCurrentPlayedCard())) {
      getCurrentPlayer().removeCardFromHand(playedCard);
      cardManager.discard(playedCard);
      playedCard.executeAction(this, ctrl);
      autoShoutUNO(ctrl);
      return true;
    }
    return false;
  }
  
  @Override
  public ICard drawOneCard(IPlayer player) {
    player.setSaidUNO(false);
    return cardManager.addCardsToPlayer(player, 1).get(0);
  }
  
  @Override
  public void announceWinner(IController ctrl) {
    ctrl.showMessage(getCurrentPlayer() + " ha ganado!");
  }
  
  @Override
  public ArrayList<IPlayer> getPlayers() {
    return playerManager.getPlayers();
  }
}
