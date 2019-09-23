package com.cashe.admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class AdminFlow extends CommonFunctions{
	AdminFlowPageFactory CustVerification;
	Wait<WebDriver> wait;
	String company_name="Infosys";
	/*@DataProvider(name = "val") public Object[][] createData(){
		  Object[][] testData = getTestData("src\\test\\resources\\TestData.xls",
		   "verify","status"); return testData; }*/

	//@Test/*(dataProvider="val")*/
	public void loanStatusInAdmin(String customer_id,String loanStatus)/*(String tabType,String Approved)*/ throws InterruptedException{
		//Login to Admin
		try{
			setup();
			System.out.println("Thread is is :"+Thread.currentThread().getName());

			System.out.println("Driver is :"+driver);
			driver.findElement(By.name("email")).clear();
			driver.findElement(By.name("email")).sendKeys("vijaysupervisor@cashe.com");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("Cashe@123");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			wait = new WebDriverWait(driver, 30);

			//assignment of object  to CustomerVerificationPage the class CustomerVerificationPage
			CustVerification = PageFactory.initElements(driver, AdminFlowPageFactory.class);


			Thread.sleep(3000);
			NavigateCusValidationScreen("Search",customer_id);
			Thread.sleep(4000);

			switch (loanStatus){

			case "Verified":
				loanVerified();
				System.out.println("Loan Verified Sucessfully");
				break;

			case "Failed":
				selectLoanStatus(loanStatus);
				break;

			case "Temporary Block":
				selectLoanStatus(loanStatus);
				break;

			case "Permanent Block":
				selectLoanStatus(loanStatus);
				break;

			}
		}catch(Exception e){

			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		driver.quit();
	}

	public  void loanVerified() throws InterruptedException{

		//Approved////
		Thread.sleep(5000);
		if (!CustVerification.PrsnlDtlsChkbox.isSelected()){

			CustVerification.PrsnlDtlsChkbox.click();
			Reporter.log("PersonalDetailsn checkbox is Checked",true);
			Thread.sleep(1000); 
		}

		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.PanDetails));
		CustVerification.PanDetails.click(); 
		//Enter cibil number
		Thread.sleep(5000); 
		wait.until(ExpectedConditions.visibilityOf(CustVerification.custCibilScore));
		CustVerification.custCibilScore.clear();
		CustVerification.custCibilScore.sendKeys("700");
		Reporter.log("cibil is entered",true);


		Thread.sleep(2000);
		if (!CustVerification.PanDtlsChkbox.isSelected())
		{
			CustVerification.PanDtlsChkbox.click();
			Reporter.log("PAN Details Category Checked",true);
			Thread.sleep(1000);
		}

		wait.until(ExpectedConditions.visibilityOf(CustVerification.CompanyDetails));
		CustVerification.CompanyDetails.click(); 

		/*
		Thread.sleep(5000); 
		wait.until(ExpectedConditions.visibilityOf(CustVerification.allcmpnyrdio));
		CustVerification.allcmpnyrdio.click();
		Reporter.log("RADIO BUTTON Checked",true);*/
		Thread.sleep(4000);


		//Enter Company Search
		/*//wait.until(ExpectedConditions.visibilityOf(CustVerification.allcmpnytextbox));
		//CustVerification.allcmpnytextbox.clear();
		//CustVerification.allcmpnytextbox.click();
		Thread.sleep(2000);
		String newstr=company_name.length() < 2 ? company_name : company_name.substring(0, 3);
		Reporter.log("sub string is ::"+newstr,true);
		CustVerification.allcmpnytextbox.sendKeys(newstr);
		Thread.sleep(3000);

		Actions builder = new Actions(driver);
		builder.moveToElement(CustVerification.allcmpnytextbox.findElement(By.xpath("//*[@id='company_dropdown']"))).click().build().perform();
		Reporter.log(" selected company is"+builder,true);*/



		wait.until(ExpectedConditions.visibilityOf(CustVerification.salary_creditDate_edit));
		Thread.sleep(4000);
		CustVerification.salary_creditDate_edit.click();

		List<WebElement> salarylist = driver.findElements(By.xpath("//select[@ng-show='view.dayEditorEnabled']/option"));
		List<String> listofsalary = new ArrayList<String>();
		for (WebElement e : salarylist) {
			listofsalary.add(e.getText().replaceAll(",$", ""));
		}
		listofsalary.removeIf(item -> item == null || "".equals(item));

		Reporter.log("list from UI:::"+listofsalary,true);
		Random rand11 = new Random();

		int  n1 = rand11.nextInt(listofsalary.size());
		Reporter.log("n1::::" +n1,true);


		Reporter.log("Selecting salary day randomly from drop down");

		wait.until(ExpectedConditions.visibilityOf(CustVerification.salary_dropdown));
		Select select_approval2 = new Select(driver.findElement(By.xpath("//select[@ng-show='view.dayEditorEnabled']")));
		String salary_date=listofsalary.get(n1);

		select_approval2.selectByVisibleText(salary_date);

		Reporter.log("Updated Salary date: " +salary_date,true);
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.salaryDate_tick));
		CustVerification.salaryDate_tick.click();
		Thread.sleep(3000);

		/*wait.until(ExpectedConditions.visibilityOf(CustVerification.salaryDate_success));
		String Actual_message_2  =CustVerification.salaryDate_success.getText();
		String Expeted_message_2="Successfully Updated Salary Day.";
		if(Actual_message_2.contains(Expeted_message_2))
		{
		Reporter.log("Success message is displayed correctly ::"+Actual_message_2,true);
			wait.until(ExpectedConditions.visibilityOf(CustVerification.salaryDate_success_cross));*/	
		CustVerification.salaryDate_success_cross.click();
		Reporter.log("cross is clicked",true);

		/*	wait.until(ExpectedConditions.visibilityOf(CustVerification.salaryDate_checkbox));
		Thread.sleep(3000);
		CustVerification.salaryDate_checkbox.click();*/


		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.CmpnyDtlsChkbox));
		if(!CustVerification.CmpnyDtlsChkbox.isSelected()){
			CustVerification.CmpnyDtlsChkbox.click();
			CustVerification.CmpnyDtlsChkbox.click();
			Reporter.log(" Company details check box is Checked",true);
			Thread.sleep(1000);
		}

		Thread.sleep(5000);


		wait.until(ExpectedConditions.visibilityOf(CustVerification.AddressDetails));
		wait.until(ExpectedConditions.visibilityOf(CustVerification.AddressDetails));
		CustVerification.AddressDetails.click();

		Thread.sleep(2000);
		if (!CustVerification.AddrDtlsChkbox.isSelected()){
			CustVerification.AddrDtlsChkbox.click();
			Reporter.log("address Details Category checkbox is Checked",true);
			Thread.sleep(1000);
		}

		//Click the checkbox for Bank details Details Category
		wait.until(ExpectedConditions.visibilityOf(CustVerification.BankDetails));
		CustVerification.BankDetails.click();
		Thread.sleep(2000);
		if (!CustVerification.BnkDtlsChkbox.isSelected()){
			CustVerification.BnkDtlsChkbox.click();
			Reporter.log("Bank Details Category Checked",true);
			Thread.sleep(1000);
		}

		//Click the checkbox for Other Details Category
		wait.until(ExpectedConditions.visibilityOf(CustVerification.OtherDetails));
		CustVerification.OtherDetails.click();
		Thread.sleep(2000);
		if (!CustVerification.OthrDtlsChkbox.isSelected()){
			CustVerification.OthrDtlsChkbox.click();
			Reporter.log("Other Details Category Checked",true);
			Thread.sleep(1000);
		}

		//verify profile pic
		Thread.sleep(9000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.custProfileImgChkbox));
		if(!CustVerification.custProfileImgChkbox.isSelected()){
			CustVerification.custProfileImgChkbox.click();
			Reporter.log("Profile Image checked",true);
		}

		//verify PANCard pic
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.custPanImgChkbox));
		if(!CustVerification.custPanImgChkbox.isSelected()){
			CustVerification.custPanImgChkbox.click();
			Reporter.log("PAN image Checked",true);
		}

		//verify address pic
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.custAddressImgChkbox));
		if(!CustVerification.custAddressImgChkbox.isSelected()){
			CustVerification.custAddressImgChkbox.click();
			Reporter.log("Address Image Checked",true);
		}

		//verify payslip pic
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.custPayslipImgChkbox));
		if(!CustVerification.custPayslipImgChkbox.isSelected()){
			CustVerification.custPayslipImgChkbox.click();
			Reporter.log("Payslip Image Checked",true);
			Thread.sleep(1000);
		}

		//verify Bank Statement pic
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.custBankStmtImgChkbox));
		if(!CustVerification.custBankStmtImgChkbox.isSelected()){
			CustVerification.custBankStmtImgChkbox.click();
			Reporter.log("Bank Stmt Image Checked",true);
			Thread.sleep(1000);
		}


		// Click Status for Verified
		wait.until(ExpectedConditions.visibilityOf(CustVerification.Status));
		Select select_statusss = new Select(CustVerification.Status);
		Thread.sleep(3000);
		select_statusss.selectByIndex(1);
		Reporter.log("Status Selected",true);
		Thread.sleep(1000);

		// Click Status for approvalby 
		Thread.sleep(4000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.approvalby));
		CustVerification.approvalby.click();
		Thread.sleep(4000);
		//wait.until(ExpectedConditions.visibilityOf(CustVerification.approvalby));
		Select select_Status = new Select(CustVerification.approvalby);
		select_Status.selectByVisibleText("Normal");
		Reporter.log("approved by:: Normal",true);
		Thread.sleep(1000);

		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.OtherDetails));
		CustVerification.OtherDetails.click();
		Reporter.log("***********",true);

		//Verify whether button is enabled or not
		if(CustVerification.btnSubmit.isEnabled())
		{
			Reporter.log("Submit Button is enabled ",true);
			wait.until(ExpectedConditions.visibilityOf(CustVerification.btnSubmit));
			CustVerification.btnSubmit.click();
			wait.until(ExpectedConditions.visibilityOf(CustVerification.submitbtnsuccessmsg));
			String Actual_message =CustVerification.submitbtnsuccessmsg.getText();
			String Expeted_message="Successfully Inserted/Updated Customer Details!";
			if(Actual_message.contains(Expeted_message))
			{
				Reporter.log("submit message is displayed ::"+Expeted_message,true);
				wait.until(ExpectedConditions.visibilityOf(CustVerification.submitbtnsuccessmsgok));
				CustVerification.submitbtnsuccessmsgok.click();
				Reporter.log("ok is clicked in success msg ",true);
			}

		}
		else{Reporter.log("Submit Button is disabled ",true);}

	}

	public  void selectLoanStatus(String loanStatus) throws InterruptedException{

		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOf(CustVerification.PanDetails));
		CustVerification.PanDetails.click(); 

		//Enter cibil number
		Thread.sleep(5000); 
		wait.until(ExpectedConditions.visibilityOf(CustVerification.custCibilScore));
		CustVerification.custCibilScore.clear();
		CustVerification.custCibilScore.sendKeys("700");
		Reporter.log("cibil is entered",true);

		// Click Status for faild
		wait.until(ExpectedConditions.visibilityOf(CustVerification.Status));
		Select select_statusss = new Select(CustVerification.Status);
		Thread.sleep(3000);
		//if("Failed".equalsIgnoreCase(loanStatus)){
		select_statusss.selectByVisibleText(loanStatus);
		Reporter.log("Status Selected",true);
		Thread.sleep(1000);

		if("Failed".equalsIgnoreCase(loanStatus)){
			wait.until(ExpectedConditions.visibilityOf(CustVerification.faildcategory));
			Select faildcat = new Select(CustVerification.faildcategory);
			List<WebElement> faildcatlist = getDropdownOptionList(CustVerification.faildcategory);
			int catlistsize = faildcatlist.size();


			faildcat.selectByIndex(catlistsize-1);
			Thread.sleep(2000);
			String faildcatdata=faildcatlist.get(catlistsize-1).getText();
			Reporter.log("category for faild is     :"+faildcatdata,true);
		}

		wait.until(ExpectedConditions.visibilityOf(CustVerification.sub_category));
		Select faildsubcat = new Select(CustVerification.sub_category);
		List<WebElement> faildsubcatlist = getDropdownOptionList(CustVerification.sub_category);
		int subcatlistsize = faildsubcatlist.size();

		faildsubcat.selectByIndex(subcatlistsize-1);
		Thread.sleep(2000);
		String faildsubcatdata=faildsubcatlist.get(subcatlistsize-1).getText();
		Reporter.log("sub-category for faild is          :"+faildsubcatdata,true);


		wait.until(ExpectedConditions.visibilityOf(CustVerification.reason));
		Select faildreasone = new Select(CustVerification.reason);
		List<WebElement> faildreasonlist = getDropdownOptionList(CustVerification.reason);
		int reasonsize = faildreasonlist.size();
		faildreasone.selectByIndex(reasonsize-1);
		Thread.sleep(2000);
		String faildreasonedata=faildreasonlist.get(reasonsize-1).getText();
		Reporter.log("Reason for faild is           :"+faildreasonedata,true);

		if(CustVerification.btnSubmit.isEnabled())
		{Reporter.log("Submit Button is enabled ",true);}

		else
		{Reporter.log("Submit Button is disabled ",true);}

		Thread.sleep(3000);
		CustVerification.btnSubmit.click();	
		wait.until(ExpectedConditions.visibilityOf(CustVerification.submitbtnsuccessmsg));
		String Actual_message =CustVerification.submitbtnsuccessmsg.getText();
		String Expeted_message="Successfully Inserted/Updated Customer Details!";
		if(Actual_message.contains(Expeted_message))
		{
			Reporter.log("submit message is displayed ::"+Expeted_message,true);
			wait.until(ExpectedConditions.visibilityOf(CustVerification.submitbtnsuccessmsgok));
			CustVerification.submitbtnsuccessmsgok.click();
			Reporter.log("ok is clicked in success msg ",true);
		}

	}


}
