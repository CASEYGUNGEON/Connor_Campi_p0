package dev.gungeon.entities;
import dev.gungeon.utilities.exceptions.*;
import dev.gungeon.utilities.structures.LinkedList;

public class Account implements AccountInterface {

    private String name;
    private int identifier;
    private int owner;
    private double balance;
    private LinkedList<Double> history;
    private LinkedList<Integer> linked;

    //---- Constructors ----//
    //lots of them. you know, just in case.

    public Account() {
        balance = 0;
        history = new LinkedList<Double>();
        linked = new LinkedList<Integer>();
    }

    public Account(int id) {
        identifier = id;
        balance = 0;
        history = new LinkedList<Double>();
        linked = new LinkedList<Integer>();
    }

    public Account(String n) {
        name = n;
        balance = 0;
        history = new LinkedList<Double>();
        linked = new LinkedList<Integer>();
    }

    public Account(String n, int o) {
        name = n;
        owner = o;
        balance = 0;
        history = new LinkedList<Double>();
        linked = new LinkedList<Integer>();
    }

    public Account(String n, int o, int id) {
        name = n;
        owner = o;
        identifier = id;
        balance = 0;
        history = new LinkedList<Double>();
        linked = new LinkedList<Integer>();
    }

    //---- account name methods ----//

    public void SetName(String n) {
        name = n;
    }

    public String GetName() {
        return name;
    }

    //---- account ID methods. ----//

    // used with JDBC calls to find data in table. not shown to user.
    public void SetIdentifier(int i) {
        identifier = i;
    }

    public int GetIdentifier() {
        return identifier;
    }

    //---- user ID methods ----//

    // for pulling all user's accounts from database.
    public int GetOwner() {
        return owner;
    }

    public void SetOwner(int o) {
        owner = o;
    }


    //---- balance-related methods ----//
    public void Deposit(double x) {
        balance += x;
        history.Add(x);
    }

    public void Withdraw(double x) throws InsufficientFundsException {
        if(balance < x)
        {
            throw new InsufficientFundsException("Insufficient Funds");
        }
        balance -= x;
        history.Add(-x);
    }

    public double GetBalance() {
        return balance;
    }

    public void SetBalance(double b) {
        balance = b;
    }


    //[soda drinker pro voice] bonus soda
    public void LinkUser(int user) throws ElementExistsException {
        if(linked.Contains(user))
            throw new ElementExistsException("User already linked");
        else
            linked.Add(user);
    }

    public void UnlinkUser(int user) throws NotALinkedUserException, ElementNotFoundException, EmptyListException {
        if(!linked.Contains(user))
            throw new NotALinkedUserException("Not a linked user");
        else
            linked.Remove(user);
    }

    public LinkedList<Integer> GetLinkedUsers() {
        return linked;
    }

    public boolean IsLinked(int user) {
        return linked.Contains(user);
    }

    public LinkedList<Double> GetHistory() {
        return history;
    }

}
