package br.com.cedran.consumers.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    Queue importantMessage() {
        return new Queue("importantMessage", true);
    }

    @Bean
    DirectExchange messageExchange() {
        return new DirectExchange("messageExchange");
    }

    @Bean
    Binding bindingImportantMessage(Queue importantMessage, DirectExchange messageExchange) {
        return BindingBuilder.bind(importantMessage).to(messageExchange).with("important");
    }

}
