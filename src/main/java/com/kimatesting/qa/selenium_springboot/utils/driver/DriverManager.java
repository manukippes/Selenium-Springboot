package com.kimatesting.qa.selenium_springboot.utils.driver;

import com.kimatesting.qa.selenium_springboot.enums.Browser;
import io.cucumber.spring.ScenarioScope;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

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
    @Value("${remote.grid.url}")
    private String remoteGridUrl;
    @Value("${remote.execution}")
    private Boolean remoteExecution;

    @Bean(destroyMethod = "quit")
    @ScenarioScope
    public WebDriver driver() throws Exception {
        if (remoteExecution){
            ChromeOptions options = new ChromeOptions();
            options.addArguments(String.format("--window-size=%d,%d", browserWidth, browserHeight));
            options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                put("enableVideo", true);
                put("enableVNC", true);
            }});
            //return new RemoteWebDriver(URI.create("http://192.168.1.15:4444/").toURL(), chromeOptions);
            return new RemoteWebDriver(URI.create("http://192.168.1.15:4444/wd/hub").toURL(), options);
        }else{
            return driverFactory.createDriver(browser,browserHeadless, browserWidth, browserHeight);
        }
    }

    @Bean
    public WebDriverWait waitFor() throws Exception {
        return new WebDriverWait(driver(), Duration.ofSeconds(timeout));
    }
}
