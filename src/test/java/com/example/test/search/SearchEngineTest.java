package com.example.test.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.test.BaseTest;

public class SearchEngineTest extends BaseTest {

    public String searchWikipediaFor(String word) throws Exception {
        getDriver().get("https://en.wikipedia.org/wiki/Main_Page");
        
        WebElement searchBox = getDriver().findElement(By.id("searchInput"));
        searchBox.sendKeys(word);
        searchBox.submit();
        
        WebElement firstResult = waitForElement(By.cssSelector("#mw-content-text > p"));

        return firstResult.getText();
    }
    
    public String searchWordReferenceFor(String search) throws Exception {
        getDriver().get("http://www.wordreference.com/");
        
        WebElement searchBox = getDriver().findElement(By.id("si"));
        searchBox.sendKeys(search);
        searchBox.submit();
        
        WebElement firstResult = waitForElement(By.cssSelector("tr#enfr\\:18029 > td:nth-child(2)"));

        return firstResult.getText();
    }

}
