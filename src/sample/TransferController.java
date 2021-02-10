package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.Account;
import model.User;

import java.io.IOException;
import java.util.Optional;

public class TransferController {
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
    @FXML
    TextField textField;
    @FXML
    Pane mainPane;
    @FXML
    Label label;
    StringBuilder sb = new StringBuilder();
    private User user;
    private boolean isIdValid = false;
    private Account destination;


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
    //inserting value through text field
    public void setTextField(String s, TextField textField) {
        textField.setText(s);
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
    @FXML
    public void okHandler() throws IOException {
        //checking to see if destination has been set
        if(!isIdValid){
            //finding destination
            destination = user.getAccounts().get(0).getBank().findAccount(sb.toString());
            //displaying error message to user
            if(destination == null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Invalid ID");
                alert.setContentText("The ID you entered was incorrect! ");
                alert.showAndWait();
            }else{
                //asking user to confirm destination
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Confirmation");
                alert.setContentText("You are transferring money to : "+destination.getHolder().getName());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    //changing isIdValid status to true
                    isIdValid = true;
                    //changing label text
                    label.setText("How much would you like to transfer?");
                }
            }

            //clearing string builder
            sb.delete(0, sb.capacity());
            //clearing text field
            textField.clear();
        }else {
            //storing account balance before transferring
            double currentBalance = user.getAccounts().get(0).getBalance();
            String s = sb.toString();
            Integer amount = Integer.parseInt(s);
            //executing transfer method
            user.getAccounts().get(0).transferMoney(destination,amount);
            //comparing current balance to balance before transfer
            if (currentBalance == user.getAccounts().get(0).getBalance()){
                //displaying unsuccessful transfer message to user
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Insufficient Balance");
                alert.setContentText("Your Balance Is Not Enough!");
                alert.showAndWait();
                sb.delete(0, sb.capacity());
                textField.clear();
            }else {
                //displaying success transfer to user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Successful");
                alert.setContentText("You Successfully Transferred "+amount+" To\n" +
                        destination.getHolder().getName()+"\nYour Current Balance is : "
                        +user.getAccounts().get(0).getBalance());
                alert.showAndWait();
                //changing screen to option menu
                showOptionMenu();
            }


        }


    }
    public void showOptionMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("optionMenu.fxml"));
        //changing the screen
        mainPane.getScene().setRoot(loader.load());
        OptionMenuController controller = loader.getController();
        //passing the user to optionMenu screen
        controller.setUser(user);
    }
    //setting the user
    public void setUser(User user) {
        this.user = user;
    }



}
