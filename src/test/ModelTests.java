package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({NumericDeckTest.class, PlayerListBuilderTest.class, PlayerTest.class,
    UnoCardPilesManagerTest.class, UnoCardPileTest.class, UnoCardTest.class, UnoLogicTest.class,
    UnoPlayerManagerTest.class})



public class ModelTests {

}
