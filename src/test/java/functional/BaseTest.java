package functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

AppiumDriver driver;
	
	@BeforeTest
	public void Setup() {
		
		DesiredCapabilities cap=new DesiredCapabilities();
		
		cap.setCapability("deviceName", "VGP Phone1");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "12");
		cap.setCapability("udid", "emulator-5554"); 
		cap.setCapability("appPackage", "com.mycygnal.mycygnal");
		cap.setCapability("appActivity", "com.mycygnal.mycygnal.MainActivity");
		cap.setCapability("automationName", "appium");
		
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
	}
	@Test(priority=0)
	public void Login() {
//		click the forward button for the omescreen display.
		driver.findElement(By.xpath("//android.widget.Button")).click();
		driver.findElement(By.xpath("//android.widget.Button")).click();
//		Enter Login details and click enter.
		WebElement emailField = driver.findElements(By.xpath("//android.widget.ImageView")).get(0);
		emailField.click();
		emailField.sendKeys("Ohlufehmii@gmail.com");
		
		WebElement passwordField = driver.findElements(By.xpath("//android.widget.ImageView")).get(1);
		passwordField.click();
		passwordField.sendKeys("Hbon@1234");
		
		driver.findElement(AppiumBy.accessibilityId("Login")).click();
	
	}
	


}
