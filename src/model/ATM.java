package model;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        //init bank
        Bank bank = new Bank("Melli");
        //init user
        User user = bank.addUser("Arman", "Ebrahimi", "1234");
        //creating a new  account
        bank.addUser("Zarina","Shiri","1234");
        //init evenet handler
        Account checking = new Account("Checking", user, bank);
        //setting the bank for created account
        bank.addAccount(checking);
        //linking a user to created account
        user.addAccount(checking);
        //init a scanner
        Scanner sc = new Scanner(System.in);
        User loggedUser;
        //referring a user if id and pin was correct
        loggedUser = ATM.promptMainMenu(bank, sc);
        //prompt user to select an operation
        ATM.printUserMenu(loggedUser,sc);



    }

    private static void printUserMenu(User loggedUser, Scanner sc) {
        boolean done = false;
        do{
            //printing main functions
            System.out.println("please choose the operation: \n"+
                    "1. print balance\n"+
                    "2. draw cash\n"+
                    "3. deposit\n"+
                    "4. transfer money\n"+
                    "5. print transactions\n"+
                    "5. done");
            //storing user's choice
            int choice = sc.nextInt();
            //validating user's choice
            if (choice >0 && choice <=6 ){
                switch (choice){
                    case 1:
                        //printing the balance of the account
                        loggedUser.getAccounts().get(0).getBalance();
                        break;
                    case 2:
                        //printing a message to console
                        System.out.println("How much would you like to draw");
                        //storing user input
                        double draw = sc.nextInt();
                        //executing draw cash method in account class
                        loggedUser.getAccounts().get(0).drawCash(draw);
                        break;
                    case 3:
                        //asking user the amount
                        System.out.println("How much would you like to deposit?");
                        int deposit = sc.nextInt();
                        double afterDeposit = loggedUser.getAccounts().get(0).deposit(deposit);
                        break;

                    case 4:
                        String id;
                        boolean valid = false;
                        Account destination;
                        do{
                            //asking user to enter a number
                            System.out.println("please enter the ID of the user to whom you would like to " +
                                    "transfer money: ");
                            //finding destination account
                            id = sc.next();
                            destination = Bank.findAccount(id);
                            //checking if the id was valid
                            if (destination != null){
                                valid = true;
                            }else {
                                System.out.println("Please enter a valid id: ");
                            }
                        }while (!valid);
                        //printing the name of the receiver
                        System.out.println("You are transferring money to: "+destination.getHolder().getName());
                        //asking user to enter the amount of money
                        System.out.println("please enter the amount of money to transfer");
                        double amount = sc.nextInt();
                        //firing the consumer
                        loggedUser.getAccounts().get(0).transferMoney(destination, amount);
                        break;
                    case 5:
                        done = true;
                        break;
                }

            }else {
                System.out.println("please choose between 1 and 4");
            }

        }while(!done);
    }

    private static User promptMainMenu(Bank bank, Scanner sc) {
        String userId;
        String pin;
        User loggedUser;
        System.out.println("Welcome to the bank "+bank.getName());
        do{

            System.out.println("please enter your id: ");
            userId = sc.nextLine();
            System.out.println("please enter your pin code: ");
            pin = sc.nextLine();
            loggedUser = bank.userLogin(userId, pin);
            if (loggedUser == null){
                System.out.println("your ID or pin was incorrect try again!");
            }

        }while (loggedUser == null);
        return loggedUser;
    }
    }

