package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String name;
    private User holder;
    protected double balance;
    private String uuid;
    private Bank bank;
    private List<Transaction> transactions;

    public Account(String name, User holder,Bank bank ) {
        //setting account's name and holder
        this.name = name;
        this.holder = holder;

        //generating for account a unique id
       this.uuid = bank.generateAccountId();

       //initializing array list for transaction
        this.transactions = new ArrayList<>();
        //linking to associated bank
        this.bank = bank;


    }

    public String getUuid() {
        return this.uuid;
    }

    public double getBalance() {
        System.out.println("Your current balance is: "+this.balance);
        return this.balance;
    }

    public double drawCash(double draw) {
            //creating transaction for withdraw
            Transaction withdraw = new Transaction(draw,this, Transaction.Operation.WITHDRAW);
            //firing the withdraw consumer
            bank.transactionEvent.fire(withdraw);
        return balance;
    }

    public void transferMoney(Account destination, double amount) {
            //init transactions
            this.transactions = new ArrayList<>();
            //creating a transaction
            Transaction transaction = new Transaction(amount, this, destination,bank,Transaction.Operation.TRANSFER);
            //adding transactions to history
            this.transactions.add(transaction);
            //executing transaction
            this.bank.transactionEvent.fire(transaction);
    }

    public User getHolder() {
        return holder;
    }

    public double deposit(int amount) {
        //creating transaction for deposit
        Transaction deposit = new Transaction(amount,this, Transaction.Operation.DEPOSIT);
        //executing transaction
        bank.transactionEvent.fire(deposit);
        //returning balance after depositing
        return this.balance;
    }

    public Bank getBank() {
        return bank;
    }

    public List<Transaction> getTransaction() {
        return transactions;
    }
}
