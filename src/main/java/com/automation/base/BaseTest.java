package com.automation.base;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;


public class BaseTest extends DriverManager {
  

    @Before
    public static void before_all() {
        driver = DriverManager.setDriver();
    }

    @After
    public static void after_all() {
        DriverManager.quitDriver();
    }
    
}
