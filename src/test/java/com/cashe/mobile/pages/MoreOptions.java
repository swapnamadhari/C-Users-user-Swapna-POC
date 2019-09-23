package com.cashe.mobile.pages;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.cash.utilities.DataBaseConnection;
import com.cashe.base.BasePage;

public class MoreOptions extends BasePage {

	AppiumDriver driver;
	Hashtable<String, String> dataArray;
	ExtentTest eTest;

	@AndroidFindBy(id="action_more")
	@iOSFindBy(id="")
	public WebElement btnMore;

	@AndroidFindBy(id="title")
	@iOSFindBy(id="")
	public WebElement txtTitle;

	@AndroidFindBy(id="ll_mm_contact_us")
	@iOSFindBy(id="")
	public WebElement tabContactus;

	@AndroidFindBy(id="input_main_category")
	@iOSFindBy(id="")
	public WebElement tabMainCategory;

	@AndroidFindBy(id="input_sub_category")
	@iOSFindBy(id="")
	public WebElement tabSubCategory;

	@AndroidFindBy(id="input_comments")
	@iOSFindBy(id="")
	public WebElement txtComments;

	@AndroidFindBy(id="btn_report")
	@iOSFindBy(id="")
	public WebElement btnReport;

	@AndroidFindBy(id="popup_message")
	@iOSFindBy(id="")
	public WebElement popupFreshDesk;

	@AndroidFindBy(id="popup_positive_button")
	public WebElement  btnPopupOK;



	public MoreOptions(AppiumDriver driver,ExtentTest eTest,DataBaseConnection dbCon) {
		this.driver = driver;
		this.eTest=eTest;
		this.dbCon=dbCon;
		setDriver(driver);
		this.dataArray=dataArray;
		//PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		PageFactory.initElements(new AppiumFieldDecorator(driver, 30, TimeUnit.SECONDS), this);

	}

	//Click on More
	public void clickMore() throws InterruptedException, IOException{
		if(isElementClickable(driver, btnMore))
			clickonElement(driver, btnMore, "Succesfully clicked on More", "Unable to click on More");
		if(txtTitle.getText().contains("More")){
			capture(Status.INFO, "MoreScreen", eTest);
			clickonElement(driver, tabContactus, "", "");
			if(txtTitle.getText().contains("CONTACT US")){
				capture(Status.INFO, "ContactUsScreen", eTest);

			}
		}
	}
	//FreshDesk Code
	public void  contactUs() throws InterruptedException, IOException{
		try{

			//Select from Main Category
			Select select_maincategory = new Select(tabMainCategory);
			List<WebElement> maindroplist= select_maincategory.getOptions();
			select_maincategory.selectByIndex(generateRandom(maindroplist.size(), 2));

			//Select from Sub Category
			Select select_subcategory = new Select(tabSubCategory);
			List<WebElement> subdroplist= select_subcategory.getOptions();
			select_subcategory.selectByIndex(generateRandom(subdroplist.size(), 2));

			//Adding the comments
			if(isElementClickable(driver, txtComments))
				mobileSetElement(driver, txtComments, "Cashe Customer raising a ticket to the Fresh Desk from More >> Contact Us tab. Fresh Desk Ticket Raised - How to apply. Test Test Test Test Test Test Test Test Test Test Test ", "Sucessfully entered the comments in the CONTACT US screen", "Unable to enter in CONTACT US in screen");
			capture(Status.INFO, "ContactUs", eTest);
			if(isElementClickable(driver, btnReport))
				clickonElement(driver, btnReport, "Sucessfully clicked on Report button", "Unable to click on Report button");
			capture(Status.INFO, "ContactUs", eTest);
			if(isElementPresent(popupFreshDesk,15)){
				if(popupFreshDesk.getText().equalsIgnoreCase("Contact Us")){
					logger.info("FreshDesk pop up text is matching with expected text and the UI text is :"+popupFreshDesk.getText());
				}else{
					logger.info("FreshDesk pop up text is not matching with expected text and the UI text is: "+popupFreshDesk.getText());
				}                              
			}else{
				logger.info("FreshDesk conformation Pop up is not displayed");
			}
			capture(Status.INFO, "btnPopupOK", eTest);
			clickonElement(driver, btnPopupOK, "", "");

		}
		catch(Exception e){
			capture(Status.FAIL,dataArray.get("btnPopupOK"),eTest);
			e.printStackTrace();
		}
	}
}





