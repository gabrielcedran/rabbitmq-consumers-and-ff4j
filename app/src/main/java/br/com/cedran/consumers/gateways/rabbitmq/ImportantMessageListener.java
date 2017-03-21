package br.com.cedran.consumers.gateways.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cedran.consumers.model.ImportantMessage;
import br.com.cedran.consumers.usecases.ProcessImportantMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ImportantMessageListener {

    private ProcessImportantMessage processImportantMessage;

    @Autowired
    public ImportantMessageListener(ProcessImportantMessage processImportantMessage) {
        this.processImportantMessage = processImportantMessage;
    }

    @RabbitListener(queues = "importantMessage", containerFactory = "listenerContainer")
    public void execute(ImportantMessage importantMessage) {
        try {
            processImportantMessage.execute(importantMessage);
        } catch (Exception e) {
            log.error("Error while processing message {}", e);
        }
    }

}
