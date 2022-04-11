package dev.gungeon.data;

import dev.gungeon.entities.Account;
import dev.gungeon.entities.UserAcc;
import dev.gungeon.utilities.exceptions.ElementExistsException;
import dev.gungeon.utilities.exceptions.ElementNotFoundException;
import dev.gungeon.utilities.structures.LinkedList;

public interface UserAccDAO {

    UserAcc CreateUser(UserAcc user) throws ElementExistsException;

    UserAcc GetUser(int id) throws ElementNotFoundException;

    UserAcc GetUser(String name) throws ElementNotFoundException;

    UserAcc UpdateUser(UserAcc user);

    boolean DeleteUser(int id);

    boolean DeleteUser(UserAcc user);

    LinkedList<Account> GetAccounts(int id);
}
