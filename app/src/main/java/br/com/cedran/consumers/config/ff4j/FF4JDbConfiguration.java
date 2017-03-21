package br.com.cedran.consumers.config.ff4j;

import org.ff4j.FF4j;
import org.ff4j.store.FeatureStoreMongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
public class FF4JDbConfiguration {

    private static final String DEFAULT_CONSOLE = "/ff4j-console/*";

    @Autowired
    private MongoDbFactory mongoFactory;

    @Value("${ff4j.collection:ff4j-features}")
    private String collection;

    @Bean
    public FF4j configureFF4j() {
        final FF4j ff4j = new FF4j();
        configureFeatureStoreMongoDB(ff4j);
        return ff4j;
    }

    private void configureFeatureStoreMongoDB(FF4j ff4j) {
        final FeatureStoreMongoDB featureStoreMongoDB = new FeatureStoreMongoDB((MongoClient) mongoFactory.getDb().getMongo(), mongoFactory.getDb().getName(), collection);
        // final InMemoryFeatureStore featureStoreMongoDB = new InMemoryFeatureStore();
        ff4j.setFeatureStore(featureStoreMongoDB);
    }

}
