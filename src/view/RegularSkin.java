package view;

import java.util.ArrayList;
import java.util.HashMap;

import model.card.type.Color;
import model.card.type.Symbol;

public class RegularSkin implements ISkin {

  private HashMap<String, String> map;
  private String skinFolder;

  public RegularSkin(String strFold) {
    skinFolder = strFold;
    
    map = new HashMap<String, String>();
    
    ArrayList<String> coloresArchivo = new ArrayList<String>();
    coloresArchivo.add("blue");
    coloresArchivo.add("red");
    coloresArchivo.add("green");
    coloresArchivo.add("yellow");
    
    ArrayList<String> simbolosArchivo = new ArrayList<String>();
    simbolosArchivo.add("0");
    simbolosArchivo.add("1");
    simbolosArchivo.add("2");
    simbolosArchivo.add("3");
    simbolosArchivo.add("4");
    simbolosArchivo.add("5");
    simbolosArchivo.add("6");
    simbolosArchivo.add("7");
    simbolosArchivo.add("8");
    simbolosArchivo.add("9");
    simbolosArchivo.add("block");
    simbolosArchivo.add("draw_2");
    simbolosArchivo.add("reverse");
    simbolosArchivo.add("star");
    
    ArrayList<Symbol> simbolos = new ArrayList<Symbol>();
    simbolos.add(Symbol.ZERO);
    simbolos.add(Symbol.ONE);
    simbolos.add(Symbol.TWO);
    simbolos.add(Symbol.THREE);
    simbolos.add(Symbol.FOUR);
    simbolos.add(Symbol.FIVE);
    simbolos.add(Symbol.SIX);
    simbolos.add(Symbol.SEVEN);
    simbolos.add(Symbol.EIGHT);
    simbolos.add(Symbol.NINE);
    simbolos.add(Symbol.SKIP);
    simbolos.add(Symbol.DRAW_TWO);
    simbolos.add(Symbol.INVERT);
    simbolos.add(Symbol.EXCHANGE_ONE_CARD);
    
    ArrayList<Color> colores = new ArrayList<Color>();
    colores.add(Color.BLUE);
    colores.add(Color.RED);
    colores.add(Color.GREEN);
    colores.add(Color.YELLOW);
    
    for(int i = 0; i < colores.size(); i++)
    {
      for(int j = 0; j < simbolos.size(); j++)
      {
        map.put(simbolos.get(j).getName() + " " + colores.get(i).getName() ,"file:assets/" + skinFolder + "/" + coloresArchivo.get(i) + "/" + simbolosArchivo.get(j) + ".png");
      }
    }
    
    map.put(Symbol.WILD.getName() + " " + Color.NONE.getName(), "file:assets/" + skinFolder + "/none/wild.png");
    map.put(Symbol.WILD_DRAW_FOUR.getName() + " " + Color.NONE.getName(), "file:assets/" + skinFolder + "/none/wild_draw_4.png");
    map.put(Symbol.WILD_SEE_HAND.getName() + " " + Color.NONE.getName(), "file:assets/" + skinFolder + "/none/wild_star.png");
    
    
    
  }

  @Override
  public String getCard(Color color, Symbol symbol) {
    return map.get(symbol.getName() + " " + color.getName());
  }

}
