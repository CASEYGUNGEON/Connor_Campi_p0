package dev.gungeon.data;
import dev.gungeon.entities.Account;
import dev.gungeon.entities.UserAcc;
import dev.gungeon.utilities.structures.LinkedList;

import java.sql.Connection;

public interface AccountDAO {

    Account CreateAccount(Account acc);

    Account GetAccount(int id);

    Account UpdateAccount(Account acc);

    boolean DeleteAccount(int id);

    boolean DeleteAccount(Account acc);

    void LinkUser(int user);

    void UnlinkUser(int user);

    LinkedList<Double> GetHistory(int accid, Connection conn);

    LinkedList<Double> UpdateHistory(Account acc, Connection conn);
}
