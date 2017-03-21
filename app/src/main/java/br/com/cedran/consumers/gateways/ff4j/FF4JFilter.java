package br.com.cedran.consumers.gateways.ff4j;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cedran.consumers.usecases.FlipConsumersState;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FF4JFilter implements Filter {

    @Autowired
    private FlipConsumersState flipConsumersState;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Initializing FF4J Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        if (servletRequest.getParameterMap().containsKey("op") && servletRequest.getParameterMap().containsKey("uid")) {
            flipConsumersState.execute(servletRequest.getParameter("uid"));
        }
    }

    @Override
    public void destroy() {
        log.info("Destroying FF4J Filter");
    }

}
