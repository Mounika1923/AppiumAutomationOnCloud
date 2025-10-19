package androidPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class SignUpPage extends AndroidActions {
	
	AndroidDriver driver;
	public SignUpPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
	}
	@AndroidFindBy(id="android:id/text1")
	private WebElement countryDropdown;
    
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleButton;
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement letsShopButton;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Argentina']")
	private WebElement countryName;
	
	@AndroidFindBy(xpath="//android.widget.Toast[@text='Please enter your name']")
	private WebElement toastmsg;
	
	public void setCountryDropdown(String countryName) {
		countryDropdown.click();
		scrollToElement(countryName);
		 String uiAutomatorText = String.format(
			        "new UiScrollable(new UiSelector()).scrollIntoView(text(\"%s\"))", 
			        countryName
			    );
			    driver.findElement(AppiumBy.androidUIAutomator(uiAutomatorText)).click();
		;
}
	
   public void setNameField(String name)
   {
	   nameField.sendKeys(name);
	   driver.hideKeyboard();
   }
   
   
   public void selectGender(String gender)
   {
	   if(gender.equalsIgnoreCase("female"))
	   {
		   femaleButton.click();
	   }
	   else maleButton.click();
   }
   
   public ProductCataloguePage clickOnLetsShopButton() {
	   letsShopButton.click();
	  return  new ProductCataloguePage(driver);
   }
   
   public String validateToastMessages()
   {
       return getTextOfToastMsg(toastmsg);

   }
	
}
