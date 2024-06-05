package functional;

import static org.testng.Assert.assertEquals;

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
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class AddMedication {

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
		emailField.sendKeys("ohlufehmii@gmail.com");
		
		WebElement passwordField = driver.findElements(By.xpath("//android.widget.ImageView")).get(1);
		passwordField.click();
		passwordField.sendKeys("Hbon@1234");
		
		driver.findElement(AppiumBy.accessibilityId("Login")).click();
	
	}
	@Test(priority=1)
	public void addMedication() {
		driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
		driver.findElement(AppiumBy.accessibilityId("Add Medications")).click();
		
//		Fill med form
		
		WebElement addMedName=driver.findElements(By.xpath("//android.widget.ImageView")).get(0);
		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) addMedName).getId()
			));
		addMedName.sendKeys("Lesinopril");
		
		WebElement Dosage=driver.findElements(By.className("android.widget.EditText")).get(1);
		((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Dosage).getId()
			));
		Dosage.sendKeys("2");
		
		WebElement Frequency = driver.findElement(AppiumBy.accessibilityId("0%"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Frequency).getId(),
			    "endX", 435,
			    "endY", 1070
			));
//		Select Category
		driver.findElement(AppiumBy.accessibilityId("Antipertensives")).click();
		
//	    select start date
		driver.findElements(By.xpath("//android.widget.ImageView")).get(1).click();
		driver.findElement(AppiumBy.accessibilityId("4, Tuesday, June 4, 2024")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		select end date
		driver.findElements(By.xpath("//android.widget.ImageView")).get(3).click();
		driver.findElement(AppiumBy.accessibilityId("22, Saturday, June 22, 2024")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

		
//		click the add medication button
		driver.findElement(AppiumBy.accessibilityId("Add Medication")).click();
		
//		Verify that medication has been added.
		boolean icon = driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
		Assert.assertTrue(icon);
		
//		String ActualsuccessTxt= driver.findElement(AppiumBy.accessibilityId("You have added a new medication")).getText();
//		String ExpectedSuccessTxt= "You have added a new medication";
//		Assert.assertEquals(ActualsuccessTxt, ExpectedSuccessTxt);
		
		}
	@Test(priority=2)
	public void SetMedReminder() {
		driver.findElement(AppiumBy.accessibilityId("View Medication")).click();
		
//		Add first dose
		
		
		driver.findElements(By.className("android.widget.ImageView")).get(0).click();
		WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) StartMinute).getId(),
			    "endX", 372,
			    "endY", 1625
			));
		WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Startseconds).getId(),
			    "endX", 675,
			    "endY", 1310
			));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
		driver.findElements(By.className("android.widget.ImageView")).get(2).click();
		WebElement EndMinute = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) EndMinute).getId(),
			    "endX", 372,
			    "endY", 1625
			));
		WebElement Endseconds = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Endseconds).getId(),
			    "endX", 944,
			    "endY", 1499
			));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
		
//		Add second dose
		driver.findElements(By.className("android.widget.ImageView")).get(4).click();
		WebElement StartMinute2 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) StartMinute2).getId(),
			    "endX", 664,
			    "endY", 1322
			));
		WebElement Startseconds2 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Startseconds2).getId(),
			    "endX", 675,
			    "endY", 1310
			));
		driver.findElement(AppiumBy.accessibilityId("PM")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
		driver.findElements(By.className("android.widget.ImageView")).get(6).click();
		WebElement EndMinute2 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) EndMinute2).getId(),
			    "endX", 664,
			    "endY", 1322
			));
		WebElement Endseconds2 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Endseconds2).getId(),
			    "endX", 944,
			    "endY", 1499
			));
		driver.findElement(AppiumBy.accessibilityId("PM")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
//		Add third dose
		driver.findElements(By.className("android.widget.ImageView")).get(8).click();
		WebElement StartMinute3 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) StartMinute3).getId(),
			    "endX", 973,
			    "endY", 1630
			));
		WebElement Startseconds3 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Startseconds3).getId(),
			    "endX", 675,
			    "endY", 1310
			));
		driver.findElement(AppiumBy.accessibilityId("PM")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
		driver.findElements(By.className("android.widget.ImageView")).get(10).click();
		WebElement EndMinute3 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) EndMinute3).getId(),
			    "endX", 973,
			    "endY", 1630
			));
		WebElement Endseconds3 = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Endseconds3).getId(),
			    "endX", 944,
			    "endY", 1499
			));
		driver.findElement(AppiumBy.accessibilityId("PM")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();
		
//		Toggle alarm
		WebElement Alarm = driver.findElement(AppiumBy.accessibilityId("Toggle Alarm On"));
		Alarm.click();
		
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) Alarm).getId(),
			    "endX", 1179,
			    "endY", 400
			));
//		Click set reminder button
		driver.findElement(AppiumBy.accessibilityId("Set Reminder")).click();

	}
@Test(priority=3)
public void ValidateReminderSet() {
	boolean successCheckMark= driver.findElement(AppiumBy.accessibilityId("Success Icon")).isDisplayed();
	Assert.assertTrue(successCheckMark);
	
}
	}

