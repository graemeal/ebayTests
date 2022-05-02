package com.example.ebay.tests;

import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Base class for ebay selenium tests.
 * This class loads the Object Repository for ebay.com
 * and set the geckodriver property
 *
 * Author: Graeme Allen
 */
public class ebayBaseTest {
    public static Properties obj;
    @BeforeAll
    public static void setup() throws IOException {
        File file = new File(".\\src\\com\\example\\ebay\\objectRepository\\ebayOR.properties");
        FileInputStream fis = new FileInputStream(file);
        obj = new Properties();
        obj.load(fis);

        //Firefox's geckodriver *requires* you to specify its location.
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver\\geckodriver.exe");
    }

}
