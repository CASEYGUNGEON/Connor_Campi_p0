package dev.gungeon.entities;

import dev.gungeon.utilities.exceptions.*;
import dev.gungeon.utilities.structures.LinkedList;
import dev.gungeon.utilities.structures.Map;

public interface UserAccInterface {

    void SetPassword(String pw);

    String GetPassword();

    LinkedList<Account> GetAccs();

    void SetName(String name);

    String GetName();

    int GetId();

    void SetId(int id);

    void Deposit(int acc, double x) throws ElementNotFoundException;

    void Withdraw(int acc, double x) throws InsufficientFundsException, ElementNotFoundException;

    double ViewBalance(int acc) throws ElementNotFoundException;

    void CreateAcc(String name) throws ElementExistsException;

    void LinkUser(int user, int acc) throws ElementExistsException, ElementNotFoundException;

    void UnlinkUser(int user, int acc) throws NotALinkedUserException, ElementNotFoundException, EmptyListException;

    LinkedList<Integer> GetLinks(int acc) throws ElementNotFoundException;

    boolean GetUserLinkedToAcc(int user, int acc) throws ElementNotFoundException;

    void Transfer(int from, int to, double x) throws ElementNotFoundException, InsufficientFundsException;

    LinkedList<Double> AccHistory(int acc) throws ElementNotFoundException;

    Account FindAccount(int num) throws ElementNotFoundException;
}