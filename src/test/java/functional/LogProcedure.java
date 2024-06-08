package functional;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PushesFiles;
import io.appium.java_client.android.AndroidDriver;

public class LogProcedure {

	AppiumDriver driver;

	@BeforeTest
	public void Setup() {

		DesiredCapabilities cap = new DesiredCapabilities();

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
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void Login() {
//	click the forward button for the Homescreen display.
		driver.findElement(By.xpath("//android.widget.Button")).click();
		driver.findElement(By.xpath("//android.widget.Button")).click();
//	Enter Login details and click enter.
		WebElement emailField = driver.findElements(By.xpath("//android.widget.ImageView")).get(0);
		emailField.click();
		emailField.sendKeys("Ohlufehmii@gmail.com");

		WebElement passwordField = driver.findElements(By.xpath("//android.widget.ImageView")).get(1);
		passwordField.click();
		passwordField.sendKeys("Hbon@1234");

		driver.findElement(AppiumBy.accessibilityId("Login")).click();

	}
	@Test(priority=1)
	public void logProcedure() throws InterruptedException, IOException {
		driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
		driver.findElement(AppiumBy.accessibilityId("Log Procedure")).click();
		
//		Fill the procedure form.
		
//	Procedure Title
	WebElement title=	driver.findElements(By.className("android.widget.ImageView")).get(0);
	title.click();
	title.sendKeys("Appendicitis");
	
//	Procedure description
	WebElement desc=	driver.findElements(By.className("android.widget.EditText")).get(1);
	desc.click();
	desc.sendKeys("Taking out the painful appendices");
	
//	Health Provider
	WebElement Provider=	driver.findElements(By.className("android.widget.EditText")).get(3);
	Provider.click();
	Provider.sendKeys("Rev, Fr. Burke Memorial Clinic");
	
//	Procedure type
	driver.findElements(By.className("android.widget.ImageView")).get(1).click();
	driver.findElement(AppiumBy.accessibilityId("Procedure 1")).click();
	
//	Enter Procedure preparation
	
//	Pre-procedure preparation.
	WebElement ProcedurePrep=	driver.findElements(By.className("android.widget.EditText")).get(5);
	ProcedurePrep.click();
	ProcedurePrep.sendKeys("Several scan was done, and a several doctor appointment was scheduled.");
	
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement)ProcedurePrep ).getId(), "endX", 681, "endY", 950));
	
//	Post-procedure care.
	WebElement care=driver.findElements(By.className("android.widget.EditText")).get(2);
	care.click();
	care.sendKeys("Series of antibiotics was administered, and also frequent dressing.");
	
	
//	Upload file 
	
    driver.findElement(AppiumBy.accessibilityId("Select File")).click();
    By permissionButton = By.id("com.android.permissioncontroller:id/permission_allow_button");
	driver.findElement(permissionButton).click();

    Thread.sleep(2000); 
    driver.findElement(AppiumBy.id("com.google.android.documentsui:id/icon_thumb")).click();	
    
//	Verify file uploaded successfully
//    String Toast=driver.findElement(AppiumBy.accessibilityId("(Success Procedure File uploaded successfully")).getAttribute("Success "
//    		+ "Procedure File uploaded successfully");
    
    Thread.sleep(7000); 

//	select date
	driver.findElements(By.className("android.widget.ImageView")).get(2).click();
	driver.findElement(AppiumBy.accessibilityId("5, Wednesday, June 5, 2024")).click();
	driver.findElement(AppiumBy.accessibilityId("OK")).click();

//	Set time.
	driver.findElements(By.className("android.widget.ImageView")).get(4).click();
	WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
	WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
	((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
			ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
	driver.findElement(AppiumBy.accessibilityId("OK")).click();
	
//	Click the log procedure button
	driver.findElement(AppiumBy.accessibilityId("Log Procedure")).click();
    
	}
	
	@Test(priority = 2)
	public void ValidateProcedureLog() {
//	 A checkmark icon is displayed.
		boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successIcon);

	}

	@AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
	}
}
