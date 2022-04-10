package dev.gungeon.tests;
import dev.gungeon.entities.*;

import dev.gungeon.utilities.exceptions.ElementExistsException;
import dev.gungeon.utilities.exceptions.ElementNotFoundException;
import dev.gungeon.utilities.structures.LinkedList;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class UserAccTests {
    static UserAcc test;

    @Test
    @Order(1)
    void MakeAndCheckTest() {
        test = new UserAcc("User 1", "bicycle12", 123);
        Assertions.assertEquals("User 1", test.GetName());
        Assertions.assertEquals(123, test.GetId());
    }

    @Test
    @Order(2)
    void MoneyTest() throws Exception {
        test.CreateAcc("Checking");
        test.CreateAcc("Savings");
        test.Deposit(1,24.5);
        test.Transfer(1, 2, 10);
        test.Withdraw(2, 5);
        Assertions.assertEquals(14.5,test.ViewBalance(1));
        Assertions.assertEquals(5,test.ViewBalance(2));
    }

    @Test
    @Order(3)
    void HistoryTest() throws Exception {
        LinkedList<Double> h = test.AccHistory(1);
        Assertions.assertEquals(14.5,h.GoToStart() + h.GoToNext());
    }

    @Test
    @Order(4)
    void LinkTest() throws Exception {
        test.LinkUser(234, 1);
        Assertions.assertTrue(test.GetUserLinkedToAcc(234, 1));
        test.UnlinkUser(234,1);
        Assertions.assertFalse(test.GetUserLinkedToAcc(234, 1));
    }
}
