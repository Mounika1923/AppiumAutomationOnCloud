package androidPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class CartPage extends AndroidActions {
	
	AndroidDriver driver;
	double totalSum=0;
	public CartPage(AndroidDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
	private WebElement toolBarTitle;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement termsAndConditions;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement closeTermsAndCondtions;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
	private WebElement subscriptionCheckBox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceedButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> listOfProducts;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement finalCartSum;
	
	public void validationOfTitleAndTermsAndContions(String title)
	{
		waitUntilExpectedTextLoactedOnElement(toolBarTitle, title,driver);
		longPressAction(termsAndConditions);
		closeTermsAndCondtions.click();
	}
	
	public void clickSubscriptionCheckboxAndProceed()
	{
		subscriptionCheckBox.click();
		proceedButton.click();
	}
	
	public void calculateTheSumOfProductsInCart() {
		
	
        System.out.println(listOfProducts.size());
        scrollToEnd();
        for(WebElement eachSum:listOfProducts)
        {
        	totalSum+=Double.parseDouble((eachSum.getAttribute("text")).replace("$","").trim());
        }
        System.out.println("total sum is"+totalSum);
	}
	
	
	public void compareProductsSumWithFinalCartValue() {
		
        double finalCartValue = Double.parseDouble((finalCartSum.getAttribute("text")).replace("$","").trim());
        System.out.println("final cart sum is"+finalCartValue);
        Assert.assertEquals(finalCartValue, totalSum);

	}
	
	
	

}
