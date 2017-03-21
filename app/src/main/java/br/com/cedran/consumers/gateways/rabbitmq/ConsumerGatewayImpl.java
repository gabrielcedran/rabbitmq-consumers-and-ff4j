package br.com.cedran.consumers.gateways.rabbitmq;

import java.util.Arrays;

import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cedran.consumers.gateways.ConsumerGateway;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConsumerGatewayImpl implements ConsumerGateway {

    @Autowired
    private RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    @Override
    public void start(String queueName) {
        rabbitListenerEndpointRegistry.getListenerContainers().stream().map(container -> (SimpleMessageListenerContainer) container)
                        .filter(message -> Arrays.asList(message.getQueueNames()).contains(queueName)).forEach(container -> {
                            container.start();
                            log.info("Staring queue {} consumer", queueName);
                        });
    }

    @Override
    public void stop(String queueName) {
        rabbitListenerEndpointRegistry.getListenerContainers().stream().map(container -> (SimpleMessageListenerContainer) container)
                        .filter(message -> Arrays.asList(message.getQueueNames()).contains(queueName)).forEach(container -> {
                            container.stop();
                            log.info("Stopping queue {} consumer", queueName);
                        });
    }
}
