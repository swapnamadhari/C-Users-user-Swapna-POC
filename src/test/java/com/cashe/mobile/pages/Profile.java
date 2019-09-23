package com.cashe.mobile.pages;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cash.utilities.DataBaseConnection;
//import com.cash.utilities.//DataBaseConnection;
import com.cashe.base.Abstract;
import com.cashe.base.BasePage;
/*import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;*/

public class Profile extends BasePage {

	AppiumDriver driver;
	ExtentTest eTest;
	Hashtable<String, String> dataArray;

	GetCashe getCashe=null;
	String slqScoreDB;
	String status;
	String custStatus;
	String neoSlqDB;



	@AndroidFindBy(id="input_Name")
	//@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='FULL NAME']/preceding-sibling::XCUIElementTypeTextField")
	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[2]")
	public WebElement  txtFullName;


	@AndroidFindBy(xpath="(//android.widget.EditText)[4]")
	@iOSFindBy(accessibility="EMAIL")
	public WebElement  txtEmail;

	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[3]")
	public WebElement  txtGender;

	@AndroidFindBy(id="rb_gender_male")
	@iOSFindBy(accessibility="Male")
	public WebElement  btnRadioMale;

	@AndroidFindBy(id="rb_gender_female")
	@iOSFindBy(accessibility="Female")
	public WebElement  btnRadioFeMale;


	@AndroidFindBy(id="input_BirthDate")
	@iOSFindBy(accessibility="DATE OF BIRTH")
	public WebElement  txtDOB;

	@AndroidFindBy(xpath = "//*[@resource-id='android:id/button1']")
	@iOSFindBy(accessibility="Done")
	public WebElement  btnOK;


	@iOSFindBy(accessibility="Toolbar Done Button")
	public WebElement  btnDone;

	@AndroidFindBy(xpath = "//*[@resource-id='co.tslc.cashe.android:id/btnOk']")
	//@iOSFindBy(xpath="")
	public WebElement  btnOKPopUp;

	@AndroidFindBy(id="input_EduQualification")
	@iOSFindBy(accessibility="EDUCATIONAL QUALIFICATION")
	public WebElement  txtEducation;

	@AndroidFindBy(xpath="//android.widget.RadioButton[1]")
	@iOSFindBy(accessibility="Undergraduate")
	public WebElement  btnRadioUnderGraduate;

	@AndroidFindBy(xpath="//android.widget.RadioButton[2]")
	@iOSFindBy(accessibility="Graduate")
	public WebElement  btnRadioGraduate;

	@AndroidFindBy(xpath="//android.widget.RadioButton[3]")
	@iOSFindBy(accessibility="Postgraduate")
	public WebElement  btnRadioPostGraduate;

	@AndroidFindBy(id="input_TypeAccommodation")
	@iOSFindBy(accessibility="TYPE OF ACCOMODATION")
	public WebElement  txtAccommodation;

	@AndroidFindBy(xpath="//android.widget.RadioButton[1]")
	@iOSFindBy(accessibility="PG")
	public WebElement  btnRadioPG;

	@AndroidFindBy(xpath="//android.widget.RadioButton[2]")
	@iOSFindBy(accessibility="Rent")
	public WebElement  btnRadioRent;

	@AndroidFindBy(xpath="//android.widget.RadioButton[3]")
	@iOSFindBy(accessibility="Own")
	public WebElement  btnRadioOwn;


	@AndroidFindBy(xpath="//android.widget.RadioButton[1]")
	//@iOSFindBy(xpath="")
	public WebElement btnRadioSalaried;

	@AndroidFindBy(xpath="//android.widget.RadioButton[2]")
	//@iOSFindBy(xpath="")
	public WebElement btnRadioUnemployed;

	@AndroidFindBy(xpath="//android.widget.RadioButton[3]")
	//@iOSFindBy(xpath="")
	public WebElement btnRadioSelfEmployed;

	@AndroidFindBy(xpath="//android.widget.RadioButton[4]")
	//@iOSFindBy(xpath="")
	public WebElement btnRadioBusiness;

	@AndroidFindBy(xpath="//android.widget.RadioButton[5]")
	//@iOSFindBy(xpath="")
	public WebElement btnRadioHomemaker;

	@AndroidFindBy(xpath="//android.widget.RadioButton[6]")
	//@iOSFindBy(xpath="")
	public WebElement btnRadioStudent;

	@AndroidFindBy(xpath="//android.widget.RadioButton[7]")
	//@iOSFindBy(xpath="")
	public WebElement btnRadioTeacher;

	@AndroidFindBy(id="input_City")
	@iOSFindBy(accessibility="CITY")
	public WebElement  txtCity;

	//co.tslc.cashe.android:id/search_src_text
	@AndroidFindBy(id="search_src_text")         
	@iOSFindBy(xpath="(//XCUIElementTypeSearchField[@name='Search'])[2] | //XCUIElementTypeSearchField[@name='Search']")  //(//XCUIElementTypeSearchField[@name='Search'])[2] | 
	public WebElement  searchBar;
	//XCUIElementTypeSearchField[@name="Search"]

	@AndroidFindBy(xpath="(//android.widget.ImageView)[2]")
	@iOSFindBy(xpath="//XCUIElementTypeTable[@name='Search']/XCUIElementTypeCell[1]")
	public WebElement  enterSearch;		

	//XCUIElementTypeTable[@name='Search']/XCUIElementTypeCell[2]
	@AndroidFindBy(xpath="//android.widget.TextView")
	@iOSFindBy(xpath="//XCUIElementTypeTable[@name='Search']/XCUIElementTypeCell[1]")
	public WebElement  selectFromDropDown;

	@AndroidFindBy(id="input_TypeEmployment")
	@iOSFindBy(accessibility="EMPLOYMENT TYPE")
	public WebElement  txtEmployerType;

	@AndroidFindBy(xpath="//android.widget.RadioButton[1]")
	@iOSFindBy(accessibility="Salaried")
	public WebElement  txtEmployerTypeSalaried;

