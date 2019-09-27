package com.edureka.devops.selenium;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hello world!
 *
 */
public class App {
	
	private static ChromeOptions chromeOptions;
	
	private static WebDriver driver;
	
	private static final String PART_ABOUT_US_PAGE_CONTENT="This is <b>about</b> page.";
	
	private static final String ABOUT_US_PAGE_CONTEXT = "content/about-us.php";
	
	private static final String HOME_PAGE_TITLE="Simple PHP Website";
	
	private static final String ABOUT_US_LINK_NAME = "About Us";
			
    private static  void _setup(String chromeDriverPath, String chromiumBrowserPath, String applicationUrl) throws InterruptedException {
       
    	System.getProperties().setProperty("webdriver.chrome.driver", chromeDriverPath);
        chromeOptions = new ChromeOptions();
        chromeOptions.setBinary(chromiumBrowserPath);
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        driver = new ChromeDriver(chromeOptions);
        driver.get(applicationUrl);
        Thread.sleep(2000);
    }
	
	public static void main(String[] args) throws IOException, InterruptedException {
		
		_setup(args[0], args[1], args[2]);
		assertTrue(driver.getPageSource().contains(HOME_PAGE_TITLE));
	        
        driver.findElement(By.linkText(ABOUT_US_LINK_NAME)).click();
        Thread.sleep(2000);
        
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(ABOUT_US_PAGE_CONTEXT));
        
        assertTrue(driver.getPageSource().contains(PART_ABOUT_US_PAGE_CONTENT));

	}
	
	@After
    public void tearDown() {
        driver.quit();
    }
	
}
