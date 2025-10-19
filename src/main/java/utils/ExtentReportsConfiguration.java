package utils;


import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentReports;


public class ExtentReportsConfiguration {

	
	public static ExtentReports getExtentReports() {
	    String reportPath = System.getProperty("user.dir") + "/Reports/index.html";
	    ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
	    
	    // Better configuration
	    reporter.config().setReportName("Mobile Automation Results");
	    reporter.config().setDocumentTitle("Appium Test Results");
	    reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	    
	    ExtentReports extent = new ExtentReports();
	    extent.attachReporter(reporter);
	    
	    // Add system info
	    extent.setSystemInfo("Tester", "Mounika Kilaru");
	    extent.setSystemInfo("Platform", "Android");
	    extent.setSystemInfo("Java Version", System.getProperty("java.version"));
	    
	    return extent;
	}
	
}
