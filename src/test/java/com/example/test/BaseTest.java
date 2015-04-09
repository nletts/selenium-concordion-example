package com.example.test;

import java.io.IOException;
import java.util.List;

import org.concordion.api.ResultSummary;
import org.concordion.api.listener.ThrowableCaughtEvent;
import org.concordion.api.listener.ThrowableCaughtListener;
import org.concordion.internal.ConcordionBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.test.config.WebDriverConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebDriverConfig.class})
@Ignore
public class BaseTest {
    
    @Autowired
    private WebDriver driver;

    @Before
    public void startUp() throws Exception {
    }
    
    @After
    public void shutDown() throws Exception {
        driver.close();
    }

    protected WebDriver getDriver() {
        return driver;
    }
    
    protected WebElement waitForElement(final By locator) {
        return (new WebDriverWait(getDriver(), 10)).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    protected List<WebElement> waitForElements(final By locator) {
        return (new WebDriverWait(getDriver(), 10)).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    @Test
    public void runConcordion() throws IOException {
        //Allows seeing Concordion exceptions in jUnit test runner
        ThrowableCaughtListener listener = new ThrowableCaughtListener() {
            @Override
            public void throwableCaught(ThrowableCaughtEvent event) {
                System.out.println(event.getThrowable().getMessage());
            }};
        ResultSummary resultSummary = new ConcordionBuilder().withThrowableListener(listener).build().process(this);
        resultSummary.print(System.out, this);
        resultSummary.assertIsSatisfied(this);
    }

}
