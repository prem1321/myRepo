package com.teksys.executer;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.teksys.commons.CommonFunction;
import com.teksys.commons.CommonVariables;
import com.teksys.pagefunctions.LandingPage;

public class RegressionJob extends CommonFunction {
LandingPage lp = new LandingPage();

public static String APPLICATION = "CPC PLUS";
public static String APP_BROWSERSEL="FireFox";
public static String EXECUTE_TST_GRP ,EXECTN_FOLDR_PATH, EXECTN_SUBFLDR_PATH,TESTCASES_FOLDERPATH;
public static WebDriver driver;
public static String ROOT_PATH ;	

SoftAssert sa;

@BeforeTest(alwaysRun=true)
public void BackUpExecutionFolder() throws Exception {

	ROOT_PATH = CommonFunction.createFolders(CommonVariables.REPORT_PATH, CommonFunction.createExecutionFolder());
	System.out.println(ROOT_PATH);
	
}	


	
	
	@Test
	public void TEST2(Method m) throws InterruptedException, IOException {
		SoftAssert sa = new SoftAssert();
		EXECUTE_TST_GRP = m.getName();
		EXECTN_SUBFLDR_PATH = CommonVariables.REPORT_PATH+"//"+ROOT_PATH+"//"+EXECUTE_TST_GRP;
		 driver = new FirefoxDriver();
	     driver.get(CommonVariables.LANDING_URL);	
	     driver.manage().window().maximize();
        lp.Services(driver, EXECTN_SUBFLDR_PATH);
      
	}
	
	
	@Test
	public void TEST1(Method m) throws InterruptedException, IOException {
		SoftAssert sa = new SoftAssert();
		EXECUTE_TST_GRP = m.getName();
		EXECTN_SUBFLDR_PATH = CommonVariables.REPORT_PATH+"//"+ROOT_PATH+"//"+EXECUTE_TST_GRP;
		 driver = new FirefoxDriver();
	     driver.get(CommonVariables.LANDING_URL);	
	     driver.manage().window().maximize();
        lp.LandingPageObjects(driver, EXECTN_SUBFLDR_PATH);
     
	}
	
	@AfterMethod(alwaysRun=true)
	public void CloseBrowser(ITestResult result)
	{
		try
		{
			if(result.getStatus() == ITestResult.FAILURE)
			{
				String iFailed = "Failed";
				Thread.sleep(5000);
				CommonFunction.captureScreenshot(driver, EXECTN_SUBFLDR_PATH, iFailed);
				System.out.println("This is failed"); 
				
			
				driver.quit();
			}

			else if(result.getStatus() == ITestResult.SUCCESS)
			{
				String iPassed = "Passed";
				Thread.sleep(5000);
			
				System.out.println("This is Passed");
				
				
				driver.quit();

			}

			else if(result.getStatus() == ITestResult.SKIP)
			{
				System.out.println("This is skipped");

			}


		}

		catch(Exception e)
		{

			//e.printStackTrace();
		}

	}
}
