package com.cashe.mobile.tests;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cash.utilities.DataBaseConnection;
import com.cashe.base.Abstract;
import com.cashe.mobile.pages.GetCashe;
import com.cashe.mobile.pages.LandingPage;
import com.cashe.mobile.pages.MoreOptions;
import com.cashe.mobile.pages.MyCashe;
import com.cashe.mobile.pages.Profile;
import com.extent.tests.ExtentManager;
import com.extent.tests.ExtentTestManager;
import com.ssts.pcloudy.dto.device.MobileDevice;

import io.appium.java_client.AppiumDriver;

public class NewCustomerFreshDesk extends Abstract  {

	Profile pe;
	GetCashe gc;
	MyCashe myCashe;
	MoreOptions mo;
	Hashtable<String, String> dataArray;

	String customerId,userId;

	ExtentReports exRep;
	ExtentHtmlReporter exHtmlRe;
	ExtentTest eTest;

	@Factory(dataProviderClass = Abstract.class,dataProvider="dp")
	public NewCustomerFreshDesk(Hashtable<String, String> table) {
		// TODO Auto-generated constructor stub
		this.dataArray=table;
	}
	@BeforeClass
	public void b() throws UnknownHostException{
		//ExtentFactory e=new ExtentFactory();
		//Thread.sleep(8000);
		//eReports = e.getInstance();
		if(ExtentManager.getInstance()==null){
			ExtentManager.createInstance("../Reports/index.html");
		}

	}


	@Test(priority=1)
	public void tt() throws InterruptedException, IOException{
		try{
			System.out.println(dataArray.get("TCNo")+"_"+dataArray.get("ScenarioDescription"));

			eTest=ExtentTestManager.createTest(dataArray.get("TCNo")+"_"+dataArray.get("ScenarioDescription"));

			//hit();
			openApplication(dataArray.get("TCNo")+"_"+dataArray.get("ScenarioDescription"));

			pe=new Profile((AppiumDriver) getDriver(),eTest,dataArray,dbCon);
			gc=new GetCashe((AppiumDriver) getDriver(),eTest,dataArray,dbCon);
			myCashe=new MyCashe((AppiumDriver) getDriver(), eTest,dataArray,dbCon);
			mo=new MoreOptions((AppiumDriver) getDriver(), eTest,dbCon);
			
			eTest.log(Status.PASS, "Sucessfully landed on Landing page ");
			capture(Status.INFO,dataArray.get("TCNo"),eTest);

			loginInToAccount();
			mandatoryForm();
			loanEligibility();
			eTest.log(Status.PASS, "Captured screenshot");
			mo.clickMore();
			mo.contactUs();
		}catch(Throwable e){

			eTest.log(Status.FAIL, e.getMessage());
			capture(Status.FAIL,dataArray.get("TCNo"),eTest);
			e.printStackTrace();
			Assert.fail();
		}
	}
	@AfterMethod
	public void t(ITestResult result) throws Exception{
		System.out.println("after test");
		if(result.getStatus()==ITestResult.FAILURE)
		{
			ExtentTestManager.getTest().fail(result.getThrowable());
			//eTest.log(LogStatus.FAIL, resul.getThrowable());
		}
		if(result.getStatus()==ITestResult.SUCCESS)
		{
			ExtentTestManager.getTest().pass("Test Case Passed");

			//eTest.log(LogStatus.PASS, "Test Case Passed");
		}
		if(result.getStatus()==ITestResult.SKIP)
		{
			//	eTest.log(LogStatus.SKIP, "Test Case Skipped");
		}

		ExtentManager.getInstance().flush();
		quitApp();
		getPSession().releaseSessionNow();
		//eReports.endTest(eTest);
		//eReports.flush();


	}
	//@AfterTest
	public void getResult(ITestResult result)
	{
		System.out.println("after test");
		if(result.getStatus()==ITestResult.FAILURE)
		{
			eTest.log(Status.FAIL, result.getThrowable());
		}
		//eReports.endTest(eTest);
		///eReports.flush();

	}
	public void openApplication(String tcName) throws Exception{


		//	pro=new Properties();
		initilize();
		 dbCon=new DataBaseConnection();

		//openAndroidApp();

		if("Local".equalsIgnoreCase(System.getProperty("environmemt"))){
			openAndroidApp();

		}else if("SauceLabs".equalsIgnoreCase(System.getProperty("environmemt"))){
			openSauceLabs(dataArray.get("ScenarioDescription")); 
		}else{
			MobileDevice device=runExecutionOnPCloudy(dataArray.get("Platform"),tcName);
			eTest.assignAuthor("PlatformName :"+device.platform);eTest.assignAuthor("DeviceName :"+device.manufacturer);
			eTest.assignAuthor("DeviceModel :"+device.model);eTest.assignAuthor("DeviceVersion :"+device.version); eTest.assignAuthor("DeviceScreenSize :"+device.display_area);


		}
	}
	public void loginInToAccount() throws InterruptedException, IOException{
		LandingPage lp=new LandingPage((AppiumDriver) getDriver(), eTest,dataArray,dbCon);
		lp.clickApplyNow();
		//lp.acceptPermissions();
		Thread.sleep(3000);
		contextHandle();
		switch (dataArray.get("LoginSource").toLowerCase()) {

		case "linkedin":
			eTest.log(Status.INFO, "User Login through :"+dataArray.get("LoginSource"));
			lp.linkedInAccount();
			break;
		case "google":
			eTest.log(Status.INFO, "User Login through :"+dataArray.get("LoginSource"));
			lp.googleAccount();
			String emailId = lp.selectGoogleAcct(dataArray.get("Platform"));//lp.selectGoogleAccount(dataArray.get("UserName"),dataArray.get("Password"));
			lp.googleLogout(dataArray.get("ReferralCode"));
			userId=lp.makeCutomerNew(emailId);
			lp.googleLoginAgain(emailId,dataArray.get("ReferralCode"));
			lp.acceptpolicy();
			String mobileNo=lp.mobileNumber();
			lp.mobileNumVerify();
			lp.backButton();
			lp.backLogout();
			lp.popupLogout();
			customerId= lp.disableMobileVerification(emailId,mobileNo);
			lp.googleAccount();
			lp.googleLoginAgain(emailId);
			break;
		case "facebook":
			eTest.log(Status.INFO, "User Login through :"+dataArray.get("LoginSource"));
			lp.facebookAccount();
			lp.facebookWeb_App(dataArray.get("UserName"),dataArray.get("Password"),userId,customerId );
			break;
		default:
			logger.info("Data error :"+dataArray.get("LoginSource"));
		}

		//lp.acceptpolicy();
	}

	//Filling Mandatory form
	public void mandatoryForm() throws InterruptedException, IOException{
		pe.enterFullName(dataArray.get("FullName"));
		pe.maleRadio();
		pe.selectDOB();
		pe.selectEducation(dataArray.get("Education").toLowerCase());
		pe.selectAccommodation(dataArray.get("Accommodation").toLowerCase());
		pe.swipeTo();
		pe.enterCity(dataArray.get("City"));

		pe.employerInfo(dataArray.get("Employer"), dataArray.get("Designation").toLowerCase(), dataArray.get("salary"),customerId);


	}

	public void loanEligibility() throws InterruptedException, IOException{
		pe.loanEligible();
		pe.clickMyProfile();
		pe.securityQues();
		pe.enterMPIN();
		pe.enterMPIN();

	}


}
