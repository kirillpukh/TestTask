package com.example.TestTask;

import java.util.Observable;
import java.util.Observer;

public class ObserverExample implements Observer {

    private ObservableTest observableTest;
    private String observerName;

    public ObserverExample(String observerName) {
        this.observerName = observerName;
    }

    @Override
    public void update(Observable observable, Object arg) {
        observableTest = (ObservableTest) observable;
        observableTest.getMessage(this.observerName);
    }

}

