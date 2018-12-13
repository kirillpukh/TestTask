package com.example.TestTask;

import com.example.Entity.Message;
import com.example.Utils.MessageComparator;

import java.util.*;

public class ObservableEntity extends Observable {
    private HashMap<Message, ArrayList<String>> observableController;
    private PriorityQueue<Message> messages;
    //private Message message;

    public ObservableEntity() {
        Comparator<Message> messageComparator = new MessageComparator();
        this.messages = new PriorityQueue<Message>(10, messageComparator);
        this.observableController = new HashMap<Message, ArrayList<String>>();
    }

    public  List getMessages(String observerName) {
        if (this.messages.size() == 0)
            return Collections.EMPTY_LIST;

        //LinkedList<Message> messagesList = new LinkedList<Message>().addAll();

        /*Iterator<Message> messageIterator = messages.iterator();

        while (messageIterator.hasNext()) {
            message = messageIterator.next();

            if (!observableController.containsKey(message)) {
                observableController.put(message, new ArrayList<String>());
                observableController.get(message).add(observerName);
                messages.add(message);
                System.out.println("> " + observerName + " ::: " + message);
            }
            else if (observableController.containsKey(message) && !observableController.get(message).contains(observerName)) {
                observableController.get(message).add(observerName);
                messages.add(message);
                System.out.println("> " + observerName + " ::: " + message);
            } else if (observableController.containsKey(message) && observableController.get(message).contains(observerName)) {
                if (observableController.get(message).size() == this.countObservers()) {
                    messageIterator.remove();
                    observableController.remove(message);
                }
            }
        }*/

        return new LinkedList<Message>(messages);

    }

    public void setMessages(ArrayList msgs) {
        if (msgs.isEmpty() || msgs == null)
            return;

        this.messages.addAll(msgs);

        setChanged();
        notifyObservers();
    }

}
