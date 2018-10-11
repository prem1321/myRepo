package com.teksys.pagefunctions;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.teksys.commons.CommonFunction;

public class LandingPage extends CommonFunction{

	By Obj1=By.xpath("//img[@alt='TEKsystems with Own Change tagline logo']");
	By Obj2=By.xpath("//span[contains(.,'Services')]");
	By Obj3=By.xpath("//span[contains(.,'Industries')]");
	By Obj4=By.xpath("//span[contains(.,'About Us')]");
	By Obji=By.xpath("//span[contains(.,'Insights')]");
	By Obj5=By.xpath("//span[contains(.,'Careers')]");
	By Obj6=By.xpath("//a[@class='score-button btn btn--util btn--locations']");
	By Obj7=By.xpath("//span[contains(.,'Digital Experience')]");
	By Obj8=By.xpath("//span[contains(.,'DevOps & Automation')]");
	By Obj9=By.xpath("//span[contains(.,'Cloud Enablement')]");
	By Obj10=By.xpath("//span[contains(.,'Risk & Security')]");
	By Obj11=By.xpath("//span[contains(.,'Data Analytics & Insights ')]");
	By Obj12=By.xpath("//span[contains(.,'Technology Operations')]");
	By Obj13=By.xpath("//span[contains(.,'Enterprise Applications')]");
	By Obj14=By.xpath("//span[contains(.,'Telecom Design, Implementation & Operations')]");
	By Obj15=By.xpath("//span[contains(.,'All Services')]");
	
	By Obj16=By.xpath("//a[contains(.,'TEKsavvy® Blog')]");
	By Obj17=By.xpath("//a[contains(.,'News')]");
	By Obj18=By.xpath("//a[contains(.,'Press')]");
	By Obj19=By.xpath("//a[contains(.,'Contact Us')]");

	
	public  void LandingPageObjects(WebDriver driver, String ScreenshotPath) throws InterruptedException, IOException {
		captureHighlightScreenshot(driver, ScreenshotPath, "logo",find_element(driver,this.Obj1));
		captureHighlightScreenshot(driver,  ScreenshotPath, "Services",find_element(driver,this.Obj2));
		captureHighlightScreenshot(driver,  ScreenshotPath, "Industries",find_element(driver,this.Obj3));
		captureHighlightScreenshot(driver,  ScreenshotPath, "AboutUs",find_element(driver,this.Obj4));
		captureHighlightScreenshot(driver,  ScreenshotPath, "Insights",find_element(driver,this.Obji));
		captureHighlightScreenshot(driver,  ScreenshotPath, "Careers",find_element(driver,this.Obj5));
		//captureHighlightScreenshot(driver,  ScreenshotPath, "Digital Experience",find_element(driver,this.Obj7));
	}

	public  void Services(WebDriver driver, String ScreenshotPath) throws InterruptedException, IOException {
		
		buttonClickScreenshot(driver,this.Obj2,ScreenshotPath,"ClickServicesLink");

		captureHighlightScreenshot(driver, ScreenshotPath, "DigitalExp",find_element(driver,this.Obj7));
		captureHighlightScreenshot(driver,  ScreenshotPath, "DevOps",find_element(driver,this.Obj8));
		captureHighlightScreenshot(driver,  ScreenshotPath, "Cloud",find_element(driver,this.Obj9));
		captureHighlightScreenshot(driver,  ScreenshotPath, "Risk",find_element(driver,this.Obj10));
		captureHighlightScreenshot(driver,  ScreenshotPath, "DataAnalytics",find_element(driver,this.Obj11));
		captureHighlightScreenshot(driver,  ScreenshotPath, "TechOperations",find_element(driver,this.Obj12));
		captureHighlightScreenshot(driver,  ScreenshotPath, "EnterPriseApplications",find_element(driver,this.Obj13));
		captureHighlightScreenshot(driver,  ScreenshotPath, "Telecom",find_element(driver,this.Obj14));
		captureHighlightScreenshot(driver,  ScreenshotPath, "AllServices",find_element(driver,this.Obj15));
	}
	
	
}
