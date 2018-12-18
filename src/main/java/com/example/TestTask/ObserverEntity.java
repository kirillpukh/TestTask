package com.example.TestTask;

import com.example.Entity.Message;
import com.example.Utils.LoggerConstants;
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
        observableEntity = (ObservableEntity) observable;
        message = observableEntity.getMessage(this.observerName);
        showMessage();
    }

    @Scheduled(fixedRateString = "1")
    public void showMessage() {
        if (message != null)
            LoggerConstants.LOGGER_TEST.info(this.observerName + " ::: " + this.message);

        message = null;
    }

}

