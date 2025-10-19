package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.ios.IOSDriver;

public class IOSActions extends AppiumCommonUtils{
	public IOSDriver driver;
	public IOSActions(IOSDriver driver) {
		this.driver= driver;
	}
	
	public void TouchAndHold(WebElement ele, String duration) {
		
		driver.executeScript("mobile:touchAndHold",ImmutableMap.of(
				"element",((RemoteWebElement)ele).getId(),
		"duration",duration
));		
	}
	
	public void swipeOnElement(WebElement ele,String direction)
	{
		driver.execute("mobile:swipe",ImmutableMap.of(
				"element",((RemoteWebElement)ele).getId(),
				"direction",direction));
	}
	
	public void swipe(WebElement ele,String direction)
	{
		driver.execute("mobile:swipe",ImmutableMap.of(
				"direction",direction));
	}
	
	
	public void scroll(WebElement ele,String direction)
	{
		driver.execute("mobile:scroll",ImmutableMap.of(
				"element",((RemoteWebElement)ele).getId(),
				"direction",direction));
	}

}
