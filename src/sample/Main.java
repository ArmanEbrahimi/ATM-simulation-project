package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import model.Bank;
import model.User;

import java.util.Scanner;

public class Main extends Application {
    protected static SceneController sc;

    @Override
    public void start(Stage primaryStage) throws Exception{
        sc = new SceneController();
        sc.addScene("main",FXMLLoader.load(getClass().getResource("sample.fxml")));
        sc.addScene("optionsMenu",FXMLLoader.load(getClass().getResource("optionMenu.fxml")));
        Parent root = sc.activate("main");
        primaryStage.setTitle("Welcome to ATM");
        primaryStage.setScene(new Scene(root, 400, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);



    }
    public static void ChangeScene(String name){
        System.out.println("hey!");
        sc.activate(name);
    }

}
