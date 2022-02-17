package com.demo.rabbit.consumer;

import com.demo.rabbit.config.MessagingConfig;
import com.demo.rabbit.dto.OrderStatus;
import com.demo.rabbit.dto.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class User {

    @RabbitListener(queues = "Buzz_queue")
    public void consumeMessageFromQueue(String product) {
        System.out.println("Message received from queue : " + product);
    }
}
