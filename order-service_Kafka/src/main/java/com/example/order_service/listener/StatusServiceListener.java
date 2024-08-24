package com.example.order_service.listener;
import com.example.order_service.DTO.StatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatusServiceListener {
    @KafkaListener(topics = "${app.kafka.kafkaMessageTopic2}",
            groupId = "${app.kafka.kafkaMessageGroupId2}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload StatusEvent statusEvent,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        log.info("Received message: {}", statusEvent);
        log.info("Key: {}; Partition: {}; Topic: {}; Timestamp: {}", key, partition, topic, timestamp);

    }
}
