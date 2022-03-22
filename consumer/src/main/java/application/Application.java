package application;

import event.EventConsumer;

public class Application {

    public static void main(String[] args) {

        new EventConsumer().receiveMessage();
    }
}
