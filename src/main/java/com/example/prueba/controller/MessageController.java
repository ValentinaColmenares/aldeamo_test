package com.example.prueba.controller;

import com.example.prueba.model.Message;
import com.example.prueba.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public ResponseEntity<String> processMessage(@RequestBody Message message) {
        if (userRepository.existsByOrigin(message.getOrigin())) {
            rabbitTemplate.convertAndSend("messages.queue", message);
            return ResponseEntity.ok("Mensaje enviado a RabbitMQ.");
        } else {
            return ResponseEntity.status(403).body("Origen no registrado.");
        }
    }

    @GetMapping("/test/{origin}")
    public ResponseEntity<Boolean> testOrigin(@PathVariable String origin) {
        return ResponseEntity.ok(userRepository.existsByOrigin(origin));
    }
}
