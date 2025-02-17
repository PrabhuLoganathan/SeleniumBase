package com.automation.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.base.DriverManager;

public class LoginPage {
    WebDriver driver;

    // Constructor to initialize driver
    public LoginPage() {
        this.driver = DriverManager.getDriver();
    }

    private By username = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.id("login");

    public void enterUsername(String user) {
        driver.findElement(username).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(password).sendKeys(pass);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
