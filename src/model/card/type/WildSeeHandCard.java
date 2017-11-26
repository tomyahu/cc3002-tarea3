package model.card.type;

import java.util.ArrayList;

import controller.IController;
import model.IGameLogic;
import model.player.type.IPlayer;

public class WildSeeHandCard extends WildCard {
  
  /**
   * Initializes a wild see hand card with no color and the wild see hand symbol.
   */
  public WildSeeHandCard() {
    super(Color.NONE, Symbol.WILD_SEE_HAND);
  }
  
  @Override
  public boolean isFirstPlayable() {
    return false;
  }
  
  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    super.executeAction(game, ctrl);
    
    IPlayer playerSelected = game.getCurrentPlayer().selectPlayer(game, ctrl);
    ArrayList<ICard> hand = playerSelected.getHand();
    
    StringBuilder strBuild = new StringBuilder();
    
    strBuild.append("El jugador ")
            .append(playerSelected.toString())
            .append(" posee la siguiene mano:\n");
    for(int i = 0; i < hand.size(); i++)
    {
      strBuild.append(hand.get(i).toString()).append("\n");
    }
    
    ctrl.showMessage(strBuild.toString());
  }
  
}
