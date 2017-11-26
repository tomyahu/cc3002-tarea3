package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Class that represents an unplayable card. This is returned when no card is able to be returned.
 * 
 * @author danno
 *
 */
public class NullCard extends AbstractCard {
  private static NullCard instance;

  /**
   * Initializes a null card.
   */
  private NullCard() {
    super(Color.NONE, Symbol.NONE);
  }

  @Override
  public boolean isPlayableOver(ICard otherCard) {
    return false;
  }
  
  @Override
  public boolean isFirstPlayable() {
    return false;
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {}

  @Override
  public boolean isDiscardable() {
    return false;
  }

  public static ICard instance() {
    if(instance == null) instance = new NullCard();
    return instance;
  }

}
