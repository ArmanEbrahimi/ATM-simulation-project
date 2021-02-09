package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Bank;
import model.User;

import java.io.IOException;

public class Controller {

    @FXML
    Label screen;
    @FXML
    TextField textField;
    @FXML
    Button buttonOne;
    @FXML
    Button buttonTwo;
    @FXML
    Button buttonThree;
    @FXML
    Button buttonFour;
    @FXML
    Button buttonFive;
    @FXML
    Button buttonSix;
    @FXML
    Button buttonSeven;
    @FXML
    Button buttonEight;
    @FXML
    Button buttonNine;
    @FXML
    Button buttonZero;
    @FXML
    Button buttonOK;
    @FXML
    Button cancelButton;
    //variable for storing a string
    StringBuilder sb;
    //variable for storing id
    String id;
    //variable for storing pincode
    String pin;
    //variable for storing a bank
    Bank bank;
    //variable to check if the id is assigned
    boolean isIdnull = true;

    public void initialize() {
        //init string builder
        sb = new StringBuilder();
        //init bank
        bank = new Bank("Melli");
        //init user
        User user = bank.addUser("Arman", "Ebrahimi", "1234");
        //executing promptmainManu method
        promptMainMenu(bank, screen);
        //setting an action for cancel button
        cancelButton.setOnMouseClicked(e->{
            screen.setText("GoodBye!");
            System.exit(0);
        });


    }

    private static void promptMainMenu(Bank bank, Label label) {
        //displaying welcome message to user
        label.setText("Welcome to Bank " + bank.getName() + "\n" +
                "\"Please Enter Your ID\"");

    }

    public int handleAction(ActionEvent e) {
        //storing the source of the action
        Object source = e.getSource();
        //checking the value of the button
        if (source.equals(buttonOne)) {
            //appending the value to clipboard
            sb.append(1);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 1;
        }
        if (source.equals(buttonTwo)) {
            //appending the value to clipboard
            sb.append(2);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 2;
        }
        if (source.equals(buttonThree)) {
            //appending the value to clipboard
            sb.append(3);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 3;
        }
        if (source.equals(buttonFour)) {
            //appending the value to clipboard
            sb.append(4);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 4;
        }
        if (source.equals(buttonFive)) {
            //appending the value to clipboard
            sb.append(5);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 5;
        }
        if (source.equals(buttonSix)) {
            //appending the value to clipboard
            sb.append(6);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 6;
        }
        if (source.equals(buttonSeven)) {
            //appending the value to clipboard
            sb.append(7);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 7;
        }
        if (source.equals(buttonEight)) {
            //appending the value to clipboard
            sb.append(8);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 8;
        }
        if (source.equals(buttonNine)) {
            //appending the value to clipboard
            sb.append(9);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 9;
        }
        if (source.equals(buttonZero)) {
            //appending the value to clipboard
            sb.append(0);
            //filling the text filed with value
            setTextField(sb.toString(), textField);
            return 0;
        }
        //sending -1 as an indicator for a error
        return -1;
    }

    //inserting text into text field
    public void setTextField(String s, TextField textField) {
        textField.setText(s);
    }

    //method for handling ok button
    public void okButtonHandler() throws IOException {
        //checking to see if id is assigned
        if (isIdnull) {
            //giving the value of string builder to id
            id = sb.toString();
            //making isIdnull variable false for next round
            isIdnull = false;
            //clearing string builder
            sb.delete(0, sb.capacity());
            //clearing text field
            textField.clear();
            //displaying a message to user
            screen.setText("please enter your pin code: ");
        } else {
            //giving the value of the string builder to pin
            pin = sb.toString();
            //clearing string builder
            sb.delete(0, sb.capacity());
            //clearing text field
            textField.clear();
            //executing method
            userLoginHandler(id, pin);
        }
    }

    //method for handling user login
    public void userLoginHandler(String id, String pin) throws IOException {
        //init user
        User loggedUser;
        loggedUser = bank.userLogin(id, pin);
        //checking to see if login was successful
        if (loggedUser == null) {
            //displaying a message to user
            screen.setText("Either Id or Password was wrong, try again!" + "\n" +
                    "\"Please Enter Your ID\"");
            //resetting login process
            isIdnull = true;
            return;
        }
        changeScreen(buttonOK.getScene(), "optionMenu",loggedUser);
//        //displaying  a message to user
//        screen.setText("Welcome "+loggedUser.getName());
//            //disabling text field
//            textField.setDisable(true);
    }

    @FXML
    //method for handling correct button
    public void correctButtonHandler() {
        //checking for last character
        if (sb.length() >= 1) {
            //deleting the last character in string builder
            sb.deleteCharAt((sb.length()) - 1);
            //deleting last character of text field
            textField.setText(sb.toString());

        }
    }
    //method for changing the screen
    public void changeScreen(Scene main, String name,User user) throws IOException {
        SceneController sc = new SceneController(main);
        sc.addScreen("optionMenu", FXMLLoader.load(getClass().getResource("optionMenu.fxml")));
        sc.activate(name);
        //displaying welcome message to user on title of stage
        Stage stage = (Stage) main.getWindow();
        stage.setTitle("Welcome "+user.getName());
    }
}
