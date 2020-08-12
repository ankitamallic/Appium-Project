package activity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;

public class Chrome1 {
	WebDriverWait wait;
	WebDriver driver = null;
  
    @BeforeTest
    public void setup() throws MalformedURLException {

        // Set the Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceId", "ZY2242RDN8");
        caps.setCapability("deviceName", "Moto G5 Plus");
        caps.setCapability("platformName", "android");
		caps.setCapability("appPackage", "com.android.chrome");
		caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
		 
       // caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        caps.setCapability("noReset", true);

        // Instantiate Appium Driver
        driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void chromeActivity()  {
        
    	driver.get("https://www.training-support.net/selenium");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 	
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")));
    	driver.findElement(MobileBy.xpath("//android.widget.Button[@text='Get Started!']")).click();
    	
    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 	
    	driver.findElement(MobileBy.AndroidUIAutomator("UiScrollable(UiSelector().scrollable(true).instance(0)).scrollForward(2).scrollIntoView(textStartsWith(\"To-Do List\"))")).click();
    	
    	
    	 driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Add tasks to list");
    	 driver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
    	 driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Get number of tasks");
    	 driver.findElement(MobileBy.xpath("//android.widget.Button[@text=\"Add Task\"]")).click();
    	 driver.findElement(MobileBy.xpath("//android.widget.EditText[@resource-id=\"taskInput\"]")).sendKeys("Clear the list");
		    

    	 driver.findElement(MobileBy.xpath("//android.view.View[1]")).click();
    	 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	 driver.findElement(MobileBy.xpath("//android.view.View/android.view.View[4]/android.view.View[2]/android.view.View[2]")).click();
    	 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	 driver.findElement(MobileBy.xpath("//android.view.View/android.view.View[4]/android.view.View[2]/android.view.View[3]")).click();
    	 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    	 driver.findElement(MobileBy.xpath("//android.widget.FrameLayout[2]/android.webkit.WebView/android.view.View/android.view.View[4]/android.view.View[3]")).click();
    	 
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    
}