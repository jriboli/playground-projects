package com.binaryNomad.message_queue.server.config;

import com.binaryNomad.message_queue.server.constant.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public NewTopic topic() {
        return TopicBuilder
                .name(AppConstant.TOPIC_NAME)
                .build();
    }
}
