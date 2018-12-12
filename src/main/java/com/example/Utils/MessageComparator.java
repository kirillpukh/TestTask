package com.example.Utils;

import com.example.Entity.Message;

import java.util.Comparator;

public class MessageComparator implements Comparator<Message> {

        @Override
        public int compare(Message m1, Message m2) {
            return Integer.signum(m2.getPriority() - m1.getPriority());
        }
}
