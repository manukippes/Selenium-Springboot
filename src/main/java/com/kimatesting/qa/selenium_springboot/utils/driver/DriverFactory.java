package com.kimatesting.qa.selenium_springboot.utils.driver;

import com.kimatesting.qa.selenium_springboot.enums.Browser;
import com.kimatesting.qa.selenium_springboot.enums.Platform;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.HashMap;
import java.util.UUID;

@Component
public class DriverFactory {
    @Value("${remote.selenoid.url}")
    private String selenoidUrl;
    @Value("${remote.selenium.grid.url}")
    private String seleniumGridUrl;
    @Value("${lambatest.username}")
    private String lambatestUsername;
    @Value("${lambatest.accessKey}")
    private String lambatestAccessKey;

    public WebDriver createDriver(Boolean remoteExecution, Platform remotePlatform, Browser browser, Boolean headless, int width, int height) throws Exception {
        if (remoteExecution) {
            String remoteUrl = getRemoteUrl(remotePlatform);
            return new RemoteWebDriver(URI.create(remoteUrl).toURL(), getRemoteBrowserOptions(browser, remotePlatform, headless, width, height));
        } else {
            return createLocalDriver(browser, headless, width, height);
        }
    }

    private WebDriver createLocalDriver(Browser browser, Boolean headless, int width, int height) throws Exception {
        switch (browser) {
            case CHROME:
                return WebDriverManager.chromedriver().capabilities(createChromeBaseOptions(headless, width, height)).create();
            case FIREFOX:
                return WebDriverManager.firefoxdriver().capabilities(createFirefoxBaseOptions(headless, width, height)).create();
            case SAFARI:
                WebDriver driver = WebDriverManager.safaridriver().create();
                driver.manage().window().setSize(new org.openqa.selenium.Dimension(width, height));
                return driver;
            default:
                throw new Exception(String.format("The browser %s was not found", browser.get()));
        }
    }

    private String getRemoteUrl(Platform remotePlatform) throws Exception {
        switch (remotePlatform) {
            case seleniumGrid:
                return seleniumGridUrl;
            case selenoid:
                return selenoidUrl;
            case lambatest:
                String url = String.format("https://%1$s:%2$s@hub.lambdatest.com/wd/hub", lambatestUsername, lambatestAccessKey);
                return url;
            default:
                throw new Exception(String.format("The remote platform %s was not found", remotePlatform));
        }
    }

    private MutableCapabilities getRemoteBrowserOptions(Browser browser, Platform remotePlatform, Boolean headless, int width, int height) {
        MutableCapabilities options;
        switch (browser) {
            case CHROME:
                options = createChromeBaseOptions(headless, width, height);
                break;
            case FIREFOX:
                options = createFirefoxBaseOptions(headless, width, height);
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        switch (remotePlatform) {
            case selenoid:
                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    put("enableVideo", true);
                    put("enableVNC", true);
                }});
            case lambatest:
                options.setCapability("LT:Options", new HashMap<String, Object>() {{
                put("project", "KIMA Testing");
                put("w3c", true);
                }});
                options.setCapability("build", "TestInLambatest");
                options.setCapability("name", "Test"+ UUID.randomUUID());
        }

        return options;
    }

    private ChromeOptions createChromeBaseOptions(Boolean headless, int width, int height) {
        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless=new");
        options.addArguments(String.format("--window-size=%d,%d", width, height));
        return options;
    }

    private FirefoxOptions createFirefoxBaseOptions(Boolean headless, int width, int height) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("--headless");
        options.addArguments(String.format("--width=%d", width));
        options.addArguments(String.format("--height=%d", height));
        return options;
    }
}
