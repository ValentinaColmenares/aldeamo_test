package com.example.prueba.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableRabbit
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue queue() {
        return new Queue("messages.queue");
    }

    // Define el exchange
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("messages.exchange");
    }

    // Define el binding entre la cola y el exchange
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("messages.routingkey");
    }

    // Define el RabbitTemplate para enviar mensajes
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
    
}
