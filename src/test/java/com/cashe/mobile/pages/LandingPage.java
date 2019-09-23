package com.cashe.mobile.pages;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.cash.utilities.DataBaseConnection;
import com.cashe.base.BasePage;
/*import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;*/

public class LandingPage extends BasePage {

	public	AppiumDriver driver;
	public	Hashtable<String, String> dataArray;
	public ExtentTest eTest;
	public	boolean passcode=false;

	@AndroidFindBy(id="btn_intro1_apply")
	@iOSFindBy(accessibility="APPLY NOW")
	public WebElement  btnApplyNow;

	@AndroidFindBy(id="btn_intro1_how_cashe")
	public WebElement  btnCasheWorks;

	@AndroidFindBy(id="btn_linked_in")
	public WebElement btnLinkedIn;

	@AndroidFindBy(id="co.tslc.cashe.android:id/img_google")
	@iOSFindBy(accessibility="GoogleLogin")
	public WebElement btnGoogle;

	@AndroidFindBy(id="img_facebook")
	@iOSFindBy(accessibility="FBLogin")
	public WebElement btnFacebook;

	@AndroidFindBy(id="co.tslc.cashe.android:id/cb_referral")
	public WebElement chkBoxRef;

	@AndroidFindBy(id = "ed_referral")
	public WebElement txtRefferalCode;

	@AndroidFindBy(id = "tv_skip_referral")
	public WebElement btnRefSkip;

	@AndroidFindBy(id = "btn_apply_referral")
	public WebElement btnRefApply;

	@AndroidFindBy(id = "co.tslc.cashe.android:id/tv_referral_congrats_ok")
	public WebElement btnRefSuccess;

	//@CacheLookup
	@AndroidFindBy(id="com.android.packageinstaller:id/permission_allow_button")
	@iOSFindBy(accessibility="ALLOW")
	public WebElement btnAllow;

	@AndroidFindBy(id="android:id/button2")
	public WebElement btnAddAccount;

	@AndroidFindBy(id="cb_i_accept")
	@iOSFindBy(xpath="(//XCUIElementTypeButton[@name='checkboxoff'])[2]")
	public WebElement checkAccept;

	@AndroidFindBy(id="btn_i_accept")
	@iOSFindBy(accessibility="Proceed")
	public WebElement btnProceedPolicy;

	@AndroidFindBy(id = "btn_logout")
	@iOSFindBy(accessibility="NavBarLeft")
	public WebElement btnBackLogout; 

	@AndroidFindBy(id = "popup_positive_button")
	@iOSFindBy(accessibility="LOGOUT")
	public WebElement btnPopupLogout;

	@AndroidFindBy(xpath = "//*[@resource-id='identifierId']")
	public WebElement txtEmailid;

	@AndroidFindBy(xpath ="(//android.view.View[@content-desc='NEXT'])[2]")
	public WebElement btnNext;

	//@AndroidFindBy(xpath = "//*[@resource-id='password']")
	@AndroidFindBy(xpath = "//android.widget.EditText")
	public WebElement txtPassword;

	@AndroidFindBy(xpath = "//android.widget.Button")
	public WebElement btnAgree;

	@AndroidFindBy(xpath = "//*[@resource-id='next']")
	public WebElement btnAccept;

	@AndroidFindBy(id = "com.google.android.gms:id/cancel_button")
	public WebElement btnPopUpDeny;

	@iOSFindBy(id="submit_approve_access")
	@AndroidFindBy(id = "com.google.android.gms:id/accept_button")
	public WebElement btnPopUpAllow;

	@AndroidFindBy(id = "input_Mobile")
	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[1]")
	public WebElement txtMobileNum;

	@AndroidFindBy(id = "mobileVerify")
	@iOSFindBy(accessibility="VerifyMobile")
	public WebElement btnMobileNumVerify;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
	@iOSFindBy(accessibility="leftArrow")
	public WebElement btnBack;

	@iOSFindBy(accessibility="Cancel")
	public WebElement btnCancel;


	@AndroidFindBy(id="popup_positive_button")
	//@iOSFindBy(xpath="")
	public WebElement  btnPopUpOk;

