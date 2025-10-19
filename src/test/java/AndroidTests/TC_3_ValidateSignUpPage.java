package AndroidTests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import utils.AndroidBaseTest;

public class TC_3_ValidateSignUpPage extends AndroidBaseTest{
	
	@BeforeMethod(alwaysRun = true)
	public void resetToHomepage() {
	    String appPackage = "com.androidsample.generalstore";
	    
	    // Close and reopen the app
	    driver.terminateApp(appPackage);
	    driver.activateApp(appPackage);
	    
	    // Wait for app to be ready
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    try {
	        Thread.sleep(3000); // Wait for splash screen
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt();
	    }
	}

	
	@Test(groups= {"Smoke"})
	public void fillForm() {
		
		signUpPage.setCountryDropdown("Angola");
		signUpPage.setNameField("Mounika");
		signUpPage.selectGender("female");
		signUpPage.selectGender("male");
        signUpPage.clickOnLetsShopButton();
		
		
	}
	
	@Test(groups= {"Smoke"})
	public void validateErrorMessage() {
		
		signUpPage.setCountryDropdown("Angola");
		//signUpPage.setNameField("Mounika");
		signUpPage.selectGender("female");
		signUpPage.selectGender("male");
        signUpPage.clickOnLetsShopButton();
        String toastmsg=  signUpPage.validateToastMessages();
        Assert.assertEquals(toastmsg, "please enter");
		
	}	
	
	

}
