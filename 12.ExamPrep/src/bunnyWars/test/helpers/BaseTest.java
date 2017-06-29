package bunnyWars.test.helpers;

import bunnyWars.main.IBunnyWarsStructure;
import org.junit.Before;
import bunnyWars.test.BunnyWarsStructureInitializer;

public class BaseTest {
    protected IBunnyWarsStructure BunnyWarCollection;

    @Before
    public void setUp() {
        this.BunnyWarCollection = BunnyWarsStructureInitializer.create();
    }
}
