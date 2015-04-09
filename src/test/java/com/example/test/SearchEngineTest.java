package com.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchEngineTest extends BaseTest {

    public String searchFor(String word) throws Exception {
        getDriver().get("https://en.wikipedia.org/wiki/Main_Page");
        
        WebElement searchBox = getDriver().findElement(By.id("searchInput"));
        searchBox.sendKeys(word);
        searchBox.submit();
        
        WebElement firstResult = waitForElement(By.cssSelector("#mw-content-text > p"));

        return firstResult.getText();
    }

}
