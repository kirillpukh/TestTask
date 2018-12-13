package com.example.TestTask;

import com.example.Entity.Message;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

public class ObserverEntity implements Observer {

    private ObservableEntity observableEntity;
    private String observerName;
    private LinkedList<Message> messages;

    public ObserverEntity(String observerName) {

        this.observerName = observerName;
        messages = new LinkedList<Message>();
    }

    @Override
    public void update(Observable observable, Object arg) {
        observableEntity = (ObservableEntity) observable;
        messages = (LinkedList<Message>) observableEntity.getMessages(this.observerName);
        showMessages();
    }

    public void showMessages() {
        for(Message m : messages)
            System.out.println(this.observerName + " ::: " + m);

        System.out.println("--- \n");
    }

}

