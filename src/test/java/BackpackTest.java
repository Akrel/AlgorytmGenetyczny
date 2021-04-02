import gui.Backpack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BackpackTest {
    private Backpack backpack = new Backpack();

    @Test
    public void sizeOfBackpack() {
        assertEquals(10, backpack.getItemList().size());
    }




}
