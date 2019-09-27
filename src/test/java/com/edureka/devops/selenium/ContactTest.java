package com.edureka.devops.selenium;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ContactTest {

	private ChromeOptions chromeOptions;
	
	private WebDriver driver;
	
	private final String PART_ABOUT_US_PAGE_CONTENT="This is <b>about</b> page.";
	
	private final String ABOUT_US_PAGE_CONTEXT = "content/about-us.php";
	
	private final String HOME_PAGE_TITLE="Simple PHP Website";
	
	private final String ABOUT_US_LINK_NAME = "About Us";
			
	@Before
    public void setup() throws InterruptedException {
        System.getProperties().setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("/usr/bin/chromium-browser");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        driver = new ChromeDriver(chromeOptions);
        driver.get("http://localhost:8889/");
        Thread.sleep(2000);
    }
	
	@After
    public void tearDown() {
        driver.quit();
    }
	
	@Test
    public void testBrowser() throws IOException, InterruptedException{
		    
        assertTrue(driver.getPageSource().contains(HOME_PAGE_TITLE));
        
        driver.findElement(By.linkText(ABOUT_US_LINK_NAME)).click();
        Thread.sleep(2000);
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(ABOUT_US_PAGE_CONTEXT));
        
        assertTrue(driver.getPageSource().contains(PART_ABOUT_US_PAGE_CONTENT));         
    }
}
