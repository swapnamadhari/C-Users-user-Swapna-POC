package com.cashe.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.cash.utilities.DataBaseConnection;

import java.util.Set;
public class BasePage {


	//public static ExtentReports eReports;
	//public   ExtentTest eTest;
	public WebDriver driver;
	Logger log = Logger.getLogger(BasePage.class);
	public  final org.slf4j.Logger logger = LoggerFactory.getLogger(BasePage.class);
	String log4jConfPath = System.getProperty("user.dir") +"/src/main/resources/properties/log4j.properties";
	public  Properties  pro=new Properties();
	public DataBaseConnection dbCon;
	//public DataBaseConnection dbCon=new DataBaseConnection();
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public WebDriver getDriver() {
		return driver;
	}
	@BeforeClass
	public void initilize(){

		try {
			//pro=new Properties();
			
			pro.load(new FileInputStream(System.getProperty("user.dir") +"/src/main/resources/properties/LoadTestDB.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		}


		//	PropertyConfigurator.configure(log4jConfPath);
		//	Logger.getRootLogger();
		//	System.out.println("initialize");
		//	MDC.put("logFileName", "NewUser");
		logger.debug("Starting test");
		//String fileName = "C:\\Users\\vijaykumar.m\\workspace\\MyCashe\\tttttt.log";
	}
	public void capture(Status s,String screenShotName,ExtentTest exTest) throws IOException
	{
		//String screenShotPath = GetScreenShot.capture(driver, "screenShotName");
		//eTest.log(LogStatus.FAIL, result.getThrowable());

		screenShotName=screenShotName+"_"+getRandMobileNum();

		TakesScreenshot ts = (TakesScreenshot)((AppiumDriver<?>)getDriver());
		File source = ts.getScreenshotAs(OutputType.FILE);
		//  String dest = System.getProperty("user.dir") +"\\Reports\\"+screenShotName+".png";
		String dest ="../Reports/"+screenShotName+".png";
		File destination = new File(dest);
		FileUtils.copyFile(source, destination);
		//exTest.addScreenCaptureFromPath(dest);
		// exTest.log(status, details)

		//exTest.log(s, "Snapshot below: " , MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		exTest.info("Snapshot below: ", MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		//exTest.log(s, "Snapshot below: " + exTest.addScreenCaptureFromPath(dest));
		logoMove();
		//   eTest.log(value, "Snapshot below: " + eTest.addScreenCapture("./"+screenShotName+".jpg"));
		// return dest;
	}
	//@Test
	public void logoMove() throws IOException{
		File source =new File("./CASHe-Logo1.png");
		File destination = new File("../Reports/CASHe-Logo1.png");
		FileUtils.copyFile(source, destination); 

		File source1 =new File("./aeries.png");
		File destination1 = new File("../Reports/aeries.png");
		FileUtils.copyFile(source1, destination1); 
	}
	public  void fluentWaitClickable(WebDriver driver, WebElement element1) {
		WebElement element = element1;
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(250, TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(StaleElementReferenceException.class);
		//wait.until(ExpectedConditions.visibilityOf(element));
		element= (WebElement) wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public void waitFor(int sec) throws InterruptedException {
		Thread.sleep(sec*1000);
	}
	public  void waitUntilElementClickable(WebDriver driver, WebElement element){

		WebDriverWait wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public  boolean waitUntilElementClickable(WebDriver driver, WebElement element,int seconds){
		try{
			WebDriverWait wait=new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));			
			return true;
		}catch(Exception e){

			return false;
		}

	}
	public void asserEqual(WebElement element,String message) {
		//Asserting messages
		Assert.assertEquals(element.getText().trim(), (message).trim());
	}
	public void waitUntilElementDisply(WebDriver driver, WebElement element){

		WebDriverWait wait=new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public  void popfluentWait(WebDriver driver, WebElement element, int Seconds) {
		logger.info("Inside pop Fluent wait");
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(Seconds, TimeUnit.SECONDS).pollingEvery(250, TimeUnit.MILLISECONDS)
		.ignoring(NoSuchElementException.class)
		.ignoring(ElementNotVisibleException.class)
		.ignoring(StaleElementReferenceException.class);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		logger.info("Waited for "+ Seconds +"inside popfluentwait");
	}

	public boolean isElementClickable(WebDriver driver, WebElement element){
		try{
			//fluentWaitClickable(driver, element);
			waitUntilElementDisply(driver, element);
			return true;
		}catch(Exception e){

			return false;
		}
	}
	public boolean swipeToDirection(MobileElement el, String direction) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> swipeObject = new HashMap<String, String>();
			if (direction.equals("d")) {
				swipeObject.put("direction", "down");
			} else if (direction.equals("u")) {
				swipeObject.put("direction", "up");
			} else if (direction.equals("l")) {
				swipeObject.put("direction", "left");
			} else if (direction.equals("r")) {
				swipeObject.put("direction", "right");
			}
			swipeObject.put("element", el.getId());
			js.executeScript("mobile:swipe", swipeObject);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public void contextHandle(){
		//Set<String> contextNames  = ((AppiumDriver<?>) driver).getContextHandles();
		//Set<String> contextNames = ((AppiumDriver<?>) getDriver()).getContextHandles();
		//System.out.println("contexts are :"+contextNames);
		//switchNative();
	}
	public boolean isElementPresent(WebElement element, int Seconds) {
		try {
			popfluentWait(driver, element, Seconds);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	//@SuppressWarnings("rawtypes")
	@SuppressWarnings("deprecation")
	public void swipeTo(){

		try {
			Dimension size=driver.manage().window().getSize();
			//int height=size.getHeight();
			//int width=size.getWidth();
			int starty= (int) (size.height*0.90);
			int endy= (int) (size.height*0.10);
			int startx=size.width/2;

			((AppiumDriver<?>)getDriver()).swipe(startx, starty-100, startx, endy, 3000);
			System.out.println("Swipe has been done"+startx+","+starty+","+startx+","+endy);
			/*TouchAction ts = new TouchAction((PerformsTouchActions) driver);
      ts.press(startx, starty).moveTo(startx, endy).release().perform();




		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        HashMap scrollObject = new HashMap<>();
	       // scrollObject.put("predicateString", "value = DESIGNATION");
	        scrollObject.put("direction", "down");
	             js.executeScript("mobile: scroll", scrollObject);*/


		}catch(Exception e) {
			System.out.println("Swipe not performed");
			e.printStackTrace();
		}



	}
	//Switch to Native Context
	public void switchNative(){
		((AppiumDriver)getDriver()).context("NATIVE_APP");
	}
	public void switchWebView() throws InterruptedException{
		Thread.sleep(8000);
		Set<String> contextNames = ((IOSDriver)getDriver()).getContextHandles();
		System.out.println("contexts are :"+contextNames);
		/*for(String context:contextNames) {
			if(context.contains("WEBVIEW")) {
				((IOSDriver)getDriver()).context(context);
			}
		}*/
		((IOSDriver)getDriver()).context("WEBVIEW_1");
		System.out.println(((IOSDriver)getDriver()).getContext());
	}
	//Click on element
	public WebElement clickonElement(WebDriver driver, WebElement element, String enterSucessMessage,
			String failedErrorMessage) throws InterruptedException, FileNotFoundException {

		WebElement web = null;
		boolean found = false;
		final long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < 90000) {
			try {
				System.out.println("just came for  click on "+element);
				//System.out.println("element is+ "+element);
				//fluentWaitClickable(driver, element);
				//waitUntilElementClickable(driver, element);
				element.click();
				logger.info(enterSucessMessage);
				found = true;
				break;
			} catch (NoSuchElementException e1) {
				Assert.fail(failedErrorMessage + " :--> EXCEPTION3: Application Error:- No Such Element Exception"+element);
			} catch (ElementNotVisibleException enve) {
				Assert.fail(failedErrorMessage + " :--> EXCEPTION3: Application Error:- Element Not Visible Exception");
			} catch (TimeoutException toe) {

				Assert.fail(failedErrorMessage
						+ " :--> Time out Exception occured :- This may be due the reason page may not have loaded or element xpath might have changed.");
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			logger.info("Found element after waiting for " + totalTime + " milliseconds.");
		} else {
			logger.info("Failed to find element after " + totalTime + " milliseconds.");
		}
		return web;
	}
	//Set element
	public WebElement mobileSetElement(WebDriver driver, WebElement element, String text, String enterSucessMessage,
			String failedErrorMessage) {
		System.out.println("came to set elememnt for "+element);
		WebElement web = null;
		boolean found = false;
		final long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < 90000) {
			try {
				//waitUntilElementClickable(driver, element);
				try {
				element.clear(); 
				}catch(Exception e) {
					
				}
				//element.sendKeys(text);
				((MobileElement) element).setValue(text);
				logger.info(enterSucessMessage);
				found = true;
				break;
			} catch (NoSuchElementException e1) {

				e1.printStackTrace();
				Assert.fail(failedErrorMessage + " :--> EXCEPTION3: Application Error:- No Such Element Exception");
			} catch (ElementNotVisibleException enve) {

				enve.printStackTrace();
				Assert.fail(failedErrorMessage + " :--> EXCEPTION3: Application Error:- Element Not Visible Exception");

			} catch (TimeoutException toe) {

				toe.printStackTrace();
				Assert.fail(failedErrorMessage
						+ " :--> EXCEPTION3: Time out Exception occured :- This may be due the reason page may not have loaded or element xpath might have changed.");
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			logger.info("Found element after waiting for " + totalTime + " milliseconds.");
		} else {
			logger.info("Failed to find element after " + totalTime + " milliseconds.");
		}
		return web;
	}
	//Scroll to element
	public void scrollToElement(WebElement element){
		try{
			while(!isElementDisplayed(element)){
				swipeTo();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//Verify element displayed
	public boolean isElementDisplayed(WebElement element){
		boolean flag=false;
		try{

			//	waitUntilElementDisply(driver, element);
			if(element.isDisplayed())
				flag= true;

		}catch(Exception e){
			flag= false;
		}
		return flag;
	}
	public String getRandMobileNum(){
		Random rn = new Random();
		int range = 9 - 6 + 1;
		int randomNum =  rn.nextInt(range) + 6;
		// System.out.println(randomNum);
		String mobileNum="";
		for(int i=0;i<9;i++){
			mobileNum=mobileNum+Integer.toString(rn.nextInt(9));
		}
		mobileNum = Integer.toString(randomNum)+mobileNum;
		System.out.println(mobileNum);

		return mobileNum;   
	}
	//
	//Random Number Genarating for Aadhaar Number
	public String getRandAadhaarNumber(){
		Random rn = new Random();
		int range = 8;
		System.out.println("range "+range);
		int randomNum =  rn.nextInt(range) + 1;
		System.out.println("randomNum"+randomNum);
		String aadhaarNum="";
		System.out.println("aadhaarNum"+aadhaarNum);
		for(int i=0;i<11;i++){
			aadhaarNum=aadhaarNum+Integer.toString(rn.nextInt(9));
		}
		aadhaarNum = Integer.toString(randomNum)+aadhaarNum;
		System.out.println(aadhaarNum);
		return aadhaarNum;
	}

	public int generateRandom(int max,int min){

		Random d=new Random();
		int x=d.nextInt((max- min) + 1) + min;
		return x;
	}
	//Fetching data from DB
	/*public List<String> dataBaseSelect(String sql){
		List<String> DataResponse = new ArrayList<String>();
		Connection conn = null; 
		String q;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://testdb.cashe.co.in:3306/cashe_qa", db_username, db_password);
			logger.debug("Database Connection Established Successfully...");
			Statement st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);
			logger.debug("Result Set :::" + rs);
			rs.beforeFirst();
			ResultSetMetaData md = rs.getMetaData();
			int colCount = md.getColumnCount();
			logger.debug("SQl query ::: " + sql);
			logger.debug("Number of Columns ::: " + colCount);

			if (rs.next())
			{
				do{ 
					for (int j = 1; j<=colCount;j++)
					{
						q = rs.getString(j);
						DataResponse.add(q);
					}

				}while(rs.next());
			}
			//closing DB connection
			conn.close();
		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Message : " + e.getMessage());
		}
		System.out.println("In function"+DataResponse);
		return DataResponse ;

	}*/

	//Upadating data into DB 
	/*public void dataBaseUpdate(String query){
		Connection conn = null; 
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://testdb.cashe.co.in:3306/cashe_qa", db_username, db_password);
			logger.debug("Database Connection Established Successfully...",true);
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			logger.debug("SQl query ::: " + query);
			logger.debug("Query executed");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Message : " + e.getMessage());
		}
	}*/
	public void quitApp(){
		System.out.println("driver quit");
		if(getDriver()!=null) {
			getDriver().quit();
		}
	}
}
