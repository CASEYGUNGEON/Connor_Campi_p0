package dev.gungeon.tests;
import dev.gungeon.utilities.Node;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class NodeTests {
    static Node<String> test = new Node<String>();

    @Test
    @Order(1)
    void GetAndSetTest(){
        test.Set("Test");
        Assertions.assertEquals("Test",test.Get());
    }

    @Test
    @Order(2)
    void LinkTest(){
        Node<String> test2 = new Node<String>("Another test");
        test.SetNext(test2);
        Assertions.assertEquals(test2,test.Next());
        test.ClearNext();
        Assertions.assertEquals(null,test.Next());
    }
}
