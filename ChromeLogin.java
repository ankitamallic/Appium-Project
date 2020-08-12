package activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ChromeLogin {
WebDriverWait wait;
    
    AppiumDriver<MobileElement> driver = null;
		
    @BeforeTest
	
    public void setup() throws MalformedURLException {
	 		
        DesiredCapabilities caps = new DesiredCapabilities();	
        caps.setCapability("deviceId", "ZY2242RDN8");
        caps.setCapability("deviceName", "Moto G5 Plus");
        caps.setCapability("platformName", "android");
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity",  ".activities.BrowseActivity");
        caps.setCapability("noReset", true);
		        	
        driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);	
        wait = new WebDriverWait(driver, 10);
	
    }
		
    @Test
	
    public void ChromeLogging() {
	       
        driver.get("https://www.training-support.net/selenium");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")));
	    driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")).click();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    
	    driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollForward(2).scrollIntoView(textStartsWith(\"Login Form\"))")).click();	      	   	 
	   	
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   	
	    driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"username\"]")).sendKeys("admin");
    	driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"password\"]")).sendKeys("password");

    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	       
	      driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Log in']")).click();
	      	        
	      String correctLogin = driver.findElement(MobileBy.xpath("//android.view.View[@text='Welcome Back, admin']")).getText();
	      Assert.assertEquals(correctLogin, "Welcome Back, admin");
	        
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        
	        driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"username\"]")).clear();
	        driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"username\"]")).sendKeys("Admin");
	     
	        driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"password\"]")).clear();
	        driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"password\"]")).sendKeys("Password");
	        
	        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Log in']")).click();
	        
	        String incorrectLogin = driver.findElement(MobileBy.xpath("//android.view.View[@text='Invalid Credentials']")).getText();
	        Assert.assertEquals(incorrectLogin, "Invalid Credentials");	        
    }
      	
	       @AfterTest	
	public void closeDriver() {
	    	   driver.quit();
		
	}
	}