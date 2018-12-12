package com.example.TestTask;

import com.example.Entity.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
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

        ObservableTest observable = new ObservableTest();
        ObserverExample observer = new ObserverExample("first observer");
        ObserverExample observerExampleAdditional = new ObserverExample("second observer");

        observable.addObserver(observer);
        observable.addObserver(observerExampleAdditional);

        try {
            while (System.in.available() == 0) {
                TimeUnit.SECONDS.sleep(PACKAGE_DELAY_SECONDS);

                for (int i = 0; i < MESSAGE_PACKAGE; i++) {
                    Message message = new Message();
                    message.setUuid(UUID.randomUUID());

                    Random generator = new Random();
                    int randomPriorityValue = generator.nextInt(3);

                    message.setPriority(randomPriorityValue);
                    observable.setMessage(message);
                }

                observable.showMessages();
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } catch (InterruptedException ie) {
            System.out.println(ie.getMessage());
        }
    }
}

