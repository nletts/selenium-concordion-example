package com.example.test;


import org.junit.After;


public class Overview extends BaseTest {

    @After
    public void tearDown() throws Exception {
        System.out.println("Close called");
        getDriver().close();
    }
}