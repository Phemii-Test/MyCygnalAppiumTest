package functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class SleepTracker {

	AppiumDriver driver;
	String initialAverageSleep;
	String finalAverageSleep;

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
//	click the forward button for the homescreen display.
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
	
	@Test(priority= 1)
	public void ValidateInitialAverageSleepValue() {
//		click on the sleep graph from the home screen
		WebElement HRIcon = driver.findElement(AppiumBy.accessibilityId("Heart Rate icon"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) HRIcon).getId(),
			    "endX", 143,
			    "endY", 1648
			));
		driver.findElements(By.className("android.widget.ImageView")).get(7).click();
		
//		Get Initial average blood pressure value.
		WebElement averageSleep = driver.findElements(By.className("android.view.View")).get(3);
		String InititialAverageSleep = averageSleep.getText();
		 double initialAverageSleep = Double.parseDouble(InititialAverageSleep.replace("[^\\d.]+", ""));
		 System.out.println("Initial Average Sleep: " + initialAverageSleep);
	}
	@Test (priority= 2)
	public void LogSleep() {
		driver.findElement(AppiumBy.accessibilityId("Add new sleep record")).click();
		
//	Set from time.
		driver.findElements(By.className("android.widget.ImageView")).get(0).click();
		WebElement clockField= driver.findElement(By.xpath("//android.view.View"));
		
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) clockField).getId(),
			    "endX", 669,
			    "endY", 1322
			));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) clockField).getId(),
			    "endX", 669,
			    "endY", 1322
			));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
//		Set to time
		driver.findElements(By.className("android.widget.ImageView")).get(3).click();
		
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) clockField).getId(),
			    "endX", 824,
			    "endY", 1877
			));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) clockField).getId(),
			    "endX", 669,
			    "endY", 1322
			));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
//		Select sleep quality
		WebElement Frequency = driver.findElement(AppiumBy.accessibilityId("0%"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Frequency).getId(),
			    "endX", 435,
			    "endY", 1070
			));
		
//		Select the yes I'm on medication option
		
		driver.findElement(AppiumBy.accessibilityId("Yes, I'm on medication")).click();
		
//		Click the record sleep button
		driver.findElements(By.className("android.widget.Button")).get(1).click();
	}
@Test (priority = 3)
public void VerifySleepLog() {
//	 A checkmark icon is displayed.
		boolean successIcon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(successIcon);
// Validate that the add related medication is displayed since I specified I'm on medication
		boolean RecordMedBtn = driver.findElement(AppiumBy.accessibilityId("Record Related Medication")).isDisplayed();
		Assert.assertTrue(RecordMedBtn);
		
//		Click view sleep tracker button
		driver.findElement(AppiumBy.accessibilityId("View Sleep Tracker")).click();
	
}
@Ignore
@Test(priority = 4)
public void ValidateAverageSleepTrackerFunctionality() {
	WebElement averageSleep = driver.findElements(By.className("android.view.View")).get(3);
	String updatedAverageText = averageSleep.getText();
    double finalAverageSleep = Double.parseDouble(updatedAverageText.replace("[^\\d.]+", ""));
    System.out.println("Updated Average Blood Pressure: " + finalAverageSleep);

    // Verify that the average has been updated
    if (initialAverageSleep > finalAverageSleep) {
        System.out.println("Test Passed: Average sleep stat has been updated.");
    } else {
        System.out.println("Test Failed: Average sleep stat has not been updated.");
    }
   
}
}
