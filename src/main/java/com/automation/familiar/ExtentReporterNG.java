package com.automation.familiar;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



//You have a file which connects to testng

//Testng to listen to this extent reports.
public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;
    public void generateReport1(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
    extent = new ExtentReports(outputDirectory);
    for (ISuite suite : suites) {

            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
            ITestContext context = r.getTestContext();
            buildTestNodes(context.getPassedTests(), LogStatus.PASS);
            buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
            buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        extent.flush();
        extent.close();
   }
@BeforeSuite
private void buildTestNodes(IResultMap tests, LogStatus status) {
   ExtentTest test;
   if (tests.size() > 0) {
   for (ITestResult result : tests.getAllResults()) {
   test = extent.startTest(result.getMethod().getMethodName());
   /*test.getTest(). = getTime(result.getStartMillis());
   test.getTest().endedTime = getTime(result.getEndMillis());*/
   for (String group : result.getMethod().getGroups())
   test.assignCategory(group);
   String message = "Test " + status.toString().toLowerCase() + "ed";
   if (result.getThrowable() != null)
   message = result.getThrowable().getMessage();
   test.log(status, message);
   extent.endTest(test);
   }
   }
   }

private Date getTime(long millis) {
   Calendar calendar = Calendar.getInstance();
   calendar.setTimeInMillis(millis);
   return calendar.getTime();        
   }
public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
		String outputDirectory) {
	// TODO Auto-generated method stub
	
}

public static String capture(WebDriver driver,String screenShotName) throws IOException
{
    TakesScreenshot ts = (TakesScreenshot)driver;
    File source = ts.getScreenshotAs(OutputType.FILE);
    String dest = System.getProperty("user.dir") +"\\ErrorScreenshots\\"+screenShotName+".png";
    File destination = new File(dest);
    FileUtils.copyFile(source, destination);        
                 
    return dest;
}
}