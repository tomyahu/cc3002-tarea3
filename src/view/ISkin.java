package view;

import model.card.type.Color;
import model.card.type.Symbol;

public interface ISkin {
  
  /**
   * Gets the direction of the image of the card described.
   * @param color The color of the card
   * @param symbol The symbol of the card
   * @return The direction of the image of the card.
   */
  String getCard(Color color, Symbol symbol);
  
}
