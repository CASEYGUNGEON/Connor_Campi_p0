package dev.gungeon.entities;

import dev.gungeon.utilities.LinkedList;
import dev.gungeon.utilities.Map;

public class UserAcc implements UserAccInterface {

    private Map<String,Double> accounts;
    private Map<String,LinkedList<String>> links;
    LinkedList<Double>[] acchistory;

    public UserAcc() {
        accounts = new Map<String,Double>();
        acchistory = new LinkedList[10];
        links = new Map<String,LinkedList<String>>();
    }

    public String[] GetAccs() {
        String[] accs = new String[accounts.Size()];
        for (int i = 0; i < accs.length; i++) {
            accs[i] = accounts.GetKeyByIndex(i);
        }
        return accs;
    }

    public double Deposit(String acc, double x) {
        Double balance = accounts.Get(acc);
        if(balance != null) {
            accounts.Set(acc, balance + x);
            return accounts.Get(acc);
        }
        return -1;
    }

    public double Withdraw(String acc, double x) {
        Double balance = accounts.Get(acc);
        if(balance == null)
            return -1;
        if(balance < x)
            return -1;
        accounts.Set(acc, balance - x);
        return accounts.Get(acc);
    }

    public double ViewBalance(String acc) {
        if(accounts.ContainsKey(acc))
        {
            accounts.Get(acc);
        }
        return -1;
    }

    public Map<String,Double> ViewAllBalances() {
        return accounts;
    }

    public boolean CreateAcc(String name) {
        return accounts.Add(name, 0.0);
    }

    public void LinkUser(String user, String acc) {
        LinkedList<String> curLinks = links.Get(user);
        if(curLinks == null)
            links.Add(user, new LinkedList<String>());
        else
            links.Get(user).Add(acc);
    }

    public void UnlinkUser(String user, String acc) {
        links.Remove(user);
    }

    public LinkedList<String> GetLinks(String acc) {
        return links.Get(acc);
    }

    public boolean GetUserLinked(String user) {
        return links.ContainsKey(user);
    }

    public boolean GetUserLinkedToAcc(String user, String acc) {
        if(GetUserLinked(user)) {
            return links.Get(user).Contains(acc);
        }
        return false;
    }

    public boolean Transfer(String from, String to, double x) {
        if(Withdraw(from, x) != -1) {
            Deposit(to, x);
            return true;
        }
        return false;
    }

    public LinkedList<Double> AccHistory(String acc) {
        return acchistory[accounts.GetIndex(acc)];
    }
}
