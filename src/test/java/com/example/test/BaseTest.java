package com.example.test;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BaseTest {
    
    private WebDriver driver;

    @Before
    public void startUp() throws Exception {
        driver = getFirefox();
    }
    
    @After
    public void shutDown() throws Exception {
        driver.close();
    }

    protected WebDriver getDriver() {
        return driver;
    }
    
    private WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/drivers/chromedriver");
        return new ChromeDriver();
    }
    
    private WebDriver getFirefox() throws IOException {
        //firebug-2.0.8-fx.xpi
        File file = new File("/usr/local/drivers/firebug-2.0.8-fx.xpi");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(file);
        firefoxProfile.setPreference("extensions.firebug.currentVersion", "2.0.8"); // Avoid startup screen

        return new FirefoxDriver(firefoxProfile);
    }

}
