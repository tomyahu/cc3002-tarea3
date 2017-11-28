package model.card.type;

import java.util.ArrayList;
import controller.IController;
import model.IGameLogic;
import model.player.type.IPlayer;

public class ExchangeOneCard extends BasicCard {
  
  /**
   * Initializes an exchange one card with the given color.
   * @param color the color of the card.
   */
  public ExchangeOneCard(Color color) {
    super(color, Symbol.EXCHANGE_ONE_CARD);
  }
  
  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    super.executeAction(game, ctrl);
    
    //Get player card to exchange
    ICard playerCard = game.getCurrentPlayer().getCardToPlay(game, ctrl);
    
    //Get other player card to exchange
    IPlayer playerSelected = game.getCurrentPlayer().selectPlayer(game, ctrl);
    ArrayList<ICard> playerSelectedHand = playerSelected.getHand();
    int num = (int) (Math.random() * playerSelectedHand.size());
    ICard randNewCard = playerSelectedHand.get(num);
    
    //Removes cards from players
    game.getCurrentPlayer().removeCardFromHand(playerCard);
    playerSelected.removeCardFromHand(randNewCard);
    
    //Creates an ArrayList with the card of the other player.
    ArrayList<ICard> currentPNewCard = new ArrayList<ICard>();
    currentPNewCard.add(randNewCard);
    
    //Creates an ArrayList with the card of the current player.
    ArrayList<ICard> otherPNewCard = new ArrayList<ICard>();
    otherPNewCard.add(playerCard);
    
    //Gives the cards to the players
    game.getCurrentPlayer().addToHand(currentPNewCard);
    playerSelected.addToHand(otherPNewCard);
  }

}
