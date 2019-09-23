package com.cashe.mobile.pages;

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
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cash.utilities.DataBaseConnection;
//import com.cash.utilities.//DataBaseConnection;
import com.cash.utilities.EC2Batch;
import com.cashe.base.BasePage;
import com.jcraft.jsch.JSchException;


public class MyCashe extends BasePage {

	AppiumDriver driver;
	ExtentTest eTest;
	Hashtable<String, String> dataArray;


	@AndroidFindBy(id="action_my_cashe")
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='My CASHe']")
	public WebElement  btnNavigateMyCashe;

	@AndroidFindBy(id="title")
	@iOSFindBy(xpath="//XCUIElementTypeNavigationBar")
	public WebElement  txtTitle;
	

	@AndroidFindBy(id="popup_positive_button")
	@iOSFindBy(accessibility="OK")
	public WebElement  btnPopupYes;

	//eMandate Flow

	//eMandate Continue button :
	@AndroidFindBy(id="btn_razor_pay_continue")
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='Continue']")
	public WebElement  btnEmandateContinue;

	//eMandate Remind Me Later button :
	@AndroidFindBy(id="btn_razor_pay_remind_later")
	//@iOSFindBy(xpath="")
	public WebElement  eManbtnEmandateRemindMeLater;

	//eMandate Aadhaar arrow:
	@AndroidFindBy(xpath="//android.view.View[@resource-id='emandate-options']/android.view.View[2]")
	@iOSFindBy(accessibility="Aadhaar Virtual ID")
	public WebElement eMantabAadhaararrow;

	//Mobile number validation in Aadhaar Screen:
	@AndroidFindBy(xpath="//android.view.View[@resource-id='user']")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[@name='']/following-sibling::XCUIElementTypeStaticText")
	public WebElement eManMobileNumber;

	//Aadhaar Bank Account Number : 
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='adhr-acc-no']")
	@iOSFindBy(xpath="//XCUIElementTypeTextField[1]")
	public WebElement eManBankAccNumber;

	//Aadhaar IFSC:
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='adhr-acc-ifsc']")
	@iOSFindBy(xpath="//XCUIElementTypeTextField[2]")
	public WebElement eManAadhaarIFSC;

	//Name on Bank Account:
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='adhr-acc-name']")
	@iOSFindBy(xpath="//XCUIElementTypeTextField[3]")
	public WebElement eManNameBankAccount;

	//Aadhaar Number:
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='adhr-acc-aadhaar']")
	@iOSFindBy(xpath="//XCUIElementTypeTextField[4]")
	public WebElement eManAadhaarNumber;

	//Authenticate Button:
	@AndroidFindBy(id="next-button")
	@iOSFindBy(accessibility="PROCEED VERIFY")
	public WebElement eManBtnAuth;

	
	@AndroidFindBy(id="next-button")
	@iOSFindBy(accessibility="NEXT VERIFY")
	public WebElement eManBtnNext;
	
	
	@AndroidFindBy(id="next-button")
	@iOSFindBy(accessibility="I have a VID")
	public WebElement eManChkHaveVID;
	
	//Success Button:
	@AndroidFindBy(xpath="//android.widget.Button[1]")
	@iOSFindBy(accessibility="Success")
	public WebElement eManBtnSuccess;

	//Failure Button
	@AndroidFindBy(xpath="//android.widget.Button[3]")
	@iOSFindBy(accessibility="Failure")
	public WebElement eManBtnFailure;

	//Success OK Button
	@AndroidFindBy(id="razor_pay_success_btn")
	@iOSFindBy(accessibility="Ok")
	public WebElement eManBtnOKSuccess;

	//eMandate Success Message
	@AndroidFindBy(xpath="co.tslc.cashe.android:id/tv_persios_suc")
	@iOSFindBy(xpath="//XCUIElementTypeStaticText[contains(@name,'You have successfully')]")
	public WebElement eManSuccessMessage;

	//Paytm validation 
	@AndroidFindBy(xpath="//android.widget.LinearLayout[7]/android.widget.RelativeLayout")
	public WebElement paytm_amount;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[8]/android.widget.RelativeLayout")
	public WebElement paytm_account_no;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[9]/android.widget.RelativeLayout")
	public WebElement paytm_status;
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='More']")
	public WebElement btnMore;
	
	@iOSFindBy(accessibility="LOGOUT")
	public WebElement btnLogout;
	
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='LOGOUT']")
	public WebElement btnLogoutPopup;
	
	@iOSFindBy(accessibility="ALLOW")
	public WebElement btnAllow;
	
	@iOSFindBy(accessibility="GoogleLogin")
	public WebElement btnGoogle;
	
	@iOSFindBy(xpath="//XCUIElementTypeButton[@name='1']")
	public WebElement  btnOne;
	
	//perfios
			@AndroidFindBy(id="popup_message")
			public WebElement perfios_msg;
			
			@AndroidFindBy(id="btn_perfios_continue")
			public WebElement perfios_continue;
			
			@AndroidFindBy(id="institutionId")
			public WebElement select_bank;
			
			@AndroidFindBy(xpath="//android.widget.CheckedTextView[2]")
			public WebElement perfiosbank;

			@AndroidFindBy(id="userId")
			public WebElement userIdd;
			
			@AndroidFindBy(id="password")
			public WebElement passwordd;
			
			@AndroidFindBy(id="confirmPassword")
			public WebElement confirmPasswordd;
			
			@AndroidFindBy(xpath="//android.view.View[17]/android.widget.Button")
			public WebElement next_button;
			
			@AndroidFindBy(id="perfios_success_btn")
			public WebElement successok;
			
			@AndroidFindBy(id="tv_perfios_max_limit")
			public WebElement trys;
			
			@AndroidFindBy(id="tv_perfios_error")
			public WebElement error;
			
			@AndroidFindBy(id="back")
			public WebElement back;

	public MyCashe(AppiumDriver driver,ExtentTest eTest,Hashtable<String, String> dataArray,DataBaseConnection dbCon) {
		this.driver = driver;
		this.eTest=eTest;
		this.dataArray=dataArray;
		this.dbCon=dbCon;
		setDriver(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);


	}

	public void clickPopupOk() throws FileNotFoundException, InterruptedException{
		if(isElementClickable(driver, btnPopupYes))
			clickonElement(driver, btnPopupYes, "Sucessfully clicked popup Ok button", "Unable to click on popup Ok button");
	}

	public void navigateToMyCashe() throws InterruptedException, IOException{

		if(isElementClickable(driver, btnNavigateMyCashe))
			clickonElement(driver, btnNavigateMyCashe, "Sucessfully clicked on My Cashe Navigate button", "Unable to click on My Cashe Navigate button");
		//Validating title of get cashe screen after navigating to get cashe screen
		if(isElementPresent(txtTitle,15)){
			/*if(txtTitle.getText().equalsIgnoreCase("MY CASHe")){
				logger.info("Get Cashe screen title is matching with expected title and the UI title is :"+txtTitle.getText());
			}else{
				logger.info("Get Cashe screen title is not matching with expected title and the UI title is: "+txtTitle.getText());
			} */                             
		}else{
			logger.info("My cashe screen not launched");
		}

		//capture("MyCashe", LogStatus.PASS, eTest);
	}

	public void loanRepayment(String repaymentType, String custid) throws JSchException, InterruptedException, FileNotFoundException{
		EC2Batch batch =new EC2Batch();
		int repaymentAmount;
		String amountToRepay;
		switch (repaymentType) {

		case "PartialRepay":
			amountToRepay = totalDueAmountDb(custid,dataArray.get("LoanType"));
			System.out.println("loanRepayment :"+amountToRepay);
			repaymentAmount = Math.round((Float.parseFloat(amountToRepay) /2));
			System.out.println("amountToRepay :"+amountToRepay);
			System.out.println("PartialRepay - repaymentAmount :"+repaymentAmount);
			dbRepayment(repaymentAmount,custid);
			batch.ec2Batch("Reconciliation");
			clickPopupOk();
			break;
		case "CompleteRepay":
			amountToRepay = totalDueAmountDb(custid,dataArray.get("LoanType"));
			System.out.println("loanRepayment :"+amountToRepay);
			repaymentAmount = Math.round(Float.parseFloat(amountToRepay));
			dbRepayment(repaymentAmount,custid);
			System.out.println("amountToRepay :"+amountToRepay);
			batch.ec2Batch("Reconciliation");
			clickPopupOk();
			break;
		case "NoRepay":
			System.out.println("No repayment for this loan");
			break;
		default:
			System.out.println("Data error :"+repaymentType);

		}
	}      

	public String totalDueAmountDb(String custid, String loanType){
		//DataBaseConnection dbCon=new //DataBaseConnection();
		List<String> loanDueAmount = new ArrayList<String>();
		if((loanType.equalsIgnoreCase("CASHe 15")) || (loanType.equalsIgnoreCase("CASHe 30"))){
			List<String> loan_req_id = new ArrayList<String>();
			loan_req_id = dbCon.dataBaseSelect("SELECT loan_request_id FROM `loan_request` WHERE customer_id = "+custid+" ORDER BY loan_request_id DESC LIMIT 1");
			System.out.println("Loan request id from db "+loan_req_id);

			List<String> count = new ArrayList<String>();
			count = dbCon.dataBaseSelect("SELECT COUNT(*) FROM loan_request_penalty_15_30 WHERE loan_request_id = "+loan_req_id.get(0));
			System.out.println("Row count in loan_request_penalty_15_30 table  "+count); 

			if(count.get(0).equalsIgnoreCase("0")){
				loanDueAmount = dbCon.dataBaseSelect("SELECT total_due_amount FROM loan_request WHERE loan_request_id = "+loan_req_id.get(0));
				System.out.println("Total due amount from db "+loanDueAmount.get(0));

			}else{
				loanDueAmount = dbCon.dataBaseSelect("SELECT total_due_amount FROM loan_request_penalty_15_30 WHERE loan_request_id = "+loan_req_id.get(0));
				System.out.println("Total due amount from db "+loanDueAmount.get(0));

			}
		}else{
			loanDueAmount = dbCon.dataBaseSelect("SELECT total_due_amount FROM loan_request WHERE customer_id = "+custid+" ORDER BY loan_request_id DESC LIMIT 1");
			System.out.println("Total due amount from db "+loanDueAmount.get(0));
		}

		return loanDueAmount.get(0);

	}

	public void dbRepayment (int repaymentAmount, String custid){
		//DataBaseConnection dbCon=new //DataBaseConnection();

		Random ran_num = new Random();
		int rNum = ran_num.nextInt(9999);
		System.out.println("Random number is "+rNum);        
		String bankRefNo = "20161485834_" +rNum;
		System.out.println("Bank ref number is  "+bankRefNo);

		String cln = "STAGE000" + custid;       

		dbCon.dataBaseUpdate("INSERT INTO `bank_electronic_repayments` (credit_date,sending_bank_ref_number,cashe_virtual_ac_number,"
				+ "amount,created_date,updated_date,updated_by,nbfc_code,nbfc_id)VALUES (NOW(),'"+bankRefNo+"',"
				+ "'"+cln+"',"+repaymentAmount+",NOW(),NOW(),1,'BHANIX',2)");
		System.out.println("Repayment done");


	}

	public void changeCustomerStatus(String custId, String custStatus, String loanType) throws JSchException, InterruptedException{
		String emi=null;
		if(custStatus.contains("#")){
			String split[] = custStatus.split("#");
			custStatus = split[0];
			emi = split[1];
			System.out.println("custStatus :"+custStatus+" emi :"+emi);

		}
		switch (custStatus) {

		case "OnDueDate":
			changeToDueDateToady (loanType,custId,emi);
			break;
		case "OverDue":
			changeToOverDue (loanType,custId,emi);
			break;
		case "Defaulted":
			changeToDefaulted (loanType,custId,emi);
			break;
		case "DefaultedBlock":
			logger.info("Status :"+custStatus);
			break;
		case "AutoExt2":
			changeToAutoExt2 (loanType,custId,emi);
			break;
		case "BeforeDueDate":
			logger.info("Status :"+custStatus);
			break;	
		default:
			logger.info("Data error :"+custStatus);

		}

	}

	public void changeToDueDateToady (String loanType,String custId,String emi){
		//DataBaseConnection dbCon=new //DataBaseConnection();
		String dueDate ="NOW()";
		String createdDate;
		switch (loanType) {
		case "CASHe 15":
			createdDate = "NOW() - INTERVAL 15 DAY";
			dbCon.dataBaseUpdate("UPDATE loan_request SET created_date = "+createdDate+"  ,"
					+ "loan_transferred_date = "+createdDate+" WHERE customer_id "
					+ "= "+custId+" ORDER BY loan_request_id DESC LIMIT 1");
			System.out.println("Changed customer status");

			changeDateInLoanRequestDb(custId,dueDate);
			break;
		case "CASHe 30":
			createdDate = "NOW() - INTERVAL 30 DAY";
			dbCon.dataBaseUpdate("UPDATE loan_request SET created_date = "+createdDate+"  ,"
					+ "loan_transferred_date = "+createdDate+" WHERE customer_id "
					+ "= "+custId+" ORDER BY loan_request_id DESC LIMIT 1");
			changeDateInLoanRequestDb(custId,dueDate);
			break;
		case "CASHe 90":
			changeDateInLoaninstallmentDb(custId,dueDate,emi);
			if(emi.equalsIgnoreCase("3")){
				changeDateInLoanRequestDb(custId,dueDate);
			}
			break;
		case "CASHe 180":
			changeDateInLoaninstallmentDb(custId,dueDate,emi);
			if(emi.equalsIgnoreCase("6")){
				changeDateInLoanRequestDb(custId,dueDate);
			}
			break;

		default:
			logger.info("Data error :"+loanType);

		}
	}

	public void changeToOverDue (String loanType,String custId,String emi) throws JSchException, InterruptedException{
		EC2Batch batch =new EC2Batch();
		changeToDueDateToady (loanType,custId,emi);
		String dueDate= "NOW() - INTERVAL 1 DAY";
		switch (loanType) {
		case "CASHe 15":
			changeDateInLoanRequestDb(custId,dueDate);
			batch.ec2Batch("15And30Penalty");
			break;
		case "CASHe 30":
			changeDateInLoanRequestDb(custId,dueDate);
			break;
		case "CASHe 90":
			changeDateInLoaninstallmentDb(custId,dueDate,emi);
			if(emi.equalsIgnoreCase("3")){
				changeDateInLoanRequestDb(custId,dueDate);
			}
			break;
		case "CASHe 180":
			changeDateInLoaninstallmentDb(custId,dueDate,emi);
			if(emi.equalsIgnoreCase("6")){
				changeDateInLoanRequestDb(custId,dueDate);
			}
			break;

		default:
			logger.info("Data error :"+loanType);

		}
	}

	public void changeToDefaulted (String loanType,String custId,String emi) throws JSchException, InterruptedException{
		EC2Batch batch =new EC2Batch();
		String dueDate;
		changeToOverDue (loanType,custId,emi);
		switch (loanType) {
		case "CASHe 15":
			dueDate= "NOW() - INTERVAL 23 DAY";
			changeDateInLoanRequestDb(custId,dueDate);
			changeDateInLoanRequest15_30(custId);
			batch.ec2Batch("15And30Penalty");
			break;
		case "CASHe 30":
			dueDate= "NOW() - INTERVAL 8 DAY";
			changeDateInLoanRequestDb(custId,dueDate);
			changeDateInLoanRequest15_30(custId);
			batch.ec2Batch("15And30Penalty");
			break;
		case "CASHe 90":
			dueDate= "NOW() - INTERVAL 8 DAY";
			changeDateInLoaninstallmentDb(custId,dueDate,emi);
			if(emi.equalsIgnoreCase("3")){
				changeDateInLoanRequestDb(custId,dueDate);
			}
			batch.ec2Batch("PenaltyCalculation");
			break;
		case "CASHe 180":
			dueDate= "NOW() - INTERVAL 8 DAY";
			changeDateInLoaninstallmentDb(custId,dueDate,emi);
			if(emi.equalsIgnoreCase("6")){
				changeDateInLoanRequestDb(custId,dueDate);
			}
			batch.ec2Batch("PenaltyCalculation");
			break;

		default:
			logger.info("Data error :"+loanType);

		}

	}

	public void changeToDefaultedBlock (String loanType,String custId){

	}

	public void changeToAutoExt2 (String loanType,String custId,String emi) throws JSchException, InterruptedException{
		EC2Batch batch =new EC2Batch();
		String dueDate= "NOW() - INTERVAL 16 DAY";
		changeToOverDue (loanType,custId,emi);
		changeDateInLoanRequestDb(custId, dueDate);
		changeDateInLoanRequest15_30(custId);
		batch.ec2Batch("15And30Penalty");

	}

	public void changeDateInLoanRequestDb(String custId, String dueDate){
		//DataBaseConnection dbCon=new //DataBaseConnection();
		dbCon.dataBaseUpdate("UPDATE loan_request SET due_date= "+dueDate+" WHERE customer_id = "+custId+""
				+ " ORDER BY loan_request_id DESC LIMIT 1");
		System.out.println("Changed due date in Loan Request table");

	}

	public void changeDateInLoaninstallmentDb(String custid, String dueDate, String emi){
		//DataBaseConnection dbCon=new //DataBaseConnection();

		List<String> loan_req_id = new ArrayList<String>();
		loan_req_id = dbCon.dataBaseSelect("SELECT loan_request_id FROM `loan_request` WHERE customer_id = "+custid+" ORDER BY loan_request_id DESC LIMIT 1");
		System.out.println("Loan request id from db "+loan_req_id);

		dbCon.dataBaseUpdate("UPDATE loan_installments SET installment_due_date = "+dueDate+","
				+ "extension_due_date = "+dueDate+" + INTERVAL 7 DAY WHERE loan_request_id = "+loan_req_id.get(0)+""
				+ " AND installment_number ="+emi+"");
		System.out.println("Changed installment_due_date and extension_due_date in Loan installment table");


	}

	public void changeDateInLoanRequest15_30(String custid){
		//DataBaseConnection dbCon=new //DataBaseConnection();

		List<String> loan_req_id = new ArrayList<String>();
		loan_req_id = dbCon.dataBaseSelect("SELECT loan_request_id FROM `loan_request` WHERE customer_id = "+custid+" ORDER BY loan_request_id DESC LIMIT 1");
		System.out.println("Loan request id from db "+loan_req_id);

		dbCon.dataBaseUpdate("UPDATE loan_request_penalty_15_30 SET next_penalty_calc_date = NOW() - INTERVAL 2 DAY WHERE loan_request_id ="+loan_req_id.get(0)+"");
		System.out.println("Changed next penalty date in loan_request_penalty_15_30 table ");

	}

	public void eMandateValidation(String custid){
		String mNumApp = eManMobileNumber.getText();
		String bNumApp = eManBankAccNumber.getText();
		String ifscApp = eManAadhaarIFSC.getText();
		String bNamApp = eManNameBankAccount.getText();
		//String aadhaarNumApp = eManAadhaarNumber.getText();
		//DataBaseConnection dbCon=new //DataBaseConnection();
		List<String> eManDBvalues= dbCon.dataBaseSelect("SELECT cd.mobile_no,bd.account_number,bd.ifsc_code,c.`customer_name`,cd.aadhar FROM customer_details cd,customers c,`bank_account_detail` bd WHERE cd.`customer_id`="+custid+" AND cd.`customer_id`=c.`customer_id` AND c.`customer_id`=bd.`customer_id`");
		if(mNumApp.contains(eManDBvalues.get(0))){
			eTest.log(Status.INFO,"Succesfully Validated Mobile Numbers "+"Mobile Num from APP is:"+mNumApp+" , and Mobile Num from DB is :"+eManDBvalues.get(0));
		} 
		if(bNumApp.contains(eManDBvalues.get(1))){
			eTest.log(Status.INFO, "Successfully Validated Bank Account Number "+"Bank Account Number from APP is:"+bNumApp+" , and Bank Account from DB is :"+eManDBvalues.get(1));
		}
		if(ifscApp.contains(eManDBvalues.get(2))){
			eTest.log(Status.INFO, "Successfully Validated IFSC Code "+"IFSC Code from APP is:"+ifscApp+" ,  and IFSC Code from DB is :"+eManDBvalues.get(2));
		}
		if(bNamApp.contains(eManDBvalues.get(3))){
			eTest.log(Status.INFO, "Succesfully Validated Name on Bank Account "+"Customer Name on Bank Account Number from APP is:"+bNamApp+" , and Customer Name on Bank Account Number from DB is :"+eManDBvalues.get(3));
		}
		/*if(aadhaarNumApp.contains(eManDBvalues.get(4))){
			eTest.log(Status.INFO, "Succesfully Validated Aadhaar Number "+"Aadhaar Number from APP is:"+aadhaarNumApp+" , and Aadhaar Number from DB is :"+eManDBvalues.get(4));
		}*/
	}
	public void googleLoginAgain() throws InterruptedException, FileNotFoundException{
		//waitUntilElementClickable(driver, element)
		
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
	
			waitFor(3);
			((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Continue']")).click();
			waitFor(10);		
			((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[contains(@name,'@gmail.com')]")).click();
			waitFor(3);
			swipeTo();
			btnAllow.click();
			//((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Continue']")).click();
			waitFor(3);
			((IOSDriver)getDriver()).findElement(By.xpath("//XCUIElementTypeButton[@name='Passcode']")).click();
			waitFor(3);
		
	}
	public void doeMandate(String custid) throws InterruptedException, IOException{
		try{
			if(!dataArray.get("Platform").equalsIgnoreCase("android")) {
				clickonElement(driver, btnMore, "", "");
				swipeTo();
				clickonElement(driver, btnLogout, "", "");
				clickonElement(driver, btnLogoutPopup, "", "");
				googleLoginAgain();
				waitFor(3);
				for(int i=1;i<5;i++){
					clickonElement(driver, btnOne, "", "");
				}
			}
			
			clickonElement(driver, btnPopupYes, "", "");
			//Title
			//if(txtTitle.getText().toLowerCase().contains("emandate")){
				capture(Status.INFO, "eMandateScreen1", eTest);
			//} 
			clickonElement(driver, btnEmandateContinue, "", "");
			//Title
			//if(txtTitle.getText().toLowerCase().contains("emandate")){
				capture(Status.INFO, "eMandateScreen1", eTest);
			//}
			clickonElement(driver, eMantabAadhaararrow, "", "");
			//Title
			//if(txtTitle.getText().toLowerCase().contains("emandate")){
				capture(Status.INFO, "eMandateScreen1", eTest);
			//}
			eMandateValidation(custid);
			
			clickonElement(driver, eManBtnNext, "", "");
			
			clickonElement(driver, eManChkHaveVID, "", "");
			capture(Status.INFO, "eManChkHaveVID", eTest);
			clickonElement(driver, eManBtnAuth, "", "");
			//Title
			//if(txtTitle.getText().toLowerCase().contains("emandate")){
				capture(Status.INFO, "eMandateScreen1", eTest);
			//}
			clickonElement(driver, eManBtnSuccess, "", "");
			//Title
			//if(txtTitle.getText().toLowerCase().contains("emandate")){
				capture(Status.INFO, "eMandateScreen1", eTest);
		//	}

			//Validating the eMandate Success message on the screen
			if(isElementPresent(eManSuccessMessage,15)){
				if(eManSuccessMessage.getText().contains("eMandate")){
					logger.info("eMandate text is matching with expected text and the UI text is :"+eManSuccessMessage.getText());
				}else{
					logger.info("eMandate text is not matching with expected text and the UI text is: "+eManSuccessMessage.getText());
				}                              
			}else{
				logger.info("eMandate screen not displayed");
			}
			capture(Status.INFO, "eMandateScreen1", eTest);
			clickonElement(driver, eManBtnOKSuccess, "", "");

			//DataBaseConnection dbCon=new //DataBaseConnection();
			List<String> eMantag = new ArrayList<String>();
			eMantag = dbCon.dataBaseSelect("SELECT ct.tag_id,t.tag_name FROM customer_tags ct INNER JOIN tags t ON t.tag_id=ct.tag_id WHERE ct.customer_id="+custid+"");
			System.out.println("Customer Tag from DB : "+eMantag.get(eMantag.size()-2));
			eTest.log(Status.INFO, "Customer Tag from DB : "+eMantag.get(eMantag.size()-2)+" ,eMandate status is:"+eMantag.get(eMantag.size()-1));
		}catch(Exception e){
			e.printStackTrace();
		}  
	}

	//Penalty Calculation for Defaulted Customers
	public void penaltyCalculation(String custid, String loanType, String RepayDate, String custStatus) throws JSchException, InterruptedException{

		EC2Batch batch =new EC2Batch();


		String penaltyCal = loanType;
		String varfromProp;
		String emi=null;

		switch(penaltyCal){

		case "CASHe 15":

			if(RepayDate=="OverDue")
			{
				changeToOverDue ( loanType, custid, emi);
				batch.ec2Batch("DefaultCustomer");
				varfromProp="penalty_autoext1_cashe15";
				LoanDBquery( loanType, custid, varfromProp);


			}
			else if(RepayDate=="AutoExt2")
			{
				changeToAutoExt2 ( loanType, custid, emi);
				batch.ec2Batch("DefaultCustomer");
				varfromProp="penalty_autoext2_cashe15";
				LoanDBquery( loanType, custid, varfromProp);


			}
			else if(RepayDate=="Defaulted")
			{
				changeToDefaulted ( loanType, custid, emi);                
				batch.ec2Batch("15And30Penalty");
				batch.ec2Batch("DefaultCustomer");

				varfromProp="penalty_defaulted_cashe15";
				LoanDBquery( loanType, custid, varfromProp);

			}

			break;

		case "CASHe 30":

			changeToDefaulted ( loanType, custid, emi);                
			batch.ec2Batch("DefaultCustomer");
			varfromProp="penalty_defaulted";
			LoanDBquery( loanType, custid, varfromProp);

			break;

		case "CASHe 90":

			if(custStatus.contains("#")){
				String split[] = custStatus.split("#");
				custStatus = split[0];
				emi = split[1];
				System.out.println("custStatus :"+custStatus+" emi :"+emi);
			}
			changeToDefaulted ( loanType, custid, emi);
			batch.ec2Batch("DefaultCustomer");
			varfromProp="penalty_defaulted";
			LoanDBquery( loanType, custid, varfromProp);



		case "CASHe 180":

			if(custStatus.contains("#")){
				String split[] = custStatus.split("#");
				custStatus = split[0];
				emi = split[1];
				System.out.println("custStatus :"+custStatus+" emi :"+emi);

			}
			changeToDefaulted ( loanType, custid, emi);
			batch.ec2Batch("DefaultCustomer");
			varfromProp="penalty_defaulted";
			LoanDBquery( loanType, custid, varfromProp);

		}
	}


	public void LoanDBquery(String loanType,String custid,String varfromProp)
	{

		int  total_due_amt;
		List<String> loan_req_id1,principalDueAmountDB1, loan_installment1;

		switch(loanType){

		case "CASHe 15":

			loan_req_id1 = dbCon.dataBaseSelect("SELECT loan_request_id FROM `loan_request` WHERE customer_id = "+custid+" ORDER BY loan_request_id DESC LIMIT 1");
			System.out.println("Loan request id from db : "+loan_req_id1.get(0));
			principalDueAmountDB1 = dbCon.dataBaseSelect("SELECT principal_due FROM loan_request_penalty_15_30 WHERE loan_request_id = "+loan_req_id1.get(0));
			System.out.println("Prinicpal Amount from db : "+principalDueAmountDB1.get(0));
			total_due_amt = (Integer.parseInt(principalDueAmountDB1.get(0))*Integer.parseInt(pro.getProperty(varfromProp)))/100;
			break;



		case "CASHe 30":
			loan_req_id1 = dbCon.dataBaseSelect("SELECT loan_request_id FROM `loan_request` WHERE customer_id = "+custid+" ORDER BY loan_request_id DESC LIMIT 1");
			System.out.println("Loan request id from db : "+loan_req_id1.get(0));
			principalDueAmountDB1 = dbCon.dataBaseSelect("SELECT principal_due FROM loan_request_penalty_15_30 WHERE loan_request_id = "+loan_req_id1.get(0));
			System.out.println("Prinicpal Amount from db : "+principalDueAmountDB1.get(0));
			total_due_amt = (Integer.parseInt(principalDueAmountDB1.get(0))*Integer.parseInt(pro.getProperty(varfromProp)))/100;
			break;

		case "CASHe 90":   

			loan_req_id1 = dbCon.dataBaseSelect("SELECT loan_request_id FROM `loan_request` WHERE customer_id = "+custid+" ORDER BY loan_request_id DESC LIMIT 1");
			System.out.println("Loan request id from db : "+loan_req_id1.get(0));
			loan_installment1=dbCon.dataBaseSelect("SELECT `installment_amount` , `penalty_accumulated`, `due_amount` FROM `loan_installments` WHERE `installment_number` = 1 AND `loan_request_id` ="+loan_req_id1.get(0));
			System.out.println("EMI Loan Details from Loan Installments - installment_amount in DB : "+loan_installment1.get(0));
			System.out.println("EMI Loan Details from Loan Installments - penalty_accumulated in DB : "+loan_installment1.get(1));
			System.out.println("EMI Loan Details from Loan Installments - due_amount in DB : "+loan_installment1.get(2));
			total_due_amt = (Integer.parseInt(loan_installment1.get(2))*Integer.parseInt(pro.getProperty(varfromProp)))/100;
			break;


		case "CASHe 180":  

			loan_req_id1 = dbCon.dataBaseSelect("SELECT loan_request_id FROM `loan_request` WHERE customer_id = "+custid+" ORDER BY loan_request_id DESC LIMIT 1");
			System.out.println("Loan request id from db : "+loan_req_id1.get(0));
			loan_installment1=dbCon.dataBaseSelect("SELECT `installment_amount` , `penalty_accumulated`, `due_amount` FROM `loan_installments` WHERE `installment_number` = 1 AND `loan_request_id` ="+loan_req_id1.get(0));
			System.out.println("EMI Loan Details from Loan Installments - installment_amount in DB : "+loan_installment1.get(0));
			System.out.println("EMI Loan Details from Loan Installments - penalty_accumulated in DB : "+loan_installment1.get(1));
			System.out.println("EMI Loan Details from Loan Installments - due_amount in DB : "+loan_installment1.get(2));
			total_due_amt = (Integer.parseInt(loan_installment1.get(2))*Integer.parseInt(pro.getProperty(varfromProp)))/100;
			break;

		}

	}
	public void paytmValidations(String customer_id)
	{
		List<String> db_response=dbCon.dataBaseSelect("SELECT lr.loan_request_id,lrpt.paytm_amount,lrpt.paytm_account_no,ls.loan_status_name,pts.paytm_txn_status_name "
				+"FROM `loan_request` lr "
				+"INNER JOIN `loan_request_paytm_txn` lrpt ON lr.loan_request_id=lrpt.loan_request_id "
				+"INNER JOIN `loan_status` ls ON ls.loan_status_id=lr.loan_status_id "
				+"INNER JOIN `paytm_txn_status` pts ON pts.paytm_txn_status_id=lrpt.paytm_txn_status_id "
				+"WHERE customer_id="+customer_id +"ORDER BY lr.loan_request_id DESC LIMIT 1");

		//validating paytm account no
		Assert.assertEquals(db_response.get(1), paytm_account_no.getText());

		//validating paytm amount
		Assert.assertEquals(db_response.get(0), paytm_amount.getText().split(".")[1].trim());	

		//validating paytm status
		if(!(db_response.get(2).contains(paytm_status.getText())))
			Assert.fail("Paytm status is NOT equal in DB and UI");
		else
			System.out.println("Paytm status is equal in DB and UI");

	}
	public void perfios_addtag(String customer_id,String loanAmt) throws FileNotFoundException, InterruptedException
	{
		
		List<String> customer_status=dbCon.dataBaseSelect("SELECT customer_status_id FROM customers WHERE  customer_id="+customer_id);

		if(customer_status.get(0).contains("2") || customer_status.get(0).contains("3") || customer_status.get(0).contains("4"))
		{
			if(loanAmt.contains("K"))
			{
			int loanAmt1=Integer.parseInt(loanAmt.split("K")[0])*1000;
			loanAmt=String.valueOf(loanAmt1);
			}
			
			List<String> db_response1=dbCon.dataBaseSelect("SELECT VALUE FROM `cashe_constants` WHERE NAME='min_loan_amount_perfios_report'");
			if(Integer.valueOf(loanAmt) >= Integer.valueOf(db_response1.get(0)))
			{
					List<String> db_tagcount=dbCon.dataBaseSelect("SELECT COUNT(*) FROM  customer_tags WHERE customer_id="+customer_id);
					if(db_tagcount.get(0).equals("0"))
					{
						//Inserting REQUIRED_PERFIOS_REPORT tag
						dbCon.dataBaseUpdate("INSERT INTO customer_tags (customer_id, tag_id) VALUES ("+customer_id+",8);");
				
					}
					else
					{
						List<String> db_response=dbCon.dataBaseSelect("SELECT tag_id FROM  customer_tags WHERE customer_id="+customer_id);
						if(db_response.get(0).contains("8"))
						{
							logger.info("Customer is having REQUIRED_PERFIOS_REPORT tag");
						}
						else
						{
							//Inserting REQUIRED_PERFIOS_REPORT tag
							dbCon.dataBaseUpdate("INSERT INTO customer_tags (customer_id, tag_id) VALUES ("+customer_id+",8);");
						}
					}

					//Develope Code for logout
					
					perfios(customer_id);
			}
		}
		else
		{
			logger.info("Customer is NOT in DR so REQUIRED_PERFIOS_REPORT is not triggered");

		}
	}
	
	public void perfios(String customer_id) throws FileNotFoundException, InterruptedException
	{
		
		//Validating perfios message
		asserEqual(perfios_msg,"Please verify the salary account of your bank with CASHe for us to analyse your bank statement.");
				
		
		//Click on OK button to complete perfios registration  
		clickonElement(driver, btnPopupYes, "Successfully clicked on OK button", "Unable to clicked on OK Button");
		
		//Validating Number of try message 
		waitFor(3);
		asserEqual(trys,"You have 5 try(s) left for today.");

		//Click on continue button for first time
		clickonElement(driver, perfios_continue, "Successfully clicked on continue button", "Unable to clicked on continue Button");
		waitFor(3);
		
		//Click on back button
		clickonElement(driver, back, "Successfully clicked on back button", "Unable to clicked on back Button");
	
		//Validating Number of try message 
		asserEqual(trys,"You have 4 try(s) left for today.");

		
		//validating the error message
		asserEqual(error,"Error. We could not establish a connection. Please retry.");


		//Click on continue button
		clickonElement(driver, perfios_continue, "Successfully clicked on continue button", "Unable to clicked on continue Button");
		waitFor(3);

		//Selecting the bank 
		clickonElement(driver, select_bank, "Successfully clicked on Bank drop_down", "Unable to clicked on Bank drop_down");

		clickonElement(driver, perfiosbank, "Successfully clicked on acme_bank ", "Unable to clicked on acme_bank");

		//Entering user_id
		mobileSetElement(driver, userIdd, "perfios" , "User_id entered successfully", "Unable to enter  User_id");
		
		//Entering password
		mobileSetElement(driver, passwordd, "perfios", "Password entered successfully", "Unable to enter password");

		//Entering confirm Password
		mobileSetElement(driver, confirmPasswordd, "perfios", "Password confirmed successfully", "Unable to enter confirmed password");

		swipeTo();
		//Click on Next button
		clickonElement(driver, next_button, "Successfully clicked on next button ", "Unable to click on next_button");
		
		waitFor(3);
		
		//Perfios registration is done successfully
		clickonElement(driver, successok, "Successfully clicked on success button ", "Unable to click on success button");
		
		//waiting for report
		waitFor(3);
		//Check for customer_tag 
		List<String> db_response=dbCon.dataBaseSelect("SELECT tag_id FROM customer_tags WHERE customer_id="+customer_id);
		
		if(db_response.get(0).equals("9"))
		{
			List<String> db_response1=dbCon.dataBaseSelect("SELECT report_s3_path FROM `customer_perfios_report`  WHERE customer_id="+customer_id);
			
			boolean a=db_response1.get(0).contains("perfios_report");
			Assert.assertTrue(a);
			logger.info("perfios_report obtained successfully");
		}

	
	}
}


