package model.card.type;

public class UsedWildCardBuilder implements ICardBuilder {
  
  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new UsedWildCard(aColor, aSymbol);
  }
}
