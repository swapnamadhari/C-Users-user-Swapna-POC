package com.cashe.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cash.utilities.DataBaseConnection;
//import com.cash.utilities.//DataBaseConnection;
import com.cashe.base.BasePage;

public class GetCashe  extends BasePage {

	AppiumDriver driver;
	ExtentTest eTest;
	Hashtable<String, String> dataArray;


	@AndroidFindBy(id="action_get_cashe")
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='Get CASHe']")
	public WebElement  btnNavigateGetCashe;

	//GetCashe screen title text
	@AndroidFindBy(id="title") //XCUIElementTypeNavigationBar[@name="Get CASHe"]
	@iOSFindBy(xpath="//XCUIElementTypeNavigationBar | //XCUIElementTypeStaticText[@name='Awesome!']")
	public WebElement  txtTitle;

	@AndroidFindBy(xpath="(//android.widget.Button[@resource-id='co.tslc.cashe.android:id/btn_apply'])[1]")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='CASHe 15']/following-sibling::XCUIElementTypeButton")
	public WebElement  btn15dayApplyNow;

	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='co.tslc.cashe.android:id/txt_casheheader'])[1]")
	@iOSFindBy(accessibility="CASHe 15")
	public WebElement  txt15dayHeader;

	@AndroidFindBy(xpath="(//android.widget.Button[@resource-id='co.tslc.cashe.android:id/btn_apply'])[2]")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='CASHe 30']/following-sibling::XCUIElementTypeButton")
	public WebElement  btn30dayApplyNow;

	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='co.tslc.cashe.android:id/txt_casheheader'])[2]")
	@iOSFindBy(accessibility="CASHe 30")
	public WebElement  txt30dayHeader;

	@AndroidFindBy(xpath="(//android.widget.Button[@resource-id='co.tslc.cashe.android:id/btn_apply'])[3]")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='CASHe 90*']/following-sibling::XCUIElementTypeButton")
	public WebElement  btn90dayApplyNow;

	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='co.tslc.cashe.android:id/txt_casheheader'])[3]")
	@iOSFindBy(accessibility="CASHe 90*")
	public WebElement  txt90dayHeader;
	
	@AndroidFindBy(xpath="(//android.widget.Button[@resource-id='co.tslc.cashe.android:id/btn_apply'])[4]")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='CASHe 180*']/following-sibling::XCUIElementTypeButton")
	public WebElement  btn180dayApplyNow;

	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='co.tslc.cashe.android:id/txt_casheheader'])[4]")
	@iOSFindBy(accessibility="CASHe 180*")
	public WebElement  txt180dayHeader;
	
	@AndroidFindBy(xpath="(//android.widget.Button[@resource-id='co.tslc.cashe.android:id/btn_apply'])[5]")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='CASHe 1 Year*']/following-sibling::XCUIElementTypeButton")
	public WebElement  btnOneYearApplyNow;

	@AndroidFindBy(xpath="(//android.widget.TextView[@resource-id='co.tslc.cashe.android:id/txt_casheheader'])[5]")
	@iOSFindBy(accessibility="CASHe 1 Year*")
	public WebElement  txtOneYearHeader;
	
	@iOSFindBy(accessibility="ESIGN")
	public WebElement btnEsign;

	@AndroidFindBy(id="back")
	@iOSFindBy(accessibility="leftArrow")
	public WebElement btnBack;


	//eSign Text field 
	@AndroidFindBy(id="input_eSign")
	@iOSFindBy(xpath="//XCUIElementTypeTable[@name='UploadPhotoProofs']//XCUIElementTypeTextField")
	public WebElement txteSign;	

	//eSign Button
	@AndroidFindBy(id ="btn_eSign")
	@iOSFindBy(accessibility="VerifyMobile")
	public WebElement btnesign;

	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'You have')]")
	public WebElement txtEsignAtempts;
	/*@AndroidFindBy(xpath="")
	                public WebElement  btn180dayApplyNow;

	                @AndroidFindBy(xpath="")
	                public WebElement  Txt180dayHeader;*/

	@AndroidFindBy(id="back")
	public WebElement  btnback;

	@AndroidFindBy(id="used_cashe")
	@iOSFindBy(accessibility="Used CASHe")
	public WebElement  txtUsedCasheLable;

	@AndroidFindBy(id="used_cashe_amount")
	@iOSFindBy(xpath="(//XCUIElementTypeStaticText[contains(@name,'₹ ')])[1]")
	public WebElement  txtUsedCasheVal;

	@AndroidFindBy(id="available_cashe")
	@iOSFindBy(accessibility="Available CASHe")
	public WebElement  txtAvailableCasheLable;

	@AndroidFindBy(id="available_cashe_amount")
	@iOSFindBy(xpath="(//XCUIElementTypeStaticText[contains(@name,'₹ ')])[2]")
	public WebElement  txtAvailableCasheVal;

	@AndroidFindBy(id="cashe_amount_needed")
	public WebElement  txtAmountNeeded;

	@AndroidFindBy(id="interval_amount")
	public WebElement  txtIntervalAmount;

	@AndroidFindBy(id="seek_bar")
	public WebElement  sliderLoanAmount;

	@AndroidFindBy(id="loan_amount_start")
	public WebElement  txtStartAmount;

	@AndroidFindBy(id="loan_amount_end")
	public WebElement  txtEndAmount;

	@AndroidFindBy(id="txt_loan")
	public WebElement  txtSliderPointerAmount;

	@AndroidFindBy(id="input_cashePurpose")
	@iOSFindBy(accessibility="Purpose of CASHe")
	public WebElement  txtCashePurpose;

	@AndroidFindBy(id="input_cashe_amount")
	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[1]")
	public WebElement  txtCasheAmt;

	@iOSFindBy(xpath="//XCUIElementTypePickerWheel")
	public WebElement  pickerSelectAmt;

	@iOSFindBy(accessibility="Done")
	public WebElement  pickerSelectDone;

	@AndroidFindBy(xpath="//android.widget.NumberPicker[@index='1']/android.widget.Button[@index='0']")
	public WebElement  pickerAndroid;

	@AndroidFindBy(xpath="//android.widget.RadioButton[1]")
	@iOSFindBy(accessibility="Medical")
	public WebElement  btnRadioMedical;

	@AndroidFindBy(xpath="//android.widget.RadioButton[2]")
	@iOSFindBy(accessibility="Travel")
	public WebElement  btnRadioTravel;

	@AndroidFindBy(xpath="//android.widget.RadioButton[3]")
	@iOSFindBy(accessibility="EMI")
	public WebElement  btnRadioEMI;

	@AndroidFindBy(xpath="//android.widget.RadioButton[4]")
	@iOSFindBy(accessibility="Purchases")
	public WebElement  btnRadioPurchases;

	@AndroidFindBy(xpath="//android.widget.RadioButton[5]")
	@iOSFindBy(accessibility="Loan repayment")
	public WebElement  btnRadioLoanRepay;

	@AndroidFindBy(xpath="//android.widget.RadioButton[6]")
	@iOSFindBy(accessibility="Others")
	public WebElement  btnRadioOthers;

	@AndroidFindBy(id="btnOk")
	public WebElement  btnOk;

	@AndroidFindBy(id="cashe_details_txt")
	public WebElement  txtDetailsHeader;

	@AndroidFindBy(id="in_hand_amount_txt")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='In-Hand Amount']")
	public WebElement  txtInHandAmountLable;
	
	
	/*@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='co.tslc.cashe.android:id/btn_apply'])[4]")
	public WebElement  btn180dayApplyNow;*/
	
	@AndroidFindBy(id = "interest_upfront_txt")
	public WebElement  txtInterestLableemi;
	
	@AndroidFindBy(id = "interest_upfront_amount")
	public WebElement  txtInterestValemi;

	@AndroidFindBy(id = "interest_to_emi_txt")
	public WebElement  txtInterestLableup;

	@AndroidFindBy(id = "interest_to_emi_amount")
	public WebElement  txtInterestValup;

	@AndroidFindBy(id="in_hand_amount")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='In-Hand Amount']/following-sibling::XCUIElementTypeStaticText")
	public WebElement  txtInHandAmountVal;

	@AndroidFindBy(id="interest_txt")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Interest')]")
	public WebElement  txtInterestLable;

	@AndroidFindBy(id="interst_amount")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'Interest')]/following-sibling::XCUIElementTypeStaticText")
	public WebElement  txtInterestVal;

	@AndroidFindBy(id="otp_fee_txt")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'First Time Processing Fee')]")
	public WebElement  txtProcessFeeLable;

	@AndroidFindBy(id="otp_amount")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'First Time Processing Fee')]/following-sibling::XCUIElementTypeStaticText")
	public WebElement  txtProcessFeeVal;

	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'GST')]")
	@AndroidFindBy(id = "gst_txt")
	public WebElement txtGSTLabel;

	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'GST')]/following-sibling::XCUIElementTypeStaticText")
	@AndroidFindBy(id = "gst_amount")
	public WebElement txtGSTVal;
	
	@AndroidFindBy(id="total_cashe")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Total cashe']")
	public WebElement  txtTotalCasheLable;

	@AndroidFindBy(id="total_cashe_amount")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Total cashe']/following-sibling::XCUIElementTypeStaticText")
	public WebElement  txtTotalCasheVal;

	@AndroidFindBy(id="action_get_cashe")
	@iOSFindBy(accessibility="Get CASHe")
	public WebElement  btnGetCashe;

	@AndroidFindBy(id="btn_get_cashe")
	@iOSFindBy(accessibility="GET CASHe")
	public WebElement  btnGetCasheBBScreen;

	@AndroidFindBy(id = "action_Profile")
	@iOSFindBy(accessibility="Profile")
	public WebElement btnProfile;


	@AndroidFindBy(id="popup_negative_button")
	@iOSFindBy(accessibility="SKIP")
	public WebElement  btnPopupSkip;



	@AndroidFindBy(id="popup_positive_button")
	@iOSFindBy(accessibility="OK")
	public WebElement  btnPopupYes;

	@AndroidFindBy(id="no_emi_txt")
	@iOSFindBy(accessibility="Number of EMIs")
	public WebElement  txtNumEmiLable;

	@AndroidFindBy(id="no_emi")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='Number of EMIs']/following-sibling::XCUIElementTypeStaticText")
	public WebElement  txtNumEmiVal;

	@AndroidFindBy(id="emi_amt_txt")
	@iOSFindBy(accessibility="EMI Amount")
	public WebElement  txtEmiAmountLable;

	@AndroidFindBy(id="emi_amt")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='EMI Amount']/following-sibling::XCUIElementTypeStaticText")
	public WebElement  txtEmiAmountVal;

	@AndroidFindBy(id="input_promo")
	public WebElement  txtboxPromoCode;

	@AndroidFindBy(id="btn_promo")
	public WebElement  BtnApplyPromoCode;

	@AndroidFindBy(id="tvPromoCodeApplied")
	public WebElement  txtPromoCodeApplied;

	@AndroidFindBy(id="txt_promo_discount")
	public WebElement  txtPromoCodeLbl;

	@AndroidFindBy(id="promo_discount")
	public WebElement  txtPromoCodeVal;


	//CASHe Details Tab
	@AndroidFindBy(id = "//android.widget.LinearLayout/android.support.v7.app.ActionBar.Tab[1]/android.widget.TextView")
	public WebElement lblCASHeDtls;

	//label of Amount To Repay
	@AndroidFindBy(id = "txt_amount_repay")
	public WebElement lbLAmtRepay;

	//Amount To Repay
	@AndroidFindBy(id = "repay_amount")
	public WebElement lblAmt;

	//Label of Due Date
	@AndroidFindBy(id = "txt_due_date")
	public WebElement lblDueDate;

	//Due Date
	@AndroidFindBy(id = "due_date")
	public WebElement lblDate;

	//Loan Request Breakdown Details
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout//android.widget.TextView")
	public WebElement lblLRBreakdown;

	//Confirm Details button
	@AndroidFindBy(id = "btn_confirm_details")
	@iOSFindBy(accessibility="CONFIRM DETAILS")
	public WebElement btnConfirmDtls;

	//Bank Details Tab
	@AndroidFindBy(xpath = "//android.support.v7.app.ActionBar.Tab[2]")
	public WebElement tabBankDtls;

	//label of Bank
	@AndroidFindBy(id = "txt_bank")
	public WebElement lblBank;

	//Bank Name
	@AndroidFindBy(id = "bank_name")
	public WebElement lblBankName;

	//label of Account Name
	@AndroidFindBy(id = "txt_account")
	public WebElement lblAcctName;

	//Account Name
	@AndroidFindBy(id = "account_num")
	public WebElement lblAcctNumber;

	//label of IFSC Code
	@AndroidFindBy(id = "txt_ifsc")
	public WebElement lblIFSC;

	//IFSC Code
	@AndroidFindBy(id = "ifsc_code")
	public WebElement lblIFSCCode;

	//Back Arrow in Details Screen
	@AndroidFindBy(id = "back")
	public WebElement btnbackarrow;

	//IOU - Name Text box
	@AndroidFindBy(id = "input_name")
	@iOSFindBy(xpath="//XCUIElementTypeTextField")
	public WebElement txtIOUName;

	//IOU - Proceed button
	@AndroidFindBy(id = "btn_proceed")
	@iOSFindBy(accessibility="PROCEED")
	public WebElement btnProceed;

	@iOSFindBy(accessibility="Passcode")
	public WebElement btnPasscode;

	//IOU - Back Arrow
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
	public WebElement btnIOUBackArrow;

	//Text - T&C
	@AndroidFindBy(id = "txt_break_down")
	public WebElement lblTC;

	//Content - T&C
	@AndroidFindBy(id = "tv_iou_t_c")
	public WebElement cntTC;

	//Thanks for Applying- My cashe butoon
	@AndroidFindBy(id = "open_my_cashe")
	@iOSFindBy(accessibility="MY CASHe")
	public WebElement btnMyCashe;

	//Thanks for Applying- Ratus Now button
	@AndroidFindBy(id = "rate_cashe")
	public WebElement btnRatusNow;

	//Thanks for Applying- Close button
	@AndroidFindBy(id = "img_close")
	public WebElement btnClose;


	@AndroidFindBy(id = "co.tslc.cashe.android:id/loan_amount_start")
	public WebElement sliderAmtStart;

	@AndroidFindBy(id = "co.tslc.cashe.android:id/loan_amount_end")
	public WebElement sliderAmtEnd;

	@AndroidFindBy(id = "co.tslc.cashe.android:id/txt_loan")
	public WebElement txtSliderAmt;

	//Paytm 

	@AndroidFindBy(id = "tvMaxAmount")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'You have in-Hand amount of')]")
	public WebElement inhand_amount;

	@AndroidFindBy(id = "etPaytmMoney")
	@iOSFindBy(accessibility="To PayTM Account")
	public WebElement entering_paytm_amount;

	@AndroidFindBy(id = "etBankMoney")
	@iOSFindBy(accessibility="To Bank Account")
	public WebElement amount_to_bank;

	@AndroidFindBy(id = "etPaytmAccount")
	@iOSFindBy(accessibility="PayTM Mobile Number")
	public WebElement account_no;

	@AndroidFindBy(id = "tvAccountHolder")
	public WebElement account_holder;

	@AndroidFindBy(id = "cbTermsConditions")
	@iOSFindBy(accessibility="checkboxoff")
	public WebElement checkbox_tc;

	@AndroidFindBy(id = "popup_message")
	@iOSFindBy(xpath="//XCUIElementTypeTextView")
	public WebElement popup_msg;

	//@AndroidFindBy(id="popup_negative_button")
	@iOSFindBy(accessibility="Cancel")
	public WebElement  btnPopupCancel;


	public GetCashe(AppiumDriver driver,ExtentTest eTest,Hashtable<String, String> dataArray,DataBaseConnection dbCon) {
		this.driver = driver;
		this.eTest=eTest;
		this.dataArray=dataArray;
		this.dbCon=dbCon;
		setDriver(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
		initilize();
	}

	public void clickBtnGetCashe() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnGetCasheBBScreen))
			clickonElement(driver, btnGetCasheBBScreen, "Sucessfully clicked get cashe button", "Unable to click on get cashe button");
	}
	public void paytmPopup(String paytm,String custmerId,String inhand_amount) throws FileNotFoundException, InterruptedException{
		if(paytm.contains("Yes")){
			if(isElementClickable(driver, btnPopupYes))
				clickonElement(driver, btnPopupYes, "Sucessfully clicked on Paytm popup skip button", "Unable to click on Paytm popup skip button");
			String paytmRange=paytm.split("#")[1];
			paytmAmtSelect(custmerId, paytmRange, inhand_amount);

		}else if(paytm.equalsIgnoreCase("No")){
			if(isElementClickable(driver, btnPopupSkip))
				clickonElement(driver, btnPopupSkip, "Sucessfully clicked on Paytm popup skip button", "Unable to click on Paytm popup skip button");
		}else{
			logger.info("Data error :"+paytm);
		}
	}


	public void navigateToGetCashe() throws FileNotFoundException, InterruptedException{
		//Click on get Cashe navigate button 
		try {
			if(isElementClickable(driver, btnNavigateGetCashe))
				clickonElement(driver, btnNavigateGetCashe, "Sucessfully clicked on Get Cashe Navigate button", "Unable to click on Get Cashe Navigate button");

			//Validating title of get cashe screen after navigating to get cashe screen
			if(isElementPresent(txtTitle,15)){
				//txtTitle.getAttribute("name");
				/*if(txtTitle.getAttribute("name").equalsIgnoreCase("GET CASHe")){
				logger.info("Get Cashe screen title is matching with expected title and the UI title is "+txtTitle.getText());
			}else{
				logger.info("Get Cashe screen title is not matching with expected title and the UI title is "+txtTitle.getText());
			}   */                           
			}else{
				logger.info("Get cashe screen not launched");
			}
		}catch(Exception e) {

		}

	}

	public void applyingLoan(String planType,String purpose,String paytm,String promo, String referral, String loanAmt, String custId,String salary) throws InterruptedException, IOException{

		if(loanAmt.contains("K")){
			int loanAmt1=Integer.parseInt(loanAmt.split("K")[0])*1000;
			loanAmt=String.valueOf(loanAmt1);
		}
		applyLoan(planType,purpose,paytm,promo,referral,loanAmt,custId,salary);

	}
	public void clickOnPlan(String planType) throws FileNotFoundException, InterruptedException {
		if(planType.contains("15")) {
			if(isElementClickable(driver, btn15dayApplyNow))
				clickonElement(driver, btn15dayApplyNow, "Sucessfully clicked on 15 day apply now button", "Unable to click on 15 day apply now button");
		}else if(planType.contains("30")) {
			if(isElementClickable(driver, btn30dayApplyNow))
				clickonElement(driver, btn30dayApplyNow, "Sucessfully clicked on 30 day apply now button", "Unable to click on 30 day apply now button");
		}else if(planType.contains("90")) {
			if(isElementClickable(driver, btn90dayApplyNow))
				clickonElement(driver, btn90dayApplyNow, "Sucessfully clicked on 90 day apply now button", "Unable to click on 180 day apply now button");
		}else if(planType.contains("180")){
			//swipe for  180 plan click
			swipeTo();
			if(isElementClickable(driver, btn180dayApplyNow))
				clickonElement(driver, btn180dayApplyNow, "Sucessfully clicked on 90 day apply now button", "Unable to click on 180 day apply now button");
		}else {
			//swipe for 1 year plan click
			swipeTo();
			if(isElementClickable(driver, btnOneYearApplyNow))
				clickonElement(driver, btnOneYearApplyNow, "Sucessfully clicked on 90 day apply now button", "Unable to click on 180 day apply now button");
		}		
	}
	public void clickOnEsign() throws InterruptedException, IOException {
		if(isElementClickable(driver, btnEsign))
			clickonElement(driver, btnEsign, "", "");
		else {
			eTest.log(Status.FAIL, "No esign Button");
			capture(Status.FAIL, "NoEsignButton", eTest);
		}
	}	
	public void applyLoan(String planType,String purpose,String paytm,String promo, String referral, String loanAmt, String custId,String salary) throws InterruptedException, IOException{

		
		
		clickOnPlan(planType);

		//clickOnEsign();
		updateEsignInDB(custId);
		/*if(isElementClickable(driver, btnBack)) {
			clickonElement(driver, btnBack, "", "");
			clickonElement(driver, btnProfile, "", "");
		}*/
		waitFor(3);		
		//clickonElement(driver, btnGetCashe, "", "");
		//clickOnPlan(planType);

		int loanEligiblity=checkLoanAvailability(planType,custId);
		if(Integer.parseInt(txtAvailableCasheVal.getText().split(" ")[1].trim())==loanEligiblity-Integer.parseInt(txtUsedCasheVal.getText().split(" ")[1].trim())) {
			logger.info("Available cashe is :"+(Integer.parseInt(txtAvailableCasheVal.getText().split(" ")[1].trim())));
			eTest.log(Status.INFO, "Loan Eligibility amt :"+loanEligiblity);
			eTest.log(Status.INFO, "Used cash :"+(loanEligiblity-Integer.parseInt(txtUsedCasheVal.getText().split(" ")[1].trim())));
			eTest.log(Status.INFO, "Available cash :"+Integer.parseInt(txtAvailableCasheVal.getText().split(" ")[1].trim()));

		}
		//sliderLoanAmount();
		selectAmt();
		selectCashePurpose(purpose);
		Thread.sleep(3000);
		validateLoanDetails(promo,referral,loanAmt,custId, planType ,salary);
		waitFor(2);
		String inhand_amount=txtInHandAmountVal.getText().split("Rs.")[1].trim();
		clickBtnGetCashe();
		paytmPopup(paytm,custId,inhand_amount);

	}
	public void updateEsignInDB(String customerId) {
		//DataBaseConnection dbConn=new //DataBaseConnection();
		dbCon.dataBaseUpdate("UPDATE customer_documents SET is_esigned =1 WHERE document_type_id =4 AND is_esigned =0 AND customer_id = "+customerId+"");
		System.out.println("PAN eSigned updated");
	}

	public boolean validateEsign() throws InterruptedException, IOException {
		try {
			for(int i=1;i<=3;i++) {
				String aadhaarno = getRandAadhaarNumber();
				mobileSetElement(driver,txteSign , aadhaarno, "Sucessfully entered Aadhaar Number", "Unable to enter Aadhaar Number");
				System.out.println("Entered Random Aadhaar Number");
				clickonElement(driver, btnesign, "", "");
				capture(Status.INFO,"eSign",eTest);
				Thread.sleep(5000);
				//txtEsignAtempts.getText();
				eTest.log(Status.INFO, txtEsignAtempts.getText());
			}
			return true;
		}catch(Exception e) {
			capture(Status.INFO,"Check screnshot",eTest);
			eTest.log(Status.FAIL, "Check screnshot");
			return false;
		}

	}

	public int checkLoanAvailability(String planType,String custId) {
		initilize();
		int salary=Integer.parseInt(dataArray.get("salary"));
		int loanElig;
		System.out.println("pro.getProperty(\"loan_eligible_percentage_cashe15\") ::"+pro.getProperty("loan_eligible_percentage_cashe15"));
		if(planType.contains("15")) {
			loanElig=(salary*Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe15")))/100;
		}else if(planType.contains("30")) {
			loanElig=(salary*Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe30")))/100;
		}else if(planType.contains("90")) {
			int checkNew=Integer.parseInt(dbCon.dataBaseSelect("select count(`interest_rate`) from loan_request where customer_id ="+custId+" and `interest_rate`=7.50").get(0));
			List<String> scores= dbCon.dataBaseSelect("select cibil_score, gm_score from `customer_details` where customer_id="+custId+"");
			if(checkNew>0 && scores.get(0)!=null && scores.get(1)!=null && Integer.parseInt(scores.get(0))>=Integer.parseInt(pro.getProperty("cibil_score")) && Float.parseFloat(scores.get(1))>=Float.parseFloat(pro.getProperty("gm_score"))) {
				loanElig=(salary*Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe90_repeat")))/100;
			}else {
				loanElig=(salary*Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe90_new")))/100;
			}
		}else {
			loanElig=(salary*Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe180")))/100;
		}
		return (int) (Math.round(loanElig/ 1000.0) * 1000.0);

	}


	public void selectAmt() throws FileNotFoundException, InterruptedException {
		if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
			clickonElement(driver, txtCasheAmt, "", "");
			mobileSetElement(driver, pickerSelectAmt, String.valueOf((Integer.parseInt(dataArray.get("LoanAmount").split("K")[0])*1000)), "", "");
			clickonElement(driver, pickerSelectDone, "", "");
		}else {
			//sliderLoanAmount();
			//((AndroidElement) pickerAndroid).replaceValue("777");
		}
	}
	public void selectCashePurpose(String purpose) throws FileNotFoundException, InterruptedException{

		clickonElement(driver, txtCashePurpose, "", "");
		if("Medical".equalsIgnoreCase(purpose)){
			clickonElement(driver, btnRadioMedical, "", "");
		}else if("Travel".equalsIgnoreCase(purpose)){
			clickonElement(driver, btnRadioTravel, "", "");

		}else if("EMI".equalsIgnoreCase(purpose)){
			clickonElement(driver, btnRadioEMI, "", "");

		}else if("Purchases".equalsIgnoreCase(purpose)){
			clickonElement(driver, btnRadioPurchases, "", "");

		}else if("loan repayment".equalsIgnoreCase(purpose)){
			clickonElement(driver, btnRadioLoanRepay, "", "");

		}else{
			clickonElement(driver, btnRadioOthers, "", "");
		}
		if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			btnOk.click();
		}

	}

	public void planDetails(String planType) throws FileNotFoundException, InterruptedException{
		if(isElementPresent(txtTitle,15)){
			/*if(txtTitle.getText().equalsIgnoreCase("Details")){
				logger.info("Plan Details title is matching with expected title and the UI title is "+txtTitle.getText());
			}else{
				logger.info("Plan Details title is not matching with expected title and the UI title is "+txtTitle.getText());
			} */             
			if(isElementClickable(driver, btnConfirmDtls))
				clickonElement(driver, btnConfirmDtls, "Sucessfully clicked on Confirm details button", "Unable to click on Confirm details button");

		}else{
			logger.info("Plan Details screen not launched");
		}

	}


	public void iouDeclaration() throws FileNotFoundException, InterruptedException{
		if(isElementPresent(txtTitle,15)){
			/*if(txtTitle.getText().equalsIgnoreCase("I.O.U Declaration")){
				logger.info("Plan Details title is matching with expected title and the UI title is "+txtTitle.getText());
			}else{
				logger.info("Plan Details title is not matching with expected title and the UI title is "+txtTitle.getText());
			}         */     
			if(isElementClickable(driver, txtIOUName))
				mobileSetElement(driver, txtIOUName, "CasheCustomer", "Sucessfully Name is entered in iou screen", "Unable to enter name in iou screen");

			if(isElementClickable(driver, txtIOUName))
				clickonElement(driver, btnProceed, "Sucessfully clicked on proceed button", "Unable to click on proceed button");

			if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
				try {
					if(isElementClickable(driver, txtIOUName))
						clickonElement(driver, btnProceed, "Sucessfully clicked on proceed button", "Unable to click on proceed button");

				}catch(Exception e) {

				}
				clickonElement(driver, btnPasscode, "Sucessfully clicked on Passcode button", "Unable to click on Passcode button");
			}
		}else{
			logger.info("Plan Details screen not launched");
		}
	}

	public void thankYouForApplying() throws InterruptedException, IOException{

		if(isElementClickable(driver, btnPopupYes)) {
			clickonElement(driver, btnPopupYes, "Succesfully clicked on Pop Up OK for accessing remainders", "Not Able to click on popUp OK button ");
		}

		if(isElementPresent(txtTitle,15)){
			if(txtTitle.getText().equalsIgnoreCase("Awesome!")){
				logger.info("Thanks for applying title is matching with expected title and the UI title is "+txtTitle.getText());
				//	capture("Awesome",LogStatus.PASS,eTest);

			}else{
				logger.info("Thanks for applying is not matching with expected title and the UI title is "+txtTitle.getText());
				//	capture("Oops",LogStatus.PASS,eTest);

			} 
			//eTest.log(LogStatus.INFO, "Awesome");
			if(isElementClickable(driver, btnMyCashe))
				clickonElement(driver, btnMyCashe, "Sucessfully clicked on MyCashe button", "Unable to click on MyCashe button");


		}
		/*eTest.log(LogStatus.INFO, "Loan requested");
		capture("LoanRequested",LogStatus.PASS,eTest);*/

	}


	public Map<String, String> getLoanDetails(String loanType, String promo, String referral){
		Map<String,String> loanDetails = new HashMap<String,String>();
		loanDetails.put(txtUsedCasheLable.getText(),txtUsedCasheVal.getText());
		loanDetails.put(txtAvailableCasheLable.getText(),txtAvailableCasheVal.getText());
		String promoStat[] = promoValidInvalid(promo);

		if(loanType.equalsIgnoreCase("CASHe 15") || loanType.equalsIgnoreCase("CASHe 30")){
			if(((!promo.equalsIgnoreCase("No")) && (promoStat[0].equalsIgnoreCase("Valid"))) || !referral.equalsIgnoreCase("No")){
				loanDetails.put(txtInHandAmountLable.getText(),txtInHandAmountVal.getText());
				loanDetails.put(txtInterestLable.getText(),txtInterestVal.getText());
				loanDetails.put(txtProcessFeeLable.getText(),txtProcessFeeVal.getText());
				if(Integer.valueOf(promoStat[1]) != 100)
				{
				loanDetails.put(txtGSTLabel.getText(),txtGSTVal.getText());
				}
				loanDetails.put(txtPromoCodeLbl.getText(),txtPromoCodeVal.getText());
				loanDetails.put(txtTotalCasheLable.getText(),txtTotalCasheVal.getText());
			}else{
				loanDetails.put(txtInHandAmountLable.getText(),txtInHandAmountVal.getText());
				loanDetails.put(txtInterestLable.getText(),txtInterestVal.getText());
				loanDetails.put(txtProcessFeeLable.getText(),txtProcessFeeVal.getText());
				loanDetails.put(txtGSTLabel.getText(),txtGSTVal.getText());
				loanDetails.put(txtTotalCasheLable.getText(),txtTotalCasheVal.getText());

			}
		}else if(loanType.equalsIgnoreCase("CASHe 90")){
			if(((!promo.equalsIgnoreCase("No")) && (promoStat[0].equalsIgnoreCase("Valid"))) || !referral.equalsIgnoreCase("No")){
				loanDetails.put(txtInHandAmountLable.getText(),txtInHandAmountVal.getText());
				loanDetails.put(txtInterestLable.getText(),txtInterestVal.getText());
				loanDetails.put(txtProcessFeeLable.getText(),txtProcessFeeVal.getText());
				if(Integer.valueOf(promoStat[1]) != 100)
				{
				loanDetails.put(txtGSTLabel.getText(),txtGSTVal.getText());
				}
				loanDetails.put(txtPromoCodeLbl.getText(),txtPromoCodeVal.getText());
				loanDetails.put(txtTotalCasheLable.getText(),txtTotalCasheVal.getText());
				loanDetails.put(txtNumEmiLable.getText(),txtNumEmiVal.getText());
				loanDetails.put(txtEmiAmountLable.getText(),txtEmiAmountVal.getText());

			}else{
				loanDetails.put(txtInHandAmountLable.getText(),txtInHandAmountVal.getText());
				loanDetails.put(txtInterestLable.getText(),txtInterestVal.getText());
				loanDetails.put(txtProcessFeeLable.getText(),txtProcessFeeVal.getText());
				loanDetails.put(txtGSTLabel.getText(),txtGSTVal.getText());
				loanDetails.put(txtTotalCasheLable.getText(),txtTotalCasheVal.getText());
				loanDetails.put(txtNumEmiLable.getText(),txtNumEmiVal.getText());
				loanDetails.put(txtEmiAmountLable.getText(),txtEmiAmountVal.getText());

			}
		}else if(loanType.equalsIgnoreCase("CASHe 180")){
			if(((!promo.equalsIgnoreCase("No")) && (promoStat[0].equalsIgnoreCase("Valid"))) || !referral.equalsIgnoreCase("No")){
				loanDetails.put(txtInHandAmountLable.getText(),txtInHandAmountVal.getText());
				loanDetails.put(txtInterestLable.getText(),txtInterestVal.getText());
				loanDetails.put(txtProcessFeeLable.getText(),txtProcessFeeVal.getText());
				if(Integer.valueOf(promoStat[1]) != 100)
				{
				loanDetails.put(txtGSTLabel.getText(),txtGSTVal.getText());
				}
				loanDetails.put(txtPromoCodeLbl.getText(),txtPromoCodeVal.getText());
				loanDetails.put(txtTotalCasheLable.getText(),txtTotalCasheVal.getText());
				loanDetails.put(txtNumEmiLable.getText(),txtNumEmiVal.getText());
				loanDetails.put(txtEmiAmountLable.getText(),txtEmiAmountVal.getText());

			}else{
				loanDetails.put(txtInHandAmountLable.getText(),txtInHandAmountVal.getText());
				loanDetails.put(txtInterestLableemi.getText(),txtInterestValemi.getText());
				loanDetails.put(txtInterestLableup.getText(),txtInterestValup.getText());
				loanDetails.put(txtProcessFeeLable.getText(),txtProcessFeeVal.getText());
				loanDetails.put(txtGSTLabel.getText(),txtGSTVal.getText());
				loanDetails.put(txtTotalCasheLable.getText(),txtTotalCasheVal.getText());
				loanDetails.put(txtNumEmiLable.getText(),txtNumEmiVal.getText());
				loanDetails.put(txtEmiAmountLable.getText(),txtEmiAmountVal.getText());

			}
		}
		//logger.info("getLoanDetails "+loanDetails);
		System.out.println("getLoanDetails "+loanDetails);
		return loanDetails;
	}

	public String roundOfEligibility(int eligibility){
		int loanEligibility;
		int lastDigits = eligibility % 1000;
		logger.info("getting last 3 digits  " + lastDigits,true);
		if(lastDigits==000)
		{
			int lastDigits4 = eligibility % 10000;
			logger.info("getting last 4 digits  " + lastDigits4,true);

			if(lastDigits4>=2500)
			{
				loanEligibility=(int) (Math.ceil(eligibility/ 5000.0) * 5000.0); 
				logger.info("Max Eligible Amount2  " + loanEligibility,true);
			}
			else
			{
				loanEligibility=(int) (Math.floor(eligibility/ 5000.0) * 5000.0); 
				logger.info("Max Eligible Amount3  " + loanEligibility,true);
			}

		}
		else
		{
			int salsub=eligibility-5000;
			logger.info("when we substract 5000 from monthly_customer_income amount is ::   "+salsub ,true);
			int lastDigitss = salsub % 10000;
			logger.info("getting last 4 digits  " + lastDigitss,true);

			if(lastDigitss>=2500)
			{
				loanEligibility=(int) (Math.ceil(eligibility/ 5000.0) * 5000.0); 
				logger.info("Max Eligible Amount4  " + loanEligibility,true);
			}
			else
			{
				loanEligibility=(int) (Math.floor(eligibility/ 5000.0) * 5000.0); 
				logger.info("Max Eligible Amount5  " + loanEligibility,true);
			}
		}
		//logger.info("roundOfEligibility "+loanEligibility);
		System.out.println("roundOfEligibility "+loanEligibility);
		return Integer.toString(loanEligibility);
	}

	public String roundingValue(int a)
	{
		int loanEligibility=(int) (Math.round(a/ 1000.0) * 1000.0); 
		logger.info("round amount  :::" + loanEligibility,true);
		return Integer.toString(loanEligibility);
	}


	public String loanEligibility(String loanType, String salary,String custId){
		int eligibility ;
		String loanEligibility=null;
		
		String New_or_repeat=customerNewOrRepeat( loanType,  custId );
		List<String> customer_details=dbCon.dataBaseSelect("SELECT cibil_score,gm_score FROM customer_details WHERE customer_id="+custId);

		
		if(loanType.equalsIgnoreCase("CASHe 15")){
			eligibility =  (int) Math.round(Integer.valueOf(salary)*(Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe15"))));
			loanEligibility = roundingValue(eligibility);
			if(Integer.valueOf(loanEligibility) > 50000){
				loanEligibility = pro.getProperty("max_loan_eligible_amount_cashe15");
			}

		}else if(loanType.equalsIgnoreCase("CASHe 30")){
			eligibility = (int) Math.round(Integer.valueOf(salary)*(Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe30"))));
			loanEligibility = roundingValue(eligibility);
			if(Integer.valueOf(loanEligibility) > 50000){
				loanEligibility = pro.getProperty("max_loan_eligible_amount_cashe30");
			}

		}else if(loanType.equalsIgnoreCase("CASHe 90")){

			if(New_or_repeat.equals("New") || ((Integer.parseInt(customer_details.get(1)) < 0.65) &&  (Integer.parseInt(customer_details.get(1)) < 600)) || ((Integer.parseInt(customer_details.get(1))<0.6) && (Integer.parseInt(customer_details.get(0))< 650)))
			{
				eligibility =  (int) Math.round(Integer.valueOf(salary)*(Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe90_new"))));
				loanEligibility = roundingValue(eligibility);
			}
			else
			{
				eligibility =  (int) Math.round(Integer.valueOf(salary)*(Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe90_repeat"))));
				loanEligibility = roundingValue(eligibility);
			}
				if(Integer.valueOf(loanEligibility) > Integer.valueOf(pro.getProperty("max_loan_eligible_amount_cashe90"))){
				loanEligibility = pro.getProperty("max_loan_eligible_amount_cashe90");
			}
		}else if(loanType.equalsIgnoreCase("CASHe 180")){
			
			eligibility =  (int) Math.round(Integer.valueOf(salary)*(Integer.parseInt(pro.getProperty("loan_eligible_percentage_cashe90_repeat"))));
			loanEligibility = roundingValue(eligibility);
				
			if(Integer.valueOf(loanEligibility) > Integer.valueOf(pro.getProperty("max_loan_eligible_amount_cashe180"))){
				loanEligibility = pro.getProperty("max_loan_eligible_amount_cashe180");
			}

		}
		//logger.info("loanEligibility "+loanEligibility);
		System.out.println("loanEligibility "+loanEligibility);
		return loanEligibility;
	}

	public String usedCashe(String loanType, String custId){
		//DataBaseConnection dbCon=new //DataBaseConnection();
		int used_Cashe;
		List<String> count = new ArrayList<String>();
		List<String> amtPenalty = new ArrayList<String>();
		if(loanType.equalsIgnoreCase("CASHe 15")){
			count = dbCon.dataBaseSelect( "SELECT COUNT(*) FROM loan_request WHERE customer_id = "+custId+" AND loan_status_id IN (4,8) AND interest_rate = 1.5");                              
			if(count.get(0).equalsIgnoreCase("0")){
				used_Cashe = 0;
			}else{
				amtPenalty = dbCon.dataBaseSelect( "SELECT lr.loan_amount,lrp.total_penalty_due FROM `loan_request` lr "
						+ "LEFT OUTER JOIN loan_request_penalty_15_30 lrp ON lrp.loan_request_id = lr.loan_request_id "
						+ "WHERE lr.customer_id = "+custId+" AND lr.loan_status_id IN (4,8) AND lr.interest_rate = 1.5");

				used_Cashe = Integer.valueOf(amtPenalty.get(0)) + Integer.valueOf(amtPenalty.get(1));
			}
		}else if(loanType.equalsIgnoreCase("CASHe 30")){
			count = dbCon.dataBaseSelect( "SELECT COUNT(*) FROM loan_request WHERE customer_id = "+custId+" AND loan_status_id IN (4,8) AND interest_rate = 3");                 
			if(count.get(0).equalsIgnoreCase("0")){
				used_Cashe = 0;
			}else{
				amtPenalty = dbCon.dataBaseSelect( "SELECT lr.loan_amount,lrp.total_penalty_due FROM `loan_request` lr "
						+ "LEFT OUTER JOIN loan_request_penalty_15_30 lrp ON lrp.loan_request_id = lr.loan_request_id "
						+ "WHERE lr.customer_id = "+custId+" AND lr.loan_status_id IN (4,8) AND lr.interest_rate = 3");

				used_Cashe = Integer.valueOf(amtPenalty.get(0)) + Integer.valueOf(amtPenalty.get(1));
			}
		}else if(loanType.equalsIgnoreCase("CASHe 90")){
			used_Cashe = 0;
		}else{
			used_Cashe = 0;
		}
		//logger.info("usedCashe "+used_Cashe);
		System.out.println("usedCashe "+used_Cashe);
		return Integer.toString(used_Cashe);
	}

	public String loanInterest(String loanType, String loanAmt){
		int interest;
		if(loanType.equalsIgnoreCase("CASHe 15")){
			interest = (int) Math.round(Integer.valueOf(loanAmt)*(Integer.parseInt(pro.getProperty("interest_cashe15"))/100));
		}else if(loanType.equalsIgnoreCase("CASHe 30")){
			interest = (int) Math.round(Integer.valueOf(loanAmt)*(Integer.parseInt(pro.getProperty("interest_cashe30"))/100)) ;
		}else if(loanType.equalsIgnoreCase("CASHe 90")){
			interest = (int) Math.round(Integer.valueOf(loanAmt)*(Integer.parseInt(pro.getProperty("interest_cashe90"))/100)) ;
		}else{
			interest = (int) Math.round(Integer.valueOf(loanAmt)*(Integer.parseInt(pro.getProperty("interest_cashe180"))/100)) ;
		}
		//logger.info("loanInterest "+interest);
		System.out.println("loanInterest "+interest);
		return Integer.toString(interest);
	}

	public String processingFee(String loanType,String loanAmt,String loanEligibility,String custId){
		int processFee = 0;
		String  loan_N_R = customerNewOrRepeat(loanType,custId );
		if(loan_N_R.equalsIgnoreCase("New")){
			if(loanType.equalsIgnoreCase("CASHe 15") || loanType.equalsIgnoreCase("CASHe 30")){
				if(((Integer.valueOf(loanEligibility)) >= 5000) && ((Integer.valueOf(loanEligibility)) <= 15000)){
					processFee =500;
				}else if(((Integer.valueOf(loanEligibility)) >= 20000) && ((Integer.valueOf(loanEligibility)) <= 30000)){
					processFee =750;
				}else if(((Integer.valueOf(loanEligibility)) >= 35000) && ((Integer.valueOf(loanEligibility)) <= 50000)){
					processFee =1250;
				}
			}else if(loanType.equalsIgnoreCase("CASHe 90")){
				processFee = (int) Math.round(Integer.valueOf(loanAmt) * 0.01);
			}else{
				if(((Integer.valueOf(loanAmt)) <= 1000000)){
					processFee =750;
				}else if(((Integer.valueOf(loanAmt)) > 1000000)){
					processFee =1000;
				}
			}
		}else{

			if((Integer.valueOf(loanAmt)) <= 25000){
				processFee = 100;
			}else if(((Integer.valueOf(loanAmt)) >= 30000) && ((Integer.valueOf(loanAmt)) <= 50000)){
				processFee = 150;
			}else if(((Integer.valueOf(loanAmt)) >= 50000) && ((Integer.valueOf(loanAmt)) <= 75000)){
				processFee = 200;
			}else if(((Integer.valueOf(loanAmt)) >= 80000) && ((Integer.valueOf(loanAmt)) <= 1000000)){
				processFee = 250;
			}else if((Integer.valueOf(loanAmt)) > 1000000){
				processFee = 300;
			}

		}
		//logger.info("processingFee "+processFee);
		System.out.println("processingFee "+processFee);
		return Integer.toString(processFee);
	}


	public String[] promoValidInvalid(String promo){
		String promoStat;
		//DataBaseConnection dbCon=new //DataBaseConnection();
		List<String> promoStatus = new ArrayList<String>();
		List<String> promoVal = new ArrayList<String>();
		promoVal = dbCon.dataBaseSelect( "SELECT COUNT(*) FROM `promo_waive_off_type` pw WHERE waive_off_type_code = '"+promo+"'");
		if(promoVal.get(0).equalsIgnoreCase("0")){
			promoStat = "Invalid";
		}else{
			//promoStatus = dbCon.dataBaseSelect( "SELECT pw.status FROM `promo_waive_off_type` pw WHERE waive_off_type_code = '"+promo+"'");
			promoStatus = dbCon.dataBaseSelect("SELECT pw.status,pt.percentage_waive_off FROM promo_waive_off_type pw INNER JOIN promo_types pt ON pt.promo_type_id=pw.waive_off_type_id WHERE pw.waive_off_type_code= '"+promo+"'");
			if(promoStatus.get(0).equalsIgnoreCase("0")){
				promoStat = "Expired";
			}else {
				promoStat = "Valid";

			}
		}
		//logger.info("promoValidInvalid "+promoStat);
		System.out.println("promoValidInvalid "+promoStat);
		return new String[] {promoStat, promoStatus.get(1)};
	}

	public String promoReferral(String promo, String referral,String processfee){
		String[] promoStat = promoValidInvalid(promo);
		//DataBaseConnection dbCon=new //DataBaseConnection();
		List<String> promoPercent = new ArrayList<String>();
		int promoVal = 0;
		if(((!promo.equalsIgnoreCase("No")) && (promoStat[0].equalsIgnoreCase("Valid"))) && referral.equalsIgnoreCase("No")){
			promoPercent = dbCon.dataBaseSelect( "SELECT percentage_waive_off FROM `promo_waive_off_type` pw "
					+ "LEFT OUTER JOIN `promo_types` pt ON pw.waive_off_type_id = pt.waive_off_type_id "
					+ "WHERE waive_off_type_code = '"+promo+"'");
			String a=promoPercent.get(0).replaceAll("\\.0*$", "");
			promoVal = ((Integer.valueOf(processfee)) * Integer.valueOf(a));
			promoVal =         Math.round(promoVal /100);

		}else if(((promo.equalsIgnoreCase("No")) || ((promo.equalsIgnoreCase("No")) && ((promoStat[0].equalsIgnoreCase("Invalid")) || (promoStat[0].equalsIgnoreCase("Expired")))) ) && !referral.equalsIgnoreCase("No")){
			promoVal = Math.round((Integer.valueOf(processfee)) / 2);
		}else if(((!promo.equalsIgnoreCase("No")) && (promoStat[0].equalsIgnoreCase("Valid"))) && !referral.equalsIgnoreCase("No")){
			promoPercent = dbCon.dataBaseSelect( "SELECT percentage_waive_off FROM `promo_waive_off_type` pw "
					+ "LEFT OUTER JOIN `promo_types` pt ON pw.waive_off_type_id = pt.waive_off_type_id "
					+ "WHERE waive_off_type_code = '"+promo+"'");
			String a=promoPercent.get(0).replaceAll("\\.0*$", "");
			if(Integer.valueOf(a) > 50){
				promoVal = ((Integer.valueOf(processfee)) * Integer.valueOf(a));
				promoVal =         Math.round(promoVal /100);
			}else{
				promoVal = Math.round((Integer.valueOf(processfee)) / 2);

			}
		}
		//logger.info("promoReferral "+promoVal);
		System.out.println("promoReferral "+promoVal);
		return Integer.toString(promoVal);
	}

	public String customerNewOrRepeat(String loanType, String custId ){
		//DataBaseConnection dbCon=new //DataBaseConnection();
		List<String> status = new ArrayList<String>();
		if(loanType.equalsIgnoreCase("CASHe 15") || loanType.equalsIgnoreCase("CASHe 30")){
			status = dbCon.dataBaseSelect( "SELECT COUNT(*) FROM loan_request WHERE customer_id ='"+custId+"' AND interest_rate IN(1.5,3)");                         
		}else if(loanType.equalsIgnoreCase("CASHe 90") ){
			status = dbCon.dataBaseSelect( "SELECT COUNT(*) FROM loan_request WHERE customer_id ='"+custId+"' AND interest_rate IN(7.5)");                   
		}else{
			status = dbCon.dataBaseSelect( "SELECT COUNT(*) FROM loan_request WHERE customer_id ='"+custId+"' AND interest_rate IN(12)");                    
		}
		if(status.get(0).equals("0")){
			return "New";
		}else{
			return "Repeat";
		}
	}

	public String numberOfEMI(String loanType){
		int numEmi = 0;
		if(loanType.equalsIgnoreCase("CASHe 90")){
			numEmi = 3;
		}else if(loanType.equalsIgnoreCase("CASHe 180")){
			numEmi = 6;
		}
		//logger.info("numberOfEMI "+numEmi);
		System.out.println("numberOfEMI "+numEmi);
		return Integer.toString(numEmi);
	}

	public String emiAmount(String loanType, String loanAmt){
		int emiAmt = 0;
		if(loanType.equalsIgnoreCase("CASHe 90")){
			emiAmt = (Integer.valueOf(loanAmt) / 3);
		}else if(loanType.equalsIgnoreCase("CASHe 180")){
			emiAmt = (Integer.valueOf(loanAmt) / 6);
		}
		//logger.info("emiAmount "+emiAmt);
		System.out.println("emiAmount "+emiAmt);
		return Integer.toString(emiAmt);
	}

	public void validateLoanDetails(String promo, String referral, String loanAmt, String custId, String loanType ,String salary) {

		Map<String,String> loanDetails = new HashMap<String,String>();
		List<String> expected = new ArrayList<String>();
		String usedCashe_expt;
		String loanEligibility_expt;
		String availableCashe_expt;
		String interest_expt;
		String processFee_expt;
		String promoDiscount_expt;
		String totalCashe_expt;
		String inHandAmount_expt;
		swipeTo();
		loanDetails = getLoanDetails(loanType, promo,referral);
		System.out.println("loanDetails "+loanDetails);
		eTest.log(Status.INFO, "LoanDetails from App::"+loanDetails);
		usedCashe_expt = usedCashe(loanType, custId);
		System.out.println("usedCashe_expt "+usedCashe_expt);
		loanEligibility_expt = loanEligibility(loanType, salary,custId);
		System.out.println("loanEligibility_expt "+loanEligibility_expt);
		availableCashe_expt = Integer.toString((Integer.valueOf(loanEligibility_expt)) - (Integer.valueOf(usedCashe_expt)));
		System.out.println("availableCashe_expt "+availableCashe_expt);
		interest_expt = loanInterest(loanType,loanAmt);
		System.out.println("interest_expt "+interest_expt);
		processFee_expt =processingFee(loanType,loanAmt,availableCashe_expt,custId);
		System.out.println("processFee_expt "+processFee_expt);
		promoDiscount_expt = promoReferral(promo, referral,processFee_expt);
		System.out.println("promoDiscount_expt "+promoDiscount_expt);
		totalCashe_expt = loanAmt ;
		System.out.println("totalCashe_expt "+totalCashe_expt);
		processFee_expt = Integer.toString((Integer.valueOf(processFee_expt)) - (Integer.valueOf(promoDiscount_expt)));
		System.out.println("processFee_expt "+processFee_expt);
		inHandAmount_expt =  Integer.toString((Integer.valueOf(totalCashe_expt)) - (Integer.valueOf(processFee_expt)) - (Integer.valueOf(interest_expt))) ; 
		System.out.println("inHandAmount_expt "+inHandAmount_expt);

		expected.addAll(Arrays.asList(usedCashe_expt,loanEligibility_expt,availableCashe_expt,interest_expt,processFee_expt,promoDiscount_expt,totalCashe_expt,inHandAmount_expt));
		System.out.println("Expected list : "+expected);

		/*String x="".split(".")[1].trim();
	                String y="".substring("?", endIndex);
		 */
	}
	public void sliderLoanAmount(){
		try{

			WebElement seek_bar=getDriver().findElement(By.id("seek_bar"));
			// get start co-ordinate of seekbar
			int start=seek_bar.getLocation().getX();
			//Get width of seekbar
			int end=seek_bar.getSize().getWidth();
			//get location of seekbar vertically
			int y=seek_bar.getLocation().getY();

			int diffSlider=end-start;

			String startAmt=sliderAmtStart.getText().split("K")[0];
			String endAmt=sliderAmtEnd.getText().split("K")[0];
			int diffAmt=Integer.parseInt(endAmt)-Integer.parseInt(startAmt);

			int totalStages=(diffAmt/5)+1;

			int diffStages=diffSlider/totalStages;

			int pointValue=((Integer.parseInt(dataArray.get("LoanAmount").split("K")[0])-Integer.parseInt(startAmt))/5)+1;


			// Select till which position you want to move the seekbar
			TouchAction action=new TouchAction((MobileDriver) getDriver());


			//Move it 40%
			int moveTo=(int)(pointValue*diffStages);
			action.longPress(start,y).moveTo(moveTo,y).release().perform();

		}catch(Exception e){

		}
	}

	public void updatePaytmMobileno(String customer_id) throws FileNotFoundException, InterruptedException{

		if(dataArray.get("Paytm").contains("Yes")){

			dbCon.dataBaseUpdate("UPDATE customer_details SET mobile_no="+pro.getProperty("PaytmMobileNo")+" WHERE customer_id= "+customer_id);
		}
	}
	public void paytmAmtSelect(String customer_id,String range,String inhand_amountt) throws FileNotFoundException, InterruptedException
	{
		//Validating the pop-up message
		Assert.assertEquals(popup_msg.getText(),"Fund transfer to the Paytm wallet will be withheld for non-KYC Paytm customers. Please ensure to get your KYC done on your Paytm wallet within three days of the fund transfer to avoid getting a failure message. Thank you.");

		//Click on Proceed
		if(dataArray.get("Platform").contains("android")) {
			clickonElement(driver, btnPopupYes, "Sucessfully clicked on Paytm KYC proceed", "Unable to click on Paytm KYC proceed button");
		}else {
			clickonElement(driver, btnProceed, "Sucessfully clicked on Paytm KYC proceed", "Unable to click on Paytm KYC proceed button");

		}
		//Click on get cashe button without entering amount to paytm
		clickonElement(driver, btnGetCasheBBScreen ,"Get Cashe button clicked successfully",  "Unable to click on Get Cashe button");

		//Validating the pop-up message
		Assert.assertEquals( popup_msg.getText(),"Are you sure you want to send full money to your bank?");

		//Click on cancle
		if(dataArray.get("Platform").contains("android")) {
			clickonElement(driver, btnPopupSkip, "Sucessfully clicked on Paytm zero amount cancle button", "Unable to click on Paytm zero amount cancle button");
		}else {
			clickonElement(driver, btnPopupCancel, "Sucessfully clicked on Paytm zero amount cancle button", "Unable to click on Paytm zero amount cancle button");

		}
		//inhand amount
		String amount = inhand_amount.getText().split("Rs.")[1].trim(); 
		//amount=amount.substring(amount.lastIndexOf("Rs.")+3, amount.lastIndexOf(". You"));
		System.out.println("amount:::::"+amount);


		String paytmamount;

		if(range.toLowerCase().equalsIgnoreCase("max")){	
			//max_amount sent to Paytm
			if( Integer.valueOf(inhand_amountt) >= Integer.valueOf(pro.getProperty("paytm_MAXamount"))){

				paytmamount=pro.getProperty("paytm_MAXamount");
				paytmValidation(paytmamount,customer_id,inhand_amountt);

			}else{

				paytmamount=amount;
				paytmValidation(paytmamount,customer_id,inhand_amountt);	
			}


		}else{
			paytmamount=pro.getProperty("paytm_MINamount");
			paytmValidation(paytmamount,customer_id,inhand_amountt);
		}


	}
	public void paytmValidation(String paytmamount,String customer_id,String inhand_amount) throws FileNotFoundException, InterruptedException
	{
		List<String> db_response1=dbCon.dataBaseSelect("SELECT  c.customer_name,cd.mobile_no  FROM `customers` c INNER JOIN customer_details cd ON cd.customer_id=c.customer_id WHERE cd.customer_id="+customer_id);


		//Entering amount
		mobileSetElement(driver, entering_paytm_amount, paytmamount,"Paytm Amount entered successfully", "Paytm Amount is NOT entered successfully");

		//Amount to the bank
		String appbank_amount=amount_to_bank.getText();
		System.out.println("appbank_amount:::::"+appbank_amount);

		int cal_bankamount= Integer.valueOf(inhand_amount)-Integer.valueOf(paytmamount);
		System.out.println("cal_bankamount:::::"+cal_bankamount);

		//validating amount to bank in app and calculated
		Assert.assertEquals(appbank_amount, String.valueOf(cal_bankamount));


		//validating mobile no
		Assert.assertEquals(db_response1.get(1), account_no.getText());

		//validating customer_name
		if(dataArray.get("Platform").contains("android")) {
			Assert.assertEquals(db_response1.get(0).toUpperCase().trim(), account_holder.getText().split(":")[1].toUpperCase().trim());
		}

		//Click on terms and conditions check-box
		clickonElement(driver, checkbox_tc,"Check box clicked successfully",  "Check box is not clicked");


		//Click on processed button
		clickonElement(driver, btnGetCasheBBScreen, "Sucessfully clicked on Paytm processed button", "Unable to click on Paytm processed button ");
	}
}

