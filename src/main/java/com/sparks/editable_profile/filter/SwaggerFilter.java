package com.sparks.editable_profile.filter;


import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nandak on Dec, 2019
 */
@Component
public class SwaggerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    /**
     * Filter written for the purpose of redirecting to Swagger UI once user loads http://localhost:8080
     * @param servletRequest request object
     * @param servletResponse response object
     * @param filterChain filter chain object
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getRequestURI().equals("") || request.getRequestURI().equals("/")) {
            response.sendRedirect("/swagger-ui.html");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}