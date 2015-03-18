package com.example.test.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WebDriverConfig {
    
    @Bean
    public WebDriver chromeWebDriver() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/drivers/chromedriver");
        return new ChromeDriver();
    }

}
