package com.example.test.ext;

import org.concordion.ext.ScreenshotTaker;
import org.concordion.ext.ScreenshotUnavailableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.IOException;
import java.io.OutputStream;

/*
 * Code taken from:
 * https://github.com/concordion/concordion-screenshot-extension-demo/blob/master/src/test/java/org/concordion/selenium/SeleniumScreenshotTaker.java
 */
public class SeleniumScreenshotTaker implements ScreenshotTaker {

    private final WebDriver driver;

    public SeleniumScreenshotTaker(WebDriver driver) {
        WebDriver baseDriver = driver;
        while (baseDriver instanceof EventFiringWebDriver) {
            baseDriver = ((EventFiringWebDriver) baseDriver).getWrappedDriver();
        }
        this.driver = baseDriver;
    }

    @Override
    public int writeScreenshotTo(OutputStream outputStream) throws IOException {
        byte[] screenshot;
        try {
            screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (ClassCastException e) {
            throw new ScreenshotUnavailableException("driver does not implement TakesScreenshot");
        }
        outputStream.write(screenshot);
        return ((Long) ((JavascriptExecutor) driver).executeScript("return document.body.clientWidth")).intValue() + 2; //window.outerWidth"));
    }

    @Override
    public String getFileExtension() {
        return "png";
    }
}