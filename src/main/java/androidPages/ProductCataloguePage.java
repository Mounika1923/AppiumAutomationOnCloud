package androidPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ProductCataloguePage extends AndroidActions {
	
	AndroidDriver driver;
	public ProductCataloguePage(AndroidDriver driver)
	{
		super(driver);
		this.driver =driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart")
	private WebElement addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cartButton;
	
	
	public  void addProductsToCart(String productname) {
		scrollToElement(productname);
		addToCart.click();
	}
	 
	public CartPage goToCartPage()
	{
		cartButton.click();
		return new CartPage(driver);
		
	}
	

}
