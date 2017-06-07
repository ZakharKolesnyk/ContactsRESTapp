package com.gmail.kolesnyk.zakhar.configuration;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.gmail.kolesnyk.zakhar")
public class WebConfig extends WebMvcConfigurerAdapter {
}
