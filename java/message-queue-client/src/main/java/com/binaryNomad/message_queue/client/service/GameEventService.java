package com.binaryNomad.message_queue.client.service;

import com.binaryNomad.message_queue.client.constant.AppConstant;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GameEventService {

    @KafkaListener(topics = AppConstant.TOPIC_NAME, groupId = "game-client-group")
    public void gameEventListener(String event) {
        System.out.println("Game Event: " + event);
    }
}
