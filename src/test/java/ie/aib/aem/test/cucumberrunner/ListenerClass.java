package ie.aib.aem.test.cucumberrunner;

import ie.aib.aem.base.BaseClass;
import io.qameta.allure.Attachment;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerClass extends BaseClass implements ITestListener {
	
	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	//HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }
    
	//Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog (String message) {
        return message;
    }
	
	@Attachment(value = "page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
	
    @Override
    public void onTestFailure(ITestResult iTestResult) {
    	System.out.println("I am in onTestFailure method " +  getTestMethodName(iTestResult) + " failed");
    	saveScreenshotPNG(driver);
            System.out.println("Screesnshot captured for test case:" + getTestMethodName(iTestResult));
        saveTextLog(getTestMethodName(iTestResult) + " failed and screenshot taken!");
    }
	@Override
	public void onFinish(ITestContext iTestContext) {
		System.out.println("I am in onFinish method " + iTestContext.getName());		
	}
	@Override
	public void onStart(ITestContext iTestContext) {
		System.out.println("I am in onStart method " + iTestContext.getName());
        iTestContext.setAttribute("WebDriver", this.driver);
		
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
		 System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));	
	}
	@Override
	public void onTestSkipped(ITestResult iTestResult) {
		System.out.println("I am in onTestSkipped method "+  getTestMethodName(iTestResult) + " skipped");
		saveScreenshotPNG(driver);
		
	}
	@Override
	public void onTestStart(ITestResult iTestResult) {
		System.out.println("I am in onTestStart method " +  getTestMethodName(iTestResult) + " start");
		
	}
	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		System.out.println("I am in onTestSuccess method " +  getTestMethodName(iTestResult) + " succeed");
		saveScreenshotPNG(driver);
		
	}

}
