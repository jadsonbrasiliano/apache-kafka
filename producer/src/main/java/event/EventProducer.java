package event;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class EventProducer {

    private Properties properties;

    public EventProducer() {
        init();
    }

    private void init() {
        properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:29092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    }

    public void sendMessage(String topic, String message) {
        try(Producer<String, String> producer = new KafkaProducer<>(properties)) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
            producer.send(record);
            producer.flush();
        }
    }
}
