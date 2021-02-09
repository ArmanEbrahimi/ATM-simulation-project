package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.Event;
import model.User;



public class OptionMenuController {
    private User user;
    @FXML
    Button balance;
    public void initialize(){

    }

    public void setUser(User user) {
        this.user = user;
    }
    @FXML
    //method for displaying account balance
    public void showBalance(){
       //storing the balance
        double balance = user.getAccounts().get(0).getBalance();
        //init Alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //setting the title
        alert.setTitle("Account Balance :");
        //setting the header
        alert.setHeaderText("Dear "+user.getName());
        //setting the content
        alert.setContentText("Your current balance is : "+balance);
        //displaying the alert
        alert.showAndWait();
    }

}
