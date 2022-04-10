package dev.gungeon.tests;
import dev.gungeon.utilities.exceptions.EmptyListException;
import dev.gungeon.utilities.structures.LinkedList;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LinkedListTests {
    static LinkedList<String> testList = new LinkedList<String>();

    @Test
    @Order(1)
    void SetFirst() throws Exception {
        testList.Add("Test");
        Assertions.assertEquals(1,testList.Size());
        Assertions.assertEquals("Test",testList.GoToStart());
    }

    @Test
    @Order(2)
    void MultiNodes() throws Exception {
        testList.Add("Test2");
        Assertions.assertNotEquals(testList.GetStart(),testList.GetLast());
        Assertions.assertTrue(testList.Contains("Test2"));
        Assertions.assertEquals(2,testList.Size());
        Assertions.assertEquals("Test",testList.GoToStart());
        Assertions.assertEquals("Test2",testList.GoToNext());
    }
}
