package br.com.cedran.consumers.config.ff4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.cedran.consumers.gateways.FeatureGateway;

@Component
public class FeaturesLoader implements CommandLineRunner {

    private final FeatureGateway featureGateway;

    @Autowired
    public FeaturesLoader(final FeatureGateway featureGateway) {
        this.featureGateway = featureGateway;
    }

    @Override
    public void run(String... args) throws Exception {
        for (Features feature : Features.values()) {
            featureGateway.createFeature(feature);
        }
    }
}
