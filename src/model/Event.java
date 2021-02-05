package model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Event<T> {
    public List<Consumer<T>> handlers = new ArrayList<>();
    public void subscribe(Consumer<T> handler){
        handlers.add(handler);
    }

    public void fire(T t){
        for(Consumer handler: handlers){
            handler.accept(t);
        }
    }

}
