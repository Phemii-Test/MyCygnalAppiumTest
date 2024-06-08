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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.PressesKey;

public class LogSymptoms {

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
//	click the forward button for the omescreen display.
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

	@Test(priority = 1)
	public void logSymptom() {
		driver.findElement(AppiumBy.accessibilityId("Select Actions Button")).click();
		driver.findElement(AppiumBy.accessibilityId("Log Symptoms")).click();

//		Fill the symptoms form.

//		Syptoms title
		WebElement title = driver.findElements(By.xpath("//android.widget.ImageView")).get(0);
		title.click();
		title.sendKeys("Headache");

//		Scroll to set severity
		WebElement Frequency = driver.findElement(AppiumBy.accessibilityId("0%"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Frequency).getId(), "endX", 435, "endY", 1070));
//		Symptoms description
		WebElement desc = driver.findElements(By.className("android.widget.EditText")).get(1);
		desc.click();
		desc.sendKeys("I feel slight headache at my forehead.");

//		Set frequency
		driver.findElement(AppiumBy.accessibilityId("Select Frequency")).click();
		driver.findElement(AppiumBy.accessibilityId("Daily")).click();

//		Set the trigger.
		WebElement Triggers = driver.findElements(By.className("android.widget.EditText")).get(3);
		Triggers.click();
		Triggers.sendKeys("Stress");
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		((HidesKeyboard) driver).hideKeyboard();

//		State the alleviating factors.
		WebElement alivFactors = driver.findElements(By.className("android.widget.EditText")).get(4);
		alivFactors.click();
		alivFactors.sendKeys("Analgesics");
		((PressesKey) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		((HidesKeyboard) driver).hideKeyboard();


//		select end date
		driver.findElements(By.xpath("//android.widget.ImageView")).get(3).click();
		driver.findElement(AppiumBy.accessibilityId("5, Wednesday, June 5, 2024")).click();
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		Set time.
		driver.findElements(By.xpath("//android.widget.ImageView")).get(5).click();
		WebElement StartMinute = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) StartMinute).getId(), "endX", 372, "endY", 1625));
		WebElement Startseconds = driver.findElement(By.xpath("//android.view.View"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) Startseconds).getId(), "endX", 675, "endY", 1310));
		driver.findElement(AppiumBy.accessibilityId("OK")).click();

//		Click the log symptoms button
		driver.findElement(AppiumBy.accessibilityId("Log Symptoms")).click();
	}

	@Test(priority = 2)
	public void ValidateSymptomsLog() {
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
