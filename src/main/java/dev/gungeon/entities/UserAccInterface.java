package dev.gungeon.entities;

import dev.gungeon.utilities.*;

public interface UserAccInterface {

    String[] GetAccs();

    double Deposit(String acc, double x);

    double Withdraw(String acc, double x);

    double ViewBalance(String acc);

    Map<String,Double> ViewAllBalances();

    boolean CreateAcc(String name);

    void LinkUser(String user, String acc);

    void UnlinkUser(String user, String acc);

    LinkedList<String> GetLinks(String acc);

    boolean GetUserLinked(String user);

    boolean GetUserLinkedToAcc(String user, String acc);

    boolean Transfer(String from, String to, double x);

    LinkedList<Double> AccHistory(String acc);
}