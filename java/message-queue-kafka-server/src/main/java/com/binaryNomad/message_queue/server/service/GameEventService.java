package com.binaryNomad.message_queue.server.service;

import com.binaryNomad.message_queue.server.constant.AppConstant;
import com.binaryNomad.message_queue.server.model.GameEventMessage;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameEventService {

    private KafkaTemplate<String, Object> kafkaTemplate;

    public GameEventService(KafkaTemplate<String, Object> template){
        kafkaTemplate = template;
    }
    public void addGameEvent(GameEventMessage message) {
        kafkaTemplate.send(AppConstant.TOPIC_NAME, message.getEvent().toString(), message.getData());
    }
}
