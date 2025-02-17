# **Selenium-Cucumber-BDD-Maven Framework Documentation**

## **📌 Overview**
This documentation provides a step-by-step guide on setting up a **Selenium-Cucumber-BDD-Maven** framework with **TestNG, Extent Reports, Allure Reports, Parallel Execution, and Jenkins Integration**.

---

## **📂 Folder Structure**
```
ProjectRoot/
│── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── automation/
│   │   │   │   │   ├── base/       # Base classes
│   │   │   │   │   ├── config/     # Configuration classes
│   │   │   │   │   ├── pages/      # Page Object Model classes
│   │   │   │   │   ├── utils/      # Utility classes
│   ├── test/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── automation/
│   │   │   │   │   ├── stepdefinitions/  # Cucumber Step Definitions
│   │   │   │   │   ├── runners/         # TestNG-Cucumber Runners
│   │   │   │   │   ├── hooks/           # Hooks (Before/After Test Setup)
│   │   ├── resources/
│   │   │   ├── features/        # Cucumber Feature Files
│   │   │   ├── config.properties  # Configuration File
│
│── pom.xml          # Maven Configuration File
│── testng.xml       # TestNG Suite Configuration
│── logs/            # Log Files
│── reports/         # Test Reports (Extent, Allure)
│── drivers/         # WebDriver Executables
│── target/          # Compiled Test Results
│── .gitignore       # Git Ignore File
│── README.md        # Project Documentation
```

---

## **📌 Step 1: Setting Up the Project**
1. Install **Java, Maven, IntelliJ/Eclipse, Git, ChromeDriver**.
2. Create a **Maven Project** in IntelliJ or Eclipse.
3. Update `pom.xml` with necessary dependencies.

**Dependencies Added in `pom.xml`:**
```xml
<!-- Selenium WebDriver -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.9.1</version>
</dependency>

<!-- Cucumber Dependencies -->
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-java</artifactId>
    <version>7.8.1</version>
</dependency>
<dependency>
    <groupId>io.cucumber</groupId>
    <artifactId>cucumber-testng</artifactId>
    <version>7.8.1</version>
</dependency>

<!-- TestNG -->
<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>7.7.0</version>
    <scope>test</scope>
</dependency>
```

Run:
```bash
mvn clean install
```

---

## **📌 Step 2: Implementing the Framework**

### **1️⃣ WebDriver Singleton (`DriverManager.java`)**
```java
package com.automation.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
```

### **2️⃣ BaseTest Setup (`BaseTest.java`)**
```java
package com.automation.base;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
```

### **3️⃣ Login Feature (`Login.feature`)**
```gherkin
Feature: Login Feature
  Scenario: Successful login
    Given User is on login page
    When User enters username "admin" and password "password123"
    And Clicks on login button
    Then User should be redirected to homepage
```

### **4️⃣ TestNG-Cucumber Runner (`TestRunner.java`)**
```java
package com.automation.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.automation.stepdefinitions",
        plugin = {"pretty", "html:target/cucumber-reports.html", "json:target/cucumber.json"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
```

---

## **📌 Step 3: Running Tests in Parallel**
Updated `testng.xml`:
```xml
<suite name="Parallel Execution" parallel="tests" thread-count="3">
    <test name="Cucumber Tests">
        <classes>
            <class name="com.automation.runners.TestRunner"/>
        </classes>
    </test>
</suite>
```
Run:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## **📌 Step 4: Generating Reports**
### **✅ Allure Reports**
Run:
```bash
allure serve target/allure-results
```

### **✅ Extent Reports**
- Open:
```
target/extent-reports/ExtentReport.html
```

---

## **📌 Step 5: Integrating with Jenkins**
1. Install **Maven, TestNG, Allure Plugin** in Jenkins.
2. Add a **Freestyle Project**.
3. Configure Build:
```bash
clean test -DsuiteXmlFile=testng.xml
```
4. Configure **Post-Build Actions**:
   - **Publish HTML Reports (Extent)**
   - **Publish Allure Reports**

---

## **🚀 Next Steps**
Would you like to:
✅ **Run tests on Selenium Grid (Docker)?**
✅ **Use Cucumber for API Testing?**
✅ **Integrate with BrowserStack/SauceLabs?**

Let me know what to explore next! 🚀

