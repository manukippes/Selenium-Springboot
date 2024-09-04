package com.kimatesting.qa.selenium_springboot.stepDefinitions;

import com.kimatesting.qa.selenium_springboot.enums.MenuSections;
import com.kimatesting.qa.selenium_springboot.pages.LandingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

public class LandingSteps {

	@Autowired
	private LandingPage landingPage;

	//GIVEN
	@Given("The user go to KIMA Testing page")
	public void the_user_go_to_KIMATesting_page() {
		landingPage.goToKIMATestingPage();
	}

	//WHEN
	@When("The user go to section {string}")
	public void the_user_go_to_section(String section) {
		landingPage.goToMenuSection(MenuSections.valueOf(section));
	}

	@When("The user clicks on the Contact button")
	public void the_user_clicks_on_the_contact_button() {
		landingPage.clickOnContactButton();
	}

	//THEN
	@Then("{string} title is displayed")
	public void kima_testing_title_is_displayed(String title) {
		Assert.assertEquals("Page title is invalid.", title, landingPage.getKIMATestingTitle());
	}

	@Then("section {string} is available")
	public void section_is_available(String section) throws InterruptedException {
		String sectionName = MenuSections.valueOf(section).getHref();
		String sectionUrl = landingPage.getSectionUrl();
		Assert.assertTrue(String.format("Menu section name is invalid, Expected: %s, Current: %s", sectionName, sectionUrl), sectionUrl.contains(sectionName));
	}

	@Then("The contact page is showed")
	public void the_contact_page_is_showed() {
		Assert.assertTrue("Contact title is not present.", landingPage.getContactSectionTitle().isDisplayed());
	}
}
