package dev.gungeon.entities;

import dev.gungeon.utilities.exceptions.*;
import dev.gungeon.utilities.structures.LinkedList;
import dev.gungeon.utilities.structures.Map;
import dev.gungeon.utilities.structures.Node;

public class UserAcc implements UserAccInterface {

    private LinkedList<Account> accounts;
    private String accName;
    private String password;
    private int accId;

    public UserAcc(String name, String pw, int id) {
        accounts = new LinkedList<Account>();
        accName = name;
        password = pw;
        accId = id;
    }

    public void SetPassword(String pw) {
        password = pw;
    }

    public String GetPassword() {
        return password;
    }

    public String GetName() {
        return accName;
    }

    public int GetId() {
        return accId;
    }

    public void SetId(int id) {
        accId = id;
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

    public void CreateAcc(String name) throws ElementExistsException {
        accounts.Add(new Account(name,accId));
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
        Node<Account> cur = accounts.GetStart();
        while(cur != null) {
            if(cur.Get().GetIdentifier() == num)
            {
                return cur.Get();
            }
            cur = cur.Next();
        }
        throw new ElementNotFoundException("Account not found");
    }
}
