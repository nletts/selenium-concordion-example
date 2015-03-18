package com.example.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchEngineTest extends BaseTest {

    public String searchFor(String word) throws Exception {
        getDriver().get("http://www.google.com.au");
        
        WebElement searchBox = getDriver().findElement(By.name("q"));
        searchBox.sendKeys(word);
        searchBox.submit();

        WebElement firstResult = (new WebDriverWait(getDriver(), 10)).until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ol#rso li > div > h3.r > a")));


        return firstResult.getText();
    }

}
