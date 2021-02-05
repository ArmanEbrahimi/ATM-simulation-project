package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String uuid;
    private byte pinAByte[];
    private List<Account> accounts;

    public User(String firstName, String lastName, String pin,Bank bank) {
        //Setting user's name and family
        this.firstName = firstName;
        this.lastName = lastName;

        //setting user pin code
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinAByte = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //generating for user a unique id
        this.uuid = bank.generateUserId();

        //initializing accounts list
        this.accounts = new ArrayList<>();

        //printing log message to the console
        System.out.printf("User %s,%s with ID %s created!\n",firstName,lastName,uuid);



    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public String getUuid() {
        return uuid;
    }
    //checking to see if the entered pin is correct
    public boolean isValidPin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()),this.pinAByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }
        //reaching this point means pin was incorrect
        return false;
    }
    //retuning user's complete name;
    public String getName() {
        return this.firstName+" "+this.lastName;
    }
    //retuning associated bank accounts
    public List<Account> getAccounts() {
        return accounts;
    }
}
