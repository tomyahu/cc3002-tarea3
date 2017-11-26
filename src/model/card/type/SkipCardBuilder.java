package model.card.type;

public class SkipCardBuilder implements ICardBuilder {
  
  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new SkipCard(aColor);
  }

}
