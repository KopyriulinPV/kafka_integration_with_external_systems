package com.example.order_service.web.controller;
import com.example.order_service.DTO.OrderEvent;
import com.example.order_service.mapper.OrderServiceMapper;
import com.example.order_service.Entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/order_service")
@RequiredArgsConstructor
public class OrderServiceController {

    @Value("${app.kafka.kafkaMessageTopic}")
    private String topicName;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    private final OrderServiceMapper orderServiceMapper;

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Order order) {
        kafkaTemplate.send(topicName, orderServiceMapper.orderToOrderEvent(order));
        return ResponseEntity.ok("Message sent to kafka");
    }

}
