package com.kimatesting.qa.selenium_springboot;

import com.kimatesting.qa.selenium_springboot.config.TestConfig;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@CucumberContextConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public class CucumberSpringContextConfig {

    @Before
    public void setup(){
        System.out.println("Context have been started.");
    }

}
