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
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import androidPages.CartPage;
import androidPages.ProductCataloguePage;
import androidPages.SignUpPage;
import utils.AndroidBaseTest;


public class TC_2_validateTotalSumOfProdcutsInCart extends AndroidBaseTest {
	

	@Test(priority=0 , enabled =true)
	public void validateTheTotalSumOfProductsIinCcart() throws InterruptedException {
		
		
		//signUpPage
		signUpPage.setCountryDropdown("Argentina");
		signUpPage.setNameField("Mounika");
		signUpPage.selectGender("female");
		ProductCataloguePage productsPage =signUpPage.clickOnLetsShopButton();
		
		
		//productsCatalogPage
		productsPage.addProductsToCart("Air Jordan 4 Retro");
		productsPage.addProductsToCart("Jordan 6 Rings");
		CartPage cart =productsPage.goToCartPage();
		cart.calculateTheSumOfProductsInCart();
		cart.compareProductsSumWithFinalCartValue();
   


	}
	
	
	
}
