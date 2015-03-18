package com.example.test;

import java.io.IOException;

import org.concordion.api.ResultSummary;
import org.concordion.internal.ConcordionBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.test.config.WebDriverConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebDriverConfig.class})
@ActiveProfiles("firefox")
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

    @Test
    public void runConcordion() throws IOException {
        ResultSummary resultSummary = new ConcordionBuilder().build().process(this);
        resultSummary.print(System.out, this);
        resultSummary.assertIsSatisfied(this);
    }

}
