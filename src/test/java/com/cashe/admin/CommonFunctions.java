package com.cashe.admin;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.mysql.jdbc.Driver;

import io.appium.java_client.android.AndroidDriver;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class CommonFunctions {

	public String[] TestData = null;
	public  WebDriver driver;

	Connection conn = null;

	/* public static final String USERNAME = "vijaycashe";
	 public static final String ACCESS_KEY = "9339d6e6-f500-4c89-ab4a-143cbd5e2ec3";
	 public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";*/

	public static final String USERNAME = "vijay875";
	public static final String AUTOMATE_KEY = "Mq2yK8xLkRyvZw2NTX5h";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	//
	//	@BeforeClass

	//	@Parameters({"browserName" , "url" })
	public  void setup(/*String browserName, String url*/) throws MalformedURLException{

		/*	DesiredCapabilities cap=new DesiredCapabilities();
		cap = DesiredCapabilities.phantomjs();
	//	cap.setCapability(CONSTANT_NATIVEEVENTS, false);
		cap.setJavascriptEnabled(true);
		cap.setCapability("handlesAlerts", true);
		cap.setCapability("cssSelectorsEnabled", false);
		cap.setCapability("applicationCacheEnabled", true);
		cap.setCapability("acceptSslCerts", true);
		 */

		/*if(browserName.equals("IExplorer")){
			System.setProperty("webdriver.ie.driver", "resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			Reporter.log("Script executing on " + browserName + "browser",true);
		} else if (browserName.equals("Chrome")){*/
		//System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
		//driver = new HtmlUnitDriver();
		//Reporter.log("Script executing on " + browserName + "browser",true);
		/*} else if (browserName.equals("Firefox")){
			driver = new FirefoxDriver();
			Reporter.log("Script executing on " + browserName + "browser",true);
		} else {
			driver = new FirefoxDriver();
			Reporter.log("Script executing on " + browserName + "browser",true);
		}*/

		//File file = new File("C:/Program Files/phantomjs-2.0.0-windows/bin/phantomjs.exe");				
		/*  System.setProperty("phantomjs.binary.path", System.getProperty("user.dir")+"/src/test/resources/phantomjs.exe");		
        driver = new PhantomJSDriver();*/

		String osName=System.getProperty("os.name");
		if(osName.contains("Mac")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver");

		}else if(osName.contains("Win")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
		}
		driver=new ChromeDriver();
		/*ChromeOptions co=new ChromeOptions();
		co.addArguments("--headless");*/
		//driver= new ChromeDriver(/*co*/);
		//ChromeOptions options = new ChromeOptions();
		//options.addArguments("--start-fullscreen");
		/*driver=new ChromeDriver();*/

/*
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "62.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("resolution", "1920x1080");

		driver = new RemoteWebDriver(new URL(URL), caps);*/
		driver.get("https://stage.cashe.co.in");
		driver.manage().window().fullscreen();
		//

	}



	public void userLogin(WebElement loginElement,WebElement pwdElement,String usrName,String pwd){
		loginElement.sendKeys(usrName);
		pwdElement.sendKeys(pwd);

	}



	public void testWebTable(WebElement tableIdentifier){
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		WebElement tableElement = tableIdentifier;
		List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));
		Reporter.log("Number of rows fetched = " + ((tableRows.size())-1),true);

		if(tableRows.size() > 0){

			for(WebElement tableRow : tableRows){
				List<WebElement> tableColumns =  tableRow.findElements(By.tagName("td"));
				Reporter.log("Number of Columns = " + tableColumns.size(),true);

				for(WebElement tableColumn : tableColumns){
					Reporter.log(tableColumn.getText(),true);
				}
			}

		}
		Reporter.log("Empty Table");


	}

	public int webtableRowCount(WebElement tableIdentifier){
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		WebElement tableElement = tableIdentifier;
		List<WebElement> tableRows = tableElement.findElements(By.tagName("tr"));

		return (tableRows.size()-1);
	}

	public int webtableColumnCount(WebElement tableIdentifier){
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);

		WebElement tableElement = tableIdentifier;
		List<WebElement> tableColumns = tableIdentifier.findElements(By.tagName("td"));

		return(tableColumns.size());

	}

	public void selectOptionsFromDropdownList(WebElement dropdownObjectElement , String dropdownOption){
		Select dropdownList = new Select(dropdownObjectElement);
		dropdownList.selectByVisibleText(dropdownOption);
		Reporter.log("The selected Dropdown Option is " + dropdownOption,true);
	}

	public List<WebElement> getDropdownOptionList(WebElement dropdownObjectElement){
		Select dropdownList = new Select(dropdownObjectElement);
		List<WebElement> dropdownOptions = dropdownList.getOptions();
		Reporter.log("Number of Options Available in " + dropdownList + "List = " +  dropdownOptions.size());

		/*for(WebElement dropdownOption : dropdownOptions){
			String dropdownName = dropdownOption.getText();
			Reporter.log(dropdownName);
		}*/
		return(dropdownOptions);
	}



	public static String[][] getTestData(String excelPath,String sheetName, String tableName){
		String testData[][] = null;

		try{
			Workbook workbook = Workbook.getWorkbook(new File(excelPath));
			Sheet sheet = workbook.getSheet(sheetName);

			int ci, cj, rowStart, rowEnd, colStart, colEnd;

			Cell tableStart = sheet.findCell(tableName);

			rowStart = tableStart.getRow();
			colStart = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, colStart+1, rowStart+1, 100, 64000, false);
			rowEnd = tableEnd.getRow();
			colEnd = tableEnd.getColumn();

			testData = new String[rowEnd - rowStart -1][colEnd - colStart -1];

			ci = 0;
			for (int i = rowStart + 1 ;i < rowEnd ;i++ ,ci++){
				cj = 0;
				for (int j = colStart +1 ;j < colEnd ;j++,cj++){
					testData[ci][cj] = sheet.getCell(j,i).getContents();

				}
			}

		}catch (Exception e){
			Reporter.log(e.getMessage(),true);
		}
		return testData;
	}

	public int randomNumberGenerator(int size){
		Random random = new Random();
		int randomInt = random.nextInt(size);

		return randomInt;

	}
	public  List<String> UIList(){

		Wait<WebDriver> wait = new WebDriverWait(driver, 100); 
		//wait.until(ExpectedConditions.visibilityOf(custSupPage.PDCdtsList));
		List<WebElement> rows = driver.findElements(By.xpath("//tr[@ng-repeat-start='pdc in pdcDetails.pdcDetailsDtoList']/td")); 
		Reporter.log("NUMBER OF ROWS IN THIS TABLE = "+ rows .size(),true);
		List<String> strings = new ArrayList<String>();
		for(WebElement e : rows)	 
		{
			System.out.println("ghgj");
			strings.add(e.getText());         
		}   
		Reporter.log("List1 is: "+strings,true); 
		return strings;

	}
	public static int Result(List<String> custlist, List<String> outputDtls){
		boolean output ;
		output = custlist.removeAll(outputDtls);
		if(output){
			System.out.println("Comparison Successful");
			return 1;
		}
		else{
			System.out.println("Comparison Failed");
			return 0;
		}

	}      





	/*	
	 public String TestData(String keyName){
		 File file = new File("src\\test\\resources\\TestData.xls.properties");
		 FileInputStream fileInput = null;
		try {
				fileInput = new FileInputStream(file);
			} catch (FileNotFoundException e) {
						e.printStackTrace();
				}
				Properties prop = new Properties();
				try {
				   prop.load(fileInput);
								} catch (IOException e) {
									e.printStackTrace();
								}

								Enumeration KeyValues = prop.keys();
								while (KeyValues.hasMoreElements()) {
									String key = (String) KeyValues.nextElement();
									String value = prop.getProperty(key);
									System.out.println(key + ":- " + value);
								} 

		return keyName;


	 }

	 public void m1(){}*/
	/*public void Login(String userName, String password) throws InterruptedException{
		LoginPage LoginPage;
			Thread.sleep(5000);
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			LoginPage = PageFactory.initElements(driver, LoginPage.class);

			//String loginStatus;
			LoginPage.userNameField.clear();
			LoginPage.passwordField.clear();

			LoginPage.userNameField.sendKeys(userName);
			System.out.print("Enter user name");

			LoginPage.passwordField.sendKeys(password);
			System.out.print("Enter password");
			if(LoginPage.submitBtn.isEnabled()){
				LoginPage.submitBtn.click();
			} else {
				Reporter.log("Log In button is not available for clicking",true);
			}

		}*/


	/*public void validPersonalDts(String fullName, String personalEmailId,String EduQualification,String MobileNum,String CustCibilScore,String VerifyCompanyName,String CusStatus) 
		throws InterruptedException{ 
		//int submitstatus = 0;
		HomePage homePage;
		LoginPage loginPage;
		CustomerVerificationPage CustomerVerificationPage;
		 homePage = PageFactory.initElements(driver, HomePage.class); 
		 loginPage = PageFactory.initElements(driver,LoginPage.class);
		CustomerVerificationPage = PageFactory.initElements(driver, CustomerVerificationPage.class);
		loginPage.validLogin(UserName, Password);

		wait for customer details category and expand the personal details


		wait for customer details category and expand the personal details
	    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
	    wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.PersonalDetails));
	 //  if(CustomerVerificationPage.PersonalDetails.isEnabled()) {



	       Thread.sleep(2000);
	       if (!CustomerVerificationPage.PrsnlDtlsChkbox.isSelected()){
	    	   CustomerVerificationPage.PrsnlDtlsChkbox.click();
	       Reporter.log("Address Details Category Checked",true);
	       Thread.sleep(1000);
	       }

	    wait for full name in personal details

	    wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.FullName));
	     if(CustomerVerificationPage.FullName.isEnabled()){
	    	 CustomerVerificationPage.FullName.clear();
	    	 CustomerVerificationPage.FullName.sendKeys(fullName);
	        Reporter.log("Address Details Category Checked",true);
	        Thread.sleep(1000);
	     }else {
	           Reporter.log("Submit button is disabled",true); 
	           Thread.sleep(2000); 
	       }

	    wait for full email id in personal details
	    wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.PersonalEmailId));
	    CustomerVerificationPage.PersonalEmailId.clear();
	    CustomerVerificationPage.PersonalEmailId.sendKeys(personalEmailId);

	    wait for education qualification in personal details
	    wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.EducationType));
	    //CustomerVerificationPage.EducationType.clear();
	    Select select_status = new Select(CustomerVerificationPage.EducationType);
	       // select_status.deselectByValue();
             select_status.selectByVisibleText("Select...");
	         select_status.selectByVisibleText(EduQualification);
	         Reporter.log("Education qualification is Selected",true);



	    wait for full  in personal details
	    wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.MobileNumber));
	    System.out.println("waiting for mobile number field");
	    Thread.sleep(5000);
	    CustomerVerificationPage.MobileNumber.clear();
	    CustomerVerificationPage.MobileNumber.sendKeys(MobileNum);

	    wait for full  in personal details
	    wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.LandlineNumber));
	    CustomerVerificationPage.LandlineNumber.clear();
	    CustomerVerificationPage.LandlineNumber.sendKeys(LandlineNo);
	    Reporter.log("Personal details entered successfully",true);
	  //Scroll the Page Down
	      Actions action = new Actions(driver);
	      Thread.sleep(5000);
	      action.sendKeys(Keys.PAGE_DOWN);
	      Thread.sleep(2000);
	      action.click(CustomerVerificationPage.pagedwn).perform();
	      Thread.sleep(2000);
	      System.out.println(" Page Scrolled Down");

	  //Click the checkbox for PAN Details Category
	    CustomerVerificationPage.PanDetails.click();
	    Thread.sleep(2000);
	    if (!CustomerVerificationPage.PanDtlsChkbox.isSelected()){
	    	CustomerVerificationPage.PanDtlsChkbox.click();
	    Reporter.log("PAN Details Category Checked",true);
	    Thread.sleep(2000);
	    }
	    wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.custCibilScore));
	    Thread.sleep(5000);
	     if(CustomerVerificationPage.custCibilScore.isEnabled()){
	    	 CustomerVerificationPage.custCibilScore.clear();
	    	 CustomerVerificationPage.custCibilScore.sendKeys(CustCibilScore);
	        Reporter.log("Pan Details Category Checked",true);
	        Thread.sleep(1000);
	     }else {
	           Reporter.log("Submit button is disabled",true); 
	           Thread.sleep(5000); 
	       }

	   //Scroll the Page Down
	      Actions action1 = new Actions(driver);
	      Thread.sleep(5000);
	      action1.sendKeys(Keys.PAGE_DOWN);
	      Thread.sleep(2000);
	      action1.click(CustomerVerificationPage.pagedwn).perform();
	      Thread.sleep(2000);



	     // System.out.println(" Page Scrolled Down");
	    //verify Verify Company checkbox 
	      CustomerVerificationPage.CompanyDetails.click();  
	      Thread.sleep(5000);
	        wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.CmpnyDtlsChkbox));
	        if(!CustomerVerificationPage.CmpnyDtlsChkbox.isSelected()){
	        	CustomerVerificationPage.CmpnyDtlsChkbox.click();
	        Reporter.log("Verify Company Checked",true);
	          Thread.sleep(1000);
	         }

	        wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.Verifycompany));
	        Thread.sleep(5000);
		     if(CustomerVerificationPage.Verifycompany.isEnabled()){
		    	 CustomerVerificationPage.Verifycompany.clear();
		    	 CustomerVerificationPage.Verifycompany.sendKeys(VerifyCompanyName);
		        Reporter.log("Verify Company Details",true);
		        Thread.sleep(1000);
		     }else {
		           Reporter.log("Submit button is disabled",true); 
		           Thread.sleep(2000); 
		       }

		     //Scroll the Page Down
		      Actions action2 = new Actions(driver);
		      Thread.sleep(2000);
		      action1.sendKeys(Keys.PAGE_DOWN);
		      Thread.sleep(2000);
		      action1.click(CustomerVerificationPage.pagedwn).perform();
		      Thread.sleep(2000);


		    //Click the checkbox for Address Details Category
		     CustomerVerificationPage.AddressDetails.click();
			  Thread.sleep(2000);
		    wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.AddrDtlsChkbox));
		   if (!CustomerVerificationPage.AddrDtlsChkbox.isSelected()){
			   CustomerVerificationPage.AddrDtlsChkbox.click();
		   Reporter.log("Address Details Category Checked",true);
		   Thread.sleep(1000);
		   }

		 //Scroll the Page Down
		      Actions action3 = new Actions(driver);
		      Thread.sleep(2000);
		      action1.sendKeys(Keys.PAGE_DOWN);
		      Thread.sleep(2000);
		      action1.click(CustomerVerificationPage.pagedwn).perform();
		      Thread.sleep(2000);


		    //Click the checkbox for Bank Details Category
		      CustomerVerificationPage.BankDetails.click();
		      wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.BnkDtlsChkbox));
		      if (!CustomerVerificationPage.BnkDtlsChkbox.isSelected()){
		    	  CustomerVerificationPage.BnkDtlsChkbox.click();
		    	  System.out.println("Succeccfully checked bank details");
		      //Reporter.log("Bank Details Category Checked",true);
		      Thread.sleep(1000);
		      }

				 //Scroll the Page Down
		      Actions action4 = new Actions(driver);
		      Thread.sleep(2000);
		      action1.sendKeys(Keys.PAGE_DOWN);
		      Thread.sleep(2000);
		      action1.click(CustomerVerificationPage.pagedwn).perform();
		      Thread.sleep(2000);



		      //Click the checkbox for Other Details Category
		      CustomerVerificationPage.OtherDetails.click();
		      wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.OthrDtlsChkbox));
		      if (!CustomerVerificationPage.OthrDtlsChkbox.isSelected()){
		    	  CustomerVerificationPage.OthrDtlsChkbox.click();
		    	  System.out.println("successfully checked other details");
		      //Reporter.log("Other Details Category Checked",true);


		   // Click Status for Verified
		      wait.until(ExpectedConditions.visibilityOf(CustomerVerificationPage.Status));
		      Thread.sleep(5000);
		      Select select_status1 = new Select(CustomerVerificationPage.Status);
		      select_status1.selectByVisibleText(CusStatus);
		      Reporter.log("Status Selected",true);
		      Thread.sleep(1000);
		   }



		//return submitStatus;
		//return ("Login Successful");

	   }
	   else{
		   Reporter.log("Personal details entered not successfully",true); 
	  // }else{
	  //   Reporter.log("Personal details category is not expanded",true); 

	 //  }
	//return submitStatus;

	}*/
	//CompanyListPage companylist;

	/*public void AddORRemoveblacklist(String ButtonType,String CompanyName,String ExpCompanyColorCode) throws InterruptedException{
	Wait<WebDriver> wait = new WebDriverWait(driver, 100); 
	if (ButtonType.equalsIgnoreCase("Addtoblocklistbutton")){
		//Get company black list status before adding black list in UI
		   wait.until(ExpectedConditions.visibilityOf(companylist.blackList));
		   String BeforeCompanyStatus = companylist.blackList.getText();
		   String ExpectedCompanyStatus = "No";			 
		   Assert.assertEquals(BeforeCompanyStatus, ExpectedCompanyStatus);
		   Reporter.log("Before adding black list to "+ CompanyName +" black lis status is "+ BeforeCompanyStatus,true);
		   //Click on add black list button
			wait.until(ExpectedConditions.visibilityOf(companylist.btnAddtoblocklist));
			companylist.btnAddtoblocklist.click();				
			wait.until(ExpectedConditions.visibilityOf(companylist.flagAddsuccess));
			 String ActualSuccessMsg = companylist.flagAddsuccess.getText();
			 String Expmsg = "Company Added To The Black List Successfully";
			 Assert.assertEquals(ActualSuccessMsg, Expmsg);
			 Reporter.log(CompanyName +" "+ ActualSuccessMsg,true);    
			 Thread.sleep(5000);
		      wait.until(ExpectedConditions.visibilityOf(companylist.Successcloseflag));
		      companylist.Successcloseflag.click();
		 	  Thread.sleep(10000);
		    //Get company black list status before adding black list
			wait.until(ExpectedConditions.visibilityOf(companylist.afterblacklist));
			String AfterCompanyStatus = companylist.afterblacklist.getText();	
			Reporter.log("After adding black list to" + CompanyName +"black lis status is"+ AfterCompanyStatus,true);
			String color = companylist.afterblacklist.getCssValue("background-color");
			String colorCode = Color.fromString(color).asHex();	

			Reporter.log(CompanyName+" company color code in hex formate is: "+colorCode,true);
			Reporter.log(CompanyName+" company color code in rgba formate is: "+color,true);
			Assert.assertTrue(colorCode.equals(ExpCompanyColorCode));;


	}else{
		if(ButtonType.equalsIgnoreCase("Removefromblocklistbutton")){
		//Get company black list status before adding black list in UI
		   wait.until(ExpectedConditions.visibilityOf(companylist.afterblacklist));
		   String BeforeCompanyStatus = companylist.afterblacklist.getText();
		   Reporter.log("Before removing black list to "+ CompanyName +" black lis status is "+ BeforeCompanyStatus,true);
		   wait.until(ExpectedConditions.visibilityOf(companylist.btnRemoveblocklist));
		   companylist.btnRemoveblocklist.click();


		   //Click on add black list button
			wait.until(ExpectedConditions.visibilityOf(companylist.btnRemoveblocklist));
			companylist.btnRemoveblocklist.click();

			wait.until(ExpectedConditions.visibilityOf(companylist.flagRemovesuccess));
			 String ActualSuccessMsg = companylist.flagRemovesuccess.getText();
			 Reporter.log("message:"+ActualSuccessMsg,true);
			 String Expmsg = "Removed The Company From  Black List Successfully";
			 //if(ActualSuccessMsg.contains(Expmsg)){
			 if(ActualSuccessMsg.equalsIgnoreCase(Expmsg)){
				 Reporter.log("Removed The Company: "+ CompanyName +" From  Black List Successfully",true);  
		         wait.until(ExpectedConditions.visibilityOf(companylist.Successcloseflag));
		         companylist.Successcloseflag.click();
			 }else{
				 Reporter.log("Failed to remove black list",true);
			 }
			//Get company black list status before adding black list
				wait.until(ExpectedConditions.visibilityOf(companylist.blackList));
				String AfterCompanyStatus = companylist.blackList.getText();
				Reporter.log("After removing black list to" + CompanyName +"black lis status is"+ BeforeCompanyStatus );
				String color = companylist.blackList.getCssValue("background-color");
				Reporter.log(color,true);
				String colorCode = Color.fromString(color).asHex();
				Reporter.log(colorCode,true);
				Assert.assertTrue(color.equals(ExpCompanyColorCode));;
		}
	}
}
String Actprotype;
CustomerVerificationPage CustVerification;
public void EditCustSal(String tabType,String Customerid,String Expprotype,String Expmaxeligibility,String CustSal,String DBCustSalary) throws InterruptedException{

	 String ProductType = "RETAIL_15";
	 String ProductType1 = "RETAIL_30";
	 String ProductType2 = "RETAIL_90";
	 String ProductType3 = "RETAIL_180";
	 String ProductType4 = "RETAIL_90_PLUS_30 ";
	 String ProductType5 = "RETAIL_30_PLUS_30 ";
	 //Initiate customer validation elements
	 CustVerification = PageFactory.initElements(driver, CustomerVerificationPage.class);

	 Wait<WebDriver> wait = new WebDriverWait(driver, 30); 
	 //Navigate to customer validation screen and click on IMEI Continue button
	 NavigateCusValidationScreen(tabType,Customerid);
	 //Get product type from UI 
	 if(ProductType.equalsIgnoreCase(Expprotype))
	 {
	     Actprotype = CustVerification.Producttype.getText();
	     Reporter.log("Product type is: "+Actprotype, true);

	     //Compare product type from UI
         Assert.assertEquals(Actprotype,Expprotype);
	     //Edit salary based on product type         
         if(Actprotype.trim().equalsIgnoreCase(Expprotype))
         {
    	   SlaryEdit_of_Producttype(Customerid,Expprotype,Expmaxeligibility,CustSal,DBCustSalary);
    	   Reporter.log("Successfully update salary is: "+CustSal+" for product type: "+Actprotype,true);
         }
	 }else if(ProductType1.equalsIgnoreCase(Expprotype)){
		    Actprotype = CustVerification.Producttype30.getText();
		    Reporter.log("Product type is: "+Actprotype, true); 
		    //Compare product type from UI
	         Assert.assertEquals(Actprotype,Expprotype);
		     //Edit salary based on product type         
	         if(Actprotype.trim().equalsIgnoreCase(Expprotype))
	         {
	    	   SlaryEdit_of_Producttype(Customerid,Expprotype,Expmaxeligibility,CustSal,DBCustSalary);
	    	   Reporter.log("Successfully update salary is: "+CustSal+" for product type: "+Actprotype,true);
	         }
	 }else if(ProductType2.equalsIgnoreCase(Expprotype)){
		    Actprotype = CustVerification.Producttype90.getText();
		    Reporter.log("Product type is: "+Actprotype, true); 
		    //Compare product type from UI
	         Assert.assertEquals(Actprotype,Expprotype);
		     //Edit salary based on product type         
	         if(Actprotype.trim().equalsIgnoreCase(Expprotype))
	         {
	    	   SlaryEdit_of_Producttype(Customerid,Expprotype,Expmaxeligibility,CustSal,DBCustSalary);
	    	   Reporter.log("Successfully update salary is: "+CustSal+" for product type: "+Actprotype,true);
	         }
	 }else if(ProductType3.equalsIgnoreCase(Expprotype)){
		    Actprotype = CustVerification.Producttype180.getText();
		    Reporter.log("Product type is: "+Actprotype, true); 
		    //Compare product type from UI
	         Assert.assertEquals(Actprotype,Expprotype);
		     //Edit salary based on product type         
	         if(Actprotype.trim().equalsIgnoreCase(Expprotype))
	         {
	    	   SlaryEdit_of_Producttype(Customerid,Expprotype,Expmaxeligibility,CustSal,DBCustSalary);
	    	   Reporter.log("Successfully update salary is: "+CustSal+" for product type: "+Actprotype,true);
	         }

	 }else if(ProductType4.equalsIgnoreCase(Expprotype)){
		    Actprotype = CustVerification.Producttype90pluse30.getText();
		    Reporter.log("Product type is: "+Actprotype, true); 
		    //Compare product type from UI
	         Assert.assertEquals(Actprotype,Expprotype);
		     //Edit salary based on product type         
	         if(Actprotype.trim().equalsIgnoreCase(Expprotype))
	         {
	    	   SlaryEdit_of_Producttype(Customerid,Expprotype,Expmaxeligibility,CustSal,DBCustSalary);
	    	   Reporter.log("Successfully update salary is: "+CustSal+" for product type: "+Actprotype,true);
	         }

	 }else if(ProductType5.equalsIgnoreCase(Expprotype)){
		    Actprotype = CustVerification.Producttype30pluse30.getText();
		    Reporter.log("Product type is: "+Actprotype, true); 
		    //Compare product type from UI
	         Assert.assertEquals(Actprotype,Expprotype);
		     //Edit salary based on product type         
	         if(Actprotype.trim().equalsIgnoreCase(Expprotype))
	         {
	    	   SlaryEdit_of_Producttype(Customerid,Expprotype,Expmaxeligibility,CustSal,DBCustSalary);
	    	   Reporter.log("Successfully update salary is: "+CustSal+" for product type: "+Actprotype,true);
	         }
	 }
}
public void SlaryEdit_of_Producttype(String Customerid,String Expprotype,String Expmaxeligibility,String CustSal,String DBCustSalary) throws InterruptedException{
	String Maxeligibility = null;
	   //verify Verify Company checkbox 
    CustVerification.CompanyDetails.click();  
	Thread.sleep(5000);
	 Wait<WebDriver> wait = new WebDriverWait(driver, 30); 
	wait.until(ExpectedConditions.visibilityOf(CustVerification.CmpnyDtlsChkbox));
	//check the company details check box
	if(!CustVerification.CmpnyDtlsChkbox.isSelected())
	{
	       CustVerification.CmpnyDtlsChkbox.click();
	       Reporter.log("Verify Company Checked",true);
	       Thread.sleep(1000);
	 }
	//get Customer monthly income
	 wait.until(ExpectedConditions.visibilityOf(CustVerification.UIMonthlyincome));
	 String ActUiMonthlyincome = CustVerification.UIMonthlyincome.getText();
	 String[] UImonthlyincome = ActUiMonthlyincome.split(" ");
	 Reporter.log("Customer monthly income is: "+UImonthlyincome[0],true);
	 String UICustMnthlyIncome = UImonthlyincome[0].trim()+".00";
	 String DBCustmonthlySalary = DBCustSalary.trim(); 
	 //compare Customer monthly income in DB and UI
	 Assert.assertEquals(UICustMnthlyIncome,DBCustmonthlySalary);
	  //Click on Edit salary  button    
	 wait.until(ExpectedConditions.visibilityOf(CustVerification.CustEditSal));
	 CustVerification.CustEditSal.click();
	 Reporter.log("Clicked on customer salary edit", true);

	 //Get max eligibility amount based on product type from UI 
	 if(Actprotype.equalsIgnoreCase("RETAIL_15"))
	 {
		 System.out.println("bsadjs15");
	    Maxeligibility = CustVerification.Maxeligibility.getText(); 
	    Reporter.log("Customer id:"+Customerid+" and max eligibility is: "+Maxeligibility+" of product type: "+Actprotype, true); 
  	 }else if(Actprotype.equalsIgnoreCase("RETAIL_30")){
		 Maxeligibility = CustVerification.Maxeligibility_30day.getText();
		 Reporter.log("Customer id:"+Customerid+" and max eligibility is: "+Maxeligibility+" of product type: "+Actprotype, true); 
	 }else if(Actprotype.equalsIgnoreCase("RETAIL_90")){
		 Maxeligibility = CustVerification.Maxeligibility_90day.getText();
		 Reporter.log("Customer id:"+Customerid+" and max eligibility is: "+Maxeligibility+" of product type: "+Actprotype, true); 
	 }else if(Actprotype.equalsIgnoreCase("RETAIL_180")){
		 Maxeligibility = CustVerification.Maxeligibility_180day.getText();
		 Reporter.log("Customer id:"+Customerid+" and max eligibility is: "+Maxeligibility+" of product type: "+Actprotype, true);
	 }else if(Actprotype.equalsIgnoreCase("RETAIL_30_PLUS_30")){
		 Maxeligibility = CustVerification.Maxeligibility_30_PLUS_30day.getText();
		 Reporter.log("Customer id:"+Customerid+" and max eligibility is: "+Maxeligibility+" of product type: "+Actprotype, true);
	 }else if(Actprotype.equalsIgnoreCase("RETAIL_90_PLUS_30")){
		 Maxeligibility = CustVerification.Maxeligibility_90_PLUS_30day.getText();
		 Reporter.log("Customer id:"+Customerid+" and max eligibility is: "+Maxeligibility+" of product type: "+Actprotype, true);
	 }
	 String CustMaxEli = Maxeligibility;

	 //Split string and get product type and max eligibility amount
	 String[] splitstring = CustMaxEli.split(" ");
	 String protype = splitstring[6];
	 Assert.assertEquals(protype,Expprotype);
	 String maxeligibilityamt = splitstring[8];
	 String[] Eligibilityamt = maxeligibilityamt.split("[a-zA-Z ]+");
	 String Eligibility = Eligibilityamt[0].trim();	
	 //Compare eligibility amount
	 Assert.assertEquals(Eligibility,Expmaxeligibility);
	 //Edit salary based on product type
	 if(Eligibility.equalsIgnoreCase(Expmaxeligibility))
	 {
		 //Enter salary
	     wait.until(ExpectedConditions.visibilityOf(CustVerification.CustSal));
		 CustVerification.CustSal.clear();
		 CustVerification.CustSal.sendKeys(CustSal);
		 //Click on slary edit yes button       
		 wait.until(ExpectedConditions.visibilityOf(CustVerification.BtnYessalaryedit));
		 CustVerification.BtnYessalaryedit.click();
		 //Click on confirm the update salary       
		 wait.until(ExpectedConditions.visibilityOf(CustVerification.BtnSalaryconfirm));
		 CustVerification.BtnSalaryconfirm.click();
		 //Click on final close button       
		 wait.until(ExpectedConditions.visibilityOf(CustVerification.BtnFinalClose));
		 CustVerification.BtnFinalClose.click();	 
	   }



}*/
	public void waitTillLoadingSymbleGo(WebElement loadingSymbol) throws InterruptedException{
		try {
			Thread.sleep(3000);
        String spinner = loadingSymbol.getAttribute("style");
        System.out.println("spinner ::"+spinner);
        int count = 0;
        while(!spinner.contains("none")){
               System.out.println(" In spinner while ::"+count);
               Thread.sleep(5000);
               count++;
               System.out.println("Page is loading");
               if (count == 100)
                     break;
               else
                     spinner = loadingSymbol.getAttribute("style");
              
        }
        System.out.println("Page is loadingggggggggggggggggggggggg");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("No spinner");
		}
 }

	public   void  NavigateCusValidationScreen(String tabType,String Customerid) throws InterruptedException{

		String Actprotype;
		AdminFlowPageFactory CustVerification;
		CustVerification = PageFactory.initElements(driver, AdminFlowPageFactory.class);
		Wait<WebDriver> wait = new WebDriverWait(driver, 30); 
		wait.until(ExpectedConditions.visibilityOf(CustVerification.toggleNavigation));
		CustVerification.toggleNavigation.click();
		Reporter.log("Toggle Navigation Clicked", true);

		// click on Customers tab
		wait.until(ExpectedConditions.elementToBeClickable(CustVerification.customersTab));
		Thread.sleep(5000);
		CustVerification.customersTab.click();
		Reporter.log("Customer Tab Clicked", true);

		// Click on CustomerValidation Tab
		wait.until(ExpectedConditions.visibilityOf(CustVerification.custValidationTab));
		Thread.sleep(5000);
		CustVerification.custValidationTab.click();
		Reporter.log("Customer Validation Clicked", true);
		
		waitTillLoadingSymbleGo(CustVerification.loadingSymbol);

		// click on toggle
		wait.until(ExpectedConditions.elementToBeClickable(CustVerification.toggleNavigation));
		Thread.sleep(5000);
		CustVerification.toggleNavigation.click();
		Reporter.log("Toggle Navigation Clicked", true);
		waitTillLoadingSymbleGo(CustVerification.loadingSymbol);

		Thread.sleep(2000);
		tabType = CustVerification.tabSearch.getText();
		if (tabType.equalsIgnoreCase("Search")) {
			//Click on search tab

			wait.until(ExpectedConditions.visibilityOf(CustVerification.tabSearch));
			Thread.sleep(3000);
			CustVerification.tabSearch.click();


			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(CustVerification.customerid));
			CustVerification.customerid.sendKeys(Customerid); 
			//click on search button
			wait.until(ExpectedConditions.visibilityOf(CustVerification.btnSearch));
			CustVerification.btnSearch.click();
			Reporter.log("Clicked on Search button",true);


			waitTillLoadingSymbleGo(CustVerification.loadingSymbol);


			// Click on 1st Row and 1st Column of the Customer in the Table
			wait.until(ExpectedConditions.visibilityOf(CustVerification.VerifyDots));
			CustVerification.VerifyDots.click();
			Reporter.log("Clicked Dots ", true);

			// Clicked Verify
			wait.until(ExpectedConditions.visibilityOf(CustVerification.verifyBtn));
			CustVerification.verifyBtn.click();
			Reporter.log("Clicked Verify Button", true);

			Thread.sleep(3000);
			waitTillLoadingSymbleGo(CustVerification.loadingSymbol);

			// Click IMEI
			wait.until(ExpectedConditions.visibilityOf(CustVerification.IMEI));          
			int IMEICount = Integer.parseInt(CustVerification.IMEI.getText().charAt(CustVerification.IMEI.getText().length()-1)+"");

			//Click Continue
			wait.until(ExpectedConditions.visibilityOf(CustVerification.ContinueBtn));
			CustVerification.ContinueBtn.click();


		}
	}


	/*public void NavigatetoCustValidationSearchActionView(String TabName, String Customerid) throws InterruptedException{
	 CustomerVerificationPage custValidationPage;
	 custValidationPage = PageFactory.initElements(driver, CustomerVerificationPage.class);

	 Wait<WebDriver> wait = new WebDriverWait(driver, 90); 

	 // click on toggle
	 wait.until(ExpectedConditions.visibilityOf(custValidationPage.toggleNavigation));
	 custValidationPage.toggleNavigation.click();
	 Reporter.log("Toggle Navigation Clicked", true);


	 // click on Customers tab
	 wait.until(ExpectedConditions.visibilityOf(custValidationPage.customersTab));
	 custValidationPage.customersTab.click();
	 Reporter.log("Customer Tab Clicked", true);


	 // Click on CustomerValidation Tab
	 wait.until(ExpectedConditions.visibilityOf(custValidationPage.custValidationTab));
	 custValidationPage.custValidationTab.click();
	 Reporter.log("Customer Validation Clicked", true);
	 // click on toggle
	 wait.until(ExpectedConditions.visibilityOf(custValidationPage.toggleNavigation));
	 custValidationPage.toggleNavigation.click();
	 Reporter.log("Toggle Navigation Clicked", true);
	 Thread.sleep(5000);



	 if (TabName.equalsIgnoreCase("Search")){
	  // Click on Search tab 
	  wait.until(ExpectedConditions.visibilityOf(custValidationPage.tabSearch));
	  custValidationPage.tabSearch.click();
	  Reporter.log("Clicked on Search tab",true);

	  // entering customer id in text box 
	  wait.until(ExpectedConditions.visibilityOf(custValidationPage.customerid));
	  custValidationPage.customerid.sendKeys(Customerid);
	  Reporter.log("Entered Customer ID " + Customerid ,true);

	  // Click on Search button
	  wait.until(ExpectedConditions.visibilityOf(custValidationPage.btnSearch));
	  custValidationPage.btnSearch.click();
	  Reporter.log("Clicked on Search button",true);

	  // Click on Action dots
	  wait.until(ExpectedConditions.visibilityOf(custValidationPage.VerifyDots));
	  custValidationPage.VerifyDots.click();
	  Reporter.log("Clicked on action Dots ", true);

	  //Click on view button
	  wait.until(ExpectedConditions.visibilityOf(custValidationPage.ActionView));
	  custValidationPage.ActionView.click();
	  Reporter.log("Clicked on Action view button ", true);

	 }else if(TabName.equalsIgnoreCase("MyCases")){
	  // Click on Search tab 
	    wait.until(ExpectedConditions.visibilityOf(custValidationPage.tabMyCases));
	    custValidationPage.tabMyCases.click();
	    Reporter.log("Clicked on My cases tab",true);

	    // entering customer id in Filter 
	       wait.until(ExpectedConditions.visibilityOf(custValidationPage.SearchFilter));
	       custValidationPage.SearchFilter.sendKeys(Customerid);
	       Reporter.log("Entered Customer ID " + Customerid ,true);

	    // Click on Action dots
	    wait.until(ExpectedConditions.visibilityOf(custValidationPage.VerifyDots));
	    custValidationPage.VerifyDots.click();
	    Reporter.log("Clicked on action Dots ", true);

	    //Click on view button
	    wait.until(ExpectedConditions.visibilityOf(custValidationPage.ActionView));
	    custValidationPage.ActionView.click();
	    Reporter.log("Clicked on Action view button ", true);
	 }



	}

public void  Navigatetocusvalidationscreen() throws InterruptedException {
	  LoginPage loginPage;
	  CustomerVerificationPage cstVdtnMycasesPage;

cstVdtnMycasesPage = PageFactory.initElements(driver, CustomerVerificationPage.class);
Wait<WebDriver> wait = new WebDriverWait(driver, 90); 

// click on toggle
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.toggleNavigation));
cstVdtnMycasesPage.toggleNavigation.click();
Reporter.log("Toggle Navigation Clicked", true);

// click on Customers tab
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.customersTab));
cstVdtnMycasesPage.customersTab.click();
Reporter.log("Customer Tab Clicked", true);

// Click on CustomerValidation Tab
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custValidationTab));
cstVdtnMycasesPage.custValidationTab.click();
Reporter.log("Customer Validation Clicked", true);

Thread.sleep(2000);

WebElement tableElement1 = cstVdtnMycasesPage.tableMyCases;

// To locate rows of table.
List<WebElement> tableRows = tableElement1.findElements(By.tagName("tr"));

// To calculate no of rows In table.
int rows_count = tableRows.size();
//java.lang.String Cstrow;
// System.out.println(Cstrow = " Number of rows " + rows_count);

// Loop will execute for all the rows of the table
for (int row = 0; row < rows_count; row++) {

       // To locate columns(cells) of that specific row.
       List<WebElement> tableColumns = tableRows.get(row).findElements(By.tagName("td"));

       // To calculate no of columns(cells) In that specific row.
       int columns_count = tableColumns.size();
       //java.lang.String Cstcolumn;
     //  System.out.println(Cstcolumn = "Number of cells In Row " + row + " are " + columns_count);

       // Loop will execute till the last cell of that specific row.

       List<String> custData = new ArrayList<String>();
       for (int column = 0; column < columns_count; column++) {

             java.lang.String Csttext = tableColumns.get(column).getText();
             custData.add(column, Csttext);
             //System.out.println(Csttext);

       }

       for (int i = 0; i < custData.size(); i++) {
             //System.out.println("List of column values  .....");
             //System.out.println(custData.get(i));
       }
}

// click on toggle
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.toggleNavigation));
cstVdtnMycasesPage.toggleNavigation.click();
Reporter.log("Toggle Navigation Clicked", true);

// Click on 1st Row and 1st Column of the Customer in the Table
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.VerifyDots));
cstVdtnMycasesPage.VerifyDots.click();
Reporter.log("Clicked Dots ", true);

// Clicked Verify
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.verifyBtn));
cstVdtnMycasesPage.verifyBtn.click();
Reporter.log("Clicked Verify Button", true);

Thread.sleep(3000);

// Click IMEI
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.IMEI));          
int IMEICount = Integer.parseInt(cstVdtnMycasesPage.IMEI.getText().charAt(cstVdtnMycasesPage.IMEI.getText().length()-1)+"");


       //Click Continue
       wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.ContinueBtn));
       cstVdtnMycasesPage.ContinueBtn.click();
       Reporter.log("Clicked Continue Button", true);
      // Thread.sleep(2000);

}


public void  PANDts(String PAN,String CustCibilScore,String VerifyCompanyName,String CusStatus) throws Exception{
	CustomerVerificationPage cstVdtnMycasesPage;

    // initialize page elements
     cstVdtnMycasesPage = PageFactory.initElements(driver, CustomerVerificationPage.class);
	  Wait<WebDriver> wait = new WebDriverWait(driver, 30); 

	  Thread.sleep(2000);
      if (!cstVdtnMycasesPage.PrsnlDtlsChkbox.isSelected()){
    	  cstVdtnMycasesPage.PrsnlDtlsChkbox.click();
      Reporter.log("Address Details Category Checked",true);
      Thread.sleep(1000);
      }     
      wait for customer details category and expand the PAN details
      cstVdtnMycasesPage.PanDetails.click(); 
    wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.PanDetails));
    if(cstVdtnMycasesPage.PanDetails.isEnabled()) {
      //CustomerVerificationPage.PanDetails.click();

      //Click the checkbox for PAN Details Category
      //CustomerVerificationPage.PanDetails.click();
        Thread.sleep(2000);
        if (!cstVdtnMycasesPage.PanDtlsChkbox.isSelected()){
           cstVdtnMycasesPage.PanDtlsChkbox.click();
        Reporter.log("PAN Details Category Checked",true);
        Thread.sleep(1000);
        }

      wait for PAN in PAN details
       wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.PAN));
      cstVdtnMycasesPage.PAN.clear();
       if(cstVdtnMycasesPage.PAN.isEnabled()){
              cstVdtnMycasesPage.PAN.sendKeys(PAN);
          Reporter.log("PAN Details Category Checked",true);
          Thread.sleep(1000);
       }else {
             Reporter.log("Submit button is disabled",true); 
             Thread.sleep(2000); 
         }

      wait for Cibil Score in PAN details
      wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custCibilScore));
      cstVdtnMycasesPage.custCibilScore.clear();
      cstVdtnMycasesPage.PersonalEmailId.sendKeys(CustCibilScore);


     }else{
       Reporter.log("PAN  details category is not expand",true); 

     }

    Actions action1 = new Actions(driver);
    Thread.sleep(5000);
    action1.sendKeys(Keys.PAGE_DOWN);
    Thread.sleep(2000);
    action1.click(cstVdtnMycasesPage.pagedwn).perform();
    Thread.sleep(2000);



   // System.out.println(" Page Scrolled Down");
  //verify Verify Company checkbox 
    cstVdtnMycasesPage.CompanyDetails.click();  
    Thread.sleep(5000);
      wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.CmpnyDtlsChkbox));
      if(!cstVdtnMycasesPage.CmpnyDtlsChkbox.isSelected()){
    	  cstVdtnMycasesPage.CmpnyDtlsChkbox.click();
      Reporter.log("Verify Company Checked",true);
        Thread.sleep(1000);
       }

      wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Verifycompany));
      Thread.sleep(5000);
	     if(cstVdtnMycasesPage.Verifycompany.isEnabled()){
	    	 cstVdtnMycasesPage.Verifycompany.clear();
	    	 cstVdtnMycasesPage.Verifycompany.sendKeys(VerifyCompanyName);
	        Reporter.log("Verify Company Details",true);
	        Thread.sleep(1000);
	     }else {
	           Reporter.log("Submit button is disabled",true); 
	           Thread.sleep(2000); 
	       }

	     //Scroll the Page Down
	      Actions action2 = new Actions(driver);
	      Thread.sleep(2000);
	      action1.sendKeys(Keys.PAGE_DOWN);
	      Thread.sleep(2000);
	      action1.click(cstVdtnMycasesPage.pagedwn).perform();
	      Thread.sleep(2000);


	    //Click the checkbox for Address Details Category
	      cstVdtnMycasesPage.AddressDetails.click();
		  Thread.sleep(2000);
	    wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.AddrDtlsChkbox));
	   if (!cstVdtnMycasesPage.AddrDtlsChkbox.isSelected()){
		   cstVdtnMycasesPage.AddrDtlsChkbox.click();
	   Reporter.log("Address Details Category Checked",true);
	   Thread.sleep(1000);
	   }

	 //Scroll the Page Down
	      Actions action3 = new Actions(driver);
	      Thread.sleep(2000);
	      action1.sendKeys(Keys.PAGE_DOWN);
	      Thread.sleep(2000);
	      action1.click(cstVdtnMycasesPage.pagedwn).perform();
	      Thread.sleep(5000);


	    //Click the checkbox for Bank Details Category
	      cstVdtnMycasesPage.BankDetails.click();
	      wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.BnkDtlsChkbox));
	      if (!cstVdtnMycasesPage.BnkDtlsChkbox.isSelected()){
	    	  cstVdtnMycasesPage.BnkDtlsChkbox.click();
	    	  System.out.println("Succeccfully checked bank details");
	      //Reporter.log("Bank Details Category Checked",true);
	      Thread.sleep(1000);
	      }

			 //Scroll the Page Down
	      Actions action4 = new Actions(driver);
	      Thread.sleep(2000);
	      action1.sendKeys(Keys.PAGE_DOWN);
	      Thread.sleep(2000);
	      action1.click(cstVdtnMycasesPage.pagedwn).perform();
	      Thread.sleep(2000);



	      //Click the checkbox for Other Details Category
	      cstVdtnMycasesPage.OtherDetails.click();
	      wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.OthrDtlsChkbox));
	      if (!cstVdtnMycasesPage.OthrDtlsChkbox.isSelected()){
	    	  cstVdtnMycasesPage.OthrDtlsChkbox.click();
	    	  System.out.println("successfully checked other details");
	      //Reporter.log("Other Details Category Checked",true);


	   // Click Status for Verified
	      wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Status));
	      Thread.sleep(5000);
	      Select select_status1 = new Select(cstVdtnMycasesPage.Status);
	      select_status1.selectByVisibleText(CusStatus);
	      Reporter.log("Status Selected",true);
	      Thread.sleep(1000);
	   }



	//return submitStatus;
	//return ("Login Successful");

 }
 else{
	   Reporter.log("Personal details entered not successfully",true); 
// }else{
//   Reporter.log("Personal details category is not expanded",true); 

//  }
//return submitStatus;


}
CustomerSupportPage Custsuppage;

public  void EnterPDCDts(String ChequeNo,String Amount,String Dtoncheque,String BankName,String IFSC,String BankAccNo,String StatusDt,String Status) throws InterruptedException{

	//Custsuppage = PageFactory.initElements(driver, CustomerSupport.class);
	String Expmessage = "This Customer Is Not Marked as PDC Required.";
	String ProductType = driver.findElement(By.xpath("//th[2][contains(text(),'Product Type')]")).getText();
	System.out.println(ProductType1);
	String ProductType2 = driver.findElement(By.xpath("//th[2][contains(text(),'Product Type')]")).getText();
	System.out.println(ProductType2);
	String ProductType3 = driver.findElement(By.xpath("//th[2][contains(text(),'Product Type')]")).getText();
	System.out.println(ProductType3);
	//Dimension count = Custsuppage.formscount.getSize();

	String ActMessage = driver.findElement(By.xpath("//div[contains(text(),'This Customer Is Not Marked as PDC Required.')]")).getText();	
	if(ProductType == " RETAIL_90" ){
		//if(count[] == 3){
		   //if(Custsuppage.formscount.isEnabled()){
			   EnterPDCFirstFormDts(ChequeNo,Amount,Dtoncheque,BankName,IFSC,BankAccNo,StatusDt,Status);
			   EnterSecondPDCFormDts(ChequeNo,Amount,Dtoncheque,BankName,IFSC,BankAccNo,StatusDt,Status);
			   EnterThirdPDCFormDts(ChequeNo,Amount,Dtoncheque,BankName,IFSC,BankAccNo,StatusDt,Status);
		   }else{
			  System.out.println("Forms count is not equal to 3"); 
		   }

	}else if((ProductType2 == "RETAIL_30" )||(ProductType3 == "RETAIL_15" )){
		EnterPDCFirstFormDts(ChequeNo,Amount,Dtoncheque,BankName,IFSC,BankAccNo,StatusDt,Status);
		 System.out.println("Successfully entered pdc first form details ");

	}else if(ActMessage==Expmessage){
		System.out.println("Message is:"+ActMessage);	
	}else{
		System.out.println("failed enter pdc details");
	}	

}

public void EnterThirdPDCFormDts(String ThrdChequeNo,String Amount,String BankName,String IFSC,String BankAccNo,String Status) throws InterruptedException{
	// wait for First PDC form
    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOf(Custsuppage.PDCThirdrow));

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCchequeNum));
   if(Custsuppage.ThirdPDCchequeNum.isEnabled()){
              Custsuppage.ThirdPDCchequeNum.clear();
              Custsuppage.ThirdPDCchequeNum.sendKeys(ThrdChequeNo);
            Reporter.log("Successfully Enter cheque number",true);
            Thread.sleep(1000);
    }else{

           Reporter.log("Failed to enter cheque number",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCchequeamount));
   if(Custsuppage.ThirdPDCchequeamount.isEnabled()){
              Custsuppage.ThirdPDCchequeamount.clear();
              Custsuppage.ThirdPDCchequeamount.sendKeys(Amount);
            Reporter.log("Successfully Enter cheque amount",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter cheque amount",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCchequeDate));
   if(Custsuppage.ThirdPDCchequeDate.isEnabled()){
	   Custsuppage.ThirdPDCchequeDate.click();
	   Custsuppage.PDCThirdTodayButton.click();
            Reporter.log("Successfully Enter cheque on date",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter cheque on date",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCBankName));
   if(Custsuppage.ThirdPDCBankName.isEnabled()){
              Custsuppage.ThirdPDCBankName.clear();
              Custsuppage.ThirdPDCBankName.sendKeys(BankName);
            Reporter.log("Successfully Enter cheque on date",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter cheque on date",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCBankIFSC));
   if(Custsuppage.ThirdPDCBankIFSC.isEnabled()){
              Custsuppage.ThirdPDCBankIFSC.clear();
              Custsuppage.ThirdPDCBankIFSC.sendKeys(IFSC);
            Reporter.log("Successfully Enter ifsc code",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter ifsc code",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCAccNum));
   if(Custsuppage.ThirdPDCAccNum.isEnabled()){
              Custsuppage.ThirdPDCAccNum.clear();
              Custsuppage.ThirdPDCAccNum.sendKeys(BankAccNo);
            Reporter.log("Successfully Enter pdc account number",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter ifsc code",true); 
           Thread.sleep(2000); 
    }



   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCStatus));
   if(Custsuppage.ThirdPDCStatus.isEnabled()){
	   Select select_status = new Select(Custsuppage.ThirdPDCStatus);
       // select_status.deselectByValue();
         //select_status.selectByVisibleText("Select...");
         select_status.selectByVisibleText(Status);
         Reporter.log("Pdc status is Selected",true);

    }else {
           Reporter.log("Failed to select pdc status",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCReceivedDate));
   if(Custsuppage.ThirdPDCReceivedDate.isEnabled()){

		   Custsuppage.ThirdPDCReceivedDate.click();
		   //custSupPage.PDCSecondClearButton.click();
		   Custsuppage.PDCThirdTodayButton.click();

            Reporter.log("Successfully Enter pdc received date",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter pdc received date",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.ThirdPDCSave));
   Custsuppage.ThirdPDCSave.click();
   Reporter.log("Clicked third pdc save Button", true);
}

public void EnterSecondPDCFormDts(String SecChequeNo,String Amount,String BankName,String IFSC,String BankAccNo,String Status) throws InterruptedException{
	// wait for First PDC form
    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOf(Custsuppage.PDCsecondrow));

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCchequeNum));
   if(Custsuppage.SecondPDCchequeNum.isEnabled()){
              Custsuppage.SecondPDCchequeNum.clear();
              Custsuppage.SecondPDCchequeNum.sendKeys(SecChequeNo);
            Reporter.log("Successfully Enter cheque number",true);
            Thread.sleep(1000);
    }else{

           Reporter.log("Failed to enter cheque number",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCchequeamount));
   if(Custsuppage.SecondPDCchequeamount.isEnabled()){
              Custsuppage.SecondPDCchequeamount.clear();
              Custsuppage.SecondPDCchequeamount.sendKeys(Amount);
            Reporter.log("Successfully Enter cheque amount",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter cheque amount",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCchequeDate));
   if(Custsuppage.SecondPDCchequeDate.isEnabled()){
	   Custsuppage.SecondPDCchequeDate.click();
	   //custSupPage.PDCSecondClearButton.click();
	   Custsuppage.PDCSecondTodayButton.click();
            Reporter.log("Successfully Enter cheque on date",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter cheque on date",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCBankName));
   if(Custsuppage.SecondPDCBankName.isEnabled()){
              Custsuppage.SecondPDCBankName.clear();
              Custsuppage.SecondPDCBankName.sendKeys(BankName);
            Reporter.log("Successfully Enter cheque on date",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter cheque on date",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCBankIFSC));
   if(Custsuppage.SecondPDCBankIFSC.isEnabled()){
              Custsuppage.SecondPDCBankIFSC.clear();
              Custsuppage.SecondPDCBankIFSC.sendKeys(IFSC);
            Reporter.log("Successfully Enter ifsc code",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter ifsc code",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCAccNum));
   if(Custsuppage.SecondPDCAccNum.isEnabled()){
              Custsuppage.SecondPDCAccNum.clear();
              Custsuppage.SecondPDCAccNum.sendKeys(BankAccNo);
            Reporter.log("Successfully Enter pdc account number",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter ifsc code",true); 
           Thread.sleep(2000); 
    }



   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCStatus));
   if(Custsuppage.SecondPDCStatus.isEnabled()){
	   Select select_status = new Select(Custsuppage.SecondPDCStatus);
       // select_status.deselectByValue();
         //select_status.selectByVisibleText("Select...");
         select_status.selectByVisibleText(Status);
         Reporter.log("Pdc status is Selected",true);

    }else {
           Reporter.log("Failed to select pdc status",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCReceivedDate));
   if(Custsuppage.SecondPDCReceivedDate.isEnabled()){
	   Custsuppage.SecondPDCReceivedDate.click();
	   //custSupPage.PDCSecondClearButton.click();
	   Custsuppage.PDCSecondTodayButton.click();
            Reporter.log("Successfully Enter pdc received date",true);
            Thread.sleep(1000);
    }else {
           Reporter.log("Failed to enter pdc received date",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCSave));
   Custsuppage.SecondPDCSave.click();
   Reporter.log("Clicked second pdc save Button", true);

}


public void EnterPDCDts(String ChequeNo,String Amount,String BankName,String IFSC,String BankAccNo,String Status) throws InterruptedException{
	// wait for First PDC form
    Wait<WebDriver> wait = new WebDriverWait(driver, 80);
    wait.until(ExpectedConditions.visibilityOf(Custsuppage.PDCFirstrow));

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.FirstPDCchequeNum));
   if(Custsuppage.FirstPDCchequeNum.isEnabled()){
              Custsuppage.FirstPDCchequeNum.clear();
              Custsuppage.FirstPDCchequeNum.sendKeys(ChequeNo);
            Reporter.log("Entered cheque number",true);
            Thread.sleep(1000);
    }else{
    	 wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCchequeNum));
    	 Custsuppage.SecondPDCchequeNum.sendKeys(ChequeNo);
           Reporter.log("Enter cheque number",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.FirstPDCchequeamount));
   if(Custsuppage.FirstPDCchequeamount.isEnabled()){
              Custsuppage.FirstPDCchequeamount.clear();
              Custsuppage.FirstPDCchequeamount.sendKeys(Amount);
            Reporter.log("Entered cheque amount",true);
            Thread.sleep(1000);
    }else {
    	wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCchequeamount));
    	Custsuppage.SecondPDCchequeamount.sendKeys(Amount);
           Reporter.log("Enter cheque amount",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.FirstPDCchequeDate));
   if(Custsuppage.FirstPDCchequeDate.isEnabled()){
	   Custsuppage.FirstPDCchequeDate.click();
	   //custSupPage.CalenderClearbutton.click();
	   Custsuppage.CalenderTodaybutton.click();
            Reporter.log("select cheque on date",true);
            Thread.sleep(1000);
    }else {
    	wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCchequeDate));
    	Custsuppage.SecondPDCchequeDate.click();
    	Custsuppage.PDCSecondTodayButton.click();
           Reporter.log("Select cheque on date",true); 
           Thread.sleep(2000); 
    }

  wait.until(ExpectedConditions.visibilityOf(Custsuppage.FirstPDCBankName));
   if(Custsuppage.FirstPDCBankName.isEnabled()){
              Custsuppage.FirstPDCBankName.clear();
              Custsuppage.FirstPDCBankName.sendKeys(BankName);
            Reporter.log("Entered Bank Name",true);
            Thread.sleep(1000);
    }else {
    	wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCBankName));
    	Custsuppage.SecondPDCBankName.sendKeys(BankName);
           Reporter.log("Enter Bank Name",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.FirstPDCBankIFSC));
   if(Custsuppage.FirstPDCBankIFSC.isEnabled()){
              Custsuppage.FirstPDCBankIFSC.clear();
              Custsuppage.FirstPDCBankIFSC.sendKeys(IFSC);
            Reporter.log("Entered ifsc code",true);
            Thread.sleep(1000);
    }else {
    	wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCBankIFSC));
    	Custsuppage.SecondPDCBankIFSC.sendKeys(IFSC);
           Reporter.log("Enter ifsc code",true); 
           Thread.sleep(2000); 
    }

  wait.until(ExpectedConditions.visibilityOf(Custsuppage.FirstPDCAccNum));
   if(Custsuppage.FirstPDCAccNum.isEnabled()){
              Custsuppage.FirstPDCAccNum.clear();
              Custsuppage.FirstPDCAccNum.sendKeys(BankAccNo);
            Reporter.log("Entered pdc account number",true);
            Thread.sleep(1000);
    }else {
    	 wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCAccNum));
    	 Custsuppage.SecondPDCAccNum.sendKeys(BankAccNo);
           Reporter.log("Enter ifsc code",true); 
           Thread.sleep(2000); 
    }


   wait.until(ExpectedConditions.visibilityOf(Custsuppage.PDCformfirstStatus));
   if(Custsuppage.PDCformfirstStatus.isEnabled()){
	   Select select_status = new Select(Custsuppage.PDCformfirstStatus);
       // select_status.deselectByValue();
         //select_status.selectByVisibleText("Select...");
         select_status.selectByVisibleText(Status);
         Reporter.log("selected PDC status",true);

    }else {
    	wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCStatus));
    	 Select select_status = new Select(Custsuppage.SecondPDCStatus);
    	 select_status.selectByVisibleText(Status);
           Reporter.log("Select pdc status",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.FirstPDCReceivedDate));
   if(Custsuppage.FirstPDCReceivedDate.isEnabled()){
	   Custsuppage.FirstPDCReceivedDate.click();
	   //.CalenderClearbutton.click();;
	   Custsuppage.CalenderTodaybutton.click();
            Reporter.log("Selected pdc received date",true);
            Thread.sleep(1000);
    }else {
    	wait.until(ExpectedConditions.visibilityOf(Custsuppage.SecondPDCReceivedDate));
    	Custsuppage.SecondPDCReceivedDate.click();
    	Custsuppage.PDCSecondTodayButton.click();
           Reporter.log("Select pdc received date",true); 
           Thread.sleep(2000); 
    }

   wait.until(ExpectedConditions.visibilityOf(Custsuppage.FirstPDCSave));
   Custsuppage.FirstPDCSave.click();
   Reporter.log("Clicked pdc save Button", true);
}
 public void PDCDts(String custid,String ChequeNo,String Amount,String BankName,String IFSC,String BankAccNo,String Status) throws InterruptedException{
	 Custsuppage = PageFactory.initElements(driver,CustomerSupportPage.class);
	  Wait<WebDriver> wait = new WebDriverWait(driver, 90); 
	 wait.until(ExpectedConditions.visibilityOf(Custsuppage.PDCTABLE));		  
     if(Custsuppage.PDCTABLE.isEnabled()){	

	     wait.until(ExpectedConditions.visibilityOf(Custsuppage.BtnAddCheque));
	        if(Custsuppage.BtnAddCheque.isEnabled())
            {
    	        wait.until(ExpectedConditions.visibilityOf(Custsuppage.Typeofproduct));
    	        String Product = Custsuppage.Typeofproduct.getText();
			    String ArrArgs[] = Product.split(":");
			    String ProductType = ArrArgs[1].trim();	
		        //based on product type we can enter pdc details
			    Reporter.log("********* BASED ON PRODUCT TYPE PDC DETAILS ARE ENTERED********* ",true);
		        if(ProductType.equalsIgnoreCase("RETAIL_90"))
		        {
			          Reporter.log("*************ADD CHEQUE BUTTON IS ENABLED*************",true);
			          wait.until(ExpectedConditions.visibilityOf(Custsuppage.BtnAddCheque)); 
			          Custsuppage.BtnAddCheque.click();
			          EnterPDCDts(ChequeNo,Amount,BankName,IFSC,BankAccNo,Status);    
			          Reporter.log("PDC details entered for " + custid + "of" + ProductType,true);
			          Reporter.log("*************PDC DETAILS ENTERED SUCCESSFULLY*************",true);
			          Thread.sleep(4000);
			          String ActualMsg = Custsuppage.ActString.getText();
		              String ExpMsg = "Customer PDC details saved/updated successfully!!";
			       //verify expected message
			         if (ActualMsg.equalsIgnoreCase(ExpMsg))
			         {
				         Reporter.log("Message is:"+ ActualMsg,true);
				         wait.until(ExpectedConditions.visibilityOf(Custsuppage.Flagclose));
				         Custsuppage.Flagclose.click();
			         }else{
				        Reporter.log("Failed to save PDC details",true);
			         }  

			   Reporter.log("************* COMPLETED ENTERED RETAIL_90 PDC DETAILS *************",true);   
		}else if((ProductType.equalsIgnoreCase("RETAIL_30") )||(ProductType.equalsIgnoreCase("RETAIL_15" )))
		  {
			   Reporter.log("*************ADD CHEQUE BUTTON IS ENABLED*************",true);
			   wait.until(ExpectedConditions.visibilityOf(Custsuppage.BtnAddCheque));
			   if (Custsuppage.BtnAddCheque.isEnabled())
			   {
				   Custsuppage.BtnAddCheque.click();
			       EnterPDCDts(ChequeNo,Amount,BankName,IFSC,BankAccNo,Status);
			       Reporter.log("PDC details entered for " + custid + " of " + ProductType,true);
			       Reporter.log("*************PDC DETAILS ENTERED SUCCESSFULLY*************",true);
			       String ActualMsg = Custsuppage.ActString.getText();
			       String ExpMsg = "Customer PDC details saved/updated successfully!!"; 
			       if (ActualMsg.equalsIgnoreCase(ExpMsg))
			       {
				           Reporter.log("Message is "+ActualMsg, true);
			       }else{
				           Reporter.log("Failed to save PDC details",true);
			       }

		  }
		} 
		   Reporter.log("************* COMPLETED ENTERED RETAIL_30 OR RETAIL_15 PDC DETAILS *************",true);    
     }else{
    	 wait.until(ExpectedConditions.visibilityOf(Custsuppage.PDCrow));
    	 wait.until(ExpectedConditions.visibilityOf(Custsuppage.EditPDClink));
    	 Custsuppage.EditPDClink.click();
    	 EnterPDCDts(ChequeNo,Amount,BankName,IFSC,BankAccNo,Status);
    	 Reporter.log("PDC details entered for " + custid ,true);
	     Reporter.log("*************PDC DETAILS ENTERED SUCCESSFULLY*************",true);  
	     String ActualMsg = Custsuppage.ActString.getText();
           String ExpMsg = "Customer PDC details saved/updated successfully!!";
	       //verify expected message
	       if (ActualMsg.equalsIgnoreCase(ExpMsg))
	       {
		       Reporter.log("Message is:"+ ActualMsg,true);
		       wait.until(ExpectedConditions.visibilityOf(Custsuppage.Flagclose));
		       Custsuppage.Flagclose.click();
	       }else{
		       Reporter.log("Failed to save PDC details",true);
	       }  
         }  

}
 else{
	 Reporter.log("********* CUSTOMER NOT MARKED AS PDC FLOW *********",true);
	Thread.sleep(4000);
	wait.until(ExpectedConditions.visibilityOf(custSupPage.ActualString));
	 String ActMsg = custSupPage.ActualString.getText();
	 String Expmessage = "This Customer Is Not Marked as PDC Required.";
	 Assert.assertEquals(ActMsg, Expmessage);
	 Reporter.log(Cusid +ActMsg, true);
     Thread.sleep(3000);
	 if(ActMsg.equalsIgnoreCase(Expmessage)){
		 Reporter.log("Message is:"+ ActMsg,true); 
	 }else{
        Reporter.log("Failed to get product type",true); 
        Thread.sleep(2000); 
	 }

   Reporter.log("********* COMPLETED CUSTOMER NOT MARKED AS PDC FLOW *********",true);



 }



//public void NavigatetoCustomerSupportScreen(String TagBaseSearch){

public void NavigatetoCustomerSupportScreen(String Cusid) throws InterruptedException{
	Custsuppage = PageFactory.initElements(driver,CustomerSupportPage.class);
	// System.out.println(driver);
	  Wait<WebDriver> wait = new WebDriverWait(driver, 100); 

		//click on toggle
		wait.until(ExpectedConditions.visibilityOf(Custsuppage.Toggle));
		Custsuppage.Toggle.click();
		Reporter.log("Toggle Navigation Clicked",true);


		//click on Customers tab
		wait.until(ExpectedConditions.visibilityOf(Custsuppage.CustomerTab));
		Custsuppage.CustomerTab.click();

		//click on Customers support tab
		wait.until(ExpectedConditions.visibilityOf(Custsuppage.CustomerSupportTab));
		Custsuppage.CustomerSupportTab.click();
		//click on left menu item
		//WebElement leftmenu = driver.findElement(By.xpath("//a[@role='button']"));
		wait.until(ExpectedConditions.visibilityOf(Custsuppage.leftmenu));
		Custsuppage.leftmenu.click();

		  wait.until(ExpectedConditions.visibilityOf(Custsuppage.TagbaseSearch));
	      Select select_status1 = new Select(Custsuppage.TagbaseSearch);
	      select_status1.selectByVisibleText(TagBaseSearch);
	      Reporter.log("Select PDC required tag",true);

		    wait.until(ExpectedConditions.visibilityOf(Custsuppage.Search));
		    Custsuppage.Search.click();;
		    Reporter.log("sucessfully clicked search button ",true);

	      //get random customer id
	      List <WebElement> listings = driver.findElements(By.xpath(".//*[@class='ng-binding'  and @style='width: 100px;']"));
	      Random r = new Random();
	      int randomValue = r.nextInt(listings.size()); //Getting a random value that is between 0 and (list's size)-1
	      String randomCust=   listings.get(randomValue).getText();   


		//Enter customer id
		wait.until(ExpectedConditions.visibilityOf(Custsuppage.CustomerId));
		Custsuppage.CustomerId.sendKeys(randomCust);
		Reporter.log("entered data "+randomCust+" into filter search ", true);

		  wait.until(ExpectedConditions.visibilityOf(Custsuppage.CustomerId));
		   Custsuppage.CustomerId.sendKeys(Cusid);
		  Reporter.log("entered customer id  "+Cusid+" into filter search ", true);

		  wait.until(ExpectedConditions.visibilityOf(Custsuppage.TagbaseSearch));
	      Select select_status2 = new Select(Custsuppage.TagbaseSearch);
	      select_status2.selectByVisibleText("All");
	      Reporter.log("Select PDC required tag",true);

	    //Click on search button
	    wait.until(ExpectedConditions.visibilityOf(Custsuppage.Search));
	    Custsuppage.Search.click();;
	    Reporter.log("sucessfully clicked search button ",true);

	    //click oncustomer details button
	    Thread.sleep(4000);
	    wait.until(ExpectedConditions.visibilityOf(Custsuppage.customerdts)); 
	    Custsuppage.customerdts.click();
	    Reporter.log("Clicked on  Customer details ",true);

	    //Click on customer pdc tab
	    wait.until(ExpectedConditions.visibilityOf(Custsuppage.PDCtab));
	    Custsuppage.PDCtab.click();
	    Reporter.log("Clicked on  PDCTab ",true);

}
BankLoanRepaymentPage bankloanrepaymentpage;
public void Calendar(WebElement TBCreditDate, WebElement ReadOnlyTB,String DateDesired) throws Exception
{
 try{


 bankloanrepaymentpage = PageFactory.initElements(driver, BankLoanRepaymentPage.class); 
 Wait<WebDriver> wait = new WebDriverWait(driver, 30);
//DateToSelect format is YYYY-MM-DD 

 //WebElement tb1= driver.findElement(By.xpath("//input[@ng-model='bankLoanRePayment.creditDate']"));
    wait.until(ExpectedConditions.visibilityOf(TBCreditDate));
    TBCreditDate.click();
    Reporter.log("date calendar is opened", true);
    Thread.sleep(2000);

    //WebElement date1 = driver.findElement(By.id("payment_datetime"));
    wait.until(ExpectedConditions.visibilityOf(ReadOnlyTB));
    ((JavascriptExecutor)driver).executeScript ("document.getElementById('payment_datetime').removeAttribute('readonly',0);");
    Reporter.log("executing javascript to remove READ ONLY ATTRIBUTE", true);
    TBCreditDate.clear();

    TBCreditDate.sendKeys(DateDesired);
    Thread.sleep(3000);

    String dateEntered = TBCreditDate.getText();
    System.out.println("date entered is" +dateEntered);

    Reporter.log("entered date",true);
}

 catch(Exception e)
 {
  e.printStackTrace();
 }
}
UpdateCustomerTagPage Updatecusttag;
CustomerVerificationPage Custverification;
public void NavigateNEnterUpdateCustomerTag(String Tagtype,String Custtag,String Custid,String Custnote,String Status,String Category,String Subcategory,String Reason) throws InterruptedException{
public void NavigateNEnterUpdateCustomerTag(String Tagtype,String Custtag,String Custid,String Custnote) throws InterruptedException{
	 Updatecusttag = PageFactory.initElements(driver,UpdateCustomerTagPage.class);
	 Custverification =  PageFactory.initElements(driver,CustomerVerificationPage.class);
	     // System.out.println(driver);
	     Wait<WebDriver> wait = new WebDriverWait(driver, 110); 

		 //click on toggle
		 wait.until(ExpectedConditions.visibilityOf(Updatecusttag.Toggle));
		 Updatecusttag.Toggle.click();
		 Reporter.log("Toggle Navigation Clicked",true);


		  //click on Customers tab
		  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.CustomerTab));
		  Updatecusttag.CustomerTab.click();

		  //click on update customer tag tab
		  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.UpdateCustagTab));
		  Updatecusttag.UpdateCustagTab.click();

		  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.leftmenu));
		  Updatecusttag.leftmenu.click();

		  switch(Tagtype){
		  case "Add Customer Tag":

			     wait.until(ExpectedConditions.visibilityOf(Updatecusttag.AddCusTag));
		         Updatecusttag.AddCusTag.click();
		         Reporter.log("Click on add customer tag");
		        //select customer tag
		         wait.until(ExpectedConditions.visibilityOf(Updatecusttag.SelectCustTag));
			      Thread.sleep(5000);
			      Select select_status = new Select(Updatecusttag.SelectCustTag);
			      select_status.selectByVisibleText(Custtag);
			      Reporter.log("Customer tag Selected",true);
			      Thread.sleep(1000);
			      //Enter customer id 
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.CustCLN));
			      Updatecusttag.CustCLN.sendKeys(Custid);
				  Reporter.log("Entered customer id : "+Custid,true);
				  //Enter notes
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.CustNotes));
			      Updatecusttag.CustNotes.sendKeys(Custnote);
				  Reporter.log("Entered customers notes",true);
				  //click on submit button
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.BtnSubmit));
			      Updatecusttag.BtnSubmit.click();
				  Reporter.log("Clicked on submit button",true);

		  case "Remove Customer Tag":

				  //select remove tag radio button
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveCusTag));
			      Updatecusttag.RemoveCusTag.click();
		          Reporter.log("Click on remove customer tag");
		          //select customer tag 
		          Thread.sleep(5000);
		          wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveSelectCustTag));
		          Thread.sleep(5000);
			      Select select_status1 = new Select(Updatecusttag.RemoveSelectCustTag);
			      select_status1.selectByVisibleText(Custtag);
			      Reporter.log("Selected remove Customer tag",true);
			      Thread.sleep(1000);
			      //Enter customer number
			      wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveCustCLN));
			      Updatecusttag.RemoveCustCLN.sendKeys(Custid);
			      Reporter.log("Entered remove customer id : "+Custid,true);

			    //Enter notes
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveTagNote));
			      Updatecusttag.RemoveTagNote.sendKeys(Custnote);
				  Reporter.log("Entered remove customers notes",true);
				  //clicked on submit button
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.BtnRemoveSubmit));
			      Updatecusttag.BtnRemoveSubmit.click();
				  Reporter.log("Clicked on submit button",true); 

		  case "Fail Customer":
			//select fail customer tag radio button
			  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.FailCusTag));
		      Updatecusttag.FailCusTag.click();
	          Reporter.log("Click on fail customer customer tag",true);

	          //Enter customer id
	          wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveCusid));
	          Updatecusttag.RemoveCusid.sendKeys(Custid);
	          Reporter.log("Enter customer CLN Number",true);

	        //click on search button
	          wait.until(ExpectedConditions.visibilityOf(Updatecusttag.Removesearch));
	          Updatecusttag.Removesearch.click();
	          Reporter.log("Click on search button",true);
	         //select cutomer status 
	          wait.until(ExpectedConditions.visibilityOf(Custverification.Status));
	          Thread.sleep(5000);
		      Select select_status2 = new Select(Custverification.Status);
		      select_status2.selectByVisibleText(Status);
		      Reporter.log("Selected customer status",true);
	          //select category
		      wait.until(ExpectedConditions.visibilityOf(Custverification.category));
		      Select select_status3 = new Select(Custverification.category);
		      select_status3.selectByVisibleText(Category);
		      Reporter.log("Selected category",true);
		       //select sub category
		      wait.until(ExpectedConditions.visibilityOf(Custverification.SubCtgry));
		      Select select_status4 = new Select(Custverification.SubCtgry);
		      select_status4.selectByVisibleText(Subcategory);
		      Reporter.log("Selected category",true);

		      //select reason
		      wait.until(ExpectedConditions.visibilityOf(Custverification.Reason));
		      Select select_status5 = new Select(Custverification.Reason);
		      select_status5.selectByVisibleText(Reason);
		      Reporter.log("Selected reason",true);

		      //click on submit button
		      wait.until(ExpectedConditions.visibilityOf(Updatecusttag.FailSubmit));
		      Updatecusttag.FailSubmit.click();
	          Reporter.log("Click on submit button",true);
		  default:
				System.out.println("tag : " + Tagtype + " is invalid,selecting the tag of choice..");
		  }

		  String RadioAddCust = "Add Customer Tag";
		  String RadioRemoveCust = "Remove Customer Tag";
		  if ((RadioAddCust.equalsIgnoreCase(Tagtype))){
		       wait.until(ExpectedConditions.visibilityOf(Updatecusttag.AddCusTag));
		       Updatecusttag.AddCusTag.click();
		       Reporter.log("Click on add customer tag");
		       //select customer tag
		         wait.until(ExpectedConditions.visibilityOf(Updatecusttag.SelectCustTag));
			      Thread.sleep(5000);
			      Select select_status = new Select(Updatecusttag.SelectCustTag);
			      select_status.selectByVisibleText(Custtag);
			      Reporter.log("Customer tag Selected",true);
			      Thread.sleep(1000);
			      //Enter customer id 
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.CustCLN));
			      Updatecusttag.CustCLN.sendKeys(Custid);
				  Reporter.log("Entered customer id : "+Custid,true);
				  //Enter notes
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.CustNotes));
			      Updatecusttag.CustNotes.sendKeys(Custnote);
				  Reporter.log("Entered customers notes",true);
				  //click on submit button
				  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.BtnSubmit));
			      Updatecusttag.BtnSubmit.click();
				  Reporter.log("Clicked on submit button",true); 
		  }else if(RadioRemoveCust.equalsIgnoreCase(Tagtype)){
			  //select remove tag radio button
			  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveCusTag));
		      Updatecusttag.RemoveCusTag.click();
	          Reporter.log("Click on remove customer tag");
	         //select customer tag 
	          Thread.sleep(5000);
	          wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveSelectCustTag));
	          Thread.sleep(5000);
		      Select select_status = new Select(Updatecusttag.RemoveSelectCustTag);
		      select_status.selectByVisibleText(Custtag);
		      Reporter.log("Selected remove Customer tag",true);
		      Thread.sleep(1000);
		      //Enter customer number
		      wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveCustCLN));
		      Updatecusttag.RemoveCustCLN.sendKeys(Custid);
		      Reporter.log("Entered remove customer id : "+Custid,true);

		    //Enter notes
			  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveTagNote));
		      Updatecusttag.RemoveTagNote.sendKeys(Custnote);
			  Reporter.log("Entered remove customers notes",true);
			  //clicked on submit button
			  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.BtnRemoveSubmit));
		      Updatecusttag.BtnRemoveSubmit.click();
			  Reporter.log("Clicked on submit button",true); 

		  }



}
CustomerSupportPage CusSupport;
public void VeifyCustStatus_Support(String CustId){
	//initialize page elements
	CusSupport = PageFactory.initElements(driver, CustomerSupportPage.class);

	Wait<WebDriver> wait = new WebDriverWait(driver, 30);

	//click on toggle
	wait.until(ExpectedConditions.visibilityOf(CusSupport.Toggle));
	CusSupport.Toggle.click();
	Reporter.log("Toggle Navigation Clicked",true);


	//click on Customers tab
	wait.until(ExpectedConditions.visibilityOf(CusSupport.CustomerTab));
	CusSupport.CustomerTab.click();

	//click on Customers support tab
	wait.until(ExpectedConditions.visibilityOf(CusSupport.CustomerSupportTab));
	CusSupport.CustomerSupportTab.click();

	wait.until(ExpectedConditions.visibilityOf(CusSupport.leftmenu));
	CusSupport.leftmenu.click();

	wait.until(ExpectedConditions.visibilityOf(CusSupport.CustomerId));
	CusSupport.CustomerId.sendKeys(CustId);

	wait.until(ExpectedConditions.visibilityOf(CusSupport.Search));
	CusSupport.Search.click();

	wait.until(ExpectedConditions.visibilityOf(CusSupport.CustStatusTag));
	String Status = CusSupport.CustStatusTag.getText();
	return Status;

}

public void EnterFailedDts_UpdateCustTag(String Tagtype,String Custid,String Status,String Category,String Subcategory,String Reason) throws InterruptedException{
	Updatecusttag = PageFactory.initElements(driver,UpdateCustomerTagPage.class);
	Custverification = PageFactory.initElements(driver,CustomerVerificationPage.class);
    // System.out.println(driver);
    Wait<WebDriver> wait = new WebDriverWait(driver, 110); 

	 //click on toggle
	 wait.until(ExpectedConditions.visibilityOf(Updatecusttag.Toggle));
	 Updatecusttag.Toggle.click();
	 Reporter.log("Toggle Navigation Clicked",true);


	  //click on Customers tab
	  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.CustomerTab));
	  Updatecusttag.CustomerTab.click();

	  //click on update customer tag tab
	  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.UpdateCustagTab));
	  Updatecusttag.UpdateCustagTab.click();

	  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.leftmenu));
	  Updatecusttag.leftmenu.click();

	  String RadioAddCust = "Fail Customer";
	  if ((RadioAddCust.equalsIgnoreCase(Tagtype))){
		//select fail customer tag radio button
		  wait.until(ExpectedConditions.visibilityOf(Updatecusttag.FailCusTag));
	      Updatecusttag.FailCusTag.click();
          Reporter.log("Click on fail customer customer tag",true);

          //Enter customer id
          wait.until(ExpectedConditions.visibilityOf(Updatecusttag.RemoveCusid));
          Updatecusttag.RemoveCusid.sendKeys(Custid);
          Reporter.log("Enter customer CLN Number: "+Custid,true);

        //click on search button
          wait.until(ExpectedConditions.visibilityOf(Updatecusttag.Removesearch));
          Updatecusttag.Removesearch.click();
          Reporter.log("Click on search button",true);
         //select cutomer status 
          wait.until(ExpectedConditions.visibilityOf(Custverification.Status));
          Thread.sleep(5000);
	      Select select_status2 = new Select(Custverification.Status);
	      select_status2.selectByVisibleText(Status);
	      Reporter.log("Selected customer status: "+Status,true);
          //select category
	      wait.until(ExpectedConditions.visibilityOf(Custverification.category));
	      Select select_status3 = new Select(Custverification.category);
	      select_status3.selectByVisibleText(Category);
	      Reporter.log("Selected category: "+Category,true);
	       //select sub category
	      wait.until(ExpectedConditions.visibilityOf(Custverification.SubCtgry));
	      Select select_status4 = new Select(Custverification.SubCtgry);
	      select_status4.selectByVisibleText(Subcategory);
	      Reporter.log("Selected subcategory: "+Subcategory,true);

	      //select reason
	      wait.until(ExpectedConditions.visibilityOf(Custverification.Reason));
	      Select select_status5 = new Select(Custverification.Reason);
	      Thread.sleep(5000);
	      select_status5.selectByVisibleText(Reason);
	      Reporter.log("Selected reason: "+Reason,true);

	      //click on submit button
	      wait.until(ExpectedConditions.visibilityOf(Updatecusttag.FailSubmit));
	      Updatecusttag.FailSubmit.click();
          Reporter.log("Click on submit button",true);
	  }else{
		  Reporter.log("Failed to enter failed tag details in update customer tag",true);
	  }
}
public String Executeueries(String Query,String Username,String Password) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
String Custid = null;
Connection conn = null;
Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
conn = DriverManager.getConnection("jdbc:mysql://testdb.cashe.co.in:3306/cashe_qa",Username,Password);
Reporter.log("Database Connection Established Successfully...",true);
    //   Statement st = conn.createStatement();
       PreparedStatement pstmt = conn.prepareStatement(Query);
      // pstmt.setString(1, "");
       ResultSet rs = pstmt.executeQuery();

       rs.beforeFirst();
       while(rs.next()){
        System.out.println(rs.getString(1));
        Custid = rs.getString(1);
        }
	return Custid;

}

public void  AddressDts(String fullCrrntAddr,String postalCode, String houseType, String verifyCompanyName) throws Exception{
	CustomerVerificationPage cstVdtnMycasesPage;

			// initialize page elements
			cstVdtnMycasesPage = PageFactory.initElements(driver, CustomerVerificationPage.class);


			//Checked Personal Details Group On checkboxes
			cstVdtnMycasesPage.PrsnlDtlsChkbox.click();
			Thread.sleep(3000);
			if (!cstVdtnMycasesPage.PrsnlDtlsChkbox.isSelected()){
			cstVdtnMycasesPage.PrsnlDtlsChkbox.click();
			Reporter.log("Personal Details Category Checked",true);
			Thread.sleep(2000);
			}


			//Click the checkbox for PAN Details Category
			cstVdtnMycasesPage.PanDetails.click();
			Thread.sleep(2000);
			if (!cstVdtnMycasesPage.PanDtlsChkbox.isSelected()){
			cstVdtnMycasesPage.PanDtlsChkbox.click();
			Reporter.log("PAN Details Category Checked",true);
			Thread.sleep(2000);
			}

			 wait for customer details category and expand the Company details
			  Wait<WebDriver> wait = new WebDriverWait(driver, 30);
			  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.CompanyDetails));
			  cstVdtnMycasesPage.CompanyDetails.click();
			 if(cstVdtnMycasesPage.CompanyDetails.isEnabled()) {
			  //CustomerVerificationPage.ChangeCompany.click();


			  wait for Change Company in customer details category
			  Wait<WebDriver> wait1 = new WebDriverWait(driver, 30);
			  wait1.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.ChangeCompany));
			  cstVdtnMycasesPage.ChangeCompany.clear();
			 if(cstVdtnMycasesPage.ChangeCompany.isEnabled()) {
			  //CustomerVerificationPage.ChangeCompany.click();

				//Click the checkbox for Company Details Category
				  //CustomerVerificationPage.ChangeCompany.click();
				    Thread.sleep(2000);
				    if (!cstVdtnMycasesPage.ChngCmpnyChkbox.isSelected()){
				    	cstVdtnMycasesPage.ChngCmpnyChkbox.click();
				    Reporter.log("Company Category Checked",true);
				    Thread.sleep(1000);
				    }


			  wait for Address Details category and expand the Address details
			 // Wait<WebDriver> wait = new WebDriverWait(driver, 30);
			  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.AddressDetails));
			  cstVdtnMycasesPage.AddressDetails.clear();
			 if(cstVdtnMycasesPage.AddressDetails.isEnabled()) {
			  //CustomerVerificationPage.AddressDetails.click();

				//Click the checkbox for Address Details Category
				  //CustomerVerificationPage.PersonalDetails.click();
				    Thread.sleep(2000);
				    if (!cstVdtnMycasesPage.CmpnyDtlsChkbox.isSelected()){
				    	cstVdtnMycasesPage.CmpnyDtlsChkbox.click();
				    Reporter.log("Company Details Category Checked",true);
				    Thread.sleep(1000);
				    }

				    wait for Full Current Address in Address details

					  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.FullCurrentAddr));
					  cstVdtnMycasesPage.FullCurrentAddr.clear();
					   if(cstVdtnMycasesPage.FullCurrentAddr.isEnabled()){
						   cstVdtnMycasesPage.FullCurrentAddr.sendKeys(fullCrrntAddr);
					      Reporter.log("Address Details Category Checked",true);
					      Thread.sleep(1000);
					   }else {
					         Reporter.log("Submit button is disabled",true); 
					         Thread.sleep(2000); 
					     }   

			  wait for Postalcode in Address details

			  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Postalcode));
			  cstVdtnMycasesPage.Postalcode.clear();
			   if(cstVdtnMycasesPage.Postalcode.isEnabled()){
				   cstVdtnMycasesPage.Postalcode.sendKeys(postalCode);
			      Reporter.log("Address Details Category Checked",true);
			      Thread.sleep(1000);
			   }else {
			         Reporter.log("Submit button is disabled",true); 
			         Thread.sleep(2000); 
			     }

			  wait for Designation in Company details
			   wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Accommodation));
			   cstVdtnMycasesPage.Accommodation.clear();
			  Select select_status = new Select(cstVdtnMycasesPage.Accommodation);
			       select_status.selectByVisibleText(houseType);
			       Reporter.log("HouseType is Selected",true);
			       Thread.sleep(1000);
			   }else {
			         Reporter.log("Submit button is disabled",true); 
			         Thread.sleep(2000); 
			   }

			//verify profile pic
				wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custProfileImgChkbox));
				if(!cstVdtnMycasesPage.custProfileImgChkbox.isSelected()){
					cstVdtnMycasesPage.custProfileImgChkbox.click();
					Reporter.log("Profile Image checked",true);
					Thread.sleep(1000);
				}


				//verify PANCard pic
				wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custPanImgChkbox));
				if(!cstVdtnMycasesPage.custPanImgChkbox.isSelected()){
					cstVdtnMycasesPage.custPanImgChkbox.click();
					Reporter.log("PAN image Checked",true);
					Thread.sleep(1000);
				}

				//verify address pic
				wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custAddressImgChkbox));
				if(!cstVdtnMycasesPage.custAddressImgChkbox.isSelected()){
					cstVdtnMycasesPage.custAddressImgChkbox.click();
					Reporter.log("Address Image Checked",true);
					Thread.sleep(1000);
				}

				//verify payslip pic
				wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custPayslipImgChkbox));
				if(!cstVdtnMycasesPage.custPayslipImgChkbox.isSelected()){
					cstVdtnMycasesPage.custPayslipImgChkbox.click();
					Reporter.log("Payslip Image Checked",true);
					Thread.sleep(1000);
				}

				//verify Bank Statement pic
				wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custBankStmtImgChkbox));
				if(!cstVdtnMycasesPage.custBankStmtImgChkbox.isSelected()){
					cstVdtnMycasesPage.custBankStmtImgChkbox.click();
					Reporter.log("Bank Stmt Image Checked",true);
					Thread.sleep(1000);
				}

			}

			//Click the checkbox for Other Details Category
				cstVdtnMycasesPage.OtherDetails.click();
				Thread.sleep(2000);
				if (!cstVdtnMycasesPage.OthrDtlsChkbox.isSelected()){
				cstVdtnMycasesPage.OthrDtlsChkbox.click();
				Reporter.log("Other Details Category Checked",true);
				Thread.sleep(1000);

				// Click Status for Verified
			    wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Status));
			    Select select_status = new Select(cstVdtnMycasesPage.Status);
			    select_status.selectByVisibleText("Verified");
			    Reporter.log("Status Selected",true);
			    Thread.sleep(1000);
				}
			 }
	}
public void validPersonalDt1s(String fullName,String personalEmailId,String EduQualification,String MobileNum,String LandlineNumber,String CustCibilScore,String VerifyCompanyName) 
        throws InterruptedException{ 
	CustomerVerificationPage cstVdtnMycasesPage;
      		cstVdtnMycasesPage = PageFactory.initElements(driver, CustomerVerificationPage.class);

wait for customer details category and expand the personal details
Wait<WebDriver> wait = new WebDriverWait(driver, 30);
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.PersonalDetails));
//if(CustomerVerificationPage.PersonalDetails.isEnabled()) {

//click on toggle
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.toggleNavigation));
cstVdtnMycasesPage.toggleNavigation.click();
Reporter.log("Toggle Navigation Clicked", true);

Thread.sleep(2000);

if (!cstVdtnMycasesPage.PrsnlDtlsChkbox.isSelected()){
   cstVdtnMycasesPage.PrsnlDtlsChkbox.click();
Reporter.log("Personal Details Category Checked",true);
Thread.sleep(1000);

}

//verify profile pic
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custProfileImgChkbox));
if(!cstVdtnMycasesPage.custProfileImgChkbox.isSelected()){
	cstVdtnMycasesPage.custProfileImgChkbox.click();
	Reporter.log("Profile Image checked",true);
	Thread.sleep(1000);
}

	//verify PANCard pic
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custPanImgChkbox));
if(!cstVdtnMycasesPage.custPanImgChkbox.isSelected()){
	cstVdtnMycasesPage.custPanImgChkbox.click();
	Reporter.log("PAN image Checked",true);
	Thread.sleep(1000);
}

//verify address pic
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custAddressImgChkbox));
if(!cstVdtnMycasesPage.custAddressImgChkbox.isSelected()){
	cstVdtnMycasesPage.custAddressImgChkbox.click();
	Reporter.log("Address Image Checked",true);
	Thread.sleep(1000);
}

//verify payslip pic
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custPayslipImgChkbox));
if(!cstVdtnMycasesPage.custPayslipImgChkbox.isSelected()){
	cstVdtnMycasesPage.custPayslipImgChkbox.click();
	Reporter.log("Payslip Image Checked",true);
	Thread.sleep(1000);
}

//verify Bank Statement pic
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custBankStmtImgChkbox));
if(!cstVdtnMycasesPage.custBankStmtImgChkbox.isSelected()){
	cstVdtnMycasesPage.custBankStmtImgChkbox.click();
	Reporter.log("Bank Stmt Image Checked",true);
	Thread.sleep(1000);
}


wait for full name in personal details

wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.FullName));
if(cstVdtnMycasesPage.FullName.isEnabled()){
 cstVdtnMycasesPage.FullName.clear();
 cstVdtnMycasesPage.FullName.sendKeys(fullName);
Reporter.log("Personal Details Category Checked",true);
Thread.sleep(1000);
}else {
   Reporter.log("Submit button is disabled",true); 
   Thread.sleep(2000); 
}

wait for full email id in personal details
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.PersonalEmailId));
cstVdtnMycasesPage.PersonalEmailId.clear();
cstVdtnMycasesPage.PersonalEmailId.sendKeys(personalEmailId);

wait for education qualification in personal details
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.EducationType));
Select select_status = new Select(cstVdtnMycasesPage.EducationType);
select_status.selectByVisibleText("Select...");
 select_status.selectByVisibleText(EduQualification);
 Reporter.log("Education qualification is Selected",true);


wait for full  in personal details
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.MobileNumber));
System.out.println("waiting for mobile number field");
Thread.sleep(5000);
cstVdtnMycasesPage.MobileNumber.clear();
cstVdtnMycasesPage.MobileNumber.sendKeys(MobileNum);

//wait for LandLine Number in personal details
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.LandlineNumber));
cstVdtnMycasesPage.LandlineNumber.clear();
cstVdtnMycasesPage.LandlineNumber.sendKeys(LandlineNumber); 

Reporter.log("Personal details entered successfully",true);
//Scroll the Page Down
Actions action = new Actions(driver);
Thread.sleep(5000);
action.sendKeys(Keys.PAGE_DOWN);
Thread.sleep(2000);
action.click(cstVdtnMycasesPage.pagedwn).perform();
Thread.sleep(2000);
System.out.println(" Page Scrolled Down");

//Click the checkbox for PAN Details Category
cstVdtnMycasesPage.PanDetails.click();
Thread.sleep(2000);
if (!cstVdtnMycasesPage.PanDtlsChkbox.isSelected()){
cstVdtnMycasesPage.PanDtlsChkbox.click();
Reporter.log("PAN Details Category Checked",true);
Thread.sleep(2000);
}
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custCibilScore));
Thread.sleep(5000);
if(cstVdtnMycasesPage.custCibilScore.isEnabled()){
 cstVdtnMycasesPage.custCibilScore.clear();
 cstVdtnMycasesPage.custCibilScore.sendKeys(CustCibilScore);
Reporter.log("Pan Details Category Checked",true);
Thread.sleep(1000);
}else {
   Reporter.log("Submit button is disabled",true); 
   Thread.sleep(5000); 
}

//Scroll the Page Down
Actions action1 = new Actions(driver);
Thread.sleep(5000);
action1.sendKeys(Keys.PAGE_DOWN);
Thread.sleep(2000);
action1.click(cstVdtnMycasesPage.pagedwn).perform();
Thread.sleep(2000);


//Verify Company checkbox 
cstVdtnMycasesPage.CompanyDetails.click();  
Thread.sleep(5000);
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.CmpnyDtlsChkbox));
if(!cstVdtnMycasesPage.CmpnyDtlsChkbox.isSelected()){
	cstVdtnMycasesPage.CmpnyDtlsChkbox.click();
Reporter.log("Verify Company Checked",true);
  Thread.sleep(1000);
 }

wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.ChangeCompany));
Thread.sleep(5000);
             if(cstVdtnMycasesPage.ChangeCompany.isEnabled()){
            	 cstVdtnMycasesPage.ChangeCompany.clear();
            	 cstVdtnMycasesPage.ChangeCompany.sendKeys(VerifyCompanyName);
                Reporter.log("Verify Company Details",true);
                Thread.sleep(1000);
             }else {
                   Reporter.log("Submit button is disabled",true); 
                   Thread.sleep(2000); 
               }

             //Scroll the Page Down
              Actions action2 = new Actions(driver);
              Thread.sleep(2000);
              action1.sendKeys(Keys.PAGE_DOWN);
              Thread.sleep(2000);
              action1.click(cstVdtnMycasesPage.pagedwn).perform();
              Thread.sleep(2000);


            //Click the checkbox for Address Details Category
              cstVdtnMycasesPage.AddressDetails.click();
                          Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.AddrDtlsChkbox));
           if (!cstVdtnMycasesPage.AddrDtlsChkbox.isSelected()){
        	   cstVdtnMycasesPage.AddrDtlsChkbox.click();
           Reporter.log("Address Details Category Checked",true);
           Thread.sleep(1000);
           }

         //Scroll the Page Down
              Actions action3 = new Actions(driver);
              Thread.sleep(2000);
              action1.sendKeys(Keys.PAGE_DOWN);
              Thread.sleep(2000);
              action1.click(cstVdtnMycasesPage.pagedwn).perform();
              Thread.sleep(2000);


            //Click the checkbox for Bank Details Category
              cstVdtnMycasesPage.BankDetails.click();
              wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.BnkDtlsChkbox));
              if (!cstVdtnMycasesPage.BnkDtlsChkbox.isSelected()){
            	  cstVdtnMycasesPage.BnkDtlsChkbox.click();
                          System.out.println("Succeccfully checked bank details");
              //Reporter.log("Bank Details Category Checked",true);
              Thread.sleep(1000);
              }

              //Scroll the Page Down
              Actions action4 = new Actions(driver);
              Thread.sleep(2000);
              action1.sendKeys(Keys.PAGE_DOWN);
              Thread.sleep(2000);
              action1.click(cstVdtnMycasesPage.pagedwn).perform();
              Thread.sleep(2000);



              //Click the checkbox for Other Details Category
              cstVdtnMycasesPage.OtherDetails.click();
              wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.OthrDtlsChkbox));
              if (!cstVdtnMycasesPage.OthrDtlsChkbox.isSelected()){
            	  cstVdtnMycasesPage.OthrDtlsChkbox.click();
              System.out.println("successfully checked other details");
              //Reporter.log("Other Details Category Checked",true);


           // Click Status for Verified
              wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Status));
              Thread.sleep(5000);
              Select select_status1 = new Select(cstVdtnMycasesPage.Status);
              select_status1.selectByVisibleText("Verified");
              Reporter.log("Status Selected",true);
              Thread.sleep(1000);
           }


  }

public void  validPANDts(String pan,String cibilscore,String VerifyCompanyName) throws InterruptedException{
	 CustomerVerificationPage cstVdtnMycasesPage;

		// initialize page elements
		cstVdtnMycasesPage = PageFactory.initElements(driver, CustomerVerificationPage.class);



		//Checked Personal Details Group On checkboxes
		cstVdtnMycasesPage.PersonalDetails.click();
		Thread.sleep(3000);
		if (!cstVdtnMycasesPage.PrsnlDtlsChkbox.isSelected()){
		cstVdtnMycasesPage.PrsnlDtlsChkbox.click();
		Reporter.log("Personal Details Category Checked",true);
		Thread.sleep(2000);

		cstVdtnMycasesPage.PersonalDetails.click();
		Thread.sleep(2000);
		}	

wait for customer details category and expand the PAN details
		  Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.PanDetails));
		 if(cstVdtnMycasesPage.PanDetails.isEnabled()) {
			 //Click the checkbox for PAN Details Category
			 cstVdtnMycasesPage.PanDetails.click();
		    Thread.sleep(2000);
		    if (!cstVdtnMycasesPage.PanDtlsChkbox.isSelected()){
		    	cstVdtnMycasesPage.PanDtlsChkbox.click();
		    Reporter.log("PAN Details Category Checked",true);
		    Thread.sleep(1000);
		    }

		  wait for PAN in PAN details
		   wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.PAN));
		  cstVdtnMycasesPage.PAN.clear();
		   if(cstVdtnMycasesPage.PAN.isEnabled()){
			   cstVdtnMycasesPage.PAN.sendKeys(pan);
		      Reporter.log("PAN Checked",true);
		      Thread.sleep(1000);
		   }else {
		         Reporter.log("Submit button is disabled",true); 
		         Thread.sleep(2000); 
		     }

		  wait for Cibil Score in PAN details
		  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custCibilScore));
		  cstVdtnMycasesPage.custCibilScore.clear();
		  cstVdtnMycasesPage.custCibilScore.sendKeys(cibilscore);
		  Reporter.log("cibil Checked",true);

		 }else{
		   Reporter.log("PAN  details category is not expand",true); 

		 }

			//verify profile pic
			wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custProfileImgChkbox));
			if(!cstVdtnMycasesPage.custProfileImgChkbox.isSelected()){
				cstVdtnMycasesPage.custProfileImgChkbox.click();
				Reporter.log("Profile Image checked",true);
				Thread.sleep(1000);
			}


			//verify PANCard pic
			wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custPanImgChkbox));
			if(!cstVdtnMycasesPage.custPanImgChkbox.isSelected()){
				cstVdtnMycasesPage.custPanImgChkbox.click();
				Reporter.log("PAN image Checked",true);
				Thread.sleep(1000);
			}

			//verify address pic
			wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custAddressImgChkbox));
			if(!cstVdtnMycasesPage.custAddressImgChkbox.isSelected()){
				cstVdtnMycasesPage.custAddressImgChkbox.click();
				Reporter.log("Address Image Checked",true);
				Thread.sleep(1000);
			}

			//verify payslip pic
			wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custPayslipImgChkbox));
			if(!cstVdtnMycasesPage.custPayslipImgChkbox.isSelected()){
				cstVdtnMycasesPage.custPayslipImgChkbox.click();
				Reporter.log("Payslip Image Checked",true);
				Thread.sleep(1000);
			}

			///verify Bank Statement pic
			wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custBankStmtImgChkbox));
			if(!cstVdtnMycasesPage.custBankStmtImgChkbox.isSelected()){
				cstVdtnMycasesPage.custBankStmtImgChkbox.click();
				Reporter.log("Bank Stmt Image Checked",true);
				Thread.sleep(1000);
			}

			//verify Bank Statement pic
			wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custBankStmtImgChkbox));
			if(!cstVdtnMycasesPage.custBankStmtImgChkbox.isSelected()){
				cstVdtnMycasesPage.custBankStmtImgChkbox.click();
				Reporter.log("Payslip Image Checked",true);
				Thread.sleep(1000);
			}

		//Scroll the Page Down
		  Actions action1 = new Actions(driver);
		  Thread.sleep(5000);
		  action1.sendKeys(Keys.PAGE_DOWN);
		  Thread.sleep(2000);
		  action1.click(cstVdtnMycasesPage.pagedwn).perform();
		  Thread.sleep(2000);



		 // System.out.println(" Page Scrolled Down");
		//verify Verify Company checkbox 
		  cstVdtnMycasesPage.CompanyDetails.click();  
		  Thread.sleep(5000);
		    wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.CmpnyDtlsChkbox));
		    if(!cstVdtnMycasesPage.CmpnyDtlsChkbox.isSelected()){
		    	cstVdtnMycasesPage.CmpnyDtlsChkbox.click();
		    Reporter.log("Verify Company Checked",true);
		      Thread.sleep(1000);
		     }

wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.ChangeCompany));
Thread.sleep(5000);
if(cstVdtnMycasesPage.ChangeCompany.isEnabled()){
cstVdtnMycasesPage.ChangeCompany.clear();
cstVdtnMycasesPage.ChangeCompany.sendKeys(VerifyCompanyName);
Reporter.log("Verify Company Details",true);
Thread.sleep(1000);
}else {
Reporter.log("Submit button is disabled",true); 
Thread.sleep(2000); 
}

//Scroll the Page Down
Actions action2 = new Actions(driver);
Thread.sleep(2000);
action1.sendKeys(Keys.PAGE_DOWN);
Thread.sleep(2000);
action1.click(cstVdtnMycasesPage.pagedwn).perform();
Thread.sleep(2000);


//Click the checkbox for Address Details Category
cstVdtnMycasesPage.AddressDetails.click();
Thread.sleep(2000);
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.AddrDtlsChkbox));
if (!cstVdtnMycasesPage.AddrDtlsChkbox.isSelected()){
cstVdtnMycasesPage.AddrDtlsChkbox.click();
Reporter.log("Address Details Category Checked",true);
Thread.sleep(1000);
}

//Scroll the Page Down
Actions action3 = new Actions(driver);
Thread.sleep(2000);
action1.sendKeys(Keys.PAGE_DOWN);
Thread.sleep(2000);
action1.click(cstVdtnMycasesPage.pagedwn).perform();
Thread.sleep(2000);


//Click the checkbox for Bank Details Category
cstVdtnMycasesPage.BankDetails.click();
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.BnkDtlsChkbox));
if (!cstVdtnMycasesPage.BnkDtlsChkbox.isSelected()){
cstVdtnMycasesPage.BnkDtlsChkbox.click();
System.out.println("Succeccfully checked bank details");
//Reporter.log("Bank Details Category Checked",true);
Thread.sleep(1000);
}

//Scroll the Page Down
Actions action4 = new Actions(driver);
Thread.sleep(2000);
action1.sendKeys(Keys.PAGE_DOWN);
Thread.sleep(2000);
action1.click(cstVdtnMycasesPage.pagedwn).perform();
Thread.sleep(2000);


//Click the checkbox for Other Details Category
cstVdtnMycasesPage.OtherDetails.click();
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.OthrDtlsChkbox));
if (!cstVdtnMycasesPage.OthrDtlsChkbox.isSelected()){
cstVdtnMycasesPage.OthrDtlsChkbox.click();
System.out.println("successfully checked other details");
//Reporter.log("Other Details Category Checked",true);


// Click Status for Verified
wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Status));
Thread.sleep(5000);
Select select_status1 = new Select(cstVdtnMycasesPage.Status);
select_status1.selectByVisibleText("Verified");
Reporter.log("Status Selected",true);
Thread.sleep(1000);
}

}	

public void  CompanyDts(String Cibilscore, String designation, String offEmailId, String cmpnyLandlineNo, String cmpnyLLExtn, String VerifyCompanyName) throws Exception{
	CustomerVerificationPage cstVdtnMycasesPage;

			// initialize page elements
			cstVdtnMycasesPage = PageFactory.initElements(driver, CustomerVerificationPage.class);

			//Click the checkbox for Personal Details Category
			if (!cstVdtnMycasesPage.PrsnlDtlsChkbox.isSelected()){
				cstVdtnMycasesPage.PrsnlDtlsChkbox.click();
				Reporter.log("Personal Details Category Checked",true);
				Thread.sleep(2000);

				cstVdtnMycasesPage.PersonalDetails.click();
				Thread.sleep(2000);
				}	

			//Click the checkbox for PAN Details Category
			cstVdtnMycasesPage.PanDetails.click();
			Thread.sleep(2000);
			if (!cstVdtnMycasesPage.PanDtlsChkbox.isSelected()){
			cstVdtnMycasesPage.PanDtlsChkbox.click();
			Reporter.log("PAN Details Category Checked",true);

			Thread.sleep(2000);
			}

		 wait for customer details category and expand the Company details
		  Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.CompanyDetails));
		  cstVdtnMycasesPage.CompanyDetails.click();
		 if(cstVdtnMycasesPage.CompanyDetails.isEnabled()) {
		  //CustomerVerificationPage.ChangeCompany.click();


			 wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.ChangeCompany));
			    Thread.sleep(5000);
			  if(cstVdtnMycasesPage.ChangeCompany.isEnabled()){
			                	 cstVdtnMycasesPage.ChangeCompany.clear();
			                	 cstVdtnMycasesPage.ChangeCompany.sendKeys(VerifyCompanyName);
			                    Reporter.log("Verify Company Details",true);
			                    Thread.sleep(1000);
			                 }else {
			                       Reporter.log("Submit button is disabled",true); 
			                       Thread.sleep(5000); 
			                   }


				//Click the checkbox for Company Details Category
				  //CustomerVerificationPage.ChangeCompany.click();
			     	Thread.sleep(2000);
				    if (!cstVdtnMycasesPage.ChngCmpnyChkbox.isSelected()){
					    	cstVdtnMycasesPage.ChngCmpnyChkbox.click();
					    Reporter.log("Company Category Checked",true);
					    Thread.sleep(1000);
					    }

				  wait for Designation in Company details
				   wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Designation));
				   cstVdtnMycasesPage.Designation.click();
				  Select select_status = new Select(cstVdtnMycasesPage.Designation);
				       select_status.selectByVisibleText(designation);
				       Reporter.log("Designation is Selected",true);
				       Thread.sleep(1000);
				   }else {
				         Reporter.log("Submit button is disabled",true); 
				         Thread.sleep(2000); 
				     }

				  wait for Official Email Id in Company details
				  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.OffEmailId));
				  cstVdtnMycasesPage.OffEmailId.clear();
				  cstVdtnMycasesPage.OffEmailId.sendKeys(offEmailId);



				  wait for Company Landline Number in Company details
				  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.CmpnyLLNumber));
				  cstVdtnMycasesPage.CmpnyLLNumber.clear();
				  cstVdtnMycasesPage.CmpnyLLNumber.sendKeys(cmpnyLandlineNo);

				  wait for Company Landline Exension Number in Company details
				  wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.CmpnyExtNumber));
				  cstVdtnMycasesPage.CmpnyExtNumber.clear();
				  cstVdtnMycasesPage.CmpnyExtNumber.sendKeys(cmpnyLLExtn);

				  }else{
				   Reporter.log("Personal details category is not expanded",true); 

				 }

				//verify profile pic
					wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custProfileImgChkbox));
					if(!cstVdtnMycasesPage.custProfileImgChkbox.isSelected()){
						cstVdtnMycasesPage.custProfileImgChkbox.click();
						Reporter.log("Profile Image checked",true);
						Thread.sleep(1000);
					}


					//verify PANCard pic
					wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custPanImgChkbox));
					if(!cstVdtnMycasesPage.custPanImgChkbox.isSelected()){
						cstVdtnMycasesPage.custPanImgChkbox.click();
						Reporter.log("PAN image Checked",true);
						Thread.sleep(1000);
					}

					//verify address pic
					wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custAddressImgChkbox));
					if(!cstVdtnMycasesPage.custAddressImgChkbox.isSelected()){
						cstVdtnMycasesPage.custAddressImgChkbox.click();
						Reporter.log("Address Image Checked",true);
						Thread.sleep(1000);
					}

					//verify payslip pic
					wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custPayslipImgChkbox));
					if(!cstVdtnMycasesPage.custPayslipImgChkbox.isSelected()){
						cstVdtnMycasesPage.custPayslipImgChkbox.click();
						Reporter.log("Payslip Image Checked",true);
						Thread.sleep(1000);
					}

					//verify Bank Statement pic
					wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.custBankStmtImgChkbox));
					if(!cstVdtnMycasesPage.custBankStmtImgChkbox.isSelected()){
						cstVdtnMycasesPage.custBankStmtImgChkbox.click();
						Reporter.log("Bank Stmt Image Checked",true);
						Thread.sleep(1000);
					}


				//Click the checkbox for Bank Details Category
				 cstVdtnMycasesPage.BankDetails.click();
					Thread.sleep(2000);
					if (!cstVdtnMycasesPage.BnkDtlsChkbox.isSelected()){
					cstVdtnMycasesPage.BnkDtlsChkbox.click();
					Reporter.log("Bank Details Cicked",true);
					Thread.sleep(1000);
					}

					//Click the checkbox for Other Details Category
					cstVdtnMycasesPage.OtherDetails.click();
					Thread.sleep(2000);
					if (!cstVdtnMycasesPage.OthrDtlsChkbox.isSelected()){
					cstVdtnMycasesPage.OthrDtlsChkbox.click();
					Reporter.log("Other Details Category Checked",true);
					Thread.sleep(1000);

					// Click Status for Verified
				    wait.until(ExpectedConditions.visibilityOf(cstVdtnMycasesPage.Status));
				    Select select_status = new Select(cstVdtnMycasesPage.Status);
				    select_status.selectByVisibleText("Verified");
				    Reporter.log("Status Selected",true);
				    Thread.sleep(1000);
					}
		}
public void ExecuteDBQueries(String connectionUrl,String dbUsername,String dbpassword,String queryType,String companyname) throws InterruptedException{
	String value;
	int value2;
	int value3 = 0;
	Connection conn = null;
	try{

	       Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); 
	       conn = DriverManager.getConnection(connectionUrl,dbUsername,dbpassword);
	       Statement st = conn.createStatement();
	       switch(queryType)
	       {

		       case "ADDCOMPBLACKLIST":
		        	 java.lang.String sql = "SELECT company_name,is_block_listed,company_id FROM `company` WHERE is_block_listed = 0 ORDER BY RAND() LIMIT 1";
		        	 ResultSet rs = st.executeQuery(sql);
		        	 while (rs.next())
		  	         {
		        		 value = rs.getString(1); 
			       	     value2 = rs.getInt(2);
			       	     value3 = rs.getInt(3);
		  	         }
		       case "REMOVECOMPBLACKLIST":	 
		        	 java.lang.String sql1 = "SELECT company_name,is_block_listed,company_id FROM `company` WHERE is_block_listed = 1 ORDER BY RAND() LIMIT 1";
		        	 ResultSet rs1 = st.executeQuery(sql1);
		        	 while (rs1.next())
			  	      {
		        		 value = rs1.getString(1); 
			       	     value2 = rs1.getInt(2);
			       	     value3 = rs1.getInt(3);
			  	      }
		       case "BLACKLISTCOMPANYFLAG":
		    	     java.lang.String sql3 ="SELECT is_block_listed FROM `company` WHERE company_name like"+"'%"+companyname+"%' ORDER BY 1";
		             ResultSet rs3 = st.executeQuery(sql3);
		             while (rs3.next())
		             {
		       	       value2 = rs3.getInt(1); 

		             }
	       }



	} catch (Exception e) 
	{
	   e.printStackTrace();
	   Reporter.log("Error Message : " + e.getMessage(),true);
	   Thread.sleep(2000);    
	}
	//return value3;
}


public void  NavigateMenuItems(String menuName){



}

	public class ReadFileData {

		//public static void main(String[] args) {


		}


	@AfterClass
	public void tearDown() throws SQLException{
		driver.quit();
		//conn.close();


	}
	 */

}