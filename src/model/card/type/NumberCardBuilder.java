package model.card.type;

public class NumberCardBuilder implements ICardBuilder {
  
  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new BasicCard(aColor, aSymbol);
  }

}
