package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class SceneController {
    //initing a map with key of string and value of pane
    private Map<String, Pane> scenes = new HashMap<>();
    public void addScene(String name,Pane pane){
        scenes.put(name,pane);
    }
    public Parent activate(String name){
        Pane pane = scenes.get(name);
        return pane;
    }
}
