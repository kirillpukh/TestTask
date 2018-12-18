package com.example.Utils;

import com.example.Entity.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MessagesGenerator {
    private ArrayList<Message> messages;

    public MessagesGenerator() {
        messages = new ArrayList<Message>();
    }

    public List generate(int amountMessages) {
        messages.clear();

        for (int i = 0; i < amountMessages; i++) {
            Message message = new Message();
            message.setUuid(UUID.randomUUID());

            Random generator = new Random();
            int randomPriorityValue = generator.nextInt(3);

            message.setPriority(randomPriorityValue);
            messages.add(message);
        }

        return messages;
    }
}
