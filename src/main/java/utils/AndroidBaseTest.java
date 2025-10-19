package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver.Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import androidPages.SignUpPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;


public class AndroidBaseTest extends AppiumCommonUtils{
	public AppiumDriverLocalService service;
	public AndroidDriver driver;
	public  UiAutomator2Options options;
	public SignUpPage signUpPage;
	 
	 @BeforeClass(alwaysRun = true)
	public void appiumSetUp() throws IOException {
		 Properties prop  = new Properties();
		 FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/testData.properties");
		 prop.load(fis);
		 String ipAddress= prop.getProperty("ipAddress");
		 String port = prop.getProperty("port");
			service=startAppiumServer(ipAddress,Integer.parseInt(port));
			
  options = new UiAutomator2Options();
  options.setApp(System.getProperty("user.dir")+"/src/main/java/resources/General-Store.apk");
  options.setDeviceName(prop.getProperty("mobileName"));
  options.setAppWaitActivity("com.androidsample.generalstore.*");
  options.setNewCommandTimeout(Duration.ofSeconds(40000));
  options.setAutoGrantPermissions(true);
  options.setAdbExecTimeout(Duration.ofSeconds(60000));
  options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60000));
  
	driver = new AndroidDriver(service.getUrl(),options);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
	 signUpPage= new SignUpPage(driver);
	}
	 
	 
	 @AfterClass(alwaysRun = true)
	 public void tearDown() {
		 service.stop();
		 driver.quit();
	 }
}
