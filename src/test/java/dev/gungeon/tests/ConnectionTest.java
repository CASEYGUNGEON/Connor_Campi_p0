package dev.gungeon.tests;
import dev.gungeon.utilities.ConnectionUtil;
import org.junit.jupiter.api.*;
import java.sql.Connection;

public class ConnectionTest {
    @Test
    void can_connect() {
        Connection conn = ConnectionUtil.CreateConnection();
        Assertions.assertNotNull(conn);
    }
}
