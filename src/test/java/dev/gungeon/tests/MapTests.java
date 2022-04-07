package dev.gungeon.tests;

import dev.gungeon.utilities.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapTests {
    static Map<Integer,String> test = new Map<Integer,String>();

    @Test
    void AddRemoveTest() {
        test.Add(1,"first");
        Assertions.assertEquals("first",test.Get(1));
        test.Remove(1);
        Assertions.assertEquals(null,test.Get(1));
    }

    @Test
    void ContainsTest() {
        test.Add(1, "first");
        test.Add(2, "second");
        Assertions.assertEquals(true,test.ContainsKey(2));
        Assertions.assertEquals(false,test.ContainsValue("third"));
    }


}
