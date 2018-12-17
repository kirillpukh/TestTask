package com.example.TestTask;

import com.example.Entity.Message;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Observable;
import java.util.Observer;

@EnableScheduling
public class ObserverEntity implements Observer {

    private ObservableEntity observableEntity;
    private String observerName;
    private Message message;

    public ObserverEntity(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println("observer update");
        //if (arg) {
        observableEntity = (ObservableEntity) observable;
        message = observableEntity.getMessage(this.observerName);
        showMessage();
    }

    @Scheduled(fixedRateString = "1")
    public void showMessage() {
        System.out.println("observer show message");
        if (message != null)
            System.out.println(this.observerName + " ::: " + this.message);

        message = null;
    }

}

