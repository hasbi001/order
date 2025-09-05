package Orders.orderapi.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import Orders.orderapi.models.entity.Order;
import Orders.orderapi.service.OrderProducer;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderProducer producer;

    public OrderController(OrderProducer producer) {
        this.producer = producer;
    }

    @PostMapping
  public String create(@Valid @RequestBody Order req) {
    producer.sendOrder("Orders",req);
    return "Order published";
  }
}
