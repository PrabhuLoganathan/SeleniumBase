Feature: Login Feature

  Scenario: Successful login with valid credentials
    Given User is on login page
    When User enters username "admin" and password "password123"
    And Clicks on login button
    Then User should be redirected to homepage
