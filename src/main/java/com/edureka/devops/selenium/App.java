package com.edureka.devops.selenium;

import java.io.IOException;

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
		        
		if(!driver.getPageSource().contains(HOME_PAGE_TITLE)) {
			System.out.println("Test failed");
        	System.exit(1);
		}
        
        driver.findElement(By.linkText(ABOUT_US_LINK_NAME)).click();
        Thread.sleep(2000);
        
        String currentUrl = driver.getCurrentUrl();
        if(!currentUrl.contains(ABOUT_US_PAGE_CONTEXT)) {
        	System.out.println("Test failed");
        	System.exit(1);
        }
        
        if(!driver.getPageSource().contains(PART_ABOUT_US_PAGE_CONTENT)) {
        	System.out.println("Test failed");
        	System.exit(1);
        }
        
        if(driver.getPageSource().contains(PART_ABOUT_US_PAGE_CONTENT)) {
        	System.out.println("Test passed successfully");
        	System.exit(0);
        }
        else {
        	System.out.println("Test failed");
        	System.exit(1);
        }
        	
        driver.quit();

	}
}
