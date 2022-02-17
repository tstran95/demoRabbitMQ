package com.demo.rabbit.publisher;

import com.demo.rabbit.config.MessagingConfig;
import com.demo.rabbit.consumer.User;
import com.demo.rabbit.dto.Order;
import com.demo.rabbit.dto.OrderStatus;
import com.demo.rabbit.dto.Product;
import com.demo.rabbit.services.ProductService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private User user;

    @Autowired
    private ProductService productService;

//    @PostMapping("/{restaurantName}")
//    public String bookOrder(@RequestBody Order order ,@PathVariable String restaurantName) {
//        order.setId(UUID.randomUUID().toString());
//        //restaurantService
//        //paymentService
//        OrderStatus orderStatus = new OrderStatus(order , "PROCESS" , "order placed successfully in " + restaurantName);
//        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE , MessagingConfig.ROUTING , orderStatus);
//        return "Success !!";
//    }

    @PostMapping("/send")
    public String sendOderProd(@RequestBody Product product) {
        rabbitTemplate.convertAndSend(MessagingConfig.EXCHANGE , MessagingConfig.ROUTING , product);

        return "Success !!";
    }

    @PostMapping("/received")
    public Product receivedOderProd(@RequestBody String products) {
        user.consumeMessageFromQueue(products);
        // Send Object from Partner:
        // Send String from partner:
        Gson g = new Gson();
        Product product = g.fromJson(products , Product.class);
        productService.createProd(product);

        return product;
    }
}
