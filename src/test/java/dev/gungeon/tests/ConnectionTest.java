package dev.gungeon.tests;
import dev.gungeon.data.AccountDAOImpl;
import dev.gungeon.data.UserAccDAOImpl;
import dev.gungeon.entities.Account;
import dev.gungeon.entities.UserAcc;
import dev.gungeon.utilities.ConnectionUtil;
import org.junit.jupiter.api.*;
import java.sql.Connection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ConnectionTest {
    @Test
    @Order(1)
    void can_connect() {
        Connection conn = ConnectionUtil.CreateConnection();
        Assertions.assertNotNull(conn);
    }

    @Test
    @Order(2)
    void edit_accs() {
        AccountDAOImpl accdao = new AccountDAOImpl();
        Account testacc = new Account("Checking 1", 1);
        testacc.Deposit(24);
        testacc = accdao.CreateAccount(testacc);
        Assertions.assertTrue(testacc.GetIdentifier() > 0);
        accdao.DeleteAccount(testacc);
    }

    @Test
    @Order(3)
    void edit_users() throws Exception {
        UserAccDAOImpl userdao = new UserAccDAOImpl();
        UserAcc testuser = new UserAcc("User 1","bicycle12");
        testuser = userdao.CreateUser(testuser);
        Assertions.assertTrue(testuser.GetId() > 0);
        userdao.DeleteUser(testuser);
    }
}
