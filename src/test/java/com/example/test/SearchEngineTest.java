package com.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchEngineTest extends BaseTest {

    public String searchFor(String word) throws Exception {
        getDriver().get("http://www.thesaurus.com/");
        
        WebElement searchBox = getDriver().findElement(By.id("q"));
        searchBox.sendKeys(word);
        searchBox.submit();

        WebElement firstResult = (new WebDriverWait(getDriver(), 10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.relevancy-list ul > li > a > span.text")));


        return firstResult.getText();
    }

}
