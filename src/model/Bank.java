package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class Bank {
    private String name;
    private static List<Account> accounts;
    private static List<User> users;
    public static Event<Transaction> transactionEvent = new Event<>();


    //constructor for the bank
    public Bank(String name) {
        this.name = name;
        //initializing user list
        this.users = new ArrayList<>();
        //initializing account list
        this.accounts = new ArrayList<>();
        //creating consumer for transferring
        Consumer<Transaction> consumer = t->{
            //checking the type of the operation
            if (t.getOperation()== Transaction.Operation.TRANSFER){
                //checking the efficiency of the balance
                if(t.getAmount() <= t.getSource().balance){
                    //executing the operation
                    t.getSource().balance -= t.getAmount();
                    t.getDestination().balance += t.getAmount();
                    //printing the balance after operation
                    System.out.println("You successfully transferred money!" +
                            "Your current balance is: "+t.getSource().balance);
                }else{
                    System.out.println("Insufficient balance!");
                }
            }
        };
        transactionEvent.subscribe(consumer);

        //creating consumer for deposit
        Consumer<Transaction> deposit = t->{
            //checking the type of the operation
            if(t.getOperation()== Transaction.Operation.DEPOSIT){
                //executing deposit
                t.getSource().balance += t.getAmount();
                //printing the balance after deposit
                System.out.println("Your balance is: "+t.getSource().balance);
            }
        };
        transactionEvent.subscribe(deposit);

        //creating consumer for withdraw
        Consumer<Transaction> withdraw = t->{
            //checking the type of the operation
            if (t.getOperation()== Transaction.Operation.WITHDRAW){
                //checking for efficiency of the balance
                if(t.getSource().balance >= t.getAmount()){
                    t.getSource().balance -= t.getAmount();
                    //printing the balance after the operation
                    System.out.println("Your Balance is: "+t.getSource().balance);
                }else{
                    System.out.println("Insufficient balance!");
                }

            }
        };
        transactionEvent.subscribe(withdraw);

    }

    public static Account findAccount(String id) {
        for (Account account : accounts){
            if(account.getUuid().equals(id)){
                return account;
            }
        }
        return null;
    }

    //generating a unique id for each user
    public String generateUserId() {
        Random random = new Random();
        String uuid = "";
        boolean unique = false;

        do {
            //generate user id
               for (int i = 0 ; i < 6 ; i++){
               uuid += random.nextInt(10);
           }

           //checking to see if the generated id is unique
            if(!users.isEmpty()){
                for (User u : users){
                    if (uuid != u.getUuid()){
                        unique = true;
                        break;
                    }
                }
            }else {
                unique = true;
            }

        }while (!unique);
        return uuid;
    }

    //generating a unique id for each account
    public String generateAccountId() {
        Random random = new Random();
        String uuid = "";
        boolean unique = false;

        do {
            //generate user id
            for (int i = 0 ; i < 6 ; i++){
                uuid += random.nextInt(10);
            }

            //checking to see if the generated id is unique
            if(!accounts.isEmpty()){
                for (Account a : accounts){
                    if (uuid != a.getUuid()){
                        unique = true;
                        break;
                    }
                }
            }else {
                unique = true;
            }

        }while (!unique);
        return uuid;
    }
    public void addAccount(Account account){
        this.accounts.add(account);
    }

    //adding a new user
    public User addUser(String name,String lastName, String pin){
        User user = new User(name, lastName, pin, this);
        this.users.add(user);

        //at the same time creating a saving account for user as a default account
        Account saving = new Account("Saving", user, this);

        //adding this account to its corresponding holder
        user.addAccount(saving);

        //adding this account to its initiated bank
        this.accounts.add(saving);
        //printing saving account number of user
        System.out.println(user.getName()+" saving account number is "+user.getAccounts().get(0).getUuid());
        return user;
    }
    //looking through user list to find associated user when entering id and pin
    public User userLogin(String id, String pin){
        //looking through user list
        for (User user : users){

            //checking to see if the id and pin is correct
            if (user.getUuid().equals(id) && user.isValidPin(pin)){
                System.out.println("welcome "+user.getName());
                return user;
            }
        }
        //returning null when no user found
        return null;
    }

    public String getName() {
        return this.name;
    }

}
