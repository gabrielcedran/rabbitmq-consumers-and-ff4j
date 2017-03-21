package br.com.cedran.consumers.usecases;

import org.ff4j.core.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cedran.consumers.gateways.ConsumerGateway;
import br.com.cedran.consumers.gateways.FeatureGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FlipConsumersState {

    private FeatureGateway featureGateway;
    private ConsumerGateway consumerGateway;

    @Autowired
    public FlipConsumersState(FeatureGateway featureGateway, ConsumerGateway consumerGateway) {
        this.featureGateway = featureGateway;
        this.consumerGateway = consumerGateway;
    }

    public void execute(String featureId) {
        log.info("Key {}", featureId);
        Feature feature = featureGateway.obtainFeatue(featureId);
        if (feature.getCustomProperties().containsKey("queue")) {
            if (feature.isEnable()) {
                consumerGateway.start(feature.getCustomProperties().get("queue").getValue().toString());
            } else {
                consumerGateway.stop(feature.getCustomProperties().get("queue").getValue().toString());
            }
        }
        log.info("Ending process");
    }
}
