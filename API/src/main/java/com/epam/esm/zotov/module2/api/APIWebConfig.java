package com.epam.esm.zotov.module2.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@EnableWebMvc
@ComponentScan("com.epam.esm.zotov.module2.api")
public class ApiWebConfig extends WebMvcConfigurationSupport {
}