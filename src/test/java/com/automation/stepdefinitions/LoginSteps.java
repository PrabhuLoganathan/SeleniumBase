package com.automation.stepdefinitions;

import com.automation.base.DriverManager;
import com.automation.pages.LoginPage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps extends DriverManager{
	
	LoginPage loginPage;

	public LoginSteps() {
		
		loginPage = new LoginPage();
	}

	@Given("User is on login page")
	public void user_is_on_login_page() {
		driver.get("https://example.com/login");
	}

	@When("User enters username {string} and password {string}")
	public void user_enters_username_and_password(String user, String pass) {
		loginPage.enterUsername(user);
		loginPage.enterPassword(pass);
	}

	@And("Clicks on login button")
	public void clicks_on_login_button() {
		loginPage.clickLogin();
	}

	@Then("User should be redirected to homepage")
	public void user_should_be_redirected_to_homepage() {
		String expectedUrl = "https://example.com/home";
		Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "User not redirected to homepage!");
	}
}
