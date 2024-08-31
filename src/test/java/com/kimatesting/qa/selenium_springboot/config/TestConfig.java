package com.kimatesting.qa.selenium_springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = "com.kimatesting.qa.selenium_springboot")
@PropertySource("classpath:application-${env:qa}.properties")
public class TestConfig {
}
