package com.kimatesting.qa.selenium_springboot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BasePage {

	protected WebDriver driver;

	@Autowired
	protected WebDriverWait wait;

	@Autowired
	public BasePage(WebDriver pDriver){
		this.driver = pDriver;
		PageFactory.initElements(pDriver, this);
    }

	// METHOD
	protected void navigateTo(String url) {
		driver.get(url);
	}

	protected void clickElement(WebElement element) {
		element.click();
	}

	// GET INFO
	protected String getTitle() {
		return driver.getTitle();
	}

	protected String getPageUrl() {
		return driver.getCurrentUrl();
	}

	// UTILS
	protected WebElement findElementByCssSelector(String locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator)));
	}
	protected WebElement findElementByXpath(String locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}
	protected void waitForElement(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitFixTime() throws InterruptedException {Thread.sleep(1000);}

}
