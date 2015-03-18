package com.example.test.config;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.gargoylesoftware.htmlunit.BrowserVersion;


@Configuration
public class WebDriverConfig {
    
    @Bean
    @Profile("default")
    public WebDriver htmlUnitWebDriver() {
        return new HtmlUnitDriver(BrowserVersion.CHROME);
    }
    
    @Bean
    @Profile("chrome")
    public WebDriver chromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/drivers/chromedriver");
        return new ChromeDriver();
    }
    
    @Bean
    @Profile("firefox")
    public WebDriver firefoxWebDriver() throws IOException {
        File file = new File("/usr/local/drivers/firebug-2.0.8-fx.xpi");
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(file);
        //Avoid startup screen
        firefoxProfile.setPreference("extensions.firebug.currentVersion", "2.0.8"); 
        return new FirefoxDriver(firefoxProfile);
    }

}
