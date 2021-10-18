package com.epam.esm.zotov.module2.api;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@ComponentScan("com.epam.esm.zotov.module2")
@PropertySource("classpath:api.properties")
public class ApiRootConfig {
    @Value("${msg.baseName}")
    private String baseName;
    @Value("${msg.defaultLocale}")
    private String defaultLocale;
    @Value("${msg.defaultEncoding}")
    private String defaultEncoding;

    @Bean("messageSource")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(baseName);
        messageSource.setDefaultLocale(Locale.forLanguageTag(defaultLocale));
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding(defaultEncoding);
        return messageSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}