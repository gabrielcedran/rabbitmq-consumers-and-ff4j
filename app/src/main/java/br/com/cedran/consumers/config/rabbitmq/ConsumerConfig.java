package br.com.cedran.consumers.config.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

@Configuration
public class ConsumerConfig implements RabbitListenerConfigurer {

    @Value(value = "${concurrentConsumers:1}")
    private Integer concurrentConsumers;

    @Value(value = "${maxConcurrentConsumers:1}")
    private Integer maxConcurrentConsumers;

    @Bean
    SimpleRabbitListenerContainerFactory listenerContainer(final ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory listenerFactory = new SimpleRabbitListenerContainerFactory();
        listenerFactory.setConnectionFactory(connectionFactory);
        listenerFactory.setConcurrentConsumers(concurrentConsumers);
        listenerFactory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        return listenerFactory;
    }

    @Bean
    MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        return converter;
    }

    @Bean
    DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
}
