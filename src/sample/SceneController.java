package sample;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class SceneController {
    //initing a map with key of string and value of pane
    private Map<String, Pane> scenes = new HashMap<>();
    //variable for main scene
    private Scene main;
    //constructor for SceneController
    public SceneController(Scene main) {
        this.main = main;
    }
    //adding different scenes and giving each scene a specific name
    public void addScreen(String name, Pane pane){
        scenes.put(name,pane);
    }
    //choosing desired scene by searching its name
    public void activate(String name){
        main.setRoot(scenes.get(name));
    }
}
