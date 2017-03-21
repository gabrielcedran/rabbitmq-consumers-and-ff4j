package br.com.cedran.consumers.gateways;

import org.ff4j.core.Feature;

import br.com.cedran.consumers.config.ff4j.Features;

public interface FeatureGateway {
    Feature obtainFeatue(String featureId);

    void createFeature(Features feature);
}
