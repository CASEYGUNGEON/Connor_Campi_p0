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

    public int GetOwner() {
        return owner;
    }

    public void SetOwner(int o) {
        owner = o;
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

    public void SetBalance(double b) {
        balance = b;
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
