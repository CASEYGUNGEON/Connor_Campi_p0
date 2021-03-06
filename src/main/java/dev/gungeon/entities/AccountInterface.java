package dev.gungeon.entities;
import dev.gungeon.utilities.exceptions.*;
import dev.gungeon.utilities.structures.LinkedList;

public interface AccountInterface {

    void SetName(String n);

    String GetName();

    void SetIdentifier(int i);

    int GetIdentifier();

    int GetOwner();

    void SetOwner(int o);

    void Deposit(double x);

    void Withdraw(double x) throws InsufficientFundsException;

    double GetBalance();

    void SetBalance(double b);

    void LinkUser(int user) throws ElementExistsException;

    void UnlinkUser(int user) throws NotALinkedUserException, ElementNotFoundException, EmptyListException;

    LinkedList<Integer> GetLinkedUsers();

    boolean IsLinked(int user);

    LinkedList<Double> GetHistory();
}
