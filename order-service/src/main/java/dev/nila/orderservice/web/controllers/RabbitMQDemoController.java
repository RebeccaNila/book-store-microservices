package dev.nila.orderservice.web.controllers;

// @RestController
// @RequiredArgsConstructor
public class RabbitMQDemoController {

    //    private final RabbitTemplate rabbitTemplate;
    //    private final ApplicationProperties properties;

    //    @PostMapping("/send")
    //    public void sendMessage(@RequestBody MyMessage message) {
    //        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), message.routingKey(), message.payload());
    //    }
}

record MyMessage(String routingKey, MyPayload payload) {}

record MyPayload(String content) {}
