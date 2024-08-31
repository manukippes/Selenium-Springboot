package com.kimatesting.qa.selenium_springboot.pages;

import com.kimatesting.qa.selenium_springboot.enums.MenuSections;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LandingPage extends BasePage {

    public LandingPage(WebDriver pDriver) {
        super(pDriver);
    }

    // Variables
    @Value("${url}")
    private String url;

    // WebElements
    @FindBy(css = "a[href='#contact']")
    private WebElement contactButton;
    @FindBy(xpath = "//h2[contains(text(),'¡Contáctanos!')]")
    private WebElement contactSectionTitle;

    public void goToKIMATestingPage(){
        navigateTo(url);
    }

    public String getKIMATestingTitle() {
        return getTitle();
    }

    public void goToMenuSection(MenuSections sectionName){
        WebElement section = findElementByCssSelector(String.format("a[href='%s']", sectionName.getHref()));
        clickElement(section);
    }

    public String getSectionUrl() throws InterruptedException {
        waitFixTime();
        return getPageUrl();
    }

    public void clickOnContactButton() {
        clickElement(contactButton);
    }
}
