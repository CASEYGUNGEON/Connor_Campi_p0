package dev.gungeon.entities;
import dev.gungeon.utilities.exceptions.*;
import dev.gungeon.utilities.structures.LinkedList;

public class Account implements AccountInterface {

    private String name;
    private int identifier;
    private double balance;
    private LinkedList<Double> history;
    private LinkedList<Integer> linked;

    public Account() {
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

    public Account(String n, int id) {
        name = n;
        identifier = id;
        balance = 0;
        history = new LinkedList<Double>();
        linked = new LinkedList<Integer>();
    }

    public void SetName(String n) {
        name = n;
    }

    public String GetName() {
        return name;
    }

    public void SetIdentifier(int i) {
        identifier = i;
    }

    public int GetIdentifier() {
        return identifier;
    }

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
