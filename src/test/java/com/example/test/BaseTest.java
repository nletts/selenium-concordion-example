package com.example.test;

import java.io.File;
import java.io.IOException;

import org.concordion.api.ResultSummary;
import org.concordion.internal.ConcordionBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.example.test.config.WebDriverConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebDriverConfig.class}, loader = AnnotationConfigContextLoader.class)
public class BaseTest {
    
    @Autowired
    private WebDriver driver;

    @Before
    public void startUp() throws Exception {
        //driver = getChromeDriver();
    }
    
    @After
    public void shutDown() throws Exception {
        driver.close();
    }

    protected WebDriver getDriver() {
        return driver;
    }
    /*
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
    */
    @Test
    public void driverNotNull() throws IOException {
        ResultSummary resultSummary = new ConcordionBuilder().build().process(this);
        resultSummary.print(System.out, this);
        resultSummary.assertIsSatisfied(this);
    }

}
