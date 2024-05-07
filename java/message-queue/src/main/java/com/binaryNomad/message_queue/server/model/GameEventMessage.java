package com.binaryNomad.message_queue.server.model;

import com.binaryNomad.message_queue.server.enums.GameEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameEventMessage {

    private GameEvent event;
    private String data;
}
