package com.example.test.search;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.test.BaseTest;

public class ThesaurusTest extends BaseTest {
    
    public List<String> searchThesaurusFor(String noun) throws Exception {
        getDriver().get("http://www.thesaurus.com/");
        
        WebElement searchBox = getDriver().findElement(By.id("q"));
        searchBox.sendKeys(noun);
        searchBox.submit();
        
        List<WebElement> results = waitForElements(By.cssSelector("div#synonyms-0 div.relevancy-list span.text"));
        
        List<String> synonyms = new ArrayList<String>();
        for(WebElement element: results) {
            synonyms.add(element.getText());
        }
        return synonyms;
    }
}
