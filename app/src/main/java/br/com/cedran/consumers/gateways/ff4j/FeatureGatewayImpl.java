package br.com.cedran.consumers.gateways.ff4j;

import org.apache.commons.lang3.StringUtils;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.property.PropertyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cedran.consumers.config.ff4j.Features;
import br.com.cedran.consumers.gateways.FeatureGateway;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FeatureGatewayImpl implements FeatureGateway {

    @Autowired
    private FF4j ff4j;

    @Override
    public Feature obtainFeatue(String featureId) {
        return ff4j.getFeature(featureId);
    }

    @Override
    public void createFeature(Features featureEnum) {
        if (!ff4j.exist(featureEnum.getKey())) {
            log.info("Feature {} created with value {}", featureEnum.getKey(), featureEnum.isDefaultValue());
            Feature feature = new Feature(featureEnum.getKey(), featureEnum.isDefaultValue(), featureEnum.getDescription(), featureEnum.getGroup());
            if (StringUtils.isNotEmpty(featureEnum.getQueue())) {
                feature.addProperty(new PropertyString("queue", featureEnum.getQueue()));
            }
            ff4j.createFeature(feature);
        }
    }

}
