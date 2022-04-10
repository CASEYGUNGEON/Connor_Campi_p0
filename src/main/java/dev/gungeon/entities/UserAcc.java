package dev.gungeon.entities;

import dev.gungeon.utilities.exceptions.*;
import dev.gungeon.utilities.structures.LinkedList;
import dev.gungeon.utilities.structures.Map;

public class UserAcc implements UserAccInterface {

    private LinkedList<Account> accounts;
    private String accName;
    private int accId;

    public UserAcc(String name, int id) {
        accounts = new LinkedList<Account>();
        accName = name;
        accId = id;
    }

    public String GetName() {
        return accName;
    }

    public int GetId() {
        return accId;
    }

    public LinkedList<Account> GetAccs() {
        return accounts;
    }

    public void Deposit(int acc, double x) throws ElementNotFoundException {
        FindAccount(acc).Deposit(x);
    }

    public void Withdraw(int acc, double x) throws InsufficientFundsException, ElementNotFoundException {
        Account a = FindAccount(acc);
        a.Withdraw(x);
    }

    public double ViewBalance(int acc) throws ElementNotFoundException {
        return FindAccount(acc).GetBalance();
    }

    public void CreateAcc(String name, int id) throws ElementExistsException {
        accounts.Add(new Account(name, id));
    }

    public void LinkUser(int user, int acc) throws ElementNotFoundException, ElementExistsException {
        FindAccount(acc).LinkUser(user);
    }

    public void UnlinkUser(int user, int acc) throws ElementNotFoundException, NotALinkedUserException, EmptyListException {
        FindAccount(acc).UnlinkUser(user);
    }

    public LinkedList<Integer> GetLinks(int acc) throws ElementNotFoundException {
        return FindAccount(acc).GetLinkedUsers();
    }

    public boolean GetUserLinkedToAcc(int user, int acc) throws ElementNotFoundException {
        return FindAccount(acc).IsLinked(user);
    }

    public void Transfer(int from, int to, double x) throws ElementNotFoundException, InsufficientFundsException {
        try {
            Withdraw(from, x);
        }
        finally {
            Deposit(to, x);
        }
    }

    public LinkedList<Double> AccHistory(int acc) throws ElementNotFoundException {
        return FindAccount(acc).GetHistory();
    }

    public Account FindAccount(int num) throws ElementNotFoundException {
        accounts.ResetCrawl();
        for(int i = 0; i < accounts.Size(); i++) {
            if(accounts.GoToNext().GetIdentifier() == num) {
                return accounts.GetCurrent();
            }
        }
        throw new ElementNotFoundException("Account not found");
    }
}
