package recipeTest.RecipeContents;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	IOSDriver driver;
	@BeforeTest
	public void setup() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("/usr/local/bin/node /usr/local/bin/appium");
		Thread.sleep(5000); 
		System.out.println("Appium server started"); 

		DesiredCapabilities cap = new DesiredCapabilities();		
		File app = new File("Recipes.app");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.1");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 6");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testTitle() {
		driver.findElementByName("Chocolate Cake").click();
		WebElement elem =  driver.findElementByXPath("//UIAApplication[1]/UIAWindow[2]/UIANavigationBar[1]/UIAStaticText[1]");
		Assert.assertEquals(elem.getAttribute("value"), "Chocolate Cake");
	}

	@AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    
		String[] command ={"/usr/bin/killall","-KILL","node"}; 
		Runtime.getRuntime().exec(command); 
		System.out.println("Appium server stop"); 

    }

}

