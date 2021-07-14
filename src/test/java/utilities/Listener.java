package utilities;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Listener implements ITestListener,ISuiteListener,IInvokedMethodListener
{

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
		
		Reporter.log("The test "+arg0.getName()+" is about to finish", false);
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		Reporter.log("The test "+arg0.getName()+" is about to Start", false);
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("The test "+result.getName()+" is about to Start", false);
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("The test "+result.getName()+" has failed due timeout", false);
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		System.out.println("****The test "+result.getName()+" has been failed****");
		result.getTestContext();
		String methodname=result.getName().toString().trim();
		String timestamp= new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
		ITestContext context=result.getTestContext();
	
		WebDriver driver=(WebDriver)context.getAttribute("driver"); 
	takescreenshot(driver);
		Reporter.log("The test "+result.getName()+" failed", false);
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("The test "+result.getName()+" Skipped", true);
	
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("The test "+result.getName()+" Started", false);
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		Reporter.log("The test "+result.getName()+" Executed sucessfully", false);
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		
		Reporter.log("The test"+method.getTestMethod().getMethodName()+" invoked sucessfully", true);
		
	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// TODO Auto-generated method stub
		Reporter.log("The test "+method.getTestMethod().getMethodName()+" about to start", true);
		
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		Reporter.log("The "+suite.getName()+ "has been completed", true);
	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		Reporter.log("The "+suite.getName()+" has started",true);
	}
	

	
	public static void takescreenshot(WebDriver driver) {
		
		try {
			String filepath="";
		TakesScreenshot sc=((TakesScreenshot)driver);
		File src=sc.getScreenshotAs(OutputType.FILE);
	
			FileUtils.copyFile(src, new File(filepath));
			
			System.out.println("The screenshot captured sucessfully in path"+filepath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("The error generarted while taking screenshot"+e.getMessage());
			e.printStackTrace();
		}
	}
}
