package pitFortress.test;

import org.junit.Before;
import pitFortress.main.PitFortressCollection;
import pitFortress.main.interfaces.IPitFortress;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class BaseTestClass {

    protected IPitFortress PitFortressCollection;

    @Before
    public void init() {
        this.PitFortressCollection = new PitFortressCollection();
    }

    public <T> List<T> toList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
