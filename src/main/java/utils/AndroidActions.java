package utils;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumCommonUtils{
	public AndroidDriver driver;
	public AndroidActions(AndroidDriver driver) {
		this.driver=driver;
		 wait = new WebDriverWait(driver,Duration.ofSeconds(10000));	
	}
	
	public void scrollToElement(String elementText)
	{

		String uiautomatortext= String.format("new UiScrollable(new UiSelector()).scrollIntoView(textContains(\"%s\"))", elementText);
		driver.findElement(AppiumBy.androidUIAutomator(uiautomatortext));
	}
	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
				"elementId",((RemoteWebElement)ele).getId(),
				"Duration",2000));
	}
		
	
	public void scrollToEnd()
	{
		boolean canScrollMore ;
		do {
			canScrollMore=(boolean) driver.executeScript("mobile: scrollGesture", ImmutableMap.of(
				"left",100,"top",100,"width",200,"height",200,"direction","down",
				"percent",3.0));
		}while(canScrollMore);
	}
	
	
	public void scrollToTop()
	{
		boolean canScrollMore;
		do {
	        canScrollMore = (boolean) ((JavascriptExecutor) driver).executeScript(
	            "mobile: scrollGesture", 
	            ImmutableMap.of(
	                "left", 100,
	                "top", 100,
	                "width", 200,
	                "height", 200,
	                "direction", "up",
	                "percent", 3.0, // Use 100% instead of 300% for a controlled scroll
	                "speed", 1500 // Optional: Slower speed can be more reliable
	            ));
		}while(canScrollMore);
	}
	
	public void swipeAction(WebElement ele,String direction)
	{
		driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId",((RemoteWebElement)ele).getId(),
				"direction",direction,
				"percent",0.75
				));
	}
	
	public void dragGesture(WebElement ele)
	{
		driver.executeScript("mobile: dragGesture",ImmutableMap.of(
				"elementId",((RemoteWebElement)ele).getId(),
				"endX",475,
				"endY",424
				));
	}
	
	public String getTextOfToastMsg(WebElement ele)
	{
	return	ele.getAttribute("name");
	}
	
	
}
