package activity;

import java.io.IOException;
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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleTask {
	
	WebDriverWait wait;
    AppiumDriver<MobileElement> driver = null;
  
    @BeforeTest
    public void setup() throws MalformedURLException {

        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "ZY2242RDN8");
        caps.setCapability("deviceName", "Moto G5 Plus");
        caps.setCapability("platformName", "android");
        caps.setCapability("appPackage", "com.google.android.apps.tasks");
        caps.setCapability("appActivity",  ".ui.TaskListsActivity");
        caps.setCapability("noReset", true);

     // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
            
            System.out.println("Google Task is open");
            
    }
    
    @Test
    public  void taskActivity() {
    	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	
    	// Add activities and click save button
        driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
        driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys("Complete Activity with Google Tasks");
        driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
        
        driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
        driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys("Complete Activity with Google Keep");
        driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
        
        driver.findElementById("com.google.android.apps.tasks:id/tasks_fab").click();
        driver.findElementById("com.google.android.apps.tasks:id/add_task_title").sendKeys("Complete the second Activity Google Keep");
        driver.findElementById("com.google.android.apps.tasks:id/add_task_done").click();
        
        // Use assert
        String task3 = driver.findElementByXPath("(//android.widget.FrameLayout[@content-desc=\"Complete the second Activity Google Keep\"])[1]/android.widget.LinearLayout/android.widget.TextView").getText();
        Assert.assertEquals(task3, "Complete the second Activity Google Keep");
        
        String task2 = driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Complete Activity with Google Keep\"]/android.widget.LinearLayout/android.widget.TextView").getText();
        Assert.assertEquals(task2, "Complete Activity with Google Keep");
        
        String task1 = driver.findElementByXPath("//android.widget.FrameLayout[@content-desc=\"Complete Activity with Google Tasks\"]/android.widget.LinearLayout/android.widget.TextView").getText();
        Assert.assertEquals(task1, "Complete Activity with Google Tasks");
 

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}