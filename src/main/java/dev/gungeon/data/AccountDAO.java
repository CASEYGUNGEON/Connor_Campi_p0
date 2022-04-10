package dev.gungeon.data;
import dev.gungeon.entities.Account;
import dev.gungeon.utilities.structures.LinkedList;

import java.sql.Connection;

public interface AccountDAO {

    Account CreateAccount(Account acc);

    Account GetAccount(int id);

    Account UpdateAccount(Account acc);

    boolean DeleteAccount(int id);

    LinkedList<Double> GetHistory(int accid, Connection conn);

    LinkedList<Double> UpdateHistory(Account acc, Connection conn);
}
