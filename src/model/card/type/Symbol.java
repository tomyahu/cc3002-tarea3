package model.card.type;

import java.util.Arrays;

/**
 * Symbols (Numeric and special) for UNO Cards.
 * @author eriveros
 *
 */
public enum Symbol {
  ZERO("0"),
  ONE("1"),
  TWO("2"), 
  THREE("3"),
  FOUR ("4"),
  FIVE("5"),
  SIX("6"),
  SEVEN("7"),
  EIGHT("8"),
  NINE("9"),
  SKIP("Saltar"),
  DRAW_TWO("Robar 2"),
  INVERT("Invertir Direccion"),
  EXCHANGE_ONE_CARD("Invertir Direccion"),
  WILD("Comodin"),
  WILD_DRAW_FOUR("Comodin + Roba 4"),
  USED_WILD_CARD("Comodin Usado"),
  WILD_SEE_HAND("Invertir Direccion"),
  NONE("Sin Simbolo");
  
  private String name;
  
  /**
   * Constructor for a symbol can assign a name for it.
   * @param name
   */
  Symbol(String name) {
    this.name = name;
  }
  
  /**
   * Returns the name of the symbol.
   * @return symbol's name
   */
  public String getName() {
    return this.name;
  }
  
  /**
   * Returns the numeric symbols
   * @return numeric symbols.
   */
  public static Symbol[] getNumeric() {
    return Arrays.copyOfRange(Symbol.values(),0,10);
  }
}
