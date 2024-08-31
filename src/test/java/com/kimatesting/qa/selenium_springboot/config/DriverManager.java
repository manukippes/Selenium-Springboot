package com.kimatesting.qa.selenium_springboot.config;

import io.cucumber.spring.ScenarioScope;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class DriverManager {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    @Bean(destroyMethod = "quit")
    @ScenarioScope
    public WebDriver driver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    @Bean
    public WebDriverWait waitFor(){
        return new WebDriverWait(driver(), TIMEOUT);
    }
}
