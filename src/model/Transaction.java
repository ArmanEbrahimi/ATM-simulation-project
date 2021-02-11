package model;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.function.Consumer;

public class Transaction {
    public enum Operation {
        DEPOSIT,
        TRANSFER,
        WITHDRAW
    }
    private double amount;
    private Date timeStamp;
    private String memo;
    private Account source;
    private Account destination;
    private Bank bank;
    private Operation operation;
    //construction for deposit and withdraw
    public Transaction(double amount, Account source, Operation operation) {
        this.amount = amount;
        this.source = source;
        this.operation = operation;
        this.timeStamp = new Date();
    }
    //construction for transfer
    public Transaction(double amount, Account source, Account destination, Bank bank,Operation operation) {
        this.amount = amount;
        this.source = source;
        this.destination = destination;
        this.timeStamp = new Date();
        this.bank = bank;
        this.operation = operation;
    }

    public Transaction(double amount, String memo, Account source, Account destination) {
        this.amount = amount;
        this.memo = memo;
        this.source = source;
        this.destination = destination;
        this.timeStamp = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public Account getSource() {
        return source;
    }

    public Account getDestination() {
        return destination;
    }

    public Operation getOperation() {
        return operation;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return timeStamp.toString();
    }
}
