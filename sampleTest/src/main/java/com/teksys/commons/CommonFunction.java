package com.teksys.commons;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class CommonFunction {

	
	public void clickElement(WebDriver driver,By locator) {
		WebElement ele = find_element(driver,locator);		
		highlightElement(driver,ele);
		ele.click();
	}

	public void jClick( WebDriver driver,By locator) {

		WebElement ele = find_element(driver,locator);
		highlightElement(driver,ele);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
	}

	public void SetCheckBoxStatus(WebDriver driver,By locator,boolean status) {
		WebElement ele = find_element(driver,locator);
		boolean ele_status = ele.isSelected();
		if (ele_status != status){
			clickElement(driver,locator);
		}

	}


	public void selectItem(WebDriver driver,By locator, String itemToSelect) {
		WebElement ele = find_element(driver,locator);
		ele.findElement(By.xpath("//option[text()='" +itemToSelect+ "']")).click();
	}
	protected WebElement find_element(WebDriver driver,By locator) {
		WebElement ele =  (new WebDriverWait(driver, 60))
				.until(ExpectedConditions.presenceOfElementLocated(locator));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", ele);
		//highlightElement(ele);
		return ele;
		//Thread.sleep(500); 
	}

	public String getElementAttribute(WebDriver driver,By locator, String Attribute) {
		WebElement ele= find_element(driver,locator);
		return ele.getAttribute(Attribute);
	}

	public static void highlightElement(WebDriver driver,WebElement element) {
		for (int i = 0; i <10; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if((i % 2)==0) {
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid yellow;");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: yellow; border: 2px solid red;");
			}else {	
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			}}
	}


	public static String getText(WebDriver driver, WebElement iObjectElement){
		String iActualText = iObjectElement.getText();
		objHighlight(driver,iObjectElement);
		//System.out.println("Actual Page Text:- " + iActualText);
		return iActualText;
	}

	public  String getAttribute(WebDriver driver, WebElement iObjectElement,String att_type){
		String iActualText = iObjectElement.getAttribute(att_type);
		//System.out.println("Actual Page attribute:- " + iActualText);

		return iActualText;
	}
	public static String generateString(int length) {
		String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";   //alphabets and numbers

		String randomtext="";
		String temp=RandomStringUtils.random(length,allowedChars);
		randomtext="CPCTEST"+temp.substring(0,temp.length()-2);

		return randomtext;
	}

	public static String generateRanPhoneNum(int length) {
		String allowedChars = "1234567890";   //alphabets and numbers
		String randomtext="";
		String temp=RandomStringUtils.random(length,allowedChars);
		randomtext=temp.substring(0,temp.length()-2);

		return randomtext;
	}

	
	
	public static void objHighlight(WebDriver driver, WebElement element){
		JavascriptExecutor js=(JavascriptExecutor)driver; 
		js.executeScript("arguments[0].setAttribute('style', 'background: #b3ffc4; border: 2px solid yellow;');", element);
		try {
			Thread.sleep(500);
		} 
		catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} 
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element); 
	}
	public static boolean isPresentAndDisplayed(WebDriver driver, WebElement element) {
		try {

			objHighlight(driver, element);

			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isPresentAndEnabled(WebDriver driver, WebElement element) {
		try {

			objHighlight(driver, element);
			return element.isEnabled();
		} catch (NoSuchElementException e) {
			return false;
		}
	}


	public static void captureScreenshot(WebDriver driver, String iScreenShotpath, String iScreenshotName){
		try {

			File iScreenshot =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(iScreenshot, new File(iScreenShotpath+"\\"+iScreenshotName + ".PNG"));
			System.out.println("Screenshot Taken refer Folder"+iScreenShotpath+"\\"+iScreenshotName);
		} catch (Exception e) {
			System.out.println("Error While Taking Screenshot"+e);
		}

		//Shutterbug.shootPage(driver,ScrollStrategy.VERTICALLY,1000,true).save(iScreenShotpath+"\\"+iScreenshotName+ ".png");
	}

	public static void FinalScreenshot(WebDriver driver, String iScreenShotpath, String iScreenshotName){
		try {
			File iScreenshot =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(iScreenshot, new File(iScreenShotpath+"\\"+iScreenshotName + ".PNG"));
			System.out.println("Screenshot Taken refer Folder"+iScreenShotpath+"\\"+iScreenshotName);
		} catch (Exception e) {
			System.out.println("Error While Taking Screenshot"+e);
		}

		//Shutterbug.shootPage(driver,ScrollStrategy.VERTICALLY,1000,true).save(iScreenShotpath+"\\"+iScreenshotName+ ".png");
	}

	public static  String createExecutionFolder() throws Exception {
		Calendar imycalendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MMdd_hhmm_ss");
		String iDatetimeStamp = formatter.format(imycalendar.getTime());
		//JOptionPane.showMessageDialog(null,iDatetimeStamp);
		return iDatetimeStamp; 
	}

	public static   String createFolders(String iOutputReportPath, String iOutputReportFolderName) throws Exception {
		String iReportFolderName = iOutputReportFolderName;
		File iReportFolderCreate = new File (iOutputReportPath+iReportFolderName);
		if (!iReportFolderCreate.exists()) {
			if (iReportFolderCreate.mkdir()) {
				System.out.println("Folder is created!");
			} 
		}
		return iReportFolderName;
	}

	public void buttonClick(WebDriver driver,By locator) {
		WebElement ele = find_element(driver,locator);
		//highlightElement(ele);
		objHighlight(driver,ele);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
	}

	public void inputTextField(WebDriver driver ,By locator,String Value) {
		WebElement ele = find_element(driver,locator);
		objHighlight(driver,ele);
		ele.clear();
		ele.sendKeys(Value);

	}


	public void dropdownByValue(WebDriver driver,By locator, String itemToSelect) {
		WebElement ele = find_element(driver,locator);
		ele.findElement(By.xpath("//option[text()='" +itemToSelect+ "']")).click();
	}

	public static boolean assertContains(String actualText,String ExpectedText) {
		boolean textval;

		if(actualText.contains(ExpectedText)) {
			textval = true;

		} 
		else {
			textval = false;
		}

		return textval;
	}
	public static int generateRandNo(int digit) {	
		SecureRandom random = new SecureRandom();
		int num = random.nextInt(digit);
		return num;
	}



	public static void captureHighlightScreenshot(WebDriver driver,String iScreenShotpath, String iScreenshotName , WebElement ele ) throws IOException {

		JavascriptExecutor js=(JavascriptExecutor)driver; 
		js.executeScript("arguments[0].setAttribute('style', 'background: orange; border: 2px solid red;');",ele);
		File iScreenshot =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(iScreenshot, new File(iScreenShotpath+"\\"+iScreenshotName + ".PNG"));
		System.out.println("Screenshot Taken refer Folder"+iScreenShotpath+"\\"+iScreenshotName);

	}
	public void buttonClickScreenshot(WebDriver driver,By locator, String ScreenshotPath, String ScreenshotName) throws InterruptedException, IOException {
		WebElement ele = find_element(driver, locator);
		//highlightElement(ele);
		//objHighlight(driver,ele);
		captureHighlightScreenshot(driver, ScreenshotPath, ScreenshotName,ele);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);

		Thread.sleep(2000);
	}


	public void inputText(WebDriver driver ,By locator,String Value, String ScreenshotPath,String ScreenshotName) throws IOException {
		WebElement ele = find_element(driver,locator);
		//objHighlight(driver,ele);
		ele.clear();
		ele.sendKeys(Value);
		captureHighlightScreenshot(driver, ScreenshotPath, ScreenshotName,ele);


	}

	public void selectDropdown(WebDriver driver,By locator, String itemToSelect, String ScreenshotPath, String ScreenshotName) throws IOException {
		WebElement ele = find_element(driver,locator);
		objHighlight(driver,ele);
		ele.findElement(By.xpath("//option[text()='" +itemToSelect+ "']")).click();
		captureHighlightScreenshot(driver, ScreenshotPath, ScreenshotName,ele);
	}


	public  void radioBtnClickScreenshot(WebDriver driver,By locator, String ScreenshotPath, String ScreenshotName) throws InterruptedException, IOException {
		WebElement ele = find_element(driver, locator);
		//highlightElement(ele);
		//objHighlight(driver,ele);

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", ele);
		captureHighlightScreenshot(driver, ScreenshotPath, "Assrt_"+ScreenshotName,ele);
		Thread.sleep(2000);
	}

	


	public static String getTextScrnshot(WebDriver driver, WebElement iObjectElement, String iScreenShotpath, String iScreenshotName) throws IOException{
		String iActualText = iObjectElement.getText();
		//objHighlight(driver,iObjectElement);
		CommonFunction.captureHighlightScreenshot(driver, iScreenShotpath, "Assrt_"+iScreenshotName, iObjectElement);
		//System.out.println("Actual Page Text:- " + iActualText);
		return iActualText;
	}



	public static boolean isDisabled(WebElement iObjectelement, String attribute) {
		Boolean result = false;
		try {
			String value = iObjectelement.getAttribute(attribute);
			if (value.equalsIgnoreCase(" ")){
				result = true;
			}
		} catch (Exception e) {}

		return result;
	}

	public static WebElement findelement(WebDriver driver,WebElement ele) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -200)");
		highlightElement(driver,ele);
		return ele;
		// Thread.sleep(500);
	}

	public void setValueInEditbox(WebDriver driver,By locator, String Value) {
		WebElement ele = find_element(driver, locator);
		highlightElement(driver, ele);
		ele.clear();
		ele.sendKeys(Value);
	}

	public void SetCheckBoxStatusElement(WebDriver driver,By locator, boolean status) {
		WebElement ele = findelement(driver, driver.findElement(locator));
		boolean ele_status = ele.isSelected();
		if (ele_status != status) {
			clickElement(driver,locator);
		}



	}

	public static WebElement find_element_footer(WebDriver driver,WebElement ele) {

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
		//	((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -50)");
		highlightElement(null, ele);
		return ele;
		// Thread.sleep(500);
	}


	public static String generateInteger(int length) {
		String allowedChars = "1234567890"; // alphabets and numbers
		String randomtext = "";
		String temp = RandomStringUtils.random(length, allowedChars);
		randomtext = temp.substring(0, temp.length() - 2);

		return randomtext;
	}


	public static void auditLog(String module,String applicationname,String testmethodname,String result,String screenshotpath, String username) {
		java.sql.Connection conn = null;
		Statement stmt = null;
		try{
			Class.forName(CommonVariables.CONNECTOR);
			conn = DriverManager.getConnection(CommonVariables.DB_ENDPOINT, CommonVariables.DB_USER, CommonVariables.DB_PWD);
			System.out.println("Connected database successfully...");
			stmt = conn.createStatement();
			PreparedStatement preparedStmt = conn.prepareStatement("INSERT into audit_log ( module, application,test_name,test_result,execute_datetime,scrnshot_path,workstationID,user_id) VALUES(?,?,?,?,?,?,?,?)",
					stmt.RETURN_GENERATED_KEYS);   
			preparedStmt.setString(1,module);
			preparedStmt.setString(2,applicationname);
			preparedStmt.setString(3,testmethodname);
			preparedStmt.setString(4,result);
			preparedStmt.setTimestamp(5, new java.sql.Timestamp(new java.util.Date().getTime()));                           
			preparedStmt.setString(6, screenshotpath);
			preparedStmt.setString(7, System.getenv("COMPUTERNAME"));
			preparedStmt.setString(8, username);
			preparedStmt.executeUpdate(); 
			ResultSet tableKeys = preparedStmt.getGeneratedKeys();
			tableKeys.next();
			int autoGeneratedID = tableKeys.getInt(1);
			System.out.println("Records Added Sucessfully");
		}
		
		catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		
		finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					conn.close();
			}
			
			catch(SQLException se){
				}
			
			try{
				if(conn!=null)
					conn.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}//end finally try
		}//end try

	}//end main
}