	@AndroidFindBy(xpath = "//*[@resource-id='identifierNext']")
	public WebElement btnEmailNext;

	@AndroidFindBy(xpath ="//*[@resource-id='passwordNext']")
	public WebElement btnPasswordNext;

	@AndroidFindBy(id = "m_login_email")
	public WebElement txtFbUserName;

	@AndroidFindBy(id = "m_login_password")
	public WebElement txtFbPwd;

	@AndroidFindBy(id = "u_0_5")
	public WebElement btnfbLogin;

	@AndroidFindBy(id = "co.tslc.cashe.android:id/toolbar")
	public WebElement pagePrivacyPolicy;

	//@AndroidFindBy(id = "com.facebook.katana:id/login_username")
	@AndroidFindBy(xpath = "(//android.widget.EditText)[1]")
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name='main']/XCUIElementTypeTextField")
	public WebElement txtFbAppUserName;

	//@AndroidFindBy(id = "com.facebook.katana:id/login_password")
	@AndroidFindBy(xpath = "(//android.widget.EditText)[2]")
	@iOSFindBy(xpath="//XCUIElementTypeOther[@name='main']/XCUIElementTypeSecureTextField")
	public WebElement txtFbAppPwd;

	//@AndroidFindBy(id = "com.facebook.katana:id/login_login")
	@AndroidFindBy(xpath = "(//android.widget.Button)[1]")
	@iOSFindBy(accessibility="Log In")
	public WebElement btnFbAppLogin;

	//@AndroidFindBy(xpath = "//android.widget.Button[@content-desc='Continue ']")
	@AndroidFindBy(id = "u_0_3")
	public WebElement btnFbContinue1;      

	@AndroidFindBy(id="u_0_9")
	public WebElement btnFbContinue2;      


	public LandingPage(AppiumDriver driver,ExtentTest eTest,Hashtable<String, String> dataArray,DataBaseConnection dbCon) {
		this.driver = driver;
		this.eTest=eTest;
		this.dataArray=dataArray;
		this.dbCon=dbCon;
		setDriver(driver);
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);

	}


	public void clickApplyNow() throws FileNotFoundException, InterruptedException{

		if(isElementClickable(driver, btnApplyNow))
			clickonElement(driver, btnApplyNow, "Sucessfully clicked on Apply now button", "Unable to click on Apply Now button ");

	}
	public void linkedInAccount() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnLinkedIn))
			clickonElement(driver, btnLinkedIn, "Sucessfully clicked on LinkedIn button", "Unable to click on LinkedIn button");


	}

	public void googleAccount() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnGoogle)) {
			clickonElement(driver, btnGoogle, "Sucessfully clicked on Google button", "Unable to click on Google button");
		}
		else {
			logger.info("Unbale to find Google button");
			if(isElementClickable(driver, btnGoogle)) {
				clickonElement(driver, btnGoogle, "Sucessfully clicked on Google button", "Unable to click on Google button");
			}else {
				logger.info("Unbale to find Google button second time");
			}
		}

	}
	public void facebookAccount() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnFacebook))
			clickonElement(driver, btnFacebook, "Sucessfully clicked on Facebook button", "Unable to click on Facebook button");

	}

	public void acceptPermissions() throws InterruptedException, IOException{
		switchNative();

		if(isElementClickable(driver, btnAllow)){
			for(int i=1;i<8;i++){
				try{
					if(isElementClickable(driver, btnAllow)){
						clickonElement(driver, btnAllow, "", "");
					}else{
						break;
					}
				}catch(Exception e){

				}
			}
		}
		//scrollToElement(btnAddAccount);
	}

	public String selectGoogleAcct(String platform) throws FileNotFoundException, InterruptedException{
		waitFor(10);
		String mailId=null;
		List<WebElement>ele1 = null;
		if(platform.toLowerCase().contains("android")) {	
			ele1=driver.findElements(By.xpath("//android.widget.TextView"));
		}else {
			//switchWebView();
			// ele1=driver.findElements(By.xpath("//*[@id='view_container']/form//li//p"));

			waitFor(5);
			try {
				if(((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'@gmail.com')]")).isDisplayed()) {
					((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'@gmail.com')]")).click();
					waitFor(3);
					btnAllow.click();

				}}catch(Exception e) {

				}
			try {
				if(((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Continue']")).isDisplayed()){

					((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Continue']")).click();
					waitFor(20);
					mailId=dataArray.get("UserName");
					System.out.println("entrting mail");
					System.out.println("First is:"+((IOSDriver)getDriver()).getPageSource());

					//switchWebView();
					//System.out.println("Last is:"+((IOSDriver)getDriver()).getPageSource());
					((IOSDriver)getDriver()).findElement(By.xpath("//*[contains(@name,'Email')]")).click();
					System.out.println("entrting mail1");
					((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeTextField[@name='Email or phone']")).sendKeys(dataArray.get("UserName"));
					System.out.println("entrting mail end");
					driver.hideKeyboard();
					//((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Next']")).click();
					// Thread.sleep(4000);                                //XCUIElementTypeSecureTextField[@name="Enter your password"]
					 ((IOSDriver)getDriver()).findElement(By.xpath("//*[contains(@name,'Enter your password')]")).click();
					((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeSecureTextField[@name='Enter your password']")).sendKeys(dataArray.get("Password"));
					((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Next']")).click();
					waitFor(5);
					swipeTo();
					btnAllow.click();
					waitFor(5);
					((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='OK']")).click();
					waitFor(5);
					try {
						((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Passcode']")).click();
						waitFor(3);
						btnBack.click();
						passcode= true;
					}catch(Exception e) {
						passcode=false;
					}
					//System.out.println(getDriver().getPageSource());
				}
			}catch(Exception e1){
				System.out.println("not entrting mail end2");

				try{
					passcode= true;
					backLogout();
					popupLogout();
				}catch(Exception e2) {
					((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Passcode']")).click();

				}
			}



		}
		//	List<String> accounts = new ArrayList<String>();
		if(platform.toLowerCase().contains("android")) {	
			for (WebElement e : ele1) {
				mailId=e.getText();
				if(e.getText().contains("gmail.com")){
					System.out.println("EmailId is::"+mailId);
					e.click();
					break;
				}
			}
		}
		swipeTo();
		if(isElementDisplayed(btnPopUpAllow))
			clickonElement(driver, btnPopUpAllow, "Sucessfully clicked on Allow button", "Unable to click on Allow button");

		return mailId;
	}

	public String selectGoogleAccount(String userName, String password) throws InterruptedException, FileNotFoundException{
		//waitUntilElementClickable(driver, element);
		waitFor(10);

		if(isElementDisplayed(btnAddAccount)){
			//scrollToElement(btnAddAccount);
			List<WebElement> ele1=driver.findElements(By.xpath("//android.widget.TextView"));
			List<String> accounts = new ArrayList<String>();
			for (WebElement e : ele1) {
				accounts.add(e.getText());
			}

			if(accounts.contains(userName)){
				for (WebElement e : ele1){
					String account = e.getText();
					if(userName.equalsIgnoreCase(account)){
						e.click();
						break;
					}
				}
				return userName;    
			}else{


				if(isElementClickable(driver, btnAddAccount))
					clickonElement(driver, btnAddAccount, "Sucessfully clicked on Add Account button", "Unable to click on Add Account button");
				int sec = 20;
				//waitFor(10);
				contextHandle();
				if(isElementPresent(txtEmailid,sec))
					mobileSetElement(driver,txtEmailid , userName, "Sucessfully entered email id", "Unable to enter email id");

				clickonElement(driver, btnEmailNext, "Sucessfully clicked on next button", "Unable to click on next button");

				if(isElementPresent(txtPassword,sec))
					mobileSetElement(driver,txtPassword , password, "Sucessfully entered password", "Unable to enter password");

				clickonElement(driver, btnPasswordNext, "Sucessfully clicked on Next button", "Unable to click on next button");

				if(isElementClickable(driver, btnAgree))
					clickonElement(driver, btnAgree, "Sucessfully clicked on Accept button", "Unable to click on Accept button");

				if(isElementClickable(driver, btnAccept))
					clickonElement(driver, btnAccept, "Sucessfully clicked on Accept button", "Unable to click on Accept button");

				if(isElementDisplayed(btnPopUpAllow))
					clickonElement(driver, btnPopUpAllow, "Sucessfully clicked on Allow button", "Unable to click on Allow button");

				return userName;
			}
		}else if(isElementPresent(txtEmailid,15)){
			mobileSetElement(driver,txtEmailid , userName, "Sucessfully entered email id", "Unable to enter email id");

			clickonElement(driver, btnEmailNext, "Sucessfully clicked on next button", "Unable to click on next button");

			if(isElementPresent(txtPassword,15))
				mobileSetElement(driver,txtPassword , password, "Sucessfully entered password", "Unable to enter password");

			clickonElement(driver, btnPasswordNext, "Sucessfully clicked on Next button", "Unable to click on next button");

			if(isElementClickable(driver, btnAgree))
				clickonElement(driver, btnAgree, "Sucessfully clicked on Accept button", "Unable to click on Accept button");

			if(isElementClickable(driver, btnAccept))
				clickonElement(driver, btnAccept, "Sucessfully clicked on Accept button", "Unable to click on Accept button");

			if(isElementDisplayed(btnPopUpAllow))
				clickonElement(driver, btnPopUpAllow, "Sucessfully clicked on Allow button", "Unable to click on Allow button");

			return userName;
		}
		else{
			List<WebElement> ele=driver.findElements(By.xpath("//android.widget.TextView"));
			String email_id = ele.get(4).getText();
			logger.info("Email id from ui list is "+email_id);
			clickonElement(driver, ele.get(4), "Sucessfully clicked on login email id", "Unable to click on login email id");

			return email_id ;

		}
	}

	public void selectFacebookAccountWeb(String userName, String password) throws InterruptedException, FileNotFoundException{

		mobileSetElement(driver,txtFbUserName , userName, "Sucessfully entered email id", "Unable to enter email id");
		mobileSetElement(driver,txtFbPwd , password, "Sucessfully entered password", "Unable to enter password");
		if(isElementClickable(driver, btnfbLogin))
			clickonElement(driver, btnfbLogin, "Sucessfully clicked on Log-in button", "Unable to click on Log-in button");
		if(isElementPresent(btnFbContinue1,15))
			clickonElement(driver, btnFbContinue1, "Sucessfully clicked on Continue1 button", "Unable to click on Continue button");
		if(isElementDisplayed(btnFbContinue2))
			clickonElement(driver, btnFbContinue2, "Sucessfully clicked on Continue2 button", "Unable to click on Continue button");


	}

	public void selectFacebookAccountApp(String userName, String password) throws InterruptedException, FileNotFoundException{

		mobileSetElement(driver,txtFbAppUserName , userName, "Sucessfully entered email id", "Unable to enter email id");
		waitFor(1);
		//btnFbAppLogin.click();
		if(isElementClickable(driver, txtFbAppPwd))
			mobileSetElement(driver,txtFbAppPwd , password, "Sucessfully entered password", "Unable to enter password");
		if(isElementClickable(driver, btnFbAppLogin))
			clickonElement(driver, btnFbAppLogin, "Sucessfully clicked on Log-in button", "Unable to click on Log-in button");



	}


	public void faceBookLogout() throws FileNotFoundException, InterruptedException{
		if(isElementPresent(btnBack,40)){
			clickonElement(driver, btnBack, "Sucessfully clicked on Back button", "Unable to click on Bank button");
		}else{
			acceptpolicy();
			backLogout();
			popupLogout();

		}
	}

	public void  facebookWeb_App(String userName, String password,String userId,String customerId) throws FileNotFoundException, InterruptedException{
		if(isElementPresent(txtFbUserName,20)){
			selectFacebookAccountWeb(userName,password);
			faceBookLogout();
			userId = makeCutomerNew(userName);
			facebookAccount();
			selectFacebookAccountWeb(userName,password);
			acceptpolicy();
			String mobileNum= mobileNumber();
			mobileNumVerify();
			backButton();
			backLogout();
			popupLogout();
			customerId = disableMobileVerification(userName,mobileNum);
			facebookAccount();
			selectFacebookAccountWeb(userName,password);

		}else if(isElementPresent(txtFbAppUserName,20)){
			selectFacebookAccountApp(userName,password);
			faceBookLogout();
			userId = makeCutomerNew(userName);
			facebookAccount();
			acceptpolicy();
			String mobileNum=mobileNumber();
			mobileNumVerify();
			backButton();
			backLogout();
			popupLogout();
			customerId = disableMobileVerification(userName,mobileNum);
			facebookAccount();

		}else{
			logger.info("already login with some account");
		}

	}

	public String makeCutomerNew(String email_id)  {

		//Getting customer_id from db 
		String query_1 = "SELECT fc.customer_id "
				+ "FROM flattend_customer_social_data fc "
				+ "WHERE fc.email LIKE '"+email_id+"' ORDER BY fc.customer_id DESC LIMIT 1";
		System.out.println("Customer query "+query_1);
		List<String> customer_id = new ArrayList<String>();
		customer_id = dbCon.dataBaseSelect(query_1);
		System.out.println("Customer id from db "+customer_id);

		//getting user_id from db based on customer_id
		String query_01 = "SELECT user_id FROM `customers` WHERE customer_id ="+customer_id.get(0);
		List<String> user_id = new ArrayList<String>();
		user_id =dbCon.dataBaseSelect(query_01);
		System.out.println("User id from db "+user_id);



		//Getting username from users table
		String query_2 = "SELECT username FROM users WHERE user_id ="+user_id.get(0);
		List<String> user_name = new ArrayList<String>();
		user_name = dbCon.dataBaseSelect(query_2);
		System.out.println("User name from db "+user_name);


		Random ran_num = new Random();
		int rNum = ran_num.nextInt(9999);
		System.out.println("Random number is "+rNum);        
		//updating new user name in users table in bd
		String userNameSub = user_name.get(0).substring(0, 10);
		String newUserName = userNameSub +rNum;
		System.out.println("New user name is  "+newUserName);

		String query_3 = "UPDATE users SET username = '"+newUserName+"' WHERE user_id = "+user_id.get(0);

		dbCon.dataBaseUpdate(query_3);
		System.out.println("Value updated");
		return user_id.get(0);
	}




	public String  mobileNumber(){
		String mobileNo=getRandMobileNum();
		txtMobileNum.click();
		mobileSetElement(driver,txtMobileNum , mobileNo, "Sucessfully entered mobile Number", "Unable to enter Mobile Number");
		return mobileNo;

	}
	public void mobileNumVerify() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnMobileNumVerify))
			clickonElement(driver, btnMobileNumVerify, "Sucessfully clicked on verify button", "Unable to click on Verify button");

	}
	public void backButton() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnBack))
			clickonElement(driver, btnBack, "Sucessfully clicked on Back button", "Unable to click on Bank button");

	}
	public void backLogout() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnBackLogout))
			clickonElement(driver, btnBackLogout, "Sucessfully clicked on Log out button", "Unable to click on Log out button");

	}
	public void popupLogout() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnPopupLogout))
			clickonElement(driver, btnPopupLogout, "Sucessfully clicked on logout button in Pop-up", "Unable to click on logout button in Pop-up");

	}
	public void googleLogout(String referralCode) throws FileNotFoundException, InterruptedException{
		if(dataArray.get("Platform").equalsIgnoreCase("android") && passcode==false) {
			if(isElementDisplayed(btnPopUpOk)){
				clickonElement(driver, btnPopUpOk, "Sucessfully clicked on alert button", "Unable to click on alert button");
			}
			waitFor(1);
			System.out.println("waiting for back");
			if(waitUntilElementClickable(driver, btnBack,20)){
				clickonElement(driver, btnBack, "Sucessfully clicked on Back button", "Unable to click on Back button");
			}
			if(!referralCode.equalsIgnoreCase("No")){
				if(isElementClickable(driver, chkBoxRef))
					clickonElement(driver, chkBoxRef, "Sucessfully clicked on Referral CheckBox button", "Unable to click on Referral CheckBox button");
			}

			if(isElementDisplayed(btnGoogle))
				clickonElement(driver, btnGoogle, "Sucessfully clicked on Google button", "Unable to click on Google button");
		}else if (!dataArray.get("Platform").equalsIgnoreCase("android") && passcode==false) {

			if(waitUntilElementClickable(driver, btnCancel,20)){
				clickonElement(driver, btnCancel, "Sucessfully clicked on cancel button", "Unable to click on cancel button");
			}
		}

	}

	public String disableMobileVerification(String email_id, String mobileNum) {
		////DataBaseConnection dbCon=new ////DataBaseConnection();

		//getting new customer id based on email id
		String query_4 = "SELECT fc.customer_id FROM flattend_customer_social_data fc "
				+ "WHERE fc.email LIKE '"+email_id+"' "
				+ "ORDER BY fc.customer_id DESC LIMIT 1";
		List<String> cust_id = new ArrayList<String>();
		cust_id = dbCon.dataBaseSelect(query_4);
		System.out.println("User id from db "+cust_id);

		//entering mobile number- upadating mobile number of new customer based on customer id
		String query_5 ="UPDATE customer_details SET mobile_no = "+mobileNum+" WHERE customer_id ="+cust_id.get(0);   
		dbCon.dataBaseUpdate(query_5);
		System.out.println("Value updated");

		//entering mobile number- upadating mobile number of new customer based on customer id

		String query_6 ="UPDATE otp_verification SET is_mobile_verified = 1 WHERE customer_id = "+cust_id.get(0);  
		dbCon.dataBaseUpdate(query_6);
		System.out.println("Value updated");

		return cust_id.get(0);
	}

	public void googleLoginAgain(String email_id,String referralCode ) throws InterruptedException, IOException{
		//waitUntilElementClickable(driver, element)
		waitFor(10);
		try{
			if(dataArray.get("Platform").equalsIgnoreCase("android")) {
				List<WebElement> ele=driver.findElements(By.xpath("//android.widget.TextView"));
				for (WebElement e : ele){
					String account = e.getText();
					if(email_id.equalsIgnoreCase(account)){
						e.click();
						break;
					}
				}
			}else {
				if(!isElementPresent(checkAccept, 20)) {

					if(isElementDisplayed(btnGoogle))
						clickonElement(driver, btnGoogle, "Sucessfully clicked on Google button", "Unable to click on Google button");
					driver.findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'@gmail.com')]")).click();
					waitFor(3);
					driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Continue']")).click();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}


		if(!referralCode.equalsIgnoreCase("No")){
			mobileSetElement(driver, txtRefferalCode, referralCode, "", "");
			waitFor(2);
			clickonElement(driver, btnRefApply, "Sucessfully clicked on Referral Apply button", "Unable to click on Referral Apply button");

			if(isElementClickable(driver, btnRefSuccess))
				//capture("ReferralAwesome", LogStatus.PASS, eTest);
				clickonElement(driver, btnRefSuccess, "Sucessfully clicked on Referral Awesome button", "Unable to click on Referral Awesome button");
		}
	}

	public void googleLoginAgain(String email_id ) throws InterruptedException, FileNotFoundException{
		//waitUntilElementClickable(driver, element)

		System.out.println("came ttooo");
		if(dataArray.get("Platform").equalsIgnoreCase("android")) {

			try{
				waitFor(10);
				List<WebElement> ele=driver.findElements(By.xpath("//android.widget.TextView"));
				for (WebElement e : ele){
					String account = e.getText();
					if(email_id.equalsIgnoreCase(account)){
						e.click();
						break;
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}else {
			waitFor(3);
			((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Continue']")).click();
			waitFor(10);		
			((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'@gmail.com')]")).click();
			waitFor(3);
			swipeTo();
			btnAllow.click();
			//((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Continue']")).click();
		}
	}

	public void acceptpolicy() throws FileNotFoundException, InterruptedException{
		waitFor(6);
		try{
			clickonElement(driver, checkAccept, "", "");
			clickonElement(driver, btnProceedPolicy, "", "");
		}catch(Exception e){

		}
	}
}
