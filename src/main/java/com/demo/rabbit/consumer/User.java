package com.demo.rabbit.consumer;

import com.demo.rabbit.config.MessagingConfig;
import com.demo.rabbit.dto.OrderStatus;
import com.demo.rabbit.dto.Product;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Component
public class User {
    private final static String QUEUE_NAME = "Buzz_queue";
    @RabbitListener(queues = "Buzz_queue")
    public void consumeMessageFromQueue(String product) {
        System.out.println("Message received from queue : " + product);
    }

    public void getDataFromRabbitmq() throws IOException, TimeoutException {
        System.out.println("Create a ConnectionFactory");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        System.out.println("Create a Connection");
        System.out.println("Create a Channel");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

//        System.out.println("Create a queue " + QUEUE_NAME);
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        System.out.println("Start receiving messages ... ");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received: '" + message + "'");
        };
        CancelCallback cancelCallback = consumerTag -> { };
        String consumerTag = channel.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        System.out.println("consumerTag: " + consumerTag);
    }
}
