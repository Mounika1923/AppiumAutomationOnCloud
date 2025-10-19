package utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumCommonUtils {
	
       
		public RemoteWebDriver driver;
		public WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		public AppiumDriverLocalService service;
		
		public AppiumDriverLocalService startAppiumServer(String ipAddress , int port) {
			
			service= new AppiumServiceBuilder() 
					.withAppiumJS(new File("C:\\Users\\Vamsi\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					.withIPAddress(ipAddress)
					.usingPort(port)
					.withTimeout(Duration.ofSeconds(60))
		           // .withArgument(GeneralServerFlag.ALLOW_INSECURE, "chromeDriver_autodownload")
					.build();
			service.start();
			return service;
		}
		
		
		public String getScreenShotPath(String testCaseName,AppiumDriver driver) throws IOException
			{
			File source =driver.getScreenshotAs(OutputType.FILE);
			String destinationPath =System.getProperty("user.dir")+"/Reports/"+testCaseName+".png";
			FileUtils.copyFile(source, new File(destinationPath));
			return destinationPath;
		}
		
		
	 
		public void waitUntilExpectedTextLoactedOnElement(WebElement ele,String expectedText,AppiumDriver driver) {
			
			wait.until(ExpectedConditions.textToBePresentInElement(ele, expectedText));
		}
	

}
