package activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleKeepReminder {
	
	AppiumDriver<MobileElement> driver;
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() throws MalformedURLException {
		// Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "ZY2242RDN8");
        caps.setCapability("deviceName", "Moto G5 Plus");
        caps.setCapability("platformName", "android");
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity",  ".activities.BrowseActivity");
        caps.setCapability("noReset", true);
        
        
     // Initialize driver
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
       
        
        System.out.println("Google Keep is open");
	}
	
	@Test
	public void GoogleKeepNotes() throws InterruptedException{
		 wait = new WebDriverWait(driver, 10);
		 
		//New Text Note
		 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	        driver.findElementById("com.google.android.keep:id/editable_title").sendKeys("Add title");
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	        driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys("Add Description");
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	        
	        //Reminder click
	        driver.findElementById("com.google.android.keep:id/menu_switch_to_list_view").click();
	     
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	        MobileElement dropdown = driver.findElementByXPath("//android.widget.LinearLayout[@content-desc=\"Time - Currently selected - 7:00 PM\"]/android.widget.Spinner");
	        dropdown.click();
	        
	        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	        MobileElement afternoon = driver.findElementByXPath("//android.widget.TextView[1][@text ='Evening']");
	        afternoon.click();
	        
	        driver.findElementById("com.google.android.keep:id/save").click();
	        driver.findElementByAccessibilityId("Open navigation drawer").click();

	        String confirmdescription = driver.findElementByXPath("//android.widget.TextView[@text='Today, 6:00 PM']").getText();
	        Assert.assertEquals(confirmdescription, "Today, 6:00 PM");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	

}