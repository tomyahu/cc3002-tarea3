package model.card.type;

public class WildDrawFourBuilder implements ICardBuilder {
  
  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new WildDraw4Card();
  }

}
