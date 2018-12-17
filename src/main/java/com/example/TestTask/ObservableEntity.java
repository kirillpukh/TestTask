package com.example.TestTask;

import com.example.Entity.Message;
import com.example.Utils.MessageComparator;
import com.example.Utils.MessagesGenerator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;

@EnableScheduling
public class ObservableEntity extends Observable {
    private HashMap<Message, ArrayList<String>> observableController;
    private PriorityQueue<Message> messages;
    private MessagesGenerator messagesGenerator;
    private Message message;

    public ObservableEntity() {
        Comparator<Message> messageComparator = new MessageComparator();
        this.messages = new PriorityQueue<Message>(10, messageComparator);
        this.observableController = new HashMap<Message, ArrayList<String>>();
    }

    public  Message getMessage(String observerName) {
        if (this.messages.size() == 0)
            return null; //Collections.EMPTY_LIST;

        //LinkedList<Message> messagesList = new LinkedList<Message>().addAll();

        Iterator<Message> messageIterator = messages.iterator();

        while (messageIterator.hasNext()) {
            message = messageIterator.next();

            if (!observableController.containsKey(message)) {
                observableController.put(message, new ArrayList<String>());
                observableController.get(message).add(observerName);
                return message;
                //messages.add(message);
                //System.out.println("> " + observerName + " ::: " + message);
            }
            else if (observableController.containsKey(message) && !observableController.get(message).contains(observerName)) {
                observableController.get(message).add(observerName);
                return message;
                //messages.add(message);
                //System.out.println("> " + observerName + " ::: " + message);
            } else if (observableController.containsKey(message) && observableController.get(message).contains(observerName)) {
                if (observableController.get(message).size() == this.countObservers()) {
                    messageIterator.remove();
                    observableController.remove(message);
                }
            }
        }

        return null;

    }

    public void setMessages(ArrayList msgs) {
        if (msgs.isEmpty() || msgs == null)
            return;

        this.messages.addAll(msgs);

        setChanged();
        notifyObservers();
    }

    /*@Scheduled(fixedRateString = "10")
    private void createMessages() {
        System.out.println("create message");
        this.messages.addAll(messagesGenerator.generate(3));
        setChanged();
        notifyObservers(true);
    }*/

    private void checkQueueSize() {
        if (messages.isEmpty()) {
            setChanged();
            notifyObservers(false);
        }
    }

}
