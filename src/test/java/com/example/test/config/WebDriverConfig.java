package com.example.test.config;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("test.properties")
public class WebDriverConfig {

    @Value("${path.to.phantomjs.driver}")
    private String pantomjsWebDriverPath;
    
    @Value("${path.to.chrome.driver}")
    private String chromeWebDriverPath;
    
    @Value("${path.to.firebug.xpi}")
    private String firebugXpiPath;
    
    @Bean
    @Profile("default")
    public WebDriver phantomjsWebDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, pantomjsWebDriverPath);
        return new PhantomJSDriver(caps);
    }
    
    @Bean
    @Profile("chrome")
    public WebDriver chromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", chromeWebDriverPath);
        return new ChromeDriver();
    }
    
    @Bean
    @Profile("firefox")
    public WebDriver firefoxWebDriver() throws IOException {
        File file = new File(firebugXpiPath);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.addExtension(file);
        //Avoid startup screen
        firefoxProfile.setPreference("extensions.firebug.currentVersion", "2.0.8"); 
        return new FirefoxDriver(firefoxProfile);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
