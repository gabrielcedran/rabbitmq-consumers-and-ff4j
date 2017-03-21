package br.com.cedran.consumers.gateways;

public interface ConsumerGateway {

    void start(String queueName);

    void stop(String queueName);

}
