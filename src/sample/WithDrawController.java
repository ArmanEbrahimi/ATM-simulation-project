package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.User;

import java.io.IOException;

public class WithDrawController {
    private User user;
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
    StringBuilder sb = new StringBuilder();

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
    //setting the user
    //method for handling ok button
    public void okHandler() throws IOException {
        //storing the current balance of the user
        double current  = user.getAccounts().get(0).getBalance();
        String s = sb.toString();
        //converting string to integer
        Integer value = Integer.parseInt(s);
        //executing the withdraw method
        double result = user.getAccounts().get(0).drawCash(value);
        //checking to see if the operation was successful
        if (result == current){
            //displaying alert to user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Insufficient Balance!");
            alert.setContentText("Your account balance is not enough");
            alert.showAndWait();
        }else {
            //displaying successful message to user
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successful!");
            alert.setContentText("Your balance is: "+user.getAccounts().get(0).getBalance());
            alert.showAndWait();
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("optionMenu.fxml"));
        //changing the screen
        mainPane.getScene().setRoot(loader.load());
        OptionMenuController controller = loader.getController();
        //passing the user to optionMenu screen
        controller.setUser(user);


    }
    public void showOptionMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("optionMenu.fxml"));
        //changing the screen
        mainPane.getScene().setRoot(loader.load());
        OptionMenuController controller = loader.getController();
        controller.setUser(user);
    }
    //setting user
    public void setUser(User user) {
        this.user =user;
    }


}
