package com.mycompany.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerExample {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.19.0.3:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "1"); 
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        Consumer<String, String> consumer = new KafkaConsumer<>(props);

        consumer.subscribe(Collections.singletonList("test-topic"));

        try 
        {
            while (true)
             {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                // Mesajları işleme
                records.forEach(record -> 
                {
                    System.out.printf("Received message: key = %s, value = %s%n", record.key(), record.value());
                    // Burada mesajları işleyebilirsiniz
                });
            }
        } 
        finally 
        {
            consumer.close();
        }
    }
}
