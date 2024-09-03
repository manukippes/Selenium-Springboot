package com.kimatesting.qa.selenium_springboot.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class HookTakeScreenshot {
    @Autowired
    private ApplicationContext ctx;

    @After
    public void takeScrenshot(Scenario scenario){
        if (scenario.isFailed()) {
            final byte[] screen = this.ctx.getBean(TakesScreenshot.class).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screen, "image/png", "[ERROR-SCREEN]");
        }
    }
}
