import gui.Backpack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BackpackTest {
    private Backpack backpack = new Backpack();

    @Test
    public void sizeOfBackpack() {
        backpack.createPopulation();
        backpack.populationToString();
        assertEquals(10, backpack.getItemList().size());
    }




}
