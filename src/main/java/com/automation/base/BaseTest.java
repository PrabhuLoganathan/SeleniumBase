package com.automation.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BaseTest {

    @Before
    public void before_each() {
        DriverManager.setDriver();  // Initialize driver for each scenario
    }

    @After
    public void after_each() {
        DriverManager.quitDriver(); // Quit driver after each scenario
    }
}
