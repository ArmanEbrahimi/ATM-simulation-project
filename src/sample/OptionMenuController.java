package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class OptionMenuController {
    @FXML
    Button sayHello;
    public void initialize(){
        sayHello.setOnMouseClicked(e->{
            System.out.println("hello");
        });
    }
}
