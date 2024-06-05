package functional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
import com.mailslurp.apis.EmailControllerApi;
import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.clients.auth.HttpBasicAuth;
import com.mailslurp.models.BasicAuthOptions;
import com.mailslurp.models.Email;
import com.mailslurp.models.EmailPreview;
import com.mailslurp.models.InboxDto;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobilePlatform;

public class Onboarding {

	AppiumDriver driver;
	InboxDto inbox;
    String otp;
	

	@BeforeTest
	public void Setup() throws ApiException {
		
		ApiClient defaultClient = Configuration.getDefaultApiClient();
		defaultClient.setApiKey("238dbc0bcd01f3c8b7d4cd42b91b7a80b8af2cf4e5fd7487c311f089562dc43a");
		InboxControllerApi inboxControllerApi = new InboxControllerApi(defaultClient);
		
		 inbox = inboxControllerApi.createInboxWithDefaults();
         assertNotNull(inbox, "Inbox creation failed. Inbox is null.");
         assertEquals(inbox.getEmailAddress().contains("@mailslurp"), true, "Email address is not valid.");
		
		
		DesiredCapabilities cap = new DesiredCapabilities();

		cap.setCapability("deviceName", "Fleap Phone");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "12");
		cap.setCapability("app", "C:\\Users\\olufemi.habib\\Downloads\\app-release (12).apk");
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
	public void clickForwardButton() {
		driver.findElement(AppiumBy.xpath("//android.widget.Button")).click();
		driver.findElement(AppiumBy.className("android.widget.Button")).click();
	}

	@Test(priority = 1)
	public void signUpForm() {
		// Click the signup button
		driver.findElement(AppiumBy.accessibilityId("Sign Up")).click();

		// Fill first name
		WebElement firstNameField = driver.findElement(By.xpath("//android.widget.ImageView"));
		firstNameField.click();
		firstNameField.sendKeys("Olufemi");
		
		// Fill last name
		WebElement lastName = driver.findElements(By.xpath("//android.widget.ImageView")).get(1);
		lastName.click();
		lastName.sendKeys("Omeiza");
		
		// Fill email

		WebElement email = driver.findElements(By.xpath("//android.widget.ImageView")).get(2);
		email.click();
		email.sendKeys(inbox.getEmailAddress());
		
//		fill Mobile number
		WebElement MobileNum = driver.findElements(By.xpath("//android.widget.ImageView")).get(3);
		MobileNum.click();
		MobileNum.sendKeys("+13473826505");
		
//		fill Password
		WebElement password = driver.findElements(By.xpath("//android.widget.ImageView")).get(4);
		password.click();
		password.sendKeys("Hbon@1234");
		
		// Check the terms and condition box and click signup button
		driver.findElement(By.xpath("//android.widget.CheckBox")).click();
		driver.findElement(AppiumBy.accessibilityId("Sign Up")).click();
		
		
		
    }


	@Test(priority = 2)
	public void OTP() {
		
		
	       
//	        String emailBody = emails.get
		WebElement otpField = driver.findElement(AppiumBy.accessibilityId("otp_field"));
		
		// Verify that the email address entered is what's on the text for the OTP.
		WebElement otpText = driver.findElement(
				AppiumBy.accessibilityId("Enter code that we have sent to your emailoh.lufehmii@gmail.com"));
		String email = null;
		Assert.assertEquals(otpText.getText(), "Enter code that we have sent to your email" + email);
//        click the verify button
		driver.findElement(AppiumBy.accessibilityId("Verify")).click();
//        Validate that the verification was successful
		String actualTxt = driver
				.findElement(By.xpath("//android.view.View[@content-desc=\"We are excited to learn more about you\"]"))
				.getText();
		String ExpectedTxt = "We are excited to learn more about you";
		Assert.assertEquals(actualTxt, ExpectedTxt);

	}

	@Test(priority = 3)
	public void HealthAssessment() {
		WebElement contBtn = driver.findElement(AppiumBy.accessibilityId("Continue"));
//    	Click health assessment button
		driver.findElement(AppiumBy.accessibilityId("Go to Health Assessment")).click();
//    	Select male and hit continue
		driver.findElement(AppiumBy.accessibilityId("Male")).click();
		contBtn.click();
//    	Scroll to 18 years and click continue.
		WebElement ele = driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"18\"));"));
		ele.click();
		contBtn.click();

//    	scroll to 60 kg and click continue.
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"60\"));"));
		contBtn.click();

//    	scroll to 5 exercise then click continue button
		driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"5\"));"));
		contBtn.click();

//    	 select a sleep level, the click the continue button
		driver.findElement(AppiumBy.accessibilityId("Level 4 7-8 HR")).click();
		contBtn.click();

//    	 select alcohol intake to 3-4 times then click the continue button.
		WebElement alch = driver.findElement(AppiumBy.accessibilityId("0%"));
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) alch).getId(), "endX", 431, "endY", 1496));
		contBtn.click();
		
//		Select blood group as A+ and click the continue button.
		driver.findElement(AppiumBy.accessibilityId("A")).click();
		driver.findElement(AppiumBy.accessibilityId("+")).click();
		contBtn.click();

//		Add symptoms and click continue.
		WebElement sym= driver.findElement(By.xpath("//android.widget.EditText"));
		sym.sendKeys("Nusea");
		sym.click();
		contBtn.click();
		
//		Add Medication and click continue
		driver.findElement(AppiumBy.accessibilityId("Yes, I'm on medication")).click();
		contBtn.click();
		
//		Select two allergies and click continue
		driver.findElement(AppiumBy.accessibilityId("Wheat")).click();
		driver.findElement(AppiumBy.accessibilityId("Strawberries")).click();
		contBtn.click();
	}
}