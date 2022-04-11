package dev.gungeon.app;

import dev.gungeon.entities.*;
import dev.gungeon.data.*;
import dev.gungeon.utilities.exceptions.ElementExistsException;
import dev.gungeon.utilities.exceptions.ElementNotFoundException;
import dev.gungeon.utilities.exceptions.EmptyListException;
import dev.gungeon.utilities.exceptions.InsufficientFundsException;
import dev.gungeon.utilities.structures.LinkedList;

import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        AccountDAOImpl accdao = new AccountDAOImpl();
        UserAccDAOImpl userdao = new UserAccDAOImpl();

        System.out.println("Welcome to %BANKNAME Bank!\n");
        System.out.println("What do you need today? Select one:");
        System.out.println("1 - Create User Account\n2 - Access User Account");
        int choice = scanner.nextInt();

        switch(choice) {
            case 1: { //create user
                System.out.print("Enter a name for the user account: ");
                String name = scanner.next();
                System.out.print("Enter a password: ");
                String password = scanner.next();
                UserAcc user = new UserAcc(name,password);
                try {
                    user = userdao.CreateUser(user);
                    System.out.println("User account successfully created.");
                }
                catch (ElementExistsException e) {
                    System.out.println("User account with that name already exists.");
                }
            } break;
            case 2: { //log in
                System.out.print("\nUsername: ");
                String name = scanner.next();
                System.out.print("Password: ");
                String password = scanner.next();
                UserAcc user = new UserAcc(null,null);
                try {
                    user = userdao.GetUser(name);
                    if (password.equals(user.GetPassword())) { //if log in successful, load accounts
                        LinkedList<Account> acclist = userdao.GetAccounts(user.GetId());
                        boolean repeat = true;
                        while(repeat) { //users may want to do more than one thing, so loop until they ask to exit
                            System.out.println("\nWhat would you like to do?");
                            System.out.println("1 - Create Account\n2 - View Existing Account\n3 - Exit");
                            choice = scanner.nextInt();
                            switch (choice) {
                                case 1: { //create new account
                                    System.out.print("Please input new account name: ");
                                    name = scanner.next();
                                    Account acc = new Account(name,user.GetId());
                                    acclist.Add(accdao.CreateAccount(acc));
                                    System.out.println("Account successfully created.");
                                }
                                break;
                                case 2: { //view existing account
                                    try {
                                        acclist.GoToStart();
                                    }
                                    catch (EmptyListException e){
                                        System.out.println("No accounts found.");
                                        break;
                                    }
                                    System.out.println("\nChoose an account: ");
                                    for(int i = 0; i<acclist.Size();i++) {
                                        System.out.println( (i+1) + " - " + acclist.GetCurrent().GetName());
                                        if(acclist.GetCurrentNode().Next() != null)
                                            acclist.GoToNext();
                                    }
                                    choice = scanner.nextInt();
                                    acclist.ResetCrawl();
                                    if(choice > acclist.Size()) {
                                        System.out.println("Invalid option.");
                                    }
                                    else {
                                        for(int i = 0;i < choice;i++)  //goto chosen account
                                            acclist.GoToNext();
                                        Account acc = acclist.GetCurrent();
                                        System.out.println("\nWhat would you like to do?");
                                        System.out.println("1 - View Balance\n2 - Deposit\n3 - Withdraw");
                                        choice = scanner.nextInt();
                                        switch(choice) {
                                            case 1: { //view balance
                                                System.out.println("\nCurrent balance is: $" + String.format("%.2f",acc.GetBalance()) + "\n");
                                            } break;
                                            case 2: { //deposit
                                                System.out.println("\nHow much would you like to deposit?");
                                                double x = scanner.nextDouble();
                                                if(String.valueOf(x).split("\\.")[1].length() < 3 && x > 0) {
                                                    acc.Deposit(x);
                                                    accdao.UpdateAccount(acc);
                                                    System.out.println("Deposit successful.");
                                                }
                                                else {
                                                    System.out.println("Invalid input.");
                                                }
                                            } break;
                                            case 3: { //withdraw
                                                System.out.println("\nHow much would you like to withdraw?");
                                                double x = scanner.nextDouble();
                                                if(String.valueOf(x).split("\\.")[1].length() < 3 && x > 0) {
                                                    try {
                                                        acc.Withdraw(x);
                                                        accdao.UpdateAccount(acc);
                                                        System.out.println("Withdrawal successful.");
                                                    } catch (InsufficientFundsException e) {
                                                        System.out.println("Insufficient funds.");
                                                    }
                                                }
                                                else {
                                                    System.out.println("Invalid input.");
                                                }
                                            } break;
                                            default: { //goes back to while(repeat)
                                                System.out.println("Invalid option.\n");
                                            }
                                        }
                                    }
                                }
                                break;
                                case 3: {
                                    System.out.println("\nGoodbye. Thank you for choosing %BANKNAME Bank!");
                                    repeat = false;
                                }
                                break;
                                default: { //goes back to while(repeat)
                                    System.out.println("Invalid option.\n");
                                }
                            }
                        }
                    } else { //incorrect password
                        System.out.println("Invalid credentials.");
                    }
                }
                catch (ElementNotFoundException e) { //username does not exist
                    System.out.println("Invalid credentials.");
                }
            } break;
            default: { //come on dude work with me here
                System.out.println("Invalid selection.");
            }
        }
    }
}