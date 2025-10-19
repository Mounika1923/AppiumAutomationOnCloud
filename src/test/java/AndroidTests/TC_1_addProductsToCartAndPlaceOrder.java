package AndroidTests;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import androidPages.CartPage;
import androidPages.ProductCataloguePage;
import androidPages.SignUpPage;
import utils.AndroidBaseTest;


public class TC_1_addProductsToCartAndPlaceOrder extends AndroidBaseTest {
	

	@Test(dataProvider ="getData", groups ={"endToEnd"})
	public void validationOfCartJourney(String countryName, String username, String gender) throws InterruptedException {
		
		
		//signUpPage
		signUpPage.setCountryDropdown(countryName);
		signUpPage.setNameField(username);
		signUpPage.selectGender(gender);
//		ProductCataloguePage productsPage =signUpPage.clickOnLetsShopButton();
//	
//		//productsCatalogPage
//		productsPage.addProductsToCart("Air Jordan 4 Retro");
//		productsPage.addProductsToCart("Jordan 6 Rings");
//		CartPage cart =productsPage.goToCartPage();
//        cart.validationOfTitleAndTermsAndContions("Cart");
//         cart.clickSubscriptionCheckboxAndProceed();

	}
	

	
	@BeforeMethod(alwaysRun = true, groups ={"endToEnd"})
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
	
	@DataProvider()
	
	public Object[][] getData(){
		return new Object[][] {{"Argentina","Mounika","female"},{"Australia","Vamsi","male"},{"Angola","Hanvamma","female"}};
	}
	
}
