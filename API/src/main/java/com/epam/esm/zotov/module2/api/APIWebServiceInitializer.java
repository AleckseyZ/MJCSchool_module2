package com.epam.esm.zotov.module2.api;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class APIWebServiceInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private static final String activeProfile = "dev";

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { ApiWebConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { ApiRootConfig.class };
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        WebApplicationContext context = super.createRootApplicationContext();
        ((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles(activeProfile);
        return context;
    }

    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
        return dispatcherServlet;
    }
}