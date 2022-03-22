package application;

import event.EventProducer;

import java.util.Random;

public class Application {

    public static void main(String[] args) {

        Random random = new Random();
        EventProducer producer = new EventProducer();

        for(int i = 0; i < 100; i++) {
            producer.sendMessage("topic", "message." + random.nextInt());
        }

        producer.sendMessage("topic", "kill.application.consumer");
    }
}
