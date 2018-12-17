package com.example.TestTask;

import com.example.Entity.Message;
import com.example.Utils.MessagesGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Configuration
@PropertySource("classpath:application.properties")
@EnableScheduling
public class ObserverMain implements CommandLineRunner {

    @Value("${message_package}")
    private int MESSAGE_PACKAGE;

    @Value("${package_delay_seconds}")
    private int PACKAGE_DELAY_SECONDS;

    public static void main(String[] args) {
        SpringApplication.run(ObserverMain.class, args);
    }

    @Override
    public void run(String[] args) {

        ObservableEntity observable = new ObservableEntity();
        ObserverEntity observer = new ObserverEntity("first observer");
        ObserverEntity observerEntityAdditional = new ObserverEntity("second observer");

        //MessagesGenerator messagesGenerator = new MessagesGenerator();
        //ArrayList<Message> messages = new ArrayList<Message>();

        observable.addObserver(observer);
        //observable.addObserver(observerEntityAdditional);

        /*try {
            while (System.in.available() == 0) {
                TimeUnit.SECONDS.sleep(PACKAGE_DELAY_SECONDS);

                messages = (ArrayList) messagesGenerator.generate(MESSAGE_PACKAGE);

                observable.setMessages(messages);

            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }*/
    }
}

