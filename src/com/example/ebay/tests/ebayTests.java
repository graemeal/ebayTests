package com.example.ebay.tests;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Junit selenium tests for ebay.com
 *
 * Author: Graeme Allen
 */
public class ebayTests extends ebayBaseTest {

    private static String ebayHomePage = "https://ebay.com/";
    private static String ebayCart = "https://cart.ebay.com/";

    private static WebDriver driver;

    @BeforeAll
    public static void setup() throws IOException {
        ebayBaseTest.setup();
        driver = new FirefoxDriver();
    }

    @AfterAll
    public static void cleanup() {
        driver.close();
    }

    @Test
    /**
     * TEST001
     * Desc: Verify the Page Title of the homepage
     * Steps:
     *  1. Open ebay.com
     * Expected: The page title to read 'Electronics, Cars, Fashion, Collectibles & More | eBay'
     */
    void TEST001_VerifyHomePageTitle() {
        driver.get(ebayHomePage);
        String title = driver.getTitle();
        assertEquals(title, "Electronics, Cars, Fashion, Collectibles & More | eBay");
    }

    @Test
    /**
     * TEST002
     * Desc: Verify a simple search can be performed from the homepage
     * Steps:
     *  1. Open ebay.com
     *  2. Enter 'televisions' in search bar, and click search
     * Expected: Search results page to show, with list of results
     */
    void TEST002_VerifySimpleSearch() {
        driver.get(ebayHomePage);
        driver.findElement(By.xpath(obj.getProperty("SearchBar"))).sendKeys("television");
        driver.findElement(By.xpath(obj.getProperty("SearchBtn"))).click();
        String results = driver.findElement(By.xpath(obj.getProperty("ResultCount"))).getText();
        Assert.assertTrue(results.contains("+ results for television"));
    }

    @Test
    /**
     * TEST003
     * Desc: Verify that going to Advanced search from Results page, has search term prefilled
     * Steps:
     *  1. Open ebay.com
     *  2. Enter 'televisions' in search bar, and click search
     *  3. Click on the 'Advanced' link
     * Expected: Advanced Search page open, and 'televisions' is prefilled as the search terms
     */
    void TEST003_VerifyAdvancedSearchLinkFromResultsPage() {
        driver.get(ebayHomePage);
        driver.findElement(By.xpath(obj.getProperty("SearchBar"))).sendKeys("television");
        driver.findElement(By.xpath(obj.getProperty("SearchBtn"))).click();
        driver.findElement((By.xpath(obj.getProperty("Advanced")))).click();
        String title = driver.findElement(By.xpath(obj.getProperty("AdvancedTitle"))).getText();
        assertEquals(title, "Advanced Search");
        String keyword = driver.findElement(By.xpath(obj.getProperty("Keyword"))).getAttribute("value");
        assertEquals(keyword, "television");
    }

    @Test
    /**
     * TEST004
     * Desc: Verify the user has an empty cart
     * Steps:
     *  1. Open cart.ebay.com
     * Expected: Text appears stating 'You don't have any items in your cart.'
     */
    void TEST004_VerifyEmptyCart() {
        driver.get(ebayCart);
        String title = driver.findElement(By.xpath(obj.getProperty("CartTitle"))).getText();
        assertEquals(title, "Shopping cart");
        String message = driver.findElement(By.xpath(obj.getProperty("EmptyCartMessage"))).getText();
        Assert.assertTrue(message.contains("You don't have any items in your cart."));
    }

    @Test
    /**
     * TEST005
     * Desc: Verify the 'Help & Contact' link on the header opens the Customer Service page
     * Steps:
     *  1. Open ebay.com
     *  2. Click 'Help & Contact' link
     * Expected: Customer Service page to open
     */
    void TEST005_VerifyHelpAndContactLink() {
        driver.get(ebayHomePage);
        driver.findElement(By.xpath(obj.getProperty("Help"))).click();
        String title = driver.findElement(By.xpath(obj.getProperty("CSTitle"))).getText();
        assertEquals(title, "Customer Service");
    }

}