	@AndroidFindBy(id="input_Employer")
	@iOSFindBy(accessibility="EMPLOYER NAME")
	public WebElement  txtEmployerName;

	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='EMPLOYER NAME']/following-sibling::XCUIElementTypeTextField")
	public WebElement  valueEmployerName;

	@AndroidFindBy(id="etEmployer")
	//@iOSFindBy(xpath="")
	public WebElement  txtEditEmployerName;

	@AndroidFindBy(id="input_WorkingSince")
	@iOSFindBy(accessibility="WORKING SINCE")
	public WebElement  txtWorkingSince;

	@AndroidFindBy(id="input_Designation")
	@iOSFindBy(accessibility="DESIGNATION")
	public WebElement  txtSelectDesignation;

	@AndroidFindBy(xpath="//android.widget.RadioButton[1]")
	@iOSFindBy(accessibility="Worker")
	public WebElement  btnRadioWorker;

	@AndroidFindBy(xpath="//android.widget.RadioButton[2]")
	@iOSFindBy(accessibility="Supervisor")
	public WebElement  btnRadioSupervisor;

	@AndroidFindBy(xpath="//android.widget.RadioButton[3]")
	@iOSFindBy(accessibility="Manager")
	public WebElement  btnRadioManager;

	@AndroidFindBy(id="input_Salary")
	@iOSFindBy(accessibility="NET MONTHLY SALARY")
	public WebElement  txtNetMonthlySalary;

	@AndroidFindBy(id="checkedTandC")
	@iOSFindBy(accessibility="checkboxoff")
	public WebElement  btnCheckBox;

	@AndroidFindBy(id="next")
	@iOSFindBy(accessibility="NEXT")
	public WebElement  btnNext;

	@AndroidFindBy(id="congrats")
	@iOSFindBy(accessibility="congratsImage")
	public WebElement  txtCongrats;

	@AndroidFindBy(id="loanAmountMessage")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'profile')]")
	public WebElement  txtLoanAmountMessage;

	@AndroidFindBy(id="loanAmmount")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'₹')]")
	public WebElement  txtLoanAmmount;

	@AndroidFindBy(id="btnNow")
	@iOSFindBy(accessibility="CongratsProfile")
	public WebElement  btnMyProfile;

	@iOSFindBy(accessibility="Passcode")
	public WebElement btnPassCode;

	@AndroidFindBy(id="popup_positive_button")
	@iOSFindBy(accessibility="OK")
	public WebElement  btnPopUpOk;

	@AndroidFindBy(id="etAnswer1")
	@iOSFindBy(xpath="//XCUIElementTypeTable[@name='SecurityQuestions']/XCUIElementTypeCell[2]/XCUIElementTypeTextField")
	public WebElement  txtAnswer1;

	@AndroidFindBy(id="etAnswer2")
	@iOSFindBy(xpath="//XCUIElementTypeTable[@name='SecurityQuestions']/XCUIElementTypeCell[4]/XCUIElementTypeTextField")
	public WebElement  txtAnswer2;


	@AndroidFindBy(id="button1")
	@iOSFindBy(accessibility="Toolbar Done Button")
	public WebElement  btnSet;

	@AndroidFindBy(id="btn_proceed")
	@iOSFindBy(accessibility="PROCEED")
	public WebElement  btnProceed;


	@iOSFindBy(accessibility="APPLY")
	public WebElement  btnApply;

	@AndroidFindBy(id="button1")
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='1']")
	public WebElement  btnOne;
	//android.widget.TextView[@content-desc="NEXT"]

	// My Profile icon
	@AndroidFindBy(id = "action_Profile")
	//@iOSFindBy(id="")
	public WebElement  iconProfile;

	//Personal Details Tab
	@AndroidFindBy(xpath="(//android.widget.TextView[contains(@resource-id,'rvtvProfileLabel')])[1]")      //id="Personal Details"/*xpath = "(//*[@resource-id='co.tslc.cashe.android:id/rv_profile_name_tv'])[1]"*/
	@iOSFindBy(accessibility="PERSONAL DETAILS")
	public WebElement tabPersonalDetails;

	//Personal Details Tab - percentage
	@AndroidFindBy(id = "tvPersonalDtl")
	//@iOSFindBy(id="")
	public WebElement txtPersonalPer;

	//Employment Details Tab
	@AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'rvtvProfileLabel')])[2]")
	@iOSFindBy(accessibility="EMPLOYMENT DETAILS")
	public WebElement tabEmpDetails;

	//Employment Details Tab - percentage
	@AndroidFindBy(id = "tvProfessionalDtl")
	//@iOSFindBy(id="")
	public WebElement tabEmpPer;

	//Bank Details Tab
	@AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'rvtvProfileLabel')])[3]")
	@iOSFindBy(accessibility="BANK DETAILS")
	public WebElement tabBankDetails;

	//Bank Details Tab - percentage
	@AndroidFindBy(id = "tvBankDtl")
	//@iOSFindBy(id="")
	public WebElement tabBankDtlPer;


	//Photo Proofs Tab
	@AndroidFindBy(xpath = "(//android.widget.TextView[contains(@resource-id,'rvtvProfileLabel')])[4]")
	@iOSFindBy(accessibility="PHOTO PROOFS")
	public WebElement tabPhotoPrfs;


	@AndroidFindBy(id = "action_Profile")
	@iOSFindBy(accessibility="Profile")
	public WebElement btnProfile;

	@AndroidFindBy(id = "action_get_cashe")
	@iOSFindBy(accessibility="Get CASHe")
	public WebElement btnGetCashe;

	//Photo Proofs Tab - percentage
	@AndroidFindBy(id = "tvPhotoDtl")
	//@iOSFindBy(id="")
	public WebElement tabPhotoPer;

	//Personal Details - Full Name
	@AndroidFindBy(id = "etName")
	//@iOSFindBy(id="")
	public WebElement txtPDFullName;

	//Personal Details - Mobile Number
	@AndroidFindBy(id = "etMobile")
	//@iOSFindBy(id="")
	public WebElement txtMobileNum;

	//Personal Details -  Email
	@AndroidFindBy(id = "etEmail")
	//@iOSFindBy(id="")
	public WebElement txtEmailId;

	//Personal Details - Male
	@AndroidFindBy(id = "rb_gender_male_profile")
	//@iOSFindBy(id="")
	public WebElement btnRadioPDMale;

	//Personal Details - Female
	@AndroidFindBy(id = "rb_gender_female_profile")
	//@iOSFindBy(id="")
	public WebElement btnRadioPDFemale;

	//Personal Details - PAN Card
	@AndroidFindBy(id = "etPanNo")
	@iOSFindBy(accessibility="PAN CARD")
	public WebElement txtPANCard;

	@AndroidFindBy(id = "etAadhaarNo")
	@iOSFindBy(accessibility="AADHAAR NUMBER")
	public WebElement txtAadharCard;

	//Personal Details - Educational Qualification
	@AndroidFindBy(id = "etEducation")
	//@iOSFindBy(id="")
	public WebElement btnRadioEducation;

	//Personal Details - current Address
	@AndroidFindBy(id = "etCurrentAddr")
	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[10]")
	public WebElement txtCrrntAddr;

	//Personal Details - Pop up - OK button
	@AndroidFindBy(id = "popup_positive_button")
	@iOSFindBy(accessibility="OK")
	public WebElement btnPDOK;

	@iOSFindBy(accessibility="Ok")
	public WebElement btnUPIOk;

	//Personal Details - Pop up - Skip button
	@AndroidFindBy(id = "popup_negative_button")
	//@iOSFindBy(id="")
	public WebElement btnPDSkip;

	//Personal Details - Address
	@AndroidFindBy(id = "input_Address")
	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[2]")
	public  WebElement txtAddress;

	//Personal Details - Locality/Area
	@AndroidFindBy(id = "input_Locality")
	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[3]")
	public WebElement txtLocality;

	//Personal Details - Pin Code
	@AndroidFindBy(id = "input_Pincode")
	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[4]")
	public WebElement txtPinCode;

	//Personal Details - State
	@AndroidFindBy(id = "input_State")
	@iOSFindBy(xpath="(//XCUIElementTypeTextField)[6]")
	public WebElement txtState;

	//Personal Details - Save button
	@AndroidFindBy(id = "next")
	@iOSFindBy(accessibility="SAVE")
	public WebElement btnSave;

	//Personal Details Screen - Save button
	/*@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='SAVE']")
	@iOSFindBy(accessibility="SAVE")
	public WebElement btnPDSave;*/

	//Personal Details(Pop Up) -  JUNK IT
	@AndroidFindBy(id = "popup_negative_button")
	//@iOSFindBy(id="")
	public WebElement btnJunkIt;

	//Personal Details(Pop Up) - Save IT 
	@AndroidFindBy(id = "popup_positive_button ")
	//@iOSFindBy(id="")
	public WebElement btnSaveIt;

	//Personal Details (Save - Pop up) - Ok button
	@AndroidFindBy(id = "popup_positive_button")
	@iOSFindBy(accessibility="SURE")
	public WebElement btnOk;

	//Employment Details - Landline Number
	@AndroidFindBy(id = "etLandline")
	@iOSFindBy(accessibility = "LANDLINE NUMBER")
	public WebElement txtLandline;

	//Employment Details - Landline Extension Number
	@AndroidFindBy(id = "etLandlineExt")
	//@iOSFindBy(id = "")
	public WebElement txtLLExtn;

	//Employment Details - Official Email
	@AndroidFindBy(id = "etProfEmail")
	@iOSFindBy(accessibility = "OFFICIAL EMAIL")
	public WebElement txtOffEmail;

	//Employment Details - Office Address
	@AndroidFindBy(id = "etOfficeAddr")
	@iOSFindBy(accessibility = "WORK ADDRESS")
	public WebElement txtOffAddress;

	//Employment Details - Address
	@AndroidFindBy(id = "input_Address")
	@iOSFindBy(xpath = "(//XCUIElementTypeTextField)[1]")
	public WebElement txtOfcAddress;

	//Employment Details - Locality
	@AndroidFindBy(id = "input_Locality")
	@iOSFindBy(xpath = "(//XCUIElementTypeTextField)[2]")
	public WebElement txtOfcLocality1;

	//Employment Details - Locality
	@AndroidFindBy(id = "input_Pincode")
	@iOSFindBy(xpath = "(//XCUIElementTypeTextField)[3]")
	public WebElement txtOfcPincode;

	//Employment Details - City
	@AndroidFindBy(id = "input_City")
	@iOSFindBy(accessibility = "CITY")
	public WebElement txtOfcCity;

	//Employment Details - State
	@AndroidFindBy(id = "input_State")
	@iOSFindBy(accessibility = "STATE")
	public WebElement txtOfcState;

	//Employment Details - Address Save option
	@AndroidFindBy(id = "next")
	@iOSFindBy(accessibility = "SAVE")
	public WebElement btnAddSave;

	//Employment Details - save
	@AndroidFindBy(id = "next")
	@iOSFindBy(accessibility = "SAVE")
	public WebElement btnEmpSave;

	//Employment Details - popup ok
	@AndroidFindBy(id = "popup_positive_button")
	@iOSFindBy(accessibility = "OK")
	public WebElement btnEmpOk;


	//Bank Details - Bank Name
	@AndroidFindBy(id = "etBankName")
	@iOSFindBy(accessibility = "BANK NAME")
	public WebElement txtBank;

	//Bank Details - Account Number
	@AndroidFindBy(id = "etBankAccount")
	@iOSFindBy(accessibility = "ACCOUNT NUMBER")
	public WebElement txtBankAccnt;

	//Bank Details - Confirm Account Number
	@AndroidFindBy(id = "etConfirmAccount")
	@iOSFindBy(accessibility = "CONFIRM ACCOUNT NUMBER")
	public WebElement txtConfirmBankAccnt;

	//Bank Details - IFSC Code
	@AndroidFindBy(id = "etBankIfsc")
	@iOSFindBy(accessibility = "IFSC CODE")
	public WebElement txtIFSC;

	//Bank Details - Save button
	@AndroidFindBy(id = "popup_positive_button")
	//@iOSFindBy(id = "")
	public WebElement btnBDSave;

	//Bank Details - Lookup 
	@AndroidFindBy(id = "etBankIfsc")
	//@iOSFindBy(id = "")
	public WebElement btnLookup;

	//Bank Details - Lookup IFSC Screen - Bank Name
	@AndroidFindBy(id = "etBankName")
	//@iOSFindBy(id = "")
	public WebElement btnLookupBankName;

	//Bank Details - Lookup IFSC Screen - State
	@AndroidFindBy(id = "etBankState")
	//@iOSFindBy(id = "")
	public WebElement btnLookupState;

	//Bank Details - Lookup IFSC Screen - State
	@AndroidFindBy(id = "etBankCity")
	//@iOSFindBy(id = "")
	public WebElement btnLookupCity;

	//Bank Details - Lookup IFSC Screen - Branch
	@AndroidFindBy(id = "etSelectedBranch")
	//@iOSFindBy(id = "")
	public WebElement btnLookupBranch;

	//Bank Details - Lookup IFSC Screen - IFSC Code
	@AndroidFindBy(id = "etBankIfsc")
	//@iOSFindBy(id = "")
	public WebElement btnLookupIFSC;

	//Bank Details - Lookup IFSC Screen - Proceed button
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='PROCEED']")
	//@iOSFindBy(id = "")
	public WebElement btnLookupProceed;

	//Mahitha

	//Photo Proofs - Photo
	@AndroidFindBy(id = "tv_photo")
	//@iOSFindBy(id = "")
	public WebElement imgPhoto;

	//Photo Proofs - PAN
	@AndroidFindBy(id = "tv_pan")
	@iOSFindBy(accessibility = "Pan card")
	public WebElement imgPan;

	@AndroidFindBy(id = "btn_eSign_virtual_adhaar")
	public WebElement btnVirtualAadhaar;

	@AndroidFindBy(id = "virtualId")
	public WebElement txtVirtualId;

	//eSign Button
	@AndroidFindBy(id ="btn_eSign")
	@iOSFindBy(accessibility="VerifyMobile")
	public WebElement btnesign;

	//eSign Text field 
	@AndroidFindBy(id="input_eSign")
	@iOSFindBy(xpath="//XCUIElementTypeTable[@name='UploadPhotoProofs']//XCUIElementTypeTextField")
	public WebElement txteSign;	

	@AndroidFindBy(id="title")
	@iOSFindBy(xpath="//XCUIElementTypeNavigationBar[@name='eSign']")
	public WebElement titleeSign;

	@AndroidFindBy(xpath="//android.view.View[@content-desc='Transaction Failed.']")
	//@iOSFindBy(xpath="")
	public WebElement eSignServerDown;

	@AndroidFindBy(xpath="//android.view.View[@content-desc='Aadhaar Number']")
	//@iOSFindBy(xpath="")
	public WebElement aadhaarlabel;

	@AndroidFindBy(id="aadhaarNo")
	//@iOSFindBy(id="")
	public WebElement adhrNumber;

	@AndroidFindBy(id="otpCheck")
	@iOSFindBy(xpath="//XCUIElementTypeSwitch")
	public WebElement chkboxeSignOTP;

	@AndroidFindBy(id="otpRequestBtn")
	@iOSFindBy(accessibility="Request OTP")
	public WebElement btnOTPeSign;


	@AndroidFindBy(id="otpSubmitBtn")
	@iOSFindBy(accessibility="Submit")
	public WebElement btnOTPSubmit;

	@AndroidFindBy(id="back")
	@iOSFindBy(accessibility="leftArrow")
	public WebElement btnBack;

	@iOSFindBy(accessibility="Back")
	public WebElement btnEsignBack;


	@AndroidFindBy(id="popup_positive_button")
	@iOSFindBy(accessibility="alert_generic")
	public WebElement btnEsignFailAlert;

	//Photo Proofs - Employee Id
	@AndroidFindBy(xpath = "tv_employee_id")
	//@iOSFindBy(id = "")
	public WebElement imgId;

	//Photo Proofs - Salary Slip
	@AndroidFindBy(xpath = "tv_salary_slip")
	//@iOSFindBy(id = "")
	public WebElement imgSalSlip;

	//Photo Proofs - Bank Statement
	@AndroidFindBy(xpath = "tv_bank_statement")
	//@iOSFindBy(id = "")
	public WebElement imgBankStmt;

	//Photo Proofs - Utility Bill
	@AndroidFindBy(xpath = "tv_utility")
	//@iOSFindBy(id = "")
	public WebElement imgUtility;

	//Photo Proofs - Telephone Bill
	@AndroidFindBy(xpath = "tv_telephone_bill")
	//@iOSFindBy(id = "")
	public WebElement imgTelBill;

	//Photo Proofs - Voter Id
	@AndroidFindBy(xpath = "tv_voter_id")
	//@iOSFindBy(id = "")
	public WebElement imgVoterId;

	//Photo Proofs - Passport
	@AndroidFindBy(xpath = "tv_passport")
	//@iOSFindBy(id = "")
	public WebElement imgPassport;

	//Photo Proofs - License
	@AndroidFindBy(xpath = "tv_license")
	//@iOSFindBy(id = "")
	public WebElement imgLicense;

	//Photo Proofs -Aadhar
	@AndroidFindBy(xpath = "tv_aadhar")
	//@iOSFindBy(id = "")
	public WebElement imgAadhar;

	//Photo Proofs - Photo
	@AndroidFindBy(xpath = "tv_rental_agreement")
	//@iOSFindBy(id = "")
	public WebElement imgRentalAgrmt;

	//Customer Badge
	@AndroidFindBy(xpath = "customerGrade")
	//@iOSFindBy(id = "")
	public WebElement txtBadge;

	//Remind Me Later Button
	@AndroidFindBy(id = "popup_negative_button")
	//@iOSFindBy(xpath="")
	public WebElement btnRemindMeLater;

	// SLQ

	// SLQ Generating text msg
	@AndroidFindBy(id = "co.tslc.cashe.android:id/popup_message")
	@iOSFindBy(xpath="//XCUIElementTypeTextView")
	public WebElement slqPopupText;

	/*// SLQ Generating popup ok message
	@AndroidFindBy(id = "co.tslc.cashe.android:id/popup_positive_button")
	public WebElement slqPopupOk;*/


	// SLQ Score
	@AndroidFindBy(id = "co.tslc.cashe.android:id/tv_progress")
	public WebElement slqScore;


	public Profile(AppiumDriver driver, ExtentTest eTest,Hashtable<String, String> dataArray,DataBaseConnection dbCon) {
		this.driver = driver;
		this.eTest=eTest;
		this.dataArray=dataArray;
		this.dbCon=dbCon;
		setDriver(driver);
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);
		getCashe = new GetCashe (driver, eTest, dataArray, dbCon);
		initilize();

	}


	public void clickIconProfile() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, iconProfile))
			clickonElement(driver, iconProfile, "Sucessfully clicked on Profile menu item", "Unable to click on Profile menu");


	}

	public void enterFullName(String name) throws InterruptedException, IOException{
		waitFor(5);
		capture(Status.PASS,"MandatoryForm",eTest);
		if(isElementClickable(driver, txtFullName))	{
			eTest.log(Status.INFO, "User at mandatory form");
			//	capture("mandatoryForm", LogStatus.INFO, eTest);
			mobileSetElement(driver, txtFullName, name, "", "");
			if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
				((AppiumDriver) driver).hideKeyboard();
			}
		}
	}
	public void enterEmail() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, txtEmail, "", "");
	}
	public void maleRadio() throws FileNotFoundException, InterruptedException{
		if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
			clickonElement(driver, txtGender, "", "");
		}

		clickonElement(driver, btnRadioMale, "", "");
	}
	public void clickOkButton() throws FileNotFoundException, InterruptedException{
		if(isElementDisplayed(btnOK))
			clickonElement(driver, btnOK, "", "");

	}
	public void selectDOB() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, txtDOB, "", "");
		clickOkButton();
	}

	public void selectEducation(String education) throws FileNotFoundException, InterruptedException{
		clickonElement(driver, txtEducation, "", "");
		if("undergraduate".equalsIgnoreCase(education)){
			clickonElement(driver, btnRadioUnderGraduate, "", "");
		}else if("graduate".equalsIgnoreCase(education)){
			clickonElement(driver, btnRadioGraduate, "", "");

		}else{
			clickonElement(driver, btnRadioPostGraduate, "", "");
		}
		if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			btnOKPopUp.click();
		}
	}

	public void selectAccommodation(String accommodation) throws FileNotFoundException, InterruptedException{
		clickonElement(driver, txtAccommodation, "", "");
		if("pg".equalsIgnoreCase(accommodation)){
			clickonElement(driver, btnRadioPG, "", "");
		}else if("rent".equalsIgnoreCase(accommodation)){
			clickonElement(driver, btnRadioRent, "", "");

		}else{
			clickonElement(driver, btnRadioOwn, "", "");
		}
		if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			btnOKPopUp.click();
		}
	}

	/*@public void selectEmploymentType(String employmenttype) throws FileNotFoundException, InterruptedException{
		clickonElement(driver, txtEmployerType, "", "");
		if("salaried".equalsIgnoreCase(employmenttype)){
			clickonElement(driver, btnRadioSalaried, "", "");
		}else if("unemployed".equalsIgnoreCase(employmenttype)){
			clickonElement(driver, btnRadioUnemployed, "", "");
		}else if("selfemployed".equalsIgnoreCase(employmenttype)){
			clickonElement(driver, btnRadioSelfEmployed, "", "");
		}else if("business".equalsIgnoreCase(employmenttype)){
			clickonElement(driver, btnRadioBusiness, "", "");
		}else if("homemaker".equalsIgnoreCase(employmenttype)){
			clickonElement(driver, btnRadioHomemaker, "", "");
		}else if("student".equalsIgnoreCase(employmenttype)){
			clickonElement(driver, btnRadioStudent, "", "");
		}else{
			clickonElement(driver, btnRadioTeacher, "", "");
		}
		if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			btnOKPopUp.click();
		}
	}*/
	public void enterCity(String city) throws FileNotFoundException, InterruptedException{

		if(dataArray.get("Platform").toLowerCase().equalsIgnoreCase("android")) {
			clickonElement(driver, txtCity, "", "");
			mobileSetElement(driver, searchBar, city, "", "");
		}else {
			mobileSetElement(driver, txtCity, city, "", "");
		}
		clickonElement(driver,enterSearch, "", "");

	}

	public void employerInfo(String employer,String designation,String salary,String custId) throws InterruptedException, IOException{
		waitFor(4);
		if(!dataArray.get("Platform").equalsIgnoreCase("android")) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap scrollObject = new HashMap<>();
			scrollObject.put("predicateString", "value = NET MONTHLY SALARY");
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);
			/*RemoteWebElement element = (RemoteWebElement)driver.findElement(By.name("NET MONTHLY SALARY"));

				String elementID = element.getId();
				HashMap<String, String> scrollObject = new HashMap<String, String>();
				scrollObject.put("element", elementID); // Only for ‘scroll in element’
				scrollObject.put("direction", "down");
				((RemoteWebDriver) driver).executeScript("mobile:scroll", scrollObject);*/

		}
		mobileSetElement(driver, txtNetMonthlySalary, salary, "", "");
		if(!dataArray.get("Platform").equalsIgnoreCase("android")) {

			clickOkButton();
		}
		clickonElement(driver, txtWorkingSince, "", "");
		clickOkButton();


		clickonElement(driver, txtSelectDesignation, "", "");
		/*if(!dataArray.get("Platform").equalsIgnoreCase("android")) {

			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap scrollObject = new HashMap<>();
			scrollObject.put("predicateString", "value = DESIGNATION");
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);

			waitFor(4);
			clickonElement(driver, txtSelectDesignation, "", "");

		}*/
		if("worker".equalsIgnoreCase(designation)){
			clickonElement(driver, btnRadioWorker, "", "");
		}else if("supervisor".equalsIgnoreCase(designation)){
			clickonElement(driver, btnRadioSupervisor, "", "");

		}else{
			clickonElement(driver, btnRadioManager, "", "");
		}
		if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			btnOKPopUp.click();
		}
		waitFor(5);
		//etEmployer
		clickonElement(driver, txtEmployerName,"","");
		mobileSetElement(driver, searchBar, employer, "", "");
		clickonElement(driver, enterSearch,"","");
		waitFor(5);
		clickonElement(driver,txtEmployerType,"","");

		clickonElement(driver,txtEmployerTypeSalaried,"","");
		if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			btnOKPopUp.click();
		}
		waitFor(3);
		String employer_name=null;
		if(dataArray.get("Platform").equalsIgnoreCase("android"))
			employer_name=txtEmployerName.getText();
		else
			employer_name=valueEmployerName.getAttribute("value");
		swipeTo();
		capture(Status.PASS,"P0",eTest);

		clickonElement(driver, btnCheckBox, "", "");
		clickonElement(driver, btnNext, "", "");

		companyValidation(custId, employer_name);

	}

	public void loanEligible() throws IOException{
		if(txtCongrats.isDisplayed()){
			//	eTest.log(LogStatus.INFO, "Congrats! user eligible for Loan");
			logger.info("Success user eligible for loan");
			logger.info("Loan eligible message :"+txtLoanAmountMessage.getText());
			logger.info("Loan eligible amount :"+txtLoanAmmount.getText());
			eTest.log(Status.INFO, "Loan eligible amount :"+txtLoanAmmount.getText());

			//	eTest.log(LogStatus.INFO, "Loan eligible message :"+txtLoanAmountMessage.getText());
			//	eTest.log(LogStatus.INFO, "Loan eligible amount :"+txtLoanAmmount.getText());
			//capture("Loan eligible",LogStatus.PASS,eTest);
			capture(Status.INFO,dataArray.get("TCNo"),eTest);


		}else{
			//	eTest.log(LogStatus.FAIL, "Something went wrong,Please check screenshot");
			Assert.fail("Something went wrong");
		}
	}

	public void clickMyProfile() throws FileNotFoundException, InterruptedException{

		clickonElement(driver, btnMyProfile, "", "");
		if(!dataArray.get("Platform").equalsIgnoreCase("android"))
			clickonElement(driver, btnPassCode, "", "");

		clickonElement(driver, btnPopUpOk, "", "");
	}
	public void securityQues() throws InterruptedException, IOException{
		mobileSetElement(driver, txtAnswer1, "cash", "", "");
		clickonElement(driver, txtAnswer2, "", "");
		clickonElement(driver,  btnSet, "", "");
		//eTest.log(LogStatus.INFO, "User at SecurityQuestions Page");
		//capture("SecurityQuestions",LogStatus.PASS,eTest);
		if(dataArray.get("Platform").equalsIgnoreCase("android")) {
			clickonElement(driver, btnProceed, "", "");
		}else {
			clickonElement(driver, btnOK, "", "");

		}

	}
	public void enterMPIN() throws FileNotFoundException, InterruptedException{
		for(int i=1;i<5;i++){
			clickonElement(driver, btnOne, "", "");
		}
		clickonElement(driver, btnProceed, "", "");
	}
	public void confirmMPIN() throws FileNotFoundException, InterruptedException{
		for(int i=1;i<5;i++){
			clickonElement(driver, btnOne, "", "");
		}
		clickonElement(driver, btnApply, "", "");
	}

	public void pageSwipeDown(){
		(new TouchAction(driver))
		.press(450, 1656)
		.moveTo( 16,  -1374)
		.release()
		.perform();
	}

	public void clickPersonalDetailsTab() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnUPIOk)) {
			clickonElement(driver, btnUPIOk, "", "");
		}

		clickonElement(driver, tabPersonalDetails, "", "");
	}



	public void enterPAN() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, txtPANCard, "", "");
		String pan =RandomStringUtils.randomAlphabetic(5).toUpperCase() + RandomStringUtils.randomNumeric(4)+RandomStringUtils.randomAlphabetic(1).toUpperCase();
		if(isElementClickable(driver, txtPANCard))
			mobileSetElement(driver, txtPANCard,pan ,"", "");
	}
	public void enterAadhar() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, txtAadharCard, "", "");
		String aadhar = getRandAadhaarNumber();//RandomStringUtils.randomNumeric(12);
		if(isElementClickable(driver, txtAadharCard))
			mobileSetElement(driver, txtAadharCard,aadhar ,"", "");

		if(!dataArray.get("Platform").equalsIgnoreCase("android")) {

			btnDone.click();
		}
	}

	public void enterAddress(String address,String locality,String pincode,String state ) throws InterruptedException, IOException{
		if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap scrollObject = new HashMap<>();
			scrollObject.put("predicateString", "value = CURRENT ADDRESS");
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);
		}else {
			scrollToElement(txtCrrntAddr);
		}
		clickonElement(driver, txtCrrntAddr, "", "");
		//clickonElement(driver, btnPDSkip, "", "");
		mobileSetElement(driver,txtAddress, address ,"" , "");
		mobileSetElement(driver, txtLocality, locality, "","");
		mobileSetElement(driver, txtPinCode, pincode, "","");
		//mobileSetElement(driver, txtState, state, "","");
		clickonElement(driver, txtOfcState, "", "");
		mobileSetElement(driver,searchBar, state,"" , "");
		clickonElement(driver, selectFromDropDown, "", "");
		//	eTest.log(LogStatus.INFO, "User at PersonalDetails screen");
		//	capture("PersonalDetails",LogStatus.PASS,eTest);
		clickonElement(driver, btnSave, "", "");

	}

	public void enterSaveP() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, btnSave, "", "");
		try {
			clickonElement(driver, btnOk, "", "");
		}catch(Exception e) {
			clickonElement(driver, btnOK, "", "");

		}
	}

	public void clickEmploymentDetailsTab() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, tabEmpDetails, "", "");
	}

	public void employerData(String landline,String ofcEmail){
		mobileSetElement(driver,txtLandline, landline,"" , "");
		mobileSetElement(driver,txtOffEmail, ofcEmail,"" , "");
		if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
			if(isElementClickable(driver, btnDone))
				btnDone.click();
		}
	}

	public void ofcAddress(String addressOfc,String localityOfc,String pincodeOfc,String cityOfc,String stateOfc) throws InterruptedException, IOException{
		clickonElement(driver, txtOffAddress, "", "");
		//clickonElement(driver, btnPDSkip, "", "");
		mobileSetElement(driver,txtOfcAddress, addressOfc,"" , "");
		mobileSetElement(driver,txtOfcLocality1, localityOfc,"" , "");
		mobileSetElement(driver,txtOfcPincode, pincodeOfc,"" , "");

		clickonElement(driver, txtOfcCity, "", "");
		mobileSetElement(driver,searchBar, cityOfc,"" , "");
		clickonElement(driver, enterSearch, "", "");


		clickonElement(driver, txtOfcState, "", "");
		mobileSetElement(driver,searchBar, stateOfc,"" , "");
		clickonElement(driver, selectFromDropDown, "", "");

		//	eTest.log(LogStatus.INFO, "User at EmployementDetails screen");
		//	capture("EmployementDetails",LogStatus.PASS,eTest);

		clickonElement(driver, btnAddSave,"","");


	}

	public void empSave() throws FileNotFoundException, InterruptedException{

		clickonElement(driver, btnEmpSave,"", "");
		clickonElement(driver, btnEmpOk,"", "");
	}

	public void clickBankDetailsTab() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, tabBankDetails, "", "");

	}



	public void enterBankDetails(String bankName,String accNo,String cAccNo,String ifsc) throws InterruptedException, IOException{
		mobileSetElement(driver,txtBankAccnt, accNo,"" , "");
		mobileSetElement(driver,txtConfirmBankAccnt, cAccNo,"" , "");
		mobileSetElement(driver,txtIFSC, ifsc,"" , "");
		clickonElement(driver,txtBank,"" , "");

		mobileSetElement(driver,searchBar, bankName,"" , "");
		clickonElement(driver, selectFromDropDown, "", "");
		//	eTest.log(LogStatus.INFO, "User at BankDetails screen");
		//	capture("BankDetails",LogStatus.PASS,eTest);

		clickonElement(driver, btnEmpSave,"", "");
		clickonElement(driver, btnPopUpOk,"", "");
	}

	public void clickPhotoProofsTab() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, tabPhotoPrfs, "", "");

	}

	public void profilePercentage() throws FileNotFoundException, InterruptedException{
		clickonElement(driver, txtPersonalPer, "", "");
	}

	public void disableOffEmail(String custid){
		//DataBaseConnection dbCon=new //DataBaseConnection();
		//adding customer id in exceptional table for office email verification
		String query ="INSERT INTO official_email_exceptional_customers (customer_id,created_by,remarks) VALUES ("+custid+",1,1)"; 
		dbCon.dataBaseUpdate(query);
		System.out.println("Value updated");

	}
	public void uploadPhotoProofsDb(String customerId, String userId) throws InterruptedException, IOException{
		//DataBaseConnection dbCon=new //DataBaseConnection();

		//Profile Photo
		dbCon.dataBaseUpdate("INSERT INTO customer_documents "
				+ "(customer_id,document_name,document_type_id,document_subtype_id,is_active,created_date,updated_date,is_pdf,created_by) "
				+ "VALUES("+customerId+",'5573949/pancard/IMG_20171129_115513_compressed_2018-05-31-055117.jpg',1,0,1,'2017-12-05 09:06:07','2017-12-05 09:06:07',0,"+userId+")");
		System.out.println("Profile Photo updated");

		//Pan Photo
		dbCon.dataBaseUpdate("INSERT INTO customer_documents "
				+ "(customer_id,document_name,document_type_id,document_subtype_id,is_active,created_date,updated_date,is_pdf,created_by,is_esigned) "
				+ "VALUES("+customerId+",'5573949/pancard/IMG_20171129_115513_compressed_2018-05-31-055117.jpg',4,0,1,'2017-12-05 09:06:07','2017-12-05 09:06:07',0,"+userId+",0)");
		System.out.println("Pan Photo updated");

		//employee Badge Photo
		dbCon.dataBaseUpdate("INSERT INTO customer_documents "
				+ "(customer_id,document_name,document_type_id,document_subtype_id,is_active,created_date,updated_date,is_pdf,created_by) "
				+ "VALUES("+customerId+",'5573949/pancard/IMG_20171129_115513_compressed_2018-05-31-055117.jpg',8,0,1,'2017-12-05 09:06:07','2017-12-05 09:06:07',0,"+userId+")");
		System.out.println("employee Badge updated");

		//Payslip  Photo
		dbCon.dataBaseUpdate("INSERT INTO customer_documents "
				+ "(customer_id,document_name,document_type_id,document_subtype_id,is_active,created_date,updated_date,is_pdf,created_by) "
				+ "VALUES("+customerId+",'5573949/pancard/IMG_20171129_115513_compressed_2018-05-31-055117.jpg',3,0,1,'2017-12-05 09:06:07','2017-12-05 09:06:07',0,"+userId+")");
		System.out.println("Payslip updated");

		//Bank Statement  Photo
		dbCon.dataBaseUpdate("INSERT INTO customer_documents "
				+ "(customer_id,document_name,document_type_id,document_subtype_id,is_active,created_date,updated_date,is_pdf,created_by) "
				+ "VALUES("+customerId+",'5573949/pancard/IMG_20171129_115513_compressed_2018-05-31-055117.jpg',5,0,1,'2017-12-05 09:06:07','2017-12-05 09:06:07',0,"+userId+")");
		System.out.println("Bank Statement updated");

		//Address  Photo
		dbCon.dataBaseUpdate("INSERT INTO customer_documents "
				+ "(customer_id,document_name,document_type_id,document_subtype_id,is_active,created_date,updated_date,is_pdf,created_by) "
				+ "VALUES("+customerId+",'5573949/pancard/IMG_20171129_115513_compressed_2018-05-31-055117.jpg',2,4,1,'2017-12-05 09:06:07','2017-12-05 09:06:07',0,"+userId+")");
		System.out.println("Address updated");


		//eSignFlow(customerId);
		//updateEsignInDB(customerId);

	}


	/*public void updateEsignInDB(String customerId) {
		//DataBaseConnection dbConn=new //DataBaseConnection();
		dbCon.dataBaseUpdate("UPDATE customer_documents SET is_esigned =1 WHERE document_type_id =4 AND is_esigned =0 AND customer_id = "+customerId+"");
		System.out.println("PAN eSigned updated");
	}

	public void eSignFlow(String customerId) throws InterruptedException, IOException {
		clickonElement(driver, btnGetCashe, "", "");
		waitFor(3);
		clickonElement(driver, btnProfile, "", "");
		if(isElementClickable(driver, tabPhotoPrfs)) {
			clickonElement(driver, tabPhotoPrfs, "", "");
		}
		clickonElement(driver, imgPan, "", "");
		//PAN eSign Mandatory
		//Generating the Random Aadhaar Number
		String aadhaarno = getRandAadhaarNumber();
		mobileSetElement(driver,txteSign , aadhaarno, "Sucessfully entered Aadhaar Number", "Unable to enter Aadhaar Number");
		System.out.println("Entered Random Aadhaar Number");

		clickonElement(driver, btnesign, "", "");

		if(titleeSign.getText().contains("eSign")){
			capture(Status.INFO, "eSignNSDLScreen", eTest);
			clickonElement(driver, chkboxeSignOTP, "", "");
			capture(Status.INFO, "OTPcheckbox", eTest);
			clickonElement(driver, btnOTPeSign, "", "");

			//Updating the column as is_eSigned as 1
			updateEsignInDB(customerId);


			if(isElementClickable(driver, btnOTPSubmit)) {

				capture(Status.INFO, "btnOTPSubmit", eTest);
			}else {
				eTest.log(Status.ERROR, "Esign Not available");
				capture(Status.INFO, "Esign Not available", eTest);
			}
			clickonElement(driver, btnBack, "", "");

			if(isElementPresent(btnEsignFailAlert, 20)) {
				capture(Status.FAIL, "Esign Failed", eTest);
				Assert.fail();
			}
			if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
				clickonElement(driver, btnEsignBack, "", "");
			}else {
				clickonElement(driver, btnBack, "", "");
			}
			clickonElement(driver, btnBack, "", "");

		}	

	}
	 */
	public void profileCompletePopup() throws FileNotFoundException, InterruptedException{
		if(isElementDisplayed(btnPDOK))
			clickonElement(driver, btnPDOK, "Sucessfully clicked on Get cashe button in pop-up", "Unable to click on Get cashe button in pop-up");

	}

	public void EditProfile(LandingPage lp)throws FileNotFoundException, InterruptedException{
		clickIconProfile();
		switch (dataArray.get("EditProfile")) {
		case "EditSalary":
			clickEmploymentDetailsTab();
			//	eTest.log(LogStatus.INFO, "Edit salary in employement details:"+dataArray.get("EditSalary"));
			mobileSetElement(driver, txtNetMonthlySalary, dataArray.get("EditSalary"), "", "");
			break;
		case "EditCompany":
			clickEmploymentDetailsTab();
			//	eTest.log(LogStatus.INFO, "Edit company name in employement details:"+dataArray.get("EditCompany"));
			clickonElement(driver, txtEditEmployerName,"","");
			mobileSetElement(driver, searchBar, dataArray.get("EditCompany"), "", "");
			clickonElement(driver, enterSearch,"","");
			clickonElement(driver, btnSave, "", "");
			clickonElement(driver, btnOk, "", "");

			break;
		case "EditAccNo":
			clickBankDetailsTab();
			//	eTest.log(LogStatus.INFO, "Edit bank account number in bank details:"+dataArray.get("EditAccNo"));
			mobileSetElement(driver, txtBankAccnt, dataArray.get("EditAccNo"), "", "");  
			mobileSetElement(driver, txtConfirmBankAccnt, dataArray.get("EditAccNo"), "", "");  

			clickonElement(driver, btnSave, "", "");
			clickonElement(driver, btnOk, "", "");
			break;
		case "EditIfsc":
			clickBankDetailsTab();
			//	eTest.log(LogStatus.INFO, "Edit ifsc code in bank details:"+dataArray.get("EditIfsc"));
			mobileSetElement(driver, txtIFSC, dataArray.get("EditIfsc"), "", "");   
			break;
		case "EditMobileNum":
			clickPersonalDetailsTab();
			//	eTest.log(LogStatus.INFO, "Edit mobile number in personal details:");
			lp.mobileNumber();

		}

	}
	public void slqValidation(String customer_id,String planType) throws InterruptedException, IOException{
		logger.info("Doing SLQ Validation");
		slqDBValidation(customer_id);
		if(status.equals("0")){
			if(custStatus.equals("Data Received P3")){
				getCashe.clickOnPlan(planType);
				String Expected_text=slqPopupText.getText();
				//Validating the pop-up message--calculating slq
				capture(Status.INFO,"SLQ AI",eTest);
				Assert.assertEquals(Expected_text,"Our AI engine is calculating your SLQ score. It will take a few minutes. You will receive a push notification once the score is ready.");
				clickonElement(driver, btnPopUpOk, "Sucessfully clicked on SLQ generating popup OK button", "Unable to click on SLQ generating popup OK button");
				waitFor(120);;//wait until sql is not null
				slqDBValidation(customer_id);
				if(slqScoreDB==null) { //need to check condition
					logger.info("Slq Score not Generated after 2 min");
					Assert.fail("Slq Score not Generated after 2 min");
				}
				slqCondition(slqScoreDB,planType,customer_id);


			}
		}
		else{
			slqDBValidation(customer_id);
			if(custStatus.equals("Credit Approved") || custStatus.equals("Cash Requested"))
			{
				//Comparing SLQ score from DB and App 
				//@@@@@@@@@@@@@@@

				slqCondition(slqScoreDB,planType,customer_id);
				clickonElement(driver, btnProfile, "Successfully clicked on Profile Button", "Unable clicked on Profile Button");

				if(isElementDisplayed(slqScore)) {
					capture(Status.FAIL, "SLQScore", eTest);
					Assert.fail("Slq Score Not displaying when score is higher "+slqScore);
				}

				String slqApp = slqScore.getText();
				//Assert.assertEquals(slqApp,neoSlq.split(".")[0]);
				float r=  Float.valueOf(neoSlqDB);
				int neoSlqDB1=(int) r;

				if(!neoSlqDB.contains(slqApp)) {
					Assert.fail("Neo SLq from DB and App Not matchecd :"+slqApp+" , From DB:"+neoSlqDB);
				}

				if(neoSlqDB1 > Integer.parseInt(pro.getProperty("slq_score")))
				{
					Assert.fail();
				}


				//Assert.assertEquals(slqScore.isDisplayed(), true);
				logger.info("SLQ score is greater than "+ pro.getProperty("slq_score")+"::: From app score is:::"+slqScore.isDisplayed());
				//######getcashe.clickOnPlan(planType);
			}
			else{
				//Assert.assertEquals(slqScore.isDisplayed(), false);
				clickonElement(driver, btnProfile, "Successfully clicked on Profile Button", "Unable clicked on Profile Button");
				if(isElementDisplayed(slqScore)) {
					capture(Status.FAIL, "SLQScore", eTest);
					Assert.fail("Slq Score is displaying when score is less "+slqScore);
				}
				logger.info("SLQ score should not be visible in Profile screen  ::");
				Assert.assertEquals(getCashe.btn15dayApplyNow.isDisplayed(), false);
			}
		}
	}

	public void updateSLQScore(String customer_id) {
		dbCon.dataBaseUpdate("UPDATE customer_details SET slq_score=550 WHERE customer_id="+customer_id+"");
	}
	public void slqCondition(String slqScoreDB,String planType,String customer_id) throws InterruptedException, IOException
	{

		float r=  Float.valueOf(slqScoreDB);
		int slqScoreDB1=(int) r;
		List<String> slqScoreConfigured=dbCon.dataBaseSelect("select max_value from `social_loan_quotient` where id=1");
		if( slqScoreDB1 >= Integer.parseInt(slqScoreConfigured.get(0))){
			clickonElement(driver, btnProfile, "Successfully clicked on Profile Button", "Unable clicked on Profile Button");
			/*if(!isElementDisplayed(slqScore)) {
				capture(Status.FAIL, "SLQScore", eTest);
				Assert.fail("Slq Score Not displaying when score is higher "+slqScore);
			}*/
			clickonElement(driver, btnGetCashe, "Successfully clicked on Get Cashe Button", "Unable clicked on Get Cashe Button");
			logger.info("SLQ score is greater than "+ Integer.parseInt(slqScoreConfigured.get(0)));
			getCashe.clickOnPlan(planType);
		}
		else{
			clickonElement(driver, btnProfile, "Successfully clicked on Profile Button", "Unable clicked on Profile Button");
			/*if(isElementDisplayed(slqScore)) {
				capture(Status.FAIL, "SLQScore", eTest);
				Assert.fail("Slq Score is displaying when score is less "+slqScore);
			}*/
			clickonElement(driver, btnGetCashe, "Successfully clicked on Get Cashe Button", "Unable clicked on Get Cashe Button");
			logger.info("SLQ score is lesser than "+ Integer.parseInt(slqScoreConfigured.get(0)));
			getCashe.clickOnPlan(planType);
			if(!(status.equals("0")))
			{
				String Expected_text=slqPopupText.getText();
				logger.info(Expected_text);
				//Validating the pop-up message
				//Assert.assertEquals("Sorry! Your SLQ score is below our cutoff mark.We won't be able to give you a loan at this point.Please check back after a few months", Expected_text);

				clickonElement(driver, btnPopUpOk, "Sucessfully clicked on SLQ popup OK button", "Unable to click on SLQ popup OK button");

				logger.info("Update slq score as 550 to continue further::");
				dbCon.dataBaseUpdate("UPDATE customer_details SET slq_score=550 WHERE customer_id="+customer_id+"");
			}

		}
	}

	public void slqDBValidation(String customer_id){
		logger.info("Fetching SLQ values from DB");
		List<String> db_response=dbCon.dataBaseSelect("SELECT cs.customer_status_name,cd.slq_score,cd.neo_slq_score FROM customers c LEFT OUTER JOIN customer_details cd ON c.customer_id=cd.customer_id LEFT OUTER JOIN `customer_status` cs ON c.customer_status_id=cs.customer_status_id WHERE c.customer_id= "+customer_id);

		List<String> db_NewRepeat = dbCon.dataBaseSelect("SELECT COUNT(*) FROM loan_request WHERE customer_id= "+customer_id);

		//float x     =  Float.valueOf(db_response.get(1));
		custStatus =  db_response.get(0);
		slqScoreDB =  db_response.get(1);
		neoSlqDB     =  db_response.get(2);
		//neoSlqDB1 = Integer.parseInt(neoSlqDB);
		status     =  db_NewRepeat.get(0);
		logger.info("SLQ values from DB are::SLQScore: "+slqScoreDB+" , Status(New/Repeat): "+status+", CustomerStatus: "+custStatus);
		eTest.log(Status.INFO, "SLQ values from DB are::SLQScore: "+slqScoreDB+" , Status(New/Repeat): "+status+", CustomerStatus: "+custStatus);

	}





	public void companyValidation(String customer_id,String employer_name)
	{

		List<String> db_response=dbCon.dataBaseSelect("SELECT c.company_name,cc.company_id FROM `customer_company` cc INNER JOIN company c ON c.company_id=cc.company_id WHERE cc.customer_id="+customer_id);

		if( db_response.get(0).equalsIgnoreCase("OTHER") && db_response.get(1).equals("1"))
		{
			List<String> db_response1=dbCon.dataBaseSelect(" SELECT company_name FROM `customer_old_other_company_name` WHERE customer_id="+customer_id);

			Assert.assertEquals(db_response1.get(0), employer_name.trim());

		}
		else
		{
			Assert.assertEquals(db_response.get(0), employer_name.trim());
		}

	}

	public boolean validateEsign(String customerId) throws InterruptedException, IOException {
		try {
			for(int i=1;i<=3;i++) {

				eSignFlow(customerId);
				Thread.sleep(5000);
				updateEsignInDB(customerId);
			}
			return true;
		}catch(Exception e) {
			capture(Status.INFO,"Check screnshot",eTest);
			eTest.log(Status.FAIL, "Check screnshot");
			return false;
		}
	}

	public void updateEsignInDB(String customerId) {
		dbCon.dataBaseUpdate("UPDATE customer_documents SET is_esigned =1 WHERE document_type_id =4 AND is_esigned =0 AND customer_id = "+customerId+"");
		System.out.println("PAN eSigned updated");
	}

	public void eSignFlow(String customerId) throws InterruptedException, IOException {
		clickonElement(driver, btnGetCashe, "", "");
		waitFor(3);
		clickonElement(driver, btnProfile, "", "");
		if(isElementClickable(driver, tabPhotoPrfs)) {
			clickonElement(driver, tabPhotoPrfs, "", "");
		}
		clickonElement(driver, imgPan, "", "");
		//PAN eSign Mandatory
		//Generating the Random Aadhaar Number
		logger.info("Generated virtual Aadhaar Number"+pro.getProperty("virtualAadhaarId"));
		clickonElement(driver, btnVirtualAadhaar, "", "");
		capture(Status.INFO, "btnVirtualAadhaar", eTest);
		waitFor(5);
		mobileSetElement(driver,txtVirtualId , pro.getProperty("virtualAadhaarId"), "Sucessfully entered Aadhaar Number", "Unable to enter Aadhaar Number");
		if(titleeSign.getText().contains("eSign")){
			capture(Status.INFO, "eSignNSDLScreen", eTest);
			clickonElement(driver, chkboxeSignOTP, "", "");
			capture(Status.INFO, "OTPcheckbox", eTest);
			clickonElement(driver, btnOTPeSign, "", "");
			if(isElementClickable(driver, btnOTPSubmit)) {

				capture(Status.INFO, "btnOTPSubmit", eTest);
			}else {
				eTest.log(Status.ERROR, "Esign Not available");
				capture(Status.INFO, "Esign Not available", eTest);
			}
			clickonElement(driver, btnBack, "", "");

			if(isElementPresent(btnEsignFailAlert, 20)) {
				capture(Status.FAIL, "Esign Failed", eTest);
				Assert.fail();
			}
			if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
				clickonElement(driver, btnEsignBack, "", "");
			}else {
				clickonElement(driver, btnBack, "", "");
			}
			clickonElement(driver, btnBack, "", "");

		}
	} 

}

