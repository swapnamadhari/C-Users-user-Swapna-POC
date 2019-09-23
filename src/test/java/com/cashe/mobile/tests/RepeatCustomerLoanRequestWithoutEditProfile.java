/*package com.cashe.mobile.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import com.cash.utilities.DataBaseConnection;
import com.cash.utilities.ExtentFactory;
import com.cashe.admin.AdminFlow;
import com.cashe.base.Abstract;
import com.cashe.mobile.pages.GetCashe;
import com.cashe.mobile.pages.LandingPage;
import com.cashe.mobile.pages.MyCashe;
import com.cashe.mobile.pages.Profile;
import com.relevantcodes.extentreports.LogStatus;
import com.saucelabs.saucerest.SauceREST;
import com.ssts.pcloudy.dto.device.MobileDevice;

import io.appium.java_client.AppiumDriver;

public class RepeatCustomerLoanRequestWithoutEditProfile extends Abstract {

	Profile pe;
	GetCashe gc;
	MyCashe myCashe;
	Hashtable<String, String> dataArray;

	String customerId,userId;

	@Factory(dataProviderClass = Abstract.class,dataProvider="dp")
	public RepeatCustomerLoanRequestWithoutEditProfile(Hashtable<String, String> table) {
		// TODO Auto-generated constructor stub
		this.dataArray=table;
	}

	@Test(priority=1)
	public void tt() throws InterruptedException, IOException{
		try{
			ExtentFactory e=new ExtentFactory();
			eReports = e.getInstance();
			eTest = eReports.startTest(dataArray.get("ScenarioDescription"));
			eTest.log(LogStatus.INFO, "Started TestCase :"+dataArray.get("ScenarioDescription"));

			//hit();
			openApplication(dataArray.get("TCNo")+"_"+dataArray.get("ScenarioDescription"));

			pe=new Profile((AppiumDriver) getDriver(),eTest,dataArray);
			gc=new GetCashe((AppiumDriver) getDriver(),eTest,dataArray);
			myCashe=new MyCashe((AppiumDriver) getDriver(), eTest,dataArray);

			eTest.log(LogStatus.PASS, "Sucessfully landed on Landing page ");
			capture("LandingScreen",LogStatus.PASS,eTest);

			loginInToAccount();
			mandatoryForm();
			loanEligibility();
			eTest.log(LogStatus.PASS, "Captured screenshot");
			if(!"No".equalsIgnoreCase(dataArray.get("LoanStatus"))){
				myProfile();
				applyloan();
				capture("Success",LogStatus.PASS,eTest);
				//Update customer loan as Approved in admin
				AdminFlow admin=new AdminFlow();
				//admin.loanStatusInAdmin(customerId,dataArray.get("LoanStatus"));
				admin.loanStatusInAdmin(customerId,dataArray.get("LoanStatus"));
				if("Verified".equalsIgnoreCase(dataArray.get("LoanStatus")))  //update loan status as 4 for Verified
					checkLoanStatusAndUpdateInDB(customerId);
				else{
					//Thread.sleep(5000);
					gc.navigateToGetCashe();
				}
				capture("Loan Approved",LogStatus.PASS,eTest);
			}


		}catch(Throwable e){

			eTest.log(LogStatus.FAIL, e.getMessage());
			capture("Failed",LogStatus.FAIL,eTest);
			e.printStackTrace();
			Assert.fail();
		}finally{
			ITestResult result=null;
				if(result.getStatus()==ITestResult.FAILURE)
					{
						eTest.log(LogStatus.FAIL, result.getThrowable());
					}
				eReports.endTest(eTest);



				eReports.flush();
				//eReports.close();

		}



	}
	@AfterMethod
	public void t(ITestResult result) throws Exception{
		System.out.println("after test");
		if(result.getStatus()==ITestResult.FAILURE)
		{
			eTest.log(LogStatus.FAIL, result.getThrowable());
		}
		eReports.endTest(eTest);
		eReports.flush();

		quitApp();
		getPSession().releaseSessionNow();

	}
	//@AfterTest
	public void getResult(ITestResult result)
	{
		System.out.println("after test");
		if(result.getStatus()==ITestResult.FAILURE)
		{
			eTest.log(LogStatus.FAIL, result.getThrowable());
		}
		eReports.endTest(eTest);
		eReports.flush();

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

	public void checkLoanStatusAndUpdateInDB(String customerId) throws InterruptedException, IOException{
		DataBaseConnection dbCon=new DataBaseConnection();
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
		LandingPage lp=new LandingPage((AppiumDriver) getDriver(), eTest);
		lp.clickApplyNow();
		//lp.acceptPermissions();
		Thread.sleep(3000);
		contextHandle();
		switch (dataArray.get("LoginSource").toLowerCase()) {

		case "linkedin":
			eTest.log(LogStatus.INFO, "User Login through :"+dataArray.get("LoginSource"));
			lp.linkedInAccount();
			break;
		case "google":
			eTest.log(LogStatus.INFO, "User Login through :"+dataArray.get("LoginSource"));
			lp.googleAccount();
			String emailId = lp.selectGoogleAccount(dataArray.get("UserName"),dataArray.get("Password"));
			lp.googleLogout(dataArray.get("ReferralCode"));
			userId=lp.makeCutomerNew(emailId);
			lp.googleLoginAgain(emailId,dataArray.get("ReferralCode"));
			lp.acceptpolicy();
			lp.mobileNumber();
			lp.mobileNumVerify();
			lp.backButton();
			lp.backLogout();
			lp.popupLogout();
			customerId= lp.disableMobileVerification(emailId,dataArray.get("MobileNumber"));
			lp.googleAccount();
			lp.googleLoginAgain(emailId);
			break;
		case "facebook":
			eTest.log(LogStatus.INFO, "User Login through :"+dataArray.get("LoginSource"));
			lp.facebookAccount();
			lp.facebookWeb_App(dataArray.get("UserName"),dataArray.get("Password"),userId,customerId,dataArray.get("MobileNumber") );
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

		pe.employerInfo(dataArray.get("Employer"), dataArray.get("Designation").toLowerCase(), dataArray.get("salary"));


	}

	public void loanEligibility() throws InterruptedException, IOException{
		pe.loanEligible();
		pe.clickMyProfile();
		pe.securityQues();
		pe.enterMPIN();
		pe.enterMPIN();

	}
	public void myProfile()  throws InterruptedException, IOException{
		eTest.log(LogStatus.INFO, "User at MyProfile screen");
		capture("MyProfile",LogStatus.PASS,eTest);
		pe.clickPersonalDetailsTab();
		pe.enterPAN();
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
		eTest.log(LogStatus.INFO, "User completed p3 sucessfully ");
		capture("MyProfileP3",LogStatus.PASS,eTest);
	}
	public void applyloan() throws InterruptedException, IOException {
		try{

			gc.navigateToGetCashe();
			gc.applyingLoan(dataArray.get("LoanType"),dataArray.get("CashePurpose"),dataArray.get("Paytm"),dataArray.get("PromoCode"),dataArray.get("ReferralCode"),dataArray.get("LoanAmount"),customerId,dataArray.get("salary"));
			gc.planDetails(dataArray.get("LoanType"));
			gc.iouDeclaration();
			pe.enterMPIN();
			gc.thankYouForApplying();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
*/