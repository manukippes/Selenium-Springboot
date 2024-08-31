package com.kimatesting.qa.selenium_springboot.utils.driver;

import com.kimatesting.qa.selenium_springboot.enums.Browser;
import io.cucumber.spring.ScenarioScope;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class DriverManager {

    @Autowired
    public DriverFactory driverFactory;

    @Value("${browser}")
    private Browser browser;
    @Value("${browserHeadless}")
    private Boolean browserHeadless;
    @Value("${browserWidth}")
    private int browserWidth;
    @Value("${browserHeight}")
    private int browserHeight;
    @Value("${timeout}")
    private int timeout;
    private final Duration TIMEOUT = Duration.ofSeconds(timeout);

    @Bean(destroyMethod = "quit")
    @ScenarioScope
    public WebDriver driver() throws Exception {
        return driverFactory.createDriver(browser,browserHeadless, browserWidth, browserHeight);
    }

    @Bean
    public WebDriverWait waitFor() throws Exception {
        return new WebDriverWait(driver(), TIMEOUT);
    }
}
