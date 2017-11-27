package model.player.type;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import controller.IController;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.ICard;

/**
 * Class that relies on chance to retrieve the correct card from the hand.
 * 
 * @author danno
 *
 */
public class NewRandomPlayer extends AbstractPlayer {

  /**
   * Initializes this player with the given name.
   * 
   * @param name name to give this player.
   */
  public NewRandomPlayer(String name) {
    super(name);
  }

  @Override
  public ICard getCardToPlay(IGameLogic game, IController ctrl) {
    try {
      TimeUnit.MILLISECONDS.sleep(1000);
    } catch (InterruptedException e) {
      
    }
    
    if (needsToDrawCard(game.getCurrentPlayedCard())) {
      return game.drawOneCard(this);
    } else {
      return getCardFromHand((int) (Math.random() * getHandSize()));
    }
  }

  @Override
  public Color selectColor(IGameLogic game, IController ctrl) {
    return Color.getColors()[(int) (Math.random() * 4)];
  }
  
  @Override
  public IPlayer selectPlayer(IGameLogic game, IController ctrl) {
    ArrayList<IPlayer> players = game.getPlayers();
    ArrayList<IPlayer> playersToChooseFrom = new ArrayList<IPlayer>();
    
    for(IPlayer player : players)
    {
      if(!player.equals(game.getCurrentPlayer())) playersToChooseFrom.add(player);
    }
    
    int num = (int) (Math.random() * playersToChooseFrom.size());
    
    return playersToChooseFrom.get(num);
  }
}
