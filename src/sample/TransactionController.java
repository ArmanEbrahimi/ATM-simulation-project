package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import model.Transaction;
import model.User;


import java.io.IOException;


public class TransactionController {
    @FXML
    Pane mainPane;
    @FXML
    ListView<Transaction> listView;
    @FXML
    TextArea textArea;
    private User user;
    // method for displaying option menu window
    public void showOptionMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("optionMenu.fxml"));
        //changing the screen
        mainPane.getScene().setRoot(loader.load());
        OptionMenuController controller = loader.getController();
        controller.setUser(user);
    }
    //filling the list view
    @FXML
    public void fillListView(){
        listView.getItems().setAll(user.getAccounts().get(0).getTransaction());

    }
    @FXML
    //method for displaying details of each transaction
    public void handleClickListView(){
        //storing selected transaction
        Transaction selectedItem = listView.getSelectionModel().getSelectedItem();
        //checking the type of transaction to display a specific type for details
        if (selectedItem.getOperation() == Transaction.Operation.DEPOSIT){
            textArea.setText("On : "+selectedItem.getTimeStamp().toString()+"\n" +
                    "Deposited : "+selectedItem.getAmount()+" $");
        }
        if (selectedItem.getOperation() == Transaction.Operation.WITHDRAW){
            textArea.setText("On : "+selectedItem.getTimeStamp().toString()+"\n" +
                    "Withdrew : "+selectedItem.getAmount()+" $");
        }
        if (selectedItem.getOperation() == Transaction.Operation.TRANSFER){
            textArea.setText("On : "+selectedItem.getTimeStamp().toString()+"\n" +
                    "Transferred : "+selectedItem.getAmount()+" $ to : "+
                    selectedItem.getDestination().getHolder().getName());
        }
    }
    //setting the user
    public void setUser(User user) {
        this.user = user;
    }

}
