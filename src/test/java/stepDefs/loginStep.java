package stepDefs;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginStep {
	AppiumDriver driver;
	
	@Before
	public void Setup() {
		
		DesiredCapabilities cap=new DesiredCapabilities();
		
		cap.setCapability("deviceName", "Fleap Phone");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "12");
		cap.setCapability("udid", "emulator-5554"); 
		cap.setCapability("appPackage", "com.example.phenome");
		cap.setCapability("appActivity", "com.example.phenome.MainActivity");
		cap.setCapability("automationName", "appium");
		
		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);		
	}
	
	@Given("I enter my valid email address")
	public void i_enter_my_valid_email_address() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.ImageView[2]")).sendKeys("ohlufehmii@gmail.com");
	}

	@Given("a valid password")
	public void a_valid_password() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(By.xpath("//android.widget.ScrollView/android.widget.ImageView[3]")).sendKeys("Hbon@1234");
	}

	@When("I click on the login button")
	public void i_click_on_the_login_button() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(AppiumBy.accessibilityId("loginBtn")).click();
	}

	@Then("I should see my dashboard page.")
	public void i_should_see_my_dashboard_page() {
	    // Write code here that turns the phrase above into concrete actions
		driver.findElement(AppiumBy.accessibilityId("loginBtn")).click();
	}

}
