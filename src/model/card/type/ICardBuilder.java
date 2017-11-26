package model.card.type;

public interface ICardBuilder {
  ICard buildCard(Color aColor, Symbol aSymbol);
}
