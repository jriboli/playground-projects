package com.binaryNomad.message_queue.server;

import com.binaryNomad.message_queue.server.enums.GameEvent;
import com.binaryNomad.message_queue.server.model.GameEventMessage;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class GameServer {
    private static final String TOPIC_NAME = "game-events";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:29092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // Simulate game events
        for (int i = 0; i < 10; i++) {
            GameEventMessage eventMessage = new GameEventMessage(GameEvent.PLAYER_MOVE, "Player moved to position " + i);
            producer.send(new ProducerRecord<>(TOPIC_NAME, eventMessage.getEvent().toString(), eventMessage.getData()));
        }

        producer.close();
    }
}