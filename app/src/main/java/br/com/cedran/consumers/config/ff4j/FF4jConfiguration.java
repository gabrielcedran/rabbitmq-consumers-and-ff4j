package br.com.cedran.consumers.config.ff4j;

import org.ff4j.FF4j;
import org.ff4j.store.InMemoryFeatureStore;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4jDispatcherServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jConfiguration {

    private static final String DEFAULT_CONSOLE = "/ff4j-console/*";

    // @Autowired
    // private MongoDbFactory mongoFactory;

    @Value("${ff4j.collection:ff4j-features}")
    private String collection;

    @Bean
    public FF4j configureFF4j() {
        final FF4j ff4j = new FF4j();
        configureFeatureStoreMongoDB(ff4j);
        return ff4j;
    }

    private void configureFeatureStoreMongoDB(FF4j ff4j) {
        // final FeatureStoreMongoDB featureStoreMongoDB = new FeatureStoreMongoDB((MongoClient)
        // mongoFactory.getDb().getMongo(), mongoFactory.getDb().getName(), collection);
        final InMemoryFeatureStore featureStoreMongoDB = new InMemoryFeatureStore();
        ff4j.setFeatureStore(featureStoreMongoDB);
    }

    @Bean
    public ApiConfig getApiConfig(@Autowired final FF4j ff4j) {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setFF4j(ff4j);
        return apiConfig;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(@Autowired final FF4j ff4j) {
        return new ServletRegistrationBean(getFF4JServlet(ff4j), DEFAULT_CONSOLE);
    }

    private FF4jDispatcherServlet getFF4JServlet(FF4j ff4j) {
        FF4jDispatcherServlet ff4jDispatcherServlet = new FF4jDispatcherServlet();
        ff4jDispatcherServlet.setFf4j(ff4j);
        return ff4jDispatcherServlet;
    }

}
