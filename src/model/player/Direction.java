package model.player;

public enum Direction {
  COUNTERCLOCKWISE(1, "Sentido Antihorario"), CLOCKWISE(-1, "Sentido Horario");
  
  int value;
  String name;
  
  Direction(int value, String name) {
    this.value = value;
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getValue() {
    return this.value;
  }
  
  public static Direction change(Direction currentDir) {
    // no ifs magic
    return values()[(currentDir.getValue() * -1 + 2) % 3];
  }
}
