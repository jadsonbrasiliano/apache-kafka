package event;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

public class EventConsumer {

    private boolean flag;
    private Properties properties;

    public EventConsumer() {
        init();
        flag = true;
    }

    private void init() {
        properties = new Properties();
        properties.put("group.id", "group-consumers");
        properties.put("auto.offset.reset", "earliest");
        properties.put("bootstrap.servers", "127.0.0.1:29092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public void receiveMessage() {
        try(Consumer<String, String> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singletonList("topic"));

            while(flag) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));

                for(ConsumerRecord<String, String> record : records) {
                    System.out.println(new Date(record.timestamp()) + " | " + record.offset() + " | " + record.value());

                    if(record.value().equals("kill.application.consumer")) flag = false;
                }
            }
        }
    }
}
