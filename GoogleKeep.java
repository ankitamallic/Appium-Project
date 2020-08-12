package activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GoogleKeep {
	
	WebDriverWait wait;
    AppiumDriver<MobileElement> driver = null;
  
    @BeforeMethod
    public void setup() throws MalformedURLException {

        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "ZY2242RDN8");
        caps.setCapability("deviceName", "Moto G5 Plus");
        caps.setCapability("platformName", "android");
        caps.setCapability("appPackage", "com.google.android.keep");
        caps.setCapability("appActivity",  ".activities.BrowseActivity");
        caps.setCapability("noReset", true);

     // Instantiate Appium Driver
        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver<MobileElement>(appServer, caps);
            
            System.out.println("Google keep is open");
            
    }
    
    @Test
    public  void keepActivity() throws InterruptedException {
    	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	
        driver.findElementById("com.google.android.keep:id/new_note_button").click();
      
    	// Add note and description
        driver.findElementById("com.google.android.keep:id/editable_title").sendKeys("Add new title");
        
        driver.findElementById("com.google.android.keep:id/edit_note_text").sendKeys("Add Description");
        
        
        //press the back button
        driver.findElementByXPath("//android.widget.ImageButton[@content-desc='Open navigation drawer']").click();
        
        
        
        //using assert
        Thread.sleep(3000);
        String titleVerify = driver.findElementByXPath("//android.widget.TextView[@text='Add new title']").getText();
        Assert.assertEquals(titleVerify, "Add new title");
        
        String descriptionVerify = driver.findElementByXPath("//android.widget.TextView[@text='Add Description']").getText();
        Assert.assertEquals(descriptionVerify, "Add Description"); 
        
        System.out.println("Complete Test Case");
        
     }

    @AfterMethod
    public void destroy() {
    	System.out.println("close driver");
        driver.quit();
    }
}