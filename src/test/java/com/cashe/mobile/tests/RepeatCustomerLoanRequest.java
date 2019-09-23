package com.cashe.mobile.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.cash.utilities.DataBaseConnection;
import com.cashe.admin.AdminFlow;
import com.cashe.base.Abstract;
import com.cashe.mobile.pages.GetCashe;
import com.cashe.mobile.pages.LandingPage;
import com.cashe.mobile.pages.MyCashe;
import com.cashe.mobile.pages.Profile;
import com.extent.tests.ExtentManager;
import com.extent.tests.ExtentTestManager;
import com.saucelabs.saucerest.SauceREST;
import com.ssts.pcloudy.dto.device.MobileDevice;

import io.appium.java_client.AppiumDriver;

public class RepeatCustomerLoanRequest extends Abstract{

	public  LandingPage lp;
	public  Profile pe;
	public  GetCashe gc;
	public  MyCashe myCashe;
	public  Hashtable<String, String> dataArray;
	public  boolean result=false;
	public  String customerId,userId;

	public  ExtentReports exRep;
	public  ExtentHtmlReporter exHtmlRe;
	public  ExtentTest eTest;
	public  Method method;

	@Factory(dataProviderClass = Abstract.class,dataProvider="dp")
	public RepeatCustomerLoanRequest(Hashtable<String, String> table) throws UnknownHostException {
		this.dataArray=table;	
		System.out.println("Scenarios are"+dataArray.size() +" and name is :"+dataArray.get("ScenarioDescription"));
	}

	@BeforeClass
	public static void b() throws UnknownHostException{

		if(ExtentManager.getInstance()==null){
			ExtentManager.createInstance("../Reports/index.html");
		}
	}

	@Test
	public void tt() throws Exception{
		try{
			eTest=ExtentTestManager.createTest(dataArray.get("TCNo")+"_"+dataArray.get("ScenarioDescription"));
			openApplication(dataArray.get("TCNo")+"_"+dataArray.get("ScenarioDescription"));
			logger.info("Successufully opened application");

			lp=new LandingPage((AppiumDriver) getDriver(),eTest,dataArray,dbCon);
			pe=new Profile((AppiumDriver) getDriver(),eTest,dataArray,dbCon);
			gc=new GetCashe((AppiumDriver) getDriver(),eTest,dataArray,dbCon);
			myCashe=new MyCashe((AppiumDriver) getDriver(),eTest,dataArray,dbCon);

			capture(Status.INFO,"ApplicationLaunched",eTest);
			loginInToAccount();
			mandatoryForm();
			loanEligibility();
			capture(Status.PASS,"LoanEligibility",eTest);

			if(!"No".equalsIgnoreCase(dataArray.get("LoanStatus"))){
				myProfile();
				applyloan();
				capture(Status.PASS,dataArray.get("TCNo"),eTest);
				//Update customer loan as Approved in Admin
				AdminFlow admin=new AdminFlow();
				admin.loanStatusInAdmin(customerId,dataArray.get("LoanStatus"));
				Thread.sleep(4000);
				capture(Status.PASS,dataArray.get("TCNo"),eTest);
				//eMandate
				if((dataArray.get("LoanType").contains("90") || dataArray.get("LoanType").contains("180")) && dataArray.get("eMandate").equalsIgnoreCase("Yes")){
					myCashe.doeMandate(customerId); 
				}
				if("Verified".equalsIgnoreCase(dataArray.get("LoanStatus")))  //update loan status as 4 for Verified
					checkLoanStatusAndUpdateInDB(customerId);
				else{
					gc.navigateToGetCashe();
				}
				myCashe.loanRepayment("CompleteRepay", customerId);

				if(!dataArray.get("EditProfile").equalsIgnoreCase("No")){
					pe.EditProfile(lp);
					applyloan();
					admin.loanStatusInAdmin(customerId,dataArray.get("LoanStatus"));
					if("Verified".equalsIgnoreCase(dataArray.get("LoanStatus")))  //update loan status as 4 for Verified
						checkLoanStatusAndUpdateInDB(customerId);
					else{ 
						gc.navigateToGetCashe();
					}

				}else{
					applyloan();
					checkLoanStatusAndUpdateInDB(customerId);
				}
				capture(Status.INFO,dataArray.get("TCNo"),eTest);
				myCashe.changeCustomerStatus(customerId, dataArray.get("RepayDate"),dataArray.get("LoanType"));
				myCashe.loanRepayment(dataArray.get("Repay"), customerId);
				capture(Status.INFO,dataArray.get("TCNo"),eTest);


				//applyloan
				//applyloan();
				//admin.loanStatusInAdmin(customerId,dataArray.get("LoanStatus"));
				//check repay
				//check repay date
				//checnge date accordingly with repay date
				//validation
				//result=true;


			}

		}catch(Throwable e){
			e.printStackTrace();
			capture(Status.FAIL,dataArray.get("TCNo"),eTest);
			eTest.fail(e.getMessage());
			throw e;
		}
	}

