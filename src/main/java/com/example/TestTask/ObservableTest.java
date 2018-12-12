package com.example.TestTask;

import com.example.Entity.Message;
import com.example.Utils.MessageComparator;

import java.util.Observable;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.Iterator;

public class ObservableTest extends Observable {
    private PriorityQueue<Message> messages;
    private HashMap<Message, ArrayList<String>> observableController;
    private Message message;

    public ObservableTest() {
        Comparator<Message> messageComparator = new MessageComparator();
        this.messages = new PriorityQueue<Message>(10, messageComparator);
        this.observableController = new HashMap<Message, ArrayList<String>>();
    }

    public void getMessage(String observerName) {
        if (this.messages.size() == 0)
            return;

        Iterator<Message> messageIterator = messages.iterator();

        while (messageIterator.hasNext()) {
            message = messageIterator.next();

            if (!observableController.containsKey(message)) {
                observableController.put(message, new ArrayList<String>());
                observableController.get(message).add(observerName);
                System.out.println("> " + observerName + " ::: " + message);
            }
            else if (observableController.containsKey(message) && !observableController.get(message).contains(observerName)) {
                observableController.get(message).add(observerName);
                System.out.println("> " + observerName + " ::: " + message);
            } else if (observableController.containsKey(message) && observableController.get(message).contains(observerName)) {
                if (observableController.get(message).size() == this.countObservers()) {
                    messageIterator.remove();
                    observableController.remove(message);
                }
            }
        }

    }

    public void setMessage(Message message) {
        if (message == null)
            return;

        this.messages.add(message);
    }

    public void showMessages() {
        setChanged();
        notifyObservers();
    }

}
