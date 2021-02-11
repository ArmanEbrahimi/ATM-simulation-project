package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import model.User;

import java.io.IOException;


public class OptionMenuController {
    private User user;
    @FXML
    BorderPane mainPane;
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
    @FXML
    //method for showing the deposit window
    public void showDepositWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("depositWindow.fxml"));
        Scene scene = mainPane.getScene();
        scene.setRoot(loader.load());
        DepositController controller = loader.getController();
        //passing the user to deposit controller
        controller.setUser(user);
        //change the screen

    }
    @FXML
    public void showWithdrawWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("withDrawWindow.fxml"));
        Scene scene = mainPane.getScene();
        scene.setRoot(loader.load());
        WithDrawController controller = loader.getController();
        //passing the user to deposit controller
        controller.setUser(user);
        //change the screen

    }
    public void showTransferWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("transferWindow.fxml"));
        Scene scene = mainPane.getScene();
        scene.setRoot(loader.load());
        TransferController controller = loader.getController();
        //passing the user to deposit controller
        controller.setUser(user);
        //change the screen

    }
    public void showTransactionWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("transactionWindow.fxml"));
        Scene scene = mainPane.getScene();
        scene.setRoot(loader.load());
        TransactionController controller = loader.getController();
        //passing the user to Transaction controller
        controller.setUser(user);
        //change the screen
        controller.fillListView();


    }


    @FXML
    //handling cancel button
    public void cancelButtonHandler(){
        System.exit(0);
    }


}
