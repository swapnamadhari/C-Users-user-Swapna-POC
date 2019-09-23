package com.cashe.mobile.tests;

import io.appium.java_client.AppiumDriver;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
//import org.testng.annotations.Test;
//import org.testng.annotations.Test;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cash.utilities.DataBaseConnection;
//import com.cash.utilities.//DataBaseConnection;
import com.cashe.admin.AdminFlow;
import com.cashe.base.Abstract;
import com.cashe.mobile.pages.GetCashe;

//import com.cashe.base.pCloudyTest;
import com.cashe.mobile.pages.LandingPage;
import com.cashe.mobile.pages.MyCashe;
import com.cashe.mobile.pages.Profile;
import com.extent.tests.ExtentManager;
import com.extent.tests.ExtentTestManager;
import com.saucelabs.saucerest.SauceREST;
import com.ssts.pcloudy.dto.device.MobileDevice;


public class NewCustomerLoanRequest extends Abstract  {

	Profile pe;
	GetCashe gc;
	MyCashe myCashe;
	Hashtable<String, String> dataArray;

	String customerId,userId;

	ExtentReports exRep;
	ExtentHtmlReporter exHtmlRe;
	ExtentTest eTest;

	@Factory(dataProviderClass = Abstract.class,dataProvider="dp")
	public NewCustomerLoanRequest(Hashtable<String, String> table) {
		// TODO Auto-generated constructor stub
		this.dataArray=table;
	}
	@BeforeClass
	public void b() throws UnknownHostException{
		System.out.println("ExtentManager is ::"+ExtentManager.getInstance());
		if(ExtentManager.getInstance()==null){
			ExtentManager.createInstance("../Reports/index.html");
			System.out.println("cameee");
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

			eTest.log(Status.PASS, "Sucessfully landed on Landing page ");
			capture(Status.INFO,dataArray.get("TCNo"),eTest);

			loginInToAccount();
			mandatoryForm();
			loanEligibility();
			eTest.log(Status.PASS, "Captured screenshot");
			if(!"No".equalsIgnoreCase(dataArray.get("LoanStatus"))){
				myProfile();
				/*if(!"No".equalsIgnoreCase(dataArray.get("ValidateEsign"))) {
					gc.clickOnPlan(dataArray.get("LoanType"));
					gc.clickOnEsign();
				}else {*/
				applyloan();
				capture(Status.PASS,dataArray.get("TCNo"),eTest);
				//Update customer loan as Approved in admin
				AdminFlow admin=new AdminFlow();
				admin.loanStatusInAdmin(customerId,dataArray.get("LoanStatus"));
				if("Verified".equalsIgnoreCase(dataArray.get("LoanStatus")))  //update loan status as 4 for Verified
					checkLoanStatusAndUpdateInDB(customerId);
				else{
					//Thread.sleep(5000);
					gc.navigateToGetCashe();
				}
				capture(Status.PASS,dataArray.get("TCNo"),eTest);
				//eMandate
				if((dataArray.get("LoanType").contains("90") || dataArray.get("LoanType").contains("180")) && dataArray.get("eMandate").equalsIgnoreCase("Yes")){
					myCashe.doeMandate(customerId); 
				}
				//}
			}


		}catch(Throwable e){

			eTest.log(Status.FAIL, e.getMessage());
			capture(Status.FAIL,dataArray.get("TCNo"),eTest);
			e.printStackTrace();

			Assert.fail();
		}/*finally{
			ITestResult result=null;
				if(result.getStatus()==ITestResult.FAILURE)
					{
						eTest.log(LogStatus.FAIL, result.getThrowable());
					}
				eReports.endTest(eTest);



				eReports.flush();
				//eReports.close();

		}*/



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
		if(getPSession()!=null){
			getPSession().releaseSessionNow();
		}
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

	public synchronized boolean hit() throws InterruptedException {
		String whoseTurn = null;
		String x = Thread.currentThread().getName();
		System.out.println("ThreadName is ::"+x);
		Thread.sleep(5000);
		if (whoseTurn == null) {
			whoseTurn = x;

		}
		return true;
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
			 
			//openiOSSimulator();

		}
	}

	public void checkLoanStatusAndUpdateInDB(String customerId) throws InterruptedException, IOException{
		//DataBaseConnection dbCon=new //DataBaseConnection();
		dbCon.dataBaseUpdate("UPDATE loan_request SET loan_status_id = 4 ,loan_transferred_date = NOW() WHERE customer_id = "+customerId+" ORDER BY loan_request_id DESC LIMIT 1");
		gc.navigateToGetCashe();
		myCashe.navigateToMyCashe();

	}
	public void endResult(){

		String jobID = ((AppiumDriver<?>)driver.get()).getSessionId().toString();
		SauceREST client = new SauceREST("username", "key");
		Map<String, Object>sauceJob = new HashMap<String, Object>();
		sauceJob.put("name", "Test method: "+dataArray.get("ScenarioDescription"));
		//// if(result.isSuccess()) {
		client.jobPassed(jobID);
		//  } else {
		//     client.jobFailed(jobID);
		//   }
		client.updateJobInfo(jobID, sauceJob);
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

		if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			pe.enterMPIN();
		}else {
			pe.confirmMPIN();
		}

	}
	public void myProfile()  throws InterruptedException, IOException{
		eTest.log(Status.INFO, "User at MyProfile screen");
		capture(Status.PASS,dataArray.get("TCNo"),eTest);
		pe.clickPersonalDetailsTab();
		pe.enterPAN();
		pe.enterAadhar();
		pe.enterAddress(dataArray.get("Address"),dataArray.get("Locality").toLowerCase(),dataArray.get("Pincode"),dataArray.get("State").toLowerCase());
		pe.enterSaveP();


		pe.uploadPhotoProofsDb(customerId, userId);

		pe.clickEmploymentDetailsTab();
		pe.disableOffEmail(customerId);
		pe.employerData(dataArray.get("Landline"), dataArray.get("OfcEmail"));
		pe.ofcAddress(dataArray.get("AddressOfc"),dataArray.get("LocalityOfc").toLowerCase(),dataArray.get("PincodeOfc"),dataArray.get("CityOfc").toLowerCase(),dataArray.get("StateOfc"));
		pe.empSave();

		pe.clickBankDetailsTab();
		pe.enterBankDetails(dataArray.get("BankName"), dataArray.get("AccNo"), dataArray.get("CAccNo"), dataArray.get("Ifsc").toLowerCase());

		pe.profileCompletePopup();
		eTest.log(Status.INFO, "User completed p3 sucessfully ");
		capture(Status.PASS,dataArray.get("TCNo"),eTest);
	}
	public void applyloan() throws InterruptedException, IOException {
		try{
			gc.updatePaytmMobileno(customerId);
			gc.navigateToGetCashe();
			capture(Status.PASS,dataArray.get("TCNo"),eTest);
			pe.slqValidation(customerId,dataArray.get("LoanType"));
			gc.applyingLoan(dataArray.get("LoanType"),dataArray.get("CashePurpose"),dataArray.get("Paytm"),dataArray.get("PromoCode"),dataArray.get("ReferralCode"),dataArray.get("LoanAmount"),customerId,dataArray.get("salary"));
			capture(Status.INFO,dataArray.get("TCNo"),eTest);
			gc.planDetails(dataArray.get("LoanType"));
			gc.iouDeclaration();
			pe.enterMPIN();
			gc.thankYouForApplying();
			capture(Status.INFO,dataArray.get("TCNo"),eTest);
		}catch(Exception e){
			capture(Status.FAIL,dataArray.get("TCNo"),eTest);
			e.printStackTrace();
		}
	}


}