	@AfterMethod
	public synchronized void t(ITestResult resul) throws Exception{
		System.out.println("after Method");
		if(resul.getStatus()==ITestResult.FAILURE){
			ExtentTestManager.getTest().fail("TestCase Failed");
			eTest.log(Status.FAIL, resul.getThrowable());
		}
		if(resul.getStatus()==ITestResult.SUCCESS){
			// ExtentTestManager.getTest().pass("Test Case Passed");

		}
		if(resul.getStatus()==ITestResult.SKIP){
			ExtentTestManager.getTest().skip("Test Case Skipped");
		}

		ExtentManager.getInstance().flush();
		quitApp();

		if(getPSession()!=null) {
			getPSession().releaseSessionNow();
		}

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
	public void openApplication(String tcName ) throws Exception{
		initilize();	
		dbCon=new DataBaseConnection();
		if("Local".equalsIgnoreCase(System.getProperty("environmemt"))){
			openAndroidApp();

		}else if("SauceLabs".equalsIgnoreCase(System.getProperty("environmemt"))){
			openSauceLabs(dataArray.get("ScenarioDescription")); 
		}else{
			  MobileDevice device=runExecutionOnPCloudy(dataArray.get("Platform"),tcName);
			  eTest.assignAuthor("PlatformName :"+device.platform);eTest.assignAuthor("DeviceName :"+device.manufacturer);
			  eTest.assignAuthor("DeviceModel :"+device.model);eTest.assignAuthor("DeviceVersion :"+device.version); eTest.assignAuthor("DeviceScreenSize :"+device.display_area);
			 
			// openiOSApp();
			//openiOSSimulator();
			// openAndroidApp();
		}
	}

	public void checkLoanStatusAndUpdateInDB(String customerId) throws InterruptedException, IOException{
		Thread.sleep(20000);
		//DataBaseConnection dbCon=new //DataBaseConnection();
		dbCon.dataBaseUpdate("UPDATE loan_request SET loan_status_id = 4 ,loan_transferred_date = NOW() WHERE customer_id = "+customerId+" ORDER BY loan_request_id DESC LIMIT 1");
		System.out.println("Loan status updated successfully"+ "UPDATE loan_request SET loan_status_id = 4 ,loan_transferred_date = NOW() WHERE customer_id = "+customerId+" ORDER BY loan_request_id DESC LIMIT 1");
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
		lp.clickApplyNow();
		//lp.acceptPermissions();
		capture(Status.PASS,"LoginScreen",eTest);
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
			capture(Status.PASS,"GoogleLogin",eTest);
			String emailId = lp.selectGoogleAcct(dataArray.get("Platform"));//lp.selectGoogleAccount(dataArray.get("UserName"),dataArray.get("Password"));
			lp.googleLogout(dataArray.get("ReferralCode"));
			userId=lp.makeCutomerNew(emailId);
			lp.googleLoginAgain(emailId,dataArray.get("ReferralCode"));
			// if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			lp.acceptpolicy();
			//  }
			String mobileNo=lp.mobileNumber();
			capture(Status.PASS,"MobileNumber",eTest);
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
		//	eTest.log(LogStatus.INFO, "User at MyProfile screen");
		//	capture("MyProfile",LogStatus.PASS,eTest);

		pe.clickPersonalDetailsTab();
		pe.enterPAN();
		pe.enterAadhar();
		pe.enterAddress(dataArray.get("Address"),dataArray.get("Locality").toLowerCase(),dataArray.get("Pincode"),dataArray.get("State").toLowerCase());
		capture(Status.INFO,"PersonalDetails",eTest);
		pe.enterSaveP();


		pe.uploadPhotoProofsDb(customerId, userId);

		pe.clickEmploymentDetailsTab();
		pe.disableOffEmail(customerId);
		pe.employerData(dataArray.get("Landline"), dataArray.get("OfcEmail"));
		pe.ofcAddress(dataArray.get("AddressOfc"),dataArray.get("LocalityOfc").toLowerCase(),dataArray.get("PincodeOfc"),dataArray.get("CityOfc").toLowerCase(),dataArray.get("StateOfc"));
		capture(Status.INFO,"EmploymentDetails",eTest);
		pe.empSave();

		pe.clickBankDetailsTab();
		pe.enterBankDetails(dataArray.get("BankName"), dataArray.get("AccNo"), dataArray.get("CAccNo"), dataArray.get("Ifsc").toLowerCase());
		capture(Status.INFO,"BankDetails",eTest);

		pe.profileCompletePopup();

		//  pe.validateEsign(customerId);
		//	eTest.log(LogStatus.INFO, "User completed p3 sucessfully ");
		//capture("MyProfileP3",LogStatus.PASS,eTest);
	}
	public void applyloan() throws InterruptedException, IOException {
		try{
			gc.updatePaytmMobileno(customerId);
			//pe.updateSLQScore(customerId); //need to remove if SLQ engine is available
			gc.navigateToGetCashe();
			capture(Status.PASS,dataArray.get("TCNo"),eTest);
			// pe.slqValidation(customerId,dataArray.get("LoanType"));

			gc.applyingLoan(dataArray.get("LoanType"),dataArray.get("CashePurpose"),dataArray.get("Paytm"),dataArray.get("PromoCode"),dataArray.get("ReferralCode"),dataArray.get("LoanAmount"),customerId,dataArray.get("salary"));
			capture(Status.INFO,dataArray.get("TCNo"),eTest);
			gc.planDetails(dataArray.get("LoanType"));
			capture(Status.INFO,"iouDeclaration",eTest);
			gc.iouDeclaration();
			pe.enterMPIN();
			gc.thankYouForApplying();
			capture(Status.INFO,dataArray.get("TCNo"),eTest);
			myCashe.perfios_addtag(customerId,dataArray.get("LoanAmount"));
		}catch(Exception e){
			capture(Status.FAIL,dataArray.get("TCNo"),eTest);
			e.printStackTrace();
		}
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
		try {
			System.out.println("exTest at capture "+exTest);
			// Thread.sleep(5000);
			exTest.info(screenShotName.split("_")[0], MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
		}catch(Exception e) {
			try {
				exTest.info(screenShotName.split("_")[0], MediaEntityBuilder.createScreenCaptureFromPath(dest).build());
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
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



}
