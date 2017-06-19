package exercises.test.correctness;

import exercises.main.Hierarchy;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import exercises.test.helpers.BaseTest;
import exercises.test.types.CorrectnessTests;

public class Constructor extends BaseTest {

    @Category(CorrectnessTests.class)
    @Test
    public void Constructor_NewHierarchyShouldHaveExactly1Element()
    {
        Hierarchy<Integer> hierarchy = new Hierarchy<>(5);
        Assert.assertEquals(1, hierarchy.getCount());
    }

    @Category(CorrectnessTests.class)
    @Test
    public void Constructor_NewHierarchyShouldHaveCorrectElement()
    {
        Hierarchy<Integer> hierarchy = new Hierarchy<>(5);
        Assert.assertTrue(hierarchy.contains(5));
    }

    @Category(CorrectnessTests.class)
    @Test
    public void Hierarchy_ShouldBeGeneric()
    {
        Hierarchy<String> hierarchy = new Hierarchy<String>("exercises/test");
        Assert.assertTrue(hierarchy.contains("exercises/test"));
    }
}
