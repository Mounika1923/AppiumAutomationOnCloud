package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import iosPages.HomePage;

public class IOSBaseTest  extends AppiumCommonUtils{
	public AppiumDriverLocalService service;
	public XCUITestOptions options;
	public IOSDriver driver;
	public HomePage homePage ;
	
@BeforeClass(alwaysRun = true)	
public void appiumSetUp() throws IOException
{
	Properties prop = new Properties();
	FileInputStream fis = new FileInputStream(System.getProperty("user.dir"+"/src/main/java/resources/testData.properties"));
	prop.load(fis);
	String ipAdress= prop.getProperty("ipAdress");
	String port = prop.getProperty("port");
	service=startAppiumServer(ipAdress,Integer.parseInt(ipAdress));
	 options = new XCUITestOptions();
	options.setDeviceName("Iphone 14 Pro");
	options.setPlatformVersion("16.0.0");
	// No need to set the App for existing Apps
	options.setApp("/users/mkilaru/Documents/mobileAutomation/src/Rresources/UIKitCatalog.app");
	options.setWdaLaunchTimeout(Duration.ofSeconds(60));
	 driver = new IOSDriver(service.getUrl(),options);
	homePage = new HomePage(driver);
}
	 
	 @AfterClass(alwaysRun=true)
	 public void tearDown() {
		 service.stop();
		 driver.quit();
	 }
}
