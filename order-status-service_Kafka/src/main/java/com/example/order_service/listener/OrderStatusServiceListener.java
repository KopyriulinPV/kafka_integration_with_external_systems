package com.example.order_service.listener;
import com.example.order_service.DTO.OrderEvent;
import com.example.order_service.DTO.StatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderStatusServiceListener {

    @Value("${app.kafka.kafkaMessageTopic2}")
    private String topicName;

    private final KafkaTemplate<String, StatusEvent> kafkaTemplate;

    @KafkaListener(topics = "${app.kafka.kafkaMessageTopic}",
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload OrderEvent orderEvent,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

        StatusEvent statusEvent = new StatusEvent();
        statusEvent.setStatus("CREATED");
        statusEvent.setDate(Instant.now());

        kafkaTemplate.send(topicName, statusEvent);
    }



}
