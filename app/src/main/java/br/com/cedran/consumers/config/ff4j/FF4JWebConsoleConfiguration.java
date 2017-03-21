package br.com.cedran.consumers.config.ff4j;

import org.ff4j.FF4j;
import org.ff4j.web.ApiConfig;
import org.ff4j.web.FF4jDispatcherServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cedran.consumers.gateways.ff4j.FF4JFilter;

@Configuration
public class FF4JWebConsoleConfiguration {

    private static final String DEFAULT_CONSOLE = "/ff4j-console/*";

    @Autowired
    private FF4JFilter filter;

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

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        final FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(filter);
        filterRegBean.addUrlPatterns("/ff4j-console/features");
        filterRegBean.setEnabled(Boolean.TRUE);
        filterRegBean.setName("FF4J Filter");
        filterRegBean.setAsyncSupported(Boolean.TRUE);
        return filterRegBean;
    }

}
